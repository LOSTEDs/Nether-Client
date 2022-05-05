/*
Decompiled By LOSTED
https://github.com/LOSTEDs
LOSTED#8754
https://www.youtube.com/watch?v=xg2M21todDU&t=55s
"...Minecraft client created by professional developers exclusively for me..." - SuchSpeed
Here is a better way to say this, "...Minecraft client skidded by some random script kittens exclusively for me"
Please SuchSpeed, don't sue me... I just dumped the source...
For Educational Purposes Only...
*/

package net.minecraft.client.network;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.status.INetHandlerStatusClient;
import net.minecraft.network.status.client.C00PacketServerQuery;
import net.minecraft.network.status.client.C01PacketPing;
import net.minecraft.network.status.server.S00PacketServerInfo;
import net.minecraft.network.status.server.S01PacketPong;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OldServerPinger {
    private static final Splitter PING_RESPONSE_SPLITTER = Splitter.on(false).limit(6);
    
    private static final Logger logger = LogManager.getLogger();
    
    private final List<NetworkManager> pingDestinations = Collections.synchronizedList(Lists.newArrayList());
    
    public void ping(final ServerData server) throws UnknownHostException {
        ServerAddress serveraddress = ServerAddress.func_78860_a(server.serverIP);
        final NetworkManager networkmanager = NetworkManager.func_181124_a(InetAddress.getByName(serveraddress.getIP()), serveraddress.getPort(), false);
        this.pingDestinations.add(networkmanager);
        server.serverMOTD = "Pinging...";
        server.pingToServer = -1L;
        server.playerList = null;
        networkmanager.setNetHandler((INetHandler)new INetHandlerStatusClient() {
                    private boolean field_147403_d = false;
                    
                    private boolean field_183009_e = false;
                    
                    private long field_175092_e = 0L;
                    
                    public void handleServerInfo(S00PacketServerInfo packetIn) {
                        if (this.field_183009_e) {
                            networkmanager.closeChannel((IChatComponent)new ChatComponentText("Received unrequested status"));
                        } else {
                            this.field_183009_e = true;
                            ServerStatusResponse serverstatusresponse = packetIn.getResponse();
                            if (serverstatusresponse.getServerDescription() != null) {
                                server.serverMOTD = serverstatusresponse.getServerDescription().getFormattedText();
                            } else {
                                server.serverMOTD = "";
                            } 
                            if (serverstatusresponse.getProtocolVersionInfo() != null) {
                                server.gameVersion = serverstatusresponse.getProtocolVersionInfo().getName();
                                server.version = serverstatusresponse.getProtocolVersionInfo().getProtocol();
                            } else {
                                server.gameVersion = "Old";
                                server.version = 0;
                            } 
                            if (serverstatusresponse.getPlayerCountData() != null) {
                                server.populationInfo = EnumChatFormatting.GRAY + serverstatusresponse.getPlayerCountData().getOnlinePlayerCount() + EnumChatFormatting.DARK_GRAY + "/" + EnumChatFormatting.GRAY + serverstatusresponse.getPlayerCountData().getMaxPlayers();
                                if (ArrayUtils.isNotEmpty((Object[])serverstatusresponse.getPlayerCountData().getPlayers())) {
                                    StringBuilder stringbuilder = new StringBuilder();
                                    byte b;
                                    int i;
                                    GameProfile[] arrayOfGameProfile;
                                    for (i = (arrayOfGameProfile = serverstatusresponse.getPlayerCountData().getPlayers()).length, b = 0; b < i; ) {
                                        GameProfile gameprofile = arrayOfGameProfile[b];
                                        if (stringbuilder.length() > 0)
                                            stringbuilder.append("\n"); 
                                        stringbuilder.append(gameprofile.getName());
                                        b++;
                                    } 
                                    if ((serverstatusresponse.getPlayerCountData().getPlayers()).length < serverstatusresponse.getPlayerCountData().getOnlinePlayerCount()) {
                                        if (stringbuilder.length() > 0)
                                            stringbuilder.append("\n"); 
                                        stringbuilder.append("... and ").append(serverstatusresponse.getPlayerCountData().getOnlinePlayerCount() - (serverstatusresponse.getPlayerCountData().getPlayers()).length).append(" more ...");
                                    } 
                                    server.playerList = stringbuilder.toString();
                                } 
                            } else {
                                server.populationInfo = EnumChatFormatting.DARK_GRAY + "???";
                            } 
                            if (serverstatusresponse.getFavicon() != null) {
                                String s = serverstatusresponse.getFavicon();
                                if (s.startsWith("data:image/png;base64,")) {
                                    server.setBase64EncodedIconData(s.substring("data:image/png;base64,".length()));
                                } else {
                                    OldServerPinger.logger.error("Invalid server icon (unknown format)");
                                } 
                            } else {
                                server.setBase64EncodedIconData(null);
                            } 
                            this.field_175092_e = Minecraft.getSystemTime();
                            networkmanager.sendPacket((Packet)new C01PacketPing(this.field_175092_e));
                            this.field_147403_d = true;
                        } 
                    }
                    
                    public void handlePong(S01PacketPong packetIn) {
                        long i = this.field_175092_e;
                        long j = Minecraft.getSystemTime();
                        server.pingToServer = j - i;
                        networkmanager.closeChannel((IChatComponent)new ChatComponentText("Finished"));
                    }
                    
                    public void onDisconnect(IChatComponent reason) {
                        if (!this.field_147403_d) {
                            OldServerPinger.logger.error("Can't ping " + server.serverIP + ": " + reason.getUnformattedText());
                            server.serverMOTD = EnumChatFormatting.DARK_RED + "Can't connect to server.";
                            server.populationInfo = "";
                            OldServerPinger.this.tryCompatibilityPing(server);
                        } 
                    }
                });
        try {
            networkmanager.sendPacket((Packet)new C00Handshake(47, serveraddress.getIP(), serveraddress.getPort(), EnumConnectionState.STATUS));
            networkmanager.sendPacket((Packet)new C00PacketServerQuery());
        } catch (Throwable throwable) {
            logger.error(throwable);
        } 
    }
    
    private void tryCompatibilityPing(final ServerData server) {
        final ServerAddress serveraddress = ServerAddress.func_78860_a(server.serverIP);
        ((Bootstrap)((Bootstrap)((Bootstrap)(new Bootstrap()).group((EventLoopGroup)NetworkManager.CLIENT_NIO_EVENTLOOP.getValue())).handler((ChannelHandler)new ChannelInitializer<Channel>() {
                    protected void initChannel(Channel p_initChannel_1_) throws Exception {
                        try {
                            p_initChannel_1_.config().setOption(ChannelOption.TCP_NODELAY, Boolean.valueOf(true));
                        } catch (ChannelException channelException) {}
                        p_initChannel_1_.pipeline().addLast(new ChannelHandler[] { (ChannelHandler)new SimpleChannelInboundHandler<ByteBuf>() {
                                        public void channelActive(ChannelHandlerContext p_channelActive_1_) throws Exception {
                                            super.channelActive(p_channelActive_1_);
                                            ByteBuf bytebuf = Unpooled.buffer();
                                            try {
                                                bytebuf.writeByte(254);
                                                bytebuf.writeByte(1);
                                                bytebuf.writeByte(250);
                                                char[] achar = "MC|PingHost".toCharArray();
                                                bytebuf.writeShort(achar.length);
                                                byte b;
                                                int i;
                                                char[] arrayOfChar1;
                                                for (i = (arrayOfChar1 = achar).length, b = 0; b < i; ) {
                                                    char c0 = arrayOfChar1[b];
                                                    bytebuf.writeChar(c0);
                                                    b++;
                                                } 
                                                bytebuf.writeShort(7 + 2 * serveraddress.getIP().length());
                                                bytebuf.writeByte(127);
                                                achar = serveraddress.getIP().toCharArray();
                                                bytebuf.writeShort(achar.length);
                                                for (i = (arrayOfChar1 = achar).length, b = 0; b < i; ) {
                                                    char c1 = arrayOfChar1[b];
                                                    bytebuf.writeChar(c1);
                                                    b++;
                                                } 
                                                bytebuf.writeInt(serveraddress.getPort());
                                                p_channelActive_1_.channel().writeAndFlush(bytebuf).addListener((GenericFutureListener)ChannelFutureListener.CLOSE_ON_FAILURE);
                                            } finally {
                                                bytebuf.release();
                                            } 
                                        }
                                        
                                        protected void channelRead0(ChannelHandlerContext p_channelRead0_1_, ByteBuf p_channelRead0_2_) throws Exception {
                                            short short1 = p_channelRead0_2_.readUnsignedByte();
                                            if (short1 == 255) {
                                                String s = new String(p_channelRead0_2_.readBytes(p_channelRead0_2_.readShort() * 2).array(), Charsets.UTF_16BE);
                                                String[] astring = (String[])Iterables.toArray(OldServerPinger.PING_RESPONSE_SPLITTER.split(s), String.class);
                                                if ("§1".equals(astring[0])) {
                                                    int i = MathHelper.parseIntWithDefault(astring[1], 0);
                                                    String s1 = astring[2];
                                                    String s2 = astring[3];
                                                    int j = MathHelper.parseIntWithDefault(astring[4], -1);
                                                    int k = MathHelper.parseIntWithDefault(astring[5], -1);
                                                    server.version = -1;
                                                    server.gameVersion = s1;
                                                    server.serverMOTD = s2;
                                                    server.populationInfo = EnumChatFormatting.GRAY + j + EnumChatFormatting.DARK_GRAY + "/" + EnumChatFormatting.GRAY + k;
                                                } 
                                            } 
                                            p_channelRead0_1_.close();
                                        }
                                        
                                        public void exceptionCaught(ChannelHandlerContext p_exceptionCaught_1_, Throwable p_exceptionCaught_2_) throws Exception {
                                            p_exceptionCaught_1_.close();
                                        }
                                    } });
                    }
                })).channel(NioSocketChannel.class)).connect(serveraddress.getIP(), serveraddress.getPort());
    }
    
    public void pingPendingNetworks() {
        synchronized (this.pingDestinations) {
            Iterator<NetworkManager> iterator = this.pingDestinations.iterator();
            while (iterator.hasNext()) {
                NetworkManager networkmanager = iterator.next();
                if (networkmanager.isChannelOpen()) {
                    networkmanager.processReceivedPackets();
                    continue;
                } 
                iterator.remove();
                networkmanager.checkDisconnected();
            } 
        } 
    }
    
    public void clearPendingNetworks() {
        synchronized (this.pingDestinations) {
            Iterator<NetworkManager> iterator = this.pingDestinations.iterator();
            while (iterator.hasNext()) {
                NetworkManager networkmanager = iterator.next();
                if (networkmanager.isChannelOpen()) {
                    iterator.remove();
                    networkmanager.closeChannel((IChatComponent)new ChatComponentText("Cancelled"));
                } 
            } 
        } 
    }
}

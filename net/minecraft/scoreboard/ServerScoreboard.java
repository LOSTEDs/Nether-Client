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

package net.minecraft.scoreboard;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.network.play.server.S3DPacketDisplayScoreboard;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.server.MinecraftServer;

public class ServerScoreboard extends Scoreboard {
    private final MinecraftServer scoreboardMCServer;
    
    private final Set<ScoreObjective> field_96553_b = Sets.newHashSet();
    
    private ScoreboardSaveData scoreboardSaveData;
    
    public ServerScoreboard(MinecraftServer mcServer) {
        this.scoreboardMCServer = mcServer;
    }
    
    public void func_96536_a(Score p_96536_1_) {
        super.func_96536_a(p_96536_1_);
        if (this.field_96553_b.contains(p_96536_1_.getObjective()))
            this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3CPacketUpdateScore(p_96536_1_)); 
        func_96551_b();
    }
    
    public void func_96516_a(String p_96516_1_) {
        super.func_96516_a(p_96516_1_);
        this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3CPacketUpdateScore(p_96516_1_));
        func_96551_b();
    }
    
    public void func_178820_a(String p_178820_1_, ScoreObjective p_178820_2_) {
        super.func_178820_a(p_178820_1_, p_178820_2_);
        this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3CPacketUpdateScore(p_178820_1_, p_178820_2_));
        func_96551_b();
    }
    
    public void setObjectiveInDisplaySlot(int p_96530_1_, ScoreObjective p_96530_2_) {
        ScoreObjective scoreobjective = getObjectiveInDisplaySlot(p_96530_1_);
        super.setObjectiveInDisplaySlot(p_96530_1_, p_96530_2_);
        if (scoreobjective != p_96530_2_ && scoreobjective != null)
            if (func_96552_h(scoreobjective) > 0) {
                this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3DPacketDisplayScoreboard(p_96530_1_, p_96530_2_));
            } else {
                getPlayerIterator(scoreobjective);
            }  
        if (p_96530_2_ != null)
            if (this.field_96553_b.contains(p_96530_2_)) {
                this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3DPacketDisplayScoreboard(p_96530_1_, p_96530_2_));
            } else {
                func_96549_e(p_96530_2_);
            }  
        func_96551_b();
    }
    
    public boolean addPlayerToTeam(String player, String newTeam) {
        if (super.addPlayerToTeam(player, newTeam)) {
            ScorePlayerTeam scoreplayerteam = getTeam(newTeam);
            this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3EPacketTeams(scoreplayerteam, Arrays.asList(new String[] { player }, ), 3));
            func_96551_b();
            return true;
        } 
        return false;
    }
    
    public void removePlayerFromTeam(String p_96512_1_, ScorePlayerTeam p_96512_2_) {
        super.removePlayerFromTeam(p_96512_1_, p_96512_2_);
        this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3EPacketTeams(p_96512_2_, Arrays.asList(new String[] { p_96512_1_ }, ), 4));
        func_96551_b();
    }
    
    public void onScoreObjectiveAdded(ScoreObjective scoreObjectiveIn) {
        super.onScoreObjectiveAdded(scoreObjectiveIn);
        func_96551_b();
    }
    
    public void func_96532_b(ScoreObjective p_96532_1_) {
        super.func_96532_b(p_96532_1_);
        if (this.field_96553_b.contains(p_96532_1_))
            this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3BPacketScoreboardObjective(p_96532_1_, 2)); 
        func_96551_b();
    }
    
    public void func_96533_c(ScoreObjective p_96533_1_) {
        super.func_96533_c(p_96533_1_);
        if (this.field_96553_b.contains(p_96533_1_))
            getPlayerIterator(p_96533_1_); 
        func_96551_b();
    }
    
    public void broadcastTeamCreated(ScorePlayerTeam playerTeam) {
        super.broadcastTeamCreated(playerTeam);
        this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3EPacketTeams(playerTeam, 0));
        func_96551_b();
    }
    
    public void sendTeamUpdate(ScorePlayerTeam playerTeam) {
        super.sendTeamUpdate(playerTeam);
        this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3EPacketTeams(playerTeam, 2));
        func_96551_b();
    }
    
    public void func_96513_c(ScorePlayerTeam playerTeam) {
        super.func_96513_c(playerTeam);
        this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3EPacketTeams(playerTeam, 1));
        func_96551_b();
    }
    
    public void func_96547_a(ScoreboardSaveData p_96547_1_) {
        this.scoreboardSaveData = p_96547_1_;
    }
    
    protected void func_96551_b() {
        if (this.scoreboardSaveData != null)
            this.scoreboardSaveData.markDirty(); 
    }
    
    public List<Packet> func_96550_d(ScoreObjective p_96550_1_) {
        List<Packet> list = Lists.newArrayList();
        list.add(new S3BPacketScoreboardObjective(p_96550_1_, 0));
        for (int i = 0; i < 19; i++) {
            if (getObjectiveInDisplaySlot(i) == p_96550_1_)
                list.add(new S3DPacketDisplayScoreboard(i, p_96550_1_)); 
        } 
        for (Score score : getSortedScores(p_96550_1_))
            list.add(new S3CPacketUpdateScore(score)); 
        return list;
    }
    
    public void func_96549_e(ScoreObjective p_96549_1_) {
        List<Packet> list = func_96550_d(p_96549_1_);
        for (EntityPlayerMP entityplayermp : this.scoreboardMCServer.getConfigurationManager().func_181057_v()) {
            for (Packet packet : list)
                entityplayermp.playerNetServerHandler.sendPacket(packet); 
        } 
        this.field_96553_b.add(p_96549_1_);
    }
    
    public List<Packet> func_96548_f(ScoreObjective p_96548_1_) {
        List<Packet> list = Lists.newArrayList();
        list.add(new S3BPacketScoreboardObjective(p_96548_1_, 1));
        for (int i = 0; i < 19; i++) {
            if (getObjectiveInDisplaySlot(i) == p_96548_1_)
                list.add(new S3DPacketDisplayScoreboard(i, p_96548_1_)); 
        } 
        return list;
    }
    
    public void getPlayerIterator(ScoreObjective p_96546_1_) {
        List<Packet> list = func_96548_f(p_96546_1_);
        for (EntityPlayerMP entityplayermp : this.scoreboardMCServer.getConfigurationManager().func_181057_v()) {
            for (Packet packet : list)
                entityplayermp.playerNetServerHandler.sendPacket(packet); 
        } 
        this.field_96553_b.remove(p_96546_1_);
    }
    
    public int func_96552_h(ScoreObjective p_96552_1_) {
        int i = 0;
        for (int j = 0; j < 19; j++) {
            if (getObjectiveInDisplaySlot(j) == p_96552_1_)
                i++; 
        } 
        return i;
    }
}

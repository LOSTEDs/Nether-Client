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

package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.WorldServer;

public class CommandSaveAll extends CommandBase {
    public String getCommandName() {
        return "save-all";
    }
    
    public String getCommandUsage(ICommandSender sender) {
        return "commands.save.usage";
    }
    
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        MinecraftServer minecraftserver = MinecraftServer.getServer();
        sender.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.save.start", new Object[0]));
        if (minecraftserver.getConfigurationManager() != null)
            minecraftserver.getConfigurationManager().saveAllPlayerData(); 
        try {
            for (int i = 0; i < minecraftserver.worldServers.length; i++) {
                if (minecraftserver.worldServers[i] != null) {
                    WorldServer worldserver = minecraftserver.worldServers[i];
                    boolean flag = worldserver.disableLevelSaving;
                    worldserver.disableLevelSaving = false;
                    worldserver.saveAllChunks(true, null);
                    worldserver.disableLevelSaving = flag;
                } 
            } 
            if (args.length > 0 && "flush".equals(args[0])) {
                sender.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.save.flushStart", new Object[0]));
                for (int j = 0; j < minecraftserver.worldServers.length; j++) {
                    if (minecraftserver.worldServers[j] != null) {
                        WorldServer worldserver1 = minecraftserver.worldServers[j];
                        boolean flag1 = worldserver1.disableLevelSaving;
                        worldserver1.disableLevelSaving = false;
                        worldserver1.saveChunkData();
                        worldserver1.disableLevelSaving = flag1;
                    } 
                } 
                sender.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.save.flushEnd", new Object[0]));
            } 
        } catch (MinecraftException minecraftexception) {
            notifyOperators(sender, (ICommand)this, "commands.save.failed", new Object[] { minecraftexception.getMessage() });
            return;
        } 
        notifyOperators(sender, (ICommand)this, "commands.save.success", new Object[0]);
    }
}

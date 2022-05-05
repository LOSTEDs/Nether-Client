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
import net.minecraft.world.WorldServer;

public class CommandSaveOn extends CommandBase {
    public String getCommandName() {
        return "save-on";
    }
    
    public String getCommandUsage(ICommandSender sender) {
        return "commands.save-on.usage";
    }
    
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        MinecraftServer minecraftserver = MinecraftServer.getServer();
        boolean flag = false;
        for (int i = 0; i < minecraftserver.worldServers.length; i++) {
            if (minecraftserver.worldServers[i] != null) {
                WorldServer worldserver = minecraftserver.worldServers[i];
                if (worldserver.disableLevelSaving) {
                    worldserver.disableLevelSaving = false;
                    flag = true;
                } 
            } 
        } 
        if (flag) {
            notifyOperators(sender, (ICommand)this, "commands.save.enabled", new Object[0]);
        } else {
            throw new CommandException("commands.save-on.alreadyOn", new Object[0]);
        } 
    }
}

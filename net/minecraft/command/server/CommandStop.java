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

public class CommandStop extends CommandBase {
    public String getCommandName() {
        return "stop";
    }
    
    public String getCommandUsage(ICommandSender sender) {
        return "commands.stop.usage";
    }
    
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if ((MinecraftServer.getServer()).worldServers != null)
            notifyOperators(sender, (ICommand)this, "commands.stop.start", new Object[0]); 
        MinecraftServer.getServer().initiateShutdown();
    }
}

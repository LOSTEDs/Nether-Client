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

package net.minecraft.command;

import net.minecraft.server.MinecraftServer;

public class CommandSetPlayerTimeout extends CommandBase {
    public String getCommandName() {
        return "setidletimeout";
    }
    
    public int getRequiredPermissionLevel() {
        return 3;
    }
    
    public String getCommandUsage(ICommandSender sender) {
        return "commands.setidletimeout.usage";
    }
    
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 1)
            throw new WrongUsageException("commands.setidletimeout.usage", new Object[0]); 
        int i = parseInt(args[0], 0);
        MinecraftServer.getServer().setPlayerIdleTimeout(i);
        notifyOperators(sender, this, "commands.setidletimeout.success", new Object[] { Integer.valueOf(i) });
    }
}

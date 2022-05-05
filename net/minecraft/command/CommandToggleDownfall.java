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
import net.minecraft.world.storage.WorldInfo;

public class CommandToggleDownfall extends CommandBase {
    public String getCommandName() {
        return "toggledownfall";
    }
    
    public int getRequiredPermissionLevel() {
        return 2;
    }
    
    public String getCommandUsage(ICommandSender sender) {
        return "commands.downfall.usage";
    }
    
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        toggleDownfall();
        notifyOperators(sender, this, "commands.downfall.success", new Object[0]);
    }
    
    protected void toggleDownfall() {
        WorldInfo worldinfo = (MinecraftServer.getServer()).worldServers[0].getWorldInfo();
        worldinfo.setRaining(!worldinfo.isRaining());
    }
}

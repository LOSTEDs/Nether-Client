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

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandKill extends CommandBase {
    public String getCommandName() {
        return "kill";
    }
    
    public int getRequiredPermissionLevel() {
        return 2;
    }
    
    public String getCommandUsage(ICommandSender sender) {
        return "commands.kill.usage";
    }
    
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            EntityPlayerMP entityPlayerMP = getCommandSenderAsPlayer(sender);
            entityPlayerMP.onKillCommand();
            notifyOperators(sender, this, "commands.kill.successful", new Object[] { entityPlayerMP.getDisplayName() });
        } else {
            Entity entity = func_175768_b(sender, args[0]);
            entity.onKillCommand();
            notifyOperators(sender, this, "commands.kill.successful", new Object[] { entity.getDisplayName() });
        } 
    }
    
    public boolean isUsernameIndex(String[] args, int index) {
        return (index == 0);
    }
    
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return (args.length == 1) ? getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames()) : null;
    }
}

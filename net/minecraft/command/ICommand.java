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
import net.minecraft.util.BlockPos;

public interface ICommand extends Comparable<ICommand> {
    String getCommandName();
    
    String getCommandUsage(ICommandSender paramICommandSender);
    
    List<String> getCommandAliases();
    
    void processCommand(ICommandSender paramICommandSender, String[] paramArrayOfString) throws CommandException;
    
    boolean canCommandSenderUseCommand(ICommandSender paramICommandSender);
    
    List<String> addTabCompletionOptions(ICommandSender paramICommandSender, String[] paramArrayOfString, BlockPos paramBlockPos);
    
    boolean isUsernameIndex(String[] paramArrayOfString, int paramInt);
}

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

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public interface ICommandSender {
    String getName();
    
    IChatComponent getDisplayName();
    
    void addChatMessage(IChatComponent paramIChatComponent);
    
    boolean canCommandSenderUseCommand(int paramInt, String paramString);
    
    BlockPos getPosition();
    
    Vec3 getPositionVector();
    
    World getEntityWorld();
    
    Entity getCommandSenderEntity();
    
    boolean sendCommandFeedback();
    
    void setCommandStat(CommandResultStats.Type paramType, int paramInt);
}

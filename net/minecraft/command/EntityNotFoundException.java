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

public class EntityNotFoundException extends CommandException {
    public EntityNotFoundException() {
        this("commands.generic.entity.notFound", new Object[0]);
    }
    
    public EntityNotFoundException(String p_i46035_1_, Object... p_i46035_2_) {
        super(p_i46035_1_, p_i46035_2_);
    }
}

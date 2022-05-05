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

package net.minecraft.util;

public class ChatComponentTranslationFormatException extends IllegalArgumentException {
    public ChatComponentTranslationFormatException(ChatComponentTranslation component, String message) {
        super(String.format("Error parsing: %s: %s", new Object[] { component, message }));
    }
    
    public ChatComponentTranslationFormatException(ChatComponentTranslation component, int index) {
        super(String.format("Invalid index %d requested for %s", new Object[] { Integer.valueOf(index), component }));
    }
    
    public ChatComponentTranslationFormatException(ChatComponentTranslation component, Throwable cause) {
        super(String.format("Error while parsing: %s", new Object[] { component }), cause);
    }
}

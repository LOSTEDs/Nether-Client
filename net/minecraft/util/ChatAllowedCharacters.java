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

public class ChatAllowedCharacters {
    public static final char[] allowedCharactersArray = new char[] { 
            '/', '\n', '\r', '\t', '\f', '`', '?', '*', '\\', '<', 
            '>', '|', '"', ':' };
    
    public static boolean isAllowedCharacter(char character) {
        return (character != '§' && character >= ' ' && character != '');
    }
    
    public static String filterAllowedCharacters(String input) {
        StringBuilder stringbuilder = new StringBuilder();
        byte b;
        int i;
        char[] arrayOfChar;
        for (i = (arrayOfChar = input.toCharArray()).length, b = 0; b < i; ) {
            char c0 = arrayOfChar[b];
            if (isAllowedCharacter(c0))
                stringbuilder.append(c0); 
            b++;
        } 
        return stringbuilder.toString();
    }
}

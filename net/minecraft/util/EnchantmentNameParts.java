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

import java.util.Random;

public class EnchantmentNameParts {
    private static final EnchantmentNameParts instance = new EnchantmentNameParts();
    
    private Random rand = new Random();
    
    private String[] namePartsArray = "the elder scrolls klaatu berata niktu xyzzy bless curse light darkness fire air earth water hot dry cold wet ignite snuff embiggen twist shorten stretch fiddle destroy imbue galvanize enchant free limited range of towards inside sphere cube self other ball mental physical grow shrink demon elemental spirit animal creature beast humanoid undead fresh stale ".split(" ");
    
    public static EnchantmentNameParts getInstance() {
        return instance;
    }
    
    public String generateNewRandomName() {
        int i = this.rand.nextInt(2) + 3;
        String s = "";
        for (int j = 0; j < i; j++) {
            if (j > 0)
                s = String.valueOf(s) + " "; 
            s = String.valueOf(s) + this.namePartsArray[this.rand.nextInt(this.namePartsArray.length)];
        } 
        return s;
    }
    
    public void reseedRandomGenerator(long seed) {
        this.rand.setSeed(seed);
    }
}

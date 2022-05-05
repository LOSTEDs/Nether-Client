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

package net.minecraft.world;

public enum EnumDifficulty {
    PEACEFUL(0, "options.difficulty.peaceful"),
    EASY(1, "options.difficulty.easy"),
    NORMAL(2, "options.difficulty.normal"),
    HARD(3, "options.difficulty.hard");
    
    private static final EnumDifficulty[] difficultyEnums;
    
    private final int difficultyId;
    
    private final String difficultyResourceKey;
    
    static {
        difficultyEnums = new EnumDifficulty[(values()).length];
        byte b;
        int i;
        EnumDifficulty[] arrayOfEnumDifficulty;
        for (i = (arrayOfEnumDifficulty = values()).length, b = 0; b < i; ) {
            EnumDifficulty enumdifficulty = arrayOfEnumDifficulty[b];
            difficultyEnums[enumdifficulty.difficultyId] = enumdifficulty;
            b++;
        } 
    }
    
    EnumDifficulty(int difficultyIdIn, String difficultyResourceKeyIn) {
        this.difficultyId = difficultyIdIn;
        this.difficultyResourceKey = difficultyResourceKeyIn;
    }
    
    public int getDifficultyId() {
        return this.difficultyId;
    }
    
    public static EnumDifficulty getDifficultyEnum(int p_151523_0_) {
        return difficultyEnums[p_151523_0_ % difficultyEnums.length];
    }
    
    public String getDifficultyResourceKey() {
        return this.difficultyResourceKey;
    }
}

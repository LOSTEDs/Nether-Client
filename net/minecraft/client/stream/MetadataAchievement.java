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

package net.minecraft.client.stream;

import net.minecraft.stats.Achievement;

public class MetadataAchievement extends Metadata {
    public MetadataAchievement(Achievement p_i1032_1_) {
        super("achievement");
        func_152808_a("achievement_id", p_i1032_1_.statId);
        func_152808_a("achievement_name", p_i1032_1_.getStatName().getUnformattedText());
        func_152808_a("achievement_description", p_i1032_1_.getDescription());
        func_152807_a("Achievement '" + p_i1032_1_.getStatName().getUnformattedText() + "' obtained!");
    }
}

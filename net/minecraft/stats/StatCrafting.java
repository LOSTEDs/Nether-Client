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

package net.minecraft.stats;

import net.minecraft.item.Item;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.util.IChatComponent;

public class StatCrafting extends StatBase {
    private final Item field_150960_a;
    
    public StatCrafting(String p_i45910_1_, String p_i45910_2_, IChatComponent statNameIn, Item p_i45910_4_) {
        super(String.valueOf(p_i45910_1_) + p_i45910_2_, statNameIn);
        this.field_150960_a = p_i45910_4_;
        int i = Item.getIdFromItem(p_i45910_4_);
        if (i != 0)
            IScoreObjectiveCriteria.INSTANCES.put(String.valueOf(p_i45910_1_) + i, func_150952_k()); 
    }
    
    public Item func_150959_a() {
        return this.field_150960_a;
    }
}

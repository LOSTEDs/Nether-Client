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

import net.minecraft.util.IChatComponent;

public class StatBasic extends StatBase {
    public StatBasic(String statIdIn, IChatComponent statNameIn, IStatType typeIn) {
        super(statIdIn, statNameIn, typeIn);
    }
    
    public StatBasic(String statIdIn, IChatComponent statNameIn) {
        super(statIdIn, statNameIn);
    }
    
    public StatBase registerStat() {
        super.registerStat();
        StatList.generalStats.add(this);
        return this;
    }
}

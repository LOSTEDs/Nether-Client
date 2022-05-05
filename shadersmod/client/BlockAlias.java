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

package shadersmod.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.src.Config;
import net.minecraft.src.MatchBlock;

public class BlockAlias {
    private int blockId;
    
    private MatchBlock[] matchBlocks;
    
    public BlockAlias(int blockId, MatchBlock[] matchBlocks) {
        this.blockId = blockId;
        this.matchBlocks = matchBlocks;
    }
    
    public int getBlockId() {
        return this.blockId;
    }
    
    public boolean matches(int id, int metadata) {
        for (int i = 0; i < this.matchBlocks.length; i++) {
            MatchBlock matchblock = this.matchBlocks[i];
            if (matchblock.matches(id, metadata))
                return true; 
        } 
        return false;
    }
    
    public int[] getMatchBlockIds() {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < this.matchBlocks.length; i++) {
            MatchBlock matchblock = this.matchBlocks[i];
            int j = matchblock.getBlockId();
            set.add(Integer.valueOf(j));
        } 
        Integer[] ainteger = set.<Integer>toArray(new Integer[set.size()]);
        int[] aint = Config.toPrimitive(ainteger);
        return aint;
    }
    
    public MatchBlock[] getMatchBlocks(int matchBlockId) {
        List<MatchBlock> list = new ArrayList<>();
        for (int i = 0; i < this.matchBlocks.length; i++) {
            MatchBlock matchblock = this.matchBlocks[i];
            if (matchblock.getBlockId() == matchBlockId)
                list.add(matchblock); 
        } 
        MatchBlock[] amatchblock = list.<MatchBlock>toArray(new MatchBlock[list.size()]);
        return amatchblock;
    }
    
    public String toString() {
        return "block." + this.blockId + "=" + Config.arrayToString((Object[])this.matchBlocks);
    }
}

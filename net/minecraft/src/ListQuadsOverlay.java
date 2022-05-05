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

package net.minecraft.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.init.Blocks;

public class ListQuadsOverlay {
    private List<BakedQuad> listQuads = new ArrayList<>();
    
    private List<IBlockState> listBlockStates = new ArrayList<>();
    
    private List<BakedQuad> listQuadsSingle = Arrays.asList(new BakedQuad[1]);
    
    public void addQuad(BakedQuad p_addQuad_1_, IBlockState p_addQuad_2_) {
        this.listQuads.add(p_addQuad_1_);
        this.listBlockStates.add(p_addQuad_2_);
    }
    
    public int size() {
        return this.listQuads.size();
    }
    
    public BakedQuad getQuad(int p_getQuad_1_) {
        return this.listQuads.get(p_getQuad_1_);
    }
    
    public IBlockState getBlockState(int p_getBlockState_1_) {
        return (p_getBlockState_1_ >= 0 && p_getBlockState_1_ < this.listBlockStates.size()) ? this.listBlockStates.get(p_getBlockState_1_) : Blocks.air.getDefaultState();
    }
    
    public List<BakedQuad> getListQuadsSingle(BakedQuad p_getListQuadsSingle_1_) {
        this.listQuadsSingle.set(0, p_getListQuadsSingle_1_);
        return this.listQuadsSingle;
    }
    
    public void clear() {
        this.listQuads.clear();
        this.listBlockStates.clear();
    }
}

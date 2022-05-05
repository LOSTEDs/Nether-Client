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

package net.minecraft.client.renderer.chunk;

import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;

public class ListedRenderChunk extends RenderChunk {
    private final int baseDisplayList = GLAllocation.generateDisplayLists((EnumWorldBlockLayer.values()).length);
    
    public ListedRenderChunk(World worldIn, RenderGlobal renderGlobalIn, BlockPos pos, int indexIn) {
        super(worldIn, renderGlobalIn, pos, indexIn);
    }
    
    public int getDisplayList(EnumWorldBlockLayer layer, CompiledChunk p_178600_2_) {
        return !p_178600_2_.isLayerEmpty(layer) ? (this.baseDisplayList + layer.ordinal()) : -1;
    }
    
    public void deleteGlResources() {
        super.deleteGlResources();
        GLAllocation.deleteDisplayLists(this.baseDisplayList, (EnumWorldBlockLayer.values()).length);
    }
}

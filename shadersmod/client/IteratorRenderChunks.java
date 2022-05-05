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

import java.util.Iterator;
import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.src.BlockPosM;
import net.minecraft.util.BlockPos;

public class IteratorRenderChunks implements Iterator<RenderChunk> {
    private ViewFrustum viewFrustum;
    
    private Iterator3d Iterator3d;
    
    private BlockPosM posBlock = new BlockPosM(0, 0, 0);
    
    public IteratorRenderChunks(ViewFrustum viewFrustum, BlockPos posStart, BlockPos posEnd, int width, int height) {
        this.viewFrustum = viewFrustum;
        this.Iterator3d = new Iterator3d(posStart, posEnd, width, height);
    }
    
    public boolean hasNext() {
        return this.Iterator3d.hasNext();
    }
    
    public RenderChunk next() {
        BlockPos blockpos = this.Iterator3d.next();
        this.posBlock.setXyz(blockpos.getX() << 4, blockpos.getY() << 4, blockpos.getZ() << 4);
        RenderChunk renderchunk = this.viewFrustum.getRenderChunk((BlockPos)this.posBlock);
        return renderchunk;
    }
    
    public void remove() {
        throw new RuntimeException("Not implemented");
    }
}

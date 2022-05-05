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

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.chunk.RenderChunk;

public class RenderInfoLazy {
    private RenderChunk renderChunk;
    
    private RenderGlobal.ContainerLocalRenderInformation renderInfo;
    
    public RenderChunk getRenderChunk() {
        return this.renderChunk;
    }
    
    public void setRenderChunk(RenderChunk p_setRenderChunk_1_) {
        this.renderChunk = p_setRenderChunk_1_;
        this.renderInfo = null;
    }
    
    public RenderGlobal.ContainerLocalRenderInformation getRenderInfo() {
        if (this.renderInfo == null)
            this.renderInfo = new RenderGlobal.ContainerLocalRenderInformation(this.renderChunk, null, 0); 
        return this.renderInfo;
    }
}

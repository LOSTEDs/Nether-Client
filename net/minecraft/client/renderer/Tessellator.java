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

package net.minecraft.client.renderer;

public class Tessellator {
    private WorldRenderer worldRenderer;
    
    private WorldVertexBufferUploader vboUploader = new WorldVertexBufferUploader();
    
    private static final Tessellator instance = new Tessellator(2097152);
    
    public static Tessellator getInstance() {
        return instance;
    }
    
    public Tessellator(int bufferSize) {
        this.worldRenderer = new WorldRenderer(bufferSize);
    }
    
    public void draw() {
        this.worldRenderer.finishDrawing();
        this.vboUploader.func_181679_a(this.worldRenderer);
    }
    
    public WorldRenderer getWorldRenderer() {
        return this.worldRenderer;
    }
}

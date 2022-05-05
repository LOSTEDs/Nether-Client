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

package net.minecraft.client.model;

import net.minecraft.util.Vec3;

public class PositionTextureVertex {
    public Vec3 vector3D;
    
    public float texturePositionX;
    
    public float texturePositionY;
    
    public PositionTextureVertex(float p_i1158_1_, float p_i1158_2_, float p_i1158_3_, float p_i1158_4_, float p_i1158_5_) {
        this(new Vec3(p_i1158_1_, p_i1158_2_, p_i1158_3_), p_i1158_4_, p_i1158_5_);
    }
    
    public PositionTextureVertex setTexturePosition(float p_78240_1_, float p_78240_2_) {
        return new PositionTextureVertex(this, p_78240_1_, p_78240_2_);
    }
    
    public PositionTextureVertex(PositionTextureVertex textureVertex, float texturePositionXIn, float texturePositionYIn) {
        this.vector3D = textureVertex.vector3D;
        this.texturePositionX = texturePositionXIn;
        this.texturePositionY = texturePositionYIn;
    }
    
    public PositionTextureVertex(Vec3 vector3DIn, float texturePositionXIn, float texturePositionYIn) {
        this.vector3D = vector3DIn;
        this.texturePositionX = texturePositionXIn;
        this.texturePositionY = texturePositionYIn;
    }
}

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

public class ModelSign extends ModelBase {
    public ModelRenderer signBoard = new ModelRenderer(this, 0, 0);
    
    public ModelRenderer signStick;
    
    public ModelSign() {
        this.signBoard.addBox(-12.0F, -14.0F, -1.0F, 24, 12, 2, 0.0F);
        this.signStick = new ModelRenderer(this, 0, 14);
        this.signStick.addBox(-1.0F, -2.0F, -1.0F, 2, 14, 2, 0.0F);
    }
    
    public void renderSign() {
        this.signBoard.render(0.0625F);
        this.signStick.render(0.0625F);
    }
}

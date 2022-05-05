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

package net.optifine.entity.model.anim;

import net.minecraft.client.model.ModelRenderer;

public class ModelVariableFloat implements IExpressionFloat {
    private String name;
    
    private ModelRenderer modelRenderer;
    
    private ModelVariableType enumModelVariable;
    
    public ModelVariableFloat(String name, ModelRenderer modelRenderer, ModelVariableType enumModelVariable) {
        this.name = name;
        this.modelRenderer = modelRenderer;
        this.enumModelVariable = enumModelVariable;
    }
    
    public ExpressionType getExpressionType() {
        return ExpressionType.FLOAT;
    }
    
    public float eval() {
        return getValue();
    }
    
    public float getValue() {
        return this.enumModelVariable.getFloat(this.modelRenderer);
    }
    
    public void setValue(float value) {
        this.enumModelVariable.setFloat(this.modelRenderer, value);
    }
    
    public String toString() {
        return this.name;
    }
}

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

import net.minecraft.src.Config;

public class ModelVariableUpdater {
    private String modelVariableName;
    
    private String expressionText;
    
    private ModelVariableFloat modelVariable;
    
    private IExpressionFloat expression;
    
    public boolean initialize(IModelResolver mr) {
        this.modelVariable = mr.getModelVariable(this.modelVariableName);
        if (this.modelVariable == null) {
            Config.warn("Model variable not found: " + this.modelVariableName);
            return false;
        } 
        try {
            ExpressionParser expressionparser = new ExpressionParser(mr);
            this.expression = expressionparser.parseFloat(this.expressionText);
            return true;
        } catch (ParseException parseexception) {
            Config.warn("Error parsing expression: " + this.expressionText);
            Config.warn(String.valueOf(parseexception.getClass().getName()) + ": " + parseexception.getMessage());
            return false;
        } 
    }
    
    public ModelVariableUpdater(String modelVariableName, String expressionText) {
        this.modelVariableName = modelVariableName;
        this.expressionText = expressionText;
    }
    
    public void update() {
        float f = this.expression.eval();
        this.modelVariable.setValue(f);
    }
}

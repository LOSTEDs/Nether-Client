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

public class FunctionBool implements IExpressionBool {
    private FunctionType type;
    
    private IExpression[] arguments;
    
    public FunctionBool(FunctionType type, IExpression[] arguments) {
        this.type = type;
        this.arguments = arguments;
    }
    
    public boolean eval() {
        return this.type.evalBool(this.arguments);
    }
    
    public ExpressionType getExpressionType() {
        return ExpressionType.BOOL;
    }
    
    public String toString() {
        return this.type + "()";
    }
}

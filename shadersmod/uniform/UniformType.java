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

package shadersmod.uniform;

import net.optifine.entity.model.anim.ExpressionType;
import net.optifine.entity.model.anim.IExpression;
import net.optifine.entity.model.anim.IExpressionBool;
import net.optifine.entity.model.anim.IExpressionFloat;

public enum UniformType {
    BOOL, INT, FLOAT;
    
    public ShaderUniformBase makeShaderUniform(String name) {
        switch (this) {
            case null:
                return new ShaderUniformInt(name);
            case INT:
                return new ShaderUniformInt(name);
            case FLOAT:
                return new ShaderUniformFloat(name);
        } 
        throw new RuntimeException("Unknown uniform type: " + this);
    }
    
    public void updateUniform(IExpression expression, ShaderUniformBase uniform) {
        switch (this) {
            case null:
                updateUniformBool((IExpressionBool)expression, (ShaderUniformInt)uniform);
                return;
            case INT:
                updateUniformInt((IExpressionFloat)expression, (ShaderUniformInt)uniform);
                return;
            case FLOAT:
                updateUniformFloat((IExpressionFloat)expression, (ShaderUniformFloat)uniform);
                return;
        } 
        throw new RuntimeException("Unknown uniform type: " + this);
    }
    
    private void updateUniformBool(IExpressionBool expression, ShaderUniformInt uniform) {
        boolean flag = expression.eval();
        int i = flag ? 1 : 0;
        uniform.setValue(i);
    }
    
    private void updateUniformInt(IExpressionFloat expression, ShaderUniformInt uniform) {
        int i = (int)expression.eval();
        uniform.setValue(i);
    }
    
    private void updateUniformFloat(IExpressionFloat expression, ShaderUniformFloat uniform) {
        float f = expression.eval();
        uniform.setValue(f);
    }
    
    public boolean matchesExpressionType(ExpressionType expressionType) {
        switch (this) {
            case null:
                return (expressionType == ExpressionType.BOOL);
            case INT:
                return (expressionType == ExpressionType.FLOAT);
            case FLOAT:
                return (expressionType == ExpressionType.FLOAT);
        } 
        throw new RuntimeException("Unknown uniform type: " + this);
    }
    
    public static UniformType parse(String type) {
        UniformType[] auniformtype = values();
        for (int i = 0; i < auniformtype.length; i++) {
            UniformType uniformtype = auniformtype[i];
            if (uniformtype.name().toLowerCase().equals(type))
                return uniformtype; 
        } 
        return null;
    }
}

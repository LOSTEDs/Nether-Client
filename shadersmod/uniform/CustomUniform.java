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

import net.optifine.entity.model.anim.IExpression;

public class CustomUniform {
    private String name;
    
    private UniformType type;
    
    private IExpression expression;
    
    private ShaderUniformBase shaderUniform;
    
    public CustomUniform(String name, UniformType type, IExpression expression) {
        this.name = name;
        this.type = type;
        this.expression = expression;
        this.shaderUniform = type.makeShaderUniform(name);
    }
    
    public void setProgram(int program) {
        this.shaderUniform.setProgram(program);
    }
    
    public void update() {
        this.type.updateUniform(this.expression, this.shaderUniform);
    }
    
    public String getName() {
        return this.name;
    }
    
    public UniformType getType() {
        return this.type;
    }
    
    public IExpression getExpression() {
        return this.expression;
    }
    
    public ShaderUniformBase getShaderUniform() {
        return this.shaderUniform;
    }
}

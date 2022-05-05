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

import org.lwjgl.opengl.ARBShaderObjects;

public abstract class ShaderUniformBase {
    private String name;
    
    private int program = -1;
    
    private int location = -1;
    
    public ShaderUniformBase(String name) {
        this.name = name;
    }
    
    public void setProgram(int program) {
        if (this.program != program) {
            this.program = program;
            this.location = ARBShaderObjects.glGetUniformLocationARB(program, this.name);
            onProgramChanged();
        } 
    }
    
    protected abstract void onProgramChanged();
    
    public String getName() {
        return this.name;
    }
    
    public int getProgram() {
        return this.program;
    }
    
    public int getLocation() {
        return this.location;
    }
    
    public boolean isDefined() {
        return (this.location >= 0);
    }
}

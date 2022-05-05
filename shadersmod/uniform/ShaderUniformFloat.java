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
import shadersmod.client.Shaders;

public class ShaderUniformFloat extends ShaderUniformBase {
    private float value = -1.0F;
    
    public ShaderUniformFloat(String name) {
        super(name);
    }
    
    protected void onProgramChanged() {
        this.value = -1.0F;
    }
    
    public void setValue(float value) {
        if (getLocation() >= 0)
            if (this.value != value) {
                ARBShaderObjects.glUniform1fARB(getLocation(), value);
                Shaders.checkGLError(getName());
                this.value = value;
            }  
    }
    
    public float getValue() {
        return this.value;
    }
}

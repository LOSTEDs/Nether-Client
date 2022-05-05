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

public class CustomUniforms {
    private CustomUniform[] uniforms;
    
    public CustomUniforms(CustomUniform[] uniforms) {
        this.uniforms = uniforms;
    }
    
    public void setProgram(int program) {
        for (int i = 0; i < this.uniforms.length; i++) {
            CustomUniform customuniform = this.uniforms[i];
            customuniform.setProgram(program);
        } 
    }
    
    public void update() {
        for (int i = 0; i < this.uniforms.length; i++) {
            CustomUniform customuniform = this.uniforms[i];
            customuniform.update();
        } 
    }
}

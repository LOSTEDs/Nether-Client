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

public class ModelUpdater {
    private ModelVariableUpdater[] modelVariableUpdaters;
    
    public ModelUpdater(ModelVariableUpdater[] modelVariableUpdaters) {
        this.modelVariableUpdaters = modelVariableUpdaters;
    }
    
    public void update() {
        for (int i = 0; i < this.modelVariableUpdaters.length; i++) {
            ModelVariableUpdater modelvariableupdater = this.modelVariableUpdaters[i];
            modelvariableupdater.update();
        } 
    }
    
    public boolean initialize(IModelResolver mr) {
        for (int i = 0; i < this.modelVariableUpdaters.length; i++) {
            ModelVariableUpdater modelvariableupdater = this.modelVariableUpdaters[i];
            if (!modelvariableupdater.initialize(mr))
                return false; 
        } 
        return true;
    }
}

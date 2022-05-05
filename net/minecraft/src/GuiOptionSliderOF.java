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

package net.minecraft.src;

import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.settings.GameSettings;

public class GuiOptionSliderOF extends GuiOptionSlider implements IOptionControl {
    private GameSettings.Options option = null;
    
    public GuiOptionSliderOF(int p_i57_1_, int p_i57_2_, int p_i57_3_, GameSettings.Options p_i57_4_) {
        super(p_i57_1_, p_i57_2_, p_i57_3_, p_i57_4_);
        this.option = p_i57_4_;
    }
    
    public GameSettings.Options getOption() {
        return this.option;
    }
}

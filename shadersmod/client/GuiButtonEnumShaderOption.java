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

package shadersmod.client;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;

public class GuiButtonEnumShaderOption extends GuiButton {
    private EnumShaderOption enumShaderOption = null;
    
    public GuiButtonEnumShaderOption(EnumShaderOption enumShaderOption, int x, int y, int widthIn, int heightIn) {
        super(enumShaderOption.ordinal(), x, y, widthIn, heightIn, getButtonText(enumShaderOption));
        this.enumShaderOption = enumShaderOption;
    }
    
    public EnumShaderOption getEnumShaderOption() {
        return this.enumShaderOption;
    }
    
    private static String getButtonText(EnumShaderOption eso) {
        String s = String.valueOf(I18n.format(eso.getResourceKey(), new Object[0])) + ": ";
        switch (eso) {
            case null:
                return String.valueOf(s) + GuiShaders.toStringAa(Shaders.configAntialiasingLevel);
            case NORMAL_MAP:
                return String.valueOf(s) + GuiShaders.toStringOnOff(Shaders.configNormalMap);
            case SPECULAR_MAP:
                return String.valueOf(s) + GuiShaders.toStringOnOff(Shaders.configSpecularMap);
            case RENDER_RES_MUL:
                return String.valueOf(s) + GuiShaders.toStringQuality(Shaders.configRenderResMul);
            case SHADOW_RES_MUL:
                return String.valueOf(s) + GuiShaders.toStringQuality(Shaders.configShadowResMul);
            case HAND_DEPTH_MUL:
                return String.valueOf(s) + GuiShaders.toStringHandDepth(Shaders.configHandDepthMul);
            case CLOUD_SHADOW:
                return String.valueOf(s) + GuiShaders.toStringOnOff(Shaders.configCloudShadow);
            case OLD_HAND_LIGHT:
                return String.valueOf(s) + Shaders.configOldHandLight.getUserValue();
            case OLD_LIGHTING:
                return String.valueOf(s) + Shaders.configOldLighting.getUserValue();
            case SHADOW_CLIP_FRUSTRUM:
                return String.valueOf(s) + GuiShaders.toStringOnOff(Shaders.configShadowClipFrustrum);
            case TWEAK_BLOCK_DAMAGE:
                return String.valueOf(s) + GuiShaders.toStringOnOff(Shaders.configTweakBlockDamage);
        } 
        return String.valueOf(s) + Shaders.getEnumShaderOption(eso);
    }
    
    public void updateButtonText() {
        this.displayString = getButtonText(this.enumShaderOption);
    }
}

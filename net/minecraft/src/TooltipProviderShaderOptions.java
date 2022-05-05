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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.settings.GameSettings;
import shadersmod.client.GuiButtonShaderOption;
import shadersmod.client.ShaderOption;

public class TooltipProviderShaderOptions extends TooltipProviderOptions {
    public String[] getTooltipLines(GuiButton p_getTooltipLines_1_, int p_getTooltipLines_2_) {
        if (!(p_getTooltipLines_1_ instanceof GuiButtonShaderOption))
            return null; 
        GuiButtonShaderOption guibuttonshaderoption = (GuiButtonShaderOption)p_getTooltipLines_1_;
        ShaderOption shaderoption = guibuttonshaderoption.getShaderOption();
        String[] astring = makeTooltipLines(shaderoption, p_getTooltipLines_2_);
        return astring;
    }
    
    private String[] makeTooltipLines(ShaderOption p_makeTooltipLines_1_, int p_makeTooltipLines_2_) {
        if (p_makeTooltipLines_1_ instanceof shadersmod.client.ShaderOptionProfile)
            return null; 
        String s = p_makeTooltipLines_1_.getNameText();
        String s1 = Config.normalize(p_makeTooltipLines_1_.getDescriptionText()).trim();
        String[] astring = splitDescription(s1);
        GameSettings gamesettings = Config.getGameSettings();
        String s2 = null;
        if (!s.equals(p_makeTooltipLines_1_.getName()) && gamesettings.advancedItemTooltips)
            s2 = "§8" + Lang.get("of.general.id") + ": " + p_makeTooltipLines_1_.getName(); 
        String s3 = null;
        if (p_makeTooltipLines_1_.getPaths() != null && gamesettings.advancedItemTooltips)
            s3 = "§8" + Lang.get("of.general.from") + ": " + Config.arrayToString((Object[])p_makeTooltipLines_1_.getPaths()); 
        String s4 = null;
        if (p_makeTooltipLines_1_.getValueDefault() != null && gamesettings.advancedItemTooltips) {
            String s5 = p_makeTooltipLines_1_.isEnabled() ? p_makeTooltipLines_1_.getValueText(p_makeTooltipLines_1_.getValueDefault()) : Lang.get("of.general.ambiguous");
            s4 = "§8" + Lang.getDefault() + ": " + s5;
        } 
        List<String> list = new ArrayList<>();
        list.add(s);
        list.addAll(Arrays.asList(astring));
        if (s2 != null)
            list.add(s2); 
        if (s3 != null)
            list.add(s3); 
        if (s4 != null)
            list.add(s4); 
        String[] astring1 = makeTooltipLines(p_makeTooltipLines_2_, list);
        return astring1;
    }
    
    private String[] splitDescription(String p_splitDescription_1_) {
        if (p_splitDescription_1_.length() <= 0)
            return new String[0]; 
        p_splitDescription_1_ = StrUtils.removePrefix(p_splitDescription_1_, "//");
        String[] astring = p_splitDescription_1_.split("\\. ");
        for (int i = 0; i < astring.length; i++) {
            astring[i] = "- " + astring[i].trim();
            astring[i] = StrUtils.removeSuffix(astring[i], ".");
        } 
        return astring;
    }
    
    private String[] makeTooltipLines(int p_makeTooltipLines_1_, List<String> p_makeTooltipLines_2_) {
        FontRenderer fontrenderer = (Config.getMinecraft()).fontRendererObj;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < p_makeTooltipLines_2_.size(); i++) {
            String s = p_makeTooltipLines_2_.get(i);
            if (s != null && s.length() > 0)
                for (Object a : fontrenderer.listFormattedStringToWidth(s, p_makeTooltipLines_1_)) {
                    String s1 = (String)a;
                    list.add(s1);
                }  
        } 
        String[] astring = list.<String>toArray(new String[list.size()]);
        return astring;
    }
}

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

import java.util.ArrayList;
import java.util.List;
import net.minecraft.src.Lang;

public class ShaderOptionProfile extends ShaderOption {
    private ShaderProfile[] profiles = null;
    
    private ShaderOption[] options = null;
    
    private static final String NAME_PROFILE = "<profile>";
    
    private static final String VALUE_CUSTOM = "<custom>";
    
    public ShaderOptionProfile(ShaderProfile[] profiles, ShaderOption[] options) {
        super("<profile>", "", detectProfileName(profiles, options), getProfileNames(profiles), detectProfileName(profiles, options, true), null);
        this.profiles = profiles;
        this.options = options;
    }
    
    public void nextValue() {
        super.nextValue();
        if (getValue().equals("<custom>"))
            super.nextValue(); 
        applyProfileOptions();
    }
    
    public void updateProfile() {
        ShaderProfile shaderprofile = getProfile(getValue());
        if (shaderprofile == null || !ShaderUtils.matchProfile(shaderprofile, this.options, false)) {
            String s = detectProfileName(this.profiles, this.options);
            setValue(s);
        } 
    }
    
    private void applyProfileOptions() {
        ShaderProfile shaderprofile = getProfile(getValue());
        if (shaderprofile != null) {
            String[] astring = shaderprofile.getOptions();
            for (int i = 0; i < astring.length; i++) {
                String s = astring[i];
                ShaderOption shaderoption = getOption(s);
                if (shaderoption != null) {
                    String s1 = shaderprofile.getValue(s);
                    shaderoption.setValue(s1);
                } 
            } 
        } 
    }
    
    private ShaderOption getOption(String name) {
        for (int i = 0; i < this.options.length; i++) {
            ShaderOption shaderoption = this.options[i];
            if (shaderoption.getName().equals(name))
                return shaderoption; 
        } 
        return null;
    }
    
    private ShaderProfile getProfile(String name) {
        for (int i = 0; i < this.profiles.length; i++) {
            ShaderProfile shaderprofile = this.profiles[i];
            if (shaderprofile.getName().equals(name))
                return shaderprofile; 
        } 
        return null;
    }
    
    public String getNameText() {
        return Lang.get("of.shaders.profile");
    }
    
    public String getValueText(String val) {
        return val.equals("<custom>") ? Lang.get("of.general.custom", "<custom>") : Shaders.translate("profile." + val, val);
    }
    
    public String getValueColor(String val) {
        return val.equals("<custom>") ? "§c" : "§a";
    }
    
    private static String detectProfileName(ShaderProfile[] profs, ShaderOption[] opts) {
        return detectProfileName(profs, opts, false);
    }
    
    private static String detectProfileName(ShaderProfile[] profs, ShaderOption[] opts, boolean def) {
        ShaderProfile shaderprofile = ShaderUtils.detectProfile(profs, opts, def);
        return (shaderprofile == null) ? "<custom>" : shaderprofile.getName();
    }
    
    private static String[] getProfileNames(ShaderProfile[] profs) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < profs.length; i++) {
            ShaderProfile shaderprofile = profs[i];
            list.add(shaderprofile.getName());
        } 
        list.add("<custom>");
        String[] astring = list.<String>toArray(new String[list.size()]);
        return astring;
    }
}

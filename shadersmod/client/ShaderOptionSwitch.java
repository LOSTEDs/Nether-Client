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

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.src.Config;
import net.minecraft.src.Lang;
import net.minecraft.src.StrUtils;

public class ShaderOptionSwitch extends ShaderOption {
    private static final Pattern PATTERN_DEFINE = Pattern.compile("^\\s*(//)?\\s*#define\\s+([A-Za-z0-9_]+)\\s*(//.*)?$");
    
    private static final Pattern PATTERN_IFDEF = Pattern.compile("^\\s*#if(n)?def\\s+([A-Za-z0-9_]+)(\\s*)?$");
    
    public ShaderOptionSwitch(String name, String description, String value, String path) {
        super(name, description, value, new String[] { "false", "true" }, value, path);
    }
    
    public String getSourceLine() {
        return isTrue(getValue()) ? ("#define " + getName() + " // Shader option ON") : ("//#define " + getName() + " // Shader option OFF");
    }
    
    public String getValueText(String val) {
        String s = super.getValueText(val);
        return (s != val) ? s : (isTrue(val) ? Lang.getOn() : Lang.getOff());
    }
    
    public String getValueColor(String val) {
        return isTrue(val) ? "§a" : "§c";
    }
    
    public static ShaderOption parseOption(String line, String path) {
        Matcher matcher = PATTERN_DEFINE.matcher(line);
        if (!matcher.matches())
            return null; 
        String s = matcher.group(1);
        String s1 = matcher.group(2);
        String s2 = matcher.group(3);
        if (s1 != null && s1.length() > 0) {
            boolean flag = Config.equals(s, "//");
            boolean flag1 = !flag;
            path = StrUtils.removePrefix(path, "/shaders/");
            ShaderOption shaderoption = new ShaderOptionSwitch(s1, s2, String.valueOf(flag1), path);
            return shaderoption;
        } 
        return null;
    }
    
    public boolean matchesLine(String line) {
        Matcher matcher = PATTERN_DEFINE.matcher(line);
        if (!matcher.matches())
            return false; 
        String s = matcher.group(2);
        return s.matches(getName());
    }
    
    public boolean checkUsed() {
        return true;
    }
    
    public boolean isUsedInLine(String line) {
        Matcher matcher = PATTERN_IFDEF.matcher(line);
        if (matcher.matches()) {
            String s = matcher.group(2);
            if (s.equals(getName()))
                return true; 
        } 
        return false;
    }
    
    public static boolean isTrue(String val) {
        return Boolean.valueOf(val).booleanValue();
    }
}

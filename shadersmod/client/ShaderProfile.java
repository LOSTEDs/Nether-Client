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

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ShaderProfile {
    private String name = null;
    
    private Map<String, String> mapOptionValues = new LinkedHashMap<>();
    
    private Set<String> disabledPrograms = new LinkedHashSet<>();
    
    public ShaderProfile(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void addOptionValue(String option, String value) {
        this.mapOptionValues.put(option, value);
    }
    
    public void addOptionValues(ShaderProfile prof) {
        if (prof != null)
            this.mapOptionValues.putAll(prof.mapOptionValues); 
    }
    
    public void applyOptionValues(ShaderOption[] options) {
        for (int i = 0; i < options.length; i++) {
            ShaderOption shaderoption = options[i];
            String s = shaderoption.getName();
            String s1 = this.mapOptionValues.get(s);
            if (s1 != null)
                shaderoption.setValue(s1); 
        } 
    }
    
    public String[] getOptions() {
        Set<String> set = this.mapOptionValues.keySet();
        String[] astring = set.<String>toArray(new String[set.size()]);
        return astring;
    }
    
    public String getValue(String key) {
        return this.mapOptionValues.get(key);
    }
    
    public void addDisabledProgram(String program) {
        this.disabledPrograms.add(program);
    }
    
    public void removeDisabledProgram(String program) {
        this.disabledPrograms.remove(program);
    }
    
    public Collection<String> getDisabledPrograms() {
        return new LinkedHashSet<>(this.disabledPrograms);
    }
    
    public void addDisabledPrograms(Collection<String> programs) {
        this.disabledPrograms.addAll(programs);
    }
    
    public boolean isProgramDisabled(String program) {
        return this.disabledPrograms.contains(program);
    }
}

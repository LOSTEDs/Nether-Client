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

import java.util.HashMap;
import java.util.Map;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.entity.model.anim.ConstantFloat;
import net.optifine.entity.model.anim.IExpression;
import net.optifine.entity.model.anim.IExpressionResolver;
import shadersmod.common.SMCLog;

public class ShaderExpressionResolver implements IExpressionResolver {
    private Map<String, IExpression> mapExpressions = new HashMap<>();
    
    public ShaderExpressionResolver(Map<String, IExpression> map) {
        registerExpressions();
        for (String s : map.keySet()) {
            IExpression iexpression = map.get(s);
            registerExpression(s, iexpression);
        } 
    }
    
    private void registerExpressions() {
        ShaderParameterFloat[] ashaderparameterfloat = ShaderParameterFloat.values();
        for (int i = 0; i < ashaderparameterfloat.length; i++) {
            ShaderParameterFloat shaderparameterfloat = ashaderparameterfloat[i];
            this.mapExpressions.put(shaderparameterfloat.getName(), shaderparameterfloat);
        } 
        ShaderParameterBool[] ashaderparameterbool = ShaderParameterBool.values();
        for (int k = 0; k < ashaderparameterbool.length; k++) {
            ShaderParameterBool shaderparameterbool = ashaderparameterbool[k];
            this.mapExpressions.put(shaderparameterbool.getName(), shaderparameterbool);
        } 
        BiomeGenBase[] abiomegenbase = BiomeGenBase.getBiomeGenArray();
        for (int l = 0; l < abiomegenbase.length; l++) {
            BiomeGenBase biomegenbase = abiomegenbase[l];
            if (biomegenbase != null) {
                String s = biomegenbase.biomeName.trim();
                s = "BIOME_" + s.toUpperCase().replace(' ', '_');
                int j = biomegenbase.biomeID;
                ConstantFloat constantFloat = new ConstantFloat(j);
                registerExpression(s, (IExpression)constantFloat);
            } 
        } 
    }
    
    public boolean registerExpression(String name, IExpression expr) {
        if (this.mapExpressions.containsKey(name)) {
            SMCLog.warning("Expression already defined: " + name);
            return false;
        } 
        this.mapExpressions.put(name, expr);
        return true;
    }
    
    public IExpression getExpression(String name) {
        return this.mapExpressions.get(name);
    }
    
    public boolean hasExpression(String name) {
        return this.mapExpressions.containsKey(name);
    }
}

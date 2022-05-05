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
import net.minecraft.src.SmoothFloat;

public class Smoother {
    private static Map<Integer, SmoothFloat> mapSmoothValues = new HashMap<>();
    
    public static float getSmoothValue(int id, float value, float timeFadeUpSec, float timeFadeDownSec) {
        synchronized (mapSmoothValues) {
            Integer integer = Integer.valueOf(id);
            SmoothFloat smoothfloat = mapSmoothValues.get(integer);
            if (smoothfloat == null) {
                smoothfloat = new SmoothFloat(value, timeFadeUpSec, timeFadeDownSec);
                mapSmoothValues.put(integer, smoothfloat);
            } 
            float f = smoothfloat.getSmoothValue(value);
            return f;
        } 
    }
    
    public static void reset() {
        synchronized (mapSmoothValues) {
            mapSmoothValues.clear();
        } 
    }
}

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

import java.util.HashMap;
import java.util.Map;

public class TimedEvent {
    private static Map<String, Long> mapEventTimes = new HashMap<>();
    
    public static boolean isActive(String p_isActive_0_, long p_isActive_1_) {
        synchronized (mapEventTimes) {
            long i = System.currentTimeMillis();
            Long olong = mapEventTimes.get(p_isActive_0_);
            if (olong == null) {
                olong = new Long(i);
                mapEventTimes.put(p_isActive_0_, olong);
            } 
            long j = olong.longValue();
            if (i < j + p_isActive_1_)
                return false; 
            mapEventTimes.put(p_isActive_0_, new Long(i));
            return true;
        } 
    }
}

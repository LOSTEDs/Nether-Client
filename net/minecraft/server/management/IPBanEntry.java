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

package net.minecraft.server.management;

import com.google.gson.JsonObject;
import java.util.Date;

public class IPBanEntry extends BanEntry<String> {
    public IPBanEntry(String p_i46330_1_) {
        this(p_i46330_1_, null, null, null, null);
    }
    
    public IPBanEntry(String p_i1159_1_, Date startDate, String banner, Date endDate, String p_i1159_5_) {
        super(p_i1159_1_, startDate, banner, endDate, p_i1159_5_);
    }
    
    public IPBanEntry(JsonObject p_i46331_1_) {
        super(getIPFromJson(p_i46331_1_), p_i46331_1_);
    }
    
    private static String getIPFromJson(JsonObject json) {
        return json.has("ip") ? json.get("ip").getAsString() : null;
    }
    
    protected void onSerialization(JsonObject data) {
        if (getValue() != null) {
            data.addProperty("ip", getValue());
            super.onSerialization(data);
        } 
    }
}

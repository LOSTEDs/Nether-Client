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
import com.mojang.authlib.GameProfile;
import java.util.Date;
import java.util.UUID;

public class UserListBansEntry extends BanEntry<GameProfile> {
    public UserListBansEntry(GameProfile profile) {
        this(profile, null, null, null, null);
    }
    
    public UserListBansEntry(GameProfile profile, Date startDate, String banner, Date endDate, String banReason) {
        super(profile, endDate, banner, endDate, banReason);
    }
    
    public UserListBansEntry(JsonObject p_i1136_1_) {
        super(func_152648_b(p_i1136_1_), p_i1136_1_);
    }
    
    protected void onSerialization(JsonObject data) {
        if (getValue() != null) {
            data.addProperty("uuid", (getValue().getId() == null) ? "" : getValue().getId().toString());
            data.addProperty("name", getValue().getName());
            super.onSerialization(data);
        } 
    }
    
    private static GameProfile func_152648_b(JsonObject p_152648_0_) {
        if (p_152648_0_.has("uuid") && p_152648_0_.has("name")) {
            UUID uuid;
            String s = p_152648_0_.get("uuid").getAsString();
            try {
                uuid = UUID.fromString(s);
            } catch (Throwable var4) {
                return null;
            } 
            return new GameProfile(uuid, p_152648_0_.get("name").getAsString());
        } 
        return null;
    }
}

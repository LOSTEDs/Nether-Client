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
import java.util.UUID;

public class UserListWhitelistEntry extends UserListEntry<GameProfile> {
    public UserListWhitelistEntry(GameProfile profile) {
        super(profile);
    }
    
    public UserListWhitelistEntry(JsonObject p_i1130_1_) {
        super(gameProfileFromJsonObject(p_i1130_1_), p_i1130_1_);
    }
    
    protected void onSerialization(JsonObject data) {
        if (getValue() != null) {
            data.addProperty("uuid", (getValue().getId() == null) ? "" : getValue().getId().toString());
            data.addProperty("name", getValue().getName());
            super.onSerialization(data);
        } 
    }
    
    private static GameProfile gameProfileFromJsonObject(JsonObject p_152646_0_) {
        if (p_152646_0_.has("uuid") && p_152646_0_.has("name")) {
            UUID uuid;
            String s = p_152646_0_.get("uuid").getAsString();
            try {
                uuid = UUID.fromString(s);
            } catch (Throwable var4) {
                return null;
            } 
            return new GameProfile(uuid, p_152646_0_.get("name").getAsString());
        } 
        return null;
    }
}

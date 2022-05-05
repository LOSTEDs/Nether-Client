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
import java.io.File;

public class UserListWhitelist extends UserList<GameProfile, UserListWhitelistEntry> {
    public UserListWhitelist(File p_i1132_1_) {
        super(p_i1132_1_);
    }
    
    protected UserListEntry<GameProfile> createEntry(JsonObject entryData) {
        return new UserListWhitelistEntry(entryData);
    }
    
    public String[] getKeys() {
        String[] astring = new String[getValues().size()];
        int i = 0;
        for (UserListWhitelistEntry userlistwhitelistentry : getValues().values())
            astring[i++] = userlistwhitelistentry.getValue().getName(); 
        return astring;
    }
    
    protected String getObjectKey(GameProfile obj) {
        return obj.getId().toString();
    }
    
    public GameProfile func_152706_a(String p_152706_1_) {
        for (UserListWhitelistEntry userlistwhitelistentry : getValues().values()) {
            if (p_152706_1_.equalsIgnoreCase(userlistwhitelistentry.getValue().getName()))
                return userlistwhitelistentry.getValue(); 
        } 
        return null;
    }
}

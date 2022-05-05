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

public class UserListOps extends UserList<GameProfile, UserListOpsEntry> {
    public UserListOps(File saveFile) {
        super(saveFile);
    }
    
    protected UserListEntry<GameProfile> createEntry(JsonObject entryData) {
        return new UserListOpsEntry(entryData);
    }
    
    public String[] getKeys() {
        String[] astring = new String[getValues().size()];
        int i = 0;
        for (UserListOpsEntry userlistopsentry : getValues().values())
            astring[i++] = userlistopsentry.getValue().getName(); 
        return astring;
    }
    
    public boolean func_183026_b(GameProfile p_183026_1_) {
        UserListOpsEntry userlistopsentry = getEntry(p_183026_1_);
        return (userlistopsentry != null) ? userlistopsentry.func_183024_b() : false;
    }
    
    protected String getObjectKey(GameProfile obj) {
        return obj.getId().toString();
    }
    
    public GameProfile getGameProfileFromName(String username) {
        for (UserListOpsEntry userlistopsentry : getValues().values()) {
            if (username.equalsIgnoreCase(userlistopsentry.getValue().getName()))
                return userlistopsentry.getValue(); 
        } 
        return null;
    }
}

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

public class UserListBans extends UserList<GameProfile, UserListBansEntry> {
    public UserListBans(File bansFile) {
        super(bansFile);
    }
    
    protected UserListEntry<GameProfile> createEntry(JsonObject entryData) {
        return new UserListBansEntry(entryData);
    }
    
    public boolean isBanned(GameProfile profile) {
        return hasEntry(profile);
    }
    
    public String[] getKeys() {
        String[] astring = new String[getValues().size()];
        int i = 0;
        for (UserListBansEntry userlistbansentry : getValues().values())
            astring[i++] = userlistbansentry.getValue().getName(); 
        return astring;
    }
    
    protected String getObjectKey(GameProfile obj) {
        return obj.getId().toString();
    }
    
    public GameProfile isUsernameBanned(String username) {
        for (UserListBansEntry userlistbansentry : getValues().values()) {
            if (username.equalsIgnoreCase(userlistbansentry.getValue().getName()))
                return userlistbansentry.getValue(); 
        } 
        return null;
    }
}
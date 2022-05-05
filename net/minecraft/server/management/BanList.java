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
import java.io.File;
import java.net.SocketAddress;

public class BanList extends UserList<String, IPBanEntry> {
    public BanList(File bansFile) {
        super(bansFile);
    }
    
    protected UserListEntry<String> createEntry(JsonObject entryData) {
        return new IPBanEntry(entryData);
    }
    
    public boolean isBanned(SocketAddress address) {
        String s = addressToString(address);
        return hasEntry(s);
    }
    
    public IPBanEntry getBanEntry(SocketAddress address) {
        String s = addressToString(address);
        return getEntry(s);
    }
    
    private String addressToString(SocketAddress address) {
        String s = address.toString();
        if (s.contains("/"))
            s = s.substring(s.indexOf('/') + 1); 
        if (s.contains(":"))
            s = s.substring(0, s.indexOf(':')); 
        return s;
    }
}

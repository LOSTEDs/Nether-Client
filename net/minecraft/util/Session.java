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

package net.minecraft.util;

import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.util.UUIDTypeAdapter;
import java.util.Map;
import java.util.UUID;

public class Session {
    private final String username;
    
    private final String playerID;
    
    private final String token;
    
    private final Type sessionType;
    
    public Session(String usernameIn, String playerIDIn, String tokenIn, String sessionTypeIn) {
        this.username = usernameIn;
        this.playerID = playerIDIn;
        this.token = tokenIn;
        this.sessionType = Type.setSessionType(sessionTypeIn);
    }
    
    public String getSessionID() {
        return "token:" + this.token + ":" + this.playerID;
    }
    
    public String getPlayerID() {
        return this.playerID;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getToken() {
        return this.token;
    }
    
    public GameProfile getProfile() {
        try {
            UUID uuid = UUIDTypeAdapter.fromString(getPlayerID());
            return new GameProfile(uuid, getUsername());
        } catch (IllegalArgumentException var2) {
            return new GameProfile(null, getUsername());
        } 
    }
    
    public Type getSessionType() {
        return this.sessionType;
    }
    
    public enum Type {
        LEGACY("legacy"),
        MOJANG("mojang");
        
        private static final Map<String, Type> SESSION_TYPES = Maps.newHashMap();
        
        private final String sessionType;
        
        static {
            byte b;
            int i;
            Type[] arrayOfType;
            for (i = (arrayOfType = values()).length, b = 0; b < i; ) {
                Type session$type = arrayOfType[b];
                SESSION_TYPES.put(session$type.sessionType, session$type);
                b++;
            } 
        }
        
        Type(String sessionTypeIn) {
            this.sessionType = sessionTypeIn;
        }
        
        public static Type setSessionType(String sessionTypeIn) {
            return SESSION_TYPES.get(sessionTypeIn.toLowerCase());
        }
    }
}

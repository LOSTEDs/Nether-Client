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

package client.utils;

import com.mojang.authlib.Agent;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.util.UUIDTypeAdapter;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class SessionChanger {
    private static SessionChanger instance;
    
    private final UserAuthentication auth;
    
    public static SessionChanger getInstance() {
        if (instance == null)
            instance = new SessionChanger(); 
        return instance;
    }
    
    private SessionChanger() {
        UUID notSureWhyINeedThis = UUID.randomUUID();
        YggdrasilAuthenticationService yggdrasilAuthenticationService = new YggdrasilAuthenticationService(Minecraft.getMinecraft().getProxy(), notSureWhyINeedThis.toString());
        this.auth = yggdrasilAuthenticationService.createUserAuthentication(Agent.MINECRAFT);
        yggdrasilAuthenticationService.createMinecraftSessionService();
    }
    
    public void setUser(String email, String password) {
        if (!Minecraft.getMinecraft().getSession().getUsername().equals(email) || Minecraft.getMinecraft().getSession().getToken().equals("0")) {
            this.auth.logOut();
            this.auth.setUsername(email);
            this.auth.setPassword(password);
            try {
                this.auth.logIn();
                Session session = new Session(this.auth.getSelectedProfile().getName(), UUIDTypeAdapter.fromUUID(this.auth.getSelectedProfile().getId()), this.auth.getAuthenticatedToken(), this.auth.getUserType().getName());
                setSession(session);
            } catch (Exception e) {
                e.printStackTrace();
            } 
        } 
    }
    
    private void setSession(Session session) {
        (Minecraft.getMinecraft()).session = session;
    }
    
    public void setUserOffline(String username) {
        this.auth.logOut();
        Session session = new Session(username, username, "0", "legacy");
        setSession(session);
    }
}

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

package net.minecraft.realms;

import java.lang.reflect.Constructor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RealmsBridge extends RealmsScreen {
    private static final Logger LOGGER = LogManager.getLogger();
    
    private GuiScreen previousScreen;
    
    public void switchToRealms(GuiScreen p_switchToRealms_1_) {
        this.previousScreen = p_switchToRealms_1_;
        try {
            Class<?> oclass = Class.forName("com.mojang.realmsclient.RealmsMainScreen");
            Constructor<?> constructor = oclass.getDeclaredConstructor(new Class[] { RealmsScreen.class });
            constructor.setAccessible(true);
            Object object = constructor.newInstance(new Object[] { this });
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)((RealmsScreen)object).getProxy());
        } catch (Exception exception) {
            LOGGER.error("Realms module missing", exception);
        } 
    }
    
    public void init() {
        Minecraft.getMinecraft().displayGuiScreen(this.previousScreen);
    }
}

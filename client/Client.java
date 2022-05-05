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

package client;

import client.config.Config;
import client.event.EventManager;
import client.event.EventTarget;
import client.event.impl.ClientTick;
import client.event.impl.KeyEvent;
import client.gui.clickgui.ClickGUI;
import client.hud.HUDConfigScreen;
import client.hud.impl.HudManager;
import client.mods.PerspectiveMod;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

public class Client {
    public static String ClientName = "Nether Client";
    
    public static String SplashScreen = "client/splash.jpg";
    
    public static String Background = "client/main_menu.jpg";
    
    public static Color ButtonOutline = new Color(170, 0, 0);
    
    public static int ButtonString = (new Color(170, 0, 0)).getRGB();
    
    public static int MainColor = (new Color(170, 0, 0)).getRGB();
    
    public static int TargetHUD = (new Color(170, 0, 0)).getRGB();
    
    public static int BlockOutline = (new Color(170, 0, 0)).getRGB();
    
    public static String Color0 = "§8";
    
    public static String ColorM = "§4";
    
    public static String ColorW = "§f";
    
    public static Client INSTANCE = new Client();
    
    public Minecraft mc = Minecraft.getMinecraft();
    
    public EventManager eventManager;
    
    public Config config;
    
    public HudManager hudManager;
    
    public void init() {
        System.out.println("Starting " + ClientName + " by NexoLegend");
    }
    
    public void start() {
        this.eventManager = new EventManager();
        this.config = new Config();
        this.config.loadModConfig();
        this.hudManager = new HudManager();
        EventManager.register(this);
    }
    
    public void shutdown() {
        System.out.println("Shutting Down " + ClientName);
        this.config.saveModConfig();
        EventManager.unregister(this);
    }
    
    @EventTarget
    public void onTick(ClientTick event) {
        if (this.mc.gameSettings.Click_GUI.isPressed())
            this.mc.displayGuiScreen((GuiScreen)new ClickGUI()); 
        if (this.mc.gameSettings.HUD_Config.isPressed())
            this.mc.displayGuiScreen((GuiScreen)new HUDConfigScreen()); 
    }
    
    @EventTarget
    public void keyboardEvent(KeyEvent e) {
        if (HudManager.perspectiveMod.isEnabled()) {
            Minecraft mc = Minecraft.getMinecraft();
            if (e.getKey() == mc.gameSettings.Perspective.getKeyCode())
                if (Keyboard.getEventKeyState()) {
                    PerspectiveMod.perspectiveToggled = !PerspectiveMod.perspectiveToggled;
                    PerspectiveMod.cameraYaw = Minecraft.thePlayer.rotationYaw;
                    PerspectiveMod.cameraPitch = Minecraft.thePlayer.rotationPitch;
                    if (PerspectiveMod.perspectiveToggled) {
                        PerspectiveMod.previousePrespective = mc.gameSettings.thirdPersonView;
                        mc.gameSettings.thirdPersonView = 1;
                    } else {
                        mc.gameSettings.thirdPersonView = PerspectiveMod.previousePrespective;
                    } 
                } else if (PerspectiveMod.returnOnRelease) {
                    PerspectiveMod.perspectiveToggled = false;
                    mc.gameSettings.thirdPersonView = PerspectiveMod.previousePrespective;
                }  
            if (Keyboard.getEventKey() == mc.gameSettings.keyBindTogglePerspective.getKeyCode())
                PerspectiveMod.perspectiveToggled = false; 
        } 
    }
}

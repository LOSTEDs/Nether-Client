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

package client.mods;

import client.Client;
import client.hud.impl.HudMod;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.opengl.GL11;

public class KeystrokesMod extends HudMod {
    private KeystrokesMode mode;
    
    public KeystrokesMod() {
        super("Keystrokes", 622, 0);
        this.mode = KeystrokesMode.WASD_JUMP_MOUSE;
    }
    
    public enum KeystrokesMode {
        WASD((String)new KeystrokesMod.Key[] { KeystrokesMod.Key.access$0(), KeystrokesMod.Key.access$1(), KeystrokesMod.Key.access$2(), KeystrokesMod.Key.access$3() }),
        WASD_MOUSE((String)new KeystrokesMod.Key[] { KeystrokesMod.Key.access$0(), KeystrokesMod.Key.access$1(), KeystrokesMod.Key.access$2(), KeystrokesMod.Key.access$3(), KeystrokesMod.Key.access$4(), KeystrokesMod.Key.access$5() }),
        WASD_JUMP((String)new KeystrokesMod.Key[] { KeystrokesMod.Key.access$0(), KeystrokesMod.Key.access$1(), KeystrokesMod.Key.access$2(), KeystrokesMod.Key.access$3(), KeystrokesMod.Key.access$6() }),
        WASD_JUMP_MOUSE((String)new KeystrokesMod.Key[] { KeystrokesMod.Key.access$0(), KeystrokesMod.Key.access$1(), KeystrokesMod.Key.access$2(), KeystrokesMod.Key.access$3(), KeystrokesMod.Key.access$4(), KeystrokesMod.Key.access$5(), KeystrokesMod.Key.access$7() });
        
        private int width = 0;
        
        private int height = 0;
        
        private final KeystrokesMod.Key[] keys;
        
        KeystrokesMode(KeystrokesMod.Key... keysIn) {
            this.keys = keysIn;
            byte b;
            int i;
            KeystrokesMod.Key[] arrayOfKey;
            for (i = (arrayOfKey = this.keys).length, b = 0; b < i; ) {
                KeystrokesMod.Key key = arrayOfKey[b];
                this.width = Math.max(this.width, key.getX() + key.getWidth());
                this.height = Math.max(this.height, key.getY() + key.getHeight());
                b++;
            } 
        }
        
        public int getHeight() {
            return this.height;
        }
        
        public int getWidth() {
            return this.width;
        }
        
        public KeystrokesMod.Key[] getKey() {
            return this.keys;
        }
    }
    
    public static class Key {
        public static Minecraft mc = Minecraft.getMinecraft();
        
        private static final Key W = new Key("W", (Minecraft.getMinecraft()).gameSettings.keyBindForward, 21, 1, 18, 18);
        
        private static final Key A = new Key("A", (Minecraft.getMinecraft()).gameSettings.keyBindLeft, 1, 21, 18, 18);
        
        private static final Key S = new Key("S", (Minecraft.getMinecraft()).gameSettings.keyBindBack, 21, 21, 18, 18);
        
        private static final Key D = new Key("D", (Minecraft.getMinecraft()).gameSettings.keyBindRight, 41, 21, 18, 18);
        
        private static final Key LMB = new Key("LMB", (Minecraft.getMinecraft()).gameSettings.keyBindAttack, 1, 41, 28, 18);
        
        private static final Key RMB = new Key("RMB", (Minecraft.getMinecraft()).gameSettings.keyBindUseItem, 31, 41, 28, 18);
        
        private static final Key JUMP1 = new Key("----", (Minecraft.getMinecraft()).gameSettings.keyBindJump, 1, 41, 58, 18);
        
        private static final Key JUMP2 = new Key("----", (Minecraft.getMinecraft()).gameSettings.keyBindJump, 1, 61, 58, 18);
        
        private final String name;
        
        private final KeyBinding keyBind;
        
        private final int x;
        
        private final int y;
        
        private final int w;
        
        private final int h;
        
        public Key(String name, KeyBinding keyBind, int x, int y, int w, int h) {
            this.name = name;
            this.keyBind = keyBind;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
        
        public boolean isDown() {
            return this.keyBind.isKeyDown();
        }
        
        public int getHeight() {
            return this.h;
        }
        
        public int getWidth() {
            return this.w;
        }
        
        public String getName() {
            return this.name;
        }
        
        public int getX() {
            return this.x;
        }
        
        public int getY() {
            return this.y;
        }
    }
    
    public int getWidth() {
        return 60;
    }
    
    public int getHeight() {
        return 80;
    }
    
    public void draw() {
        GL11.glPushMatrix();
        byte b;
        int i;
        Key[] arrayOfKey;
        for (i = (arrayOfKey = this.mode.getKey()).length, b = 0; b < i; ) {
            Key key = arrayOfKey[b];
            int textWidth = this.fr.getStringWidth(key.getName());
            Gui.drawRect(
                    getX() + key.getX(), 
                    getY() + key.getY(), 
                    getX() + key.getX() + key.getWidth(), 
                    getY() + key.getY() + key.getHeight(), 
                    key.isDown() ? (new Color(255, 255, 255, 102)).getRGB() : (new Color(0, 0, 0, 120)).getRGB());
            this.fr.drawStringWithShadow(
                    key.getName(), (
                    getX() + key.getX() + key.getWidth() / 2 - textWidth / 2), (
                    getY() + key.getY() + key.getHeight() / 2 - 4), 
                    key.isDown() ? Client.MainColor : Client.MainColor);
            b++;
        } 
        GL11.glPopMatrix();
        super.draw();
    }
    
    public void renderDummy(int mouseX, int mouseY) {
        GL11.glPushMatrix();
        byte b;
        int i;
        Key[] arrayOfKey;
        for (i = (arrayOfKey = this.mode.getKey()).length, b = 0; b < i; ) {
            Key key = arrayOfKey[b];
            int textWidth = this.fr.getStringWidth(key.getName());
            Gui.drawRect(
                    getX() + key.getX(), 
                    getY() + key.getY(), 
                    getX() + key.getX() + key.getWidth(), 
                    getY() + key.getY() + key.getHeight(), 
                    key.isDown() ? (new Color(255, 255, 255, 102)).getRGB() : (new Color(0, 0, 0, 120)).getRGB());
            this.fr.drawStringWithShadow(
                    key.getName(), (
                    getX() + key.getX() + key.getWidth() / 2 - textWidth / 2), (
                    getY() + key.getY() + key.getHeight() / 2 - 4), 
                    key.isDown() ? Client.MainColor : Client.MainColor);
            b++;
        } 
        GL11.glPopMatrix();
        super.renderDummy(mouseX, mouseY);
    }
}

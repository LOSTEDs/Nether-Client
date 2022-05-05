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

package client.hud;

import client.Client;
import client.gui.clickgui.ClickGUI;
import client.hud.impl.HudMod;
import java.awt.Color;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class HUDConfigScreen extends GuiScreen {
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(1, this.width / 2 - 50, this.height / 2 - 10, 100, 20, "ClickGUI"));
    }
    
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawHollowRect(0, 0, this.width - 1, this.height - 1, (new Color(0, 180, 235)).getRGB());
        for (HudMod m : Client.INSTANCE.hudManager.hudMods) {
            if (m.isEnabled())
                m.renderDummy(mouseX, mouseY); 
        } 
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    public void drawHollowRect(int x, int y, int w, int h, int color) {
        drawHorizontalLine(x, x + w, y, color);
        drawHorizontalLine(x, x + w, y + h, color);
        drawVerticalLine(x, y + h, y, color);
        drawVerticalLine(x + w, y + h, y, color);
    }
    
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        switch (button.id) {
            case 1:
                this.mc.displayGuiScreen((GuiScreen)new ClickGUI());
                break;
        } 
    }
}

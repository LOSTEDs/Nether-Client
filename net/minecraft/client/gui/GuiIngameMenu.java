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

package net.minecraft.client.gui;

import client.Client;
import client.gui.GuiServerSwitch;
import client.gui.clickgui.ClickGUI;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.resources.I18n;
import net.minecraft.realms.RealmsBridge;

public class GuiIngameMenu extends GuiScreen {
    private int field_146445_a;
    
    private int field_146444_f;
    
    public void initGui() {
        this.field_146445_a = 0;
        this.buttonList.clear();
        int i = -16;
        int j = 98;
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 144 + -16, I18n.format("menu.returnToMenu", new Object[0])));
        if (!this.mc.isIntegratedServerRunning())
            ((GuiButton)this.buttonList.get(0)).displayString = I18n.format("menu.disconnect", new Object[0]); 
        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 24 + -16, I18n.format("menu.returnToGame", new Object[0])));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + -16, 98, 20, I18n.format("menu.options", new Object[0])));
        if (this.mc.isSingleplayer()) {
            GuiButton guibutton;
            this.buttonList.add(guibutton = new GuiButton(7, this.width / 2 + 2, this.height / 4 + 96 + -16, 98, 20, I18n.format("menu.shareToLan", new Object[0])));
            guibutton.enabled = (this.mc.isSingleplayer() && !this.mc.getIntegratedServer().getPublic());
        } else {
            this.buttonList.add(new GuiButton(77, this.width / 2 + 2, this.height / 4 + 96 + -16, 98, 20, I18n.format("Server List", new Object[0])));
        } 
        this.buttonList.add(new GuiButton(187, this.width / 2 - 100, this.height / 4 + 120 + -16, I18n.format(String.valueOf(String.valueOf(Client.ClientName)) + " Settings", new Object[0])));
        this.buttonList.add(new GuiButton(5, this.width / 2 - 100, this.height / 4 + 48 + -16, 98, 20, I18n.format("gui.achievements", new Object[0])));
        this.buttonList.add(new GuiButton(6, this.width / 2 + 2, this.height / 4 + 48 + -16, 98, 20, I18n.format("gui.stats", new Object[0])));
    }
    
    protected void actionPerformed(GuiButton button) throws IOException {
        boolean flag, flag1;
        switch (button.id) {
            case 0:
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
            case 1:
                flag = this.mc.isIntegratedServerRunning();
                flag1 = this.mc.func_181540_al();
                button.enabled = false;
                this.mc.theWorld.sendQuittingDisconnectingPacket();
                this.mc.loadWorld(null);
                if (flag) {
                    this.mc.displayGuiScreen(new GuiMainMenu());
                } else if (flag1) {
                    RealmsBridge realmsbridge = new RealmsBridge();
                    realmsbridge.switchToRealms(new GuiMainMenu());
                } else {
                    this.mc.displayGuiScreen(new GuiMultiplayer(new GuiMainMenu()));
                } 
            default:
                return;
            case 4:
                this.mc.displayGuiScreen(null);
                this.mc.setIngameFocus();
            case 5:
                this.mc.displayGuiScreen((GuiScreen)new GuiAchievements(this, Minecraft.thePlayer.getStatFileWriter()));
            case 6:
                this.mc.displayGuiScreen((GuiScreen)new GuiStats(this, Minecraft.thePlayer.getStatFileWriter()));
            case 7:
                this.mc.displayGuiScreen(new GuiShareToLan(this));
            case 187:
                this.mc.displayGuiScreen((GuiScreen)new ClickGUI());
            case 77:
                break;
        } 
        this.mc.displayGuiScreen((GuiScreen)new GuiServerSwitch(this));
    }
    
    public void updateScreen() {
        super.updateScreen();
        this.field_146444_f++;
    }
    
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(this.fontRendererObj, I18n.format("menu.game", new Object[0]), this.width / 2, 40, 16777215);
        String s = Client.ClientName;
        drawString(this.fontRendererObj, s, 2, this.height - 10, 8421504);
        String s2 = "Developed By NexoLegend";
        int i = this.fontRendererObj.getStringWidth(s2);
        drawString(this.fontRendererObj, s2, this.width - i - 2, this.height - 10, 8421504);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}

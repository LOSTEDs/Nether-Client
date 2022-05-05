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

import java.io.IOException;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C00PacketKeepAlive;

public class GuiDownloadTerrain extends GuiScreen {
    private NetHandlerPlayClient netHandlerPlayClient;
    
    private int progress;
    
    public GuiDownloadTerrain(NetHandlerPlayClient netHandler) {
        this.netHandlerPlayClient = netHandler;
    }
    
    protected void keyTyped(char typedChar, int keyCode) throws IOException {}
    
    public void initGui() {
        this.buttonList.clear();
    }
    
    public void updateScreen() {
        this.progress++;
        if (this.progress % 20 == 0)
            this.netHandlerPlayClient.addToSendQueue((Packet)new C00PacketKeepAlive()); 
    }
    
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawBackground(0);
        drawCenteredString(this.fontRendererObj, I18n.format("multiplayer.downloadingTerrain", new Object[0]), this.width / 2, this.height / 2 - 50, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
}

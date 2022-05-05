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

package client.gui;

import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;

public class GuiServerSwitch extends GuiMultiplayer {
    public GuiServerSwitch(GuiScreen parentScreen) {
        super(null);
    }
    
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1 || button.id == 4)
            disconnect(); 
        super.actionPerformed(button);
    }
    
    public void connectToSelected() {
        disconnect();
        super.connectToSelected();
    }
    
    private void disconnect() {
        if (this.mc.theWorld != null) {
            this.mc.theWorld.sendQuittingDisconnectingPacket();
            this.mc.loadWorld(null);
            this.mc.displayGuiScreen(null);
            this.parentScreen = null;
        } 
    }
}

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
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class GuiSleepMP extends GuiChat {
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height - 40, I18n.format("multiplayer.stopSleeping", new Object[0])));
    }
    
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1) {
            wakeFromSleep();
        } else if (keyCode != 28 && keyCode != 156) {
            super.keyTyped(typedChar, keyCode);
        } else {
            String s = this.inputField.getText().trim();
            if (!s.isEmpty())
                Minecraft.thePlayer.sendChatMessage(s); 
            this.inputField.setText("");
            this.mc.ingameGUI.getChatGUI().resetScroll();
        } 
    }
    
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1) {
            wakeFromSleep();
        } else {
            super.actionPerformed(button);
        } 
    }
    
    private void wakeFromSleep() {
        NetHandlerPlayClient nethandlerplayclient = Minecraft.thePlayer.sendQueue;
        nethandlerplayclient.addToSendQueue((Packet)new C0BPacketEntityAction((Entity)Minecraft.thePlayer, C0BPacketEntityAction.Action.STOP_SLEEPING));
    }
}

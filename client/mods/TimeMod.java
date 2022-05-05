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
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeMod extends HudMod {
    public TimeMod() {
        super("Time Display", 0, 10);
    }
    
    public void draw() {
        String pattern = "hh : mm a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String time = simpleDateFormat.format(new Date());
        this.fr.drawStringWithShadow(String.valueOf(Client.Color0) + "[ " + Client.ColorM + "Time" + Client.Color0 + " ] " + Client.ColorW + time, (getX() + 1), (getY() + 1), -1);
        super.draw();
    }
    
    public void renderDummy(int mouseX, int mouseY) {
        String pattern = "hh : mm a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String time = simpleDateFormat.format(new Date());
        this.fr.drawStringWithShadow(String.valueOf(Client.Color0) + "[ " + Client.ColorM + "Time" + Client.Color0 + " ] " + Client.ColorW + time, (getX() + 1), (getY() + 1), -1);
        super.renderDummy(mouseX, mouseY);
    }
    
    public int getWidth() {
        String pattern = "hh : mm a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String time = simpleDateFormat.format(new Date());
        return this.fr.getStringWidth(String.valueOf(Client.Color0) + "[ " + Client.ColorM + "Time" + Client.Color0 + " ] " + Client.ColorW + time);
    }
    
    public int getHeight() {
        return this.fr.FONT_HEIGHT;
    }
}

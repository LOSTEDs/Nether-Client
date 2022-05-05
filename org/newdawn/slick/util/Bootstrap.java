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

package org.newdawn.slick.util;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;

public class Bootstrap {
    public static void runAsApplication(Game game, int width, int height, boolean fullscreen) {
        try {
            AppGameContainer container = new AppGameContainer(game, width, height, fullscreen);
            container.start();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}

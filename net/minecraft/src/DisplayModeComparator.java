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

package net.minecraft.src;

import java.util.Comparator;
import org.lwjgl.opengl.DisplayMode;

public class DisplayModeComparator implements Comparator {
    public int compare(Object p_compare_1_, Object p_compare_2_) {
        DisplayMode displaymode = (DisplayMode)p_compare_1_;
        DisplayMode displaymode1 = (DisplayMode)p_compare_2_;
        return (displaymode.getWidth() != displaymode1.getWidth()) ? (displaymode.getWidth() - displaymode1.getWidth()) : ((displaymode.getHeight() != displaymode1.getHeight()) ? (displaymode.getHeight() - displaymode1.getHeight()) : ((displaymode.getBitsPerPixel() != displaymode1.getBitsPerPixel()) ? (displaymode.getBitsPerPixel() - displaymode1.getBitsPerPixel()) : ((displaymode.getFrequency() != displaymode1.getFrequency()) ? (displaymode.getFrequency() - displaymode1.getFrequency()) : 0)));
    }
}

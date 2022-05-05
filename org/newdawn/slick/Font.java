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

package org.newdawn.slick;

public interface Font {
    int getWidth(String paramString);
    
    int getHeight(String paramString);
    
    int getLineHeight();
    
    void drawString(float paramFloat1, float paramFloat2, String paramString);
    
    void drawString(float paramFloat1, float paramFloat2, String paramString, Color paramColor);
    
    void drawString(float paramFloat1, float paramFloat2, String paramString, Color paramColor, int paramInt1, int paramInt2);
}

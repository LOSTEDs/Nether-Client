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

package org.newdawn.slick.gui;

import org.lwjgl.input.Cursor;
import org.newdawn.slick.Font;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.ImageData;

public interface GUIContext {
    Input getInput();
    
    long getTime();
    
    int getScreenWidth();
    
    int getScreenHeight();
    
    int getWidth();
    
    int getHeight();
    
    Font getDefaultFont();
    
    void setMouseCursor(String paramString, int paramInt1, int paramInt2) throws SlickException;
    
    void setMouseCursor(ImageData paramImageData, int paramInt1, int paramInt2) throws SlickException;
    
    void setMouseCursor(Cursor paramCursor, int paramInt1, int paramInt2) throws SlickException;
    
    void setDefaultMouseCursor();
}

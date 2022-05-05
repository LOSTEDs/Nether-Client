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

public interface MouseListener extends ControlledInputReciever {
    void mouseWheelMoved(int paramInt);
    
    void mouseClicked(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
    
    void mousePressed(int paramInt1, int paramInt2, int paramInt3);
    
    void mouseReleased(int paramInt1, int paramInt2, int paramInt3);
    
    void mouseMoved(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
    
    void mouseDragged(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
}

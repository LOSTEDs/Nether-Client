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

public interface ControllerListener extends ControlledInputReciever {
    void controllerLeftPressed(int paramInt);
    
    void controllerLeftReleased(int paramInt);
    
    void controllerRightPressed(int paramInt);
    
    void controllerRightReleased(int paramInt);
    
    void controllerUpPressed(int paramInt);
    
    void controllerUpReleased(int paramInt);
    
    void controllerDownPressed(int paramInt);
    
    void controllerDownReleased(int paramInt);
    
    void controllerButtonPressed(int paramInt1, int paramInt2);
    
    void controllerButtonReleased(int paramInt1, int paramInt2);
}

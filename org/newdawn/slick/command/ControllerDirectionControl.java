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

package org.newdawn.slick.command;

public class ControllerDirectionControl extends ControllerControl {
    public static final Direction LEFT = new Direction(1);
    
    public static final Direction UP = new Direction(3);
    
    public static final Direction DOWN = new Direction(4);
    
    public static final Direction RIGHT = new Direction(2);
    
    public ControllerDirectionControl(int controllerIndex, Direction dir) {
        super(controllerIndex, dir.event, 0);
    }
    
    private static class Direction {
        private int event;
        
        public Direction(int event) {
            this.event = event;
        }
    }
}

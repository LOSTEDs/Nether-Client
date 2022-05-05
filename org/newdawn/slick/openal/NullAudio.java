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

package org.newdawn.slick.openal;

public class NullAudio implements Audio {
    public int getBufferID() {
        return 0;
    }
    
    public float getPosition() {
        return 0.0F;
    }
    
    public boolean isPlaying() {
        return false;
    }
    
    public int playAsMusic(float pitch, float gain, boolean loop) {
        return 0;
    }
    
    public int playAsSoundEffect(float pitch, float gain, boolean loop) {
        return 0;
    }
    
    public int playAsSoundEffect(float pitch, float gain, boolean loop, float x, float y, float z) {
        return 0;
    }
    
    public boolean setPosition(float position) {
        return false;
    }
    
    public void stop() {}
}

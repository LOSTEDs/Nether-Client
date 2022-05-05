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

import java.io.IOException;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;
import org.newdawn.slick.util.Log;

public class StreamSound extends AudioImpl {
    private OpenALStreamPlayer player;
    
    public StreamSound(OpenALStreamPlayer player) {
        this.player = player;
    }
    
    public boolean isPlaying() {
        return SoundStore.get().isPlaying(this.player);
    }
    
    public int playAsMusic(float pitch, float gain, boolean loop) {
        try {
            cleanUpSource();
            this.player.setup(pitch);
            this.player.play(loop);
            SoundStore.get().setStream(this.player);
        } catch (IOException e) {
            Log.error("Failed to read OGG source: " + this.player.getSource());
        } 
        return SoundStore.get().getSource(0);
    }
    
    private void cleanUpSource() {
        SoundStore store = SoundStore.get();
        AL10.alSourceStop(store.getSource(0));
        IntBuffer buffer = BufferUtils.createIntBuffer(1);
        int queued = AL10.alGetSourcei(store.getSource(0), 4117);
        while (queued > 0) {
            AL10.alSourceUnqueueBuffers(store.getSource(0), buffer);
            queued--;
        } 
        AL10.alSourcei(store.getSource(0), 4105, 0);
    }
    
    public int playAsSoundEffect(float pitch, float gain, boolean loop, float x, float y, float z) {
        return playAsMusic(pitch, gain, loop);
    }
    
    public int playAsSoundEffect(float pitch, float gain, boolean loop) {
        return playAsMusic(pitch, gain, loop);
    }
    
    public void stop() {
        SoundStore.get().setStream(null);
    }
    
    public boolean setPosition(float position) {
        return this.player.setPosition(position);
    }
    
    public float getPosition() {
        return this.player.getPosition();
    }
}

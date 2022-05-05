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

import java.io.InputStream;
import java.net.URL;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.Log;

public class Sound {
    private Audio sound;
    
    public Sound(InputStream in, String ref) throws SlickException {
        SoundStore.get().init();
        try {
            if (ref.toLowerCase().endsWith(".ogg")) {
                this.sound = SoundStore.get().getOgg(in);
            } else if (ref.toLowerCase().endsWith(".wav")) {
                this.sound = SoundStore.get().getWAV(in);
            } else if (ref.toLowerCase().endsWith(".aif")) {
                this.sound = SoundStore.get().getAIF(in);
            } else if (ref.toLowerCase().endsWith(".xm") || ref.toLowerCase().endsWith(".mod")) {
                this.sound = SoundStore.get().getMOD(in);
            } else {
                throw new SlickException("Only .xm, .mod, .aif, .wav and .ogg are currently supported.");
            } 
        } catch (Exception e) {
            Log.error(e);
            throw new SlickException("Failed to load sound: " + ref);
        } 
    }
    
    public Sound(URL url) throws SlickException {
        SoundStore.get().init();
        String ref = url.getFile();
        try {
            if (ref.toLowerCase().endsWith(".ogg")) {
                this.sound = SoundStore.get().getOgg(url.openStream());
            } else if (ref.toLowerCase().endsWith(".wav")) {
                this.sound = SoundStore.get().getWAV(url.openStream());
            } else if (ref.toLowerCase().endsWith(".aif")) {
                this.sound = SoundStore.get().getAIF(url.openStream());
            } else if (ref.toLowerCase().endsWith(".xm") || ref.toLowerCase().endsWith(".mod")) {
                this.sound = SoundStore.get().getMOD(url.openStream());
            } else {
                throw new SlickException("Only .xm, .mod, .aif, .wav and .ogg are currently supported.");
            } 
        } catch (Exception e) {
            Log.error(e);
            throw new SlickException("Failed to load sound: " + ref);
        } 
    }
    
    public Sound(String ref) throws SlickException {
        SoundStore.get().init();
        try {
            if (ref.toLowerCase().endsWith(".ogg")) {
                this.sound = SoundStore.get().getOgg(ref);
            } else if (ref.toLowerCase().endsWith(".wav")) {
                this.sound = SoundStore.get().getWAV(ref);
            } else if (ref.toLowerCase().endsWith(".aif")) {
                this.sound = SoundStore.get().getAIF(ref);
            } else if (ref.toLowerCase().endsWith(".xm") || ref.toLowerCase().endsWith(".mod")) {
                this.sound = SoundStore.get().getMOD(ref);
            } else {
                throw new SlickException("Only .xm, .mod, .aif, .wav and .ogg are currently supported.");
            } 
        } catch (Exception e) {
            Log.error(e);
            throw new SlickException("Failed to load sound: " + ref);
        } 
    }
    
    public void play() {
        play(1.0F, 1.0F);
    }
    
    public void play(float pitch, float volume) {
        this.sound.playAsSoundEffect(pitch, volume * SoundStore.get().getSoundVolume(), false);
    }
    
    public void playAt(float x, float y, float z) {
        playAt(1.0F, 1.0F, x, y, z);
    }
    
    public void playAt(float pitch, float volume, float x, float y, float z) {
        this.sound.playAsSoundEffect(pitch, volume * SoundStore.get().getSoundVolume(), false, x, y, z);
    }
    
    public void loop() {
        loop(1.0F, 1.0F);
    }
    
    public void loop(float pitch, float volume) {
        this.sound.playAsSoundEffect(pitch, volume * SoundStore.get().getSoundVolume(), true);
    }
    
    public boolean playing() {
        return this.sound.isPlaying();
    }
    
    public void stop() {
        this.sound.stop();
    }
}

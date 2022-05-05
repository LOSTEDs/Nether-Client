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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import org.lwjgl.BufferUtils;
import org.lwjgl.Sys;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.OpenALException;
import org.newdawn.slick.util.Log;
import org.newdawn.slick.util.ResourceLoader;

public class SoundStore {
    private static SoundStore store = new SoundStore();
    
    private boolean sounds;
    
    private boolean music;
    
    private boolean soundWorks;
    
    private int sourceCount;
    
    private HashMap loaded = new HashMap<Object, Object>();
    
    private int currentMusic = -1;
    
    private IntBuffer sources;
    
    private int nextSource;
    
    private boolean inited = false;
    
    private MODSound mod;
    
    private OpenALStreamPlayer stream;
    
    private float musicVolume = 1.0F;
    
    private float soundVolume = 1.0F;
    
    private float lastCurrentMusicVolume = 1.0F;
    
    private boolean paused;
    
    private boolean deferred;
    
    private FloatBuffer sourceVel = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F });
    
    private FloatBuffer sourcePos = BufferUtils.createFloatBuffer(3);
    
    private int maxSources = 64;
    
    public void clear() {
        store = new SoundStore();
    }
    
    public void disable() {
        this.inited = true;
    }
    
    public void setDeferredLoading(boolean deferred) {
        this.deferred = deferred;
    }
    
    public boolean isDeferredLoading() {
        return this.deferred;
    }
    
    public void setMusicOn(boolean music) {
        if (this.soundWorks) {
            this.music = music;
            if (music) {
                restartLoop();
                setMusicVolume(this.musicVolume);
            } else {
                pauseLoop();
            } 
        } 
    }
    
    public boolean isMusicOn() {
        return this.music;
    }
    
    public void setMusicVolume(float volume) {
        if (volume < 0.0F)
            volume = 0.0F; 
        if (volume > 1.0F)
            volume = 1.0F; 
        this.musicVolume = volume;
        if (this.soundWorks)
            AL10.alSourcef(this.sources.get(0), 4106, this.lastCurrentMusicVolume * this.musicVolume); 
    }
    
    public float getCurrentMusicVolume() {
        return this.lastCurrentMusicVolume;
    }
    
    public void setCurrentMusicVolume(float volume) {
        if (volume < 0.0F)
            volume = 0.0F; 
        if (volume > 1.0F)
            volume = 1.0F; 
        if (this.soundWorks) {
            this.lastCurrentMusicVolume = volume;
            AL10.alSourcef(this.sources.get(0), 4106, this.lastCurrentMusicVolume * this.musicVolume);
        } 
    }
    
    public void setSoundVolume(float volume) {
        if (volume < 0.0F)
            volume = 0.0F; 
        this.soundVolume = volume;
    }
    
    public boolean soundWorks() {
        return this.soundWorks;
    }
    
    public boolean musicOn() {
        return this.music;
    }
    
    public float getSoundVolume() {
        return this.soundVolume;
    }
    
    public float getMusicVolume() {
        return this.musicVolume;
    }
    
    public int getSource(int index) {
        if (!this.soundWorks)
            return -1; 
        if (index < 0)
            return -1; 
        return this.sources.get(index);
    }
    
    public void setSoundsOn(boolean sounds) {
        if (this.soundWorks)
            this.sounds = sounds; 
    }
    
    public boolean soundsOn() {
        return this.sounds;
    }
    
    public void setMaxSources(int max) {
        this.maxSources = max;
    }
    
    public void init() {
        if (this.inited)
            return; 
        Log.info("Initialising sounds..");
        this.inited = true;
        AccessController.doPrivileged(new PrivilegedAction() {
                    public Object run() {
                        try {
                            AL.create();
                            SoundStore.this.soundWorks = true;
                            SoundStore.this.sounds = true;
                            SoundStore.this.music = true;
                            Log.info("- Sound works");
                        } catch (Exception e) {
                            Log.error("Sound initialisation failure.");
                            Log.error(e);
                            SoundStore.this.soundWorks = false;
                            SoundStore.this.sounds = false;
                            SoundStore.this.music = false;
                        } 
                        return null;
                    }
                });
        if (this.soundWorks) {
            this.sourceCount = 0;
            this.sources = BufferUtils.createIntBuffer(this.maxSources);
            while (AL10.alGetError() == 0) {
                IntBuffer temp = BufferUtils.createIntBuffer(1);
                try {
                    AL10.alGenSources(temp);
                    if (AL10.alGetError() == 0) {
                        this.sourceCount++;
                        this.sources.put(temp.get(0));
                        if (this.sourceCount > this.maxSources - 1)
                            break; 
                    } 
                } catch (OpenALException e) {
                    break;
                } 
            } 
            Log.info("- " + this.sourceCount + " OpenAL source available");
            if (AL10.alGetError() != 0) {
                this.sounds = false;
                this.music = false;
                this.soundWorks = false;
                Log.error("- AL init failed");
            } else {
                FloatBuffer listenerOri = BufferUtils.createFloatBuffer(6).put(new float[] { 0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 0.0F });
                FloatBuffer listenerVel = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F });
                FloatBuffer listenerPos = BufferUtils.createFloatBuffer(3).put(new float[] { 0.0F, 0.0F, 0.0F });
                listenerPos.flip();
                listenerVel.flip();
                listenerOri.flip();
                AL10.alListener(4100, listenerPos);
                AL10.alListener(4102, listenerVel);
                AL10.alListener(4111, listenerOri);
                Log.info("- Sounds source generated");
            } 
        } 
    }
    
    void stopSource(int index) {
        AL10.alSourceStop(this.sources.get(index));
    }
    
    int playAsSound(int buffer, float pitch, float gain, boolean loop) {
        return playAsSoundAt(buffer, pitch, gain, loop, 0.0F, 0.0F, 0.0F);
    }
    
    int playAsSoundAt(int buffer, float pitch, float gain, boolean loop, float x, float y, float z) {
        gain *= this.soundVolume;
        if (gain == 0.0F)
            gain = 0.001F; 
        if (this.soundWorks && 
            this.sounds) {
            int nextSource = findFreeSource();
            if (nextSource == -1)
                return -1; 
            AL10.alSourceStop(this.sources.get(nextSource));
            AL10.alSourcei(this.sources.get(nextSource), 4105, buffer);
            AL10.alSourcef(this.sources.get(nextSource), 4099, pitch);
            AL10.alSourcef(this.sources.get(nextSource), 4106, gain);
            AL10.alSourcei(this.sources.get(nextSource), 4103, loop ? 1 : 0);
            this.sourcePos.clear();
            this.sourceVel.clear();
            this.sourceVel.put(new float[] { 0.0F, 0.0F, 0.0F });
            this.sourcePos.put(new float[] { x, y, z });
            this.sourcePos.flip();
            this.sourceVel.flip();
            AL10.alSource(this.sources.get(nextSource), 4100, this.sourcePos);
            AL10.alSource(this.sources.get(nextSource), 4102, this.sourceVel);
            AL10.alSourcePlay(this.sources.get(nextSource));
            return nextSource;
        } 
        return -1;
    }
    
    boolean isPlaying(int index) {
        int state = AL10.alGetSourcei(this.sources.get(index), 4112);
        return (state == 4114);
    }
    
    private int findFreeSource() {
        for (int i = 1; i < this.sourceCount - 1; i++) {
            int state = AL10.alGetSourcei(this.sources.get(i), 4112);
            if (state != 4114 && state != 4115)
                return i; 
        } 
        return -1;
    }
    
    void playAsMusic(int buffer, float pitch, float gain, boolean loop) {
        this.paused = false;
        setMOD(null);
        if (this.soundWorks) {
            if (this.currentMusic != -1)
                AL10.alSourceStop(this.sources.get(0)); 
            getMusicSource();
            AL10.alSourcei(this.sources.get(0), 4105, buffer);
            AL10.alSourcef(this.sources.get(0), 4099, pitch);
            AL10.alSourcei(this.sources.get(0), 4103, loop ? 1 : 0);
            this.currentMusic = this.sources.get(0);
            if (!this.music) {
                pauseLoop();
            } else {
                AL10.alSourcePlay(this.sources.get(0));
            } 
        } 
    }
    
    private int getMusicSource() {
        return this.sources.get(0);
    }
    
    public void setMusicPitch(float pitch) {
        if (this.soundWorks)
            AL10.alSourcef(this.sources.get(0), 4099, pitch); 
    }
    
    public void pauseLoop() {
        if (this.soundWorks && this.currentMusic != -1) {
            this.paused = true;
            AL10.alSourcePause(this.currentMusic);
        } 
    }
    
    public void restartLoop() {
        if (this.music && this.soundWorks && this.currentMusic != -1) {
            this.paused = false;
            AL10.alSourcePlay(this.currentMusic);
        } 
    }
    
    boolean isPlaying(OpenALStreamPlayer player) {
        return (this.stream == player);
    }
    
    public Audio getMOD(String ref) throws IOException {
        return getMOD(ref, ResourceLoader.getResourceAsStream(ref));
    }
    
    public Audio getMOD(InputStream in) throws IOException {
        return getMOD(in.toString(), in);
    }
    
    public Audio getMOD(String ref, InputStream in) throws IOException {
        if (!this.soundWorks)
            return new NullAudio(); 
        if (!this.inited)
            throw new RuntimeException("Can't load sounds until SoundStore is init(). Use the container init() method."); 
        if (this.deferred)
            return new DeferredSound(ref, in, 3); 
        return new MODSound(this, in);
    }
    
    public Audio getAIF(String ref) throws IOException {
        return getAIF(ref, ResourceLoader.getResourceAsStream(ref));
    }
    
    public Audio getAIF(InputStream in) throws IOException {
        return getAIF(in.toString(), in);
    }
    
    public Audio getAIF(String ref, InputStream in) throws IOException {
        in = new BufferedInputStream(in);
        if (!this.soundWorks)
            return new NullAudio(); 
        if (!this.inited)
            throw new RuntimeException("Can't load sounds until SoundStore is init(). Use the container init() method."); 
        if (this.deferred)
            return new DeferredSound(ref, in, 4); 
        int buffer = -1;
        if (this.loaded.get(ref) != null) {
            buffer = ((Integer)this.loaded.get(ref)).intValue();
        } else {
            try {
                IntBuffer buf = BufferUtils.createIntBuffer(1);
                AiffData data = AiffData.create(in);
                AL10.alGenBuffers(buf);
                AL10.alBufferData(buf.get(0), data.format, data.data, data.samplerate);
                this.loaded.put(ref, new Integer(buf.get(0)));
                buffer = buf.get(0);
            } catch (Exception e) {
                Log.error(e);
                IOException x = new IOException("Failed to load: " + ref);
                x.initCause(e);
                throw x;
            } 
        } 
        if (buffer == -1)
            throw new IOException("Unable to load: " + ref); 
        return new AudioImpl(this, buffer);
    }
    
    public Audio getWAV(String ref) throws IOException {
        return getWAV(ref, ResourceLoader.getResourceAsStream(ref));
    }
    
    public Audio getWAV(InputStream in) throws IOException {
        return getWAV(in.toString(), in);
    }
    
    public Audio getWAV(String ref, InputStream in) throws IOException {
        if (!this.soundWorks)
            return new NullAudio(); 
        if (!this.inited)
            throw new RuntimeException("Can't load sounds until SoundStore is init(). Use the container init() method."); 
        if (this.deferred)
            return new DeferredSound(ref, in, 2); 
        int buffer = -1;
        if (this.loaded.get(ref) != null) {
            buffer = ((Integer)this.loaded.get(ref)).intValue();
        } else {
            try {
                IntBuffer buf = BufferUtils.createIntBuffer(1);
                WaveData data = WaveData.create(in);
                AL10.alGenBuffers(buf);
                AL10.alBufferData(buf.get(0), data.format, data.data, data.samplerate);
                this.loaded.put(ref, new Integer(buf.get(0)));
                buffer = buf.get(0);
            } catch (Exception e) {
                Log.error(e);
                IOException x = new IOException("Failed to load: " + ref);
                x.initCause(e);
                throw x;
            } 
        } 
        if (buffer == -1)
            throw new IOException("Unable to load: " + ref); 
        return new AudioImpl(this, buffer);
    }
    
    public Audio getOggStream(String ref) throws IOException {
        if (!this.soundWorks)
            return new NullAudio(); 
        setMOD(null);
        setStream(null);
        if (this.currentMusic != -1)
            AL10.alSourceStop(this.sources.get(0)); 
        getMusicSource();
        this.currentMusic = this.sources.get(0);
        return new StreamSound(new OpenALStreamPlayer(this.currentMusic, ref));
    }
    
    public Audio getOggStream(URL ref) throws IOException {
        if (!this.soundWorks)
            return new NullAudio(); 
        setMOD(null);
        setStream(null);
        if (this.currentMusic != -1)
            AL10.alSourceStop(this.sources.get(0)); 
        getMusicSource();
        this.currentMusic = this.sources.get(0);
        return new StreamSound(new OpenALStreamPlayer(this.currentMusic, ref));
    }
    
    public Audio getOgg(String ref) throws IOException {
        return getOgg(ref, ResourceLoader.getResourceAsStream(ref));
    }
    
    public Audio getOgg(InputStream in) throws IOException {
        return getOgg(in.toString(), in);
    }
    
    public Audio getOgg(String ref, InputStream in) throws IOException {
        if (!this.soundWorks)
            return new NullAudio(); 
        if (!this.inited)
            throw new RuntimeException("Can't load sounds until SoundStore is init(). Use the container init() method."); 
        if (this.deferred)
            return new DeferredSound(ref, in, 1); 
        int buffer = -1;
        if (this.loaded.get(ref) != null) {
            buffer = ((Integer)this.loaded.get(ref)).intValue();
        } else {
            try {
                IntBuffer buf = BufferUtils.createIntBuffer(1);
                OggDecoder decoder = new OggDecoder();
                OggData ogg = decoder.getData(in);
                AL10.alGenBuffers(buf);
                AL10.alBufferData(buf.get(0), (ogg.channels > 1) ? 4355 : 4353, ogg.data, ogg.rate);
                this.loaded.put(ref, new Integer(buf.get(0)));
                buffer = buf.get(0);
            } catch (Exception e) {
                Log.error(e);
                Sys.alert("Error", "Failed to load: " + ref + " - " + e.getMessage());
                throw new IOException("Unable to load: " + ref);
            } 
        } 
        if (buffer == -1)
            throw new IOException("Unable to load: " + ref); 
        return new AudioImpl(this, buffer);
    }
    
    void setMOD(MODSound sound) {
        if (!this.soundWorks)
            return; 
        this.currentMusic = this.sources.get(0);
        stopSource(0);
        this.mod = sound;
        if (sound != null)
            this.stream = null; 
        this.paused = false;
    }
    
    void setStream(OpenALStreamPlayer stream) {
        if (!this.soundWorks)
            return; 
        this.currentMusic = this.sources.get(0);
        this.stream = stream;
        if (stream != null)
            this.mod = null; 
        this.paused = false;
    }
    
    public void poll(int delta) {
        if (!this.soundWorks)
            return; 
        if (this.paused)
            return; 
        if (this.music) {
            if (this.mod != null)
                try {
                    this.mod.poll();
                } catch (OpenALException e) {
                    Log.error("Error with OpenGL MOD Player on this this platform");
                    Log.error((Throwable)e);
                    this.mod = null;
                }  
            if (this.stream != null)
                try {
                    this.stream.update();
                } catch (OpenALException e) {
                    Log.error("Error with OpenGL Streaming Player on this this platform");
                    Log.error((Throwable)e);
                    this.mod = null;
                }  
        } 
    }
    
    public boolean isMusicPlaying() {
        if (!this.soundWorks)
            return false; 
        int state = AL10.alGetSourcei(this.sources.get(0), 4112);
        return (state == 4114 || state == 4115);
    }
    
    public static SoundStore get() {
        return store;
    }
    
    public void stopSoundEffect(int id) {
        AL10.alSourceStop(id);
    }
    
    public int getSourceCount() {
        return this.sourceCount;
    }
}

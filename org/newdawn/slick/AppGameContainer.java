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

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.opengl.CursorLoader;
import org.newdawn.slick.opengl.ImageData;
import org.newdawn.slick.opengl.ImageIOImageData;
import org.newdawn.slick.opengl.InternalTextureLoader;
import org.newdawn.slick.opengl.TGAImageData;
import org.newdawn.slick.util.Log;
import org.newdawn.slick.util.ResourceLoader;

public class AppGameContainer extends GameContainer {
    protected DisplayMode originalDisplayMode;
    
    protected DisplayMode targetDisplayMode;
    
    static {
        AccessController.doPrivileged(new PrivilegedAction() {
                    public Object run() {
                        try {
                            Display.getDisplayMode();
                        } catch (Exception e) {
                            Log.error(e);
                        } 
                        return null;
                    }
                });
    }
    
    protected boolean updateOnlyOnVisible = true;
    
    protected boolean alphaSupport = false;
    
    public AppGameContainer(Game game) throws SlickException {
        this(game, 640, 480, false);
    }
    
    public AppGameContainer(Game game, int width, int height, boolean fullscreen) throws SlickException {
        super(game);
        this.originalDisplayMode = Display.getDisplayMode();
        setDisplayMode(width, height, fullscreen);
    }
    
    public boolean supportsAlphaInBackBuffer() {
        return this.alphaSupport;
    }
    
    public void setTitle(String title) {
        Display.setTitle(title);
    }
    
    public void setDisplayMode(int width, int height, boolean fullscreen) throws SlickException {
        if (this.width == width && this.height == height && isFullscreen() == fullscreen)
            return; 
        try {
            this.targetDisplayMode = null;
            if (fullscreen) {
                DisplayMode[] modes = Display.getAvailableDisplayModes();
                int freq = 0;
                for (int i = 0; i < modes.length; i++) {
                    DisplayMode current = modes[i];
                    if (current.getWidth() == width && current.getHeight() == height) {
                        if ((this.targetDisplayMode == null || current.getFrequency() >= freq) && (
                            this.targetDisplayMode == null || current.getBitsPerPixel() > this.targetDisplayMode.getBitsPerPixel())) {
                            this.targetDisplayMode = current;
                            freq = this.targetDisplayMode.getFrequency();
                        } 
                        if (current.getBitsPerPixel() == this.originalDisplayMode.getBitsPerPixel() && current.getFrequency() == this.originalDisplayMode.getFrequency()) {
                            this.targetDisplayMode = current;
                            break;
                        } 
                    } 
                } 
            } else {
                this.targetDisplayMode = new DisplayMode(width, height);
            } 
            if (this.targetDisplayMode == null)
                throw new SlickException("Failed to find value mode: " + width + "x" + height + " fs=" + fullscreen); 
            this.width = width;
            this.height = height;
            Display.setDisplayMode(this.targetDisplayMode);
            Display.setFullscreen(fullscreen);
            if (Display.isCreated()) {
                initGL();
                enterOrtho();
            } 
            if (this.targetDisplayMode.getBitsPerPixel() == 16)
                InternalTextureLoader.get().set16BitMode(); 
        } catch (LWJGLException e) {
            throw new SlickException("Unable to setup mode " + width + "x" + height + " fullscreen=" + fullscreen, e);
        } 
        getDelta();
    }
    
    public boolean isFullscreen() {
        return Display.isFullscreen();
    }
    
    public void setFullscreen(boolean fullscreen) throws SlickException {
        if (isFullscreen() == fullscreen)
            return; 
        if (!fullscreen) {
            try {
                Display.setFullscreen(fullscreen);
            } catch (LWJGLException e) {
                throw new SlickException("Unable to set fullscreen=" + fullscreen, e);
            } 
        } else {
            setDisplayMode(this.width, this.height, fullscreen);
        } 
        getDelta();
    }
    
    public void setMouseCursor(String ref, int hotSpotX, int hotSpotY) throws SlickException {
        try {
            Cursor cursor = CursorLoader.get().getCursor(ref, hotSpotX, hotSpotY);
            Mouse.setNativeCursor(cursor);
        } catch (Throwable e) {
            Log.error("Failed to load and apply cursor.", e);
            throw new SlickException("Failed to set mouse cursor", e);
        } 
    }
    
    public void setMouseCursor(ImageData data, int hotSpotX, int hotSpotY) throws SlickException {
        try {
            Cursor cursor = CursorLoader.get().getCursor(data, hotSpotX, hotSpotY);
            Mouse.setNativeCursor(cursor);
        } catch (Throwable e) {
            Log.error("Failed to load and apply cursor.", e);
            throw new SlickException("Failed to set mouse cursor", e);
        } 
    }
    
    public void setMouseCursor(Cursor cursor, int hotSpotX, int hotSpotY) throws SlickException {
        try {
            Mouse.setNativeCursor(cursor);
        } catch (Throwable e) {
            Log.error("Failed to load and apply cursor.", e);
            throw new SlickException("Failed to set mouse cursor", e);
        } 
    }
    
    private int get2Fold(int fold) {
        int ret = 2;
        while (ret < fold)
            ret *= 2; 
        return ret;
    }
    
    public void setMouseCursor(Image image, int hotSpotX, int hotSpotY) throws SlickException {
        try {
            Image temp = new Image(get2Fold(image.getWidth()), get2Fold(image.getHeight()));
            Graphics g = temp.getGraphics();
            ByteBuffer buffer = BufferUtils.createByteBuffer(temp.getWidth() * temp.getHeight() * 4);
            g.drawImage(image.getFlippedCopy(false, true), 0.0F, 0.0F);
            g.flush();
            g.getArea(0, 0, temp.getWidth(), temp.getHeight(), buffer);
            Cursor cursor = CursorLoader.get().getCursor(buffer, hotSpotX, hotSpotY, temp.getWidth(), image.getHeight());
            Mouse.setNativeCursor(cursor);
        } catch (Throwable e) {
            Log.error("Failed to load and apply cursor.", e);
            throw new SlickException("Failed to set mouse cursor", e);
        } 
    }
    
    public void reinit() throws SlickException {
        InternalTextureLoader.get().clear();
        SoundStore.get().clear();
        initSystem();
        enterOrtho();
        try {
            this.game.init(this);
        } catch (SlickException e) {
            Log.error(e);
            this.running = false;
        } 
    }
    
    private void tryCreateDisplay(PixelFormat format) throws LWJGLException {
        if (SHARED_DRAWABLE == null) {
            Display.create(format);
        } else {
            Display.create(format, SHARED_DRAWABLE);
        } 
    }
    
    public void start() throws SlickException {
        try {
            setup();
            getDelta();
            while (running())
                gameLoop(); 
        } finally {
            destroy();
        } 
        if (this.forceExit)
            System.exit(0); 
    }
    
    protected void setup() throws SlickException {
        if (this.targetDisplayMode == null)
            setDisplayMode(640, 480, false); 
        Display.setTitle(this.game.getTitle());
        Log.info("LWJGL Version: " + Sys.getVersion());
        Log.info("OriginalDisplayMode: " + this.originalDisplayMode);
        Log.info("TargetDisplayMode: " + this.targetDisplayMode);
        AccessController.doPrivileged(new PrivilegedAction() {
                    public Object run() {
                        try {
                            PixelFormat format = new PixelFormat(8, 8, GameContainer.stencil ? 8 : 0, AppGameContainer.this.samples);
                            AppGameContainer.this.tryCreateDisplay(format);
                            AppGameContainer.this.supportsMultiSample = true;
                        } catch (Exception e) {
                            Display.destroy();
                            try {
                                PixelFormat format = new PixelFormat(8, 8, GameContainer.stencil ? 8 : 0);
                                AppGameContainer.this.tryCreateDisplay(format);
                                AppGameContainer.this.alphaSupport = false;
                            } catch (Exception e2) {
                                Display.destroy();
                                try {
                                    AppGameContainer.this.tryCreateDisplay(new PixelFormat());
                                } catch (Exception e3) {
                                    Log.error(e3);
                                } 
                            } 
                        } 
                        return null;
                    }
                });
        if (!Display.isCreated())
            throw new SlickException("Failed to initialise the LWJGL display"); 
        initSystem();
        enterOrtho();
        try {
            getInput().initControllers();
        } catch (SlickException e) {
            Log.info("Controllers not available");
        } catch (Throwable e) {
            Log.info("Controllers not available");
        } 
        try {
            this.game.init(this);
        } catch (SlickException e) {
            Log.error(e);
            this.running = false;
        } 
    }
    
    protected void gameLoop() throws SlickException {
        int delta = getDelta();
        if (!Display.isVisible() && this.updateOnlyOnVisible) {
            try {
                Thread.sleep(100L);
            } catch (Exception e) {}
        } else {
            try {
                updateAndRender(delta);
            } catch (SlickException e) {
                Log.error(e);
                this.running = false;
                return;
            } 
        } 
        updateFPS();
        Display.update();
        if (Display.isCloseRequested() && 
            this.game.closeRequested())
            this.running = false; 
    }
    
    public void setUpdateOnlyWhenVisible(boolean updateOnlyWhenVisible) {
        this.updateOnlyOnVisible = updateOnlyWhenVisible;
    }
    
    public boolean isUpdatingOnlyWhenVisible() {
        return this.updateOnlyOnVisible;
    }
    
    public void setIcon(String ref) throws SlickException {
        setIcons(new String[] { ref });
    }
    
    public void setMouseGrabbed(boolean grabbed) {
        Mouse.setGrabbed(grabbed);
    }
    
    public boolean isMouseGrabbed() {
        return Mouse.isGrabbed();
    }
    
    public boolean hasFocus() {
        return Display.isActive();
    }
    
    public int getScreenHeight() {
        return this.originalDisplayMode.getHeight();
    }
    
    public int getScreenWidth() {
        return this.originalDisplayMode.getWidth();
    }
    
    public void destroy() {
        Display.destroy();
        AL.destroy();
    }
    
    private class NullOutputStream extends OutputStream {
        public void write(int b) throws IOException {}
    }
    
    public void setIcons(String[] refs) throws SlickException {
        ByteBuffer[] bufs = new ByteBuffer[refs.length];
        for (int i = 0; i < refs.length; i++) {
            ImageIOImageData imageIOImageData;
            boolean flip = true;
            if (refs[i].endsWith(".tga")) {
                TGAImageData tGAImageData = new TGAImageData();
            } else {
                flip = false;
                imageIOImageData = new ImageIOImageData();
            } 
            try {
                bufs[i] = imageIOImageData.loadImage(ResourceLoader.getResourceAsStream(refs[i]), flip, false, null);
            } catch (Exception e) {
                Log.error(e);
                throw new SlickException("Failed to set the icon");
            } 
        } 
        Display.setIcon(bufs);
    }
    
    public void setDefaultMouseCursor() {
        try {
            Mouse.setNativeCursor(null);
        } catch (LWJGLException e) {
            Log.error("Failed to reset mouse cursor", (Throwable)e);
        } 
    }
}

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

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.opengl.CursorLoader;
import org.newdawn.slick.opengl.ImageData;
import org.newdawn.slick.opengl.InternalTextureLoader;
import org.newdawn.slick.util.Log;

public class AppletGameContainer extends Applet {
    protected ContainerPanel canvas;
    
    protected Container container;
    
    protected Canvas displayParent;
    
    protected Thread gameThread;
    
    protected boolean alphaSupport = true;
    
    public void destroy() {
        if (this.displayParent != null)
            remove(this.displayParent); 
        super.destroy();
        Log.info("Clear up");
    }
    
    private void destroyLWJGL() {
        this.container.stopApplet();
        try {
            this.gameThread.join();
        } catch (InterruptedException e) {
            Log.error(e);
        } 
    }
    
    public void start() {}
    
    public void startLWJGL() {
        if (this.gameThread != null)
            return; 
        this.gameThread = new Thread() {
                public void run() {
                    try {
                        AppletGameContainer.this.canvas.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (Display.isCreated())
                            Display.destroy(); 
                        AppletGameContainer.this.displayParent.setVisible(false);
                        AppletGameContainer.this.add(new AppletGameContainer.ConsolePanel(e));
                        AppletGameContainer.this.validate();
                    } 
                }
            };
        this.gameThread.start();
    }
    
    public void stop() {}
    
    public void init() {
        removeAll();
        setLayout(new BorderLayout());
        setIgnoreRepaint(true);
        try {
            Game game = (Game)Class.forName(getParameter("game")).newInstance();
            this.container = new Container(game);
            this.canvas = new ContainerPanel(this.container);
            this.displayParent = new Canvas() {
                    public final void addNotify() {
                        super.addNotify();
                        AppletGameContainer.this.startLWJGL();
                    }
                    
                    public final void removeNotify() {
                        AppletGameContainer.this.destroyLWJGL();
                        super.removeNotify();
                    }
                };
            this.displayParent.setSize(getWidth(), getHeight());
            add(this.displayParent);
            this.displayParent.setFocusable(true);
            this.displayParent.requestFocus();
            this.displayParent.setIgnoreRepaint(true);
            setVisible(true);
        } catch (Exception e) {
            Log.error(e);
            throw new RuntimeException("Unable to create game container");
        } 
    }
    
    public GameContainer getContainer() {
        return this.container;
    }
    
    public class ContainerPanel {
        private AppletGameContainer.Container container;
        
        public ContainerPanel(AppletGameContainer.Container container) {
            this.container = container;
        }
        
        private void createDisplay() throws Exception {
            try {
                Display.create(new PixelFormat(8, 8, GameContainer.stencil ? 8 : 0));
                AppletGameContainer.this.alphaSupport = true;
            } catch (Exception e) {
                AppletGameContainer.this.alphaSupport = false;
                Display.destroy();
                Display.create();
            } 
        }
        
        public void start() throws Exception {
            Display.setParent(AppletGameContainer.this.displayParent);
            Display.setVSyncEnabled(true);
            try {
                createDisplay();
            } catch (LWJGLException e) {
                e.printStackTrace();
                Thread.sleep(1000L);
                createDisplay();
            } 
            initGL();
            AppletGameContainer.this.displayParent.requestFocus();
            this.container.runloop();
        }
        
        protected void initGL() {
            try {
                InternalTextureLoader.get().clear();
                SoundStore.get().clear();
                this.container.initApplet();
            } catch (Exception e) {
                Log.error(e);
                this.container.stopApplet();
            } 
        }
    }
    
    public class Container extends GameContainer {
        public Container(Game game) {
            super(game);
            this.width = AppletGameContainer.this.getWidth();
            this.height = AppletGameContainer.this.getHeight();
        }
        
        public void initApplet() throws SlickException {
            initSystem();
            enterOrtho();
            try {
                getInput().initControllers();
            } catch (SlickException e) {
                Log.info("Controllers not available");
            } catch (Throwable e) {
                Log.info("Controllers not available");
            } 
            this.game.init(this);
            getDelta();
        }
        
        public boolean isRunning() {
            return this.running;
        }
        
        public void stopApplet() {
            this.running = false;
        }
        
        public int getScreenHeight() {
            return 0;
        }
        
        public int getScreenWidth() {
            return 0;
        }
        
        public boolean supportsAlphaInBackBuffer() {
            return AppletGameContainer.this.alphaSupport;
        }
        
        public boolean hasFocus() {
            return true;
        }
        
        public Applet getApplet() {
            return AppletGameContainer.this;
        }
        
        public void setIcon(String ref) throws SlickException {}
        
        public void setMouseGrabbed(boolean grabbed) {
            Mouse.setGrabbed(grabbed);
        }
        
        public boolean isMouseGrabbed() {
            return Mouse.isGrabbed();
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
                Cursor cursor = CursorLoader.get().getCursor(buffer, hotSpotX, hotSpotY, temp.getWidth(), temp.getHeight());
                Mouse.setNativeCursor(cursor);
            } catch (Throwable e) {
                Log.error("Failed to load and apply cursor.", e);
                throw new SlickException("Failed to set mouse cursor", e);
            } 
        }
        
        public void setIcons(String[] refs) throws SlickException {}
        
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
        
        public void setDefaultMouseCursor() {}
        
        public boolean isFullscreen() {
            return Display.isFullscreen();
        }
        
        public void setFullscreen(boolean fullscreen) throws SlickException {
            if (fullscreen == isFullscreen())
                return; 
            try {
                if (fullscreen) {
                    int newWidth, newHeight, screenWidth = Display.getDisplayMode().getWidth();
                    int screenHeight = Display.getDisplayMode().getHeight();
                    float gameAspectRatio = this.width / this.height;
                    float screenAspectRatio = screenWidth / screenHeight;
                    if (gameAspectRatio >= screenAspectRatio) {
                        newWidth = screenWidth;
                        newHeight = (int)(this.height / this.width / screenWidth);
                    } else {
                        newWidth = (int)(this.width / this.height / screenHeight);
                        newHeight = screenHeight;
                    } 
                    int xoffset = (screenWidth - newWidth) / 2;
                    int yoffset = (screenHeight - newHeight) / 2;
                    GL11.glViewport(xoffset, yoffset, newWidth, newHeight);
                    enterOrtho();
                    getInput().setOffset(-xoffset * this.width / newWidth, -yoffset * this.height / newHeight);
                    getInput().setScale(this.width / newWidth, this.height / newHeight);
                    this.width = screenWidth;
                    this.height = screenHeight;
                    Display.setFullscreen(true);
                } else {
                    getInput().setOffset(0.0F, 0.0F);
                    getInput().setScale(1.0F, 1.0F);
                    this.width = AppletGameContainer.this.getWidth();
                    this.height = AppletGameContainer.this.getHeight();
                    GL11.glViewport(0, 0, this.width, this.height);
                    enterOrtho();
                    Display.setFullscreen(false);
                } 
            } catch (LWJGLException e) {
                Log.error((Throwable)e);
            } 
        }
        
        public void runloop() throws Exception {
            while (this.running) {
                int delta = getDelta();
                updateAndRender(delta);
                updateFPS();
                Display.update();
            } 
            Display.destroy();
        }
    }
    
    public class ConsolePanel extends Panel {
        TextArea textArea = new TextArea();
        
        public ConsolePanel(Exception e) {
            setLayout(new BorderLayout());
            setBackground(Color.black);
            setForeground(Color.white);
            Font consoleFont = new Font("Arial", 1, 14);
            Label slickLabel = new Label("SLICK CONSOLE", 1);
            slickLabel.setFont(consoleFont);
            add(slickLabel, "First");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            this.textArea.setText(sw.toString());
            this.textArea.setEditable(false);
            add(this.textArea, "Center");
            add(new Panel(), "Before");
            add(new Panel(), "After");
            Panel bottomPanel = new Panel();
            bottomPanel.setLayout(new GridLayout(0, 1));
            Label infoLabel1 = new Label("An error occured while running the applet.", 1);
            Label infoLabel2 = new Label("Plese contact support to resolve this issue.", 1);
            infoLabel1.setFont(consoleFont);
            infoLabel2.setFont(consoleFont);
            bottomPanel.add(infoLabel1);
            bottomPanel.add(infoLabel2);
            add(bottomPanel, "Last");
        }
    }
}

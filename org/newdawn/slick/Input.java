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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.util.Log;

public class Input {
    public static final int ANY_CONTROLLER = -1;
    
    private static final int MAX_BUTTONS = 100;
    
    public static final int KEY_ESCAPE = 1;
    
    public static final int KEY_1 = 2;
    
    public static final int KEY_2 = 3;
    
    public static final int KEY_3 = 4;
    
    public static final int KEY_4 = 5;
    
    public static final int KEY_5 = 6;
    
    public static final int KEY_6 = 7;
    
    public static final int KEY_7 = 8;
    
    public static final int KEY_8 = 9;
    
    public static final int KEY_9 = 10;
    
    public static final int KEY_0 = 11;
    
    public static final int KEY_MINUS = 12;
    
    public static final int KEY_EQUALS = 13;
    
    public static final int KEY_BACK = 14;
    
    public static final int KEY_TAB = 15;
    
    public static final int KEY_Q = 16;
    
    public static final int KEY_W = 17;
    
    public static final int KEY_E = 18;
    
    public static final int KEY_R = 19;
    
    public static final int KEY_T = 20;
    
    public static final int KEY_Y = 21;
    
    public static final int KEY_U = 22;
    
    public static final int KEY_I = 23;
    
    public static final int KEY_O = 24;
    
    public static final int KEY_P = 25;
    
    public static final int KEY_LBRACKET = 26;
    
    public static final int KEY_RBRACKET = 27;
    
    public static final int KEY_RETURN = 28;
    
    public static final int KEY_ENTER = 28;
    
    public static final int KEY_LCONTROL = 29;
    
    public static final int KEY_A = 30;
    
    public static final int KEY_S = 31;
    
    public static final int KEY_D = 32;
    
    public static final int KEY_F = 33;
    
    public static final int KEY_G = 34;
    
    public static final int KEY_H = 35;
    
    public static final int KEY_J = 36;
    
    public static final int KEY_K = 37;
    
    public static final int KEY_L = 38;
    
    public static final int KEY_SEMICOLON = 39;
    
    public static final int KEY_APOSTROPHE = 40;
    
    public static final int KEY_GRAVE = 41;
    
    public static final int KEY_LSHIFT = 42;
    
    public static final int KEY_BACKSLASH = 43;
    
    public static final int KEY_Z = 44;
    
    public static final int KEY_X = 45;
    
    public static final int KEY_C = 46;
    
    public static final int KEY_V = 47;
    
    public static final int KEY_B = 48;
    
    public static final int KEY_N = 49;
    
    public static final int KEY_M = 50;
    
    public static final int KEY_COMMA = 51;
    
    public static final int KEY_PERIOD = 52;
    
    public static final int KEY_SLASH = 53;
    
    public static final int KEY_RSHIFT = 54;
    
    public static final int KEY_MULTIPLY = 55;
    
    public static final int KEY_LMENU = 56;
    
    public static final int KEY_SPACE = 57;
    
    public static final int KEY_CAPITAL = 58;
    
    public static final int KEY_F1 = 59;
    
    public static final int KEY_F2 = 60;
    
    public static final int KEY_F3 = 61;
    
    public static final int KEY_F4 = 62;
    
    public static final int KEY_F5 = 63;
    
    public static final int KEY_F6 = 64;
    
    public static final int KEY_F7 = 65;
    
    public static final int KEY_F8 = 66;
    
    public static final int KEY_F9 = 67;
    
    public static final int KEY_F10 = 68;
    
    public static final int KEY_NUMLOCK = 69;
    
    public static final int KEY_SCROLL = 70;
    
    public static final int KEY_NUMPAD7 = 71;
    
    public static final int KEY_NUMPAD8 = 72;
    
    public static final int KEY_NUMPAD9 = 73;
    
    public static final int KEY_SUBTRACT = 74;
    
    public static final int KEY_NUMPAD4 = 75;
    
    public static final int KEY_NUMPAD5 = 76;
    
    public static final int KEY_NUMPAD6 = 77;
    
    public static final int KEY_ADD = 78;
    
    public static final int KEY_NUMPAD1 = 79;
    
    public static final int KEY_NUMPAD2 = 80;
    
    public static final int KEY_NUMPAD3 = 81;
    
    public static final int KEY_NUMPAD0 = 82;
    
    public static final int KEY_DECIMAL = 83;
    
    public static final int KEY_F11 = 87;
    
    public static final int KEY_F12 = 88;
    
    public static final int KEY_F13 = 100;
    
    public static final int KEY_F14 = 101;
    
    public static final int KEY_F15 = 102;
    
    public static final int KEY_KANA = 112;
    
    public static final int KEY_CONVERT = 121;
    
    public static final int KEY_NOCONVERT = 123;
    
    public static final int KEY_YEN = 125;
    
    public static final int KEY_NUMPADEQUALS = 141;
    
    public static final int KEY_CIRCUMFLEX = 144;
    
    public static final int KEY_AT = 145;
    
    public static final int KEY_COLON = 146;
    
    public static final int KEY_UNDERLINE = 147;
    
    public static final int KEY_KANJI = 148;
    
    public static final int KEY_STOP = 149;
    
    public static final int KEY_AX = 150;
    
    public static final int KEY_UNLABELED = 151;
    
    public static final int KEY_NUMPADENTER = 156;
    
    public static final int KEY_RCONTROL = 157;
    
    public static final int KEY_NUMPADCOMMA = 179;
    
    public static final int KEY_DIVIDE = 181;
    
    public static final int KEY_SYSRQ = 183;
    
    public static final int KEY_RMENU = 184;
    
    public static final int KEY_PAUSE = 197;
    
    public static final int KEY_HOME = 199;
    
    public static final int KEY_UP = 200;
    
    public static final int KEY_PRIOR = 201;
    
    public static final int KEY_LEFT = 203;
    
    public static final int KEY_RIGHT = 205;
    
    public static final int KEY_END = 207;
    
    public static final int KEY_DOWN = 208;
    
    public static final int KEY_NEXT = 209;
    
    public static final int KEY_INSERT = 210;
    
    public static final int KEY_DELETE = 211;
    
    public static final int KEY_LWIN = 219;
    
    public static final int KEY_RWIN = 220;
    
    public static final int KEY_APPS = 221;
    
    public static final int KEY_POWER = 222;
    
    public static final int KEY_SLEEP = 223;
    
    public static final int KEY_LALT = 56;
    
    public static final int KEY_RALT = 184;
    
    private static final int LEFT = 0;
    
    private static final int RIGHT = 1;
    
    private static final int UP = 2;
    
    private static final int DOWN = 3;
    
    private static final int BUTTON1 = 4;
    
    private static final int BUTTON2 = 5;
    
    private static final int BUTTON3 = 6;
    
    private static final int BUTTON4 = 7;
    
    private static final int BUTTON5 = 8;
    
    private static final int BUTTON6 = 9;
    
    private static final int BUTTON7 = 10;
    
    private static final int BUTTON8 = 11;
    
    private static final int BUTTON9 = 12;
    
    private static final int BUTTON10 = 13;
    
    public static final int MOUSE_LEFT_BUTTON = 0;
    
    public static final int MOUSE_RIGHT_BUTTON = 1;
    
    public static final int MOUSE_MIDDLE_BUTTON = 2;
    
    private static boolean controllersInited = false;
    
    private static ArrayList controllers = new ArrayList();
    
    private int lastMouseX;
    
    private int lastMouseY;
    
    protected boolean[] mousePressed = new boolean[10];
    
    private boolean[][] controllerPressed = new boolean[100][100];
    
    protected char[] keys = new char[1024];
    
    protected boolean[] pressed = new boolean[1024];
    
    protected long[] nextRepeat = new long[1024];
    
    private boolean[][] controls = new boolean[10][110];
    
    protected boolean consumed = false;
    
    protected HashSet allListeners = new HashSet();
    
    protected ArrayList keyListeners = new ArrayList();
    
    protected ArrayList keyListenersToAdd = new ArrayList();
    
    protected ArrayList mouseListeners = new ArrayList();
    
    protected ArrayList mouseListenersToAdd = new ArrayList();
    
    protected ArrayList controllerListeners = new ArrayList();
    
    private int wheel;
    
    private int height;
    
    private boolean displayActive = true;
    
    private boolean keyRepeat;
    
    private int keyRepeatInitial;
    
    private int keyRepeatInterval;
    
    private boolean paused;
    
    private float scaleX = 1.0F;
    
    private float scaleY = 1.0F;
    
    private float xoffset = 0.0F;
    
    private float yoffset = 0.0F;
    
    private int doubleClickDelay = 250;
    
    private long doubleClickTimeout = 0L;
    
    private int clickX;
    
    private int clickY;
    
    private int clickButton;
    
    private int pressedX = -1;
    
    private int pressedY = -1;
    
    private int mouseClickTolerance = 5;
    
    public static void disableControllers() {
        controllersInited = true;
    }
    
    public Input(int height) {
        init(height);
    }
    
    public void setDoubleClickInterval(int delay) {
        this.doubleClickDelay = delay;
    }
    
    public void setMouseClickTolerance(int mouseClickTolerance) {
        this.mouseClickTolerance = mouseClickTolerance;
    }
    
    public void setScale(float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }
    
    public void setOffset(float xoffset, float yoffset) {
        this.xoffset = xoffset;
        this.yoffset = yoffset;
    }
    
    public void resetInputTransform() {
        setOffset(0.0F, 0.0F);
        setScale(1.0F, 1.0F);
    }
    
    public void addListener(InputListener listener) {
        addKeyListener(listener);
        addMouseListener(listener);
        addControllerListener(listener);
    }
    
    public void addKeyListener(KeyListener listener) {
        this.keyListenersToAdd.add(listener);
    }
    
    private void addKeyListenerImpl(KeyListener listener) {
        if (this.keyListeners.contains(listener))
            return; 
        this.keyListeners.add(listener);
        this.allListeners.add(listener);
    }
    
    public void addMouseListener(MouseListener listener) {
        this.mouseListenersToAdd.add(listener);
    }
    
    private void addMouseListenerImpl(MouseListener listener) {
        if (this.mouseListeners.contains(listener))
            return; 
        this.mouseListeners.add(listener);
        this.allListeners.add(listener);
    }
    
    public void addControllerListener(ControllerListener listener) {
        if (this.controllerListeners.contains(listener))
            return; 
        this.controllerListeners.add(listener);
        this.allListeners.add(listener);
    }
    
    public void removeAllListeners() {
        removeAllKeyListeners();
        removeAllMouseListeners();
        removeAllControllerListeners();
    }
    
    public void removeAllKeyListeners() {
        this.allListeners.removeAll(this.keyListeners);
        this.keyListeners.clear();
    }
    
    public void removeAllMouseListeners() {
        this.allListeners.removeAll(this.mouseListeners);
        this.mouseListeners.clear();
    }
    
    public void removeAllControllerListeners() {
        this.allListeners.removeAll(this.controllerListeners);
        this.controllerListeners.clear();
    }
    
    public void addPrimaryListener(InputListener listener) {
        removeListener(listener);
        this.keyListeners.add(0, listener);
        this.mouseListeners.add(0, listener);
        this.controllerListeners.add(0, listener);
        this.allListeners.add(listener);
    }
    
    public void removeListener(InputListener listener) {
        removeKeyListener(listener);
        removeMouseListener(listener);
        removeControllerListener(listener);
    }
    
    public void removeKeyListener(KeyListener listener) {
        this.keyListeners.remove(listener);
        if (!this.mouseListeners.contains(listener) && !this.controllerListeners.contains(listener))
            this.allListeners.remove(listener); 
    }
    
    public void removeControllerListener(ControllerListener listener) {
        this.controllerListeners.remove(listener);
        if (!this.mouseListeners.contains(listener) && !this.keyListeners.contains(listener))
            this.allListeners.remove(listener); 
    }
    
    public void removeMouseListener(MouseListener listener) {
        this.mouseListeners.remove(listener);
        if (!this.controllerListeners.contains(listener) && !this.keyListeners.contains(listener))
            this.allListeners.remove(listener); 
    }
    
    void init(int height) {
        this.height = height;
        this.lastMouseX = getMouseX();
        this.lastMouseY = getMouseY();
    }
    
    public static String getKeyName(int code) {
        return Keyboard.getKeyName(code);
    }
    
    public boolean isKeyPressed(int code) {
        if (this.pressed[code]) {
            this.pressed[code] = false;
            return true;
        } 
        return false;
    }
    
    public boolean isMousePressed(int button) {
        if (this.mousePressed[button]) {
            this.mousePressed[button] = false;
            return true;
        } 
        return false;
    }
    
    public boolean isControlPressed(int button) {
        return isControlPressed(button, 0);
    }
    
    public boolean isControlPressed(int button, int controller) {
        if (this.controllerPressed[controller][button]) {
            this.controllerPressed[controller][button] = false;
            return true;
        } 
        return false;
    }
    
    public void clearControlPressedRecord() {
        for (int i = 0; i < controllers.size(); i++)
            Arrays.fill(this.controllerPressed[i], false); 
    }
    
    public void clearKeyPressedRecord() {
        Arrays.fill(this.pressed, false);
    }
    
    public void clearMousePressedRecord() {
        Arrays.fill(this.mousePressed, false);
    }
    
    public boolean isKeyDown(int code) {
        return Keyboard.isKeyDown(code);
    }
    
    public int getAbsoluteMouseX() {
        return Mouse.getX();
    }
    
    public int getAbsoluteMouseY() {
        return this.height - Mouse.getY();
    }
    
    public int getMouseX() {
        return (int)(Mouse.getX() * this.scaleX + this.xoffset);
    }
    
    public int getMouseY() {
        return (int)((this.height - Mouse.getY()) * this.scaleY + this.yoffset);
    }
    
    public boolean isMouseButtonDown(int button) {
        return Mouse.isButtonDown(button);
    }
    
    private boolean anyMouseDown() {
        for (int i = 0; i < 3; i++) {
            if (Mouse.isButtonDown(i))
                return true; 
        } 
        return false;
    }
    
    public int getControllerCount() {
        try {
            initControllers();
        } catch (SlickException e) {
            throw new RuntimeException("Failed to initialise controllers");
        } 
        return controllers.size();
    }
    
    public int getAxisCount(int controller) {
        return ((Controller)controllers.get(controller)).getAxisCount();
    }
    
    public float getAxisValue(int controller, int axis) {
        return ((Controller)controllers.get(controller)).getAxisValue(axis);
    }
    
    public String getAxisName(int controller, int axis) {
        return ((Controller)controllers.get(controller)).getAxisName(axis);
    }
    
    public boolean isControllerLeft(int controller) {
        if (controller >= getControllerCount())
            return false; 
        if (controller == -1) {
            for (int i = 0; i < controllers.size(); i++) {
                if (isControllerLeft(i))
                    return true; 
            } 
            return false;
        } 
        return (((Controller)controllers.get(controller)).getXAxisValue() < -0.5F || ((Controller)controllers.get(controller)).getPovX() < -0.5F);
    }
    
    public boolean isControllerRight(int controller) {
        if (controller >= getControllerCount())
            return false; 
        if (controller == -1) {
            for (int i = 0; i < controllers.size(); i++) {
                if (isControllerRight(i))
                    return true; 
            } 
            return false;
        } 
        return (((Controller)controllers.get(controller)).getXAxisValue() > 0.5F || ((Controller)controllers.get(controller)).getPovX() > 0.5F);
    }
    
    public boolean isControllerUp(int controller) {
        if (controller >= getControllerCount())
            return false; 
        if (controller == -1) {
            for (int i = 0; i < controllers.size(); i++) {
                if (isControllerUp(i))
                    return true; 
            } 
            return false;
        } 
        return (((Controller)controllers.get(controller)).getYAxisValue() < -0.5F || ((Controller)controllers.get(controller)).getPovY() < -0.5F);
    }
    
    public boolean isControllerDown(int controller) {
        if (controller >= getControllerCount())
            return false; 
        if (controller == -1) {
            for (int i = 0; i < controllers.size(); i++) {
                if (isControllerDown(i))
                    return true; 
            } 
            return false;
        } 
        return (((Controller)controllers.get(controller)).getYAxisValue() > 0.5F || ((Controller)controllers.get(controller)).getPovY() > 0.5F);
    }
    
    public boolean isButtonPressed(int index, int controller) {
        if (controller >= getControllerCount())
            return false; 
        if (controller == -1) {
            for (int i = 0; i < controllers.size(); i++) {
                if (isButtonPressed(index, i))
                    return true; 
            } 
            return false;
        } 
        return ((Controller)controllers.get(controller)).isButtonPressed(index);
    }
    
    public boolean isButton1Pressed(int controller) {
        return isButtonPressed(0, controller);
    }
    
    public boolean isButton2Pressed(int controller) {
        return isButtonPressed(1, controller);
    }
    
    public boolean isButton3Pressed(int controller) {
        return isButtonPressed(2, controller);
    }
    
    public void initControllers() throws SlickException {
        if (controllersInited)
            return; 
        controllersInited = true;
        try {
            Controllers.create();
            int count = Controllers.getControllerCount();
            int i;
            for (i = 0; i < count; i++) {
                Controller controller = Controllers.getController(i);
                if (controller.getButtonCount() >= 3 && controller.getButtonCount() < 100)
                    controllers.add(controller); 
            } 
            Log.info("Found " + controllers.size() + " controllers");
            for (i = 0; i < controllers.size(); i++)
                Log.info(i + " : " + ((Controller)controllers.get(i)).getName()); 
        } catch (LWJGLException e) {
            if (e.getCause() instanceof ClassNotFoundException)
                throw new SlickException("Unable to create controller - no jinput found - add jinput.jar to your classpath"); 
            throw new SlickException("Unable to create controllers");
        } catch (NoClassDefFoundError e) {}
    }
    
    public void consumeEvent() {
        this.consumed = true;
    }
    
    private class NullOutputStream extends OutputStream {
        public void write(int b) throws IOException {}
    }
    
    private int resolveEventKey(int key, char c) {
        if (c == '=' || key == 0)
            return 13; 
        return key;
    }
    
    public void considerDoubleClick(int button, int x, int y) {
        if (this.doubleClickTimeout == 0L) {
            this.clickX = x;
            this.clickY = y;
            this.clickButton = button;
            this.doubleClickTimeout = System.currentTimeMillis() + this.doubleClickDelay;
            fireMouseClicked(button, x, y, 1);
        } else if (this.clickButton == button && 
            System.currentTimeMillis() < this.doubleClickTimeout) {
            fireMouseClicked(button, x, y, 2);
            this.doubleClickTimeout = 0L;
        } 
    }
    
    public void poll(int width, int height) {
        if (this.paused) {
            clearControlPressedRecord();
            clearKeyPressedRecord();
            clearMousePressedRecord();
            while (Keyboard.next());
            while (Mouse.next());
            return;
        } 
        if (!Display.isActive()) {
            clearControlPressedRecord();
            clearKeyPressedRecord();
            clearMousePressedRecord();
        } 
        int i;
        for (i = 0; i < this.keyListenersToAdd.size(); i++)
            addKeyListenerImpl(this.keyListenersToAdd.get(i)); 
        this.keyListenersToAdd.clear();
        for (i = 0; i < this.mouseListenersToAdd.size(); i++)
            addMouseListenerImpl(this.mouseListenersToAdd.get(i)); 
        this.mouseListenersToAdd.clear();
        if (this.doubleClickTimeout != 0L && 
            System.currentTimeMillis() > this.doubleClickTimeout)
            this.doubleClickTimeout = 0L; 
        this.height = height;
        Iterator<ControlledInputReciever> allStarts = this.allListeners.iterator();
        while (allStarts.hasNext()) {
            ControlledInputReciever listener = allStarts.next();
            listener.inputStarted();
        } 
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                int k = resolveEventKey(Keyboard.getEventKey(), Keyboard.getEventCharacter());
                this.keys[k] = Keyboard.getEventCharacter();
                this.pressed[k] = true;
                this.nextRepeat[k] = System.currentTimeMillis() + this.keyRepeatInitial;
                this.consumed = false;
                for (int m = 0; m < this.keyListeners.size(); m++) {
                    KeyListener listener = this.keyListeners.get(m);
                    if (listener.isAcceptingInput()) {
                        listener.keyPressed(k, Keyboard.getEventCharacter());
                        if (this.consumed)
                            break; 
                    } 
                } 
                continue;
            } 
            int eventKey = resolveEventKey(Keyboard.getEventKey(), Keyboard.getEventCharacter());
            this.nextRepeat[eventKey] = 0L;
            this.consumed = false;
            for (int j = 0; j < this.keyListeners.size(); j++) {
                KeyListener listener = this.keyListeners.get(j);
                if (listener.isAcceptingInput()) {
                    listener.keyReleased(eventKey, this.keys[eventKey]);
                    if (this.consumed)
                        break; 
                } 
            } 
        } 
        while (Mouse.next()) {
            if (Mouse.getEventButton() >= 0) {
                if (Mouse.getEventButtonState()) {
                    this.consumed = false;
                    this.mousePressed[Mouse.getEventButton()] = true;
                    this.pressedX = (int)(this.xoffset + Mouse.getEventX() * this.scaleX);
                    this.pressedY = (int)(this.yoffset + (height - Mouse.getEventY()) * this.scaleY);
                    for (int k = 0; k < this.mouseListeners.size(); k++) {
                        MouseListener listener = this.mouseListeners.get(k);
                        if (listener.isAcceptingInput()) {
                            listener.mousePressed(Mouse.getEventButton(), this.pressedX, this.pressedY);
                            if (this.consumed)
                                break; 
                        } 
                    } 
                    continue;
                } 
                this.consumed = false;
                this.mousePressed[Mouse.getEventButton()] = false;
                int releasedX = (int)(this.xoffset + Mouse.getEventX() * this.scaleX);
                int releasedY = (int)(this.yoffset + (height - Mouse.getEventY()) * this.scaleY);
                if (this.pressedX != -1 && this.pressedY != -1 && Math.abs(this.pressedX - releasedX) < this.mouseClickTolerance && Math.abs(this.pressedY - releasedY) < this.mouseClickTolerance) {
                    considerDoubleClick(Mouse.getEventButton(), releasedX, releasedY);
                    this.pressedX = this.pressedY = -1;
                } 
                for (int j = 0; j < this.mouseListeners.size(); j++) {
                    MouseListener listener = this.mouseListeners.get(j);
                    if (listener.isAcceptingInput()) {
                        listener.mouseReleased(Mouse.getEventButton(), releasedX, releasedY);
                        if (this.consumed)
                            break; 
                    } 
                } 
                continue;
            } 
            if (Mouse.isGrabbed() && this.displayActive && (
                Mouse.getEventDX() != 0 || Mouse.getEventDY() != 0)) {
                this.consumed = false;
                for (int j = 0; j < this.mouseListeners.size(); j++) {
                    MouseListener listener = this.mouseListeners.get(j);
                    if (listener.isAcceptingInput()) {
                        if (anyMouseDown()) {
                            listener.mouseDragged(0, 0, Mouse.getEventDX(), -Mouse.getEventDY());
                        } else {
                            listener.mouseMoved(0, 0, Mouse.getEventDX(), -Mouse.getEventDY());
                        } 
                        if (this.consumed)
                            break; 
                    } 
                } 
            } 
            int dwheel = Mouse.getEventDWheel();
            this.wheel += dwheel;
            if (dwheel != 0) {
                this.consumed = false;
                for (int j = 0; j < this.mouseListeners.size(); j++) {
                    MouseListener listener = this.mouseListeners.get(j);
                    if (listener.isAcceptingInput()) {
                        listener.mouseWheelMoved(dwheel);
                        if (this.consumed)
                            break; 
                    } 
                } 
            } 
        } 
        if (!this.displayActive || Mouse.isGrabbed()) {
            this.lastMouseX = getMouseX();
            this.lastMouseY = getMouseY();
        } else if (this.lastMouseX != getMouseX() || this.lastMouseY != getMouseY()) {
            this.consumed = false;
            for (int j = 0; j < this.mouseListeners.size(); j++) {
                MouseListener listener = this.mouseListeners.get(j);
                if (listener.isAcceptingInput()) {
                    if (anyMouseDown()) {
                        listener.mouseDragged(this.lastMouseX, this.lastMouseY, getMouseX(), getMouseY());
                    } else {
                        listener.mouseMoved(this.lastMouseX, this.lastMouseY, getMouseX(), getMouseY());
                    } 
                    if (this.consumed)
                        break; 
                } 
            } 
            this.lastMouseX = getMouseX();
            this.lastMouseY = getMouseY();
        } 
        if (controllersInited)
            for (int j = 0; j < getControllerCount(); j++) {
                int count = ((Controller)controllers.get(j)).getButtonCount() + 3;
                count = Math.min(count, 24);
                for (int c = 0; c <= count; c++) {
                    if (this.controls[j][c] && !isControlDwn(c, j)) {
                        this.controls[j][c] = false;
                        fireControlRelease(c, j);
                    } else if (!this.controls[j][c] && isControlDwn(c, j)) {
                        this.controllerPressed[j][c] = true;
                        this.controls[j][c] = true;
                        fireControlPress(c, j);
                    } 
                } 
            }  
        if (this.keyRepeat)
            for (int j = 0; j < 1024; j++) {
                if (this.pressed[j] && this.nextRepeat[j] != 0L && 
                    System.currentTimeMillis() > this.nextRepeat[j]) {
                    this.nextRepeat[j] = System.currentTimeMillis() + this.keyRepeatInterval;
                    this.consumed = false;
                    for (int k = 0; k < this.keyListeners.size(); k++) {
                        KeyListener listener = this.keyListeners.get(k);
                        if (listener.isAcceptingInput()) {
                            listener.keyPressed(j, this.keys[j]);
                            if (this.consumed)
                                break; 
                        } 
                    } 
                } 
            }  
        Iterator<ControlledInputReciever> all = this.allListeners.iterator();
        while (all.hasNext()) {
            ControlledInputReciever listener = all.next();
            listener.inputEnded();
        } 
        if (Display.isCreated())
            this.displayActive = Display.isActive(); 
    }
    
    public void enableKeyRepeat(int initial, int interval) {
        Keyboard.enableRepeatEvents(true);
    }
    
    public void enableKeyRepeat() {
        Keyboard.enableRepeatEvents(true);
    }
    
    public void disableKeyRepeat() {
        Keyboard.enableRepeatEvents(false);
    }
    
    public boolean isKeyRepeatEnabled() {
        return Keyboard.areRepeatEventsEnabled();
    }
    
    private void fireControlPress(int index, int controllerIndex) {
        this.consumed = false;
        for (int i = 0; i < this.controllerListeners.size(); i++) {
            ControllerListener listener = this.controllerListeners.get(i);
            if (listener.isAcceptingInput()) {
                switch (index) {
                    case 0:
                        listener.controllerLeftPressed(controllerIndex);
                        break;
                    case 1:
                        listener.controllerRightPressed(controllerIndex);
                        break;
                    case 2:
                        listener.controllerUpPressed(controllerIndex);
                        break;
                    case 3:
                        listener.controllerDownPressed(controllerIndex);
                        break;
                    default:
                        listener.controllerButtonPressed(controllerIndex, index - 4 + 1);
                        break;
                } 
                if (this.consumed)
                    break; 
            } 
        } 
    }
    
    private void fireControlRelease(int index, int controllerIndex) {
        this.consumed = false;
        for (int i = 0; i < this.controllerListeners.size(); i++) {
            ControllerListener listener = this.controllerListeners.get(i);
            if (listener.isAcceptingInput()) {
                switch (index) {
                    case 0:
                        listener.controllerLeftReleased(controllerIndex);
                        break;
                    case 1:
                        listener.controllerRightReleased(controllerIndex);
                        break;
                    case 2:
                        listener.controllerUpReleased(controllerIndex);
                        break;
                    case 3:
                        listener.controllerDownReleased(controllerIndex);
                        break;
                    default:
                        listener.controllerButtonReleased(controllerIndex, index - 4 + 1);
                        break;
                } 
                if (this.consumed)
                    break; 
            } 
        } 
    }
    
    private boolean isControlDwn(int index, int controllerIndex) {
        switch (index) {
            case 0:
                return isControllerLeft(controllerIndex);
            case 1:
                return isControllerRight(controllerIndex);
            case 2:
                return isControllerUp(controllerIndex);
            case 3:
                return isControllerDown(controllerIndex);
        } 
        if (index >= 4)
            return isButtonPressed(index - 4, controllerIndex); 
        throw new RuntimeException("Unknown control index");
    }
    
    public void pause() {
        this.paused = true;
        clearKeyPressedRecord();
        clearMousePressedRecord();
        clearControlPressedRecord();
    }
    
    public void resume() {
        this.paused = false;
    }
    
    private void fireMouseClicked(int button, int x, int y, int clickCount) {
        this.consumed = false;
        for (int i = 0; i < this.mouseListeners.size(); i++) {
            MouseListener listener = this.mouseListeners.get(i);
            if (listener.isAcceptingInput()) {
                listener.mouseClicked(button, x, y, clickCount);
                if (this.consumed)
                    break; 
            } 
        } 
    }
}

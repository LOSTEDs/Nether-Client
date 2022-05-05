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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;
import org.newdawn.slick.util.InputAdapter;

public class InputProvider {
    private HashMap commands;
    
    private ArrayList listeners = new ArrayList();
    
    private Input input;
    
    private HashMap commandState = new HashMap<Object, Object>();
    
    private boolean active = true;
    
    public InputProvider(Input input) {
        this.input = input;
        input.addListener((InputListener)new InputListenerImpl());
        this.commands = new HashMap<Object, Object>();
    }
    
    public List getUniqueCommands() {
        List<Command> uniqueCommands = new ArrayList();
        for (Iterator<Command> it = this.commands.values().iterator(); it.hasNext(); ) {
            Command command = it.next();
            if (!uniqueCommands.contains(command))
                uniqueCommands.add(command); 
        } 
        return uniqueCommands;
    }
    
    public List getControlsFor(Command command) {
        List<Control> controlsForCommand = new ArrayList();
        for (Iterator<Map.Entry> it = this.commands.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry entry = it.next();
            Control key = (Control)entry.getKey();
            Command value = (Command)entry.getValue();
            if (value == command)
                controlsForCommand.add(key); 
        } 
        return controlsForCommand;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public void addListener(InputProviderListener listener) {
        this.listeners.add(listener);
    }
    
    public void removeListener(InputProviderListener listener) {
        this.listeners.remove(listener);
    }
    
    public void bindCommand(Control control, Command command) {
        this.commands.put(control, command);
        if (this.commandState.get(command) == null)
            this.commandState.put(command, new CommandState()); 
    }
    
    public void clearCommand(Command command) {
        List<Control> controls = getControlsFor(command);
        for (int i = 0; i < controls.size(); i++)
            unbindCommand(controls.get(i)); 
    }
    
    public void unbindCommand(Control control) {
        Command command = (Command)this.commands.remove(control);
        if (command != null && 
            !this.commands.keySet().contains(command))
            this.commandState.remove(command); 
    }
    
    private CommandState getState(Command command) {
        return (CommandState)this.commandState.get(command);
    }
    
    public boolean isCommandControlDown(Command command) {
        return getState(command).isDown();
    }
    
    public boolean isCommandControlPressed(Command command) {
        return getState(command).isPressed();
    }
    
    protected void firePressed(Command command) {
        (getState(command)).down = true;
        (getState(command)).pressed = true;
        if (!isActive())
            return; 
        for (int i = 0; i < this.listeners.size(); i++)
            ((InputProviderListener)this.listeners.get(i)).controlPressed(command); 
    }
    
    protected void fireReleased(Command command) {
        (getState(command)).down = false;
        if (!isActive())
            return; 
        for (int i = 0; i < this.listeners.size(); i++)
            ((InputProviderListener)this.listeners.get(i)).controlReleased(command); 
    }
    
    private class CommandState {
        private boolean down;
        
        private boolean pressed;
        
        private CommandState() {}
        
        public boolean isPressed() {
            if (this.pressed) {
                this.pressed = false;
                return true;
            } 
            return false;
        }
        
        public boolean isDown() {
            return this.down;
        }
    }
    
    private class InputListenerImpl extends InputAdapter {
        private InputListenerImpl() {}
        
        public boolean isAcceptingInput() {
            return true;
        }
        
        public void keyPressed(int key, char c) {
            Command command = (Command)InputProvider.this.commands.get(new KeyControl(key));
            if (command != null)
                InputProvider.this.firePressed(command); 
        }
        
        public void keyReleased(int key, char c) {
            Command command = (Command)InputProvider.this.commands.get(new KeyControl(key));
            if (command != null)
                InputProvider.this.fireReleased(command); 
        }
        
        public void mousePressed(int button, int x, int y) {
            Command command = (Command)InputProvider.this.commands.get(new MouseButtonControl(button));
            if (command != null)
                InputProvider.this.firePressed(command); 
        }
        
        public void mouseReleased(int button, int x, int y) {
            Command command = (Command)InputProvider.this.commands.get(new MouseButtonControl(button));
            if (command != null)
                InputProvider.this.fireReleased(command); 
        }
        
        public void controllerLeftPressed(int controller) {
            Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.LEFT));
            if (command != null)
                InputProvider.this.firePressed(command); 
        }
        
        public void controllerLeftReleased(int controller) {
            Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.LEFT));
            if (command != null)
                InputProvider.this.fireReleased(command); 
        }
        
        public void controllerRightPressed(int controller) {
            Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.RIGHT));
            if (command != null)
                InputProvider.this.firePressed(command); 
        }
        
        public void controllerRightReleased(int controller) {
            Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.RIGHT));
            if (command != null)
                InputProvider.this.fireReleased(command); 
        }
        
        public void controllerUpPressed(int controller) {
            Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.UP));
            if (command != null)
                InputProvider.this.firePressed(command); 
        }
        
        public void controllerUpReleased(int controller) {
            Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.UP));
            if (command != null)
                InputProvider.this.fireReleased(command); 
        }
        
        public void controllerDownPressed(int controller) {
            Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.DOWN));
            if (command != null)
                InputProvider.this.firePressed(command); 
        }
        
        public void controllerDownReleased(int controller) {
            Command command = (Command)InputProvider.this.commands.get(new ControllerDirectionControl(controller, ControllerDirectionControl.DOWN));
            if (command != null)
                InputProvider.this.fireReleased(command); 
        }
        
        public void controllerButtonPressed(int controller, int button) {
            Command command = (Command)InputProvider.this.commands.get(new ControllerButtonControl(controller, button));
            if (command != null)
                InputProvider.this.firePressed(command); 
        }
        
        public void controllerButtonReleased(int controller, int button) {
            Command command = (Command)InputProvider.this.commands.get(new ControllerButtonControl(controller, button));
            if (command != null)
                InputProvider.this.fireReleased(command); 
        }
    }
}

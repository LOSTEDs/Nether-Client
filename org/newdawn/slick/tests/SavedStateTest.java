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

package org.newdawn.slick.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SavedState;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;

public class SavedStateTest extends BasicGame implements ComponentListener {
    private TextField name;
    
    private TextField age;
    
    private String nameValue = "none";
    
    private int ageValue = 0;
    
    private SavedState state;
    
    private String message = "Enter a name and age to store";
    
    private static AppGameContainer container;
    
    public SavedStateTest() {
        super("Saved State Test");
    }
    
    public void init(GameContainer container) throws SlickException {
        this.state = new SavedState("testdata");
        this.nameValue = this.state.getString("name", "DefaultName");
        this.ageValue = (int)this.state.getNumber("age", 64.0D);
        this.name = new TextField((GUIContext)container, container.getDefaultFont(), 100, 100, 300, 20, this);
        this.age = new TextField((GUIContext)container, container.getDefaultFont(), 100, 150, 201, 20, this);
    }
    
    public void render(GameContainer container, Graphics g) {
        this.name.render((GUIContext)container, g);
        this.age.render((GUIContext)container, g);
        container.getDefaultFont().drawString(100.0F, 300.0F, "Stored Name: " + this.nameValue);
        container.getDefaultFont().drawString(100.0F, 350.0F, "Stored Age: " + this.ageValue);
        container.getDefaultFont().drawString(200.0F, 500.0F, this.message);
    }
    
    public void update(GameContainer container, int delta) throws SlickException {}
    
    public void keyPressed(int key, char c) {
        if (key == 1)
            System.exit(0); 
    }
    
    public static void main(String[] argv) {
        try {
            container = new AppGameContainer((Game)new SavedStateTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        } 
    }
    
    public void componentActivated(AbstractComponent source) {
        if (source == this.name) {
            this.nameValue = this.name.getText();
            this.state.setString("name", this.nameValue);
        } 
        if (source == this.age)
            try {
                this.ageValue = Integer.parseInt(this.age.getText());
                this.state.setNumber("age", this.ageValue);
            } catch (NumberFormatException e) {} 
        try {
            this.state.save();
        } catch (Exception e) {
            this.message = System.currentTimeMillis() + " : Failed to save state";
        } 
    }
}

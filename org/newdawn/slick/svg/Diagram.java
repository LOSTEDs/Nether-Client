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

package org.newdawn.slick.svg;

import java.util.ArrayList;
import java.util.HashMap;

public class Diagram {
    private ArrayList figures = new ArrayList();
    
    private HashMap patterns = new HashMap<Object, Object>();
    
    private HashMap gradients = new HashMap<Object, Object>();
    
    private HashMap figureMap = new HashMap<Object, Object>();
    
    private float width;
    
    private float height;
    
    public Diagram(float width, float height) {
        this.width = width;
        this.height = height;
    }
    
    public float getWidth() {
        return this.width;
    }
    
    public float getHeight() {
        return this.height;
    }
    
    public void addPatternDef(String name, String href) {
        this.patterns.put(name, href);
    }
    
    public void addGradient(String name, Gradient gradient) {
        this.gradients.put(name, gradient);
    }
    
    public String getPatternDef(String name) {
        return (String)this.patterns.get(name);
    }
    
    public Gradient getGradient(String name) {
        return (Gradient)this.gradients.get(name);
    }
    
    public String[] getPatternDefNames() {
        return (String[])this.patterns.keySet().toArray((Object[])new String[0]);
    }
    
    public Figure getFigureByID(String id) {
        return (Figure)this.figureMap.get(id);
    }
    
    public void addFigure(Figure figure) {
        this.figures.add(figure);
        this.figureMap.put(figure.getData().getAttribute("id"), figure);
        String fillRef = figure.getData().getAsReference("fill");
        Gradient gradient = getGradient(fillRef);
        if (gradient != null && 
            gradient.isRadial())
            for (int i = 0; i < InkscapeLoader.RADIAL_TRIANGULATION_LEVEL; i++)
                figure.getShape().increaseTriangulation();  
    }
    
    public int getFigureCount() {
        return this.figures.size();
    }
    
    public Figure getFigure(int index) {
        return this.figures.get(index);
    }
    
    public void removeFigure(Figure figure) {
        this.figures.remove(figure);
        this.figureMap.remove(figure.getData().getAttribute("id"));
    }
}

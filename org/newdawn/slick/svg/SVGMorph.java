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
import org.newdawn.slick.geom.MorphShape;
import org.newdawn.slick.geom.Shape;

public class SVGMorph extends Diagram {
    private ArrayList figures = new ArrayList();
    
    public SVGMorph(Diagram diagram) {
        super(diagram.getWidth(), diagram.getHeight());
        for (int i = 0; i < diagram.getFigureCount(); i++) {
            Figure figure = diagram.getFigure(i);
            Figure copy = new Figure(figure.getType(), (Shape)new MorphShape(figure.getShape()), figure.getData(), figure.getTransform());
            this.figures.add(copy);
        } 
    }
    
    public void addStep(Diagram diagram) {
        if (diagram.getFigureCount() != this.figures.size())
            throw new RuntimeException("Mismatched diagrams, missing ids"); 
        for (int i = 0; i < diagram.getFigureCount(); i++) {
            Figure figure = diagram.getFigure(i);
            String id = figure.getData().getMetaData();
            for (int j = 0; j < this.figures.size(); j++) {
                Figure existing = this.figures.get(j);
                if (existing.getData().getMetaData().equals(id)) {
                    MorphShape morph = (MorphShape)existing.getShape();
                    morph.addShape(figure.getShape());
                    break;
                } 
            } 
        } 
    }
    
    public void setExternalDiagram(Diagram diagram) {
        for (int i = 0; i < this.figures.size(); i++) {
            Figure figure = this.figures.get(i);
            for (int j = 0; j < diagram.getFigureCount(); j++) {
                Figure newBase = diagram.getFigure(j);
                if (newBase.getData().getMetaData().equals(figure.getData().getMetaData())) {
                    MorphShape shape = (MorphShape)figure.getShape();
                    shape.setExternalFrame(newBase.getShape());
                    break;
                } 
            } 
        } 
    }
    
    public void updateMorphTime(float delta) {
        for (int i = 0; i < this.figures.size(); i++) {
            Figure figure = this.figures.get(i);
            MorphShape shape = (MorphShape)figure.getShape();
            shape.updateMorphTime(delta);
        } 
    }
    
    public void setMorphTime(float time) {
        for (int i = 0; i < this.figures.size(); i++) {
            Figure figure = this.figures.get(i);
            MorphShape shape = (MorphShape)figure.getShape();
            shape.setMorphTime(time);
        } 
    }
    
    public int getFigureCount() {
        return this.figures.size();
    }
    
    public Figure getFigure(int index) {
        return this.figures.get(index);
    }
}

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

package org.newdawn.slick.svg.inkscape;

import org.newdawn.slick.svg.NonGeometricData;
import org.w3c.dom.Element;

public class InkscapeNonGeometricData extends NonGeometricData {
    private Element element;
    
    public InkscapeNonGeometricData(String metaData, Element element) {
        super(metaData);
        this.element = element;
    }
    
    public String getAttribute(String attribute) {
        String result = super.getAttribute(attribute);
        if (result == null)
            result = this.element.getAttribute(attribute); 
        return result;
    }
    
    public Element getElement() {
        return this.element;
    }
}

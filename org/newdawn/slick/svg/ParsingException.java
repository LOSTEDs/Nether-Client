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

import org.newdawn.slick.SlickException;
import org.w3c.dom.Element;

public class ParsingException extends SlickException {
    public ParsingException(String nodeID, String message, Throwable cause) {
        super("(" + nodeID + ") " + message, cause);
    }
    
    public ParsingException(Element element, String message, Throwable cause) {
        super("(" + element.getAttribute("id") + ") " + message, cause);
    }
    
    public ParsingException(String nodeID, String message) {
        super("(" + nodeID + ") " + message);
    }
    
    public ParsingException(Element element, String message) {
        super("(" + element.getAttribute("id") + ") " + message);
    }
}

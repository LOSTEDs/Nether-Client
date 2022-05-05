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

package org.newdawn.slick.util.xml;

import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.ResourceLoader;
import org.w3c.dom.Document;

public class XMLParser {
    private static DocumentBuilderFactory factory;
    
    public XMLElement parse(String ref) throws SlickException {
        return parse(ref, ResourceLoader.getResourceAsStream(ref));
    }
    
    public XMLElement parse(String name, InputStream in) throws SlickXMLException {
        try {
            if (factory == null)
                factory = DocumentBuilderFactory.newInstance(); 
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(in);
            return new XMLElement(doc.getDocumentElement());
        } catch (Exception e) {
            throw new SlickXMLException("Failed to parse document: " + name, e);
        } 
    }
}

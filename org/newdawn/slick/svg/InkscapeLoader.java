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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.svg.inkscape.DefsProcessor;
import org.newdawn.slick.svg.inkscape.ElementProcessor;
import org.newdawn.slick.svg.inkscape.EllipseProcessor;
import org.newdawn.slick.svg.inkscape.GroupProcessor;
import org.newdawn.slick.svg.inkscape.LineProcessor;
import org.newdawn.slick.svg.inkscape.PathProcessor;
import org.newdawn.slick.svg.inkscape.PolygonProcessor;
import org.newdawn.slick.svg.inkscape.RectProcessor;
import org.newdawn.slick.svg.inkscape.UseProcessor;
import org.newdawn.slick.util.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class InkscapeLoader implements Loader {
    public static int RADIAL_TRIANGULATION_LEVEL = 1;
    
    private static ArrayList processors = new ArrayList();
    
    private Diagram diagram;
    
    static {
        addElementProcessor((ElementProcessor)new RectProcessor());
        addElementProcessor((ElementProcessor)new EllipseProcessor());
        addElementProcessor((ElementProcessor)new PolygonProcessor());
        addElementProcessor((ElementProcessor)new PathProcessor());
        addElementProcessor((ElementProcessor)new LineProcessor());
        addElementProcessor((ElementProcessor)new GroupProcessor());
        addElementProcessor((ElementProcessor)new DefsProcessor());
        addElementProcessor((ElementProcessor)new UseProcessor());
    }
    
    public static void addElementProcessor(ElementProcessor proc) {
        processors.add(proc);
    }
    
    public static Diagram load(String ref, boolean offset) throws SlickException {
        return load(ResourceLoader.getResourceAsStream(ref), offset);
    }
    
    public static Diagram load(String ref) throws SlickException {
        return load(ResourceLoader.getResourceAsStream(ref), false);
    }
    
    public static Diagram load(InputStream in, boolean offset) throws SlickException {
        return (new InkscapeLoader()).loadDiagram(in, offset);
    }
    
    private Diagram loadDiagram(InputStream in) throws SlickException {
        return loadDiagram(in, false);
    }
    
    private Diagram loadDiagram(InputStream in, boolean offset) throws SlickException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setEntityResolver(new EntityResolver() {
                        public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                            return new InputSource(new ByteArrayInputStream(new byte[0]));
                        }
                    });
            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();
            String widthString = root.getAttribute("width");
            while (Character.isLetter(widthString.charAt(widthString.length() - 1)))
                widthString = widthString.substring(0, widthString.length() - 1); 
            String heightString = root.getAttribute("height");
            while (Character.isLetter(heightString.charAt(heightString.length() - 1)))
                heightString = heightString.substring(0, heightString.length() - 1); 
            float docWidth = Float.parseFloat(widthString);
            float docHeight = Float.parseFloat(heightString);
            this.diagram = new Diagram(docWidth, docHeight);
            if (!offset)
                docHeight = 0.0F; 
            loadChildren(root, Transform.createTranslateTransform(0.0F, -docHeight));
            return this.diagram;
        } catch (Exception e) {
            throw new SlickException("Failed to load inkscape document", e);
        } 
    }
    
    public void loadChildren(Element element, Transform t) throws ParsingException {
        NodeList list = element.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i) instanceof Element)
                loadElement((Element)list.item(i), t); 
        } 
    }
    
    private void loadElement(Element element, Transform t) throws ParsingException {
        for (int i = 0; i < processors.size(); i++) {
            ElementProcessor processor = processors.get(i);
            if (processor.handles(element))
                processor.process(this, element, this.diagram, t); 
        } 
    }
}

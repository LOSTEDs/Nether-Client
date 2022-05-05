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

package org.newdawn.slick.tiled;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;
import org.newdawn.slick.util.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class TiledMap {
    private static boolean headless;
    
    protected int width;
    
    protected int height;
    
    protected int tileWidth;
    
    protected int tileHeight;
    
    protected String tilesLocation;
    
    protected Properties props;
    
    private static void setHeadless(boolean h) {
        headless = h;
    }
    
    protected ArrayList tileSets = new ArrayList();
    
    protected ArrayList layers = new ArrayList();
    
    protected ArrayList objectGroups = new ArrayList();
    
    protected static final int ORTHOGONAL = 1;
    
    protected static final int ISOMETRIC = 2;
    
    protected int orientation;
    
    private boolean loadTileSets = true;
    
    public TiledMap(String ref) throws SlickException {
        this(ref, true);
    }
    
    public TiledMap(String ref, boolean loadTileSets) throws SlickException {
        this.loadTileSets = loadTileSets;
        ref = ref.replace('\\', '/');
        load(ResourceLoader.getResourceAsStream(ref), ref.substring(0, ref.lastIndexOf("/")));
    }
    
    public TiledMap(String ref, String tileSetsLocation) throws SlickException {
        load(ResourceLoader.getResourceAsStream(ref), tileSetsLocation);
    }
    
    public TiledMap(InputStream in) throws SlickException {
        load(in, "");
    }
    
    public TiledMap(InputStream in, String tileSetsLocation) throws SlickException {
        load(in, tileSetsLocation);
    }
    
    public String getTilesLocation() {
        return this.tilesLocation;
    }
    
    public int getLayerIndex(String name) {
        int idx = 0;
        for (int i = 0; i < this.layers.size(); i++) {
            Layer layer = this.layers.get(i);
            if (layer.name.equals(name))
                return i; 
        } 
        return -1;
    }
    
    public Image getTileImage(int x, int y, int layerIndex) {
        Layer layer = this.layers.get(layerIndex);
        int tileSetIndex = layer.data[x][y][0];
        if (tileSetIndex >= 0 && tileSetIndex < this.tileSets.size()) {
            TileSet tileSet = this.tileSets.get(tileSetIndex);
            int sheetX = tileSet.getTileX(layer.data[x][y][1]);
            int sheetY = tileSet.getTileY(layer.data[x][y][1]);
            return tileSet.tiles.getSprite(sheetX, sheetY);
        } 
        return null;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getTileHeight() {
        return this.tileHeight;
    }
    
    public int getTileWidth() {
        return this.tileWidth;
    }
    
    public int getTileId(int x, int y, int layerIndex) {
        Layer layer = this.layers.get(layerIndex);
        return layer.getTileID(x, y);
    }
    
    public void setTileId(int x, int y, int layerIndex, int tileid) {
        Layer layer = this.layers.get(layerIndex);
        layer.setTileID(x, y, tileid);
    }
    
    public String getMapProperty(String propertyName, String def) {
        if (this.props == null)
            return def; 
        return this.props.getProperty(propertyName, def);
    }
    
    public String getLayerProperty(int layerIndex, String propertyName, String def) {
        Layer layer = this.layers.get(layerIndex);
        if (layer == null || layer.props == null)
            return def; 
        return layer.props.getProperty(propertyName, def);
    }
    
    public String getTileProperty(int tileID, String propertyName, String def) {
        if (tileID == 0)
            return def; 
        TileSet set = findTileSet(tileID);
        Properties props = set.getProperties(tileID);
        if (props == null)
            return def; 
        return props.getProperty(propertyName, def);
    }
    
    public void render(int x, int y) {
        render(x, y, 0, 0, this.width, this.height, false);
    }
    
    public void render(int x, int y, int layer) {
        render(x, y, 0, 0, getWidth(), getHeight(), layer, false);
    }
    
    public void render(int x, int y, int sx, int sy, int width, int height) {
        render(x, y, sx, sy, width, height, false);
    }
    
    public void render(int x, int y, int sx, int sy, int width, int height, int l, boolean lineByLine) {
        int ty;
        Layer layer = this.layers.get(l);
        switch (this.orientation) {
            case 1:
                for (ty = 0; ty < height; ty++)
                    layer.render(x, y, sx, sy, width, ty, lineByLine, this.tileWidth, this.tileHeight); 
                break;
            case 2:
                renderIsometricMap(x, y, sx, sy, width, height, layer, lineByLine);
                break;
        } 
    }
    
    public void render(int x, int y, int sx, int sy, int width, int height, boolean lineByLine) {
        int ty;
        switch (this.orientation) {
            case 1:
                for (ty = 0; ty < height; ty++) {
                    for (int i = 0; i < this.layers.size(); i++) {
                        Layer layer = this.layers.get(i);
                        layer.render(x, y, sx, sy, width, ty, lineByLine, this.tileWidth, this.tileHeight);
                    } 
                } 
                break;
            case 2:
                renderIsometricMap(x, y, sx, sy, width, height, null, lineByLine);
                break;
        } 
    }
    
    protected void renderIsometricMap(int x, int y, int sx, int sy, int width, int height, Layer layer, boolean lineByLine) {
        ArrayList<Layer> drawLayers = this.layers;
        if (layer != null) {
            drawLayers = new ArrayList();
            drawLayers.add(layer);
        } 
        int maxCount = width * height;
        int allCount = 0;
        boolean allProcessed = false;
        int initialLineX = x;
        int initialLineY = y;
        int startLineTileX = 0;
        int startLineTileY = 0;
        while (!allProcessed) {
            int currentTileX = startLineTileX;
            int currentTileY = startLineTileY;
            int currentLineX = initialLineX;
            int min = 0;
            if (height > width) {
                min = (startLineTileY < width - 1) ? startLineTileY : ((width - currentTileX < height) ? (width - currentTileX - 1) : (width - 1));
            } else {
                min = (startLineTileY < height - 1) ? startLineTileY : ((width - currentTileX < height) ? (width - currentTileX - 1) : (height - 1));
            } 
            for (int burner = 0; burner <= min; currentTileX++, currentTileY--, burner++) {
                for (int layerIdx = 0; layerIdx < drawLayers.size(); layerIdx++) {
                    Layer currentLayer = drawLayers.get(layerIdx);
                    currentLayer.render(currentLineX, initialLineY, currentTileX, currentTileY, 1, 0, lineByLine, this.tileWidth, this.tileHeight);
                } 
                currentLineX += this.tileWidth;
                allCount++;
            } 
            if (startLineTileY < height - 1) {
                startLineTileY++;
                initialLineX -= this.tileWidth / 2;
                initialLineY += this.tileHeight / 2;
            } else {
                startLineTileX++;
                initialLineX += this.tileWidth / 2;
                initialLineY += this.tileHeight / 2;
            } 
            if (allCount >= maxCount)
                allProcessed = true; 
        } 
    }
    
    public int getLayerCount() {
        return this.layers.size();
    }
    
    private int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        } 
    }
    
    private void load(InputStream in, String tileSetsLocation) throws SlickException {
        this.tilesLocation = tileSetsLocation;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setEntityResolver(new EntityResolver() {
                        public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                            return new InputSource(new ByteArrayInputStream(new byte[0]));
                        }
                    });
            Document doc = builder.parse(in);
            Element docElement = doc.getDocumentElement();
            if (docElement.getAttribute("orientation").equals("orthogonal")) {
                this.orientation = 1;
            } else {
                this.orientation = 2;
            } 
            this.width = parseInt(docElement.getAttribute("width"));
            this.height = parseInt(docElement.getAttribute("height"));
            this.tileWidth = parseInt(docElement.getAttribute("tilewidth"));
            this.tileHeight = parseInt(docElement.getAttribute("tileheight"));
            Element propsElement = (Element)docElement.getElementsByTagName("properties").item(0);
            if (propsElement != null) {
                NodeList properties = propsElement.getElementsByTagName("property");
                if (properties != null) {
                    this.props = new Properties();
                    for (int p = 0; p < properties.getLength(); p++) {
                        Element propElement = (Element)properties.item(p);
                        String name = propElement.getAttribute("name");
                        String value = propElement.getAttribute("value");
                        this.props.setProperty(name, value);
                    } 
                } 
            } 
            if (this.loadTileSets) {
                TileSet tileSet = null;
                TileSet lastSet = null;
                NodeList setNodes = docElement.getElementsByTagName("tileset");
                for (int k = 0; k < setNodes.getLength(); k++) {
                    Element current = (Element)setNodes.item(k);
                    tileSet = new TileSet(this, current, !headless);
                    tileSet.index = k;
                    if (lastSet != null)
                        lastSet.setLimit(tileSet.firstGID - 1); 
                    lastSet = tileSet;
                    this.tileSets.add(tileSet);
                } 
            } 
            NodeList layerNodes = docElement.getElementsByTagName("layer");
            for (int i = 0; i < layerNodes.getLength(); i++) {
                Element current = (Element)layerNodes.item(i);
                Layer layer = new Layer(this, current);
                layer.index = i;
                this.layers.add(layer);
            } 
            NodeList objectGroupNodes = docElement.getElementsByTagName("objectgroup");
            for (int j = 0; j < objectGroupNodes.getLength(); j++) {
                Element current = (Element)objectGroupNodes.item(j);
                ObjectGroup objectGroup = new ObjectGroup(current);
                objectGroup.index = j;
                this.objectGroups.add(objectGroup);
            } 
        } catch (Exception e) {
            Log.error(e);
            throw new SlickException("Failed to parse tilemap", e);
        } 
    }
    
    public int getTileSetCount() {
        return this.tileSets.size();
    }
    
    public TileSet getTileSet(int index) {
        return this.tileSets.get(index);
    }
    
    public TileSet getTileSetByGID(int gid) {
        for (int i = 0; i < this.tileSets.size(); i++) {
            TileSet set = this.tileSets.get(i);
            if (set.contains(gid))
                return set; 
        } 
        return null;
    }
    
    public TileSet findTileSet(int gid) {
        for (int i = 0; i < this.tileSets.size(); i++) {
            TileSet set = this.tileSets.get(i);
            if (set.contains(gid))
                return set; 
        } 
        return null;
    }
    
    protected void renderedLine(int visualY, int mapY, int layer) {}
    
    public int getObjectGroupCount() {
        return this.objectGroups.size();
    }
    
    public int getObjectCount(int groupID) {
        if (groupID >= 0 && groupID < this.objectGroups.size()) {
            ObjectGroup grp = this.objectGroups.get(groupID);
            return grp.objects.size();
        } 
        return -1;
    }
    
    public String getObjectName(int groupID, int objectID) {
        if (groupID >= 0 && groupID < this.objectGroups.size()) {
            ObjectGroup grp = this.objectGroups.get(groupID);
            if (objectID >= 0 && objectID < grp.objects.size()) {
                GroupObject object = grp.objects.get(objectID);
                return object.name;
            } 
        } 
        return null;
    }
    
    public String getObjectType(int groupID, int objectID) {
        if (groupID >= 0 && groupID < this.objectGroups.size()) {
            ObjectGroup grp = this.objectGroups.get(groupID);
            if (objectID >= 0 && objectID < grp.objects.size()) {
                GroupObject object = grp.objects.get(objectID);
                return object.type;
            } 
        } 
        return null;
    }
    
    public int getObjectX(int groupID, int objectID) {
        if (groupID >= 0 && groupID < this.objectGroups.size()) {
            ObjectGroup grp = this.objectGroups.get(groupID);
            if (objectID >= 0 && objectID < grp.objects.size()) {
                GroupObject object = grp.objects.get(objectID);
                return object.x;
            } 
        } 
        return -1;
    }
    
    public int getObjectY(int groupID, int objectID) {
        if (groupID >= 0 && groupID < this.objectGroups.size()) {
            ObjectGroup grp = this.objectGroups.get(groupID);
            if (objectID >= 0 && objectID < grp.objects.size()) {
                GroupObject object = grp.objects.get(objectID);
                return object.y;
            } 
        } 
        return -1;
    }
    
    public int getObjectWidth(int groupID, int objectID) {
        if (groupID >= 0 && groupID < this.objectGroups.size()) {
            ObjectGroup grp = this.objectGroups.get(groupID);
            if (objectID >= 0 && objectID < grp.objects.size()) {
                GroupObject object = grp.objects.get(objectID);
                return object.width;
            } 
        } 
        return -1;
    }
    
    public int getObjectHeight(int groupID, int objectID) {
        if (groupID >= 0 && groupID < this.objectGroups.size()) {
            ObjectGroup grp = this.objectGroups.get(groupID);
            if (objectID >= 0 && objectID < grp.objects.size()) {
                GroupObject object = grp.objects.get(objectID);
                return object.height;
            } 
        } 
        return -1;
    }
    
    public String getObjectImage(int groupID, int objectID) {
        if (groupID >= 0 && groupID < this.objectGroups.size()) {
            ObjectGroup grp = this.objectGroups.get(groupID);
            if (objectID >= 0 && objectID < grp.objects.size()) {
                GroupObject object = grp.objects.get(objectID);
                if (object == null)
                    return null; 
                return object.image;
            } 
        } 
        return null;
    }
    
    public String getObjectProperty(int groupID, int objectID, String propertyName, String def) {
        if (groupID >= 0 && groupID < this.objectGroups.size()) {
            ObjectGroup grp = this.objectGroups.get(groupID);
            if (objectID >= 0 && objectID < grp.objects.size()) {
                GroupObject object = grp.objects.get(objectID);
                if (object == null)
                    return def; 
                if (object.props == null)
                    return def; 
                return object.props.getProperty(propertyName, def);
            } 
        } 
        return def;
    }
    
    protected class ObjectGroup {
        public int index;
        
        public String name;
        
        public ArrayList objects;
        
        public int width;
        
        public int height;
        
        public Properties props;
        
        public ObjectGroup(Element element) throws SlickException {
            this.name = element.getAttribute("name");
            this.width = Integer.parseInt(element.getAttribute("width"));
            this.height = Integer.parseInt(element.getAttribute("height"));
            this.objects = new ArrayList();
            Element propsElement = (Element)element.getElementsByTagName("properties").item(0);
            if (propsElement != null) {
                NodeList properties = propsElement.getElementsByTagName("property");
                if (properties != null) {
                    this.props = new Properties();
                    for (int p = 0; p < properties.getLength(); p++) {
                        Element propElement = (Element)properties.item(p);
                        String name = propElement.getAttribute("name");
                        String value = propElement.getAttribute("value");
                        this.props.setProperty(name, value);
                    } 
                } 
            } 
            NodeList objectNodes = element.getElementsByTagName("object");
            for (int i = 0; i < objectNodes.getLength(); i++) {
                Element objElement = (Element)objectNodes.item(i);
                TiledMap.GroupObject object = new TiledMap.GroupObject(objElement);
                object.index = i;
                this.objects.add(object);
            } 
        }
    }
    
    protected class GroupObject {
        public int index;
        
        public String name;
        
        public String type;
        
        public int x;
        
        public int y;
        
        public int width;
        
        public int height;
        
        private String image;
        
        public Properties props;
        
        public GroupObject(Element element) throws SlickException {
            this.name = element.getAttribute("name");
            this.type = element.getAttribute("type");
            this.x = Integer.parseInt(element.getAttribute("x"));
            this.y = Integer.parseInt(element.getAttribute("y"));
            this.width = Integer.parseInt(element.getAttribute("width"));
            this.height = Integer.parseInt(element.getAttribute("height"));
            Element imageElement = (Element)element.getElementsByTagName("image").item(0);
            if (imageElement != null)
                this.image = imageElement.getAttribute("source"); 
            Element propsElement = (Element)element.getElementsByTagName("properties").item(0);
            if (propsElement != null) {
                NodeList properties = propsElement.getElementsByTagName("property");
                if (properties != null) {
                    this.props = new Properties();
                    for (int p = 0; p < properties.getLength(); p++) {
                        Element propElement = (Element)properties.item(p);
                        String name = propElement.getAttribute("name");
                        String value = propElement.getAttribute("value");
                        this.props.setProperty(name, value);
                    } 
                } 
            } 
        }
    }
}

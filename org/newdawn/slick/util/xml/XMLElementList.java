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

import java.util.ArrayList;
import java.util.Collection;

public class XMLElementList {
    private ArrayList list = new ArrayList();
    
    public void add(XMLElement element) {
        this.list.add(element);
    }
    
    public int size() {
        return this.list.size();
    }
    
    public XMLElement get(int i) {
        return this.list.get(i);
    }
    
    public boolean contains(XMLElement element) {
        return this.list.contains(element);
    }
    
    public void addAllTo(Collection collection) {
        collection.addAll(this.list);
    }
}

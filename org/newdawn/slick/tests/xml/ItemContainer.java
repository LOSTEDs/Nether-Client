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

package org.newdawn.slick.tests.xml;

import java.util.ArrayList;

public class ItemContainer extends Item {
    private ArrayList items = new ArrayList();
    
    private void add(Item item) {
        this.items.add(item);
    }
    
    private void setName(String name) {
        this.name = name;
    }
    
    private void setCondition(int condition) {
        this.condition = condition;
    }
    
    public void dump(String prefix) {
        System.out.println(prefix + "Item Container " + this.name + "," + this.condition);
        for (int i = 0; i < this.items.size(); i++)
            ((Item)this.items.get(i)).dump(prefix + "\t"); 
    }
}

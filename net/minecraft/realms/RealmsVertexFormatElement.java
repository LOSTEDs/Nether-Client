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

package net.minecraft.realms;

import net.minecraft.client.renderer.vertex.VertexFormatElement;

public class RealmsVertexFormatElement {
    private VertexFormatElement v;
    
    public RealmsVertexFormatElement(VertexFormatElement p_i46463_1_) {
        this.v = p_i46463_1_;
    }
    
    public VertexFormatElement getVertexFormatElement() {
        return this.v;
    }
    
    public boolean isPosition() {
        return this.v.isPositionElement();
    }
    
    public int getIndex() {
        return this.v.getIndex();
    }
    
    public int getByteSize() {
        return this.v.getSize();
    }
    
    public int getCount() {
        return this.v.getElementCount();
    }
    
    public int hashCode() {
        return this.v.hashCode();
    }
    
    public boolean equals(Object p_equals_1_) {
        return this.v.equals(p_equals_1_);
    }
    
    public String toString() {
        return this.v.toString();
    }
}

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

package org.newdawn.slick.font.effects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.Glyph;

public class FilterEffect implements Effect {
    private BufferedImageOp filter;
    
    public FilterEffect() {}
    
    public FilterEffect(BufferedImageOp filter) {
        this.filter = filter;
    }
    
    public void draw(BufferedImage image, Graphics2D g, UnicodeFont unicodeFont, Glyph glyph) {
        BufferedImage scratchImage = EffectUtil.getScratchImage();
        this.filter.filter(image, scratchImage);
        image.getGraphics().drawImage(scratchImage, 0, 0, null);
    }
    
    public BufferedImageOp getFilter() {
        return this.filter;
    }
    
    public void setFilter(BufferedImageOp filter) {
        this.filter = filter;
    }
}

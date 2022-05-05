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

package shadersmod.client;

import java.util.ArrayList;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.src.Lang;

class GuiSlotShaders extends GuiSlot {
    private ArrayList shaderslist;
    
    private int selectedIndex;
    
    private long lastClickedCached = 0L;
    
    final GuiShaders shadersGui;
    
    public GuiSlotShaders(GuiShaders par1GuiShaders, int width, int height, int top, int bottom, int slotHeight) {
        super(par1GuiShaders.getMc(), width, height, top, bottom, slotHeight);
        this.shadersGui = par1GuiShaders;
        updateList();
        this.amountScrolled = 0.0F;
        int i = this.selectedIndex * slotHeight;
        int j = (bottom - top) / 2;
        if (i > j)
            scrollBy(i - j); 
    }
    
    public int getListWidth() {
        return this.width - 20;
    }
    
    public void updateList() {
        this.shaderslist = Shaders.listOfShaders();
        this.selectedIndex = 0;
        int i = 0;
        for (int j = this.shaderslist.size(); i < j; i++) {
            if (((String)this.shaderslist.get(i)).equals(Shaders.currentshadername)) {
                this.selectedIndex = i;
                break;
            } 
        } 
    }
    
    protected int getSize() {
        return this.shaderslist.size();
    }
    
    protected void elementClicked(int index, boolean doubleClicked, int mouseX, int mouseY) {
        if (index != this.selectedIndex || this.lastClicked != this.lastClickedCached) {
            this.selectedIndex = index;
            this.lastClickedCached = this.lastClicked;
            Shaders.setShaderPack(this.shaderslist.get(index));
            Shaders.uninit();
            this.shadersGui.updateButtons();
        } 
    }
    
    protected boolean isSelected(int index) {
        return (index == this.selectedIndex);
    }
    
    protected int getScrollBarX() {
        return this.width - 6;
    }
    
    protected int getContentHeight() {
        return getSize() * 18;
    }
    
    protected void drawBackground() {}
    
    protected void drawSlot(int index, int posX, int posY, int contentY, int mouseX, int mouseY) {
        String s = this.shaderslist.get(index);
        if (s.equals(Shaders.packNameNone)) {
            s = Lang.get("of.options.shaders.packNone");
        } else if (s.equals(Shaders.packNameDefault)) {
            s = Lang.get("of.options.shaders.packDefault");
        } 
        this.shadersGui.drawCenteredString(s, this.width / 2, posY + 1, 14737632);
    }
    
    public int getSelectedIndex() {
        return this.selectedIndex;
    }
}
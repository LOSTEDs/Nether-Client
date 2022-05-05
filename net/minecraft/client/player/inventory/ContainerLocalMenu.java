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

package net.minecraft.client.player.inventory;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.LockCode;

public class ContainerLocalMenu extends InventoryBasic implements ILockableContainer {
    private String guiID;
    
    private Map<Integer, Integer> field_174895_b = Maps.newHashMap();
    
    public ContainerLocalMenu(String id, IChatComponent title, int slotCount) {
        super(title, slotCount);
        this.guiID = id;
    }
    
    public int getField(int id) {
        return this.field_174895_b.containsKey(Integer.valueOf(id)) ? ((Integer)this.field_174895_b.get(Integer.valueOf(id))).intValue() : 0;
    }
    
    public void setField(int id, int value) {
        this.field_174895_b.put(Integer.valueOf(id), Integer.valueOf(value));
    }
    
    public int getFieldCount() {
        return this.field_174895_b.size();
    }
    
    public boolean isLocked() {
        return false;
    }
    
    public void setLockCode(LockCode code) {}
    
    public LockCode getLockCode() {
        return LockCode.EMPTY_CODE;
    }
    
    public String getGuiID() {
        return this.guiID;
    }
    
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        throw new UnsupportedOperationException();
    }
}

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

package net.minecraft.world.storage;

import net.minecraft.world.WorldSavedData;

public class SaveDataMemoryStorage extends MapStorage {
    public SaveDataMemoryStorage() {
        super(null);
    }
    
    public WorldSavedData loadData(Class<? extends WorldSavedData> clazz, String dataIdentifier) {
        return this.loadedDataMap.get(dataIdentifier);
    }
    
    public void setData(String dataIdentifier, WorldSavedData data) {
        this.loadedDataMap.put(dataIdentifier, data);
    }
    
    public void saveAllData() {}
    
    public int getUniqueDataId(String key) {
        return 0;
    }
}

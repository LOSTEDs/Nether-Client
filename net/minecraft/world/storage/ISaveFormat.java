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

import java.util.List;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.util.IProgressUpdate;

public interface ISaveFormat {
    String getName();
    
    ISaveHandler getSaveLoader(String paramString, boolean paramBoolean);
    
    List<SaveFormatComparator> getSaveList() throws AnvilConverterException;
    
    void flushCache();
    
    WorldInfo getWorldInfo(String paramString);
    
    boolean func_154335_d(String paramString);
    
    boolean deleteWorldDirectory(String paramString);
    
    void renameWorld(String paramString1, String paramString2);
    
    boolean func_154334_a(String paramString);
    
    boolean isOldMapFormat(String paramString);
    
    boolean convertMapFormat(String paramString, IProgressUpdate paramIProgressUpdate);
    
    boolean canLoadWorld(String paramString);
}

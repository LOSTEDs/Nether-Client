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

package net.minecraft.client.resources;

import java.io.InputStream;
import net.minecraft.util.ResourceLocation;

public interface IResource {
    ResourceLocation getResourceLocation();
    
    InputStream getInputStream();
    
    boolean hasMetadata();
    
    <T extends net.minecraft.client.resources.data.IMetadataSection> T getMetadata(String paramString);
    
    String getResourcePackName();
}

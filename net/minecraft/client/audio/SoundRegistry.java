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

package net.minecraft.client.audio;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.util.RegistrySimple;
import net.minecraft.util.ResourceLocation;

public class SoundRegistry extends RegistrySimple<ResourceLocation, SoundEventAccessorComposite> {
    private Map<ResourceLocation, SoundEventAccessorComposite> soundRegistry;
    
    protected Map<ResourceLocation, SoundEventAccessorComposite> createUnderlyingMap() {
        this.soundRegistry = Maps.newHashMap();
        return this.soundRegistry;
    }
    
    public void registerSound(SoundEventAccessorComposite p_148762_1_) {
        putObject(p_148762_1_.getSoundEventLocation(), p_148762_1_);
    }
    
    public void clearMap() {
        this.soundRegistry.clear();
    }
}

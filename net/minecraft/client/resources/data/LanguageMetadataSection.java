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

package net.minecraft.client.resources.data;

import java.util.Collection;
import net.minecraft.client.resources.Language;

public class LanguageMetadataSection implements IMetadataSection {
    private final Collection<Language> languages;
    
    public LanguageMetadataSection(Collection<Language> p_i1311_1_) {
        this.languages = p_i1311_1_;
    }
    
    public Collection<Language> getLanguages() {
        return this.languages;
    }
}

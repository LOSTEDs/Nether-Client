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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapterFactory;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumTypeAdapterFactory;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IRegistry;
import net.minecraft.util.RegistrySimple;

public class IMetadataSerializer {
    private final IRegistry<String, Registration<? extends IMetadataSection>> metadataSectionSerializerRegistry = (IRegistry<String, Registration<? extends IMetadataSection>>)new RegistrySimple();
    
    private final GsonBuilder gsonBuilder = new GsonBuilder();
    
    private Gson gson;
    
    public IMetadataSerializer() {
        this.gsonBuilder.registerTypeHierarchyAdapter(IChatComponent.class, new IChatComponent.Serializer());
        this.gsonBuilder.registerTypeHierarchyAdapter(ChatStyle.class, new ChatStyle.Serializer());
        this.gsonBuilder.registerTypeAdapterFactory((TypeAdapterFactory)new EnumTypeAdapterFactory());
    }
    
    public <T extends IMetadataSection> void registerMetadataSectionType(IMetadataSectionSerializer<T> p_110504_1_, Class<T> p_110504_2_) {
        this.metadataSectionSerializerRegistry.putObject(p_110504_1_.getSectionName(), new Registration<>(p_110504_1_, p_110504_2_, null));
        this.gsonBuilder.registerTypeAdapter(p_110504_2_, p_110504_1_);
        this.gson = null;
    }
    
    public <T extends IMetadataSection> T parseMetadataSection(String p_110503_1_, JsonObject p_110503_2_) {
        if (p_110503_1_ == null)
            throw new IllegalArgumentException("Metadata section name cannot be null"); 
        if (!p_110503_2_.has(p_110503_1_))
            return null; 
        if (!p_110503_2_.get(p_110503_1_).isJsonObject())
            throw new IllegalArgumentException("Invalid metadata for '" + p_110503_1_ + "' - expected object, found " + p_110503_2_.get(p_110503_1_)); 
        Registration<?> registration = (Registration)this.metadataSectionSerializerRegistry.getObject(p_110503_1_);
        if (registration == null)
            throw new IllegalArgumentException("Don't know how to handle metadata section '" + p_110503_1_ + "'"); 
        return (T)getGson().fromJson((JsonElement)p_110503_2_.getAsJsonObject(p_110503_1_), registration.field_110500_b);
    }
    
    private Gson getGson() {
        if (this.gson == null)
            this.gson = this.gsonBuilder.create(); 
        return this.gson;
    }
    
    class Registration<T extends IMetadataSection> {
        final IMetadataSectionSerializer<T> field_110502_a;
        
        final Class<T> field_110500_b;
        
        private Registration(IMetadataSectionSerializer<T> p_i1305_2_, Class<T> p_i1305_3_) {
            this.field_110502_a = p_i1305_2_;
            this.field_110500_b = p_i1305_3_;
        }
    }
}

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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.resources.data.AnimationMetadataSectionSerializer;
import net.minecraft.client.resources.data.FontMetadataSection;
import net.minecraft.client.resources.data.FontMetadataSectionSerializer;
import net.minecraft.client.resources.data.IMetadataSectionSerializer;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.resources.data.LanguageMetadataSection;
import net.minecraft.client.resources.data.LanguageMetadataSectionSerializer;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.client.resources.data.PackMetadataSectionSerializer;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.client.resources.data.TextureMetadataSectionSerializer;
import org.apache.commons.io.IOUtils;
import shadersmod.common.SMCLog;

public class SimpleShaderTexture extends AbstractTexture {
    private String texturePath;
    
    private static final IMetadataSerializer METADATA_SERIALIZER = makeMetadataSerializer();
    
    public SimpleShaderTexture(String texturePath) {
        this.texturePath = texturePath;
    }
    
    public void loadTexture(IResourceManager resourceManager) throws IOException {
        deleteGlTexture();
        InputStream inputstream = Shaders.getShaderPackResourceStream(this.texturePath);
        if (inputstream == null)
            throw new FileNotFoundException("Shader texture not found: " + this.texturePath); 
        try {
            BufferedImage bufferedimage = TextureUtil.readBufferedImage(inputstream);
            TextureMetadataSection texturemetadatasection = loadTextureMetadataSection();
            TextureUtil.uploadTextureImageAllocate(getGlTextureId(), bufferedimage, texturemetadatasection.getTextureBlur(), texturemetadatasection.getTextureClamp());
        } finally {
            IOUtils.closeQuietly(inputstream);
        } 
    }
    
    private TextureMetadataSection loadTextureMetadataSection() {
        String s = String.valueOf(this.texturePath) + ".mcmeta";
        String s1 = "texture";
        InputStream inputstream = Shaders.getShaderPackResourceStream(s);
        if (inputstream != null) {
            TextureMetadataSection texturemetadatasection1;
            IMetadataSerializer imetadataserializer = METADATA_SERIALIZER;
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
            try {
                JsonObject jsonobject = (new JsonParser()).parse(bufferedreader).getAsJsonObject();
                TextureMetadataSection texturemetadatasection = (TextureMetadataSection)imetadataserializer.parseMetadataSection(s1, jsonobject);
            } catch (RuntimeException runtimeexception) {
                SMCLog.warning("Error reading metadata: " + s);
                SMCLog.warning(runtimeexception.getClass().getName() + ": " + runtimeexception.getMessage());
                return new TextureMetadataSection(false, false, new ArrayList());
            } finally {
                IOUtils.closeQuietly(bufferedreader);
                IOUtils.closeQuietly(inputstream);
            } 
            IOUtils.closeQuietly(bufferedreader);
            IOUtils.closeQuietly(inputstream);
            return texturemetadatasection1;
        } 
        return new TextureMetadataSection(false, false, new ArrayList());
    }
    
    private static IMetadataSerializer makeMetadataSerializer() {
        IMetadataSerializer imetadataserializer = new IMetadataSerializer();
        imetadataserializer.registerMetadataSectionType((IMetadataSectionSerializer)new TextureMetadataSectionSerializer(), TextureMetadataSection.class);
        imetadataserializer.registerMetadataSectionType((IMetadataSectionSerializer)new FontMetadataSectionSerializer(), FontMetadataSection.class);
        imetadataserializer.registerMetadataSectionType((IMetadataSectionSerializer)new AnimationMetadataSectionSerializer(), AnimationMetadataSection.class);
        imetadataserializer.registerMetadataSectionType((IMetadataSectionSerializer)new PackMetadataSectionSerializer(), PackMetadataSection.class);
        imetadataserializer.registerMetadataSectionType((IMetadataSectionSerializer)new LanguageMetadataSectionSerializer(), LanguageMetadataSection.class);
        return imetadataserializer;
    }
}

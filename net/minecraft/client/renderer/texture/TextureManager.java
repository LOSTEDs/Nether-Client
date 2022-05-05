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

package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.src.Config;
import net.minecraft.src.CustomGuis;
import net.minecraft.src.RandomMobs;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shadersmod.client.ShadersTex;

public class TextureManager implements ITickable, IResourceManagerReloadListener {
    private static final Logger logger = LogManager.getLogger();
    
    private final Map<ResourceLocation, ITextureObject> mapTextureObjects = Maps.newHashMap();
    
    private final List<ITickable> listTickables = Lists.newArrayList();
    
    private final Map<String, Integer> mapTextureCounters = Maps.newHashMap();
    
    private IResourceManager theResourceManager;
    
    private static final String __OBFID = "CL_00001064";
    
    public TextureManager(IResourceManager resourceManager) {
        this.theResourceManager = resourceManager;
    }
    
    public void bindTexture(ResourceLocation resource) {
        if (Config.isRandomMobs())
            resource = RandomMobs.getTextureLocation(resource); 
        if (Config.isCustomGuis())
            resource = CustomGuis.getTextureLocation(resource); 
        Object object = this.mapTextureObjects.get(resource);
        if (object == null) {
            object = new SimpleTexture(resource);
            loadTexture(resource, (ITextureObject)object);
        } 
        if (Config.isShaders()) {
            ShadersTex.bindTexture((ITextureObject)object);
        } else {
            TextureUtil.bindTexture(((ITextureObject)object).getGlTextureId());
        } 
    }
    
    public boolean loadTickableTexture(ResourceLocation textureLocation, ITickableTextureObject textureObj) {
        if (loadTexture(textureLocation, textureObj)) {
            this.listTickables.add(textureObj);
            return true;
        } 
        return false;
    }
    
    public boolean loadTexture(ResourceLocation textureLocation, ITextureObject textureObj) {
        boolean flag = true;
        ITextureObject itextureobject = textureObj;
        try {
            textureObj.loadTexture(this.theResourceManager);
        } catch (IOException ioexception) {
            logger.warn("Failed to load texture: " + textureLocation, ioexception);
            itextureobject = TextureUtil.missingTexture;
            this.mapTextureObjects.put(textureLocation, itextureobject);
            flag = false;
        } catch (Throwable throwable) {
            final ITextureObject textureObjf = textureObj;
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Registering texture");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("Resource location being registered");
            crashreportcategory.addCrashSection("Resource location", textureLocation);
            crashreportcategory.addCrashSectionCallable("Texture object class", new Callable() {
                        private static final String __OBFID = "CL_00001065";
                        
                        public String call() throws Exception {
                            return textureObjf.getClass().getName();
                        }
                    });
            throw new ReportedException(crashreport);
        } 
        this.mapTextureObjects.put(textureLocation, itextureobject);
        return flag;
    }
    
    public ITextureObject getTexture(ResourceLocation textureLocation) {
        return this.mapTextureObjects.get(textureLocation);
    }
    
    public ResourceLocation getDynamicTextureLocation(String name, DynamicTexture texture) {
        if (name.equals("logo"))
            texture = Config.getMojangLogoTexture(texture); 
        Integer integer = this.mapTextureCounters.get(name);
        if (integer == null) {
            integer = Integer.valueOf(1);
        } else {
            integer = Integer.valueOf(integer.intValue() + 1);
        } 
        this.mapTextureCounters.put(name, integer);
        ResourceLocation resourcelocation = new ResourceLocation(String.format("dynamic/%s_%d", new Object[] { name, integer }));
        loadTexture(resourcelocation, texture);
        return resourcelocation;
    }
    
    public void tick() {
        for (ITickable itickable : this.listTickables)
            itickable.tick(); 
    }
    
    public void deleteTexture(ResourceLocation textureLocation) {
        ITextureObject itextureobject = getTexture(textureLocation);
        if (itextureobject != null) {
            this.mapTextureObjects.remove(textureLocation);
            TextureUtil.deleteTexture(itextureobject.getGlTextureId());
        } 
    }
    
    public void onResourceManagerReload(IResourceManager resourceManager) {
        Config.dbg("*** Reloading textures ***");
        Config.log("Resource packs: " + Config.getResourcePackNames());
        Iterator<ResourceLocation> iterator = this.mapTextureObjects.keySet().iterator();
        while (iterator.hasNext()) {
            ResourceLocation resourcelocation = iterator.next();
            String s = resourcelocation.getResourcePath();
            if (s.startsWith("mcpatcher/") || s.startsWith("optifine/")) {
                ITextureObject itextureobject = this.mapTextureObjects.get(resourcelocation);
                if (itextureobject instanceof AbstractTexture) {
                    AbstractTexture abstracttexture = (AbstractTexture)itextureobject;
                    abstracttexture.deleteGlTexture();
                } 
                iterator.remove();
            } 
        } 
        for (Map.Entry<ResourceLocation, ITextureObject> entry : this.mapTextureObjects.entrySet())
            loadTexture((ResourceLocation)entry.getKey(), (ITextureObject)entry.getValue()); 
    }
    
    public void reloadBannerTextures() {
        for (Map.Entry<ResourceLocation, ITextureObject> entry : this.mapTextureObjects.entrySet()) {
            ResourceLocation resourcelocation = (ResourceLocation)entry.getKey();
            ITextureObject itextureobject = (ITextureObject)entry.getValue();
            if (itextureobject instanceof LayeredColorMaskTexture)
                loadTexture(resourcelocation, itextureobject); 
        } 
    }
}

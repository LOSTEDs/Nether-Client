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

package net.minecraft.client.renderer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.src.CapeImageBuffer;
import net.minecraft.src.Config;
import net.minecraft.src.HttpPipeline;
import net.minecraft.src.HttpRequest;
import net.minecraft.src.HttpResponse;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadDownloadImageData extends SimpleTexture {
    private static final Logger logger = LogManager.getLogger();
    
    private static final AtomicInteger threadDownloadCounter = new AtomicInteger(0);
    
    private final File cacheFile;
    
    private final String imageUrl;
    
    private final IImageBuffer imageBuffer;
    
    private BufferedImage bufferedImage;
    
    private Thread imageThread;
    
    private boolean textureUploaded;
    
    private static final String __OBFID = "CL_00001049";
    
    public Boolean imageFound = null;
    
    public boolean pipeline = false;
    
    public ThreadDownloadImageData(File cacheFileIn, String imageUrlIn, ResourceLocation textureResourceLocation, IImageBuffer imageBufferIn) {
        super(textureResourceLocation);
        this.cacheFile = cacheFileIn;
        this.imageUrl = imageUrlIn;
        this.imageBuffer = imageBufferIn;
    }
    
    private void checkTextureUploaded() {
        if (!this.textureUploaded && this.bufferedImage != null) {
            this.textureUploaded = true;
            if (this.textureLocation != null)
                deleteGlTexture(); 
            TextureUtil.uploadTextureImage(super.getGlTextureId(), this.bufferedImage);
        } 
    }
    
    public int getGlTextureId() {
        checkTextureUploaded();
        return super.getGlTextureId();
    }
    
    public void setBufferedImage(BufferedImage bufferedImageIn) {
        this.bufferedImage = bufferedImageIn;
        if (this.imageBuffer != null)
            this.imageBuffer.skinAvailable(); 
        this.imageFound = Boolean.valueOf((this.bufferedImage != null));
    }
    
    public void loadTexture(IResourceManager resourceManager) throws IOException {
        if (this.bufferedImage == null && this.textureLocation != null)
            super.loadTexture(resourceManager); 
        if (this.imageThread == null)
            if (this.cacheFile != null && this.cacheFile.isFile()) {
                logger.debug("Loading http texture from local cache ({})", new Object[] { this.cacheFile });
                try {
                    this.bufferedImage = ImageIO.read(this.cacheFile);
                    if (this.imageBuffer != null)
                        setBufferedImage(this.imageBuffer.parseUserSkin(this.bufferedImage)); 
                    loadingFinished();
                } catch (IOException ioexception) {
                    logger.error("Couldn't load skin " + this.cacheFile, ioexception);
                    loadTextureFromServer();
                } 
            } else {
                loadTextureFromServer();
            }  
    }
    
    protected void loadTextureFromServer() {
        this.imageThread = new Thread("Texture Downloader #" + threadDownloadCounter.incrementAndGet()) {
                private static final String __OBFID = "CL_00001050";
                
                public void run() {
                    HttpURLConnection httpurlconnection = null;
                    ThreadDownloadImageData.logger.debug("Downloading http texture from {} to {}", new Object[] { ThreadDownloadImageData.access$1(this.this$0), ThreadDownloadImageData.access$2(this.this$0) });
                    if (ThreadDownloadImageData.this.shouldPipeline()) {
                        ThreadDownloadImageData.this.loadPipelined();
                    } else {
                        try {
                            BufferedImage bufferedimage;
                            httpurlconnection = (HttpURLConnection)(new URL(ThreadDownloadImageData.this.imageUrl)).openConnection(Minecraft.getMinecraft().getProxy());
                            httpurlconnection.setDoInput(true);
                            httpurlconnection.setDoOutput(false);
                            httpurlconnection.connect();
                            if (httpurlconnection.getResponseCode() / 100 != 2) {
                                if (httpurlconnection.getErrorStream() != null)
                                    Config.readAll(httpurlconnection.getErrorStream()); 
                                return;
                            } 
                            if (ThreadDownloadImageData.this.cacheFile != null) {
                                FileUtils.copyInputStreamToFile(httpurlconnection.getInputStream(), ThreadDownloadImageData.this.cacheFile);
                                bufferedimage = ImageIO.read(ThreadDownloadImageData.this.cacheFile);
                            } else {
                                bufferedimage = TextureUtil.readBufferedImage(httpurlconnection.getInputStream());
                            } 
                        } catch (Exception exception) {
                            ThreadDownloadImageData.logger.error("Couldn't download http texture: " + exception.getClass().getName() + ": " + exception.getMessage());
                            return;
                        } finally {
                            if (httpurlconnection != null)
                                httpurlconnection.disconnect(); 
                            ThreadDownloadImageData.this.loadingFinished();
                        } 
                        if (httpurlconnection != null)
                            httpurlconnection.disconnect(); 
                        ThreadDownloadImageData.this.loadingFinished();
                    } 
                }
            };
        this.imageThread.setDaemon(true);
        this.imageThread.start();
    }
    
    private boolean shouldPipeline() {
        if (!this.pipeline)
            return false; 
        Proxy proxy = Minecraft.getMinecraft().getProxy();
        return (proxy.type() != Proxy.Type.DIRECT && proxy.type() != Proxy.Type.SOCKS) ? false : this.imageUrl.startsWith("http://");
    }
    
    private void loadPipelined() {
        try {
            BufferedImage bufferedimage;
            HttpRequest httprequest = HttpPipeline.makeRequest(this.imageUrl, Minecraft.getMinecraft().getProxy());
            HttpResponse httpresponse = HttpPipeline.executeRequest(httprequest);
            if (httpresponse.getStatus() / 100 != 2)
                return; 
            byte[] abyte = httpresponse.getBody();
            ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte);
            if (this.cacheFile != null) {
                FileUtils.copyInputStreamToFile(bytearrayinputstream, this.cacheFile);
                bufferedimage = ImageIO.read(this.cacheFile);
            } else {
                bufferedimage = TextureUtil.readBufferedImage(bytearrayinputstream);
            } 
            if (this.imageBuffer != null)
                bufferedimage = this.imageBuffer.parseUserSkin(bufferedimage); 
            setBufferedImage(bufferedimage);
        } catch (Exception exception) {
            logger.error("Couldn't download http texture: " + exception.getClass().getName() + ": " + exception.getMessage());
            return;
        } finally {
            loadingFinished();
        } 
    }
    
    private void loadingFinished() {
        this.imageFound = Boolean.valueOf((this.bufferedImage != null));
        if (this.imageBuffer instanceof CapeImageBuffer) {
            CapeImageBuffer capeimagebuffer = (CapeImageBuffer)this.imageBuffer;
            capeimagebuffer.cleanup();
        } 
    }
}

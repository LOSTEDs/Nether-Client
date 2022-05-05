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

import client.Client;
import client.hud.impl.HudManager;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.client.renderer.chunk.IRenderChunkFactory;
import net.minecraft.client.renderer.chunk.ListChunkFactory;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.chunk.VboChunkFactory;
import net.minecraft.client.renderer.chunk.VisGraph;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderLinkHelper;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemRecord;
import net.minecraft.src.ChunkUtils;
import net.minecraft.src.CloudRenderer;
import net.minecraft.src.Config;
import net.minecraft.src.CustomColors;
import net.minecraft.src.CustomSky;
import net.minecraft.src.DynamicLights;
import net.minecraft.src.Lagometer;
import net.minecraft.src.RandomMobs;
import net.minecraft.src.Reflector;
import net.minecraft.src.RenderInfoLazy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Matrix4f;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vector3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.Chunk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import shadersmod.client.Shaders;
import shadersmod.client.ShadersRender;
import shadersmod.client.ShadowUtils;

public class RenderGlobal implements IWorldAccess, IResourceManagerReloadListener {
    private static final Logger logger = LogManager.getLogger();
    
    private static final ResourceLocation locationMoonPhasesPng = new ResourceLocation("textures/environment/moon_phases.png");
    
    private static final ResourceLocation locationSunPng = new ResourceLocation("textures/environment/sun.png");
    
    private static final ResourceLocation locationCloudsPng = new ResourceLocation("textures/environment/clouds.png");
    
    private static final ResourceLocation locationEndSkyPng = new ResourceLocation("textures/environment/end_sky.png");
    
    private static final ResourceLocation locationForcefieldPng = new ResourceLocation("textures/misc/forcefield.png");
    
    public final Minecraft mc;
    
    private final TextureManager renderEngine;
    
    private final RenderManager renderManager;
    
    private WorldClient theWorld;
    
    private Set<RenderChunk> chunksToUpdate = Sets.newLinkedHashSet();
    
    private List<ContainerLocalRenderInformation> renderInfos = Lists.newArrayListWithCapacity(69696);
    
    private final Set<TileEntity> setTileEntities = Sets.newHashSet();
    
    private ViewFrustum viewFrustum;
    
    private int starGLCallList = -1;
    
    private int glSkyList = -1;
    
    private int glSkyList2 = -1;
    
    private VertexFormat vertexBufferFormat;
    
    private VertexBuffer starVBO;
    
    private VertexBuffer skyVBO;
    
    private VertexBuffer sky2VBO;
    
    private int cloudTickCounter;
    
    final Map<Integer, DestroyBlockProgress> damagedBlocks = Maps.newHashMap();
    
    private final Map<BlockPos, ISound> mapSoundPositions = Maps.newHashMap();
    
    private TextureAtlasSprite[] destroyBlockIcons = new TextureAtlasSprite[10];
    
    private Framebuffer entityOutlineFramebuffer;
    
    private ShaderGroup entityOutlineShader;
    
    private double frustumUpdatePosX = Double.MIN_VALUE;
    
    private double frustumUpdatePosY = Double.MIN_VALUE;
    
    private double frustumUpdatePosZ = Double.MIN_VALUE;
    
    private int frustumUpdatePosChunkX = Integer.MIN_VALUE;
    
    private int frustumUpdatePosChunkY = Integer.MIN_VALUE;
    
    private int frustumUpdatePosChunkZ = Integer.MIN_VALUE;
    
    private double lastViewEntityX = Double.MIN_VALUE;
    
    private double lastViewEntityY = Double.MIN_VALUE;
    
    private double lastViewEntityZ = Double.MIN_VALUE;
    
    private double lastViewEntityPitch = Double.MIN_VALUE;
    
    private double lastViewEntityYaw = Double.MIN_VALUE;
    
    private final ChunkRenderDispatcher renderDispatcher = new ChunkRenderDispatcher();
    
    private ChunkRenderContainer renderContainer;
    
    private int renderDistanceChunks = -1;
    
    private int renderEntitiesStartupCounter = 2;
    
    private int countEntitiesTotal;
    
    private int countEntitiesRendered;
    
    private int countEntitiesHidden;
    
    private boolean debugFixTerrainFrustum = false;
    
    private ClippingHelper debugFixedClippingHelper;
    
    private final Vector4f[] debugTerrainMatrix = new Vector4f[8];
    
    private final Vector3d debugTerrainFrustumPosition = new Vector3d();
    
    private boolean vboEnabled = false;
    
    IRenderChunkFactory renderChunkFactory;
    
    private double prevRenderSortX;
    
    private double prevRenderSortY;
    
    private double prevRenderSortZ;
    
    public boolean displayListEntitiesDirty = true;
    
    private static final String __OBFID = "CL_00000954";
    
    private CloudRenderer cloudRenderer;
    
    public Entity renderedEntity;
    
    public Set chunksToResortTransparency = new LinkedHashSet();
    
    public Set chunksToUpdateForced = new LinkedHashSet();
    
    private Deque visibilityDeque = new ArrayDeque();
    
    private List renderInfosEntities = new ArrayList(1024);
    
    private List<ContainerLocalRenderInformation> renderInfosTileEntities = new ArrayList<>(1024);
    
    private List renderInfosNormal = new ArrayList(1024);
    
    private List renderInfosEntitiesNormal = new ArrayList(1024);
    
    private List renderInfosTileEntitiesNormal = new ArrayList(1024);
    
    private List renderInfosShadow = new ArrayList(1024);
    
    private List renderInfosEntitiesShadow = new ArrayList(1024);
    
    private List renderInfosTileEntitiesShadow = new ArrayList(1024);
    
    private int renderDistance = 0;
    
    private int renderDistanceSq = 0;
    
    private static final Set SET_ALL_FACINGS = Collections.unmodifiableSet(new HashSet(Arrays.asList((Object[])EnumFacing.VALUES)));
    
    private int countTileEntitiesRendered;
    
    public boolean renderOverlayDamaged = false;
    
    public boolean renderOverlayEyes = false;
    
    public RenderGlobal(Minecraft mcIn) {
        this.cloudRenderer = new CloudRenderer(mcIn);
        this.mc = mcIn;
        this.renderManager = mcIn.getRenderManager();
        this.renderEngine = mcIn.getTextureManager();
        this.renderEngine.bindTexture(locationForcefieldPng);
        GL11.glTexParameteri(3553, 10242, 10497);
        GL11.glTexParameteri(3553, 10243, 10497);
        GlStateManager.bindTexture(0);
        updateDestroyBlockIcons();
        this.vboEnabled = OpenGlHelper.useVbo();
        if (this.vboEnabled) {
            this.renderContainer = new VboRenderList();
            this.renderChunkFactory = (IRenderChunkFactory)new VboChunkFactory();
        } else {
            this.renderContainer = new RenderList();
            this.renderChunkFactory = (IRenderChunkFactory)new ListChunkFactory();
        } 
        this.vertexBufferFormat = new VertexFormat();
        this.vertexBufferFormat.func_181721_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.POSITION, 3));
        generateStars();
        generateSky();
        generateSky2();
    }
    
    public void onResourceManagerReload(IResourceManager resourceManager) {
        updateDestroyBlockIcons();
    }
    
    private void updateDestroyBlockIcons() {
        TextureMap texturemap = this.mc.getTextureMapBlocks();
        for (int i = 0; i < this.destroyBlockIcons.length; i++)
            this.destroyBlockIcons[i] = texturemap.getAtlasSprite("minecraft:blocks/destroy_stage_" + i); 
    }
    
    public void makeEntityOutlineShader() {
        if (OpenGlHelper.shadersSupported) {
            if (ShaderLinkHelper.getStaticShaderLinkHelper() == null)
                ShaderLinkHelper.setNewStaticShaderLinkHelper(); 
            ResourceLocation resourcelocation = new ResourceLocation("shaders/post/entity_outline.json");
            try {
                this.entityOutlineShader = new ShaderGroup(this.mc.getTextureManager(), this.mc.getResourceManager(), this.mc.getFramebuffer(), resourcelocation);
                this.entityOutlineShader.createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
                this.entityOutlineFramebuffer = this.entityOutlineShader.getFramebufferRaw("final");
            } catch (IOException ioexception) {
                logger.warn("Failed to load shader: " + resourcelocation, ioexception);
                this.entityOutlineShader = null;
                this.entityOutlineFramebuffer = null;
            } catch (JsonSyntaxException jsonsyntaxexception) {
                logger.warn("Failed to load shader: " + resourcelocation, (Throwable)jsonsyntaxexception);
                this.entityOutlineShader = null;
                this.entityOutlineFramebuffer = null;
            } 
        } else {
            this.entityOutlineShader = null;
            this.entityOutlineFramebuffer = null;
        } 
    }
    
    public void renderEntityOutlineFramebuffer() {
        if (isRenderEntityOutlines()) {
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
            this.entityOutlineFramebuffer.framebufferRenderExt(this.mc.displayWidth, this.mc.displayHeight, false);
            GlStateManager.disableBlend();
        } 
    }
    
    protected boolean isRenderEntityOutlines() {
        return (!Config.isFastRender() && !Config.isShaders() && !Config.isAntialiasing()) ? ((this.entityOutlineFramebuffer != null && this.entityOutlineShader != null && Minecraft.thePlayer != null && Minecraft.thePlayer.isSpectator() && this.mc.gameSettings.keyBindSpectatorOutlines.isKeyDown())) : false;
    }
    
    private void generateSky2() {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        if (this.sky2VBO != null)
            this.sky2VBO.deleteGlBuffers(); 
        if (this.glSkyList2 >= 0) {
            GLAllocation.deleteDisplayLists(this.glSkyList2);
            this.glSkyList2 = -1;
        } 
        if (this.vboEnabled) {
            this.sky2VBO = new VertexBuffer(this.vertexBufferFormat);
            renderSky(worldrenderer, -16.0F, true);
            worldrenderer.finishDrawing();
            worldrenderer.reset();
            this.sky2VBO.func_181722_a(worldrenderer.getByteBuffer());
        } else {
            this.glSkyList2 = GLAllocation.generateDisplayLists(1);
            GL11.glNewList(this.glSkyList2, 4864);
            renderSky(worldrenderer, -16.0F, true);
            tessellator.draw();
            GL11.glEndList();
        } 
    }
    
    private void generateSky() {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        if (this.skyVBO != null)
            this.skyVBO.deleteGlBuffers(); 
        if (this.glSkyList >= 0) {
            GLAllocation.deleteDisplayLists(this.glSkyList);
            this.glSkyList = -1;
        } 
        if (this.vboEnabled) {
            this.skyVBO = new VertexBuffer(this.vertexBufferFormat);
            renderSky(worldrenderer, 16.0F, false);
            worldrenderer.finishDrawing();
            worldrenderer.reset();
            this.skyVBO.func_181722_a(worldrenderer.getByteBuffer());
        } else {
            this.glSkyList = GLAllocation.generateDisplayLists(1);
            GL11.glNewList(this.glSkyList, 4864);
            renderSky(worldrenderer, 16.0F, false);
            tessellator.draw();
            GL11.glEndList();
        } 
    }
    
    private void renderSky(WorldRenderer worldRendererIn, float p_174968_2_, boolean p_174968_3_) {
        boolean flag = true;
        boolean flag1 = true;
        worldRendererIn.begin(7, DefaultVertexFormats.POSITION);
        int i = (this.renderDistance / 64 + 1) * 64 + 64;
        for (int j = -i; j <= i; j += 64) {
            for (int k = -i; k <= i; k += 64) {
                float f = j;
                float f1 = (j + 64);
                if (p_174968_3_) {
                    f1 = j;
                    f = (j + 64);
                } 
                worldRendererIn.pos(f, p_174968_2_, k).endVertex();
                worldRendererIn.pos(f1, p_174968_2_, k).endVertex();
                worldRendererIn.pos(f1, p_174968_2_, (k + 64)).endVertex();
                worldRendererIn.pos(f, p_174968_2_, (k + 64)).endVertex();
            } 
        } 
    }
    
    private void generateStars() {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        if (this.starVBO != null)
            this.starVBO.deleteGlBuffers(); 
        if (this.starGLCallList >= 0) {
            GLAllocation.deleteDisplayLists(this.starGLCallList);
            this.starGLCallList = -1;
        } 
        if (this.vboEnabled) {
            this.starVBO = new VertexBuffer(this.vertexBufferFormat);
            renderStars(worldrenderer);
            worldrenderer.finishDrawing();
            worldrenderer.reset();
            this.starVBO.func_181722_a(worldrenderer.getByteBuffer());
        } else {
            this.starGLCallList = GLAllocation.generateDisplayLists(1);
            GlStateManager.pushMatrix();
            GL11.glNewList(this.starGLCallList, 4864);
            renderStars(worldrenderer);
            tessellator.draw();
            GL11.glEndList();
            GlStateManager.popMatrix();
        } 
    }
    
    private void renderStars(WorldRenderer worldRendererIn) {
        Random random = new Random(10842L);
        worldRendererIn.begin(7, DefaultVertexFormats.POSITION);
        for (int i = 0; i < 1500; i++) {
            double d0 = (random.nextFloat() * 2.0F - 1.0F);
            double d1 = (random.nextFloat() * 2.0F - 1.0F);
            double d2 = (random.nextFloat() * 2.0F - 1.0F);
            double d3 = (0.15F + random.nextFloat() * 0.1F);
            double d4 = d0 * d0 + d1 * d1 + d2 * d2;
            if (d4 < 1.0D && d4 > 0.01D) {
                d4 = 1.0D / Math.sqrt(d4);
                d0 *= d4;
                d1 *= d4;
                d2 *= d4;
                double d5 = d0 * 100.0D;
                double d6 = d1 * 100.0D;
                double d7 = d2 * 100.0D;
                double d8 = Math.atan2(d0, d2);
                double d9 = Math.sin(d8);
                double d10 = Math.cos(d8);
                double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
                double d12 = Math.sin(d11);
                double d13 = Math.cos(d11);
                double d14 = random.nextDouble() * Math.PI * 2.0D;
                double d15 = Math.sin(d14);
                double d16 = Math.cos(d14);
                for (int j = 0; j < 4; j++) {
                    double d17 = 0.0D;
                    double d18 = ((j & 0x2) - 1) * d3;
                    double d19 = ((j + 1 & 0x2) - 1) * d3;
                    double d20 = 0.0D;
                    double d21 = d18 * d16 - d19 * d15;
                    double d22 = d19 * d16 + d18 * d15;
                    double d23 = d21 * d12 + 0.0D * d13;
                    double d24 = 0.0D * d12 - d21 * d13;
                    double d25 = d24 * d9 - d22 * d10;
                    double d26 = d22 * d9 + d24 * d10;
                    worldRendererIn.pos(d5 + d25, d6 + d23, d7 + d26).endVertex();
                } 
            } 
        } 
    }
    
    public void setWorldAndLoadRenderers(WorldClient worldClientIn) {
        if (this.theWorld != null)
            this.theWorld.removeWorldAccess(this); 
        this.frustumUpdatePosX = Double.MIN_VALUE;
        this.frustumUpdatePosY = Double.MIN_VALUE;
        this.frustumUpdatePosZ = Double.MIN_VALUE;
        this.frustumUpdatePosChunkX = Integer.MIN_VALUE;
        this.frustumUpdatePosChunkY = Integer.MIN_VALUE;
        this.frustumUpdatePosChunkZ = Integer.MIN_VALUE;
        this.renderManager.set((World)worldClientIn);
        this.theWorld = worldClientIn;
        if (Config.isDynamicLights())
            DynamicLights.clear(); 
        if (worldClientIn != null) {
            worldClientIn.addWorldAccess(this);
            loadRenderers();
        } 
    }
    
    public void loadRenderers() {
        if (this.theWorld != null) {
            this.displayListEntitiesDirty = true;
            Blocks.leaves.setGraphicsLevel(Config.isTreesFancy());
            Blocks.leaves2.setGraphicsLevel(Config.isTreesFancy());
            BlockModelRenderer.updateAoLightValue();
            if (Config.isDynamicLights())
                DynamicLights.clear(); 
            this.renderDistanceChunks = this.mc.gameSettings.renderDistanceChunks;
            this.renderDistance = this.renderDistanceChunks * 16;
            this.renderDistanceSq = this.renderDistance * this.renderDistance;
            boolean flag = this.vboEnabled;
            this.vboEnabled = OpenGlHelper.useVbo();
            if (flag && !this.vboEnabled) {
                this.renderContainer = new RenderList();
                this.renderChunkFactory = (IRenderChunkFactory)new ListChunkFactory();
            } else if (!flag && this.vboEnabled) {
                this.renderContainer = new VboRenderList();
                this.renderChunkFactory = (IRenderChunkFactory)new VboChunkFactory();
            } 
            generateStars();
            generateSky();
            generateSky2();
            if (this.viewFrustum != null)
                this.viewFrustum.deleteGlResources(); 
            stopChunkUpdates();
            synchronized (this.chunksToUpdate) {
                this.chunksToUpdate.clear();
            } 
            this.viewFrustum = new ViewFrustum((World)this.theWorld, this.mc.gameSettings.renderDistanceChunks, this, this.renderChunkFactory);
            if (this.theWorld != null) {
                Entity entity = this.mc.getRenderViewEntity();
                if (entity != null)
                    this.viewFrustum.updateChunkPositions(entity.posX, entity.posZ); 
            } 
            this.renderEntitiesStartupCounter = 2;
        } 
    }
    
    protected void stopChunkUpdates() {
        this.chunksToUpdate.clear();
        this.renderDispatcher.stopChunkUpdates();
    }
    
    public void createBindEntityOutlineFbs(int p_72720_1_, int p_72720_2_) {
        if (OpenGlHelper.shadersSupported && this.entityOutlineShader != null)
            this.entityOutlineShader.createBindFramebuffers(p_72720_1_, p_72720_2_); 
    }
    
    public void renderEntities(Entity renderViewEntity, ICamera camera, float partialTicks) {
        // Byte code:
        //   0: iconst_0
        //   1: istore #4
        //   3: getstatic net/minecraft/src/Reflector.MinecraftForgeClient_getRenderPass : Lnet/minecraft/src/ReflectorMethod;
        //   6: invokevirtual exists : ()Z
        //   9: ifeq -> 24
        //   12: getstatic net/minecraft/src/Reflector.MinecraftForgeClient_getRenderPass : Lnet/minecraft/src/ReflectorMethod;
        //   15: iconst_0
        //   16: anewarray java/lang/Object
        //   19: invokestatic callInt : (Lnet/minecraft/src/ReflectorMethod;[Ljava/lang/Object;)I
        //   22: istore #4
        //   24: aload_0
        //   25: getfield renderEntitiesStartupCounter : I
        //   28: ifle -> 50
        //   31: iload #4
        //   33: ifle -> 37
        //   36: return
        //   37: aload_0
        //   38: dup
        //   39: getfield renderEntitiesStartupCounter : I
        //   42: iconst_1
        //   43: isub
        //   44: putfield renderEntitiesStartupCounter : I
        //   47: goto -> 2181
        //   50: aload_1
        //   51: getfield prevPosX : D
        //   54: aload_1
        //   55: getfield posX : D
        //   58: aload_1
        //   59: getfield prevPosX : D
        //   62: dsub
        //   63: fload_3
        //   64: f2d
        //   65: dmul
        //   66: dadd
        //   67: dstore #5
        //   69: aload_1
        //   70: getfield prevPosY : D
        //   73: aload_1
        //   74: getfield posY : D
        //   77: aload_1
        //   78: getfield prevPosY : D
        //   81: dsub
        //   82: fload_3
        //   83: f2d
        //   84: dmul
        //   85: dadd
        //   86: dstore #7
        //   88: aload_1
        //   89: getfield prevPosZ : D
        //   92: aload_1
        //   93: getfield posZ : D
        //   96: aload_1
        //   97: getfield prevPosZ : D
        //   100: dsub
        //   101: fload_3
        //   102: f2d
        //   103: dmul
        //   104: dadd
        //   105: dstore #9
        //   107: aload_0
        //   108: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
        //   111: getfield theProfiler : Lnet/minecraft/profiler/Profiler;
        //   114: ldc_w 'prepare'
        //   117: invokevirtual startSection : (Ljava/lang/String;)V
        //   120: getstatic net/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher.instance : Lnet/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher;
        //   123: aload_0
        //   124: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
        //   127: aload_0
        //   128: getfield mc : Lnet/minecraft/client/Minecraft;
        //   131: invokevirtual getTextureManager : ()Lnet/minecraft/client/renderer/texture/TextureManager;
        //   134: aload_0
        //   135: getfield mc : Lnet/minecraft/client/Minecraft;
        //   138: getfield fontRendererObj : Lnet/minecraft/client/gui/FontRenderer;
        //   141: aload_0
        //   142: getfield mc : Lnet/minecraft/client/Minecraft;
        //   145: invokevirtual getRenderViewEntity : ()Lnet/minecraft/entity/Entity;
        //   148: fload_3
        //   149: invokevirtual cacheActiveRenderInfo : (Lnet/minecraft/world/World;Lnet/minecraft/client/renderer/texture/TextureManager;Lnet/minecraft/client/gui/FontRenderer;Lnet/minecraft/entity/Entity;F)V
        //   152: aload_0
        //   153: getfield renderManager : Lnet/minecraft/client/renderer/entity/RenderManager;
        //   156: aload_0
        //   157: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
        //   160: aload_0
        //   161: getfield mc : Lnet/minecraft/client/Minecraft;
        //   164: getfield fontRendererObj : Lnet/minecraft/client/gui/FontRenderer;
        //   167: aload_0
        //   168: getfield mc : Lnet/minecraft/client/Minecraft;
        //   171: invokevirtual getRenderViewEntity : ()Lnet/minecraft/entity/Entity;
        //   174: aload_0
        //   175: getfield mc : Lnet/minecraft/client/Minecraft;
        //   178: getfield pointedEntity : Lnet/minecraft/entity/Entity;
        //   181: aload_0
        //   182: getfield mc : Lnet/minecraft/client/Minecraft;
        //   185: getfield gameSettings : Lnet/minecraft/client/settings/GameSettings;
        //   188: fload_3
        //   189: invokevirtual cacheActiveRenderInfo : (Lnet/minecraft/world/World;Lnet/minecraft/client/gui/FontRenderer;Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/Entity;Lnet/minecraft/client/settings/GameSettings;F)V
        //   192: iload #4
        //   194: ifne -> 217
        //   197: aload_0
        //   198: iconst_0
        //   199: putfield countEntitiesTotal : I
        //   202: aload_0
        //   203: iconst_0
        //   204: putfield countEntitiesRendered : I
        //   207: aload_0
        //   208: iconst_0
        //   209: putfield countEntitiesHidden : I
        //   212: aload_0
        //   213: iconst_0
        //   214: putfield countTileEntitiesRendered : I
        //   217: aload_0
        //   218: getfield mc : Lnet/minecraft/client/Minecraft;
        //   221: invokevirtual getRenderViewEntity : ()Lnet/minecraft/entity/Entity;
        //   224: astore #11
        //   226: aload #11
        //   228: getfield lastTickPosX : D
        //   231: aload #11
        //   233: getfield posX : D
        //   236: aload #11
        //   238: getfield lastTickPosX : D
        //   241: dsub
        //   242: fload_3
        //   243: f2d
        //   244: dmul
        //   245: dadd
        //   246: dstore #12
        //   248: aload #11
        //   250: getfield lastTickPosY : D
        //   253: aload #11
        //   255: getfield posY : D
        //   258: aload #11
        //   260: getfield lastTickPosY : D
        //   263: dsub
        //   264: fload_3
        //   265: f2d
        //   266: dmul
        //   267: dadd
        //   268: dstore #14
        //   270: aload #11
        //   272: getfield lastTickPosZ : D
        //   275: aload #11
        //   277: getfield posZ : D
        //   280: aload #11
        //   282: getfield lastTickPosZ : D
        //   285: dsub
        //   286: fload_3
        //   287: f2d
        //   288: dmul
        //   289: dadd
        //   290: dstore #16
        //   292: dload #12
        //   294: putstatic net/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher.staticPlayerX : D
        //   297: dload #14
        //   299: putstatic net/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher.staticPlayerY : D
        //   302: dload #16
        //   304: putstatic net/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher.staticPlayerZ : D
        //   307: aload_0
        //   308: getfield renderManager : Lnet/minecraft/client/renderer/entity/RenderManager;
        //   311: dload #12
        //   313: dload #14
        //   315: dload #16
        //   317: invokevirtual setRenderPosition : (DDD)V
        //   320: aload_0
        //   321: getfield mc : Lnet/minecraft/client/Minecraft;
        //   324: getfield entityRenderer : Lnet/minecraft/client/renderer/EntityRenderer;
        //   327: invokevirtual enableLightmap : ()V
        //   330: aload_0
        //   331: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
        //   334: getfield theProfiler : Lnet/minecraft/profiler/Profiler;
        //   337: ldc_w 'global'
        //   340: invokevirtual endStartSection : (Ljava/lang/String;)V
        //   343: aload_0
        //   344: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
        //   347: invokevirtual getLoadedEntityList : ()Ljava/util/List;
        //   350: astore #18
        //   352: iload #4
        //   354: ifne -> 368
        //   357: aload_0
        //   358: aload #18
        //   360: invokeinterface size : ()I
        //   365: putfield countEntitiesTotal : I
        //   368: invokestatic isFogOff : ()Z
        //   371: ifeq -> 390
        //   374: aload_0
        //   375: getfield mc : Lnet/minecraft/client/Minecraft;
        //   378: getfield entityRenderer : Lnet/minecraft/client/renderer/EntityRenderer;
        //   381: getfield fogStandard : Z
        //   384: ifeq -> 390
        //   387: invokestatic disableFog : ()V
        //   390: getstatic net/minecraft/src/Reflector.ForgeEntity_shouldRenderInPass : Lnet/minecraft/src/ReflectorMethod;
        //   393: invokevirtual exists : ()Z
        //   396: istore #19
        //   398: getstatic net/minecraft/src/Reflector.ForgeTileEntity_shouldRenderInPass : Lnet/minecraft/src/ReflectorMethod;
        //   401: invokevirtual exists : ()Z
        //   404: istore #20
        //   406: iconst_0
        //   407: istore #21
        //   409: goto -> 497
        //   412: aload_0
        //   413: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
        //   416: getfield weatherEffects : Ljava/util/List;
        //   419: iload #21
        //   421: invokeinterface get : (I)Ljava/lang/Object;
        //   426: checkcast net/minecraft/entity/Entity
        //   429: astore #22
        //   431: iload #19
        //   433: ifeq -> 459
        //   436: aload #22
        //   438: getstatic net/minecraft/src/Reflector.ForgeEntity_shouldRenderInPass : Lnet/minecraft/src/ReflectorMethod;
        //   441: iconst_1
        //   442: anewarray java/lang/Object
        //   445: dup
        //   446: iconst_0
        //   447: iload #4
        //   449: invokestatic valueOf : (I)Ljava/lang/Integer;
        //   452: aastore
        //   453: invokestatic callBoolean : (Ljava/lang/Object;Lnet/minecraft/src/ReflectorMethod;[Ljava/lang/Object;)Z
        //   456: ifeq -> 494
        //   459: aload_0
        //   460: dup
        //   461: getfield countEntitiesRendered : I
        //   464: iconst_1
        //   465: iadd
        //   466: putfield countEntitiesRendered : I
        //   469: aload #22
        //   471: dload #5
        //   473: dload #7
        //   475: dload #9
        //   477: invokevirtual isInRangeToRender3d : (DDD)Z
        //   480: ifeq -> 494
        //   483: aload_0
        //   484: getfield renderManager : Lnet/minecraft/client/renderer/entity/RenderManager;
        //   487: aload #22
        //   489: fload_3
        //   490: invokevirtual renderEntitySimple : (Lnet/minecraft/entity/Entity;F)Z
        //   493: pop
        //   494: iinc #21, 1
        //   497: iload #21
        //   499: aload_0
        //   500: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
        //   503: getfield weatherEffects : Ljava/util/List;
        //   506: invokeinterface size : ()I
        //   511: if_icmplt -> 412
        //   514: aload_0
        //   515: invokevirtual isRenderEntityOutlines : ()Z
        //   518: ifeq -> 838
        //   521: sipush #519
        //   524: invokestatic depthFunc : (I)V
        //   527: invokestatic disableFog : ()V
        //   530: aload_0
        //   531: getfield entityOutlineFramebuffer : Lnet/minecraft/client/shader/Framebuffer;
        //   534: invokevirtual framebufferClear : ()V
        //   537: aload_0
        //   538: getfield entityOutlineFramebuffer : Lnet/minecraft/client/shader/Framebuffer;
        //   541: iconst_0
        //   542: invokevirtual bindFramebuffer : (Z)V
        //   545: aload_0
        //   546: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
        //   549: getfield theProfiler : Lnet/minecraft/profiler/Profiler;
        //   552: ldc_w 'entityOutlines'
        //   555: invokevirtual endStartSection : (Ljava/lang/String;)V
        //   558: invokestatic disableStandardItemLighting : ()V
        //   561: aload_0
        //   562: getfield renderManager : Lnet/minecraft/client/renderer/entity/RenderManager;
        //   565: iconst_1
        //   566: invokevirtual setRenderOutlines : (Z)V
        //   569: iconst_0
        //   570: istore #21
        //   572: goto -> 764
        //   575: aload #18
        //   577: iload #21
        //   579: invokeinterface get : (I)Ljava/lang/Object;
        //   584: checkcast net/minecraft/entity/Entity
        //   587: astore #22
        //   589: iload #19
        //   591: ifeq -> 617
        //   594: aload #22
        //   596: getstatic net/minecraft/src/Reflector.ForgeEntity_shouldRenderInPass : Lnet/minecraft/src/ReflectorMethod;
        //   599: iconst_1
        //   600: anewarray java/lang/Object
        //   603: dup
        //   604: iconst_0
        //   605: iload #4
        //   607: invokestatic valueOf : (I)Ljava/lang/Integer;
        //   610: aastore
        //   611: invokestatic callBoolean : (Ljava/lang/Object;Lnet/minecraft/src/ReflectorMethod;[Ljava/lang/Object;)Z
        //   614: ifeq -> 761
        //   617: aload_0
        //   618: getfield mc : Lnet/minecraft/client/Minecraft;
        //   621: invokevirtual getRenderViewEntity : ()Lnet/minecraft/entity/Entity;
        //   624: instanceof net/minecraft/entity/EntityLivingBase
        //   627: ifeq -> 650
        //   630: aload_0
        //   631: getfield mc : Lnet/minecraft/client/Minecraft;
        //   634: invokevirtual getRenderViewEntity : ()Lnet/minecraft/entity/Entity;
        //   637: checkcast net/minecraft/entity/EntityLivingBase
        //   640: invokevirtual isPlayerSleeping : ()Z
        //   643: ifeq -> 650
        //   646: iconst_1
        //   647: goto -> 651
        //   650: iconst_0
        //   651: istore #23
        //   653: aload #22
        //   655: dload #5
        //   657: dload #7
        //   659: dload #9
        //   661: invokevirtual isInRangeToRender3d : (DDD)Z
        //   664: ifeq -> 712
        //   667: aload #22
        //   669: getfield ignoreFrustumCheck : Z
        //   672: ifne -> 700
        //   675: aload_2
        //   676: aload #22
        //   678: invokevirtual getEntityBoundingBox : ()Lnet/minecraft/util/AxisAlignedBB;
        //   681: invokeinterface isBoundingBoxInFrustum : (Lnet/minecraft/util/AxisAlignedBB;)Z
        //   686: ifne -> 700
        //   689: aload #22
        //   691: getfield riddenByEntity : Lnet/minecraft/entity/Entity;
        //   694: getstatic net/minecraft/client/Minecraft.thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
        //   697: if_acmpne -> 712
        //   700: aload #22
        //   702: instanceof net/minecraft/entity/player/EntityPlayer
        //   705: ifeq -> 712
        //   708: iconst_1
        //   709: goto -> 713
        //   712: iconst_0
        //   713: istore #24
        //   715: aload #22
        //   717: aload_0
        //   718: getfield mc : Lnet/minecraft/client/Minecraft;
        //   721: invokevirtual getRenderViewEntity : ()Lnet/minecraft/entity/Entity;
        //   724: if_acmpne -> 745
        //   727: aload_0
        //   728: getfield mc : Lnet/minecraft/client/Minecraft;
        //   731: getfield gameSettings : Lnet/minecraft/client/settings/GameSettings;
        //   734: getfield thirdPersonView : I
        //   737: ifne -> 745
        //   740: iload #23
        //   742: ifeq -> 761
        //   745: iload #24
        //   747: ifeq -> 761
        //   750: aload_0
        //   751: getfield renderManager : Lnet/minecraft/client/renderer/entity/RenderManager;
        //   754: aload #22
        //   756: fload_3
        //   757: invokevirtual renderEntitySimple : (Lnet/minecraft/entity/Entity;F)Z
        //   760: pop
        //   761: iinc #21, 1
        //   764: iload #21
        //   766: aload #18
        //   768: invokeinterface size : ()I
        //   773: if_icmplt -> 575
        //   776: aload_0
        //   777: getfield renderManager : Lnet/minecraft/client/renderer/entity/RenderManager;
        //   780: iconst_0
        //   781: invokevirtual setRenderOutlines : (Z)V
        //   784: invokestatic enableStandardItemLighting : ()V
        //   787: iconst_0
        //   788: invokestatic depthMask : (Z)V
        //   791: aload_0
        //   792: getfield entityOutlineShader : Lnet/minecraft/client/shader/ShaderGroup;
        //   795: fload_3
        //   796: invokevirtual loadShaderGroup : (F)V
        //   799: invokestatic enableLighting : ()V
        //   802: iconst_1
        //   803: invokestatic depthMask : (Z)V
        //   806: aload_0
        //   807: getfield mc : Lnet/minecraft/client/Minecraft;
        //   810: invokevirtual getFramebuffer : ()Lnet/minecraft/client/shader/Framebuffer;
        //   813: iconst_0
        //   814: invokevirtual bindFramebuffer : (Z)V
        //   817: invokestatic enableFog : ()V
        //   820: invokestatic enableBlend : ()V
        //   823: invokestatic enableColorMaterial : ()V
        //   826: sipush #515
        //   829: invokestatic depthFunc : (I)V
        //   832: invokestatic enableDepth : ()V
        //   835: invokestatic enableAlpha : ()V
        //   838: aload_0
        //   839: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
        //   842: getfield theProfiler : Lnet/minecraft/profiler/Profiler;
        //   845: ldc_w 'entities'
        //   848: invokevirtual endStartSection : (Ljava/lang/String;)V
        //   851: invokestatic isShaders : ()Z
        //   854: istore #21
        //   856: iload #21
        //   858: ifeq -> 864
        //   861: invokestatic beginEntities : ()V
        //   864: aload_0
        //   865: getfield renderInfosEntities : Ljava/util/List;
        //   868: invokeinterface iterator : ()Ljava/util/Iterator;
        //   873: astore #22
        //   875: aload_0
        //   876: getfield mc : Lnet/minecraft/client/Minecraft;
        //   879: getfield gameSettings : Lnet/minecraft/client/settings/GameSettings;
        //   882: getfield fancyGraphics : Z
        //   885: istore #23
        //   887: aload_0
        //   888: getfield mc : Lnet/minecraft/client/Minecraft;
        //   891: getfield gameSettings : Lnet/minecraft/client/settings/GameSettings;
        //   894: invokestatic isDroppedItemsFancy : ()Z
        //   897: putfield fancyGraphics : Z
        //   900: goto -> 1263
        //   903: aload #22
        //   905: invokeinterface next : ()Ljava/lang/Object;
        //   910: checkcast net/minecraft/client/renderer/RenderGlobal$ContainerLocalRenderInformation
        //   913: astore #24
        //   915: aload #24
        //   917: getfield renderChunk : Lnet/minecraft/client/renderer/chunk/RenderChunk;
        //   920: invokevirtual getChunk : ()Lnet/minecraft/world/chunk/Chunk;
        //   923: astore #25
        //   925: aload #25
        //   927: invokevirtual getEntityLists : ()[Lnet/minecraft/util/ClassInheritanceMultiMap;
        //   930: aload #24
        //   932: getfield renderChunk : Lnet/minecraft/client/renderer/chunk/RenderChunk;
        //   935: invokevirtual getPosition : ()Lnet/minecraft/util/BlockPos;
        //   938: invokevirtual getY : ()I
        //   941: bipush #16
        //   943: idiv
        //   944: aaload
        //   945: astore #26
        //   947: aload #26
        //   949: invokevirtual isEmpty : ()Z
        //   952: ifne -> 1263
        //   955: aload #26
        //   957: invokevirtual iterator : ()Ljava/util/Iterator;
        //   960: astore #27
        //   962: aload #27
        //   964: invokeinterface hasNext : ()Z
        //   969: ifne -> 975
        //   972: goto -> 1263
        //   975: aload #27
        //   977: invokeinterface next : ()Ljava/lang/Object;
        //   982: checkcast net/minecraft/entity/Entity
        //   985: astore #28
        //   987: iload #19
        //   989: ifeq -> 1015
        //   992: aload #28
        //   994: getstatic net/minecraft/src/Reflector.ForgeEntity_shouldRenderInPass : Lnet/minecraft/src/ReflectorMethod;
        //   997: iconst_1
        //   998: anewarray java/lang/Object
        //   1001: dup
        //   1002: iconst_0
        //   1003: iload #4
        //   1005: invokestatic valueOf : (I)Ljava/lang/Integer;
        //   1008: aastore
        //   1009: invokestatic callBoolean : (Ljava/lang/Object;Lnet/minecraft/src/ReflectorMethod;[Ljava/lang/Object;)Z
        //   1012: ifeq -> 962
        //   1015: aload_0
        //   1016: getfield renderManager : Lnet/minecraft/client/renderer/entity/RenderManager;
        //   1019: aload #28
        //   1021: aload_2
        //   1022: dload #5
        //   1024: dload #7
        //   1026: dload #9
        //   1028: invokevirtual shouldRender : (Lnet/minecraft/entity/Entity;Lnet/minecraft/client/renderer/culling/ICamera;DDD)Z
        //   1031: ifne -> 1049
        //   1034: aload #28
        //   1036: getfield riddenByEntity : Lnet/minecraft/entity/Entity;
        //   1039: getstatic net/minecraft/client/Minecraft.thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
        //   1042: if_acmpeq -> 1049
        //   1045: iconst_0
        //   1046: goto -> 1050
        //   1049: iconst_1
        //   1050: istore #29
        //   1052: iload #29
        //   1054: ifne -> 1060
        //   1057: goto -> 1224
        //   1060: aload_0
        //   1061: getfield mc : Lnet/minecraft/client/Minecraft;
        //   1064: invokevirtual getRenderViewEntity : ()Lnet/minecraft/entity/Entity;
        //   1067: instanceof net/minecraft/entity/EntityLivingBase
        //   1070: ifeq -> 1089
        //   1073: aload_0
        //   1074: getfield mc : Lnet/minecraft/client/Minecraft;
        //   1077: invokevirtual getRenderViewEntity : ()Lnet/minecraft/entity/Entity;
        //   1080: checkcast net/minecraft/entity/EntityLivingBase
        //   1083: invokevirtual isPlayerSleeping : ()Z
        //   1086: goto -> 1090
        //   1089: iconst_0
        //   1090: istore #30
        //   1092: aload #28
        //   1094: aload_0
        //   1095: getfield mc : Lnet/minecraft/client/Minecraft;
        //   1098: invokevirtual getRenderViewEntity : ()Lnet/minecraft/entity/Entity;
        //   1101: if_acmpne -> 1122
        //   1104: aload_0
        //   1105: getfield mc : Lnet/minecraft/client/Minecraft;
        //   1108: getfield gameSettings : Lnet/minecraft/client/settings/GameSettings;
        //   1111: getfield thirdPersonView : I
        //   1114: ifne -> 1122
        //   1117: iload #30
        //   1119: ifeq -> 962
        //   1122: aload #28
        //   1124: getfield posY : D
        //   1127: dconst_0
        //   1128: dcmpg
        //   1129: iflt -> 1163
        //   1132: aload #28
        //   1134: getfield posY : D
        //   1137: ldc2_w 256.0
        //   1140: dcmpl
        //   1141: ifge -> 1163
        //   1144: aload_0
        //   1145: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
        //   1148: new net/minecraft/util/BlockPos
        //   1151: dup
        //   1152: aload #28
        //   1154: invokespecial <init> : (Lnet/minecraft/entity/Entity;)V
        //   1157: invokevirtual isBlockLoaded : (Lnet/minecraft/util/BlockPos;)Z
        //   1160: ifeq -> 962
        //   1163: aload_0
        //   1164: dup
        //   1165: getfield countEntitiesRendered : I
        //   1168: iconst_1
        //   1169: iadd
        //   1170: putfield countEntitiesRendered : I
        //   1173: aload #28
        //   1175: invokevirtual getClass : ()Ljava/lang/Class;
        //   1178: ldc_w net/minecraft/entity/item/EntityItemFrame
        //   1181: if_acmpne -> 1192
        //   1184: aload #28
        //   1186: ldc2_w 0.06
        //   1189: putfield renderDistanceWeight : D
        //   1192: aload_0
        //   1193: aload #28
        //   1195: putfield renderedEntity : Lnet/minecraft/entity/Entity;
        //   1198: iload #21
        //   1200: ifeq -> 1208
        //   1203: aload #28
        //   1205: invokestatic nextEntity : (Lnet/minecraft/entity/Entity;)V
        //   1208: aload_0
        //   1209: getfield renderManager : Lnet/minecraft/client/renderer/entity/RenderManager;
        //   1212: aload #28
        //   1214: fload_3
        //   1215: invokevirtual renderEntitySimple : (Lnet/minecraft/entity/Entity;F)Z
        //   1218: pop
        //   1219: aload_0
        //   1220: aconst_null
        //   1221: putfield renderedEntity : Lnet/minecraft/entity/Entity;
        //   1224: iload #29
        //   1226: ifne -> 962
        //   1229: aload #28
        //   1231: instanceof net/minecraft/entity/projectile/EntityWitherSkull
        //   1234: ifeq -> 962
        //   1237: iload #21
        //   1239: ifeq -> 1247
        //   1242: aload #28
        //   1244: invokestatic nextEntity : (Lnet/minecraft/entity/Entity;)V
        //   1247: aload_0
        //   1248: getfield mc : Lnet/minecraft/client/Minecraft;
        //   1251: invokevirtual getRenderManager : ()Lnet/minecraft/client/renderer/entity/RenderManager;
        //   1254: aload #28
        //   1256: fload_3
        //   1257: invokevirtual renderWitherSkull : (Lnet/minecraft/entity/Entity;F)V
        //   1260: goto -> 962
        //   1263: aload #22
        //   1265: invokeinterface hasNext : ()Z
        //   1270: ifne -> 903
        //   1273: aload_0
        //   1274: getfield mc : Lnet/minecraft/client/Minecraft;
        //   1277: getfield gameSettings : Lnet/minecraft/client/settings/GameSettings;
        //   1280: iload #23
        //   1282: putfield fancyGraphics : Z
        //   1285: getstatic net/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher.instance : Lnet/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher;
        //   1288: invokevirtual getFontRenderer : ()Lnet/minecraft/client/gui/FontRenderer;
        //   1291: astore #24
        //   1293: iload #21
        //   1295: ifeq -> 1304
        //   1298: invokestatic endEntities : ()V
        //   1301: invokestatic beginBlockEntities : ()V
        //   1304: aload_0
        //   1305: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
        //   1308: getfield theProfiler : Lnet/minecraft/profiler/Profiler;
        //   1311: ldc_w 'blockentities'
        //   1314: invokevirtual endStartSection : (Ljava/lang/String;)V
        //   1317: invokestatic enableStandardItemLighting : ()V
        //   1320: getstatic net/minecraft/src/Reflector.ForgeTileEntityRendererDispatcher_preDrawBatch : Lnet/minecraft/src/ReflectorMethod;
        //   1323: invokevirtual exists : ()Z
        //   1326: ifeq -> 1343
        //   1329: getstatic net/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher.instance : Lnet/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher;
        //   1332: getstatic net/minecraft/src/Reflector.ForgeTileEntityRendererDispatcher_preDrawBatch : Lnet/minecraft/src/ReflectorMethod;
        //   1335: iconst_0
        //   1336: anewarray java/lang/Object
        //   1339: invokestatic call : (Ljava/lang/Object;Lnet/minecraft/src/ReflectorMethod;[Ljava/lang/Object;)Ljava/lang/Object;
        //   1342: pop
        //   1343: aload_0
        //   1344: getfield renderInfosTileEntities : Ljava/util/List;
        //   1347: invokeinterface iterator : ()Ljava/util/Iterator;
        //   1352: astore #26
        //   1354: goto -> 1592
        //   1357: aload #26
        //   1359: invokeinterface next : ()Ljava/lang/Object;
        //   1364: checkcast net/minecraft/client/renderer/RenderGlobal$ContainerLocalRenderInformation
        //   1367: astore #25
        //   1369: aload #25
        //   1371: getfield renderChunk : Lnet/minecraft/client/renderer/chunk/RenderChunk;
        //   1374: invokevirtual getCompiledChunk : ()Lnet/minecraft/client/renderer/chunk/CompiledChunk;
        //   1377: invokevirtual getTileEntities : ()Ljava/util/List;
        //   1380: astore #27
        //   1382: aload #27
        //   1384: invokeinterface isEmpty : ()Z
        //   1389: ifne -> 1592
        //   1392: aload #27
        //   1394: invokeinterface iterator : ()Ljava/util/Iterator;
        //   1399: astore #28
        //   1401: aload #28
        //   1403: invokeinterface hasNext : ()Z
        //   1408: ifne -> 1414
        //   1411: goto -> 1592
        //   1414: aload #28
        //   1416: invokeinterface next : ()Ljava/lang/Object;
        //   1421: checkcast net/minecraft/tileentity/TileEntity
        //   1424: astore #29
        //   1426: iload #20
        //   1428: ifne -> 1434
        //   1431: goto -> 1490
        //   1434: aload #29
        //   1436: getstatic net/minecraft/src/Reflector.ForgeTileEntity_shouldRenderInPass : Lnet/minecraft/src/ReflectorMethod;
        //   1439: iconst_1
        //   1440: anewarray java/lang/Object
        //   1443: dup
        //   1444: iconst_0
        //   1445: iload #4
        //   1447: invokestatic valueOf : (I)Ljava/lang/Integer;
        //   1450: aastore
        //   1451: invokestatic callBoolean : (Ljava/lang/Object;Lnet/minecraft/src/ReflectorMethod;[Ljava/lang/Object;)Z
        //   1454: ifeq -> 1401
        //   1457: aload #29
        //   1459: getstatic net/minecraft/src/Reflector.ForgeTileEntity_getRenderBoundingBox : Lnet/minecraft/src/ReflectorMethod;
        //   1462: iconst_0
        //   1463: anewarray java/lang/Object
        //   1466: invokestatic call : (Ljava/lang/Object;Lnet/minecraft/src/ReflectorMethod;[Ljava/lang/Object;)Ljava/lang/Object;
        //   1469: checkcast net/minecraft/util/AxisAlignedBB
        //   1472: astore #30
        //   1474: aload #30
        //   1476: ifnull -> 1490
        //   1479: aload_2
        //   1480: aload #30
        //   1482: invokeinterface isBoundingBoxInFrustum : (Lnet/minecraft/util/AxisAlignedBB;)Z
        //   1487: ifeq -> 1401
        //   1490: aload #29
        //   1492: invokevirtual getClass : ()Ljava/lang/Class;
        //   1495: astore #30
        //   1497: aload #30
        //   1499: ldc_w net/minecraft/tileentity/TileEntitySign
        //   1502: if_acmpne -> 1553
        //   1505: getstatic net/minecraft/src/Config.zoomMode : Z
        //   1508: ifne -> 1553
        //   1511: getstatic net/minecraft/client/Minecraft.thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
        //   1514: astore #31
        //   1516: aload #29
        //   1518: aload #31
        //   1520: getfield posX : D
        //   1523: aload #31
        //   1525: getfield posY : D
        //   1528: aload #31
        //   1530: getfield posZ : D
        //   1533: invokevirtual getDistanceSq : (DDD)D
        //   1536: dstore #32
        //   1538: dload #32
        //   1540: ldc2_w 256.0
        //   1543: dcmpl
        //   1544: ifle -> 1553
        //   1547: aload #24
        //   1549: iconst_0
        //   1550: putfield enabled : Z
        //   1553: iload #21
        //   1555: ifeq -> 1563
        //   1558: aload #29
        //   1560: invokestatic nextBlockEntity : (Lnet/minecraft/tileentity/TileEntity;)V
        //   1563: getstatic net/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher.instance : Lnet/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher;
        //   1566: aload #29
        //   1568: fload_3
        //   1569: iconst_m1
        //   1570: invokevirtual renderTileEntity : (Lnet/minecraft/tileentity/TileEntity;FI)V
        //   1573: aload_0
        //   1574: dup
        //   1575: getfield countTileEntitiesRendered : I
        //   1578: iconst_1
        //   1579: iadd
        //   1580: putfield countTileEntitiesRendered : I
        //   1583: aload #24
        //   1585: iconst_1
        //   1586: putfield enabled : Z
        //   1589: goto -> 1401
        //   1592: aload #26
        //   1594: invokeinterface hasNext : ()Z
        //   1599: ifne -> 1357
        //   1602: aload_0
        //   1603: getfield setTileEntities : Ljava/util/Set;
        //   1606: dup
        //   1607: astore #25
        //   1609: monitorenter
        //   1610: aload_0
        //   1611: getfield setTileEntities : Ljava/util/Set;
        //   1614: invokeinterface iterator : ()Ljava/util/Iterator;
        //   1619: astore #27
        //   1621: goto -> 1792
        //   1624: aload #27
        //   1626: invokeinterface next : ()Ljava/lang/Object;
        //   1631: checkcast net/minecraft/tileentity/TileEntity
        //   1634: astore #26
        //   1636: iload #20
        //   1638: ifeq -> 1703
        //   1641: aload #26
        //   1643: getstatic net/minecraft/src/Reflector.ForgeTileEntity_shouldRenderInPass : Lnet/minecraft/src/ReflectorMethod;
        //   1646: iconst_1
        //   1647: anewarray java/lang/Object
        //   1650: dup
        //   1651: iconst_0
        //   1652: iload #4
        //   1654: invokestatic valueOf : (I)Ljava/lang/Integer;
        //   1657: aastore
        //   1658: invokestatic callBoolean : (Ljava/lang/Object;Lnet/minecraft/src/ReflectorMethod;[Ljava/lang/Object;)Z
        //   1661: ifne -> 1667
        //   1664: goto -> 1792
        //   1667: aload #26
        //   1669: getstatic net/minecraft/src/Reflector.ForgeTileEntity_getRenderBoundingBox : Lnet/minecraft/src/ReflectorMethod;
        //   1672: iconst_0
        //   1673: anewarray java/lang/Object
        //   1676: invokestatic call : (Ljava/lang/Object;Lnet/minecraft/src/ReflectorMethod;[Ljava/lang/Object;)Ljava/lang/Object;
        //   1679: checkcast net/minecraft/util/AxisAlignedBB
        //   1682: astore #28
        //   1684: aload #28
        //   1686: ifnull -> 1703
        //   1689: aload_2
        //   1690: aload #28
        //   1692: invokeinterface isBoundingBoxInFrustum : (Lnet/minecraft/util/AxisAlignedBB;)Z
        //   1697: ifne -> 1703
        //   1700: goto -> 1792
        //   1703: aload #26
        //   1705: invokevirtual getClass : ()Ljava/lang/Class;
        //   1708: astore #28
        //   1710: aload #28
        //   1712: ldc_w net/minecraft/tileentity/TileEntitySign
        //   1715: if_acmpne -> 1766
        //   1718: getstatic net/minecraft/src/Config.zoomMode : Z
        //   1721: ifne -> 1766
        //   1724: getstatic net/minecraft/client/Minecraft.thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
        //   1727: astore #29
        //   1729: aload #26
        //   1731: aload #29
        //   1733: getfield posX : D
        //   1736: aload #29
        //   1738: getfield posY : D
        //   1741: aload #29
        //   1743: getfield posZ : D
        //   1746: invokevirtual getDistanceSq : (DDD)D
        //   1749: dstore #30
        //   1751: dload #30
        //   1753: ldc2_w 256.0
        //   1756: dcmpl
        //   1757: ifle -> 1766
        //   1760: aload #24
        //   1762: iconst_0
        //   1763: putfield enabled : Z
        //   1766: iload #21
        //   1768: ifeq -> 1776
        //   1771: aload #26
        //   1773: invokestatic nextBlockEntity : (Lnet/minecraft/tileentity/TileEntity;)V
        //   1776: getstatic net/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher.instance : Lnet/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher;
        //   1779: aload #26
        //   1781: fload_3
        //   1782: iconst_m1
        //   1783: invokevirtual renderTileEntity : (Lnet/minecraft/tileentity/TileEntity;FI)V
        //   1786: aload #24
        //   1788: iconst_1
        //   1789: putfield enabled : Z
        //   1792: aload #27
        //   1794: invokeinterface hasNext : ()Z
        //   1799: ifne -> 1624
        //   1802: aload #25
        //   1804: monitorexit
        //   1805: goto -> 1812
        //   1808: aload #25
        //   1810: monitorexit
        //   1811: athrow
        //   1812: getstatic net/minecraft/src/Reflector.ForgeTileEntityRendererDispatcher_drawBatch : Lnet/minecraft/src/ReflectorMethod;
        //   1815: invokevirtual exists : ()Z
        //   1818: ifeq -> 1843
        //   1821: getstatic net/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher.instance : Lnet/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher;
        //   1824: getstatic net/minecraft/src/Reflector.ForgeTileEntityRendererDispatcher_drawBatch : Lnet/minecraft/src/ReflectorMethod;
        //   1827: iconst_1
        //   1828: anewarray java/lang/Object
        //   1831: dup
        //   1832: iconst_0
        //   1833: iload #4
        //   1835: invokestatic valueOf : (I)Ljava/lang/Integer;
        //   1838: aastore
        //   1839: invokestatic call : (Ljava/lang/Object;Lnet/minecraft/src/ReflectorMethod;[Ljava/lang/Object;)Ljava/lang/Object;
        //   1842: pop
        //   1843: aload_0
        //   1844: invokespecial preRenderDamagedBlocks : ()V
        //   1847: aload_0
        //   1848: getfield damagedBlocks : Ljava/util/Map;
        //   1851: invokeinterface values : ()Ljava/util/Collection;
        //   1856: invokeinterface iterator : ()Ljava/util/Iterator;
        //   1861: astore #26
        //   1863: goto -> 2147
        //   1866: aload #26
        //   1868: invokeinterface next : ()Ljava/lang/Object;
        //   1873: checkcast net/minecraft/client/renderer/DestroyBlockProgress
        //   1876: astore #25
        //   1878: aload #25
        //   1880: invokevirtual getPosition : ()Lnet/minecraft/util/BlockPos;
        //   1883: astore #27
        //   1885: aload_0
        //   1886: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
        //   1889: aload #27
        //   1891: invokevirtual getTileEntity : (Lnet/minecraft/util/BlockPos;)Lnet/minecraft/tileentity/TileEntity;
        //   1894: astore #28
        //   1896: aload #28
        //   1898: instanceof net/minecraft/tileentity/TileEntityChest
        //   1901: ifeq -> 1972
        //   1904: aload #28
        //   1906: checkcast net/minecraft/tileentity/TileEntityChest
        //   1909: astore #29
        //   1911: aload #29
        //   1913: getfield adjacentChestXNeg : Lnet/minecraft/tileentity/TileEntityChest;
        //   1916: ifnull -> 1943
        //   1919: aload #27
        //   1921: getstatic net/minecraft/util/EnumFacing.WEST : Lnet/minecraft/util/EnumFacing;
        //   1924: invokevirtual offset : (Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/BlockPos;
        //   1927: astore #27
        //   1929: aload_0
        //   1930: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
        //   1933: aload #27
        //   1935: invokevirtual getTileEntity : (Lnet/minecraft/util/BlockPos;)Lnet/minecraft/tileentity/TileEntity;
        //   1938: astore #28
        //   1940: goto -> 1972
        //   1943: aload #29
        //   1945: getfield adjacentChestZNeg : Lnet/minecraft/tileentity/TileEntityChest;
        //   1948: ifnull -> 1972
        //   1951: aload #27
        //   1953: getstatic net/minecraft/util/EnumFacing.NORTH : Lnet/minecraft/util/EnumFacing;
        //   1956: invokevirtual offset : (Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/BlockPos;
        //   1959: astore #27
        //   1961: aload_0
        //   1962: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
        //   1965: aload #27
        //   1967: invokevirtual getTileEntity : (Lnet/minecraft/util/BlockPos;)Lnet/minecraft/tileentity/TileEntity;
        //   1970: astore #28
        //   1972: aload_0
        //   1973: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
        //   1976: aload #27
        //   1978: invokevirtual getBlockState : (Lnet/minecraft/util/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   1981: invokeinterface getBlock : ()Lnet/minecraft/block/Block;
        //   1986: astore #29
        //   1988: iload #20
        //   1990: ifeq -> 2074
        //   1993: iconst_0
        //   1994: istore #30
        //   1996: aload #28
        //   1998: ifnull -> 2118
        //   2001: aload #28
        //   2003: getstatic net/minecraft/src/Reflector.ForgeTileEntity_shouldRenderInPass : Lnet/minecraft/src/ReflectorMethod;
        //   2006: iconst_1
        //   2007: anewarray java/lang/Object
        //   2010: dup
        //   2011: iconst_0
        //   2012: iload #4
        //   2014: invokestatic valueOf : (I)Ljava/lang/Integer;
        //   2017: aastore
        //   2018: invokestatic callBoolean : (Ljava/lang/Object;Lnet/minecraft/src/ReflectorMethod;[Ljava/lang/Object;)Z
        //   2021: ifeq -> 2118
        //   2024: aload #28
        //   2026: getstatic net/minecraft/src/Reflector.ForgeTileEntity_canRenderBreaking : Lnet/minecraft/src/ReflectorMethod;
        //   2029: iconst_0
        //   2030: anewarray java/lang/Object
        //   2033: invokestatic callBoolean : (Ljava/lang/Object;Lnet/minecraft/src/ReflectorMethod;[Ljava/lang/Object;)Z
        //   2036: ifeq -> 2118
        //   2039: aload #28
        //   2041: getstatic net/minecraft/src/Reflector.ForgeTileEntity_getRenderBoundingBox : Lnet/minecraft/src/ReflectorMethod;
        //   2044: iconst_0
        //   2045: anewarray java/lang/Object
        //   2048: invokestatic call : (Ljava/lang/Object;Lnet/minecraft/src/ReflectorMethod;[Ljava/lang/Object;)Ljava/lang/Object;
        //   2051: checkcast net/minecraft/util/AxisAlignedBB
        //   2054: astore #31
        //   2056: aload #31
        //   2058: ifnull -> 2118
        //   2061: aload_2
        //   2062: aload #31
        //   2064: invokeinterface isBoundingBoxInFrustum : (Lnet/minecraft/util/AxisAlignedBB;)Z
        //   2069: istore #30
        //   2071: goto -> 2118
        //   2074: aload #28
        //   2076: ifnull -> 2115
        //   2079: aload #29
        //   2081: instanceof net/minecraft/block/BlockChest
        //   2084: ifne -> 2111
        //   2087: aload #29
        //   2089: instanceof net/minecraft/block/BlockEnderChest
        //   2092: ifne -> 2111
        //   2095: aload #29
        //   2097: instanceof net/minecraft/block/BlockSign
        //   2100: ifne -> 2111
        //   2103: aload #29
        //   2105: instanceof net/minecraft/block/BlockSkull
        //   2108: ifeq -> 2115
        //   2111: iconst_1
        //   2112: goto -> 2116
        //   2115: iconst_0
        //   2116: istore #30
        //   2118: iload #30
        //   2120: ifeq -> 2147
        //   2123: iload #21
        //   2125: ifeq -> 2133
        //   2128: aload #28
        //   2130: invokestatic nextBlockEntity : (Lnet/minecraft/tileentity/TileEntity;)V
        //   2133: getstatic net/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher.instance : Lnet/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher;
        //   2136: aload #28
        //   2138: fload_3
        //   2139: aload #25
        //   2141: invokevirtual getPartialBlockDamage : ()I
        //   2144: invokevirtual renderTileEntity : (Lnet/minecraft/tileentity/TileEntity;FI)V
        //   2147: aload #26
        //   2149: invokeinterface hasNext : ()Z
        //   2154: ifne -> 1866
        //   2157: aload_0
        //   2158: invokespecial postRenderDamagedBlocks : ()V
        //   2161: aload_0
        //   2162: getfield mc : Lnet/minecraft/client/Minecraft;
        //   2165: getfield entityRenderer : Lnet/minecraft/client/renderer/EntityRenderer;
        //   2168: invokevirtual disableLightmap : ()V
        //   2171: aload_0
        //   2172: getfield mc : Lnet/minecraft/client/Minecraft;
        //   2175: getfield mcProfiler : Lnet/minecraft/profiler/Profiler;
        //   2178: invokevirtual endSection : ()V
        //   2181: return
        // Line number table:
        //   Java source line number -> byte code offset
        //   #622	-> 0
        //   #624	-> 3
        //   #626	-> 12
        //   #629	-> 24
        //   #631	-> 31
        //   #633	-> 36
        //   #636	-> 37
        //   #637	-> 47
        //   #640	-> 50
        //   #641	-> 69
        //   #642	-> 88
        //   #643	-> 107
        //   #644	-> 120
        //   #645	-> 152
        //   #647	-> 192
        //   #649	-> 197
        //   #650	-> 202
        //   #651	-> 207
        //   #652	-> 212
        //   #655	-> 217
        //   #656	-> 226
        //   #657	-> 248
        //   #658	-> 270
        //   #659	-> 292
        //   #660	-> 297
        //   #661	-> 302
        //   #662	-> 307
        //   #663	-> 320
        //   #664	-> 330
        //   #665	-> 343
        //   #667	-> 352
        //   #669	-> 357
        //   #672	-> 368
        //   #674	-> 387
        //   #677	-> 390
        //   #678	-> 398
        //   #680	-> 406
        //   #682	-> 412
        //   #684	-> 431
        //   #686	-> 459
        //   #688	-> 469
        //   #690	-> 483
        //   #680	-> 494
        //   #695	-> 514
        //   #697	-> 521
        //   #698	-> 527
        //   #699	-> 530
        //   #700	-> 537
        //   #701	-> 545
        //   #702	-> 558
        //   #703	-> 561
        //   #705	-> 569
        //   #707	-> 575
        //   #709	-> 589
        //   #711	-> 617
        //   #712	-> 653
        //   #714	-> 715
        //   #716	-> 750
        //   #705	-> 761
        //   #721	-> 776
        //   #722	-> 784
        //   #723	-> 787
        //   #724	-> 791
        //   #725	-> 799
        //   #726	-> 802
        //   #727	-> 806
        //   #728	-> 817
        //   #729	-> 820
        //   #730	-> 823
        //   #731	-> 826
        //   #732	-> 832
        //   #733	-> 835
        //   #736	-> 838
        //   #737	-> 851
        //   #739	-> 856
        //   #741	-> 861
        //   #744	-> 864
        //   #745	-> 875
        //   #746	-> 887
        //   #749	-> 900
        //   #751	-> 903
        //   #752	-> 915
        //   #753	-> 925
        //   #755	-> 947
        //   #757	-> 955
        //   #766	-> 962
        //   #768	-> 972
        //   #771	-> 975
        //   #773	-> 987
        //   #775	-> 1015
        //   #777	-> 1052
        //   #779	-> 1057
        //   #782	-> 1060
        //   #784	-> 1092
        //   #786	-> 1163
        //   #788	-> 1173
        //   #790	-> 1184
        //   #793	-> 1192
        //   #795	-> 1198
        //   #797	-> 1203
        //   #800	-> 1208
        //   #801	-> 1219
        //   #807	-> 1224
        //   #809	-> 1237
        //   #811	-> 1242
        //   #814	-> 1247
        //   #759	-> 1260
        //   #749	-> 1263
        //   #820	-> 1273
        //   #821	-> 1285
        //   #823	-> 1293
        //   #825	-> 1298
        //   #826	-> 1301
        //   #829	-> 1304
        //   #830	-> 1317
        //   #832	-> 1320
        //   #834	-> 1329
        //   #839	-> 1343
        //   #841	-> 1369
        //   #843	-> 1382
        //   #845	-> 1392
        //   #853	-> 1401
        //   #855	-> 1411
        //   #858	-> 1414
        //   #860	-> 1426
        //   #862	-> 1431
        //   #865	-> 1434
        //   #867	-> 1457
        //   #869	-> 1474
        //   #876	-> 1490
        //   #878	-> 1497
        //   #880	-> 1511
        //   #881	-> 1516
        //   #883	-> 1538
        //   #885	-> 1547
        //   #889	-> 1553
        //   #891	-> 1558
        //   #894	-> 1563
        //   #895	-> 1573
        //   #896	-> 1583
        //   #847	-> 1589
        //   #839	-> 1592
        //   #901	-> 1602
        //   #903	-> 1610
        //   #905	-> 1636
        //   #907	-> 1641
        //   #909	-> 1664
        //   #911	-> 1667
        //   #913	-> 1684
        //   #915	-> 1700
        //   #919	-> 1703
        //   #921	-> 1710
        //   #923	-> 1724
        //   #924	-> 1729
        //   #926	-> 1751
        //   #928	-> 1760
        //   #932	-> 1766
        //   #934	-> 1771
        //   #937	-> 1776
        //   #938	-> 1786
        //   #903	-> 1792
        //   #901	-> 1802
        //   #942	-> 1812
        //   #944	-> 1821
        //   #947	-> 1843
        //   #949	-> 1847
        //   #951	-> 1878
        //   #952	-> 1885
        //   #954	-> 1896
        //   #956	-> 1904
        //   #958	-> 1911
        //   #960	-> 1919
        //   #961	-> 1929
        //   #962	-> 1940
        //   #963	-> 1943
        //   #965	-> 1951
        //   #966	-> 1961
        //   #970	-> 1972
        //   #973	-> 1988
        //   #975	-> 1993
        //   #977	-> 1996
        //   #979	-> 2039
        //   #981	-> 2056
        //   #983	-> 2061
        //   #986	-> 2071
        //   #989	-> 2074
        //   #992	-> 2118
        //   #994	-> 2123
        //   #996	-> 2128
        //   #999	-> 2133
        //   #949	-> 2147
        //   #1003	-> 2157
        //   #1004	-> 2161
        //   #1005	-> 2171
        //   #1007	-> 2181
        // Local variable table:
        //   start	length	slot	name	descriptor
        //   0	2182	0	this	Lnet/minecraft/client/renderer/RenderGlobal;
        //   0	2182	1	renderViewEntity	Lnet/minecraft/entity/Entity;
        //   0	2182	2	camera	Lnet/minecraft/client/renderer/culling/ICamera;
        //   0	2182	3	partialTicks	F
        //   3	2179	4	i	I
        //   69	2112	5	d0	D
        //   88	2093	7	d1	D
        //   107	2074	9	d2	D
        //   226	1955	11	entity	Lnet/minecraft/entity/Entity;
        //   248	1933	12	d3	D
        //   270	1911	14	d4	D
        //   292	1889	16	d5	D
        //   352	1829	18	list	Ljava/util/List;
        //   398	1783	19	flag	Z
        //   406	1775	20	flag1	Z
        //   409	105	21	j	I
        //   431	63	22	entity1	Lnet/minecraft/entity/Entity;
        //   572	204	21	k	I
        //   589	172	22	entity3	Lnet/minecraft/entity/Entity;
        //   653	108	23	flag2	Z
        //   715	46	24	flag3	Z
        //   856	1325	21	flag7	Z
        //   875	1306	22	iterator1	Ljava/util/Iterator;
        //   887	1294	23	flag4	Z
        //   915	348	24	renderglobal$containerlocalrenderinformation	Lnet/minecraft/client/renderer/RenderGlobal$ContainerLocalRenderInformation;
        //   925	338	25	chunk	Lnet/minecraft/world/chunk/Chunk;
        //   947	316	26	classinheritancemultimap	Lnet/minecraft/util/ClassInheritanceMultiMap;
        //   962	301	27	iterator	Ljava/util/Iterator;
        //   987	273	28	entity2	Lnet/minecraft/entity/Entity;
        //   1052	208	29	flag5	Z
        //   1092	132	30	flag6	Z
        //   1293	888	24	fontrenderer	Lnet/minecraft/client/gui/FontRenderer;
        //   1369	223	25	renderglobal$containerlocalrenderinformation1	Lnet/minecraft/client/renderer/RenderGlobal$ContainerLocalRenderInformation;
        //   1382	210	27	list1	Ljava/util/List;
        //   1401	191	28	iterator2	Ljava/util/Iterator;
        //   1426	163	29	tileentity	Lnet/minecraft/tileentity/TileEntity;
        //   1474	16	30	axisalignedbb	Lnet/minecraft/util/AxisAlignedBB;
        //   1497	92	30	oclass	Ljava/lang/Class;
        //   1516	37	31	entityplayer	Lnet/minecraft/entity/player/EntityPlayer;
        //   1538	15	32	d6	D
        //   1636	156	26	tileentity1	Lnet/minecraft/tileentity/TileEntity;
        //   1684	19	28	axisalignedbb1	Lnet/minecraft/util/AxisAlignedBB;
        //   1710	82	28	oclass1	Ljava/lang/Class;
        //   1729	37	29	entityplayer1	Lnet/minecraft/entity/player/EntityPlayer;
        //   1751	15	30	d7	D
        //   1878	269	25	destroyblockprogress	Lnet/minecraft/client/renderer/DestroyBlockProgress;
        //   1885	262	27	blockpos	Lnet/minecraft/util/BlockPos;
        //   1896	251	28	tileentity2	Lnet/minecraft/tileentity/TileEntity;
        //   1911	61	29	tileentitychest	Lnet/minecraft/tileentity/TileEntityChest;
        //   1988	159	29	block	Lnet/minecraft/block/Block;
        //   1996	78	30	flag8	Z
        //   2118	29	30	flag8	Z
        //   2056	15	31	axisalignedbb2	Lnet/minecraft/util/AxisAlignedBB;
        // Exception table:
        //   from	to	target	type
        //   1610	1805	1808	finally
        //   1808	1811	1808	finally
    }
    
    public String getDebugInfoRenders() {
        int i = this.viewFrustum.renderChunks.length;
        int j = 0;
        for (ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation : this.renderInfos) {
            CompiledChunk compiledchunk = renderglobal$containerlocalrenderinformation.renderChunk.compiledChunk;
            if (compiledchunk != CompiledChunk.DUMMY && !compiledchunk.isEmpty())
                j++; 
        } 
        return String.format("C: %d/%d %sD: %d, %s", new Object[] { Integer.valueOf(j), Integer.valueOf(i), this.mc.renderChunksMany ? "(s) " : "", Integer.valueOf(this.renderDistanceChunks), this.renderDispatcher.getDebugInfo() });
    }
    
    public String getDebugInfoEntities() {
        return "E: " + this.countEntitiesRendered + "/" + this.countEntitiesTotal + ", B: " + this.countEntitiesHidden + ", I: " + (this.countEntitiesTotal - this.countEntitiesHidden - this.countEntitiesRendered) + ", " + Config.getVersionDebug();
    }
    
    public void setupTerrain(Entity viewEntity, double partialTicks, ICamera camera, int frameCount, boolean playerSpectator) {
        Frustum frustum;
        if (this.mc.gameSettings.renderDistanceChunks != this.renderDistanceChunks)
            loadRenderers(); 
        this.theWorld.theProfiler.startSection("camera");
        double d0 = viewEntity.posX - this.frustumUpdatePosX;
        double d1 = viewEntity.posY - this.frustumUpdatePosY;
        double d2 = viewEntity.posZ - this.frustumUpdatePosZ;
        if (this.frustumUpdatePosChunkX != viewEntity.chunkCoordX || this.frustumUpdatePosChunkY != viewEntity.chunkCoordY || this.frustumUpdatePosChunkZ != viewEntity.chunkCoordZ || d0 * d0 + d1 * d1 + d2 * d2 > 16.0D) {
            this.frustumUpdatePosX = viewEntity.posX;
            this.frustumUpdatePosY = viewEntity.posY;
            this.frustumUpdatePosZ = viewEntity.posZ;
            this.frustumUpdatePosChunkX = viewEntity.chunkCoordX;
            this.frustumUpdatePosChunkY = viewEntity.chunkCoordY;
            this.frustumUpdatePosChunkZ = viewEntity.chunkCoordZ;
            this.viewFrustum.updateChunkPositions(viewEntity.posX, viewEntity.posZ);
        } 
        if (Config.isDynamicLights())
            DynamicLights.update(this); 
        this.theWorld.theProfiler.endStartSection("renderlistcamera");
        double d3 = viewEntity.lastTickPosX + (viewEntity.posX - viewEntity.lastTickPosX) * partialTicks;
        double d4 = viewEntity.lastTickPosY + (viewEntity.posY - viewEntity.lastTickPosY) * partialTicks;
        double d5 = viewEntity.lastTickPosZ + (viewEntity.posZ - viewEntity.lastTickPosZ) * partialTicks;
        this.renderContainer.initialize(d3, d4, d5);
        this.theWorld.theProfiler.endStartSection("cull");
        if (this.debugFixedClippingHelper != null) {
            Frustum frustum1 = new Frustum(this.debugFixedClippingHelper);
            frustum1.setPosition(this.debugTerrainFrustumPosition.field_181059_a, this.debugTerrainFrustumPosition.field_181060_b, this.debugTerrainFrustumPosition.field_181061_c);
            frustum = frustum1;
        } 
        this.mc.mcProfiler.endStartSection("culling");
        BlockPos blockpos2 = new BlockPos(d3, d4 + viewEntity.getEyeHeight(), d5);
        RenderChunk renderchunk = this.viewFrustum.getRenderChunk(blockpos2);
        BlockPos blockpos = new BlockPos(MathHelper.floor_double(d3 / 16.0D) * 16, MathHelper.floor_double(d4 / 16.0D) * 16, MathHelper.floor_double(d5 / 16.0D) * 16);
        this.displayListEntitiesDirty = !(!this.displayListEntitiesDirty && this.chunksToUpdate.isEmpty() && viewEntity.posX == this.lastViewEntityX && viewEntity.posY == this.lastViewEntityY && viewEntity.posZ == this.lastViewEntityZ && viewEntity.rotationPitch == this.lastViewEntityPitch && viewEntity.rotationYaw == this.lastViewEntityYaw);
        this.lastViewEntityX = viewEntity.posX;
        this.lastViewEntityY = viewEntity.posY;
        this.lastViewEntityZ = viewEntity.posZ;
        this.lastViewEntityPitch = viewEntity.rotationPitch;
        this.lastViewEntityYaw = viewEntity.rotationYaw;
        boolean flag = (this.debugFixedClippingHelper != null);
        Lagometer.timerVisibility.start();
        if (Shaders.isShadowPass) {
            this.renderInfos = this.renderInfosShadow;
            this.renderInfosEntities = this.renderInfosEntitiesShadow;
            this.renderInfosTileEntities = this.renderInfosTileEntitiesShadow;
            if (!flag && this.displayListEntitiesDirty) {
                this.renderInfos.clear();
                this.renderInfosEntities.clear();
                this.renderInfosTileEntities.clear();
                RenderInfoLazy renderinfolazy = new RenderInfoLazy();
                Iterator<RenderChunk> iterator = ShadowUtils.makeShadowChunkIterator(this.theWorld, partialTicks, viewEntity, this.renderDistanceChunks, this.viewFrustum);
                while (iterator.hasNext()) {
                    RenderChunk renderchunk1 = iterator.next();
                    if (renderchunk1 != null) {
                        renderinfolazy.setRenderChunk(renderchunk1);
                        if (!renderchunk1.compiledChunk.isEmpty() || renderchunk1.isNeedsUpdate())
                            this.renderInfos.add(renderinfolazy.getRenderInfo()); 
                        BlockPos blockpos1 = renderchunk1.getPosition();
                        if (ChunkUtils.hasEntities(this.theWorld.getChunkFromBlockCoords(blockpos1)))
                            this.renderInfosEntities.add(renderinfolazy.getRenderInfo()); 
                        if (renderchunk1.getCompiledChunk().getTileEntities().size() > 0)
                            this.renderInfosTileEntities.add(renderinfolazy.getRenderInfo()); 
                    } 
                } 
            } 
        } else {
            this.renderInfos = this.renderInfosNormal;
            this.renderInfosEntities = this.renderInfosEntitiesNormal;
            this.renderInfosTileEntities = this.renderInfosTileEntitiesNormal;
        } 
        if (!flag && this.displayListEntitiesDirty && !Shaders.isShadowPass) {
            this.displayListEntitiesDirty = false;
            this.renderInfos.clear();
            this.renderInfosEntities.clear();
            this.renderInfosTileEntities.clear();
            this.visibilityDeque.clear();
            Deque<ContainerLocalRenderInformation> deque = this.visibilityDeque;
            boolean flag1 = this.mc.renderChunksMany;
            if (renderchunk != null) {
                boolean flag2 = false;
                ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation3 = new ContainerLocalRenderInformation(renderchunk, null, 0, null);
                Set set1 = SET_ALL_FACINGS;
                if (set1.size() == 1) {
                    Vector3f vector3f = getViewVector(viewEntity, partialTicks);
                    EnumFacing enumfacing = EnumFacing.getFacingFromVector(vector3f.x, vector3f.y, vector3f.z).getOpposite();
                    set1.remove(enumfacing);
                } 
                if (set1.isEmpty())
                    flag2 = true; 
                if (flag2 && !playerSpectator) {
                    this.renderInfos.add(renderglobal$containerlocalrenderinformation3);
                } else {
                    if (playerSpectator && this.theWorld.getBlockState(blockpos2).getBlock().isOpaqueCube())
                        flag1 = false; 
                    renderchunk.setFrameIndex(frameCount);
                    deque.add(renderglobal$containerlocalrenderinformation3);
                } 
            } else {
                int i = (blockpos2.getY() > 0) ? 248 : 8;
                for (int j = -this.renderDistanceChunks; j <= this.renderDistanceChunks; j++) {
                    for (int k = -this.renderDistanceChunks; k <= this.renderDistanceChunks; k++) {
                        RenderChunk renderchunk2 = this.viewFrustum.getRenderChunk(new BlockPos((j << 4) + 8, i, (k << 4) + 8));
                        if (renderchunk2 != null && frustum.isBoundingBoxInFrustum(renderchunk2.boundingBox)) {
                            renderchunk2.setFrameIndex(frameCount);
                            deque.add(new ContainerLocalRenderInformation(renderchunk2, null, 0, null));
                        } 
                    } 
                } 
            } 
            EnumFacing[] aenumfacing = EnumFacing.VALUES;
            int l = aenumfacing.length;
            while (!deque.isEmpty()) {
                ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation1 = deque.poll();
                RenderChunk renderchunk4 = renderglobal$containerlocalrenderinformation1.renderChunk;
                EnumFacing enumfacing2 = renderglobal$containerlocalrenderinformation1.facing;
                BlockPos blockpos3 = renderchunk4.getPosition();
                if (!renderchunk4.compiledChunk.isEmpty() || renderchunk4.isNeedsUpdate())
                    this.renderInfos.add(renderglobal$containerlocalrenderinformation1); 
                if (ChunkUtils.hasEntities(renderchunk4.getChunk()))
                    this.renderInfosEntities.add(renderglobal$containerlocalrenderinformation1); 
                if (renderchunk4.getCompiledChunk().getTileEntities().size() > 0)
                    this.renderInfosTileEntities.add(renderglobal$containerlocalrenderinformation1); 
                for (int i1 = 0; i1 < l; i1++) {
                    EnumFacing enumfacing1 = aenumfacing[i1];
                    if ((!flag1 || !renderglobal$containerlocalrenderinformation1.setFacing.contains(enumfacing1.getOpposite())) && (!flag1 || enumfacing2 == null || renderchunk4.getCompiledChunk().isVisible(enumfacing2.getOpposite(), enumfacing1))) {
                        RenderChunk renderchunk3 = func_181562_a(blockpos2, renderchunk4, enumfacing1);
                        if (renderchunk3 != null && renderchunk3.setFrameIndex(frameCount) && frustum.isBoundingBoxInFrustum(renderchunk3.boundingBox)) {
                            ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation = new ContainerLocalRenderInformation(renderchunk3, enumfacing1, renderglobal$containerlocalrenderinformation1.counter + 1, null);
                            renderglobal$containerlocalrenderinformation.setFacing.addAll(renderglobal$containerlocalrenderinformation1.setFacing);
                            renderglobal$containerlocalrenderinformation.setFacing.add(enumfacing1);
                            deque.add(renderglobal$containerlocalrenderinformation);
                        } 
                    } 
                } 
            } 
        } 
        if (this.debugFixTerrainFrustum) {
            fixTerrainFrustum(d3, d4, d5);
            this.debugFixTerrainFrustum = false;
        } 
        Lagometer.timerVisibility.end();
        if (Shaders.isShadowPass) {
            Shaders.mcProfilerEndSection();
        } else {
            this.renderDispatcher.clearChunkUpdates();
            Set<RenderChunk> set = this.chunksToUpdate;
            this.chunksToUpdate = Sets.newLinkedHashSet();
            Iterator<ContainerLocalRenderInformation> iterator1 = this.renderInfos.iterator();
            Lagometer.timerChunkUpdate.start();
            while (iterator1.hasNext()) {
                ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation2 = iterator1.next();
                RenderChunk renderchunk5 = renderglobal$containerlocalrenderinformation2.renderChunk;
                if (renderchunk5.isNeedsUpdate() || set.contains(renderchunk5)) {
                    this.displayListEntitiesDirty = true;
                    if (isPositionInRenderChunk(blockpos, renderglobal$containerlocalrenderinformation2.renderChunk)) {
                        if (!renderchunk5.isPlayerUpdate()) {
                            this.chunksToUpdateForced.add(renderchunk5);
                            continue;
                        } 
                        this.mc.mcProfiler.startSection("build near");
                        this.renderDispatcher.updateChunkNow(renderchunk5);
                        renderchunk5.setNeedsUpdate(false);
                        this.mc.mcProfiler.endSection();
                        continue;
                    } 
                    this.chunksToUpdate.add(renderchunk5);
                } 
            } 
            Lagometer.timerChunkUpdate.end();
            this.chunksToUpdate.addAll(set);
            this.mc.mcProfiler.endSection();
        } 
    }
    
    private boolean isPositionInRenderChunk(BlockPos pos, RenderChunk renderChunkIn) {
        BlockPos blockpos = renderChunkIn.getPosition();
        return (MathHelper.abs_int(pos.getX() - blockpos.getX()) > 16) ? false : ((MathHelper.abs_int(pos.getY() - blockpos.getY()) > 16) ? false : ((MathHelper.abs_int(pos.getZ() - blockpos.getZ()) <= 16)));
    }
    
    private Set getVisibleFacings(BlockPos pos) {
        VisGraph visgraph = new VisGraph();
        BlockPos blockpos = new BlockPos(pos.getX() >> 4 << 4, pos.getY() >> 4 << 4, pos.getZ() >> 4 << 4);
        Chunk chunk = this.theWorld.getChunkFromBlockCoords(blockpos);
        for (BlockPos.MutableBlockPos blockpos$mutableblockpos : BlockPos.getAllInBoxMutable(blockpos, blockpos.add(15, 15, 15))) {
            if (chunk.getBlock((BlockPos)blockpos$mutableblockpos).isOpaqueCube())
                visgraph.func_178606_a((BlockPos)blockpos$mutableblockpos); 
        } 
        return visgraph.func_178609_b(pos);
    }
    
    private RenderChunk func_181562_a(BlockPos p_181562_1_, RenderChunk p_181562_2_, EnumFacing p_181562_3_) {
        BlockPos blockpos = p_181562_2_.getPositionOffset16(p_181562_3_);
        if (blockpos.getY() >= 0 && blockpos.getY() < 256) {
            int i = MathHelper.abs_int(p_181562_1_.getX() - blockpos.getX());
            int j = MathHelper.abs_int(p_181562_1_.getZ() - blockpos.getZ());
            if (Config.isFogOff()) {
                if (i > this.renderDistance || j > this.renderDistance)
                    return null; 
            } else {
                int k = i * i + j * j;
                if (k > this.renderDistanceSq)
                    return null; 
            } 
            return this.viewFrustum.getRenderChunk(blockpos);
        } 
        return null;
    }
    
    private void fixTerrainFrustum(double x, double y, double z) {
        this.debugFixedClippingHelper = (ClippingHelper)new ClippingHelperImpl();
        ((ClippingHelperImpl)this.debugFixedClippingHelper).init();
        Matrix4f matrix4f = new Matrix4f(this.debugFixedClippingHelper.modelviewMatrix);
        matrix4f.transpose();
        Matrix4f matrix4f1 = new Matrix4f(this.debugFixedClippingHelper.projectionMatrix);
        matrix4f1.transpose();
        Matrix4f matrix4f2 = new Matrix4f();
        Matrix4f.mul((Matrix4f)matrix4f1, (Matrix4f)matrix4f, (Matrix4f)matrix4f2);
        matrix4f2.invert();
        this.debugTerrainFrustumPosition.field_181059_a = x;
        this.debugTerrainFrustumPosition.field_181060_b = y;
        this.debugTerrainFrustumPosition.field_181061_c = z;
        this.debugTerrainMatrix[0] = new Vector4f(-1.0F, -1.0F, -1.0F, 1.0F);
        this.debugTerrainMatrix[1] = new Vector4f(1.0F, -1.0F, -1.0F, 1.0F);
        this.debugTerrainMatrix[2] = new Vector4f(1.0F, 1.0F, -1.0F, 1.0F);
        this.debugTerrainMatrix[3] = new Vector4f(-1.0F, 1.0F, -1.0F, 1.0F);
        this.debugTerrainMatrix[4] = new Vector4f(-1.0F, -1.0F, 1.0F, 1.0F);
        this.debugTerrainMatrix[5] = new Vector4f(1.0F, -1.0F, 1.0F, 1.0F);
        this.debugTerrainMatrix[6] = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.debugTerrainMatrix[7] = new Vector4f(-1.0F, 1.0F, 1.0F, 1.0F);
        for (int i = 0; i < 8; i++) {
            Matrix4f.transform((Matrix4f)matrix4f2, this.debugTerrainMatrix[i], this.debugTerrainMatrix[i]);
            (this.debugTerrainMatrix[i]).x /= (this.debugTerrainMatrix[i]).w;
            (this.debugTerrainMatrix[i]).y /= (this.debugTerrainMatrix[i]).w;
            (this.debugTerrainMatrix[i]).z /= (this.debugTerrainMatrix[i]).w;
            (this.debugTerrainMatrix[i]).w = 1.0F;
        } 
    }
    
    protected Vector3f getViewVector(Entity entityIn, double partialTicks) {
        float f = (float)(entityIn.prevRotationPitch + (entityIn.rotationPitch - entityIn.prevRotationPitch) * partialTicks);
        float f1 = (float)(entityIn.prevRotationYaw + (entityIn.rotationYaw - entityIn.prevRotationYaw) * partialTicks);
        if ((Minecraft.getMinecraft()).gameSettings.thirdPersonView == 2)
            f += 180.0F; 
        float f2 = MathHelper.cos(-f1 * 0.017453292F - 3.1415927F);
        float f3 = MathHelper.sin(-f1 * 0.017453292F - 3.1415927F);
        float f4 = -MathHelper.cos(-f * 0.017453292F);
        float f5 = MathHelper.sin(-f * 0.017453292F);
        return new Vector3f(f3 * f4, f5, f2 * f4);
    }
    
    public int renderBlockLayer(EnumWorldBlockLayer blockLayerIn, double partialTicks, int pass, Entity entityIn) {
        RenderHelper.disableStandardItemLighting();
        if (blockLayerIn == EnumWorldBlockLayer.TRANSLUCENT) {
            this.mc.mcProfiler.startSection("translucent_sort");
            double d0 = entityIn.posX - this.prevRenderSortX;
            double d1 = entityIn.posY - this.prevRenderSortY;
            double d2 = entityIn.posZ - this.prevRenderSortZ;
            if (d0 * d0 + d1 * d1 + d2 * d2 > 1.0D) {
                this.prevRenderSortX = entityIn.posX;
                this.prevRenderSortY = entityIn.posY;
                this.prevRenderSortZ = entityIn.posZ;
                int k = 0;
                Iterator<ContainerLocalRenderInformation> iterator = this.renderInfos.iterator();
                this.chunksToResortTransparency.clear();
                while (iterator.hasNext()) {
                    ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation = iterator.next();
                    if (renderglobal$containerlocalrenderinformation.renderChunk.compiledChunk.isLayerStarted(blockLayerIn) && k++ < 15)
                        this.chunksToResortTransparency.add(renderglobal$containerlocalrenderinformation.renderChunk); 
                } 
            } 
            this.mc.mcProfiler.endSection();
        } 
        this.mc.mcProfiler.startSection("filterempty");
        int l = 0;
        boolean flag = (blockLayerIn == EnumWorldBlockLayer.TRANSLUCENT);
        int i1 = flag ? (this.renderInfos.size() - 1) : 0;
        int i = flag ? -1 : this.renderInfos.size();
        int j1 = flag ? -1 : 1;
        for (int j = i1; j != i; j += j1) {
            RenderChunk renderchunk = ((ContainerLocalRenderInformation)this.renderInfos.get(j)).renderChunk;
            if (!renderchunk.getCompiledChunk().isLayerEmpty(blockLayerIn)) {
                l++;
                this.renderContainer.addRenderChunk(renderchunk, blockLayerIn);
            } 
        } 
        if (l == 0) {
            this.mc.mcProfiler.endSection();
            return l;
        } 
        if (Config.isFogOff() && this.mc.entityRenderer.fogStandard)
            GlStateManager.disableFog(); 
        this.mc.mcProfiler.endStartSection("render_" + blockLayerIn);
        renderBlockLayer(blockLayerIn);
        this.mc.mcProfiler.endSection();
        return l;
    }
    
    private void renderBlockLayer(EnumWorldBlockLayer blockLayerIn) {
        this.mc.entityRenderer.enableLightmap();
        if (OpenGlHelper.useVbo()) {
            GL11.glEnableClientState(32884);
            OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
            GL11.glEnableClientState(32888);
            OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
            GL11.glEnableClientState(32888);
            OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
            GL11.glEnableClientState(32886);
        } 
        if (Config.isShaders())
            ShadersRender.preRenderChunkLayer(blockLayerIn); 
        this.renderContainer.renderChunkLayer(blockLayerIn);
        if (Config.isShaders())
            ShadersRender.postRenderChunkLayer(blockLayerIn); 
        if (OpenGlHelper.useVbo())
            for (VertexFormatElement vertexformatelement : DefaultVertexFormats.BLOCK.getElements()) {
                VertexFormatElement.EnumUsage vertexformatelement$enumusage = vertexformatelement.getUsage();
                int i = vertexformatelement.getIndex();
                switch (RenderGlobal$2.field_178037_a[vertexformatelement$enumusage.ordinal()]) {
                    case 1:
                        GL11.glDisableClientState(32884);
                    case 2:
                        OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit + i);
                        GL11.glDisableClientState(32888);
                        OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
                    case 3:
                        GL11.glDisableClientState(32886);
                        GlStateManager.resetColor();
                } 
            }  
        this.mc.entityRenderer.disableLightmap();
    }
    
    private void cleanupDamagedBlocks(Iterator<DestroyBlockProgress> iteratorIn) {
        while (iteratorIn.hasNext()) {
            DestroyBlockProgress destroyblockprogress = iteratorIn.next();
            int i = destroyblockprogress.getCreationCloudUpdateTick();
            if (this.cloudTickCounter - i > 400)
                iteratorIn.remove(); 
        } 
    }
    
    public void updateClouds() {
        if (Config.isShaders() && Keyboard.isKeyDown(61) && Keyboard.isKeyDown(19)) {
            Shaders.uninit();
            Shaders.loadShaderPack();
        } 
        this.cloudTickCounter++;
        if (this.cloudTickCounter % 20 == 0)
            cleanupDamagedBlocks(this.damagedBlocks.values().iterator()); 
    }
    
    private void renderSkyEnd() {
        if (Config.isSkyEnabled()) {
            GlStateManager.disableFog();
            GlStateManager.disableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.depthMask(false);
            this.renderEngine.bindTexture(locationEndSkyPng);
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();
            for (int i = 0; i < 6; i++) {
                GlStateManager.pushMatrix();
                if (i == 1)
                    GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F); 
                if (i == 2)
                    GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F); 
                if (i == 3)
                    GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F); 
                if (i == 4)
                    GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F); 
                if (i == 5)
                    GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F); 
                worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                worldrenderer.pos(-100.0D, -100.0D, -100.0D).tex(0.0D, 0.0D).color(40, 40, 40, 255).endVertex();
                worldrenderer.pos(-100.0D, -100.0D, 100.0D).tex(0.0D, 16.0D).color(40, 40, 40, 255).endVertex();
                worldrenderer.pos(100.0D, -100.0D, 100.0D).tex(16.0D, 16.0D).color(40, 40, 40, 255).endVertex();
                worldrenderer.pos(100.0D, -100.0D, -100.0D).tex(16.0D, 0.0D).color(40, 40, 40, 255).endVertex();
                tessellator.draw();
                GlStateManager.popMatrix();
            } 
            GlStateManager.depthMask(true);
            GlStateManager.enableTexture2D();
            GlStateManager.enableAlpha();
            GlStateManager.disableBlend();
        } 
    }
    
    public void renderSky(float partialTicks, int pass) {
        if (Reflector.ForgeWorldProvider_getSkyRenderer.exists()) {
            WorldProvider worldprovider = this.mc.theWorld.provider;
            Object object = Reflector.call(worldprovider, Reflector.ForgeWorldProvider_getSkyRenderer, new Object[0]);
            if (object != null) {
                Reflector.callVoid(object, Reflector.IRenderHandler_render, new Object[] { Float.valueOf(partialTicks), this.theWorld, this.mc });
                return;
            } 
        } 
        if (this.mc.theWorld.provider.getDimensionId() == 1) {
            renderSkyEnd();
        } else if (this.mc.theWorld.provider.isSurfaceWorld()) {
            GlStateManager.disableTexture2D();
            boolean flag1 = Config.isShaders();
            if (flag1)
                Shaders.disableTexture2D(); 
            Vec3 vec3 = this.theWorld.getSkyColor(this.mc.getRenderViewEntity(), partialTicks);
            vec3 = CustomColors.getSkyColor(vec3, (IBlockAccess)this.mc.theWorld, (this.mc.getRenderViewEntity()).posX, (this.mc.getRenderViewEntity()).posY + 1.0D, (this.mc.getRenderViewEntity()).posZ);
            if (flag1)
                Shaders.setSkyColor(vec3); 
            float f = (float)vec3.xCoord;
            float f1 = (float)vec3.yCoord;
            float f2 = (float)vec3.zCoord;
            if (pass != 2) {
                float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
                float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
                float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
                f = f3;
                f1 = f4;
                f2 = f5;
            } 
            GlStateManager.color(f, f1, f2);
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();
            GlStateManager.depthMask(false);
            GlStateManager.enableFog();
            if (flag1)
                Shaders.enableFog(); 
            GlStateManager.color(f, f1, f2);
            if (flag1)
                Shaders.preSkyList(); 
            if (Config.isSkyEnabled())
                if (this.vboEnabled) {
                    this.skyVBO.bindBuffer();
                    GL11.glEnableClientState(32884);
                    GL11.glVertexPointer(3, 5126, 12, 0L);
                    this.skyVBO.drawArrays(7);
                    this.skyVBO.unbindBuffer();
                    GL11.glDisableClientState(32884);
                } else {
                    GlStateManager.callList(this.glSkyList);
                }  
            GlStateManager.disableFog();
            if (flag1)
                Shaders.disableFog(); 
            GlStateManager.disableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            RenderHelper.disableStandardItemLighting();
            float[] afloat = this.theWorld.provider.calcSunriseSunsetColors(this.theWorld.getCelestialAngle(partialTicks), partialTicks);
            if (afloat != null && Config.isSunMoonEnabled()) {
                GlStateManager.disableTexture2D();
                if (flag1)
                    Shaders.disableTexture2D(); 
                GlStateManager.shadeModel(7425);
                GlStateManager.pushMatrix();
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate((MathHelper.sin(this.theWorld.getCelestialAngleRadians(partialTicks)) < 0.0F) ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                float f6 = afloat[0];
                float f7 = afloat[1];
                float f8 = afloat[2];
                if (pass != 2) {
                    float f9 = (f6 * 30.0F + f7 * 59.0F + f8 * 11.0F) / 100.0F;
                    float f10 = (f6 * 30.0F + f7 * 70.0F) / 100.0F;
                    float f11 = (f6 * 30.0F + f8 * 70.0F) / 100.0F;
                    f6 = f9;
                    f7 = f10;
                    f8 = f11;
                } 
                worldrenderer.begin(6, DefaultVertexFormats.POSITION_COLOR);
                worldrenderer.pos(0.0D, 100.0D, 0.0D).color(f6, f7, f8, afloat[3]).endVertex();
                boolean flag = true;
                for (int i = 0; i <= 16; i++) {
                    float f20 = i * 3.1415927F * 2.0F / 16.0F;
                    float f12 = MathHelper.sin(f20);
                    float f13 = MathHelper.cos(f20);
                    worldrenderer.pos((f12 * 120.0F), (f13 * 120.0F), (-f13 * 40.0F * afloat[3])).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
                } 
                tessellator.draw();
                GlStateManager.popMatrix();
                GlStateManager.shadeModel(7424);
            } 
            GlStateManager.enableTexture2D();
            if (flag1)
                Shaders.enableTexture2D(); 
            GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
            GlStateManager.pushMatrix();
            float f15 = 1.0F - this.theWorld.getRainStrength(partialTicks);
            GlStateManager.color(1.0F, 1.0F, 1.0F, f15);
            GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
            CustomSky.renderSky((World)this.theWorld, this.renderEngine, partialTicks);
            if (flag1)
                Shaders.preCelestialRotate(); 
            GlStateManager.rotate(this.theWorld.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
            if (flag1)
                Shaders.postCelestialRotate(); 
            float f16 = 30.0F;
            if (Config.isSunTexture()) {
                this.renderEngine.bindTexture(locationSunPng);
                worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
                worldrenderer.pos(-f16, 100.0D, -f16).tex(0.0D, 0.0D).endVertex();
                worldrenderer.pos(f16, 100.0D, -f16).tex(1.0D, 0.0D).endVertex();
                worldrenderer.pos(f16, 100.0D, f16).tex(1.0D, 1.0D).endVertex();
                worldrenderer.pos(-f16, 100.0D, f16).tex(0.0D, 1.0D).endVertex();
                tessellator.draw();
            } 
            f16 = 20.0F;
            if (Config.isMoonTexture()) {
                this.renderEngine.bindTexture(locationMoonPhasesPng);
                int l = this.theWorld.getMoonPhase();
                int j = l % 4;
                int k = l / 4 % 2;
                float f21 = (j + 0) / 4.0F;
                float f22 = (k + 0) / 2.0F;
                float f23 = (j + 1) / 4.0F;
                float f14 = (k + 1) / 2.0F;
                worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
                worldrenderer.pos(-f16, -100.0D, f16).tex(f23, f14).endVertex();
                worldrenderer.pos(f16, -100.0D, f16).tex(f21, f14).endVertex();
                worldrenderer.pos(f16, -100.0D, -f16).tex(f21, f22).endVertex();
                worldrenderer.pos(-f16, -100.0D, -f16).tex(f23, f22).endVertex();
                tessellator.draw();
            } 
            GlStateManager.disableTexture2D();
            if (flag1)
                Shaders.disableTexture2D(); 
            float f24 = this.theWorld.getStarBrightness(partialTicks) * f15;
            if (f24 > 0.0F && Config.isStarsEnabled() && !CustomSky.hasSkyLayers((World)this.theWorld)) {
                GlStateManager.color(f24, f24, f24, f24);
                if (this.vboEnabled) {
                    this.starVBO.bindBuffer();
                    GL11.glEnableClientState(32884);
                    GL11.glVertexPointer(3, 5126, 12, 0L);
                    this.starVBO.drawArrays(7);
                    this.starVBO.unbindBuffer();
                    GL11.glDisableClientState(32884);
                } else {
                    GlStateManager.callList(this.starGLCallList);
                } 
            } 
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableBlend();
            GlStateManager.enableAlpha();
            GlStateManager.enableFog();
            if (flag1)
                Shaders.enableFog(); 
            GlStateManager.popMatrix();
            GlStateManager.disableTexture2D();
            if (flag1)
                Shaders.disableTexture2D(); 
            GlStateManager.color(0.0F, 0.0F, 0.0F);
            double d0 = (Minecraft.thePlayer.getPositionEyes(partialTicks)).yCoord - this.theWorld.getHorizon();
            if (d0 < 0.0D) {
                GlStateManager.pushMatrix();
                GlStateManager.translate(0.0F, 12.0F, 0.0F);
                if (this.vboEnabled) {
                    this.sky2VBO.bindBuffer();
                    GL11.glEnableClientState(32884);
                    GL11.glVertexPointer(3, 5126, 12, 0L);
                    this.sky2VBO.drawArrays(7);
                    this.sky2VBO.unbindBuffer();
                    GL11.glDisableClientState(32884);
                } else {
                    GlStateManager.callList(this.glSkyList2);
                } 
                GlStateManager.popMatrix();
                float f17 = 1.0F;
                float f18 = -((float)(d0 + 65.0D));
                float f19 = -1.0F;
                worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
                worldrenderer.pos(-1.0D, f18, 1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(1.0D, f18, 1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(1.0D, f18, -1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(-1.0D, f18, -1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(1.0D, f18, 1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(1.0D, f18, -1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(-1.0D, f18, -1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(-1.0D, f18, 1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                worldrenderer.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
                tessellator.draw();
            } 
            if (this.theWorld.provider.isSkyColored()) {
                GlStateManager.color(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F);
            } else {
                GlStateManager.color(f, f1, f2);
            } 
            if (this.mc.gameSettings.renderDistanceChunks <= 4)
                GlStateManager.color(this.mc.entityRenderer.fogColorRed, this.mc.entityRenderer.fogColorGreen, this.mc.entityRenderer.fogColorBlue); 
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, -((float)(d0 - 16.0D)), 0.0F);
            if (Config.isSkyEnabled())
                if (this.vboEnabled) {
                    this.sky2VBO.bindBuffer();
                    GL11.glEnableClientState(32884);
                    GL11.glVertexPointer(3, 5126, 12, 0L);
                    this.sky2VBO.drawArrays(7);
                    this.sky2VBO.unbindBuffer();
                    GL11.glDisableClientState(32884);
                } else {
                    GlStateManager.callList(this.glSkyList2);
                }  
            GlStateManager.popMatrix();
            GlStateManager.enableTexture2D();
            if (flag1)
                Shaders.enableTexture2D(); 
            GlStateManager.depthMask(true);
        } 
    }
    
    public void renderClouds(float partialTicks, int pass) {
        if (!Config.isCloudsOff()) {
            if (Reflector.ForgeWorldProvider_getCloudRenderer.exists()) {
                WorldProvider worldprovider = this.mc.theWorld.provider;
                Object object = Reflector.call(worldprovider, Reflector.ForgeWorldProvider_getCloudRenderer, new Object[0]);
                if (object != null) {
                    Reflector.callVoid(object, Reflector.IRenderHandler_render, new Object[] { Float.valueOf(partialTicks), this.theWorld, this.mc });
                    return;
                } 
            } 
            if (this.mc.theWorld.provider.isSurfaceWorld()) {
                if (Config.isShaders())
                    Shaders.beginClouds(); 
                if (Config.isCloudsFancy()) {
                    renderCloudsFancy(partialTicks, pass);
                } else {
                    float f9 = partialTicks;
                    partialTicks = 0.0F;
                    GlStateManager.disableCull();
                    float f10 = (float)((this.mc.getRenderViewEntity()).lastTickPosY + ((this.mc.getRenderViewEntity()).posY - (this.mc.getRenderViewEntity()).lastTickPosY) * partialTicks);
                    boolean flag = true;
                    boolean flag1 = true;
                    Tessellator tessellator = Tessellator.getInstance();
                    WorldRenderer worldrenderer = tessellator.getWorldRenderer();
                    this.renderEngine.bindTexture(locationCloudsPng);
                    GlStateManager.enableBlend();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                    Vec3 vec3 = this.theWorld.getCloudColour(partialTicks);
                    float f = (float)vec3.xCoord;
                    float f1 = (float)vec3.yCoord;
                    float f2 = (float)vec3.zCoord;
                    this.cloudRenderer.prepareToRender(false, this.cloudTickCounter, f9, vec3);
                    if (this.cloudRenderer.shouldUpdateGlList()) {
                        this.cloudRenderer.startUpdateGlList();
                        if (pass != 2) {
                            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
                            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
                            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
                            f = f3;
                            f1 = f4;
                            f2 = f5;
                        } 
                        float f11 = 4.8828125E-4F;
                        double d2 = (this.cloudTickCounter + partialTicks);
                        double d0 = (this.mc.getRenderViewEntity()).prevPosX + ((this.mc.getRenderViewEntity()).posX - (this.mc.getRenderViewEntity()).prevPosX) * partialTicks + d2 * 0.029999999329447746D;
                        double d1 = (this.mc.getRenderViewEntity()).prevPosZ + ((this.mc.getRenderViewEntity()).posZ - (this.mc.getRenderViewEntity()).prevPosZ) * partialTicks;
                        int i = MathHelper.floor_double(d0 / 2048.0D);
                        int j = MathHelper.floor_double(d1 / 2048.0D);
                        d0 -= (i * 2048);
                        d1 -= (j * 2048);
                        float f6 = this.theWorld.provider.getCloudHeight() - f10 + 0.33F;
                        f6 += this.mc.gameSettings.ofCloudsHeight * 128.0F;
                        float f7 = (float)(d0 * 4.8828125E-4D);
                        float f8 = (float)(d1 * 4.8828125E-4D);
                        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                        for (int k = -256; k < 256; k += 32) {
                            for (int l = -256; l < 256; l += 32) {
                                worldrenderer.pos((k + 0), f6, (l + 32)).tex(((k + 0) * 4.8828125E-4F + f7), ((l + 32) * 4.8828125E-4F + f8)).color(f, f1, f2, 0.8F).endVertex();
                                worldrenderer.pos((k + 32), f6, (l + 32)).tex(((k + 32) * 4.8828125E-4F + f7), ((l + 32) * 4.8828125E-4F + f8)).color(f, f1, f2, 0.8F).endVertex();
                                worldrenderer.pos((k + 32), f6, (l + 0)).tex(((k + 32) * 4.8828125E-4F + f7), ((l + 0) * 4.8828125E-4F + f8)).color(f, f1, f2, 0.8F).endVertex();
                                worldrenderer.pos((k + 0), f6, (l + 0)).tex(((k + 0) * 4.8828125E-4F + f7), ((l + 0) * 4.8828125E-4F + f8)).color(f, f1, f2, 0.8F).endVertex();
                            } 
                        } 
                        tessellator.draw();
                        this.cloudRenderer.endUpdateGlList();
                    } 
                    this.cloudRenderer.renderGlList();
                    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                    GlStateManager.disableBlend();
                    GlStateManager.enableCull();
                } 
                if (Config.isShaders())
                    Shaders.endClouds(); 
            } 
        } 
    }
    
    public boolean hasCloudFog(double x, double y, double z, float partialTicks) {
        return false;
    }
    
    private void renderCloudsFancy(float partialTicks, int pass) {
        partialTicks = 0.0F;
        GlStateManager.disableCull();
        float f = (float)((this.mc.getRenderViewEntity()).lastTickPosY + ((this.mc.getRenderViewEntity()).posY - (this.mc.getRenderViewEntity()).lastTickPosY) * partialTicks);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        float f1 = 12.0F;
        float f2 = 4.0F;
        double d0 = (this.cloudTickCounter + partialTicks);
        double d1 = ((this.mc.getRenderViewEntity()).prevPosX + ((this.mc.getRenderViewEntity()).posX - (this.mc.getRenderViewEntity()).prevPosX) * partialTicks + d0 * 0.029999999329447746D) / 12.0D;
        double d2 = ((this.mc.getRenderViewEntity()).prevPosZ + ((this.mc.getRenderViewEntity()).posZ - (this.mc.getRenderViewEntity()).prevPosZ) * partialTicks) / 12.0D + 0.33000001311302185D;
        float f3 = this.theWorld.provider.getCloudHeight() - f + 0.33F;
        f3 += this.mc.gameSettings.ofCloudsHeight * 128.0F;
        int i = MathHelper.floor_double(d1 / 2048.0D);
        int j = MathHelper.floor_double(d2 / 2048.0D);
        d1 -= (i * 2048);
        d2 -= (j * 2048);
        this.renderEngine.bindTexture(locationCloudsPng);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        Vec3 vec3 = this.theWorld.getCloudColour(partialTicks);
        float f4 = (float)vec3.xCoord;
        float f5 = (float)vec3.yCoord;
        float f6 = (float)vec3.zCoord;
        this.cloudRenderer.prepareToRender(true, this.cloudTickCounter, partialTicks, vec3);
        if (pass != 2) {
            float f7 = (f4 * 30.0F + f5 * 59.0F + f6 * 11.0F) / 100.0F;
            float f8 = (f4 * 30.0F + f5 * 70.0F) / 100.0F;
            float f9 = (f4 * 30.0F + f6 * 70.0F) / 100.0F;
            f4 = f7;
            f5 = f8;
            f6 = f9;
        } 
        float f26 = f4 * 0.9F;
        float f27 = f5 * 0.9F;
        float f28 = f6 * 0.9F;
        float f10 = f4 * 0.7F;
        float f11 = f5 * 0.7F;
        float f12 = f6 * 0.7F;
        float f13 = f4 * 0.8F;
        float f14 = f5 * 0.8F;
        float f15 = f6 * 0.8F;
        float f16 = 0.00390625F;
        float f17 = MathHelper.floor_double(d1) * 0.00390625F;
        float f18 = MathHelper.floor_double(d2) * 0.00390625F;
        float f19 = (float)(d1 - MathHelper.floor_double(d1));
        float f20 = (float)(d2 - MathHelper.floor_double(d2));
        boolean flag = true;
        boolean flag1 = true;
        float f21 = 9.765625E-4F;
        GlStateManager.scale(12.0F, 1.0F, 12.0F);
        for (int k = 0; k < 2; k++) {
            if (k == 0) {
                GlStateManager.colorMask(false, false, false, false);
            } else {
                switch (pass) {
                    case 0:
                        GlStateManager.colorMask(false, true, true, true);
                        break;
                    case 1:
                        GlStateManager.colorMask(true, false, false, true);
                        break;
                    case 2:
                        GlStateManager.colorMask(true, true, true, true);
                        break;
                } 
            } 
            this.cloudRenderer.renderGlList();
        } 
        if (this.cloudRenderer.shouldUpdateGlList()) {
            this.cloudRenderer.startUpdateGlList();
            for (int j1 = -3; j1 <= 4; j1++) {
                for (int l = -3; l <= 4; l++) {
                    worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
                    float f22 = (j1 * 8);
                    float f23 = (l * 8);
                    float f24 = f22 - f19;
                    float f25 = f23 - f20;
                    if (f3 > -5.0F) {
                        worldrenderer.pos((f24 + 0.0F), (f3 + 0.0F), (f25 + 8.0F)).tex(((f22 + 0.0F) * 0.00390625F + f17), ((f23 + 8.0F) * 0.00390625F + f18)).color(f10, f11, f12, 0.8F).normal(0.0F, -1.0F, 0.0F).endVertex();
                        worldrenderer.pos((f24 + 8.0F), (f3 + 0.0F), (f25 + 8.0F)).tex(((f22 + 8.0F) * 0.00390625F + f17), ((f23 + 8.0F) * 0.00390625F + f18)).color(f10, f11, f12, 0.8F).normal(0.0F, -1.0F, 0.0F).endVertex();
                        worldrenderer.pos((f24 + 8.0F), (f3 + 0.0F), (f25 + 0.0F)).tex(((f22 + 8.0F) * 0.00390625F + f17), ((f23 + 0.0F) * 0.00390625F + f18)).color(f10, f11, f12, 0.8F).normal(0.0F, -1.0F, 0.0F).endVertex();
                        worldrenderer.pos((f24 + 0.0F), (f3 + 0.0F), (f25 + 0.0F)).tex(((f22 + 0.0F) * 0.00390625F + f17), ((f23 + 0.0F) * 0.00390625F + f18)).color(f10, f11, f12, 0.8F).normal(0.0F, -1.0F, 0.0F).endVertex();
                    } 
                    if (f3 <= 5.0F) {
                        worldrenderer.pos((f24 + 0.0F), (f3 + 4.0F - 9.765625E-4F), (f25 + 8.0F)).tex(((f22 + 0.0F) * 0.00390625F + f17), ((f23 + 8.0F) * 0.00390625F + f18)).color(f4, f5, f6, 0.8F).normal(0.0F, 1.0F, 0.0F).endVertex();
                        worldrenderer.pos((f24 + 8.0F), (f3 + 4.0F - 9.765625E-4F), (f25 + 8.0F)).tex(((f22 + 8.0F) * 0.00390625F + f17), ((f23 + 8.0F) * 0.00390625F + f18)).color(f4, f5, f6, 0.8F).normal(0.0F, 1.0F, 0.0F).endVertex();
                        worldrenderer.pos((f24 + 8.0F), (f3 + 4.0F - 9.765625E-4F), (f25 + 0.0F)).tex(((f22 + 8.0F) * 0.00390625F + f17), ((f23 + 0.0F) * 0.00390625F + f18)).color(f4, f5, f6, 0.8F).normal(0.0F, 1.0F, 0.0F).endVertex();
                        worldrenderer.pos((f24 + 0.0F), (f3 + 4.0F - 9.765625E-4F), (f25 + 0.0F)).tex(((f22 + 0.0F) * 0.00390625F + f17), ((f23 + 0.0F) * 0.00390625F + f18)).color(f4, f5, f6, 0.8F).normal(0.0F, 1.0F, 0.0F).endVertex();
                    } 
                    if (j1 > -1)
                        for (int i1 = 0; i1 < 8; i1++) {
                            worldrenderer.pos((f24 + i1 + 0.0F), (f3 + 0.0F), (f25 + 8.0F)).tex(((f22 + i1 + 0.5F) * 0.00390625F + f17), ((f23 + 8.0F) * 0.00390625F + f18)).color(f26, f27, f28, 0.8F).normal(-1.0F, 0.0F, 0.0F).endVertex();
                            worldrenderer.pos((f24 + i1 + 0.0F), (f3 + 4.0F), (f25 + 8.0F)).tex(((f22 + i1 + 0.5F) * 0.00390625F + f17), ((f23 + 8.0F) * 0.00390625F + f18)).color(f26, f27, f28, 0.8F).normal(-1.0F, 0.0F, 0.0F).endVertex();
                            worldrenderer.pos((f24 + i1 + 0.0F), (f3 + 4.0F), (f25 + 0.0F)).tex(((f22 + i1 + 0.5F) * 0.00390625F + f17), ((f23 + 0.0F) * 0.00390625F + f18)).color(f26, f27, f28, 0.8F).normal(-1.0F, 0.0F, 0.0F).endVertex();
                            worldrenderer.pos((f24 + i1 + 0.0F), (f3 + 0.0F), (f25 + 0.0F)).tex(((f22 + i1 + 0.5F) * 0.00390625F + f17), ((f23 + 0.0F) * 0.00390625F + f18)).color(f26, f27, f28, 0.8F).normal(-1.0F, 0.0F, 0.0F).endVertex();
                        }  
                    if (j1 <= 1)
                        for (int k1 = 0; k1 < 8; k1++) {
                            worldrenderer.pos((f24 + k1 + 1.0F - 9.765625E-4F), (f3 + 0.0F), (f25 + 8.0F)).tex(((f22 + k1 + 0.5F) * 0.00390625F + f17), ((f23 + 8.0F) * 0.00390625F + f18)).color(f26, f27, f28, 0.8F).normal(1.0F, 0.0F, 0.0F).endVertex();
                            worldrenderer.pos((f24 + k1 + 1.0F - 9.765625E-4F), (f3 + 4.0F), (f25 + 8.0F)).tex(((f22 + k1 + 0.5F) * 0.00390625F + f17), ((f23 + 8.0F) * 0.00390625F + f18)).color(f26, f27, f28, 0.8F).normal(1.0F, 0.0F, 0.0F).endVertex();
                            worldrenderer.pos((f24 + k1 + 1.0F - 9.765625E-4F), (f3 + 4.0F), (f25 + 0.0F)).tex(((f22 + k1 + 0.5F) * 0.00390625F + f17), ((f23 + 0.0F) * 0.00390625F + f18)).color(f26, f27, f28, 0.8F).normal(1.0F, 0.0F, 0.0F).endVertex();
                            worldrenderer.pos((f24 + k1 + 1.0F - 9.765625E-4F), (f3 + 0.0F), (f25 + 0.0F)).tex(((f22 + k1 + 0.5F) * 0.00390625F + f17), ((f23 + 0.0F) * 0.00390625F + f18)).color(f26, f27, f28, 0.8F).normal(1.0F, 0.0F, 0.0F).endVertex();
                        }  
                    if (l > -1)
                        for (int l1 = 0; l1 < 8; l1++) {
                            worldrenderer.pos((f24 + 0.0F), (f3 + 4.0F), (f25 + l1 + 0.0F)).tex(((f22 + 0.0F) * 0.00390625F + f17), ((f23 + l1 + 0.5F) * 0.00390625F + f18)).color(f13, f14, f15, 0.8F).normal(0.0F, 0.0F, -1.0F).endVertex();
                            worldrenderer.pos((f24 + 8.0F), (f3 + 4.0F), (f25 + l1 + 0.0F)).tex(((f22 + 8.0F) * 0.00390625F + f17), ((f23 + l1 + 0.5F) * 0.00390625F + f18)).color(f13, f14, f15, 0.8F).normal(0.0F, 0.0F, -1.0F).endVertex();
                            worldrenderer.pos((f24 + 8.0F), (f3 + 0.0F), (f25 + l1 + 0.0F)).tex(((f22 + 8.0F) * 0.00390625F + f17), ((f23 + l1 + 0.5F) * 0.00390625F + f18)).color(f13, f14, f15, 0.8F).normal(0.0F, 0.0F, -1.0F).endVertex();
                            worldrenderer.pos((f24 + 0.0F), (f3 + 0.0F), (f25 + l1 + 0.0F)).tex(((f22 + 0.0F) * 0.00390625F + f17), ((f23 + l1 + 0.5F) * 0.00390625F + f18)).color(f13, f14, f15, 0.8F).normal(0.0F, 0.0F, -1.0F).endVertex();
                        }  
                    if (l <= 1)
                        for (int i2 = 0; i2 < 8; i2++) {
                            worldrenderer.pos((f24 + 0.0F), (f3 + 4.0F), (f25 + i2 + 1.0F - 9.765625E-4F)).tex(((f22 + 0.0F) * 0.00390625F + f17), ((f23 + i2 + 0.5F) * 0.00390625F + f18)).color(f13, f14, f15, 0.8F).normal(0.0F, 0.0F, 1.0F).endVertex();
                            worldrenderer.pos((f24 + 8.0F), (f3 + 4.0F), (f25 + i2 + 1.0F - 9.765625E-4F)).tex(((f22 + 8.0F) * 0.00390625F + f17), ((f23 + i2 + 0.5F) * 0.00390625F + f18)).color(f13, f14, f15, 0.8F).normal(0.0F, 0.0F, 1.0F).endVertex();
                            worldrenderer.pos((f24 + 8.0F), (f3 + 0.0F), (f25 + i2 + 1.0F - 9.765625E-4F)).tex(((f22 + 8.0F) * 0.00390625F + f17), ((f23 + i2 + 0.5F) * 0.00390625F + f18)).color(f13, f14, f15, 0.8F).normal(0.0F, 0.0F, 1.0F).endVertex();
                            worldrenderer.pos((f24 + 0.0F), (f3 + 0.0F), (f25 + i2 + 1.0F - 9.765625E-4F)).tex(((f22 + 0.0F) * 0.00390625F + f17), ((f23 + i2 + 0.5F) * 0.00390625F + f18)).color(f13, f14, f15, 0.8F).normal(0.0F, 0.0F, 1.0F).endVertex();
                        }  
                    tessellator.draw();
                } 
            } 
            this.cloudRenderer.endUpdateGlList();
        } 
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableCull();
    }
    
    public void updateChunks(long finishTimeNano) {
        finishTimeNano = (long)(finishTimeNano + 1.0E8D);
        this.displayListEntitiesDirty |= this.renderDispatcher.runChunkUploads(finishTimeNano);
        if (this.chunksToUpdateForced.size() > 0) {
            Iterator<RenderChunk> iterator = this.chunksToUpdateForced.iterator();
            while (iterator.hasNext()) {
                RenderChunk renderchunk = iterator.next();
                if (!this.renderDispatcher.updateChunkLater(renderchunk))
                    break; 
                renderchunk.setNeedsUpdate(false);
                iterator.remove();
                this.chunksToUpdate.remove(renderchunk);
                this.chunksToResortTransparency.remove(renderchunk);
            } 
        } 
        if (this.chunksToResortTransparency.size() > 0) {
            Iterator<RenderChunk> iterator2 = this.chunksToResortTransparency.iterator();
            if (iterator2.hasNext()) {
                RenderChunk renderchunk2 = iterator2.next();
                if (this.renderDispatcher.updateTransparencyLater(renderchunk2))
                    iterator2.remove(); 
            } 
        } 
        int i = 0;
        int j = Config.getUpdatesPerFrame();
        Iterator<RenderChunk> iterator1 = this.chunksToUpdate.iterator();
        while (iterator1.hasNext()) {
            RenderChunk renderchunk1 = iterator1.next();
            boolean flag = renderchunk1.isChunkRegionEmpty();
            if (flag) {
                if (!this.renderDispatcher.updateChunkNow(renderchunk1))
                    break; 
            } else if (!this.renderDispatcher.updateChunkLater(renderchunk1)) {
                break;
            } 
            renderchunk1.setNeedsUpdate(false);
            iterator1.remove();
            if (!flag) {
                i++;
                if (i >= j)
                    break; 
            } 
        } 
    }
    
    public void renderWorldBorder(Entity p_180449_1_, float partialTicks) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        WorldBorder worldborder = this.theWorld.getWorldBorder();
        double d0 = (this.mc.gameSettings.renderDistanceChunks * 16);
        if (p_180449_1_.posX >= worldborder.maxX() - d0 || p_180449_1_.posX <= worldborder.minX() + d0 || p_180449_1_.posZ >= worldborder.maxZ() - d0 || p_180449_1_.posZ <= worldborder.minZ() + d0) {
            double d1 = 1.0D - worldborder.getClosestDistance(p_180449_1_) / d0;
            d1 = Math.pow(d1, 4.0D);
            double d2 = p_180449_1_.lastTickPosX + (p_180449_1_.posX - p_180449_1_.lastTickPosX) * partialTicks;
            double d3 = p_180449_1_.lastTickPosY + (p_180449_1_.posY - p_180449_1_.lastTickPosY) * partialTicks;
            double d4 = p_180449_1_.lastTickPosZ + (p_180449_1_.posZ - p_180449_1_.lastTickPosZ) * partialTicks;
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
            this.renderEngine.bindTexture(locationForcefieldPng);
            GlStateManager.depthMask(false);
            GlStateManager.pushMatrix();
            int i = worldborder.getStatus().getID();
            float f = (i >> 16 & 0xFF) / 255.0F;
            float f1 = (i >> 8 & 0xFF) / 255.0F;
            float f2 = (i & 0xFF) / 255.0F;
            GlStateManager.color(f, f1, f2, (float)d1);
            GlStateManager.doPolygonOffset(-3.0F, -3.0F);
            GlStateManager.enablePolygonOffset();
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.enableAlpha();
            GlStateManager.disableCull();
            float f3 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F;
            float f4 = 0.0F;
            float f5 = 0.0F;
            float f6 = 128.0F;
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
            worldrenderer.setTranslation(-d2, -d3, -d4);
            double d5 = Math.max(MathHelper.floor_double(d4 - d0), worldborder.minZ());
            double d6 = Math.min(MathHelper.ceiling_double_int(d4 + d0), worldborder.maxZ());
            if (d2 > worldborder.maxX() - d0) {
                float f7 = 0.0F;
                for (double d7 = d5; d7 < d6; f7 += 0.5F) {
                    double d8 = Math.min(1.0D, d6 - d7);
                    float f8 = (float)d8 * 0.5F;
                    worldrenderer.pos(worldborder.maxX(), 256.0D, d7).tex((f3 + f7), (f3 + 0.0F)).endVertex();
                    worldrenderer.pos(worldborder.maxX(), 256.0D, d7 + d8).tex((f3 + f8 + f7), (f3 + 0.0F)).endVertex();
                    worldrenderer.pos(worldborder.maxX(), 0.0D, d7 + d8).tex((f3 + f8 + f7), (f3 + 128.0F)).endVertex();
                    worldrenderer.pos(worldborder.maxX(), 0.0D, d7).tex((f3 + f7), (f3 + 128.0F)).endVertex();
                    d7++;
                } 
            } 
            if (d2 < worldborder.minX() + d0) {
                float f9 = 0.0F;
                for (double d9 = d5; d9 < d6; f9 += 0.5F) {
                    double d12 = Math.min(1.0D, d6 - d9);
                    float f12 = (float)d12 * 0.5F;
                    worldrenderer.pos(worldborder.minX(), 256.0D, d9).tex((f3 + f9), (f3 + 0.0F)).endVertex();
                    worldrenderer.pos(worldborder.minX(), 256.0D, d9 + d12).tex((f3 + f12 + f9), (f3 + 0.0F)).endVertex();
                    worldrenderer.pos(worldborder.minX(), 0.0D, d9 + d12).tex((f3 + f12 + f9), (f3 + 128.0F)).endVertex();
                    worldrenderer.pos(worldborder.minX(), 0.0D, d9).tex((f3 + f9), (f3 + 128.0F)).endVertex();
                    d9++;
                } 
            } 
            d5 = Math.max(MathHelper.floor_double(d2 - d0), worldborder.minX());
            d6 = Math.min(MathHelper.ceiling_double_int(d2 + d0), worldborder.maxX());
            if (d4 > worldborder.maxZ() - d0) {
                float f10 = 0.0F;
                for (double d10 = d5; d10 < d6; f10 += 0.5F) {
                    double d13 = Math.min(1.0D, d6 - d10);
                    float f13 = (float)d13 * 0.5F;
                    worldrenderer.pos(d10, 256.0D, worldborder.maxZ()).tex((f3 + f10), (f3 + 0.0F)).endVertex();
                    worldrenderer.pos(d10 + d13, 256.0D, worldborder.maxZ()).tex((f3 + f13 + f10), (f3 + 0.0F)).endVertex();
                    worldrenderer.pos(d10 + d13, 0.0D, worldborder.maxZ()).tex((f3 + f13 + f10), (f3 + 128.0F)).endVertex();
                    worldrenderer.pos(d10, 0.0D, worldborder.maxZ()).tex((f3 + f10), (f3 + 128.0F)).endVertex();
                    d10++;
                } 
            } 
            if (d4 < worldborder.minZ() + d0) {
                float f11 = 0.0F;
                for (double d11 = d5; d11 < d6; f11 += 0.5F) {
                    double d14 = Math.min(1.0D, d6 - d11);
                    float f14 = (float)d14 * 0.5F;
                    worldrenderer.pos(d11, 256.0D, worldborder.minZ()).tex((f3 + f11), (f3 + 0.0F)).endVertex();
                    worldrenderer.pos(d11 + d14, 256.0D, worldborder.minZ()).tex((f3 + f14 + f11), (f3 + 0.0F)).endVertex();
                    worldrenderer.pos(d11 + d14, 0.0D, worldborder.minZ()).tex((f3 + f14 + f11), (f3 + 128.0F)).endVertex();
                    worldrenderer.pos(d11, 0.0D, worldborder.minZ()).tex((f3 + f11), (f3 + 128.0F)).endVertex();
                    d11++;
                } 
            } 
            tessellator.draw();
            worldrenderer.setTranslation(0.0D, 0.0D, 0.0D);
            GlStateManager.enableCull();
            GlStateManager.disableAlpha();
            GlStateManager.doPolygonOffset(0.0F, 0.0F);
            GlStateManager.disablePolygonOffset();
            GlStateManager.enableAlpha();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
            GlStateManager.depthMask(true);
        } 
    }
    
    private void preRenderDamagedBlocks() {
        GlStateManager.tryBlendFuncSeparate(774, 768, 1, 0);
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
        GlStateManager.doPolygonOffset(-3.0F, -3.0F);
        GlStateManager.enablePolygonOffset();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.enableAlpha();
        GlStateManager.pushMatrix();
        if (Config.isShaders())
            ShadersRender.beginBlockDamage(); 
    }
    
    private void postRenderDamagedBlocks() {
        GlStateManager.disableAlpha();
        GlStateManager.doPolygonOffset(0.0F, 0.0F);
        GlStateManager.disablePolygonOffset();
        GlStateManager.enableAlpha();
        GlStateManager.depthMask(true);
        GlStateManager.popMatrix();
        if (Config.isShaders())
            ShadersRender.endBlockDamage(); 
    }
    
    public void drawBlockDamageTexture(Tessellator tessellatorIn, WorldRenderer worldRendererIn, Entity entityIn, float partialTicks) {
        double d0 = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * partialTicks;
        double d1 = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * partialTicks;
        double d2 = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * partialTicks;
        if (!this.damagedBlocks.isEmpty()) {
            this.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
            preRenderDamagedBlocks();
            worldRendererIn.begin(7, DefaultVertexFormats.BLOCK);
            worldRendererIn.setTranslation(-d0, -d1, -d2);
            worldRendererIn.markDirty();
            Iterator<DestroyBlockProgress> iterator = this.damagedBlocks.values().iterator();
            while (iterator.hasNext()) {
                boolean flag;
                DestroyBlockProgress destroyblockprogress = iterator.next();
                BlockPos blockpos = destroyblockprogress.getPosition();
                double d3 = blockpos.getX() - d0;
                double d4 = blockpos.getY() - d1;
                double d5 = blockpos.getZ() - d2;
                Block block = this.theWorld.getBlockState(blockpos).getBlock();
                if (Reflector.ForgeTileEntity_canRenderBreaking.exists()) {
                    boolean flag1 = !(!(block instanceof net.minecraft.block.BlockChest) && !(block instanceof net.minecraft.block.BlockEnderChest) && !(block instanceof net.minecraft.block.BlockSign) && !(block instanceof net.minecraft.block.BlockSkull));
                    if (!flag1) {
                        TileEntity tileentity = this.theWorld.getTileEntity(blockpos);
                        if (tileentity != null)
                            flag1 = Reflector.callBoolean(tileentity, Reflector.ForgeTileEntity_canRenderBreaking, new Object[0]); 
                    } 
                    flag = !flag1;
                } else {
                    flag = (!(block instanceof net.minecraft.block.BlockChest) && !(block instanceof net.minecraft.block.BlockEnderChest) && !(block instanceof net.minecraft.block.BlockSign) && !(block instanceof net.minecraft.block.BlockSkull));
                } 
                if (flag) {
                    if (d3 * d3 + d4 * d4 + d5 * d5 > 1024.0D) {
                        iterator.remove();
                        continue;
                    } 
                    IBlockState iblockstate = this.theWorld.getBlockState(blockpos);
                    if (iblockstate.getBlock().getMaterial() != Material.air) {
                        int i = destroyblockprogress.getPartialBlockDamage();
                        TextureAtlasSprite textureatlassprite = this.destroyBlockIcons[i];
                        BlockRendererDispatcher blockrendererdispatcher = this.mc.getBlockRendererDispatcher();
                        blockrendererdispatcher.renderBlockDamage(iblockstate, blockpos, textureatlassprite, (IBlockAccess)this.theWorld);
                    } 
                } 
            } 
            tessellatorIn.draw();
            worldRendererIn.setTranslation(0.0D, 0.0D, 0.0D);
            postRenderDamagedBlocks();
        } 
    }
    
    public void drawSelectionBox(EntityPlayer player, MovingObjectPosition movingObjectPositionIn, int p_72731_3_, float partialTicks) {
        if (p_72731_3_ == 0 && movingObjectPositionIn.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            if (HudManager.blockOutline.isEnabled()) {
                this.mc.fontRendererObj.drawStringWithShadow("", 0.0F, 0.0F, Client.BlockOutline);
                GL11.glLineWidth(2.3F);
            } else {
                GlStateManager.color(0.0F, 0.0F, 0.0F, 0.4F);
                GL11.glLineWidth(2.0F);
            } 
            GlStateManager.disableTexture2D();
            if (Config.isShaders())
                Shaders.disableTexture2D(); 
            GlStateManager.depthMask(false);
            float f = 0.002F;
            BlockPos blockpos = movingObjectPositionIn.getBlockPos();
            Block block = this.theWorld.getBlockState(blockpos).getBlock();
            if (block.getMaterial() != Material.air && this.theWorld.getWorldBorder().contains(blockpos)) {
                block.setBlockBoundsBasedOnState((IBlockAccess)this.theWorld, blockpos);
                double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
                double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
                double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;
                func_181561_a(block.getSelectedBoundingBox((World)this.theWorld, blockpos).expand(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).offset(-d0, -d1, -d2));
            } 
            GlStateManager.depthMask(true);
            GlStateManager.enableTexture2D();
            if (Config.isShaders())
                Shaders.enableTexture2D(); 
            GlStateManager.disableBlend();
        } 
    }
    
    public static void func_181561_a(AxisAlignedBB p_181561_0_) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(3, DefaultVertexFormats.POSITION);
        worldrenderer.pos(p_181561_0_.minX, p_181561_0_.minY, p_181561_0_.minZ).endVertex();
        worldrenderer.pos(p_181561_0_.maxX, p_181561_0_.minY, p_181561_0_.minZ).endVertex();
        worldrenderer.pos(p_181561_0_.maxX, p_181561_0_.minY, p_181561_0_.maxZ).endVertex();
        worldrenderer.pos(p_181561_0_.minX, p_181561_0_.minY, p_181561_0_.maxZ).endVertex();
        worldrenderer.pos(p_181561_0_.minX, p_181561_0_.minY, p_181561_0_.minZ).endVertex();
        tessellator.draw();
        worldrenderer.begin(3, DefaultVertexFormats.POSITION);
        worldrenderer.pos(p_181561_0_.minX, p_181561_0_.maxY, p_181561_0_.minZ).endVertex();
        worldrenderer.pos(p_181561_0_.maxX, p_181561_0_.maxY, p_181561_0_.minZ).endVertex();
        worldrenderer.pos(p_181561_0_.maxX, p_181561_0_.maxY, p_181561_0_.maxZ).endVertex();
        worldrenderer.pos(p_181561_0_.minX, p_181561_0_.maxY, p_181561_0_.maxZ).endVertex();
        worldrenderer.pos(p_181561_0_.minX, p_181561_0_.maxY, p_181561_0_.minZ).endVertex();
        tessellator.draw();
        worldrenderer.begin(1, DefaultVertexFormats.POSITION);
        worldrenderer.pos(p_181561_0_.minX, p_181561_0_.minY, p_181561_0_.minZ).endVertex();
        worldrenderer.pos(p_181561_0_.minX, p_181561_0_.maxY, p_181561_0_.minZ).endVertex();
        worldrenderer.pos(p_181561_0_.maxX, p_181561_0_.minY, p_181561_0_.minZ).endVertex();
        worldrenderer.pos(p_181561_0_.maxX, p_181561_0_.maxY, p_181561_0_.minZ).endVertex();
        worldrenderer.pos(p_181561_0_.maxX, p_181561_0_.minY, p_181561_0_.maxZ).endVertex();
        worldrenderer.pos(p_181561_0_.maxX, p_181561_0_.maxY, p_181561_0_.maxZ).endVertex();
        worldrenderer.pos(p_181561_0_.minX, p_181561_0_.minY, p_181561_0_.maxZ).endVertex();
        worldrenderer.pos(p_181561_0_.minX, p_181561_0_.maxY, p_181561_0_.maxZ).endVertex();
        tessellator.draw();
    }
    
    public static void func_181563_a(AxisAlignedBB p_181563_0_, int p_181563_1_, int p_181563_2_, int p_181563_3_, int p_181563_4_) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(p_181563_0_.minX, p_181563_0_.minY, p_181563_0_.minZ).color(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).endVertex();
        worldrenderer.pos(p_181563_0_.maxX, p_181563_0_.minY, p_181563_0_.minZ).color(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).endVertex();
        worldrenderer.pos(p_181563_0_.maxX, p_181563_0_.minY, p_181563_0_.maxZ).color(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).endVertex();
        worldrenderer.pos(p_181563_0_.minX, p_181563_0_.minY, p_181563_0_.maxZ).color(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).endVertex();
        worldrenderer.pos(p_181563_0_.minX, p_181563_0_.minY, p_181563_0_.minZ).color(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).endVertex();
        tessellator.draw();
        worldrenderer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(p_181563_0_.minX, p_181563_0_.maxY, p_181563_0_.minZ).color(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).endVertex();
        worldrenderer.pos(p_181563_0_.maxX, p_181563_0_.maxY, p_181563_0_.minZ).color(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).endVertex();
        worldrenderer.pos(p_181563_0_.maxX, p_181563_0_.maxY, p_181563_0_.maxZ).color(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).endVertex();
        worldrenderer.pos(p_181563_0_.minX, p_181563_0_.maxY, p_181563_0_.maxZ).color(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).endVertex();
        worldrenderer.pos(p_181563_0_.minX, p_181563_0_.maxY, p_181563_0_.minZ).color(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).endVertex();
        tessellator.draw();
        worldrenderer.begin(1, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(p_181563_0_.minX, p_181563_0_.minY, p_181563_0_.minZ).color(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).endVertex();
        worldrenderer.pos(p_181563_0_.minX, p_181563_0_.maxY, p_181563_0_.minZ).color(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).endVertex();
        worldrenderer.pos(p_181563_0_.maxX, p_181563_0_.minY, p_181563_0_.minZ).color(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).endVertex();
        worldrenderer.pos(p_181563_0_.maxX, p_181563_0_.maxY, p_181563_0_.minZ).color(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).endVertex();
        worldrenderer.pos(p_181563_0_.maxX, p_181563_0_.minY, p_181563_0_.maxZ).color(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).endVertex();
        worldrenderer.pos(p_181563_0_.maxX, p_181563_0_.maxY, p_181563_0_.maxZ).color(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).endVertex();
        worldrenderer.pos(p_181563_0_.minX, p_181563_0_.minY, p_181563_0_.maxZ).color(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).endVertex();
        worldrenderer.pos(p_181563_0_.minX, p_181563_0_.maxY, p_181563_0_.maxZ).color(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).endVertex();
        tessellator.draw();
    }
    
    private void markBlocksForUpdate(int x1, int y1, int z1, int x2, int y2, int z2) {
        this.viewFrustum.markBlocksForUpdate(x1, y1, z1, x2, y2, z2);
    }
    
    public void markBlockForUpdate(BlockPos pos) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        markBlocksForUpdate(i - 1, j - 1, k - 1, i + 1, j + 1, k + 1);
    }
    
    public void notifyLightSet(BlockPos pos) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        markBlocksForUpdate(i - 1, j - 1, k - 1, i + 1, j + 1, k + 1);
    }
    
    public void markBlockRangeForRenderUpdate(int x1, int y1, int z1, int x2, int y2, int z2) {
        markBlocksForUpdate(x1 - 1, y1 - 1, z1 - 1, x2 + 1, y2 + 1, z2 + 1);
    }
    
    public void playRecord(String recordName, BlockPos blockPosIn) {
        ISound isound = this.mapSoundPositions.get(blockPosIn);
        if (isound != null) {
            this.mc.getSoundHandler().stopSound(isound);
            this.mapSoundPositions.remove(blockPosIn);
        } 
        if (recordName != null) {
            ItemRecord itemrecord = ItemRecord.getRecord(recordName);
            if (itemrecord != null)
                this.mc.ingameGUI.setRecordPlayingMessage(itemrecord.getRecordNameLocal()); 
            ResourceLocation resourcelocation = null;
            if (Reflector.ForgeItemRecord_getRecordResource.exists() && itemrecord != null)
                resourcelocation = (ResourceLocation)Reflector.call(itemrecord, Reflector.ForgeItemRecord_getRecordResource, new Object[] { recordName }); 
            if (resourcelocation == null)
                resourcelocation = new ResourceLocation(recordName); 
            PositionedSoundRecord positionedsoundrecord = PositionedSoundRecord.create(resourcelocation, blockPosIn.getX(), blockPosIn.getY(), blockPosIn.getZ());
            this.mapSoundPositions.put(blockPosIn, positionedsoundrecord);
            this.mc.getSoundHandler().playSound((ISound)positionedsoundrecord);
        } 
    }
    
    public void playSound(String soundName, double x, double y, double z, float volume, float pitch) {}
    
    public void playSoundToNearExcept(EntityPlayer except, String soundName, double x, double y, double z, float volume, float pitch) {}
    
    public void spawnParticle(int particleID, boolean ignoreRange, final double xCoord, final double yCoord, final double zCoord, double xOffset, double yOffset, double zOffset, int... p_180442_15_) {
        try {
            spawnEntityFX(particleID, ignoreRange, xCoord, yCoord, zCoord, xOffset, yOffset, zOffset, p_180442_15_);
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Exception while adding particle");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("Particle being added");
            crashreportcategory.addCrashSection("ID", Integer.valueOf(particleID));
            if (p_180442_15_ != null)
                crashreportcategory.addCrashSection("Parameters", p_180442_15_); 
            crashreportcategory.addCrashSectionCallable("Position", new Callable() {
                        private static final String __OBFID = "CL_00000955";
                        
                        public String call() throws Exception {
                            return CrashReportCategory.getCoordinateInfo(xCoord, yCoord, zCoord);
                        }
                    });
            throw new ReportedException(crashreport);
        } 
    }
    
    private void spawnParticle(EnumParticleTypes particleIn, double p_174972_2_, double p_174972_4_, double p_174972_6_, double p_174972_8_, double p_174972_10_, double p_174972_12_, int... p_174972_14_) {
        spawnParticle(particleIn.getParticleID(), particleIn.getShouldIgnoreRange(), p_174972_2_, p_174972_4_, p_174972_6_, p_174972_8_, p_174972_10_, p_174972_12_, p_174972_14_);
    }
    
    private EntityFX spawnEntityFX(int p_174974_1_, boolean ignoreRange, double p_174974_3_, double p_174974_5_, double p_174974_7_, double p_174974_9_, double p_174974_11_, double p_174974_13_, int... p_174974_15_) {
        if (this.mc != null && this.mc.getRenderViewEntity() != null && this.mc.effectRenderer != null) {
            int i = this.mc.gameSettings.particleSetting;
            if (i == 1 && this.theWorld.rand.nextInt(3) == 0)
                i = 2; 
            double d0 = (this.mc.getRenderViewEntity()).posX - p_174974_3_;
            double d1 = (this.mc.getRenderViewEntity()).posY - p_174974_5_;
            double d2 = (this.mc.getRenderViewEntity()).posZ - p_174974_7_;
            if (p_174974_1_ == EnumParticleTypes.EXPLOSION_HUGE.getParticleID() && !Config.isAnimatedExplosion())
                return null; 
            if (p_174974_1_ == EnumParticleTypes.EXPLOSION_LARGE.getParticleID() && !Config.isAnimatedExplosion())
                return null; 
            if (p_174974_1_ == EnumParticleTypes.EXPLOSION_NORMAL.getParticleID() && !Config.isAnimatedExplosion())
                return null; 
            if (p_174974_1_ == EnumParticleTypes.SUSPENDED.getParticleID() && !Config.isWaterParticles())
                return null; 
            if (p_174974_1_ == EnumParticleTypes.SUSPENDED_DEPTH.getParticleID() && !Config.isVoidParticles())
                return null; 
            if (p_174974_1_ == EnumParticleTypes.SMOKE_NORMAL.getParticleID() && !Config.isAnimatedSmoke())
                return null; 
            if (p_174974_1_ == EnumParticleTypes.SMOKE_LARGE.getParticleID() && !Config.isAnimatedSmoke())
                return null; 
            if (p_174974_1_ == EnumParticleTypes.SPELL_MOB.getParticleID() && !Config.isPotionParticles())
                return null; 
            if (p_174974_1_ == EnumParticleTypes.SPELL_MOB_AMBIENT.getParticleID() && !Config.isPotionParticles())
                return null; 
            if (p_174974_1_ == EnumParticleTypes.SPELL.getParticleID() && !Config.isPotionParticles())
                return null; 
            if (p_174974_1_ == EnumParticleTypes.SPELL_INSTANT.getParticleID() && !Config.isPotionParticles())
                return null; 
            if (p_174974_1_ == EnumParticleTypes.SPELL_WITCH.getParticleID() && !Config.isPotionParticles())
                return null; 
            if (p_174974_1_ == EnumParticleTypes.PORTAL.getParticleID() && !Config.isAnimatedPortal())
                return null; 
            if (p_174974_1_ == EnumParticleTypes.FLAME.getParticleID() && !Config.isAnimatedFlame())
                return null; 
            if (p_174974_1_ == EnumParticleTypes.REDSTONE.getParticleID() && !Config.isAnimatedRedstone())
                return null; 
            if (p_174974_1_ == EnumParticleTypes.DRIP_WATER.getParticleID() && !Config.isDrippingWaterLava())
                return null; 
            if (p_174974_1_ == EnumParticleTypes.DRIP_LAVA.getParticleID() && !Config.isDrippingWaterLava())
                return null; 
            if (p_174974_1_ == EnumParticleTypes.FIREWORKS_SPARK.getParticleID() && !Config.isFireworkParticles())
                return null; 
            if (ignoreRange)
                return this.mc.effectRenderer.spawnEffectParticle(p_174974_1_, p_174974_3_, p_174974_5_, p_174974_7_, p_174974_9_, p_174974_11_, p_174974_13_, p_174974_15_); 
            double d3 = 16.0D;
            double d4 = 256.0D;
            if (p_174974_1_ == EnumParticleTypes.CRIT.getParticleID())
                d4 = 38416.0D; 
            if (d0 * d0 + d1 * d1 + d2 * d2 > d4)
                return null; 
            if (i > 1)
                return null; 
            EntityFX entityfx = this.mc.effectRenderer.spawnEffectParticle(p_174974_1_, p_174974_3_, p_174974_5_, p_174974_7_, p_174974_9_, p_174974_11_, p_174974_13_, p_174974_15_);
            if (p_174974_1_ == EnumParticleTypes.WATER_BUBBLE.getParticleID())
                CustomColors.updateWaterFX(entityfx, (IBlockAccess)this.theWorld, p_174974_3_, p_174974_5_, p_174974_7_); 
            if (p_174974_1_ == EnumParticleTypes.WATER_SPLASH.getParticleID())
                CustomColors.updateWaterFX(entityfx, (IBlockAccess)this.theWorld, p_174974_3_, p_174974_5_, p_174974_7_); 
            if (p_174974_1_ == EnumParticleTypes.WATER_DROP.getParticleID())
                CustomColors.updateWaterFX(entityfx, (IBlockAccess)this.theWorld, p_174974_3_, p_174974_5_, p_174974_7_); 
            if (p_174974_1_ == EnumParticleTypes.TOWN_AURA.getParticleID())
                CustomColors.updateMyceliumFX(entityfx); 
            if (p_174974_1_ == EnumParticleTypes.PORTAL.getParticleID())
                CustomColors.updatePortalFX(entityfx); 
            if (p_174974_1_ == EnumParticleTypes.REDSTONE.getParticleID())
                CustomColors.updateReddustFX(entityfx, (IBlockAccess)this.theWorld, p_174974_3_, p_174974_5_, p_174974_7_); 
            return entityfx;
        } 
        return null;
    }
    
    public void onEntityAdded(Entity entityIn) {
        RandomMobs.entityLoaded(entityIn, (World)this.theWorld);
        if (Config.isDynamicLights())
            DynamicLights.entityAdded(entityIn, this); 
    }
    
    public void onEntityRemoved(Entity entityIn) {
        if (Config.isDynamicLights())
            DynamicLights.entityRemoved(entityIn, this); 
    }
    
    public void deleteAllDisplayLists() {}
    
    public void broadcastSound(int p_180440_1_, BlockPos p_180440_2_, int p_180440_3_) {
        switch (p_180440_1_) {
            case 1013:
            case 1018:
                if (this.mc.getRenderViewEntity() != null) {
                    double d0 = p_180440_2_.getX() - (this.mc.getRenderViewEntity()).posX;
                    double d1 = p_180440_2_.getY() - (this.mc.getRenderViewEntity()).posY;
                    double d2 = p_180440_2_.getZ() - (this.mc.getRenderViewEntity()).posZ;
                    double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    double d4 = (this.mc.getRenderViewEntity()).posX;
                    double d5 = (this.mc.getRenderViewEntity()).posY;
                    double d6 = (this.mc.getRenderViewEntity()).posZ;
                    if (d3 > 0.0D) {
                        d4 += d0 / d3 * 2.0D;
                        d5 += d1 / d3 * 2.0D;
                        d6 += d2 / d3 * 2.0D;
                    } 
                    if (p_180440_1_ == 1013) {
                        this.theWorld.playSound(d4, d5, d6, "mob.wither.spawn", 1.0F, 1.0F, false);
                        break;
                    } 
                    this.theWorld.playSound(d4, d5, d6, "mob.enderdragon.end", 5.0F, 1.0F, false);
                } 
                break;
        } 
    }
    
    public void playAuxSFX(EntityPlayer player, int sfxType, BlockPos blockPosIn, int p_180439_4_) {
        int k, l;
        double d13, d15, d19;
        int l1;
        Block block;
        double d11, d12, d14;
        int i1, j1;
        float f, f1, f2;
        EnumParticleTypes enumparticletypes;
        int k1;
        double var7, var9, var11;
        int var13;
        double var32;
        int var18;
        Random random = this.theWorld.rand;
        switch (sfxType) {
            case 1000:
                this.theWorld.playSoundAtPos(blockPosIn, "random.click", 1.0F, 1.0F, false);
                break;
            case 1001:
                this.theWorld.playSoundAtPos(blockPosIn, "random.click", 1.0F, 1.2F, false);
                break;
            case 1002:
                this.theWorld.playSoundAtPos(blockPosIn, "random.bow", 1.0F, 1.2F, false);
                break;
            case 1003:
                this.theWorld.playSoundAtPos(blockPosIn, "random.door_open", 1.0F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1004:
                this.theWorld.playSoundAtPos(blockPosIn, "random.fizz", 0.5F, 2.6F + (random.nextFloat() - random.nextFloat()) * 0.8F, false);
                break;
            case 1005:
                if (Item.getItemById(p_180439_4_) instanceof ItemRecord) {
                    this.theWorld.playRecord(blockPosIn, "records." + ((ItemRecord)Item.getItemById(p_180439_4_)).recordName);
                    break;
                } 
                this.theWorld.playRecord(blockPosIn, null);
                break;
            case 1006:
                this.theWorld.playSoundAtPos(blockPosIn, "random.door_close", 1.0F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1007:
                this.theWorld.playSoundAtPos(blockPosIn, "mob.ghast.charge", 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1008:
                this.theWorld.playSoundAtPos(blockPosIn, "mob.ghast.fireball", 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1009:
                this.theWorld.playSoundAtPos(blockPosIn, "mob.ghast.fireball", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1010:
                this.theWorld.playSoundAtPos(blockPosIn, "mob.zombie.wood", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1011:
                this.theWorld.playSoundAtPos(blockPosIn, "mob.zombie.metal", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1012:
                this.theWorld.playSoundAtPos(blockPosIn, "mob.zombie.woodbreak", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1014:
                this.theWorld.playSoundAtPos(blockPosIn, "mob.wither.shoot", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1015:
                this.theWorld.playSoundAtPos(blockPosIn, "mob.bat.takeoff", 0.05F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1016:
                this.theWorld.playSoundAtPos(blockPosIn, "mob.zombie.infect", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1017:
                this.theWorld.playSoundAtPos(blockPosIn, "mob.zombie.unfect", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
                break;
            case 1020:
                this.theWorld.playSoundAtPos(blockPosIn, "random.anvil_break", 1.0F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1021:
                this.theWorld.playSoundAtPos(blockPosIn, "random.anvil_use", 1.0F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 1022:
                this.theWorld.playSoundAtPos(blockPosIn, "random.anvil_land", 0.3F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 2000:
                k = p_180439_4_ % 3 - 1;
                l = p_180439_4_ / 3 % 3 - 1;
                d13 = blockPosIn.getX() + k * 0.6D + 0.5D;
                d15 = blockPosIn.getY() + 0.5D;
                d19 = blockPosIn.getZ() + l * 0.6D + 0.5D;
                for (l1 = 0; l1 < 10; l1++) {
                    double d20 = random.nextDouble() * 0.2D + 0.01D;
                    double d21 = d13 + k * 0.01D + (random.nextDouble() - 0.5D) * l * 0.5D;
                    double d22 = d15 + (random.nextDouble() - 0.5D) * 0.5D;
                    double d23 = d19 + l * 0.01D + (random.nextDouble() - 0.5D) * k * 0.5D;
                    double d24 = k * d20 + random.nextGaussian() * 0.01D;
                    double d9 = -0.03D + random.nextGaussian() * 0.01D;
                    double d10 = l * d20 + random.nextGaussian() * 0.01D;
                    spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d21, d22, d23, d24, d9, d10, new int[0]);
                } 
                return;
            case 2001:
                block = Block.getBlockById(p_180439_4_ & 0xFFF);
                if (block.getMaterial() != Material.air)
                    this.mc.getSoundHandler().playSound((ISound)new PositionedSoundRecord(new ResourceLocation(block.stepSound.getBreakSound()), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getFrequency() * 0.8F, blockPosIn.getX() + 0.5F, blockPosIn.getY() + 0.5F, blockPosIn.getZ() + 0.5F)); 
                this.mc.effectRenderer.addBlockDestroyEffects(blockPosIn, block.getStateFromMeta(p_180439_4_ >> 12 & 0xFF));
                break;
            case 2002:
                d11 = blockPosIn.getX();
                d12 = blockPosIn.getY();
                d14 = blockPosIn.getZ();
                for (i1 = 0; i1 < 8; i1++) {
                    spawnParticle(EnumParticleTypes.ITEM_CRACK, d11, d12, d14, random.nextGaussian() * 0.15D, random.nextDouble() * 0.2D, random.nextGaussian() * 0.15D, new int[] { Item.getIdFromItem((Item)Items.potionitem), p_180439_4_ });
                } 
                j1 = Items.potionitem.getColorFromDamage(p_180439_4_);
                f = (j1 >> 16 & 0xFF) / 255.0F;
                f1 = (j1 >> 8 & 0xFF) / 255.0F;
                f2 = (j1 >> 0 & 0xFF) / 255.0F;
                enumparticletypes = EnumParticleTypes.SPELL;
                if (Items.potionitem.isEffectInstant(p_180439_4_))
                    enumparticletypes = EnumParticleTypes.SPELL_INSTANT; 
                for (k1 = 0; k1 < 100; k1++) {
                    double d16 = random.nextDouble() * 4.0D;
                    double d17 = random.nextDouble() * Math.PI * 2.0D;
                    double d18 = Math.cos(d17) * d16;
                    double d7 = 0.01D + random.nextDouble() * 0.5D;
                    double d8 = Math.sin(d17) * d16;
                    EntityFX entityfx = spawnEntityFX(enumparticletypes.getParticleID(), enumparticletypes.getShouldIgnoreRange(), d11 + d18 * 0.1D, d12 + 0.3D, d14 + d8 * 0.1D, d18, d7, d8, new int[0]);
                    if (entityfx != null) {
                        float f3 = 0.75F + random.nextFloat() * 0.25F;
                        entityfx.setRBGColorF(f * f3, f1 * f3, f2 * f3);
                        entityfx.multiplyVelocity((float)d16);
                    } 
                } 
                this.theWorld.playSoundAtPos(blockPosIn, "game.potion.smash", 1.0F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
                break;
            case 2003:
                var7 = blockPosIn.getX() + 0.5D;
                var9 = blockPosIn.getY();
                var11 = blockPosIn.getZ() + 0.5D;
                for (var13 = 0; var13 < 8; var13++) {
                    spawnParticle(EnumParticleTypes.ITEM_CRACK, var7, var9, var11, random.nextGaussian() * 0.15D, random.nextDouble() * 0.2D, random.nextGaussian() * 0.15D, new int[] { Item.getIdFromItem(Items.ender_eye) });
                } 
                for (var32 = 0.0D; var32 < 6.283185307179586D; var32 += 0.15707963267948966D) {
                    spawnParticle(EnumParticleTypes.PORTAL, var7 + Math.cos(var32) * 5.0D, var9 - 0.4D, var11 + Math.sin(var32) * 5.0D, Math.cos(var32) * -5.0D, 0.0D, Math.sin(var32) * -5.0D, new int[0]);
                    spawnParticle(EnumParticleTypes.PORTAL, var7 + Math.cos(var32) * 5.0D, var9 - 0.4D, var11 + Math.sin(var32) * 5.0D, Math.cos(var32) * -7.0D, 0.0D, Math.sin(var32) * -7.0D, new int[0]);
                } 
                return;
            case 2004:
                for (var18 = 0; var18 < 20; var18++) {
                    double d3 = blockPosIn.getX() + 0.5D + (this.theWorld.rand.nextFloat() - 0.5D) * 2.0D;
                    double d4 = blockPosIn.getY() + 0.5D + (this.theWorld.rand.nextFloat() - 0.5D) * 2.0D;
                    double d5 = blockPosIn.getZ() + 0.5D + (this.theWorld.rand.nextFloat() - 0.5D) * 2.0D;
                    this.theWorld.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d3, d4, d5, 0.0D, 0.0D, 0.0D, new int[0]);
                    this.theWorld.spawnParticle(EnumParticleTypes.FLAME, d3, d4, d5, 0.0D, 0.0D, 0.0D, new int[0]);
                } 
                return;
            case 2005:
                ItemDye.spawnBonemealParticles((World)this.theWorld, blockPosIn, p_180439_4_);
                break;
        } 
    }
    
    public void sendBlockBreakProgress(int breakerId, BlockPos pos, int progress) {
        if (progress >= 0 && progress < 10) {
            DestroyBlockProgress destroyblockprogress = this.damagedBlocks.get(Integer.valueOf(breakerId));
            if (destroyblockprogress == null || destroyblockprogress.getPosition().getX() != pos.getX() || destroyblockprogress.getPosition().getY() != pos.getY() || destroyblockprogress.getPosition().getZ() != pos.getZ()) {
                destroyblockprogress = new DestroyBlockProgress(breakerId, pos);
                this.damagedBlocks.put(Integer.valueOf(breakerId), destroyblockprogress);
            } 
            destroyblockprogress.setPartialBlockDamage(progress);
            destroyblockprogress.setCloudUpdateTick(this.cloudTickCounter);
        } else {
            this.damagedBlocks.remove(Integer.valueOf(breakerId));
        } 
    }
    
    public void setDisplayListEntitiesDirty() {
        this.displayListEntitiesDirty = true;
    }
    
    public void resetClouds() {
        this.cloudRenderer.reset();
    }
    
    public int getCountRenderers() {
        return this.viewFrustum.renderChunks.length;
    }
    
    public int getCountActiveRenderers() {
        return this.renderInfos.size();
    }
    
    public int getCountEntitiesRendered() {
        return this.countEntitiesRendered;
    }
    
    public int getCountTileEntitiesRendered() {
        return this.countTileEntitiesRendered;
    }
    
    public RenderChunk getRenderChunk(BlockPos p_getRenderChunk_1_) {
        return this.viewFrustum.getRenderChunk(p_getRenderChunk_1_);
    }
    
    public RenderChunk getRenderChunk(RenderChunk p_getRenderChunk_1_, EnumFacing p_getRenderChunk_2_) {
        if (p_getRenderChunk_1_ == null)
            return null; 
        BlockPos blockpos = p_getRenderChunk_1_.func_181701_a(p_getRenderChunk_2_);
        return this.viewFrustum.getRenderChunk(blockpos);
    }
    
    public WorldClient getWorld() {
        return this.theWorld;
    }
    
    public void func_181023_a(Collection<?> p_181023_1_, Collection<? extends TileEntity> p_181023_2_) {
        synchronized (this.setTileEntities) {
            this.setTileEntities.removeAll(p_181023_1_);
            this.setTileEntities.addAll(p_181023_2_);
        } 
    }
    
    static final class RenderGlobal$2 {
        static final int[] field_178037_a = new int[(VertexFormatElement.EnumUsage.values()).length];
        
        private static final String __OBFID = "CL_00002535";
        
        static {
            try {
                field_178037_a[VertexFormatElement.EnumUsage.POSITION.ordinal()] = 1;
            } catch (NoSuchFieldError noSuchFieldError) {}
            try {
                field_178037_a[VertexFormatElement.EnumUsage.UV.ordinal()] = 2;
            } catch (NoSuchFieldError noSuchFieldError) {}
            try {
                field_178037_a[VertexFormatElement.EnumUsage.COLOR.ordinal()] = 3;
            } catch (NoSuchFieldError noSuchFieldError) {}
        }
    }
    
    public static class ContainerLocalRenderInformation {
        final RenderChunk renderChunk;
        
        final EnumFacing facing;
        
        final Set setFacing;
        
        final int counter;
        
        private static final String __OBFID = "CL_00002534";
        
        public ContainerLocalRenderInformation(RenderChunk p_i5_1_, EnumFacing p_i5_2_, int p_i5_3_) {
            this.setFacing = EnumSet.noneOf(EnumFacing.class);
            this.renderChunk = p_i5_1_;
            this.facing = p_i5_2_;
            this.counter = p_i5_3_;
        }
        
        ContainerLocalRenderInformation(RenderChunk p_i6_1_, EnumFacing p_i6_2_, int p_i6_3_, Object p_i6_4_) {
            this(p_i6_1_, p_i6_2_, p_i6_3_);
        }
    }
}

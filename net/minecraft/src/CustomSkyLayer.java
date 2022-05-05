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

package net.minecraft.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class CustomSkyLayer {
    public String source = null;
    
    private int startFadeIn = -1;
    
    private int endFadeIn = -1;
    
    private int startFadeOut = -1;
    
    private int endFadeOut = -1;
    
    private int blend = 1;
    
    private boolean rotate = false;
    
    private float speed = 1.0F;
    
    private float[] axis;
    
    private RangeListInt days;
    
    private int daysLoop;
    
    private boolean weatherClear;
    
    private boolean weatherRain;
    
    private boolean weatherThunder;
    
    public BiomeGenBase[] biomes;
    
    public RangeListInt heights;
    
    private float transition;
    
    private SmoothFloat smoothPositionBrightness;
    
    public int textureId;
    
    private World lastWorld;
    
    public static final float[] DEFAULT_AXIS = new float[] { 1.0F, 0.0F, 0.0F };
    
    private static final String WEATHER_CLEAR = "clear";
    
    private static final String WEATHER_RAIN = "rain";
    
    private static final String WEATHER_THUNDER = "thunder";
    
    public CustomSkyLayer(Properties p_i41_1_, String p_i41_2_) {
        this.axis = DEFAULT_AXIS;
        this.days = null;
        this.daysLoop = 8;
        this.weatherClear = true;
        this.weatherRain = false;
        this.weatherThunder = false;
        this.biomes = null;
        this.heights = null;
        this.transition = 1.0F;
        this.smoothPositionBrightness = null;
        this.textureId = -1;
        this.lastWorld = null;
        ConnectedParser connectedparser = new ConnectedParser("CustomSky");
        this.source = p_i41_1_.getProperty("source", p_i41_2_);
        this.startFadeIn = parseTime(p_i41_1_.getProperty("startFadeIn"));
        this.endFadeIn = parseTime(p_i41_1_.getProperty("endFadeIn"));
        this.startFadeOut = parseTime(p_i41_1_.getProperty("startFadeOut"));
        this.endFadeOut = parseTime(p_i41_1_.getProperty("endFadeOut"));
        this.blend = Blender.parseBlend(p_i41_1_.getProperty("blend"));
        this.rotate = parseBoolean(p_i41_1_.getProperty("rotate"), true);
        this.speed = parseFloat(p_i41_1_.getProperty("speed"), 1.0F);
        this.axis = parseAxis(p_i41_1_.getProperty("axis"), DEFAULT_AXIS);
        this.days = connectedparser.parseRangeListInt(p_i41_1_.getProperty("days"));
        this.daysLoop = connectedparser.parseInt(p_i41_1_.getProperty("daysLoop"), 8);
        List<String> list = parseWeatherList(p_i41_1_.getProperty("weather", "clear"));
        this.weatherClear = list.contains("clear");
        this.weatherRain = list.contains("rain");
        this.weatherThunder = list.contains("thunder");
        this.biomes = connectedparser.parseBiomes(p_i41_1_.getProperty("biomes"));
        this.heights = connectedparser.parseRangeListInt(p_i41_1_.getProperty("heights"));
        this.transition = parseFloat(p_i41_1_.getProperty("transition"), 1.0F);
    }
    
    private List<String> parseWeatherList(String p_parseWeatherList_1_) {
        List<String> list = Arrays.asList(new String[] { "clear", "rain", "thunder" });
        List<String> list1 = new ArrayList<>();
        String[] astring = Config.tokenize(p_parseWeatherList_1_, " ");
        for (int i = 0; i < astring.length; i++) {
            String s = astring[i];
            if (!list.contains(s)) {
                Config.warn("Unknown weather: " + s);
            } else {
                list1.add(s);
            } 
        } 
        return list1;
    }
    
    private int parseTime(String p_parseTime_1_) {
        if (p_parseTime_1_ == null)
            return -1; 
        String[] astring = Config.tokenize(p_parseTime_1_, ":");
        if (astring.length != 2) {
            Config.warn("Invalid time: " + p_parseTime_1_);
            return -1;
        } 
        String s = astring[0];
        String s1 = astring[1];
        int i = Config.parseInt(s, -1);
        int j = Config.parseInt(s1, -1);
        if (i >= 0 && i <= 23 && j >= 0 && j <= 59) {
            i -= 6;
            if (i < 0)
                i += 24; 
            int k = i * 1000 + (int)(j / 60.0D * 1000.0D);
            return k;
        } 
        Config.warn("Invalid time: " + p_parseTime_1_);
        return -1;
    }
    
    private boolean parseBoolean(String p_parseBoolean_1_, boolean p_parseBoolean_2_) {
        if (p_parseBoolean_1_ == null)
            return p_parseBoolean_2_; 
        if (p_parseBoolean_1_.toLowerCase().equals("true"))
            return true; 
        if (p_parseBoolean_1_.toLowerCase().equals("false"))
            return false; 
        Config.warn("Unknown boolean: " + p_parseBoolean_1_);
        return p_parseBoolean_2_;
    }
    
    private float parseFloat(String p_parseFloat_1_, float p_parseFloat_2_) {
        if (p_parseFloat_1_ == null)
            return p_parseFloat_2_; 
        float f = Config.parseFloat(p_parseFloat_1_, Float.MIN_VALUE);
        if (f == Float.MIN_VALUE) {
            Config.warn("Invalid value: " + p_parseFloat_1_);
            return p_parseFloat_2_;
        } 
        return f;
    }
    
    private float[] parseAxis(String p_parseAxis_1_, float[] p_parseAxis_2_) {
        if (p_parseAxis_1_ == null)
            return p_parseAxis_2_; 
        String[] astring = Config.tokenize(p_parseAxis_1_, " ");
        if (astring.length != 3) {
            Config.warn("Invalid axis: " + p_parseAxis_1_);
            return p_parseAxis_2_;
        } 
        float[] afloat = new float[3];
        for (int i = 0; i < astring.length; i++) {
            afloat[i] = Config.parseFloat(astring[i], Float.MIN_VALUE);
            if (afloat[i] == Float.MIN_VALUE) {
                Config.warn("Invalid axis: " + p_parseAxis_1_);
                return p_parseAxis_2_;
            } 
            if (afloat[i] < -1.0F || afloat[i] > 1.0F) {
                Config.warn("Invalid axis values: " + p_parseAxis_1_);
                return p_parseAxis_2_;
            } 
        } 
        float f2 = afloat[0];
        float f = afloat[1];
        float f1 = afloat[2];
        if (f2 * f2 + f * f + f1 * f1 < 1.0E-5F) {
            Config.warn("Invalid axis values: " + p_parseAxis_1_);
            return p_parseAxis_2_;
        } 
        float[] afloat1 = { f1, f, -f2 };
        return afloat1;
    }
    
    public boolean isValid(String p_isValid_1_) {
        if (this.source == null) {
            Config.warn("No source texture: " + p_isValid_1_);
            return false;
        } 
        this.source = TextureUtils.fixResourcePath(this.source, TextureUtils.getBasePath(p_isValid_1_));
        if (this.startFadeIn >= 0 && this.endFadeIn >= 0 && this.endFadeOut >= 0) {
            int i = normalizeTime(this.endFadeIn - this.startFadeIn);
            if (this.startFadeOut < 0) {
                this.startFadeOut = normalizeTime(this.endFadeOut - i);
                if (timeBetween(this.startFadeOut, this.startFadeIn, this.endFadeIn))
                    this.startFadeOut = this.endFadeIn; 
            } 
            int j = normalizeTime(this.startFadeOut - this.endFadeIn);
            int k = normalizeTime(this.endFadeOut - this.startFadeOut);
            int l = normalizeTime(this.startFadeIn - this.endFadeOut);
            int i1 = i + j + k + l;
            if (i1 != 24000) {
                Config.warn("Invalid fadeIn/fadeOut times, sum is not 24h: " + i1);
                return false;
            } 
            if (this.speed < 0.0F) {
                Config.warn("Invalid speed: " + this.speed);
                return false;
            } 
            if (this.daysLoop <= 0) {
                Config.warn("Invalid daysLoop: " + this.daysLoop);
                return false;
            } 
            return true;
        } 
        Config.warn("Invalid times, required are: startFadeIn, endFadeIn and endFadeOut.");
        return false;
    }
    
    private int normalizeTime(int p_normalizeTime_1_) {
        while (p_normalizeTime_1_ >= 24000)
            p_normalizeTime_1_ -= 24000; 
        while (p_normalizeTime_1_ < 0)
            p_normalizeTime_1_ += 24000; 
        return p_normalizeTime_1_;
    }
    
    public void render(World p_render_1_, int p_render_2_, float p_render_3_, float p_render_4_, float p_render_5_) {
        float f = getPositionBrightness(p_render_1_);
        float f1 = getWeatherBrightness(p_render_4_, p_render_5_);
        float f2 = getFadeBrightness(p_render_2_);
        float f3 = f * f1 * f2;
        f3 = Config.limit(f3, 0.0F, 1.0F);
        if (f3 >= 1.0E-4F) {
            GlStateManager.bindTexture(this.textureId);
            Blender.setupBlend(this.blend, f3);
            GlStateManager.pushMatrix();
            if (this.rotate) {
                float f4 = 0.0F;
                if (this.speed != Math.round(this.speed)) {
                    long i = (p_render_1_.getWorldTime() + 18000L) / 24000L;
                    double d0 = (this.speed % 1.0F);
                    double d1 = i * d0;
                    f4 = (float)(d1 % 1.0D);
                } 
                GlStateManager.rotate(360.0F * (f4 + p_render_3_ * this.speed), this.axis[0], this.axis[1], this.axis[2]);
            } 
            Tessellator tessellator = Tessellator.getInstance();
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
            renderSide(tessellator, 4);
            GlStateManager.pushMatrix();
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            renderSide(tessellator, 1);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
            renderSide(tessellator, 0);
            GlStateManager.popMatrix();
            GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            renderSide(tessellator, 5);
            GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            renderSide(tessellator, 2);
            GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            renderSide(tessellator, 3);
            GlStateManager.popMatrix();
        } 
    }
    
    private float getPositionBrightness(World p_getPositionBrightness_1_) {
        if (this.biomes == null && this.heights == null)
            return 1.0F; 
        float f = getPositionBrightnessRaw(p_getPositionBrightness_1_);
        if (this.smoothPositionBrightness == null)
            this.smoothPositionBrightness = new SmoothFloat(f, this.transition); 
        f = this.smoothPositionBrightness.getSmoothValue(f);
        return f;
    }
    
    private float getPositionBrightnessRaw(World p_getPositionBrightnessRaw_1_) {
        Entity entity = Minecraft.getMinecraft().getRenderViewEntity();
        if (entity == null)
            return 0.0F; 
        BlockPos blockpos = entity.getPosition();
        if (this.biomes != null) {
            BiomeGenBase biomegenbase = p_getPositionBrightnessRaw_1_.getBiomeGenForCoords(blockpos);
            if (biomegenbase == null)
                return 0.0F; 
            if (!Matches.biome(biomegenbase, this.biomes))
                return 0.0F; 
        } 
        return (this.heights != null && !this.heights.isInRange(blockpos.getY())) ? 0.0F : 1.0F;
    }
    
    private float getWeatherBrightness(float p_getWeatherBrightness_1_, float p_getWeatherBrightness_2_) {
        float f = 1.0F - p_getWeatherBrightness_1_;
        float f1 = p_getWeatherBrightness_1_ - p_getWeatherBrightness_2_;
        float f2 = 0.0F;
        if (this.weatherClear)
            f2 += f; 
        if (this.weatherRain)
            f2 += f1; 
        if (this.weatherThunder)
            f2 += p_getWeatherBrightness_2_; 
        f2 = NumUtils.limit(f2, 0.0F, 1.0F);
        return f2;
    }
    
    private float getFadeBrightness(int p_getFadeBrightness_1_) {
        if (timeBetween(p_getFadeBrightness_1_, this.startFadeIn, this.endFadeIn)) {
            int k = normalizeTime(this.endFadeIn - this.startFadeIn);
            int l = normalizeTime(p_getFadeBrightness_1_ - this.startFadeIn);
            return l / k;
        } 
        if (timeBetween(p_getFadeBrightness_1_, this.endFadeIn, this.startFadeOut))
            return 1.0F; 
        if (timeBetween(p_getFadeBrightness_1_, this.startFadeOut, this.endFadeOut)) {
            int i = normalizeTime(this.endFadeOut - this.startFadeOut);
            int j = normalizeTime(p_getFadeBrightness_1_ - this.startFadeOut);
            return 1.0F - j / i;
        } 
        return 0.0F;
    }
    
    private void renderSide(Tessellator p_renderSide_1_, int p_renderSide_2_) {
        WorldRenderer worldrenderer = p_renderSide_1_.getWorldRenderer();
        double d0 = (p_renderSide_2_ % 3) / 3.0D;
        double d1 = (p_renderSide_2_ / 3) / 2.0D;
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(-100.0D, -100.0D, -100.0D).tex(d0, d1).endVertex();
        worldrenderer.pos(-100.0D, -100.0D, 100.0D).tex(d0, d1 + 0.5D).endVertex();
        worldrenderer.pos(100.0D, -100.0D, 100.0D).tex(d0 + 0.3333333333333333D, d1 + 0.5D).endVertex();
        worldrenderer.pos(100.0D, -100.0D, -100.0D).tex(d0 + 0.3333333333333333D, d1).endVertex();
        p_renderSide_1_.draw();
    }
    
    public boolean isActive(World p_isActive_1_, int p_isActive_2_) {
        if (p_isActive_1_ != this.lastWorld) {
            this.lastWorld = p_isActive_1_;
            this.smoothPositionBrightness = null;
        } 
        if (timeBetween(p_isActive_2_, this.endFadeOut, this.startFadeIn))
            return false; 
        if (this.days != null) {
            long i = p_isActive_1_.getWorldTime();
            long j;
            for (j = i - this.startFadeIn; j < 0L; j += (24000 * this.daysLoop));
            int k = (int)(j / 24000L);
            int l = k % this.daysLoop;
            if (!this.days.isInRange(l))
                return false; 
        } 
        return true;
    }
    
    private boolean timeBetween(int p_timeBetween_1_, int p_timeBetween_2_, int p_timeBetween_3_) {
        return (p_timeBetween_2_ <= p_timeBetween_3_) ? ((p_timeBetween_1_ >= p_timeBetween_2_ && p_timeBetween_1_ <= p_timeBetween_3_)) : (!(p_timeBetween_1_ < p_timeBetween_2_ && p_timeBetween_1_ > p_timeBetween_3_));
    }
    
    public String toString() {
        return this.source + ", " + this.startFadeIn + "-" + this.endFadeIn + " " + this.startFadeOut + "-" + this.endFadeOut;
    }
}

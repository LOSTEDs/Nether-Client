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

package net.minecraft.stats;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S37PacketStatistics;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IJsonSerializable;
import net.minecraft.util.TupleIntJsonSerializable;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StatisticsFile extends StatFileWriter {
    private static final Logger logger = LogManager.getLogger();
    
    private final MinecraftServer mcServer;
    
    private final File statsFile;
    
    private final Set<StatBase> field_150888_e = Sets.newHashSet();
    
    private int field_150885_f = -300;
    
    private boolean field_150886_g = false;
    
    public StatisticsFile(MinecraftServer serverIn, File statsFileIn) {
        this.mcServer = serverIn;
        this.statsFile = statsFileIn;
    }
    
    public void readStatFile() {
        if (this.statsFile.isFile())
            try {
                this.statsData.clear();
                this.statsData.putAll(parseJson(FileUtils.readFileToString(this.statsFile)));
            } catch (IOException ioexception) {
                logger.error("Couldn't read statistics file " + this.statsFile, ioexception);
            } catch (JsonParseException jsonparseexception) {
                logger.error("Couldn't parse statistics file " + this.statsFile, (Throwable)jsonparseexception);
            }  
    }
    
    public void saveStatFile() {
        try {
            FileUtils.writeStringToFile(this.statsFile, dumpJson(this.statsData));
        } catch (IOException ioexception) {
            logger.error("Couldn't save stats", ioexception);
        } 
    }
    
    public void unlockAchievement(EntityPlayer playerIn, StatBase statIn, int p_150873_3_) {
        int i = statIn.isAchievement() ? readStat(statIn) : 0;
        super.unlockAchievement(playerIn, statIn, p_150873_3_);
        this.field_150888_e.add(statIn);
        if (statIn.isAchievement() && i == 0 && p_150873_3_ > 0) {
            this.field_150886_g = true;
            if (this.mcServer.isAnnouncingPlayerAchievements())
                this.mcServer.getConfigurationManager().sendChatMsg((IChatComponent)new ChatComponentTranslation("chat.type.achievement", new Object[] { playerIn.getDisplayName(), statIn.func_150955_j() })); 
        } 
        if (statIn.isAchievement() && i > 0 && p_150873_3_ == 0) {
            this.field_150886_g = true;
            if (this.mcServer.isAnnouncingPlayerAchievements())
                this.mcServer.getConfigurationManager().sendChatMsg((IChatComponent)new ChatComponentTranslation("chat.type.achievement.taken", new Object[] { playerIn.getDisplayName(), statIn.func_150955_j() })); 
        } 
    }
    
    public Set<StatBase> func_150878_c() {
        Set<StatBase> set = Sets.newHashSet(this.field_150888_e);
        this.field_150888_e.clear();
        this.field_150886_g = false;
        return set;
    }
    
    public Map<StatBase, TupleIntJsonSerializable> parseJson(String p_150881_1_) {
        JsonElement jsonelement = (new JsonParser()).parse(p_150881_1_);
        if (!jsonelement.isJsonObject())
            return Maps.newHashMap(); 
        JsonObject jsonobject = jsonelement.getAsJsonObject();
        Map<StatBase, TupleIntJsonSerializable> map = Maps.newHashMap();
        for (Map.Entry<String, JsonElement> entry : (Iterable<Map.Entry<String, JsonElement>>)jsonobject.entrySet()) {
            StatBase statbase = StatList.getOneShotStat(entry.getKey());
            if (statbase != null) {
                TupleIntJsonSerializable tupleintjsonserializable = new TupleIntJsonSerializable();
                if (((JsonElement)entry.getValue()).isJsonPrimitive() && ((JsonElement)entry.getValue()).getAsJsonPrimitive().isNumber()) {
                    tupleintjsonserializable.setIntegerValue(((JsonElement)entry.getValue()).getAsInt());
                } else if (((JsonElement)entry.getValue()).isJsonObject()) {
                    JsonObject jsonobject1 = ((JsonElement)entry.getValue()).getAsJsonObject();
                    if (jsonobject1.has("value") && jsonobject1.get("value").isJsonPrimitive() && jsonobject1.get("value").getAsJsonPrimitive().isNumber())
                        tupleintjsonserializable.setIntegerValue(jsonobject1.getAsJsonPrimitive("value").getAsInt()); 
                    if (jsonobject1.has("progress") && statbase.func_150954_l() != null)
                        try {
                            Constructor<? extends IJsonSerializable> constructor = statbase.func_150954_l().getConstructor(new Class[0]);
                            IJsonSerializable ijsonserializable = constructor.newInstance(new Object[0]);
                            ijsonserializable.fromJson(jsonobject1.get("progress"));
                            tupleintjsonserializable.setJsonSerializableValue(ijsonserializable);
                        } catch (Throwable throwable) {
                            logger.warn("Invalid statistic progress in " + this.statsFile, throwable);
                        }  
                } 
                map.put(statbase, tupleintjsonserializable);
                continue;
            } 
            logger.warn("Invalid statistic in " + this.statsFile + ": Don't know what " + (String)entry.getKey() + " is");
        } 
        return map;
    }
    
    public static String dumpJson(Map<StatBase, TupleIntJsonSerializable> p_150880_0_) {
        JsonObject jsonobject = new JsonObject();
        for (Map.Entry<StatBase, TupleIntJsonSerializable> entry : p_150880_0_.entrySet()) {
            if (((TupleIntJsonSerializable)entry.getValue()).getJsonSerializableValue() != null) {
                JsonObject jsonobject1 = new JsonObject();
                jsonobject1.addProperty("value", Integer.valueOf(((TupleIntJsonSerializable)entry.getValue()).getIntegerValue()));
                try {
                    jsonobject1.add("progress", ((TupleIntJsonSerializable)entry.getValue()).getJsonSerializableValue().getSerializableElement());
                } catch (Throwable throwable) {
                    logger.warn("Couldn't save statistic " + ((StatBase)entry.getKey()).getStatName() + ": error serializing progress", throwable);
                } 
                jsonobject.add(((StatBase)entry.getKey()).statId, (JsonElement)jsonobject1);
                continue;
            } 
            jsonobject.addProperty(((StatBase)entry.getKey()).statId, Integer.valueOf(((TupleIntJsonSerializable)entry.getValue()).getIntegerValue()));
        } 
        return jsonobject.toString();
    }
    
    public void func_150877_d() {
        for (StatBase statbase : this.statsData.keySet())
            this.field_150888_e.add(statbase); 
    }
    
    public void func_150876_a(EntityPlayerMP p_150876_1_) {
        int i = this.mcServer.getTickCounter();
        Map<StatBase, Integer> map = Maps.newHashMap();
        if (this.field_150886_g || i - this.field_150885_f > 300) {
            this.field_150885_f = i;
            for (StatBase statbase : func_150878_c())
                map.put(statbase, Integer.valueOf(readStat(statbase))); 
        } 
        p_150876_1_.playerNetServerHandler.sendPacket((Packet)new S37PacketStatistics(map));
    }
    
    public void sendAchievements(EntityPlayerMP player) {
        Map<StatBase, Integer> map = Maps.newHashMap();
        for (Achievement achievement : AchievementList.achievementList) {
            if (hasAchievementUnlocked(achievement)) {
                map.put(achievement, Integer.valueOf(readStat(achievement)));
                this.field_150888_e.remove(achievement);
            } 
        } 
        player.playerNetServerHandler.sendPacket((Packet)new S37PacketStatistics(map));
    }
    
    public boolean func_150879_e() {
        return this.field_150886_g;
    }
}

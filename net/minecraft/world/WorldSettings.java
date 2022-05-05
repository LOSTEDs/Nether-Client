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

package net.minecraft.world;

import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.world.storage.WorldInfo;

public final class WorldSettings {
    private final long seed;
    
    private final GameType theGameType;
    
    private final boolean mapFeaturesEnabled;
    
    private final boolean hardcoreEnabled;
    
    private final WorldType terrainType;
    
    private boolean commandsAllowed;
    
    private boolean bonusChestEnabled;
    
    private String worldName;
    
    public WorldSettings(long seedIn, GameType gameType, boolean enableMapFeatures, boolean hardcoreMode, WorldType worldTypeIn) {
        this.worldName = "";
        this.seed = seedIn;
        this.theGameType = gameType;
        this.mapFeaturesEnabled = enableMapFeatures;
        this.hardcoreEnabled = hardcoreMode;
        this.terrainType = worldTypeIn;
    }
    
    public WorldSettings(WorldInfo info) {
        this(info.getSeed(), info.getGameType(), info.isMapFeaturesEnabled(), info.isHardcoreModeEnabled(), info.getTerrainType());
    }
    
    public WorldSettings enableBonusChest() {
        this.bonusChestEnabled = true;
        return this;
    }
    
    public WorldSettings enableCommands() {
        this.commandsAllowed = true;
        return this;
    }
    
    public WorldSettings setWorldName(String name) {
        this.worldName = name;
        return this;
    }
    
    public boolean isBonusChestEnabled() {
        return this.bonusChestEnabled;
    }
    
    public long getSeed() {
        return this.seed;
    }
    
    public GameType getGameType() {
        return this.theGameType;
    }
    
    public boolean getHardcoreEnabled() {
        return this.hardcoreEnabled;
    }
    
    public boolean isMapFeaturesEnabled() {
        return this.mapFeaturesEnabled;
    }
    
    public WorldType getTerrainType() {
        return this.terrainType;
    }
    
    public boolean areCommandsAllowed() {
        return this.commandsAllowed;
    }
    
    public static GameType getGameTypeById(int id) {
        return GameType.getByID(id);
    }
    
    public String getWorldName() {
        return this.worldName;
    }
    
    public enum GameType {
        NOT_SET(-1, ""),
        SURVIVAL(0, "survival"),
        CREATIVE(1, "creative"),
        ADVENTURE(2, "adventure"),
        SPECTATOR(3, "spectator");
        
        int id;
        
        String name;
        
        GameType(int typeId, String nameIn) {
            this.id = typeId;
            this.name = nameIn;
        }
        
        public int getID() {
            return this.id;
        }
        
        public String getName() {
            return this.name;
        }
        
        public void configurePlayerCapabilities(PlayerCapabilities capabilities) {
            if (this == CREATIVE) {
                capabilities.allowFlying = true;
                capabilities.isCreativeMode = true;
                capabilities.disableDamage = true;
            } else if (this == SPECTATOR) {
                capabilities.allowFlying = true;
                capabilities.isCreativeMode = false;
                capabilities.disableDamage = true;
                capabilities.isFlying = true;
            } else {
                capabilities.allowFlying = false;
                capabilities.isCreativeMode = false;
                capabilities.disableDamage = false;
                capabilities.isFlying = false;
            } 
            capabilities.allowEdit = !isAdventure();
        }
        
        public boolean isAdventure() {
            return !(this != ADVENTURE && this != SPECTATOR);
        }
        
        public boolean isCreative() {
            return (this == CREATIVE);
        }
        
        public boolean isSurvivalOrAdventure() {
            return !(this != SURVIVAL && this != ADVENTURE);
        }
        
        public static GameType getByID(int idIn) {
            byte b;
            int i;
            GameType[] arrayOfGameType;
            for (i = (arrayOfGameType = values()).length, b = 0; b < i; ) {
                GameType worldsettings$gametype = arrayOfGameType[b];
                if (worldsettings$gametype.id == idIn)
                    return worldsettings$gametype; 
                b++;
            } 
            return SURVIVAL;
        }
        
        public static GameType getByName(String p_77142_0_) {
            byte b;
            int i;
            GameType[] arrayOfGameType;
            for (i = (arrayOfGameType = values()).length, b = 0; b < i; ) {
                GameType worldsettings$gametype = arrayOfGameType[b];
                if (worldsettings$gametype.name.equals(p_77142_0_))
                    return worldsettings$gametype; 
                b++;
            } 
            return SURVIVAL;
        }
    }
}

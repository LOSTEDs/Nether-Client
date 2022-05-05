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

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IWorldNameable;
import net.minecraft.world.biome.BiomeGenBase;

public class CustomGuiProperties {
    private String fileName = null;
    
    private String basePath = null;
    
    private EnumContainer container = null;
    
    private Map<ResourceLocation, ResourceLocation> textureLocations = null;
    
    private NbtTagValue nbtName = null;
    
    private BiomeGenBase[] biomes = null;
    
    private RangeListInt heights = null;
    
    private Boolean large = null;
    
    private Boolean trapped = null;
    
    private Boolean christmas = null;
    
    private Boolean ender = null;
    
    private RangeListInt levels = null;
    
    private VillagerProfession[] professions = null;
    
    private EnumVariant[] variants = null;
    
    private EnumDyeColor[] colors = null;
    
    private static final EnumVariant[] VARIANTS_HORSE = new EnumVariant[] { EnumVariant.HORSE, EnumVariant.DONKEY, EnumVariant.MULE, EnumVariant.LLAMA };
    
    private static final EnumVariant[] VARIANTS_DISPENSER = new EnumVariant[] { EnumVariant.DISPENSER, EnumVariant.DROPPER };
    
    private static final EnumVariant[] VARIANTS_INVALID = new EnumVariant[0];
    
    private static final EnumDyeColor[] COLORS_INVALID = new EnumDyeColor[0];
    
    private static final ResourceLocation ANVIL_GUI_TEXTURE = new ResourceLocation("textures/gui/container/anvil.png");
    
    private static final ResourceLocation BEACON_GUI_TEXTURE = new ResourceLocation("textures/gui/container/beacon.png");
    
    private static final ResourceLocation BREWING_STAND_GUI_TEXTURE = new ResourceLocation("textures/gui/container/brewing_stand.png");
    
    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
    
    private static final ResourceLocation CRAFTING_TABLE_GUI_TEXTURE = new ResourceLocation("textures/gui/container/crafting_table.png");
    
    private static final ResourceLocation HORSE_GUI_TEXTURE = new ResourceLocation("textures/gui/container/horse.png");
    
    private static final ResourceLocation DISPENSER_GUI_TEXTURE = new ResourceLocation("textures/gui/container/dispenser.png");
    
    private static final ResourceLocation ENCHANTMENT_TABLE_GUI_TEXTURE = new ResourceLocation("textures/gui/container/enchanting_table.png");
    
    private static final ResourceLocation FURNACE_GUI_TEXTURE = new ResourceLocation("textures/gui/container/furnace.png");
    
    private static final ResourceLocation HOPPER_GUI_TEXTURE = new ResourceLocation("textures/gui/container/hopper.png");
    
    private static final ResourceLocation INVENTORY_GUI_TEXTURE = new ResourceLocation("textures/gui/container/inventory.png");
    
    private static final ResourceLocation SHULKER_BOX_GUI_TEXTURE = new ResourceLocation("textures/gui/container/shulker_box.png");
    
    private static final ResourceLocation VILLAGER_GUI_TEXTURE = new ResourceLocation("textures/gui/container/villager.png");
    
    public CustomGuiProperties(Properties p_i38_1_, String p_i38_2_) {
        ConnectedParser connectedparser = new ConnectedParser("CustomGuis");
        this.fileName = connectedparser.parseName(p_i38_2_);
        this.basePath = connectedparser.parseBasePath(p_i38_2_);
        this.container = (EnumContainer)connectedparser.parseEnum(p_i38_1_.getProperty("container"), (Enum[])EnumContainer.values(), "container");
        this.textureLocations = parseTextureLocations(p_i38_1_, "texture", this.container, "textures/gui/", this.basePath);
        this.nbtName = connectedparser.parseNbtTagValue("name", p_i38_1_.getProperty("name"));
        this.biomes = connectedparser.parseBiomes(p_i38_1_.getProperty("biomes"));
        this.heights = connectedparser.parseRangeListInt(p_i38_1_.getProperty("heights"));
        this.large = connectedparser.parseBooleanObject(p_i38_1_.getProperty("large"));
        this.trapped = connectedparser.parseBooleanObject(p_i38_1_.getProperty("trapped"));
        this.christmas = connectedparser.parseBooleanObject(p_i38_1_.getProperty("christmas"));
        this.ender = connectedparser.parseBooleanObject(p_i38_1_.getProperty("ender"));
        this.levels = connectedparser.parseRangeListInt(p_i38_1_.getProperty("levels"));
        this.professions = connectedparser.parseProfessions(p_i38_1_.getProperty("professions"));
        EnumVariant[] acustomguiproperties$enumvariant = getContainerVariants(this.container);
        this.variants = (EnumVariant[])connectedparser.parseEnums(p_i38_1_.getProperty("variants"), (Enum[])acustomguiproperties$enumvariant, "variants", (Enum[])VARIANTS_INVALID);
        this.colors = parseEnumDyeColors(p_i38_1_.getProperty("colors"));
    }
    
    private static EnumVariant[] getContainerVariants(EnumContainer p_getContainerVariants_0_) {
        return (p_getContainerVariants_0_ == EnumContainer.HORSE) ? VARIANTS_HORSE : ((p_getContainerVariants_0_ == EnumContainer.DISPENSER) ? VARIANTS_DISPENSER : new EnumVariant[0]);
    }
    
    private static EnumDyeColor[] parseEnumDyeColors(String p_parseEnumDyeColors_0_) {
        if (p_parseEnumDyeColors_0_ == null)
            return null; 
        p_parseEnumDyeColors_0_ = p_parseEnumDyeColors_0_.toLowerCase();
        String[] astring = Config.tokenize(p_parseEnumDyeColors_0_, " ");
        EnumDyeColor[] aenumdyecolor = new EnumDyeColor[astring.length];
        for (int i = 0; i < astring.length; i++) {
            String s = astring[i];
            EnumDyeColor enumdyecolor = parseEnumDyeColor(s);
            if (enumdyecolor == null) {
                warn("Invalid color: " + s);
                return COLORS_INVALID;
            } 
            aenumdyecolor[i] = enumdyecolor;
        } 
        return aenumdyecolor;
    }
    
    private static EnumDyeColor parseEnumDyeColor(String p_parseEnumDyeColor_0_) {
        if (p_parseEnumDyeColor_0_ == null)
            return null; 
        EnumDyeColor[] aenumdyecolor = EnumDyeColor.values();
        for (int i = 0; i < aenumdyecolor.length; i++) {
            EnumDyeColor enumdyecolor = aenumdyecolor[i];
            if (enumdyecolor.getName().equals(p_parseEnumDyeColor_0_))
                return enumdyecolor; 
            if (enumdyecolor.getUnlocalizedName().equals(p_parseEnumDyeColor_0_))
                return enumdyecolor; 
        } 
        return null;
    }
    
    private static ResourceLocation parseTextureLocation(String p_parseTextureLocation_0_, String p_parseTextureLocation_1_) {
        if (p_parseTextureLocation_0_ == null)
            return null; 
        p_parseTextureLocation_0_ = p_parseTextureLocation_0_.trim();
        String s = TextureUtils.fixResourcePath(p_parseTextureLocation_0_, p_parseTextureLocation_1_);
        if (!s.endsWith(".png"))
            s = String.valueOf(s) + ".png"; 
        return new ResourceLocation(String.valueOf(p_parseTextureLocation_1_) + "/" + s);
    }
    
    private static Map<ResourceLocation, ResourceLocation> parseTextureLocations(Properties p_parseTextureLocations_0_, String p_parseTextureLocations_1_, EnumContainer p_parseTextureLocations_2_, String p_parseTextureLocations_3_, String p_parseTextureLocations_4_) {
        Map<ResourceLocation, ResourceLocation> map = new HashMap<>();
        String s = p_parseTextureLocations_0_.getProperty(p_parseTextureLocations_1_);
        if (s != null) {
            ResourceLocation resourcelocation = getGuiTextureLocation(p_parseTextureLocations_2_);
            ResourceLocation resourcelocation1 = parseTextureLocation(s, p_parseTextureLocations_4_);
            if (resourcelocation != null && resourcelocation1 != null)
                map.put(resourcelocation, resourcelocation1); 
        } 
        String s5 = String.valueOf(p_parseTextureLocations_1_) + ".";
        for (Object a : p_parseTextureLocations_0_.keySet()) {
            String s1 = (String)a;
            if (s1.startsWith(s5)) {
                String s2 = s1.substring(s5.length());
                s2 = s2.replace('\\', '/');
                s2 = StrUtils.removePrefixSuffix(s2, "/", ".png");
                String s3 = String.valueOf(p_parseTextureLocations_3_) + s2 + ".png";
                String s4 = p_parseTextureLocations_0_.getProperty(s1);
                ResourceLocation resourcelocation2 = new ResourceLocation(s3);
                ResourceLocation resourcelocation3 = parseTextureLocation(s4, p_parseTextureLocations_4_);
                map.put(resourcelocation2, resourcelocation3);
            } 
        } 
        return map;
    }
    
    private static ResourceLocation getGuiTextureLocation(EnumContainer p_getGuiTextureLocation_0_) {
        switch (p_getGuiTextureLocation_0_) {
            case null:
                return ANVIL_GUI_TEXTURE;
            case BEACON:
                return BEACON_GUI_TEXTURE;
            case BREWING_STAND:
                return BREWING_STAND_GUI_TEXTURE;
            case CHEST:
                return CHEST_GUI_TEXTURE;
            case CRAFTING:
                return CRAFTING_TABLE_GUI_TEXTURE;
            case CREATIVE:
                return null;
            case DISPENSER:
                return DISPENSER_GUI_TEXTURE;
            case ENCHANTMENT:
                return ENCHANTMENT_TABLE_GUI_TEXTURE;
            case FURNACE:
                return FURNACE_GUI_TEXTURE;
            case HOPPER:
                return HOPPER_GUI_TEXTURE;
            case HORSE:
                return HORSE_GUI_TEXTURE;
            case INVENTORY:
                return INVENTORY_GUI_TEXTURE;
            case SHULKER_BOX:
                return SHULKER_BOX_GUI_TEXTURE;
            case VILLAGER:
                return VILLAGER_GUI_TEXTURE;
        } 
        return null;
    }
    
    public boolean isValid(String p_isValid_1_) {
        if (this.fileName != null && this.fileName.length() > 0) {
            if (this.basePath == null) {
                warn("No base path found: " + p_isValid_1_);
                return false;
            } 
            if (this.container == null) {
                warn("No container found: " + p_isValid_1_);
                return false;
            } 
            if (this.textureLocations.isEmpty()) {
                warn("No texture found: " + p_isValid_1_);
                return false;
            } 
            if (this.professions == ConnectedParser.PROFESSIONS_INVALID) {
                warn("Invalid professions or careers: " + p_isValid_1_);
                return false;
            } 
            if (this.variants == VARIANTS_INVALID) {
                warn("Invalid variants: " + p_isValid_1_);
                return false;
            } 
            if (this.colors == COLORS_INVALID) {
                warn("Invalid colors: " + p_isValid_1_);
                return false;
            } 
            return true;
        } 
        warn("No name found: " + p_isValid_1_);
        return false;
    }
    
    private static void warn(String p_warn_0_) {
        Config.warn("[CustomGuis] " + p_warn_0_);
    }
    
    private boolean matchesGeneral(EnumContainer p_matchesGeneral_1_, BlockPos p_matchesGeneral_2_, IBlockAccess p_matchesGeneral_3_) {
        if (this.container != p_matchesGeneral_1_)
            return false; 
        if (this.biomes != null) {
            BiomeGenBase biomegenbase = p_matchesGeneral_3_.getBiomeGenForCoords(p_matchesGeneral_2_);
            if (!Matches.biome(biomegenbase, this.biomes))
                return false; 
        } 
        return !(this.heights != null && !this.heights.isInRange(p_matchesGeneral_2_.getY()));
    }
    
    public boolean matchesPos(EnumContainer p_matchesPos_1_, BlockPos p_matchesPos_2_, IBlockAccess p_matchesPos_3_, GuiScreen p_matchesPos_4_) {
        if (!matchesGeneral(p_matchesPos_1_, p_matchesPos_2_, p_matchesPos_3_))
            return false; 
        if (this.nbtName != null) {
            String s = getName(p_matchesPos_4_);
            if (!this.nbtName.matchesValue(s))
                return false; 
        } 
        switch (p_matchesPos_1_) {
            case BEACON:
                return matchesBeacon(p_matchesPos_2_, p_matchesPos_3_);
            case CHEST:
                return matchesChest(p_matchesPos_2_, p_matchesPos_3_);
            case DISPENSER:
                return matchesDispenser(p_matchesPos_2_, p_matchesPos_3_);
        } 
        return true;
    }
    
    public static String getName(GuiScreen p_getName_0_) {
        IWorldNameable iworldnameable = getWorldNameable(p_getName_0_);
        return (iworldnameable == null) ? null : iworldnameable.getDisplayName().getUnformattedText();
    }
    
    private static IWorldNameable getWorldNameable(GuiScreen p_getWorldNameable_0_) {
        return (p_getWorldNameable_0_ instanceof net.minecraft.client.gui.inventory.GuiBeacon) ? getWorldNameable(p_getWorldNameable_0_, Reflector.GuiBeacon_tileBeacon) : ((p_getWorldNameable_0_ instanceof net.minecraft.client.gui.inventory.GuiBrewingStand) ? getWorldNameable(p_getWorldNameable_0_, Reflector.GuiBrewingStand_tileBrewingStand) : ((p_getWorldNameable_0_ instanceof net.minecraft.client.gui.inventory.GuiChest) ? getWorldNameable(p_getWorldNameable_0_, Reflector.GuiChest_lowerChestInventory) : ((p_getWorldNameable_0_ instanceof GuiDispenser) ? (IWorldNameable)((GuiDispenser)p_getWorldNameable_0_).dispenserInventory : ((p_getWorldNameable_0_ instanceof net.minecraft.client.gui.GuiEnchantment) ? getWorldNameable(p_getWorldNameable_0_, Reflector.GuiEnchantment_nameable) : ((p_getWorldNameable_0_ instanceof net.minecraft.client.gui.inventory.GuiFurnace) ? getWorldNameable(p_getWorldNameable_0_, Reflector.GuiFurnace_tileFurnace) : ((p_getWorldNameable_0_ instanceof net.minecraft.client.gui.GuiHopper) ? getWorldNameable(p_getWorldNameable_0_, Reflector.GuiHopper_hopperInventory) : null))))));
    }
    
    private static IWorldNameable getWorldNameable(GuiScreen p_getWorldNameable_0_, ReflectorField p_getWorldNameable_1_) {
        Object object = Reflector.getFieldValue(p_getWorldNameable_0_, p_getWorldNameable_1_);
        return !(object instanceof IWorldNameable) ? null : (IWorldNameable)object;
    }
    
    private boolean matchesBeacon(BlockPos p_matchesBeacon_1_, IBlockAccess p_matchesBeacon_2_) {
        TileEntity tileentity = p_matchesBeacon_2_.getTileEntity(p_matchesBeacon_1_);
        if (!(tileentity instanceof TileEntityBeacon))
            return false; 
        TileEntityBeacon tileentitybeacon = (TileEntityBeacon)tileentity;
        if (this.levels != null) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            tileentitybeacon.writeToNBT(nbttagcompound);
            int i = nbttagcompound.getInteger("Levels");
            if (!this.levels.isInRange(i))
                return false; 
        } 
        return true;
    }
    
    private boolean matchesChest(BlockPos p_matchesChest_1_, IBlockAccess p_matchesChest_2_) {
        TileEntity tileentity = p_matchesChest_2_.getTileEntity(p_matchesChest_1_);
        if (tileentity instanceof TileEntityChest) {
            TileEntityChest tileentitychest = (TileEntityChest)tileentity;
            return matchesChest(tileentitychest, p_matchesChest_1_, p_matchesChest_2_);
        } 
        if (tileentity instanceof TileEntityEnderChest) {
            TileEntityEnderChest tileentityenderchest = (TileEntityEnderChest)tileentity;
            return matchesEnderChest(tileentityenderchest, p_matchesChest_1_, p_matchesChest_2_);
        } 
        return false;
    }
    
    private boolean matchesChest(TileEntityChest p_matchesChest_1_, BlockPos p_matchesChest_2_, IBlockAccess p_matchesChest_3_) {
        boolean flag = !(p_matchesChest_1_.adjacentChestXNeg == null && p_matchesChest_1_.adjacentChestXPos == null && p_matchesChest_1_.adjacentChestZNeg == null && p_matchesChest_1_.adjacentChestZPos == null);
        boolean flag1 = (p_matchesChest_1_.getChestType() == 1);
        boolean flag2 = CustomGuis.isChristmas;
        boolean flag3 = false;
        return matchesChest(flag, flag1, flag2, flag3);
    }
    
    private boolean matchesEnderChest(TileEntityEnderChest p_matchesEnderChest_1_, BlockPos p_matchesEnderChest_2_, IBlockAccess p_matchesEnderChest_3_) {
        return matchesChest(false, false, false, true);
    }
    
    private boolean matchesChest(boolean p_matchesChest_1_, boolean p_matchesChest_2_, boolean p_matchesChest_3_, boolean p_matchesChest_4_) {
        return (this.large != null && this.large.booleanValue() != p_matchesChest_1_) ? false : ((this.trapped != null && this.trapped.booleanValue() != p_matchesChest_2_) ? false : ((this.christmas != null && this.christmas.booleanValue() != p_matchesChest_3_) ? false : (!(this.ender != null && this.ender.booleanValue() != p_matchesChest_4_))));
    }
    
    private boolean matchesDispenser(BlockPos p_matchesDispenser_1_, IBlockAccess p_matchesDispenser_2_) {
        TileEntity tileentity = p_matchesDispenser_2_.getTileEntity(p_matchesDispenser_1_);
        if (!(tileentity instanceof TileEntityDispenser))
            return false; 
        TileEntityDispenser tileentitydispenser = (TileEntityDispenser)tileentity;
        if (this.variants != null) {
            EnumVariant customguiproperties$enumvariant = getDispenserVariant(tileentitydispenser);
            if (!Config.equalsOne(customguiproperties$enumvariant, (Object[])this.variants))
                return false; 
        } 
        return true;
    }
    
    private EnumVariant getDispenserVariant(TileEntityDispenser p_getDispenserVariant_1_) {
        return (p_getDispenserVariant_1_ instanceof net.minecraft.tileentity.TileEntityDropper) ? EnumVariant.DROPPER : EnumVariant.DISPENSER;
    }
    
    public boolean matchesEntity(EnumContainer p_matchesEntity_1_, Entity p_matchesEntity_2_, IBlockAccess p_matchesEntity_3_) {
        if (!matchesGeneral(p_matchesEntity_1_, p_matchesEntity_2_.getPosition(), p_matchesEntity_3_))
            return false; 
        if (this.nbtName != null) {
            String s = p_matchesEntity_2_.getName();
            if (!this.nbtName.matchesValue(s))
                return false; 
        } 
        switch (p_matchesEntity_1_) {
            case HORSE:
                return matchesHorse(p_matchesEntity_2_, p_matchesEntity_3_);
            case VILLAGER:
                return matchesVillager(p_matchesEntity_2_, p_matchesEntity_3_);
        } 
        return true;
    }
    
    private boolean matchesVillager(Entity p_matchesVillager_1_, IBlockAccess p_matchesVillager_2_) {
        if (!(p_matchesVillager_1_ instanceof EntityVillager))
            return false; 
        EntityVillager entityvillager = (EntityVillager)p_matchesVillager_1_;
        if (this.professions != null) {
            int i = entityvillager.getProfession();
            int j = Reflector.getFieldValueInt(entityvillager, Reflector.EntityVillager_careerId, -1);
            if (j < 0)
                return false; 
            boolean flag = false;
            for (int k = 0; k < this.professions.length; k++) {
                VillagerProfession villagerprofession = this.professions[k];
                if (villagerprofession.matches(i, j)) {
                    flag = true;
                    break;
                } 
            } 
            if (!flag)
                return false; 
        } 
        return true;
    }
    
    private boolean matchesHorse(Entity p_matchesHorse_1_, IBlockAccess p_matchesHorse_2_) {
        if (!(p_matchesHorse_1_ instanceof EntityHorse))
            return false; 
        EntityHorse entityhorse = (EntityHorse)p_matchesHorse_1_;
        if (this.variants != null) {
            EnumVariant customguiproperties$enumvariant = getHorseVariant(entityhorse);
            if (!Config.equalsOne(customguiproperties$enumvariant, (Object[])this.variants))
                return false; 
        } 
        return true;
    }
    
    private EnumVariant getHorseVariant(EntityHorse p_getHorseVariant_1_) {
        int i = p_getHorseVariant_1_.getHorseType();
        switch (i) {
            case 0:
                return EnumVariant.HORSE;
            case 1:
                return EnumVariant.DONKEY;
            case 2:
                return EnumVariant.MULE;
        } 
        return null;
    }
    
    public EnumContainer getContainer() {
        return this.container;
    }
    
    public ResourceLocation getTextureLocation(ResourceLocation p_getTextureLocation_1_) {
        ResourceLocation resourcelocation = this.textureLocations.get(p_getTextureLocation_1_);
        return (resourcelocation == null) ? p_getTextureLocation_1_ : resourcelocation;
    }
    
    public String toString() {
        return "name: " + this.fileName + ", container: " + this.container + ", textures: " + this.textureLocations;
    }
    
    public enum EnumContainer {
        ANVIL, BEACON, BREWING_STAND, CHEST, CRAFTING, DISPENSER, ENCHANTMENT, FURNACE, HOPPER, HORSE, VILLAGER, SHULKER_BOX, CREATIVE, INVENTORY;
        
        public static final EnumContainer[] VALUES = values();
        
        static {
        
        }
    }
    
    private enum EnumVariant {
        HORSE, DONKEY, MULE, LLAMA, DISPENSER, DROPPER;
    }
}

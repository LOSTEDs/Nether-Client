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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Config;
import net.minecraft.src.ConnectedParser;
import net.minecraft.src.MatchBlock;
import net.minecraft.src.PropertiesOrdered;
import net.minecraft.src.Reflector;
import net.minecraft.src.ReflectorForge;
import net.minecraft.src.StrUtils;
import net.minecraft.util.ResourceLocation;

public class BlockAliases {
    private static BlockAlias[][] blockAliases = null;
    
    private static boolean updateOnResourcesReloaded;
    
    public static int getMappedBlockId(int blockId, int metadata) {
        if (blockAliases == null)
            return blockId; 
        if (blockId >= 0 && blockId < blockAliases.length) {
            BlockAlias[] ablockalias = blockAliases[blockId];
            if (ablockalias == null)
                return blockId; 
            for (int i = 0; i < ablockalias.length; i++) {
                BlockAlias blockalias = ablockalias[i];
                if (blockalias.matches(blockId, metadata))
                    return blockalias.getBlockId(); 
            } 
            return blockId;
        } 
        return blockId;
    }
    
    public static void resourcesReloaded() {
        if (updateOnResourcesReloaded) {
            updateOnResourcesReloaded = false;
            update(Shaders.shaderPack);
        } 
    }
    
    public static void update(IShaderPack shaderPack) {
        reset();
        if (shaderPack != null)
            if (Reflector.Loader_getActiveModList.exists() && Minecraft.getMinecraft().getResourcePackRepository() == null) {
                Config.dbg("[Shaders] Delayed loading of block mappings after resources are loaded");
                updateOnResourcesReloaded = true;
            } else {
                List<List<BlockAlias>> list = new ArrayList<>();
                String s = "/shaders/block.properties";
                InputStream inputstream = shaderPack.getResourceAsStream(s);
                if (inputstream != null)
                    loadBlockAliases(inputstream, s, list); 
                loadModBlockAliases(list);
                if (list.size() > 0)
                    blockAliases = toArrays(list); 
            }  
    }
    
    private static void loadModBlockAliases(List<List<BlockAlias>> listBlockAliases) {
        String[] astring = ReflectorForge.getForgeModIds();
        for (int i = 0; i < astring.length; i++) {
            String s = astring[i];
            try {
                ResourceLocation resourcelocation = new ResourceLocation(s, "shaders/block.properties");
                InputStream inputstream = Config.getResourceStream(resourcelocation);
                loadBlockAliases(inputstream, resourcelocation.toString(), listBlockAliases);
            } catch (IOException iOException) {}
        } 
    }
    
    private static void loadBlockAliases(InputStream in, String path, List<List<BlockAlias>> listBlockAliases) {
        if (in != null)
            try {
                PropertiesOrdered propertiesOrdered = new PropertiesOrdered();
                propertiesOrdered.load(in);
                in.close();
                Config.dbg("[Shaders] Parsing block mappings: " + path);
                ConnectedParser connectedparser = new ConnectedParser("Shaders");
                for (Object a : propertiesOrdered.keySet()) {
                    String s = (String)a;
                    String s1 = propertiesOrdered.getProperty(s);
                    String s2 = "block.";
                    if (!s.startsWith(s2)) {
                        Config.warn("[Shaders] Invalid block ID: " + s);
                        continue;
                    } 
                    String s3 = StrUtils.removePrefix(s, s2);
                    int i = Config.parseInt(s3, -1);
                    if (i < 0) {
                        Config.warn("[Shaders] Invalid block ID: " + s);
                        continue;
                    } 
                    MatchBlock[] amatchblock = connectedparser.parseMatchBlocks(s1);
                    if (amatchblock != null && amatchblock.length >= 1) {
                        BlockAlias blockalias = new BlockAlias(i, amatchblock);
                        addToList(listBlockAliases, blockalias);
                        continue;
                    } 
                    Config.warn("[Shaders] Invalid block ID mapping: " + s + "=" + s1);
                } 
            } catch (IOException var14) {
                Config.warn("[Shaders] Error reading: " + path);
            }  
    }
    
    private static void addToList(List<List<BlockAlias>> blocksAliases, BlockAlias ba) {
        int[] aint = ba.getMatchBlockIds();
        for (int i = 0; i < aint.length; i++) {
            int j = aint[i];
            while (j >= blocksAliases.size())
                blocksAliases.add(null); 
            List<BlockAlias> list = blocksAliases.get(j);
            if (list == null) {
                list = new ArrayList<>();
                blocksAliases.set(j, list);
            } 
            BlockAlias blockalias = new BlockAlias(ba.getBlockId(), ba.getMatchBlocks(j));
            list.add(blockalias);
        } 
    }
    
    private static BlockAlias[][] toArrays(List<List<BlockAlias>> listBlocksAliases) {
        BlockAlias[][] ablockalias = new BlockAlias[listBlocksAliases.size()][];
        for (int i = 0; i < ablockalias.length; i++) {
            List<BlockAlias> list = listBlocksAliases.get(i);
            if (list != null)
                ablockalias[i] = list.<BlockAlias>toArray(new BlockAlias[list.size()]); 
        } 
        return ablockalias;
    }
    
    public static void reset() {
        blockAliases = null;
    }
}

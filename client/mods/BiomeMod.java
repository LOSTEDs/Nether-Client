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

package client.mods;

import client.Client;
import client.hud.impl.HudMod;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class BiomeMod extends HudMod {
    public BiomeMod() {
        super("Biome Display", 0, 30);
    }
    
    public void draw() {
        BlockPos blockpos = new BlockPos((this.mc.getRenderViewEntity()).posX, (this.mc.getRenderViewEntity().getEntityBoundingBox()).minY, (this.mc.getRenderViewEntity()).posZ);
        Chunk chunk = this.mc.theWorld.getChunkFromBlockCoords(blockpos);
        this.fr.drawStringWithShadow(String.valueOf(Client.Color0) + "[ " + Client.ColorM + "Biome" + Client.Color0 + " ] " + Client.ColorW + (chunk.getBiome(blockpos, this.mc.theWorld.getWorldChunkManager())).biomeName, getX(), getY(), -1);
        super.draw();
    }
    
    public void renderDummy(int mouseX, int mouseY) {
        this.fr.drawStringWithShadow(String.valueOf(Client.Color0) + "[ " + Client.ColorM + "Biome" + Client.Color0 + " ] " + Client.ColorW + "Desert", getX(), getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }
    
    public int getWidth() {
        return this.fr.getStringWidth("[ Biome ] Desert");
    }
    
    public int getHeight() {
        return this.fr.FONT_HEIGHT;
    }
}

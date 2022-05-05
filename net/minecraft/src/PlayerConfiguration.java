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

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;

public class PlayerConfiguration {
    private PlayerItemModel[] playerItemModels = new PlayerItemModel[0];
    
    private boolean initialized = false;
    
    public void renderPlayerItems(ModelBiped p_renderPlayerItems_1_, AbstractClientPlayer p_renderPlayerItems_2_, float p_renderPlayerItems_3_, float p_renderPlayerItems_4_) {
        if (this.initialized)
            for (int i = 0; i < this.playerItemModels.length; i++) {
                PlayerItemModel playeritemmodel = this.playerItemModels[i];
                playeritemmodel.render(p_renderPlayerItems_1_, p_renderPlayerItems_2_, p_renderPlayerItems_3_, p_renderPlayerItems_4_);
            }  
    }
    
    public boolean isInitialized() {
        return this.initialized;
    }
    
    public void setInitialized(boolean p_setInitialized_1_) {
        this.initialized = p_setInitialized_1_;
    }
    
    public PlayerItemModel[] getPlayerItemModels() {
        return this.playerItemModels;
    }
    
    public void addPlayerItemModel(PlayerItemModel p_addPlayerItemModel_1_) {
        this.playerItemModels = (PlayerItemModel[])Config.addObjectToArray((Object[])this.playerItemModels, p_addPlayerItemModel_1_);
    }
}

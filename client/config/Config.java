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

package client.config;

import client.Client;
import client.hud.impl.HudMod;
import java.io.File;
import java.io.IOException;

public class Config {
    public File configFolder = new File(Client.ClientName);
    
    public Configuration config;
    
    public Configuration configToSave = ConfigurationAPI.newConfiguration(new File(String.valueOf(Client.ClientName) + "/ModsConfig.json"));
    
    public void saveModConfig() {
        if (!this.configFolder.exists())
            this.configFolder.mkdirs(); 
        System.out.println("Saving Mods Config");
        for (HudMod m : Client.INSTANCE.hudManager.hudMods) {
            this.configToSave.set(String.valueOf(m.name.toLowerCase()) + " x", Integer.valueOf(m.getX()));
            this.configToSave.set(String.valueOf(m.name.toLowerCase()) + " y", Integer.valueOf(m.getY()));
            this.configToSave.set(String.valueOf(m.name.toLowerCase()) + " enabled", Boolean.valueOf(m.isEnabled()));
        } 
        try {
            this.configToSave.save();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
    public void loadModConfig() {
        try {
            this.config = ConfigurationAPI.loadExistingConfiguration(new File(String.valueOf(Client.ClientName) + "/ModsConfig.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}

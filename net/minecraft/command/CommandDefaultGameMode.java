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

package net.minecraft.command;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.WorldSettings;

public class CommandDefaultGameMode extends CommandGameMode {
    public String getCommandName() {
        return "defaultgamemode";
    }
    
    public String getCommandUsage(ICommandSender sender) {
        return "commands.defaultgamemode.usage";
    }
    
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length <= 0)
            throw new WrongUsageException("commands.defaultgamemode.usage", new Object[0]); 
        WorldSettings.GameType worldsettings$gametype = getGameModeFromCommand(sender, args[0]);
        setGameType(worldsettings$gametype);
        notifyOperators(sender, this, "commands.defaultgamemode.success", new Object[] { new ChatComponentTranslation("gameMode." + worldsettings$gametype.getName(), new Object[0]) });
    }
    
    protected void setGameType(WorldSettings.GameType p_71541_1_) {
        MinecraftServer minecraftserver = MinecraftServer.getServer();
        minecraftserver.setGameType(p_71541_1_);
        if (minecraftserver.getForceGamemode())
            for (EntityPlayerMP entityplayermp : MinecraftServer.getServer().getConfigurationManager().func_181057_v()) {
                entityplayermp.setGameType(p_71541_1_);
                entityplayermp.fallDistance = 0.0F;
            }  
    }
}

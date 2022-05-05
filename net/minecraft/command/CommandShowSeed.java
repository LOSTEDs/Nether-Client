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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class CommandShowSeed extends CommandBase {
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return !(!MinecraftServer.getServer().isSinglePlayer() && !super.canCommandSenderUseCommand(sender));
    }
    
    public String getCommandName() {
        return "seed";
    }
    
    public int getRequiredPermissionLevel() {
        return 2;
    }
    
    public String getCommandUsage(ICommandSender sender) {
        return "commands.seed.usage";
    }
    
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        World world = (sender instanceof EntityPlayer) ? ((EntityPlayer)sender).worldObj : (World)MinecraftServer.getServer().worldServerForDimension(0);
        sender.addChatMessage((IChatComponent)new ChatComponentTranslation("commands.seed.success", new Object[] { Long.valueOf(world.getSeed()) }));
    }
}

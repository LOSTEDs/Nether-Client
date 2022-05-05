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

package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandTestFor extends CommandBase {
    public String getCommandName() {
        return "testfor";
    }
    
    public int getRequiredPermissionLevel() {
        return 2;
    }
    
    public String getCommandUsage(ICommandSender sender) {
        return "commands.testfor.usage";
    }
    
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1)
            throw new WrongUsageException("commands.testfor.usage", new Object[0]); 
        Entity entity = func_175768_b(sender, args[0]);
        NBTTagCompound nbttagcompound = null;
        if (args.length >= 2)
            try {
                nbttagcompound = JsonToNBT.getTagFromJson(buildString(args, 1));
            } catch (NBTException nbtexception) {
                throw new CommandException("commands.testfor.tagError", new Object[] { nbtexception.getMessage() });
            }  
        if (nbttagcompound != null) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            entity.writeToNBT(nbttagcompound1);
            if (!NBTUtil.func_181123_a((NBTBase)nbttagcompound, (NBTBase)nbttagcompound1, true))
                throw new CommandException("commands.testfor.failure", new Object[] { entity.getName() }); 
        } 
        notifyOperators(sender, (ICommand)this, "commands.testfor.success", new Object[] { entity.getName() });
    }
    
    public boolean isUsernameIndex(String[] args, int index) {
        return (index == 0);
    }
    
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return (args.length == 1) ? getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames()) : null;
    }
}

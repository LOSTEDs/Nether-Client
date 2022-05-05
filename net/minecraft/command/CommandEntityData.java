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

import net.minecraft.entity.Entity;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;

public class CommandEntityData extends CommandBase {
    public String getCommandName() {
        return "entitydata";
    }
    
    public int getRequiredPermissionLevel() {
        return 2;
    }
    
    public String getCommandUsage(ICommandSender sender) {
        return "commands.entitydata.usage";
    }
    
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        NBTTagCompound nbttagcompound2;
        if (args.length < 2)
            throw new WrongUsageException("commands.entitydata.usage", new Object[0]); 
        Entity entity = func_175768_b(sender, args[0]);
        if (entity instanceof net.minecraft.entity.player.EntityPlayer)
            throw new CommandException("commands.entitydata.noPlayers", new Object[] { entity.getDisplayName() }); 
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        entity.writeToNBT(nbttagcompound);
        NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttagcompound.copy();
        try {
            nbttagcompound2 = JsonToNBT.getTagFromJson(getChatComponentFromNthArg(sender, args, 1).getUnformattedText());
        } catch (NBTException nbtexception) {
            throw new CommandException("commands.entitydata.tagError", new Object[] { nbtexception.getMessage() });
        } 
        nbttagcompound2.removeTag("UUIDMost");
        nbttagcompound2.removeTag("UUIDLeast");
        nbttagcompound.merge(nbttagcompound2);
        if (nbttagcompound.equals(nbttagcompound1))
            throw new CommandException("commands.entitydata.failed", new Object[] { nbttagcompound.toString() }); 
        entity.readFromNBT(nbttagcompound);
        notifyOperators(sender, this, "commands.entitydata.success", new Object[] { nbttagcompound.toString() });
    }
    
    public boolean isUsernameIndex(String[] args, int index) {
        return (index == 0);
    }
}

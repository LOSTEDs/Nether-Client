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

import io.netty.buffer.ByteBuf;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;

public abstract class CommandBlockLogic implements ICommandSender {
    public CommandBlockLogic() {
        this.trackOutput = true;
        this.lastOutput = null;
        this.commandStored = "";
        this.customName = "@";
        this.resultStats = new CommandResultStats();
    }
    
    private static final SimpleDateFormat timestampFormat = new SimpleDateFormat("HH:mm:ss");
    
    private int successCount;
    
    private boolean trackOutput;
    
    private IChatComponent lastOutput;
    
    private String commandStored;
    
    private String customName;
    
    private final CommandResultStats resultStats;
    
    public int getSuccessCount() {
        return this.successCount;
    }
    
    public IChatComponent getLastOutput() {
        return this.lastOutput;
    }
    
    public void writeDataToNBT(NBTTagCompound tagCompound) {
        tagCompound.setString("Command", this.commandStored);
        tagCompound.setInteger("SuccessCount", this.successCount);
        tagCompound.setString("CustomName", this.customName);
        tagCompound.setBoolean("TrackOutput", this.trackOutput);
        if (this.lastOutput != null && this.trackOutput)
            tagCompound.setString("LastOutput", IChatComponent.Serializer.componentToJson(this.lastOutput)); 
        this.resultStats.writeStatsToNBT(tagCompound);
    }
    
    public void readDataFromNBT(NBTTagCompound nbt) {
        this.commandStored = nbt.getString("Command");
        this.successCount = nbt.getInteger("SuccessCount");
        if (nbt.hasKey("CustomName", 8))
            this.customName = nbt.getString("CustomName"); 
        if (nbt.hasKey("TrackOutput", 1))
            this.trackOutput = nbt.getBoolean("TrackOutput"); 
        if (nbt.hasKey("LastOutput", 8) && this.trackOutput)
            this.lastOutput = IChatComponent.Serializer.jsonToComponent(nbt.getString("LastOutput")); 
        this.resultStats.readStatsFromNBT(nbt);
    }
    
    public boolean canCommandSenderUseCommand(int permLevel, String commandName) {
        return (permLevel <= 2);
    }
    
    public void setCommand(String command) {
        this.commandStored = command;
        this.successCount = 0;
    }
    
    public String getCommand() {
        return this.commandStored;
    }
    
    public void trigger(World worldIn) {
        if (worldIn.isRemote)
            this.successCount = 0; 
        MinecraftServer minecraftserver = MinecraftServer.getServer();
        if (minecraftserver != null && minecraftserver.isAnvilFileSet() && minecraftserver.isCommandBlockEnabled()) {
            ICommandManager icommandmanager = minecraftserver.getCommandManager();
            try {
                this.lastOutput = null;
                this.successCount = icommandmanager.executeCommand(this, this.commandStored);
            } catch (Throwable throwable) {
                CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Executing command block");
                CrashReportCategory crashreportcategory = crashreport.makeCategory("Command to be executed");
                crashreportcategory.addCrashSectionCallable("Command", new Callable<String>() {
                            public String call() throws Exception {
                                return CommandBlockLogic.this.getCommand();
                            }
                        });
                crashreportcategory.addCrashSectionCallable("Name", new Callable<String>() {
                            public String call() throws Exception {
                                return CommandBlockLogic.this.getName();
                            }
                        });
                throw new ReportedException(crashreport);
            } 
        } else {
            this.successCount = 0;
        } 
    }
    
    public String getName() {
        return this.customName;
    }
    
    public IChatComponent getDisplayName() {
        return (IChatComponent)new ChatComponentText(getName());
    }
    
    public void setName(String p_145754_1_) {
        this.customName = p_145754_1_;
    }
    
    public void addChatMessage(IChatComponent component) {
        if (this.trackOutput && getEntityWorld() != null && !(getEntityWorld()).isRemote) {
            this.lastOutput = (new ChatComponentText("[" + timestampFormat.format(new Date()) + "] ")).appendSibling(component);
            updateCommand();
        } 
    }
    
    public boolean sendCommandFeedback() {
        MinecraftServer minecraftserver = MinecraftServer.getServer();
        return !(minecraftserver != null && minecraftserver.isAnvilFileSet() && !minecraftserver.worldServers[0].getGameRules().getBoolean("commandBlockOutput"));
    }
    
    public void setCommandStat(CommandResultStats.Type type, int amount) {
        this.resultStats.func_179672_a(this, type, amount);
    }
    
    public abstract void updateCommand();
    
    public abstract int func_145751_f();
    
    public abstract void func_145757_a(ByteBuf paramByteBuf);
    
    public void setLastOutput(IChatComponent lastOutputMessage) {
        this.lastOutput = lastOutputMessage;
    }
    
    public void setTrackOutput(boolean shouldTrackOutput) {
        this.trackOutput = shouldTrackOutput;
    }
    
    public boolean shouldTrackOutput() {
        return this.trackOutput;
    }
    
    public boolean tryOpenEditCommandBlock(EntityPlayer playerIn) {
        if (!playerIn.capabilities.isCreativeMode)
            return false; 
        if ((playerIn.getEntityWorld()).isRemote)
            playerIn.openEditCommandBlock(this); 
        return true;
    }
    
    public CommandResultStats getCommandResultStats() {
        return this.resultStats;
    }
}
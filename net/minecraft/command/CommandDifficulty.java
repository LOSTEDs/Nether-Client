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

import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.EnumDifficulty;

public class CommandDifficulty extends CommandBase {
    public String getCommandName() {
        return "difficulty";
    }
    
    public int getRequiredPermissionLevel() {
        return 2;
    }
    
    public String getCommandUsage(ICommandSender sender) {
        return "commands.difficulty.usage";
    }
    
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length <= 0)
            throw new WrongUsageException("commands.difficulty.usage", new Object[0]); 
        EnumDifficulty enumdifficulty = getDifficultyFromCommand(args[0]);
        MinecraftServer.getServer().setDifficultyForAllWorlds(enumdifficulty);
        notifyOperators(sender, this, "commands.difficulty.success", new Object[] { new ChatComponentTranslation(enumdifficulty.getDifficultyResourceKey(), new Object[0]) });
    }
    
    protected EnumDifficulty getDifficultyFromCommand(String p_180531_1_) throws CommandException, NumberInvalidException {
        return (!p_180531_1_.equalsIgnoreCase("peaceful") && !p_180531_1_.equalsIgnoreCase("p")) ? ((!p_180531_1_.equalsIgnoreCase("easy") && !p_180531_1_.equalsIgnoreCase("e")) ? ((!p_180531_1_.equalsIgnoreCase("normal") && !p_180531_1_.equalsIgnoreCase("n")) ? ((!p_180531_1_.equalsIgnoreCase("hard") && !p_180531_1_.equalsIgnoreCase("h")) ? EnumDifficulty.getDifficultyEnum(parseInt(p_180531_1_, 0, 3)) : EnumDifficulty.HARD) : EnumDifficulty.NORMAL) : EnumDifficulty.EASY) : EnumDifficulty.PEACEFUL;
    }
    
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return (args.length == 1) ? getListOfStringsMatchingLastWord(args, new String[] { "peaceful", "easy", "normal", "hard" }) : null;
    }
}

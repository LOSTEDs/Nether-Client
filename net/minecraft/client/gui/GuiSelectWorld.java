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

package net.minecraft.client.gui;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveFormatComparator;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiSelectWorld extends GuiScreen implements GuiYesNoCallback {
    private static final Logger logger = LogManager.getLogger();
    
    private final DateFormat field_146633_h = new SimpleDateFormat();
    
    protected GuiScreen parentScreen;
    
    protected String field_146628_f = "Select world";
    
    private boolean field_146634_i;
    
    private int field_146640_r;
    
    private java.util.List<SaveFormatComparator> field_146639_s;
    
    private List field_146638_t;
    
    private String field_146637_u;
    
    private String field_146636_v;
    
    private String[] field_146635_w = new String[4];
    
    private boolean field_146643_x;
    
    private GuiButton deleteButton;
    
    private GuiButton selectButton;
    
    private GuiButton renameButton;
    
    private GuiButton recreateButton;
    
    public GuiSelectWorld(GuiScreen parentScreenIn) {
        this.parentScreen = parentScreenIn;
    }
    
    public void initGui() {
        this.field_146628_f = I18n.format("selectWorld.title", new Object[0]);
        try {
            func_146627_h();
        } catch (AnvilConverterException anvilconverterexception) {
            logger.error("Couldn't load level list", (Throwable)anvilconverterexception);
            this.mc.displayGuiScreen(new GuiErrorScreen("Unable to load worlds", anvilconverterexception.getMessage()));
            return;
        } 
        this.field_146637_u = I18n.format("selectWorld.world", new Object[0]);
        this.field_146636_v = I18n.format("selectWorld.conversion", new Object[0]);
        this.field_146635_w[WorldSettings.GameType.SURVIVAL.getID()] = I18n.format("gameMode.survival", new Object[0]);
        this.field_146635_w[WorldSettings.GameType.CREATIVE.getID()] = I18n.format("gameMode.creative", new Object[0]);
        this.field_146635_w[WorldSettings.GameType.ADVENTURE.getID()] = I18n.format("gameMode.adventure", new Object[0]);
        this.field_146635_w[WorldSettings.GameType.SPECTATOR.getID()] = I18n.format("gameMode.spectator", new Object[0]);
        this.field_146638_t = new List(this.mc);
        this.field_146638_t.registerScrollButtons(4, 5);
        func_146618_g();
    }
    
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        this.field_146638_t.handleMouseInput();
    }
    
    private void func_146627_h() throws AnvilConverterException {
        ISaveFormat isaveformat = this.mc.getSaveLoader();
        this.field_146639_s = isaveformat.getSaveList();
        Collections.sort(this.field_146639_s);
        this.field_146640_r = -1;
    }
    
    protected String func_146621_a(int p_146621_1_) {
        return ((SaveFormatComparator)this.field_146639_s.get(p_146621_1_)).getFileName();
    }
    
    protected String func_146614_d(int p_146614_1_) {
        String s = ((SaveFormatComparator)this.field_146639_s.get(p_146614_1_)).getDisplayName();
        if (StringUtils.isEmpty(s))
            s = String.valueOf(I18n.format("selectWorld.world", new Object[0])) + " " + (p_146614_1_ + 1); 
        return s;
    }
    
    public void func_146618_g() {
        this.buttonList.add(this.selectButton = new GuiButton(1, this.width / 2 - 154, this.height - 52, 150, 20, I18n.format("selectWorld.select", new Object[0])));
        this.buttonList.add(new GuiButton(3, this.width / 2 + 4, this.height - 52, 150, 20, I18n.format("selectWorld.create", new Object[0])));
        this.buttonList.add(this.renameButton = new GuiButton(6, this.width / 2 - 154, this.height - 28, 72, 20, I18n.format("selectWorld.rename", new Object[0])));
        this.buttonList.add(this.deleteButton = new GuiButton(2, this.width / 2 - 76, this.height - 28, 72, 20, I18n.format("selectWorld.delete", new Object[0])));
        this.buttonList.add(this.recreateButton = new GuiButton(7, this.width / 2 + 4, this.height - 28, 72, 20, I18n.format("selectWorld.recreate", new Object[0])));
        this.buttonList.add(new GuiButton(0, this.width / 2 + 82, this.height - 28, 72, 20, I18n.format("gui.cancel", new Object[0])));
        this.selectButton.enabled = false;
        this.deleteButton.enabled = false;
        this.renameButton.enabled = false;
        this.recreateButton.enabled = false;
    }
    
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.enabled)
            if (button.id == 2) {
                String s = func_146614_d(this.field_146640_r);
                if (s != null) {
                    this.field_146643_x = true;
                    GuiYesNo guiyesno = func_152129_a(this, s, this.field_146640_r);
                    this.mc.displayGuiScreen(guiyesno);
                } 
            } else if (button.id == 1) {
                func_146615_e(this.field_146640_r);
            } else if (button.id == 3) {
                this.mc.displayGuiScreen(new GuiCreateWorld(this));
            } else if (button.id == 6) {
                this.mc.displayGuiScreen(new GuiRenameWorld(this, func_146621_a(this.field_146640_r)));
            } else if (button.id == 0) {
                this.mc.displayGuiScreen(this.parentScreen);
            } else if (button.id == 7) {
                GuiCreateWorld guicreateworld = new GuiCreateWorld(this);
                ISaveHandler isavehandler = this.mc.getSaveLoader().getSaveLoader(func_146621_a(this.field_146640_r), false);
                WorldInfo worldinfo = isavehandler.loadWorldInfo();
                isavehandler.flush();
                guicreateworld.func_146318_a(worldinfo);
                this.mc.displayGuiScreen(guicreateworld);
            } else {
                this.field_146638_t.actionPerformed(button);
            }  
    }
    
    public void func_146615_e(int p_146615_1_) {
        this.mc.displayGuiScreen(null);
        if (!this.field_146634_i) {
            this.field_146634_i = true;
            String s = func_146621_a(p_146615_1_);
            if (s == null)
                s = "World" + p_146615_1_; 
            String s1 = func_146614_d(p_146615_1_);
            if (s1 == null)
                s1 = "World" + p_146615_1_; 
            if (this.mc.getSaveLoader().canLoadWorld(s))
                this.mc.launchIntegratedServer(s, s1, null); 
        } 
    }
    
    public void confirmClicked(boolean result, int id) {
        if (this.field_146643_x) {
            this.field_146643_x = false;
            if (result) {
                ISaveFormat isaveformat = this.mc.getSaveLoader();
                isaveformat.flushCache();
                isaveformat.deleteWorldDirectory(func_146621_a(id));
                try {
                    func_146627_h();
                } catch (AnvilConverterException anvilconverterexception) {
                    logger.error("Couldn't load level list", (Throwable)anvilconverterexception);
                } 
            } 
            this.mc.displayGuiScreen(this);
        } 
    }
    
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.field_146638_t.drawScreen(mouseX, mouseY, partialTicks);
        drawCenteredString(this.fontRendererObj, this.field_146628_f, this.width / 2, 20, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    public static GuiYesNo func_152129_a(GuiYesNoCallback p_152129_0_, String p_152129_1_, int p_152129_2_) {
        String s = I18n.format("selectWorld.deleteQuestion", new Object[0]);
        String s1 = "'" + p_152129_1_ + "' " + I18n.format("selectWorld.deleteWarning", new Object[0]);
        String s2 = I18n.format("selectWorld.deleteButton", new Object[0]);
        String s3 = I18n.format("gui.cancel", new Object[0]);
        GuiYesNo guiyesno = new GuiYesNo(p_152129_0_, s, s1, s2, s3, p_152129_2_);
        return guiyesno;
    }
    
    class List extends GuiSlot {
        public List(Minecraft mcIn) {
            super(mcIn, GuiSelectWorld.this.width, GuiSelectWorld.this.height, 32, GuiSelectWorld.this.height - 64, 36);
        }
        
        protected int getSize() {
            return GuiSelectWorld.this.field_146639_s.size();
        }
        
        protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
            GuiSelectWorld.this.field_146640_r = slotIndex;
            boolean flag = (GuiSelectWorld.this.field_146640_r >= 0 && GuiSelectWorld.this.field_146640_r < getSize());
            GuiSelectWorld.this.selectButton.enabled = flag;
            GuiSelectWorld.this.deleteButton.enabled = flag;
            GuiSelectWorld.this.renameButton.enabled = flag;
            GuiSelectWorld.this.recreateButton.enabled = flag;
            if (isDoubleClick && flag)
                GuiSelectWorld.this.func_146615_e(slotIndex); 
        }
        
        protected boolean isSelected(int slotIndex) {
            return (slotIndex == GuiSelectWorld.this.field_146640_r);
        }
        
        protected int getContentHeight() {
            return GuiSelectWorld.this.field_146639_s.size() * 36;
        }
        
        protected void drawBackground() {
            GuiSelectWorld.this.drawDefaultBackground();
        }
        
        protected void drawSlot(int entryID, int p_180791_2_, int p_180791_3_, int p_180791_4_, int mouseXIn, int mouseYIn) {
            SaveFormatComparator saveformatcomparator = GuiSelectWorld.this.field_146639_s.get(entryID);
            String s = saveformatcomparator.getDisplayName();
            if (StringUtils.isEmpty(s))
                s = String.valueOf(GuiSelectWorld.this.field_146637_u) + " " + (entryID + 1); 
            String s1 = saveformatcomparator.getFileName();
            s1 = String.valueOf(s1) + " (" + GuiSelectWorld.this.field_146633_h.format(new Date(saveformatcomparator.getLastTimePlayed()));
            s1 = String.valueOf(s1) + ")";
            String s2 = "";
            if (saveformatcomparator.requiresConversion()) {
                s2 = String.valueOf(GuiSelectWorld.this.field_146636_v) + " " + s2;
            } else {
                s2 = GuiSelectWorld.this.field_146635_w[saveformatcomparator.getEnumGameType().getID()];
                if (saveformatcomparator.isHardcoreModeEnabled())
                    s2 = EnumChatFormatting.DARK_RED + I18n.format("gameMode.hardcore", new Object[0]) + EnumChatFormatting.RESET; 
                if (saveformatcomparator.getCheatsEnabled())
                    s2 = String.valueOf(s2) + ", " + I18n.format("selectWorld.cheats", new Object[0]); 
            } 
            GuiSelectWorld.this.drawString(GuiSelectWorld.this.fontRendererObj, s, p_180791_2_ + 2, p_180791_3_ + 1, 16777215);
            GuiSelectWorld.this.drawString(GuiSelectWorld.this.fontRendererObj, s1, p_180791_2_ + 2, p_180791_3_ + 12, 8421504);
            GuiSelectWorld.this.drawString(GuiSelectWorld.this.fontRendererObj, s2, p_180791_2_ + 2, p_180791_3_ + 12 + 10, 8421504);
        }
    }
}

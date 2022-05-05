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

public class VillagerProfession {
    private int profession;
    
    private int[] careers;
    
    public VillagerProfession(int p_i113_1_) {
        this(p_i113_1_, (int[])null);
    }
    
    public VillagerProfession(int p_i114_1_, int p_i114_2_) {
        this(p_i114_1_, new int[] { p_i114_2_ });
    }
    
    public VillagerProfession(int p_i115_1_, int[] p_i115_2_) {
        this.profession = p_i115_1_;
        this.careers = p_i115_2_;
    }
    
    public boolean matches(int p_matches_1_, int p_matches_2_) {
        return (this.profession != p_matches_1_) ? false : (!(this.careers != null && !Config.equalsOne(p_matches_2_, this.careers)));
    }
    
    private boolean hasCareer(int p_hasCareer_1_) {
        return (this.careers == null) ? false : Config.equalsOne(p_hasCareer_1_, this.careers);
    }
    
    public boolean addCareer(int p_addCareer_1_) {
        if (this.careers == null) {
            this.careers = new int[] { p_addCareer_1_ };
            return true;
        } 
        if (hasCareer(p_addCareer_1_))
            return false; 
        this.careers = Config.addIntToArray(this.careers, p_addCareer_1_);
        return true;
    }
    
    public int getProfession() {
        return this.profession;
    }
    
    public int[] getCareers() {
        return this.careers;
    }
    
    public String toString() {
        return (this.careers == null) ? this.profession : (this.profession + ":" + Config.arrayToString(this.careers));
    }
}

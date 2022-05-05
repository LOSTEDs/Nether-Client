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

package net.minecraft.entity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.passive.IAnimals;

public enum EnumCreatureType {
    MONSTER((Class)IMob.class, 70, Material.air, false, false),
    CREATURE((Class)EntityAnimal.class, 10, Material.air, true, true),
    AMBIENT((Class)EntityAmbientCreature.class, 15, Material.air, true, false),
    WATER_CREATURE((Class)EntityWaterMob.class, 5, Material.water, true, false);
    
    private final Class<? extends IAnimals> creatureClass;
    
    private final int maxNumberOfCreature;
    
    private final Material creatureMaterial;
    
    private final boolean isPeacefulCreature;
    
    private final boolean isAnimal;
    
    EnumCreatureType(Class<? extends IAnimals> creatureClassIn, int maxNumberOfCreatureIn, Material creatureMaterialIn, boolean isPeacefulCreatureIn, boolean isAnimalIn) {
        this.creatureClass = creatureClassIn;
        this.maxNumberOfCreature = maxNumberOfCreatureIn;
        this.creatureMaterial = creatureMaterialIn;
        this.isPeacefulCreature = isPeacefulCreatureIn;
        this.isAnimal = isAnimalIn;
    }
    
    public Class<? extends IAnimals> getCreatureClass() {
        return this.creatureClass;
    }
    
    public int getMaxNumberOfCreature() {
        return this.maxNumberOfCreature;
    }
    
    public boolean getPeacefulCreature() {
        return this.isPeacefulCreature;
    }
    
    public boolean getAnimal() {
        return this.isAnimal;
    }
}

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

package net.minecraft.util;

import java.util.Collection;
import java.util.Random;

public class WeightedRandom {
    public static int getTotalWeight(Collection<? extends Item> collection) {
        int i = 0;
        for (Item weightedrandom$item : collection)
            i += weightedrandom$item.itemWeight; 
        return i;
    }
    
    public static <T extends Item> T getRandomItem(Random random, Collection<T> collection, int totalWeight) {
        if (totalWeight <= 0)
            throw new IllegalArgumentException(); 
        int i = random.nextInt(totalWeight);
        return getRandomItem(collection, i);
    }
    
    public static <T extends Item> T getRandomItem(Collection<T> collection, int weight) {
        for (Item item : collection) {
            weight -= item.itemWeight;
            if (weight < 0)
                return (T)item; 
        } 
        return null;
    }
    
    public static <T extends Item> T getRandomItem(Random random, Collection<T> collection) {
        return getRandomItem(random, collection, getTotalWeight(collection));
    }
    
    public static class Item {
        protected int itemWeight;
        
        public Item(int itemWeightIn) {
            this.itemWeight = itemWeightIn;
        }
    }
}

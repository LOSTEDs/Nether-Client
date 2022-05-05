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

package net.minecraft.block.state;

import com.google.common.collect.ImmutableMap;
import java.util.Collection;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;

public interface IBlockState {
    Collection<IProperty> getPropertyNames();
    
    <T extends Comparable<T>> T getValue(IProperty<T> paramIProperty);
    
    <T extends Comparable<T>, V extends T> IBlockState withProperty(IProperty<T> paramIProperty, V paramV);
    
    <T extends Comparable<T>> IBlockState cycleProperty(IProperty<T> paramIProperty);
    
    ImmutableMap<IProperty, Comparable> getProperties();
    
    Block getBlock();
}

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

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.Minecraft;

public class FieldLocatorActionKeyF3 implements IFieldLocator {
    public Field getField() {
        Class<Minecraft> oclass = Minecraft.class;
        Field field = getFieldRenderChunksMany();
        if (field == null) {
            Config.log("(Reflector) Field not present: " + oclass.getName() + ".actionKeyF3 (field renderChunksMany not found)");
            return null;
        } 
        Field field1 = ReflectorRaw.getFieldAfter(Minecraft.class, field, boolean.class, 0);
        if (field1 == null) {
            Config.log("(Reflector) Field not present: " + oclass.getName() + ".actionKeyF3");
            return null;
        } 
        return field1;
    }
    
    private Field getFieldRenderChunksMany() {
        Minecraft minecraft = Minecraft.getMinecraft();
        boolean flag = minecraft.renderChunksMany;
        Field[] afield = Minecraft.class.getDeclaredFields();
        minecraft.renderChunksMany = true;
        Field[] afield1 = ReflectorRaw.getFields(minecraft, afield, boolean.class, Boolean.TRUE);
        minecraft.renderChunksMany = false;
        Field[] afield2 = ReflectorRaw.getFields(minecraft, afield, boolean.class, Boolean.FALSE);
        minecraft.renderChunksMany = flag;
        Set<Field> set = new HashSet<>(Arrays.asList(afield1));
        Set<Field> set1 = new HashSet<>(Arrays.asList(afield2));
        Set<Field> set2 = new HashSet<>(set);
        set2.retainAll(set1);
        Field[] afield3 = set2.<Field>toArray(new Field[set2.size()]);
        return (afield3.length != 1) ? null : afield3[0];
    }
}

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

package org.apache.commons.io;

import java.nio.ByteOrder;

public final class ByteOrderParser {
    public static ByteOrder parseByteOrder(String value) {
        if (ByteOrder.BIG_ENDIAN.toString().equals(value))
            return ByteOrder.BIG_ENDIAN; 
        if (ByteOrder.LITTLE_ENDIAN.toString().equals(value))
            return ByteOrder.LITTLE_ENDIAN; 
        throw new IllegalArgumentException("Unsupported byte order setting: " + value + ", expected one of " + ByteOrder.LITTLE_ENDIAN + ", " + ByteOrder.BIG_ENDIAN);
    }
}

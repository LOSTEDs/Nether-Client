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

package org.apache.commons.io.input;

import java.io.Reader;
import java.util.Collections;
import java.util.Set;

public class CharacterSetFilterReader extends AbstractCharacterFilterReader {
    private static final Set<Integer> EMPTY_SET = Collections.emptySet();
    
    private final Set<Integer> skipSet;
    
    public CharacterSetFilterReader(Reader reader, Set<Integer> skip) {
        super(reader);
        this.skipSet = (skip == null) ? EMPTY_SET : Collections.<Integer>unmodifiableSet(skip);
    }
    
    protected boolean filter(int ch) {
        return this.skipSet.contains(Integer.valueOf(ch));
    }
}

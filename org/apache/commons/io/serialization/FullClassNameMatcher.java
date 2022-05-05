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

package org.apache.commons.io.serialization;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

final class FullClassNameMatcher implements ClassNameMatcher {
    private final Set<String> classesSet;
    
    public FullClassNameMatcher(String... classes) {
        this.classesSet = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(classes)));
    }
    
    public boolean matches(String className) {
        return this.classesSet.contains(className);
    }
}

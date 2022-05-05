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

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ValidatingObjectInputStream extends ObjectInputStream {
    private final List<ClassNameMatcher> acceptMatchers = new ArrayList<>();
    
    private final List<ClassNameMatcher> rejectMatchers = new ArrayList<>();
    
    public ValidatingObjectInputStream(InputStream input) throws IOException {
        super(input);
    }
    
    private void validateClassName(String name) throws InvalidClassException {
        for (ClassNameMatcher m : this.rejectMatchers) {
            if (m.matches(name))
                invalidClassNameFound(name); 
        } 
        boolean ok = false;
        for (ClassNameMatcher m : this.acceptMatchers) {
            if (m.matches(name)) {
                ok = true;
                break;
            } 
        } 
        if (!ok)
            invalidClassNameFound(name); 
    }
    
    protected void invalidClassNameFound(String className) throws InvalidClassException {
        throw new InvalidClassException("Class name not accepted: " + className);
    }
    
    protected Class<?> resolveClass(ObjectStreamClass osc) throws IOException, ClassNotFoundException {
        validateClassName(osc.getName());
        return super.resolveClass(osc);
    }
    
    public ValidatingObjectInputStream accept(Class<?>... classes) {
        for (Class<?> c : classes) {
            this.acceptMatchers.add(new FullClassNameMatcher(new String[] { c.getName() }));
        } 
        return this;
    }
    
    public ValidatingObjectInputStream reject(Class<?>... classes) {
        for (Class<?> c : classes) {
            this.rejectMatchers.add(new FullClassNameMatcher(new String[] { c.getName() }));
        } 
        return this;
    }
    
    public ValidatingObjectInputStream accept(String... patterns) {
        for (String pattern : patterns)
            this.acceptMatchers.add(new WildcardClassNameMatcher(pattern)); 
        return this;
    }
    
    public ValidatingObjectInputStream reject(String... patterns) {
        for (String pattern : patterns)
            this.rejectMatchers.add(new WildcardClassNameMatcher(pattern)); 
        return this;
    }
    
    public ValidatingObjectInputStream accept(Pattern pattern) {
        this.acceptMatchers.add(new RegexpClassNameMatcher(pattern));
        return this;
    }
    
    public ValidatingObjectInputStream reject(Pattern pattern) {
        this.rejectMatchers.add(new RegexpClassNameMatcher(pattern));
        return this;
    }
    
    public ValidatingObjectInputStream accept(ClassNameMatcher m) {
        this.acceptMatchers.add(m);
        return this;
    }
    
    public ValidatingObjectInputStream reject(ClassNameMatcher m) {
        this.rejectMatchers.add(m);
        return this;
    }
}

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

import java.util.Objects;

public enum IOCase {
    SENSITIVE("Sensitive", true),
    INSENSITIVE("Insensitive", false),
    SYSTEM("System", !FilenameUtils.isSystemWindows());
    
    private static final long serialVersionUID = -6343169151696340687L;
    
    private final String name;
    
    private final transient boolean sensitive;
    
    public static IOCase forName(String name) {
        for (IOCase ioCase : values()) {
            if (ioCase.getName().equals(name))
                return ioCase; 
        } 
        throw new IllegalArgumentException("Invalid IOCase name: " + name);
    }
    
    IOCase(String name, boolean sensitive) {
        this.name = name;
        this.sensitive = sensitive;
    }
    
    private Object readResolve() {
        return forName(this.name);
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean isCaseSensitive() {
        return this.sensitive;
    }
    
    public int checkCompareTo(String str1, String str2) {
        Objects.requireNonNull(str1, "str1");
        Objects.requireNonNull(str2, "str2");
        return this.sensitive ? str1.compareTo(str2) : str1.compareToIgnoreCase(str2);
    }
    
    public boolean checkEquals(String str1, String str2) {
        Objects.requireNonNull(str1, "str1");
        Objects.requireNonNull(str2, "str2");
        return this.sensitive ? str1.equals(str2) : str1.equalsIgnoreCase(str2);
    }
    
    public boolean checkStartsWith(String str, String start) {
        return str.regionMatches(!this.sensitive, 0, start, 0, start.length());
    }
    
    public boolean checkEndsWith(String str, String end) {
        int endLen = end.length();
        return str.regionMatches(!this.sensitive, str.length() - endLen, end, 0, endLen);
    }
    
    public int checkIndexOf(String str, int strStartIndex, String search) {
        int endIndex = str.length() - search.length();
        if (endIndex >= strStartIndex)
            for (int i = strStartIndex; i <= endIndex; i++) {
                if (checkRegionMatches(str, i, search))
                    return i; 
            }  
        return -1;
    }
    
    public boolean checkRegionMatches(String str, int strStartIndex, String search) {
        return str.regionMatches(!this.sensitive, strStartIndex, search, 0, search.length());
    }
    
    public String toString() {
        return this.name;
    }
}

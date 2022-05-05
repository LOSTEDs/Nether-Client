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

package org.json;

public class XMLParserConfiguration {
    public static final XMLParserConfiguration ORIGINAL = new XMLParserConfiguration();
    
    public static final XMLParserConfiguration KEEP_STRINGS = new XMLParserConfiguration(true);
    
    public final boolean keepStrings;
    
    public final String cDataTagName;
    
    public final boolean convertNilAttributeToNull;
    
    public XMLParserConfiguration() {
        this(false, "content", false);
    }
    
    public XMLParserConfiguration(boolean keepStrings) {
        this(keepStrings, "content", false);
    }
    
    public XMLParserConfiguration(String cDataTagName) {
        this(false, cDataTagName, false);
    }
    
    public XMLParserConfiguration(boolean keepStrings, String cDataTagName) {
        this.keepStrings = keepStrings;
        this.cDataTagName = cDataTagName;
        this.convertNilAttributeToNull = false;
    }
    
    public XMLParserConfiguration(boolean keepStrings, String cDataTagName, boolean convertNilAttributeToNull) {
        this.keepStrings = keepStrings;
        this.cDataTagName = cDataTagName;
        this.convertNilAttributeToNull = convertNilAttributeToNull;
    }
}

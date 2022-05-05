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

package net.optifine.entity.model.anim;

public enum TokenType {
    IDENTIFIER("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_:."),
    NUMBER("0123456789", "0123456789."),
    OPERATOR("+-*/%!&|<>=", "&|="),
    COMMA(","),
    BRACKET_OPEN("("),
    BRACKET_CLOSE(")");
    
    private String charsFirst;
    
    private String charsNext;
    
    public static final TokenType[] VALUES;
    
    static {
        VALUES = values();
    }
    
    TokenType(String charsFirst, String charsNext) {
        this.charsFirst = charsFirst;
        this.charsNext = charsNext;
    }
    
    public String getCharsFirst() {
        return this.charsFirst;
    }
    
    public String getCharsNext() {
        return this.charsNext;
    }
    
    public static TokenType getTypeByFirstChar(char ch) {
        for (int i = 0; i < VALUES.length; i++) {
            TokenType tokentype = VALUES[i];
            if (tokentype.getCharsFirst().indexOf(ch) >= 0)
                return tokentype; 
        } 
        return null;
    }
    
    public boolean hasCharNext(char ch) {
        return (this.charsNext.indexOf(ch) >= 0);
    }
    
    private static class Const {
        static final String ALPHAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        
        static final String DIGITS = "0123456789";
    }
}

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

public class Token {
    private TokenType type;
    
    private String text;
    
    public Token(TokenType type, String text) {
        this.type = type;
        this.text = text;
    }
    
    public TokenType getType() {
        return this.type;
    }
    
    public String getText() {
        return this.text;
    }
    
    public String toString() {
        return this.text;
    }
}

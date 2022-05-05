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

public class HTTPTokener extends JSONTokener {
    public HTTPTokener(String string) {
        super(string);
    }
    
    public String nextToken() throws JSONException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            char c = next();
            if (!Character.isWhitespace(c)) {
                if (c == '"' || c == '\'') {
                    char q = c;
                    while (true) {
                        c = next();
                        if (c < ' ')
                            throw syntaxError("Unterminated string."); 
                        if (c == q)
                            return sb.toString(); 
                        sb.append(c);
                    } 
                    break;
                } 
                while (true) {
                    if (c == '\000' || Character.isWhitespace(c))
                        return sb.toString(); 
                    sb.append(c);
                    c = next();
                } 
                break;
            } 
        } 
    }
}

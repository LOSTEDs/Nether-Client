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

import java.io.StringWriter;

public class JSONStringer extends JSONWriter {
    public JSONStringer() {
        super(new StringWriter());
    }
    
    public String toString() {
        return (this.mode == 'd') ? this.writer.toString() : null;
    }
}

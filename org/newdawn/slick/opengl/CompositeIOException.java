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

package org.newdawn.slick.opengl;

import java.io.IOException;
import java.util.ArrayList;

public class CompositeIOException extends IOException {
    private ArrayList exceptions = new ArrayList();
    
    public void addException(Exception e) {
        this.exceptions.add(e);
    }
    
    public String getMessage() {
        String msg = "Composite Exception: \n";
        for (int i = 0; i < this.exceptions.size(); i++)
            msg = msg + "\t" + ((IOException)this.exceptions.get(i)).getMessage() + "\n"; 
        return msg;
    }
}

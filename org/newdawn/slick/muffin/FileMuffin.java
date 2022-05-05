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

package org.newdawn.slick.muffin;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import org.newdawn.slick.util.Log;

public class FileMuffin implements Muffin {
    public void saveFile(HashMap scoreMap, String fileName) throws IOException {
        String userHome = System.getProperty("user.home");
        File file = new File(userHome);
        file = new File(file, ".java");
        if (!file.exists())
            file.mkdir(); 
        file = new File(file, fileName);
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(scoreMap);
        oos.close();
    }
    
    public HashMap loadFile(String fileName) throws IOException {
        HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        String userHome = System.getProperty("user.home");
        File file = new File(userHome);
        file = new File(file, ".java");
        file = new File(file, fileName);
        if (file.exists())
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                hashMap = (HashMap<Object, Object>)ois.readObject();
                ois.close();
            } catch (EOFException e) {
            
            } catch (ClassNotFoundException e) {
                Log.error(e);
                throw new IOException("Failed to pull state from store - class not found");
            }  
        return hashMap;
    }
}

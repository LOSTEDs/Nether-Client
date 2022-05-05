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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.jnlp.BasicService;
import javax.jnlp.FileContents;
import javax.jnlp.PersistenceService;
import javax.jnlp.ServiceManager;
import org.newdawn.slick.util.Log;

public class WebstartMuffin implements Muffin {
    public void saveFile(HashMap scoreMap, String fileName) throws IOException {
        PersistenceService ps;
        URL configURL;
        try {
            ps = (PersistenceService)ServiceManager.lookup("javax.jnlp.PersistenceService");
            BasicService bs = (BasicService)ServiceManager.lookup("javax.jnlp.BasicService");
            URL baseURL = bs.getCodeBase();
            configURL = new URL(baseURL, fileName);
        } catch (Exception e) {
            Log.error(e);
            throw new IOException("Failed to save state: ");
        } 
        try {
            ps.delete(configURL);
        } catch (Exception e) {
            Log.info("No exisiting Muffin Found - First Save");
        } 
        try {
            ps.create(configURL, 1024L);
            FileContents fc = ps.get(configURL);
            DataOutputStream oos = new DataOutputStream(fc.getOutputStream(false));
            Set keys = scoreMap.keySet();
            for (Iterator<String> i = keys.iterator(); i.hasNext(); ) {
                String key = i.next();
                oos.writeUTF(key);
                if (fileName.endsWith("Number")) {
                    double d = ((Double)scoreMap.get(key)).doubleValue();
                    oos.writeDouble(d);
                    continue;
                } 
                String value = (String)scoreMap.get(key);
                oos.writeUTF(value);
            } 
            oos.flush();
            oos.close();
        } catch (Exception e) {
            Log.error(e);
            throw new IOException("Failed to store map of state data");
        } 
    }
    
    public HashMap loadFile(String fileName) throws IOException {
        HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        try {
            PersistenceService ps = (PersistenceService)ServiceManager.lookup("javax.jnlp.PersistenceService");
            BasicService bs = (BasicService)ServiceManager.lookup("javax.jnlp.BasicService");
            URL baseURL = bs.getCodeBase();
            URL configURL = new URL(baseURL, fileName);
            FileContents fc = ps.get(configURL);
            DataInputStream ois = new DataInputStream(fc.getInputStream());
            if (fileName.endsWith("Number")) {
                String key;
                while ((key = ois.readUTF()) != null) {
                    double value = ois.readDouble();
                    hashMap.put(key, new Double(value));
                } 
            } else {
                String key;
                while ((key = ois.readUTF()) != null) {
                    String value = ois.readUTF();
                    hashMap.put(key, value);
                } 
            } 
            ois.close();
        } catch (EOFException e) {
        
        } catch (IOException e) {
        
        } catch (Exception e) {
            Log.error(e);
            throw new IOException("Failed to load state from webstart muffin");
        } 
        return hashMap;
    }
}

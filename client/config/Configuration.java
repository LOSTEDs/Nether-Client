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

package client.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class Configuration {
    private File file;
    
    public Map<String, Object> options;
    
    public Configuration(File file, Map<String, Object> options) {
        this.file = file;
        this.options = options;
    }
    
    public Configuration(File file) {
        this.file = file;
        this.options = new HashMap<>();
    }
    
    public Object get(String key) {
        return this.options.get(key);
    }
    
    public void set(String key, Object value) {
        this.options.put(key, value);
    }
    
    public void save() throws IOException {
        JSONObject jsonObject = new JSONObject(this.options);
        this.file.createNewFile();
        FileWriter fileWriter = new FileWriter(this.file);
        fileWriter.write(jsonObject.toString());
        fileWriter.flush();
        fileWriter.close();
    }
}

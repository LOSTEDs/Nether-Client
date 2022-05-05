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
import java.io.IOException;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

public class ConfigurationAPI {
    public static Configuration loadExistingConfiguration(File file) throws IOException {
        JSONObject jsonObject = new JSONObject(FileUtils.readFileToString(file, Charsets.UTF_8));
        return new Configuration(file, jsonObject.toMap());
    }
    
    public static Configuration newConfiguration(File file) {
        return new Configuration(file);
    }
}

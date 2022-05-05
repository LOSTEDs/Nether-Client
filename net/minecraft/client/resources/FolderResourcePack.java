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

package net.minecraft.client.resources;

import com.google.common.collect.Sets;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import org.apache.commons.io.filefilter.DirectoryFileFilter;

public class FolderResourcePack extends AbstractResourcePack {
    public FolderResourcePack(File resourcePackFileIn) {
        super(resourcePackFileIn);
    }
    
    protected InputStream getInputStreamByName(String name) throws IOException {
        return new BufferedInputStream(new FileInputStream(new File(this.resourcePackFile, name)));
    }
    
    protected boolean hasResourceName(String name) {
        return (new File(this.resourcePackFile, name)).isFile();
    }
    
    public Set<String> getResourceDomains() {
        Set<String> set = Sets.newHashSet();
        File file1 = new File(this.resourcePackFile, "assets/");
        if (file1.isDirectory()) {
            byte b;
            int i;
            File[] arrayOfFile;
            for (i = (arrayOfFile = file1.listFiles((FileFilter)DirectoryFileFilter.DIRECTORY)).length, b = 0; b < i; ) {
                File file2 = arrayOfFile[b];
                String s = getRelativeName(file1, file2);
                if (!s.equals(s.toLowerCase())) {
                    logNameNotLowercase(s);
                } else {
                    set.add(s.substring(0, s.length() - 1));
                } 
                b++;
            } 
        } 
        return set;
    }
}

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

package net.minecraft.client.util;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class JsonException extends IOException {
    private final List<Entry> field_151383_a = Lists.newArrayList();
    
    private final String field_151382_b;
    
    public JsonException(String p_i45279_1_) {
        this.field_151383_a.add(new Entry(null));
        this.field_151382_b = p_i45279_1_;
    }
    
    public JsonException(String p_i45280_1_, Throwable p_i45280_2_) {
        super(p_i45280_2_);
        this.field_151383_a.add(new Entry(null));
        this.field_151382_b = p_i45280_1_;
    }
    
    public void func_151380_a(String p_151380_1_) {
        ((Entry)this.field_151383_a.get(0)).func_151373_a(p_151380_1_);
    }
    
    public void func_151381_b(String p_151381_1_) {
        (this.field_151383_a.get(0)).field_151376_a = p_151381_1_;
        this.field_151383_a.add(0, new Entry(null));
    }
    
    public String getMessage() {
        return "Invalid " + ((Entry)this.field_151383_a.get(this.field_151383_a.size() - 1)).toString() + ": " + this.field_151382_b;
    }
    
    public static JsonException func_151379_a(Exception p_151379_0_) {
        if (p_151379_0_ instanceof JsonException)
            return (JsonException)p_151379_0_; 
        String s = p_151379_0_.getMessage();
        if (p_151379_0_ instanceof java.io.FileNotFoundException)
            s = "File not found"; 
        return new JsonException(s, p_151379_0_);
    }
    
    public static class Entry {
        private String field_151376_a = null;
        
        private final List<String> field_151375_b = Lists.newArrayList();
        
        private void func_151373_a(String p_151373_1_) {
            this.field_151375_b.add(0, p_151373_1_);
        }
        
        public String func_151372_b() {
            return StringUtils.join(this.field_151375_b, "->");
        }
        
        public String toString() {
            return (this.field_151376_a != null) ? (!this.field_151375_b.isEmpty() ? (String.valueOf(this.field_151376_a) + " " + func_151372_b()) : this.field_151376_a) : (!this.field_151375_b.isEmpty() ? ("(Unknown file) " + func_151372_b()) : "(Unknown file)");
        }
        
        private Entry() {}
    }
}

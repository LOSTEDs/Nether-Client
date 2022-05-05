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

package shadersmod.client;

public class ShaderLine {
    private int type;
    
    private String name;
    
    private String value;
    
    private String line;
    
    public static final int TYPE_UNIFORM = 1;
    
    public static final int TYPE_ATTRIBUTE = 2;
    
    public static final int TYPE_CONST_INT = 3;
    
    public static final int TYPE_CONST_FLOAT = 4;
    
    public static final int TYPE_CONST_BOOL = 5;
    
    public static final int TYPE_PROPERTY = 6;
    
    public ShaderLine(int type, String name, String value, String line) {
        this.type = type;
        this.name = name;
        this.value = value;
        this.line = line;
    }
    
    public int getType() {
        return this.type;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public boolean isUniform() {
        return (this.type == 1);
    }
    
    public boolean isUniform(String name) {
        return (isUniform() && name.equals(this.name));
    }
    
    public boolean isAttribute() {
        return (this.type == 2);
    }
    
    public boolean isAttribute(String name) {
        return (isAttribute() && name.equals(this.name));
    }
    
    public boolean isProperty() {
        return (this.type == 6);
    }
    
    public boolean isConstInt() {
        return (this.type == 3);
    }
    
    public boolean isConstFloat() {
        return (this.type == 4);
    }
    
    public boolean isConstBool() {
        return (this.type == 5);
    }
    
    public boolean isProperty(String name) {
        return (isProperty() && name.equals(this.name));
    }
    
    public boolean isProperty(String name, String value) {
        return (isProperty(name) && value.equals(this.value));
    }
    
    public boolean isConstInt(String name) {
        return (isConstInt() && name.equals(this.name));
    }
    
    public boolean isConstIntSuffix(String suffix) {
        return (isConstInt() && this.name.endsWith(suffix));
    }
    
    public boolean isConstFloat(String name) {
        return (isConstFloat() && name.equals(this.name));
    }
    
    public boolean isConstBool(String name) {
        return (isConstBool() && name.equals(this.name));
    }
    
    public boolean isConstBoolSuffix(String suffix) {
        return (isConstBool() && this.name.endsWith(suffix));
    }
    
    public boolean isConstBoolSuffix(String suffix, boolean val) {
        return (isConstBoolSuffix(suffix) && getValueBool() == val);
    }
    
    public boolean isConstBool(String name1, String name2) {
        return !(!isConstBool(name1) && !isConstBool(name2));
    }
    
    public boolean isConstBool(String name1, String name2, String name3) {
        return !(!isConstBool(name1) && !isConstBool(name2) && !isConstBool(name3));
    }
    
    public boolean isConstBool(String name, boolean val) {
        return (isConstBool(name) && getValueBool() == val);
    }
    
    public boolean isConstBool(String name1, String name2, boolean val) {
        return (isConstBool(name1, name2) && getValueBool() == val);
    }
    
    public boolean isConstBool(String name1, String name2, String name3, boolean val) {
        return (isConstBool(name1, name2, name3) && getValueBool() == val);
    }
    
    public int getValueInt() {
        try {
            return Integer.parseInt(this.value);
        } catch (NumberFormatException var2) {
            throw new NumberFormatException("Invalid integer: " + this.value + ", line: " + this.line);
        } 
    }
    
    public float getValueFloat() {
        try {
            return Float.parseFloat(this.value);
        } catch (NumberFormatException var2) {
            throw new NumberFormatException("Invalid float: " + this.value + ", line: " + this.line);
        } 
    }
    
    public boolean getValueBool() {
        String s = this.value.toLowerCase();
        if (!s.equals("true") && !s.equals("false"))
            throw new RuntimeException("Invalid boolean: " + this.value + ", line: " + this.line); 
        return Boolean.valueOf(this.value).booleanValue();
    }
}

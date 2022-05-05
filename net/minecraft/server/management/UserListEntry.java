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

package net.minecraft.server.management;

import com.google.gson.JsonObject;

public class UserListEntry<T> {
    private final T value;
    
    public UserListEntry(T p_i1146_1_) {
        this.value = p_i1146_1_;
    }
    
    protected UserListEntry(T p_i1147_1_, JsonObject p_i1147_2_) {
        this.value = p_i1147_1_;
    }
    
    T getValue() {
        return this.value;
    }
    
    boolean hasBanExpired() {
        return false;
    }
    
    protected void onSerialization(JsonObject data) {}
}

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BanEntry<T> extends UserListEntry<T> {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
    
    protected final Date banStartDate;
    
    protected final String bannedBy;
    
    protected final Date banEndDate;
    
    protected final String reason;
    
    public BanEntry(T valueIn, Date startDate, String banner, Date endDate, String banReason) {
        super(valueIn);
        this.banStartDate = (startDate == null) ? new Date() : startDate;
        this.bannedBy = (banner == null) ? "(Unknown)" : banner;
        this.banEndDate = endDate;
        this.reason = (banReason == null) ? "Banned by an operator." : banReason;
    }
    
    protected BanEntry(T p_i1174_1_, JsonObject p_i1174_2_) {
        super(p_i1174_1_, p_i1174_2_);
        Date date, date1;
        try {
            date = p_i1174_2_.has("created") ? dateFormat.parse(p_i1174_2_.get("created").getAsString()) : new Date();
        } catch (ParseException var7) {
            date = new Date();
        } 
        this.banStartDate = date;
        this.bannedBy = p_i1174_2_.has("source") ? p_i1174_2_.get("source").getAsString() : "(Unknown)";
        try {
            date1 = p_i1174_2_.has("expires") ? dateFormat.parse(p_i1174_2_.get("expires").getAsString()) : null;
        } catch (ParseException var6) {
            date1 = null;
        } 
        this.banEndDate = date1;
        this.reason = p_i1174_2_.has("reason") ? p_i1174_2_.get("reason").getAsString() : "Banned by an operator.";
    }
    
    public Date getBanEndDate() {
        return this.banEndDate;
    }
    
    public String getBanReason() {
        return this.reason;
    }
    
    boolean hasBanExpired() {
        return (this.banEndDate == null) ? false : this.banEndDate.before(new Date());
    }
    
    protected void onSerialization(JsonObject data) {
        data.addProperty("created", dateFormat.format(this.banStartDate));
        data.addProperty("source", this.bannedBy);
        data.addProperty("expires", (this.banEndDate == null) ? "forever" : dateFormat.format(this.banEndDate));
        data.addProperty("reason", this.reason);
    }
}

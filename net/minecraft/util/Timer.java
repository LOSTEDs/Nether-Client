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

package net.minecraft.util;

import net.minecraft.client.Minecraft;

public class Timer {
    private float ticksPerSecond;
    
    private double lastHRTime;
    
    public int elapsedTicks;
    
    public float renderPartialTicks;
    
    public float timerSpeed;
    
    public float elapsedPartialTicks;
    
    private long lastSyncSysClock;
    
    private long lastSyncHRClock;
    
    private long field_74285_i;
    
    private double timeSyncAdjustment;
    
    public Timer(float p_i1018_1_) {
        this.timerSpeed = 1.0F;
        this.timeSyncAdjustment = 1.0D;
        setTicksPerSecond(p_i1018_1_);
        this.lastSyncSysClock = Minecraft.getSystemTime();
        this.lastSyncHRClock = System.nanoTime() / 1000000L;
    }
    
    public void updateTimer() {
        long i = Minecraft.getSystemTime();
        long j = i - this.lastSyncSysClock;
        long k = System.nanoTime() / 1000000L;
        double d0 = k / 1000.0D;
        if (j <= 1000L && j >= 0L) {
            this.field_74285_i += j;
            if (this.field_74285_i > 1000L) {
                long l = k - this.lastSyncHRClock;
                double d2 = this.field_74285_i / l;
                this.timeSyncAdjustment += (d2 - this.timeSyncAdjustment) * 0.20000000298023224D;
                this.lastSyncHRClock = k;
                this.field_74285_i = 0L;
            } 
            if (this.field_74285_i < 0L)
                this.lastSyncHRClock = k; 
        } else {
            this.lastHRTime = d0;
        } 
        this.lastSyncSysClock = i;
        double d3 = (d0 - this.lastHRTime) * this.timeSyncAdjustment;
        this.lastHRTime = d0;
        d3 = MathHelper.clamp_double(d3, 0.0D, 1.0D);
        this.elapsedPartialTicks += (float)(d3 * this.timerSpeed * getTicksPerSecond());
        this.elapsedTicks = (int)this.elapsedPartialTicks;
        this.elapsedPartialTicks -= this.elapsedTicks;
        if (this.elapsedTicks > 10)
            this.elapsedTicks = 10; 
        this.renderPartialTicks = this.elapsedPartialTicks;
    }
    
    public float getTicksPerSecond() {
        return this.ticksPerSecond;
    }
    
    public void setTicksPerSecond(float ticksPerSecond) {
        this.ticksPerSecond = ticksPerSecond;
    }
}

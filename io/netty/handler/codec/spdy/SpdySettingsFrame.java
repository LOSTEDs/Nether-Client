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

package io.netty.handler.codec.spdy;

import java.util.Set;

public interface SpdySettingsFrame extends SpdyFrame {
    public static final int SETTINGS_MINOR_VERSION = 0;
    
    public static final int SETTINGS_UPLOAD_BANDWIDTH = 1;
    
    public static final int SETTINGS_DOWNLOAD_BANDWIDTH = 2;
    
    public static final int SETTINGS_ROUND_TRIP_TIME = 3;
    
    public static final int SETTINGS_MAX_CONCURRENT_STREAMS = 4;
    
    public static final int SETTINGS_CURRENT_CWND = 5;
    
    public static final int SETTINGS_DOWNLOAD_RETRANS_RATE = 6;
    
    public static final int SETTINGS_INITIAL_WINDOW_SIZE = 7;
    
    public static final int SETTINGS_CLIENT_CERTIFICATE_VECTOR_SIZE = 8;
    
    Set<Integer> ids();
    
    boolean isSet(int paramInt);
    
    int getValue(int paramInt);
    
    SpdySettingsFrame setValue(int paramInt1, int paramInt2);
    
    SpdySettingsFrame setValue(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2);
    
    SpdySettingsFrame removeValue(int paramInt);
    
    boolean isPersistValue(int paramInt);
    
    SpdySettingsFrame setPersistValue(int paramInt, boolean paramBoolean);
    
    boolean isPersisted(int paramInt);
    
    SpdySettingsFrame setPersisted(int paramInt, boolean paramBoolean);
    
    boolean clearPreviouslyPersistedSettings();
    
    SpdySettingsFrame setClearPreviouslyPersistedSettings(boolean paramBoolean);
}

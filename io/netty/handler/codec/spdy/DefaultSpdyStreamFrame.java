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

public abstract class DefaultSpdyStreamFrame implements SpdyStreamFrame {
    private int streamId;
    
    private boolean last;
    
    protected DefaultSpdyStreamFrame(int streamId) {
        setStreamId(streamId);
    }
    
    public int streamId() {
        return this.streamId;
    }
    
    public SpdyStreamFrame setStreamId(int streamId) {
        if (streamId <= 0)
            throw new IllegalArgumentException("Stream-ID must be positive: " + streamId); 
        this.streamId = streamId;
        return this;
    }
    
    public boolean isLast() {
        return this.last;
    }
    
    public SpdyStreamFrame setLast(boolean last) {
        this.last = last;
        return this;
    }
}

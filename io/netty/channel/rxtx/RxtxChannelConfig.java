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

package io.netty.channel.rxtx;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelConfig;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;

public interface RxtxChannelConfig extends ChannelConfig {
    RxtxChannelConfig setBaudrate(int paramInt);
    
    RxtxChannelConfig setStopbits(Stopbits paramStopbits);
    
    RxtxChannelConfig setDatabits(Databits paramDatabits);
    
    RxtxChannelConfig setParitybit(Paritybit paramParitybit);
    
    int getBaudrate();
    
    Stopbits getStopbits();
    
    Databits getDatabits();
    
    Paritybit getParitybit();
    
    boolean isDtr();
    
    RxtxChannelConfig setDtr(boolean paramBoolean);
    
    boolean isRts();
    
    RxtxChannelConfig setRts(boolean paramBoolean);
    
    int getWaitTimeMillis();
    
    RxtxChannelConfig setWaitTimeMillis(int paramInt);
    
    RxtxChannelConfig setReadTimeout(int paramInt);
    
    int getReadTimeout();
    
    RxtxChannelConfig setConnectTimeoutMillis(int paramInt);
    
    RxtxChannelConfig setMaxMessagesPerRead(int paramInt);
    
    RxtxChannelConfig setWriteSpinCount(int paramInt);
    
    RxtxChannelConfig setAllocator(ByteBufAllocator paramByteBufAllocator);
    
    RxtxChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator paramRecvByteBufAllocator);
    
    RxtxChannelConfig setAutoRead(boolean paramBoolean);
    
    RxtxChannelConfig setAutoClose(boolean paramBoolean);
    
    RxtxChannelConfig setWriteBufferHighWaterMark(int paramInt);
    
    RxtxChannelConfig setWriteBufferLowWaterMark(int paramInt);
    
    RxtxChannelConfig setMessageSizeEstimator(MessageSizeEstimator paramMessageSizeEstimator);
    
    public enum Stopbits {
        STOPBITS_1(1),
        STOPBITS_2(2),
        STOPBITS_1_5(3);
        
        private final int value;
        
        Stopbits(int value) {
            this.value = value;
        }
        
        public int value() {
            return this.value;
        }
    }
    
    public enum Databits {
        DATABITS_5(5),
        DATABITS_6(6),
        DATABITS_7(7),
        DATABITS_8(8);
        
        private final int value;
        
        Databits(int value) {
            this.value = value;
        }
        
        public int value() {
            return this.value;
        }
    }
    
    public enum Paritybit {
        NONE(0),
        ODD(1),
        EVEN(2),
        MARK(3),
        SPACE(4);
        
        private final int value;
        
        Paritybit(int value) {
            this.value = value;
        }
        
        public int value() {
            return this.value;
        }
    }
}

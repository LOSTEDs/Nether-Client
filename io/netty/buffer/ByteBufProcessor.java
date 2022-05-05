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

package io.netty.buffer;

public interface ByteBufProcessor {
    public static final ByteBufProcessor FIND_NUL = new ByteBufProcessor() {
            public boolean process(byte value) throws Exception {
                return (value != 0);
            }
        };
    
    public static final ByteBufProcessor FIND_NON_NUL = new ByteBufProcessor() {
            public boolean process(byte value) throws Exception {
                return (value == 0);
            }
        };
    
    public static final ByteBufProcessor FIND_CR = new ByteBufProcessor() {
            public boolean process(byte value) throws Exception {
                return (value != 13);
            }
        };
    
    public static final ByteBufProcessor FIND_NON_CR = new ByteBufProcessor() {
            public boolean process(byte value) throws Exception {
                return (value == 13);
            }
        };
    
    public static final ByteBufProcessor FIND_LF = new ByteBufProcessor() {
            public boolean process(byte value) throws Exception {
                return (value != 10);
            }
        };
    
    public static final ByteBufProcessor FIND_NON_LF = new ByteBufProcessor() {
            public boolean process(byte value) throws Exception {
                return (value == 10);
            }
        };
    
    public static final ByteBufProcessor FIND_CRLF = new ByteBufProcessor() {
            public boolean process(byte value) throws Exception {
                return (value != 13 && value != 10);
            }
        };
    
    public static final ByteBufProcessor FIND_NON_CRLF = new ByteBufProcessor() {
            public boolean process(byte value) throws Exception {
                return (value == 13 || value == 10);
            }
        };
    
    public static final ByteBufProcessor FIND_LINEAR_WHITESPACE = new ByteBufProcessor() {
            public boolean process(byte value) throws Exception {
                return (value != 32 && value != 9);
            }
        };
    
    public static final ByteBufProcessor FIND_NON_LINEAR_WHITESPACE = new ByteBufProcessor() {
            public boolean process(byte value) throws Exception {
                return (value == 32 || value == 9);
            }
        };
    
    boolean process(byte paramByte) throws Exception;
}

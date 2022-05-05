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

package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestCalculatingInputStream extends ObservableInputStream {
    private final MessageDigest messageDigest;
    
    public static class MessageDigestMaintainingObserver extends ObservableInputStream.Observer {
        private final MessageDigest messageDigest;
        
        public MessageDigestMaintainingObserver(MessageDigest messageDigest) {
            this.messageDigest = messageDigest;
        }
        
        public void data(int input) throws IOException {
            this.messageDigest.update((byte)input);
        }
        
        public void data(byte[] input, int offset, int length) throws IOException {
            this.messageDigest.update(input, offset, length);
        }
    }
    
    public MessageDigestCalculatingInputStream(InputStream inputStream, MessageDigest messageDigest) {
        super(inputStream);
        this.messageDigest = messageDigest;
        add(new MessageDigestMaintainingObserver(messageDigest));
    }
    
    public MessageDigestCalculatingInputStream(InputStream inputStream, String algorithm) throws NoSuchAlgorithmException {
        this(inputStream, MessageDigest.getInstance(algorithm));
    }
    
    public MessageDigestCalculatingInputStream(InputStream inputStream) throws NoSuchAlgorithmException {
        this(inputStream, MessageDigest.getInstance("MD5"));
    }
    
    public MessageDigest getMessageDigest() {
        return this.messageDigest;
    }
}

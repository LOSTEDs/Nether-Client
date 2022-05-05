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

package io.netty.handler.ssl.util;

import io.netty.util.internal.ThreadLocalRandom;
import java.security.SecureRandom;
import java.util.Random;

final class ThreadLocalInsecureRandom extends SecureRandom {
    private static final long serialVersionUID = -8209473337192526191L;
    
    private static final SecureRandom INSTANCE = new ThreadLocalInsecureRandom();
    
    static SecureRandom current() {
        return INSTANCE;
    }
    
    public String getAlgorithm() {
        return "insecure";
    }
    
    public void setSeed(byte[] seed) {}
    
    public void setSeed(long seed) {}
    
    public void nextBytes(byte[] bytes) {
        random().nextBytes(bytes);
    }
    
    public byte[] generateSeed(int numBytes) {
        byte[] seed = new byte[numBytes];
        random().nextBytes(seed);
        return seed;
    }
    
    public int nextInt() {
        return random().nextInt();
    }
    
    public int nextInt(int n) {
        return random().nextInt(n);
    }
    
    public boolean nextBoolean() {
        return random().nextBoolean();
    }
    
    public long nextLong() {
        return random().nextLong();
    }
    
    public float nextFloat() {
        return random().nextFloat();
    }
    
    public double nextDouble() {
        return random().nextDouble();
    }
    
    public double nextGaussian() {
        return random().nextGaussian();
    }
    
    private static Random random() {
        return (Random)ThreadLocalRandom.current();
    }
}

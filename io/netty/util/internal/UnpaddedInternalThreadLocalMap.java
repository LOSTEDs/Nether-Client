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

package io.netty.util.internal;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

class UnpaddedInternalThreadLocalMap {
    static ThreadLocal<InternalThreadLocalMap> slowThreadLocalMap;
    
    static final AtomicInteger nextIndex = new AtomicInteger();
    
    Object[] indexedVariables;
    
    int futureListenerStackDepth;
    
    int localChannelReaderStackDepth;
    
    Map<Class<?>, Boolean> handlerSharableCache;
    
    IntegerHolder counterHashCode;
    
    ThreadLocalRandom random;
    
    Map<Class<?>, TypeParameterMatcher> typeParameterMatcherGetCache;
    
    Map<Class<?>, Map<String, TypeParameterMatcher>> typeParameterMatcherFindCache;
    
    StringBuilder stringBuilder;
    
    Map<Charset, CharsetEncoder> charsetEncoderCache;
    
    Map<Charset, CharsetDecoder> charsetDecoderCache;
    
    UnpaddedInternalThreadLocalMap(Object[] indexedVariables) {
        this.indexedVariables = indexedVariables;
    }
}

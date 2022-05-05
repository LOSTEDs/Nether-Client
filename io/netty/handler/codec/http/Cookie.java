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

package io.netty.handler.codec.http;

import java.util.Set;

public interface Cookie extends Comparable<Cookie> {
    String getName();
    
    String getValue();
    
    void setValue(String paramString);
    
    String getDomain();
    
    void setDomain(String paramString);
    
    String getPath();
    
    void setPath(String paramString);
    
    String getComment();
    
    void setComment(String paramString);
    
    long getMaxAge();
    
    void setMaxAge(long paramLong);
    
    int getVersion();
    
    void setVersion(int paramInt);
    
    boolean isSecure();
    
    void setSecure(boolean paramBoolean);
    
    boolean isHttpOnly();
    
    void setHttpOnly(boolean paramBoolean);
    
    String getCommentUrl();
    
    void setCommentUrl(String paramString);
    
    boolean isDiscard();
    
    void setDiscard(boolean paramBoolean);
    
    Set<Integer> getPorts();
    
    void setPorts(int... paramVarArgs);
    
    void setPorts(Iterable<Integer> paramIterable);
}

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

package io.netty.channel.sctp;

import com.sun.nio.sctp.AbstractNotificationHandler;
import com.sun.nio.sctp.AssociationChangeNotification;
import com.sun.nio.sctp.HandlerResult;
import com.sun.nio.sctp.Notification;
import com.sun.nio.sctp.PeerAddressChangeNotification;
import com.sun.nio.sctp.SendFailedNotification;
import com.sun.nio.sctp.ShutdownNotification;

public final class SctpNotificationHandler extends AbstractNotificationHandler<Object> {
    private final SctpChannel sctpChannel;
    
    public SctpNotificationHandler(SctpChannel sctpChannel) {
        if (sctpChannel == null)
            throw new NullPointerException("sctpChannel"); 
        this.sctpChannel = sctpChannel;
    }
    
    public HandlerResult handleNotification(AssociationChangeNotification notification, Object o) {
        fireEvent(notification);
        return HandlerResult.CONTINUE;
    }
    
    public HandlerResult handleNotification(PeerAddressChangeNotification notification, Object o) {
        fireEvent(notification);
        return HandlerResult.CONTINUE;
    }
    
    public HandlerResult handleNotification(SendFailedNotification notification, Object o) {
        fireEvent(notification);
        return HandlerResult.CONTINUE;
    }
    
    public HandlerResult handleNotification(ShutdownNotification notification, Object o) {
        fireEvent(notification);
        this.sctpChannel.close();
        return HandlerResult.RETURN;
    }
    
    private void fireEvent(Notification notification) {
        this.sctpChannel.pipeline().fireUserEventTriggered(notification);
    }
}

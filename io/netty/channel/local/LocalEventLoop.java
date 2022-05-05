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

package io.netty.channel.local;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.SingleThreadEventLoop;
import java.util.concurrent.ThreadFactory;

final class LocalEventLoop extends SingleThreadEventLoop {
    LocalEventLoop(LocalEventLoopGroup parent, ThreadFactory threadFactory) {
        super((EventLoopGroup)parent, threadFactory, true);
    }
    
    protected void run() {
        do {
            Runnable task = takeTask();
            if (task == null)
                continue; 
            task.run();
            updateLastExecutionTime();
        } while (!confirmShutdown());
    }
}

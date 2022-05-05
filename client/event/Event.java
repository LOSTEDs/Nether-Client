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

package client.event;

import java.lang.reflect.InvocationTargetException;

public abstract class Event {
    private boolean cancelled;
    
    public enum State {
        PRE("PRE", 0),
        POST("POST", 1);
    }
    
    public Event call() {
        this.cancelled = false;
        call(this);
        return this;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    private static final void call(Event event) {
        ArrayHelper<Data> dataList = EventManager.get((Class)event.getClass());
        if (dataList != null)
            for (Data data : dataList) {
                try {
                    data.target.invoke(data.source, new Object[] { event });
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } 
            }  
    }
}

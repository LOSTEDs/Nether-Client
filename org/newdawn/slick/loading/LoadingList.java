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

package org.newdawn.slick.loading;

import java.util.ArrayList;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.opengl.InternalTextureLoader;
import org.newdawn.slick.util.Log;

public class LoadingList {
    private static LoadingList single = new LoadingList();
    
    public static LoadingList get() {
        return single;
    }
    
    public static void setDeferredLoading(boolean loading) {
        single = new LoadingList();
        InternalTextureLoader.get().setDeferredLoading(loading);
        SoundStore.get().setDeferredLoading(loading);
    }
    
    public static boolean isDeferredLoading() {
        return InternalTextureLoader.get().isDeferredLoading();
    }
    
    private ArrayList deferred = new ArrayList();
    
    private int total;
    
    public void add(DeferredResource resource) {
        this.total++;
        this.deferred.add(resource);
    }
    
    public void remove(DeferredResource resource) {
        Log.info("Early loading of deferred resource due to req: " + resource.getDescription());
        this.total--;
        this.deferred.remove(resource);
    }
    
    public int getTotalResources() {
        return this.total;
    }
    
    public int getRemainingResources() {
        return this.deferred.size();
    }
    
    public DeferredResource getNext() {
        if (this.deferred.size() == 0)
            return null; 
        return this.deferred.remove(0);
    }
}

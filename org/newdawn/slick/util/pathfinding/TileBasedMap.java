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

package org.newdawn.slick.util.pathfinding;

public interface TileBasedMap {
    int getWidthInTiles();
    
    int getHeightInTiles();
    
    void pathFinderVisited(int paramInt1, int paramInt2);
    
    boolean blocked(PathFindingContext paramPathFindingContext, int paramInt1, int paramInt2);
    
    float getCost(PathFindingContext paramPathFindingContext, int paramInt1, int paramInt2);
}

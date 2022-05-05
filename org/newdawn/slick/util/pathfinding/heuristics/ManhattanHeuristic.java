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

package org.newdawn.slick.util.pathfinding.heuristics;

import org.newdawn.slick.util.pathfinding.AStarHeuristic;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class ManhattanHeuristic implements AStarHeuristic {
    private int minimumCost;
    
    public ManhattanHeuristic(int minimumCost) {
        this.minimumCost = minimumCost;
    }
    
    public float getCost(TileBasedMap map, Mover mover, int x, int y, int tx, int ty) {
        return (this.minimumCost * (Math.abs(x - tx) + Math.abs(y - ty)));
    }
}

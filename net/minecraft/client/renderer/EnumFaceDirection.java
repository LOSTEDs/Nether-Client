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

package net.minecraft.client.renderer;

import net.minecraft.util.EnumFacing;

public enum EnumFaceDirection {
    DOWN(new VertexInformation[] { new VertexInformation(Constants.WEST_INDEX, Constants.DOWN_INDEX, Constants.SOUTH_INDEX, null), new VertexInformation(Constants.WEST_INDEX, Constants.DOWN_INDEX, Constants.NORTH_INDEX, null), new VertexInformation(Constants.EAST_INDEX, Constants.DOWN_INDEX, Constants.NORTH_INDEX, null), new VertexInformation(Constants.EAST_INDEX, Constants.DOWN_INDEX, Constants.SOUTH_INDEX, null) }),
    UP(new VertexInformation[] { new VertexInformation(Constants.WEST_INDEX, Constants.UP_INDEX, Constants.NORTH_INDEX, null), new VertexInformation(Constants.WEST_INDEX, Constants.UP_INDEX, Constants.SOUTH_INDEX, null), new VertexInformation(Constants.EAST_INDEX, Constants.UP_INDEX, Constants.SOUTH_INDEX, null), new VertexInformation(Constants.EAST_INDEX, Constants.UP_INDEX, Constants.NORTH_INDEX, null) }),
    NORTH(new VertexInformation[] { new VertexInformation(Constants.EAST_INDEX, Constants.UP_INDEX, Constants.NORTH_INDEX, null), new VertexInformation(Constants.EAST_INDEX, Constants.DOWN_INDEX, Constants.NORTH_INDEX, null), new VertexInformation(Constants.WEST_INDEX, Constants.DOWN_INDEX, Constants.NORTH_INDEX, null), new VertexInformation(Constants.WEST_INDEX, Constants.UP_INDEX, Constants.NORTH_INDEX, null) }),
    SOUTH(new VertexInformation[] { new VertexInformation(Constants.WEST_INDEX, Constants.UP_INDEX, Constants.SOUTH_INDEX, null), new VertexInformation(Constants.WEST_INDEX, Constants.DOWN_INDEX, Constants.SOUTH_INDEX, null), new VertexInformation(Constants.EAST_INDEX, Constants.DOWN_INDEX, Constants.SOUTH_INDEX, null), new VertexInformation(Constants.EAST_INDEX, Constants.UP_INDEX, Constants.SOUTH_INDEX, null) }),
    WEST(new VertexInformation[] { new VertexInformation(Constants.WEST_INDEX, Constants.UP_INDEX, Constants.NORTH_INDEX, null), new VertexInformation(Constants.WEST_INDEX, Constants.DOWN_INDEX, Constants.NORTH_INDEX, null), new VertexInformation(Constants.WEST_INDEX, Constants.DOWN_INDEX, Constants.SOUTH_INDEX, null), new VertexInformation(Constants.WEST_INDEX, Constants.UP_INDEX, Constants.SOUTH_INDEX, null) }),
    EAST(new VertexInformation[] { new VertexInformation(Constants.EAST_INDEX, Constants.UP_INDEX, Constants.SOUTH_INDEX, null), new VertexInformation(Constants.EAST_INDEX, Constants.DOWN_INDEX, Constants.SOUTH_INDEX, null), new VertexInformation(Constants.EAST_INDEX, Constants.DOWN_INDEX, Constants.NORTH_INDEX, null), new VertexInformation(Constants.EAST_INDEX, Constants.UP_INDEX, Constants.NORTH_INDEX, null) });
    
    private static final EnumFaceDirection[] facings;
    
    private final VertexInformation[] vertexInfos;
    
    static {
        facings = new EnumFaceDirection[6];
        facings[Constants.DOWN_INDEX] = DOWN;
        facings[Constants.UP_INDEX] = UP;
        facings[Constants.NORTH_INDEX] = NORTH;
        facings[Constants.SOUTH_INDEX] = SOUTH;
        facings[Constants.WEST_INDEX] = WEST;
        facings[Constants.EAST_INDEX] = EAST;
    }
    
    public static EnumFaceDirection getFacing(EnumFacing facing) {
        return facings[facing.getIndex()];
    }
    
    EnumFaceDirection(VertexInformation[] vertexInfosIn) {
        this.vertexInfos = vertexInfosIn;
    }
    
    public VertexInformation func_179025_a(int p_179025_1_) {
        return this.vertexInfos[p_179025_1_];
    }
    
    public static final class Constants {
        public static final int SOUTH_INDEX = EnumFacing.SOUTH.getIndex();
        
        public static final int UP_INDEX = EnumFacing.UP.getIndex();
        
        public static final int EAST_INDEX = EnumFacing.EAST.getIndex();
        
        public static final int NORTH_INDEX = EnumFacing.NORTH.getIndex();
        
        public static final int DOWN_INDEX = EnumFacing.DOWN.getIndex();
        
        public static final int WEST_INDEX = EnumFacing.WEST.getIndex();
    }
    
    public static class VertexInformation {
        public final int field_179184_a;
        
        public final int field_179182_b;
        
        public final int field_179183_c;
        
        private VertexInformation(int p_i46270_1_, int p_i46270_2_, int p_i46270_3_) {
            this.field_179184_a = p_i46270_1_;
            this.field_179182_b = p_i46270_2_;
            this.field_179183_c = p_i46270_3_;
        }
    }
}

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

package net.minecraft.client.renderer.block.model;

import net.minecraft.client.renderer.EnumFaceDirection;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.src.Config;
import net.minecraft.src.Reflector;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3i;
import net.minecraftforge.client.model.ITransformation;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.ReadableVector3f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import shadersmod.client.Shaders;

public class FaceBakery {
    public BakedQuad makeBakedQuad(Vector3f posFrom, Vector3f posTo, BlockPartFace face, TextureAtlasSprite sprite, EnumFacing facing, ModelRotation modelRotationIn, BlockPartRotation partRotation, boolean uvLocked, boolean shade) {
        return makeBakedQuad(posFrom, posTo, face, sprite, facing, (ITransformation)modelRotationIn, partRotation, uvLocked, shade);
    }
    
    public BakedQuad makeBakedQuad(Vector3f posFrom, Vector3f posTo, BlockPartFace face, TextureAtlasSprite sprite, EnumFacing facing, ITransformation modelRotationIn, BlockPartRotation partRotation, boolean uvLocked, boolean shade) {
        int[] var10 = makeQuadVertexData(face, sprite, facing, getPositionsDiv16(posFrom, posTo), modelRotationIn, partRotation, uvLocked, shade);
        EnumFacing var11 = getFacingFromVertexData(var10);
        if (uvLocked)
            func_178409_a(var10, var11, face.blockFaceUV, sprite); 
        if (partRotation == null)
            applyFacing(var10, var11); 
        if (Reflector.ForgeHooksClient_fillNormal.exists())
            Reflector.callVoid(Reflector.ForgeHooksClient_fillNormal, new Object[] { var10, var11 }); 
        return new BakedQuad(var10, face.tintIndex, var11, sprite);
    }
    
    private int[] makeQuadVertexData(BlockPartFace p_178405_1_, TextureAtlasSprite p_178405_2_, EnumFacing p_178405_3_, float[] p_178405_4_, ITransformation p_178405_5_, BlockPartRotation p_178405_6_, boolean p_178405_7_, boolean shade) {
        int vertexSize = 28;
        if (Config.isShaders())
            vertexSize = 56; 
        int[] aint = new int[vertexSize];
        for (int var10 = 0; var10 < 4; var10++)
            fillVertexData(aint, var10, p_178405_3_, p_178405_1_, p_178405_4_, p_178405_2_, p_178405_5_, p_178405_6_, p_178405_7_, shade); 
        return aint;
    }
    
    private int getFaceShadeColor(EnumFacing p_178413_1_) {
        float var2 = getFaceBrightness(p_178413_1_);
        int var3 = MathHelper.clamp_int((int)(var2 * 255.0F), 0, 255);
        return 0xFF000000 | var3 << 16 | var3 << 8 | var3;
    }
    
    private float getFaceBrightness(EnumFacing p_178412_1_) {
        switch (SwitchEnumFacing.field_178400_a[p_178412_1_.ordinal()]) {
            case 1:
                if (Config.isShaders())
                    return Shaders.blockLightLevel05; 
                return 0.5F;
            case 2:
                return 1.0F;
            case 3:
            case 4:
                if (Config.isShaders())
                    return Shaders.blockLightLevel08; 
                return 0.8F;
            case 5:
            case 6:
                if (Config.isShaders())
                    return Shaders.blockLightLevel06; 
                return 0.6F;
        } 
        return 1.0F;
    }
    
    private float[] getPositionsDiv16(Vector3f pos1, Vector3f pos2) {
        float[] afloat = new float[(EnumFacing.values()).length];
        afloat[EnumFaceDirection.Constants.WEST_INDEX] = pos1.x / 16.0F;
        afloat[EnumFaceDirection.Constants.DOWN_INDEX] = pos1.y / 16.0F;
        afloat[EnumFaceDirection.Constants.NORTH_INDEX] = pos1.z / 16.0F;
        afloat[EnumFaceDirection.Constants.EAST_INDEX] = pos2.x / 16.0F;
        afloat[EnumFaceDirection.Constants.UP_INDEX] = pos2.y / 16.0F;
        afloat[EnumFaceDirection.Constants.SOUTH_INDEX] = pos2.z / 16.0F;
        return afloat;
    }
    
    private void fillVertexData(int[] faceData, int vertexIndex, EnumFacing facing, BlockPartFace partFace, float[] p_178402_5_, TextureAtlasSprite sprite, ITransformation modelRotationIn, BlockPartRotation partRotation, boolean uvLocked, boolean shade) {
        EnumFacing var11 = modelRotationIn.rotate(facing);
        int var12 = shade ? getFaceShadeColor(var11) : -1;
        EnumFaceDirection.VertexInformation var13 = EnumFaceDirection.getFacing(facing).func_179025_a(vertexIndex);
        Vector3f var14 = new Vector3f(p_178402_5_[var13.field_179184_a], p_178402_5_[var13.field_179182_b], p_178402_5_[var13.field_179183_c]);
        func_178407_a(var14, partRotation);
        int var15 = rotateVertex(var14, facing, vertexIndex, modelRotationIn, uvLocked);
        storeVertexData(faceData, var15, vertexIndex, var14, var12, sprite, partFace.blockFaceUV);
    }
    
    private void storeVertexData(int[] faceData, int storeIndex, int vertexIndex, Vector3f position, int shadeColor, TextureAtlasSprite sprite, BlockFaceUV faceUV) {
        int step = faceData.length / 4;
        int i = storeIndex * step;
        faceData[i] = Float.floatToRawIntBits(position.x);
        faceData[i + 1] = Float.floatToRawIntBits(position.y);
        faceData[i + 2] = Float.floatToRawIntBits(position.z);
        faceData[i + 3] = shadeColor;
        faceData[i + 4] = Float.floatToRawIntBits(sprite.getInterpolatedU(faceUV.func_178348_a(vertexIndex)));
        faceData[i + 4 + 1] = Float.floatToRawIntBits(sprite.getInterpolatedV(faceUV.func_178346_b(vertexIndex)));
    }
    
    private void func_178407_a(Vector3f p_178407_1_, BlockPartRotation p_178407_2_) {
        if (p_178407_2_ != null) {
            Matrix4f var3 = getMatrixIdentity();
            Vector3f var4 = new Vector3f(0.0F, 0.0F, 0.0F);
            switch (SwitchEnumFacing.field_178399_b[p_178407_2_.axis.ordinal()]) {
                case 1:
                    Matrix4f.rotate(p_178407_2_.angle * 0.017453292F, new Vector3f(1.0F, 0.0F, 0.0F), var3, var3);
                    var4.set(0.0F, 1.0F, 1.0F);
                    break;
                case 2:
                    Matrix4f.rotate(p_178407_2_.angle * 0.017453292F, new Vector3f(0.0F, 1.0F, 0.0F), var3, var3);
                    var4.set(1.0F, 0.0F, 1.0F);
                    break;
                case 3:
                    Matrix4f.rotate(p_178407_2_.angle * 0.017453292F, new Vector3f(0.0F, 0.0F, 1.0F), var3, var3);
                    var4.set(1.0F, 1.0F, 0.0F);
                    break;
            } 
            if (p_178407_2_
                .rescale) {
                if (Math.abs(p_178407_2_.angle) == 22.5F) {
                    var4.scale(field_178418_a);
                } else {
                    var4.scale(field_178417_b);
                } 
                Vector3f.add(var4, new Vector3f(1.0F, 1.0F, 1.0F), var4);
            } else {
                var4.set(1.0F, 1.0F, 1.0F);
            } 
            rotateScale(p_178407_1_, new Vector3f((ReadableVector3f)p_178407_2_.origin), var3, var4);
        } 
    }
    
    public int rotateVertex(Vector3f position, EnumFacing facing, int vertexIndex, ModelRotation modelRotationIn, boolean uvLocked) {
        return rotateVertex(position, facing, vertexIndex, (ITransformation)modelRotationIn, uvLocked);
    }
    
    public int rotateVertex(Vector3f position, EnumFacing facing, int vertexIndex, ITransformation modelRotationIn, boolean uvLocked) {
        if (modelRotationIn == ModelRotation.X0_Y0)
            return vertexIndex; 
        if (Reflector.ForgeHooksClient_transform.exists()) {
            Reflector.call(Reflector.ForgeHooksClient_transform, new Object[] { position, modelRotationIn.getMatrix() });
        } else {
            rotateScale(position, new Vector3f(0.5F, 0.5F, 0.5F), ((ModelRotation)modelRotationIn).getMatrix4d(), new Vector3f(1.0F, 1.0F, 1.0F));
        } 
        return modelRotationIn.rotate(facing, vertexIndex);
    }
    
    private void rotateScale(Vector3f position, Vector3f rotationOrigin, Matrix4f rotationMatrix, Vector3f scale) {
        Vector4f var5 = new Vector4f(position.x - rotationOrigin.x, position.y - rotationOrigin.y, position.z - rotationOrigin.z, 1.0F);
        Matrix4f.transform(rotationMatrix, var5, var5);
        Vector4f vector4f = var5;
        vector4f.x *= scale.x;
        Vector4f vector4f2 = var5;
        vector4f2.y *= scale.y;
        Vector4f vector4f3 = var5;
        vector4f3.z *= scale.z;
        position.set(var5.x + rotationOrigin.x, var5.y + rotationOrigin.y, var5.z + rotationOrigin.z);
    }
    
    private Matrix4f getMatrixIdentity() {
        Matrix4f var1 = new Matrix4f();
        var1.setIdentity();
        return var1;
    }
    
    public static EnumFacing getFacingFromVertexData(int[] faceData) {
        int step = faceData.length / 4;
        int step2 = step * 2;
        int step3 = step * 3;
        Vector3f vector3f = new Vector3f(Float.intBitsToFloat(faceData[0]), Float.intBitsToFloat(faceData[1]), Float.intBitsToFloat(faceData[2]));
        Vector3f vector3f2 = new Vector3f(Float.intBitsToFloat(faceData[step]), Float.intBitsToFloat(faceData[step + 1]), Float.intBitsToFloat(faceData[step + 2]));
        Vector3f vector3f3 = new Vector3f(Float.intBitsToFloat(faceData[step2]), Float.intBitsToFloat(faceData[step2 + 1]), Float.intBitsToFloat(faceData[step2 + 2]));
        Vector3f vector3f4 = new Vector3f();
        Vector3f vector3f5 = new Vector3f();
        Vector3f vector3f6 = new Vector3f();
        Vector3f.sub(vector3f, vector3f2, vector3f4);
        Vector3f.sub(vector3f3, vector3f2, vector3f5);
        Vector3f.cross(vector3f5, vector3f4, vector3f6);
        float f = (float)Math.sqrt((vector3f6.x * vector3f6.x + vector3f6.y * vector3f6.y + vector3f6.z * vector3f6.z));
        Vector3f vector3f8 = vector3f6;
        vector3f8.x /= f;
        Vector3f vector3f9 = vector3f6;
        vector3f9.y /= f;
        Vector3f vector3f10 = vector3f6;
        vector3f10.z /= f;
        EnumFacing enumfacing = null;
        float f2 = 0.0F;
        byte b;
        int i;
        EnumFacing[] arrayOfEnumFacing;
        for (i = (arrayOfEnumFacing = EnumFacing.values()).length, b = 0; b < i; ) {
            EnumFacing enumfacing2 = arrayOfEnumFacing[b];
            Vec3i vec3i = enumfacing2.getDirectionVec();
            Vector3f vector3f7 = new Vector3f(vec3i.getX(), vec3i.getY(), vec3i.getZ());
            float f3 = Vector3f.dot(vector3f6, vector3f7);
            if (f3 >= 0.0F && f3 > f2) {
                f2 = f3;
                enumfacing = enumfacing2;
            } 
            b++;
        } 
        if (f2 < 0.719F)
            if (enumfacing == EnumFacing.EAST || enumfacing == EnumFacing.WEST || enumfacing == EnumFacing.NORTH || enumfacing == EnumFacing.SOUTH) {
                enumfacing = EnumFacing.NORTH;
            } else {
                enumfacing = EnumFacing.UP;
            }  
        if (enumfacing == null)
            return EnumFacing.UP; 
        return enumfacing;
    }
    
    public void func_178409_a(int[] p_178409_1_, EnumFacing p_178409_2_, BlockFaceUV p_178409_3_, TextureAtlasSprite p_178409_4_) {
        for (int var5 = 0; var5 < 4; var5++)
            func_178401_a(var5, p_178409_1_, p_178409_2_, p_178409_3_, p_178409_4_); 
    }
    
    private void applyFacing(int[] p_178408_1_, EnumFacing p_178408_2_) {
        int[] aint = new int[p_178408_1_.length];
        System.arraycopy(p_178408_1_, 0, aint, 0, p_178408_1_.length);
        float[] afloat = new float[(EnumFacing.values()).length];
        afloat[EnumFaceDirection.Constants.WEST_INDEX] = 999.0F;
        afloat[EnumFaceDirection.Constants.DOWN_INDEX] = 999.0F;
        afloat[EnumFaceDirection.Constants.NORTH_INDEX] = 999.0F;
        afloat[EnumFaceDirection.Constants.EAST_INDEX] = -999.0F;
        afloat[EnumFaceDirection.Constants.UP_INDEX] = -999.0F;
        afloat[EnumFaceDirection.Constants.SOUTH_INDEX] = -999.0F;
        int step = p_178408_1_.length / 4;
        for (int i = 0; i < 4; i++) {
            int k = step * i;
            float f = Float.intBitsToFloat(aint[k]);
            float f2 = Float.intBitsToFloat(aint[k + 1]);
            float f3 = Float.intBitsToFloat(aint[k + 2]);
            if (f < afloat[EnumFaceDirection.Constants.WEST_INDEX])
                afloat[EnumFaceDirection.Constants.WEST_INDEX] = f; 
            if (f2 < afloat[EnumFaceDirection.Constants.DOWN_INDEX])
                afloat[EnumFaceDirection.Constants.DOWN_INDEX] = f2; 
            if (f3 < afloat[EnumFaceDirection.Constants.NORTH_INDEX])
                afloat[EnumFaceDirection.Constants.NORTH_INDEX] = f3; 
            if (f > afloat[EnumFaceDirection.Constants.EAST_INDEX])
                afloat[EnumFaceDirection.Constants.EAST_INDEX] = f; 
            if (f2 > afloat[EnumFaceDirection.Constants.UP_INDEX])
                afloat[EnumFaceDirection.Constants.UP_INDEX] = f2; 
            if (f3 > afloat[EnumFaceDirection.Constants.SOUTH_INDEX])
                afloat[EnumFaceDirection.Constants.SOUTH_INDEX] = f3; 
        } 
        EnumFaceDirection var17 = EnumFaceDirection.getFacing(p_178408_2_);
        for (int j = 0; j < 4; j++) {
            int j2 = step * j;
            EnumFaceDirection.VertexInformation var18 = var17.func_179025_a(j);
            float f3 = afloat[var18.field_179184_a];
            float var19 = afloat[var18.field_179182_b];
            float var20 = afloat[var18.field_179183_c];
            p_178408_1_[j2] = Float.floatToRawIntBits(f3);
            p_178408_1_[j2 + 1] = Float.floatToRawIntBits(var19);
            p_178408_1_[j2 + 2] = Float.floatToRawIntBits(var20);
            for (int var21 = 0; var21 < 4; var21++) {
                int var22 = step * var21;
                float var23 = Float.intBitsToFloat(aint[var22]);
                float var24 = Float.intBitsToFloat(aint[var22 + 1]);
                float var25 = Float.intBitsToFloat(aint[var22 + 2]);
                if (MathHelper.epsilonEquals(f3, var23) && MathHelper.epsilonEquals(var19, var24) && MathHelper.epsilonEquals(var20, var25)) {
                    p_178408_1_[j2 + 4] = aint[var22 + 4];
                    p_178408_1_[j2 + 4 + 1] = aint[var22 + 4 + 1];
                } 
            } 
        } 
    }
    
    private void func_178401_a(int p_178401_1_, int[] p_178401_2_, EnumFacing p_178401_3_, BlockFaceUV p_178401_4_, TextureAtlasSprite p_178401_5_) {
        int step = p_178401_2_.length / 4;
        int var6 = step * p_178401_1_;
        float var7 = Float.intBitsToFloat(p_178401_2_[var6]);
        float var8 = Float.intBitsToFloat(p_178401_2_[var6 + 1]);
        float var9 = Float.intBitsToFloat(p_178401_2_[var6 + 2]);
        if (var7 < -0.1F || var7 >= 1.1F)
            var7 -= MathHelper.floor_float(var7); 
        if (var8 < -0.1F || var8 >= 1.1F)
            var8 -= MathHelper.floor_float(var8); 
        if (var9 < -0.1F || var9 >= 1.1F)
            var9 -= MathHelper.floor_float(var9); 
        float var10 = 0.0F;
        float var11 = 0.0F;
        switch (SwitchEnumFacing.field_178400_a[p_178401_3_.ordinal()]) {
            case 1:
                var10 = var7 * 16.0F;
                var11 = (1.0F - var9) * 16.0F;
                break;
            case 2:
                var10 = var7 * 16.0F;
                var11 = var9 * 16.0F;
                break;
            case 3:
                var10 = (1.0F - var7) * 16.0F;
                var11 = (1.0F - var8) * 16.0F;
                break;
            case 4:
                var10 = var7 * 16.0F;
                var11 = (1.0F - var8) * 16.0F;
                break;
            case 5:
                var10 = var9 * 16.0F;
                var11 = (1.0F - var8) * 16.0F;
                break;
            case 6:
                var10 = (1.0F - var9) * 16.0F;
                var11 = (1.0F - var8) * 16.0F;
                break;
        } 
        int var12 = p_178401_4_.func_178345_c(p_178401_1_) * step;
        p_178401_2_[var12 + 4] = Float.floatToRawIntBits(p_178401_5_.getInterpolatedU(var10));
        p_178401_2_[var12 + 4 + 1] = Float.floatToRawIntBits(p_178401_5_.getInterpolatedV(var11));
    }
    
    private static final float field_178418_a = 1.0F / (float)Math.cos(0.39269909262657166D) - 1.0F;
    
    private static final float field_178417_b = 1.0F / (float)Math.cos(0.7853981633974483D) - 1.0F;
    
    private static final String __OBFID = "CL_00002490";
    
    static final class SwitchEnumFacing {
        static final int[] field_178400_a = new int[(EnumFacing.values()).length];
        
        static final int[] field_178399_b = new int[(EnumFacing.Axis.values()).length];
        
        private static final String __OBFID = "CL_00002489";
        
        static {
            try {
                field_178400_a[EnumFacing.DOWN.ordinal()] = 1;
            } catch (NoSuchFieldError noSuchFieldError) {}
            try {
                field_178400_a[EnumFacing.UP.ordinal()] = 2;
            } catch (NoSuchFieldError noSuchFieldError) {}
            try {
                field_178400_a[EnumFacing.NORTH.ordinal()] = 3;
            } catch (NoSuchFieldError noSuchFieldError) {}
            try {
                field_178400_a[EnumFacing.SOUTH.ordinal()] = 4;
            } catch (NoSuchFieldError noSuchFieldError) {}
            try {
                field_178400_a[EnumFacing.WEST.ordinal()] = 5;
            } catch (NoSuchFieldError noSuchFieldError) {}
            try {
                field_178400_a[EnumFacing.EAST.ordinal()] = 6;
            } catch (NoSuchFieldError noSuchFieldError) {}
        }
    }
}

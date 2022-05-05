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

package org.newdawn.slick.particles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;

public class Particle {
    protected static SGL GL = Renderer.get();
    
    public static final int INHERIT_POINTS = 1;
    
    public static final int USE_POINTS = 2;
    
    public static final int USE_QUADS = 3;
    
    protected float x;
    
    protected float y;
    
    protected float velx;
    
    protected float vely;
    
    protected float size = 10.0F;
    
    protected Color color = Color.white;
    
    protected float life;
    
    protected float originalLife;
    
    private ParticleSystem engine;
    
    private ParticleEmitter emitter;
    
    protected Image image;
    
    protected int type;
    
    protected int usePoints = 1;
    
    protected boolean oriented = false;
    
    protected float scaleY = 1.0F;
    
    public Particle(ParticleSystem engine) {
        this.engine = engine;
    }
    
    public float getX() {
        return this.x;
    }
    
    public float getY() {
        return this.y;
    }
    
    public void move(float x, float y) {
        this.x += x;
        this.y += y;
    }
    
    public float getSize() {
        return this.size;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public void setImage(Image image) {
        this.image = image;
    }
    
    public float getOriginalLife() {
        return this.originalLife;
    }
    
    public float getLife() {
        return this.life;
    }
    
    public boolean inUse() {
        return (this.life > 0.0F);
    }
    
    public void render() {
        if ((this.engine.usePoints() && this.usePoints == 1) || this.usePoints == 2) {
            TextureImpl.bindNone();
            GL.glEnable(2832);
            GL.glPointSize(this.size / 2.0F);
            this.color.bind();
            GL.glBegin(0);
            GL.glVertex2f(this.x, this.y);
            GL.glEnd();
        } else if (this.oriented || this.scaleY != 1.0F) {
            GL.glPushMatrix();
            GL.glTranslatef(this.x, this.y, 0.0F);
            if (this.oriented) {
                float angle = (float)(Math.atan2(this.y, this.x) * 180.0D / Math.PI);
                GL.glRotatef(angle, 0.0F, 0.0F, 1.0F);
            } 
            GL.glScalef(1.0F, this.scaleY, 1.0F);
            this.image.draw((int)-(this.size / 2.0F), (int)-(this.size / 2.0F), (int)this.size, (int)this.size, this.color);
            GL.glPopMatrix();
        } else {
            this.color.bind();
            this.image.drawEmbedded((int)(this.x - this.size / 2.0F), (int)(this.y - this.size / 2.0F), (int)this.size, (int)this.size);
        } 
    }
    
    public void update(int delta) {
        this.emitter.updateParticle(this, delta);
        this.life -= delta;
        if (this.life > 0.0F) {
            this.x += delta * this.velx;
            this.y += delta * this.vely;
        } else {
            this.engine.release(this);
        } 
    }
    
    public void init(ParticleEmitter emitter, float life) {
        this.x = 0.0F;
        this.emitter = emitter;
        this.y = 0.0F;
        this.velx = 0.0F;
        this.vely = 0.0F;
        this.size = 10.0F;
        this.type = 0;
        this.originalLife = this.life = life;
        this.oriented = false;
        this.scaleY = 1.0F;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public void setUsePoint(int usePoints) {
        this.usePoints = usePoints;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setSize(float size) {
        this.size = size;
    }
    
    public void adjustSize(float delta) {
        this.size += delta;
        this.size = Math.max(0.0F, this.size);
    }
    
    public void setLife(float life) {
        this.life = life;
    }
    
    public void adjustLife(float delta) {
        this.life += delta;
    }
    
    public void kill() {
        this.life = 1.0F;
    }
    
    public void setColor(float r, float g, float b, float a) {
        if (this.color == Color.white) {
            this.color = new Color(r, g, b, a);
        } else {
            this.color.r = r;
            this.color.g = g;
            this.color.b = b;
            this.color.a = a;
        } 
    }
    
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void setVelocity(float dirx, float diry, float speed) {
        this.velx = dirx * speed;
        this.vely = diry * speed;
    }
    
    public void setSpeed(float speed) {
        float currentSpeed = (float)Math.sqrt((this.velx * this.velx + this.vely * this.vely));
        this.velx *= speed;
        this.vely *= speed;
        this.velx /= currentSpeed;
        this.vely /= currentSpeed;
    }
    
    public void setVelocity(float velx, float vely) {
        setVelocity(velx, vely, 1.0F);
    }
    
    public void adjustPosition(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }
    
    public void adjustColor(float r, float g, float b, float a) {
        if (this.color == Color.white)
            this.color = new Color(1.0F, 1.0F, 1.0F, 1.0F); 
        this.color.r += r;
        this.color.g += g;
        this.color.b += b;
        this.color.a += a;
    }
    
    public void adjustColor(int r, int g, int b, int a) {
        if (this.color == Color.white)
            this.color = new Color(1.0F, 1.0F, 1.0F, 1.0F); 
        this.color.r += r / 255.0F;
        this.color.g += g / 255.0F;
        this.color.b += b / 255.0F;
        this.color.a += a / 255.0F;
    }
    
    public void adjustVelocity(float dx, float dy) {
        this.velx += dx;
        this.vely += dy;
    }
    
    public ParticleEmitter getEmitter() {
        return this.emitter;
    }
    
    public String toString() {
        return super.toString() + " : " + this.life;
    }
    
    public boolean isOriented() {
        return this.oriented;
    }
    
    public void setOriented(boolean oriented) {
        this.oriented = oriented;
    }
    
    public float getScaleY() {
        return this.scaleY;
    }
    
    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }
}

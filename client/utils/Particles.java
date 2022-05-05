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

package client.utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.gui.Gui;

public class Particles {
    private final List<Particle> particles;
    
    private int width;
    
    private int height;
    
    private int count;
    
    public Particles(int width, int height) {
        this.width = width;
        this.height = height;
        this.count = 70;
        this.particles = new ArrayList<>();
        for (int count = 0; count <= this.count; count++)
            this.particles.add(new Particle((new Random()).nextInt(width), (new Random()).nextInt(height))); 
    }
    
    public void drawParticles() {
        this.particles.forEach(particle -> particle.drawParticle());
    }
    
    public class Particle {
        private int xPos;
        
        private int yPos;
        
        public Particle(int xPos, int yPos) {
            this.xPos = xPos;
            this.yPos = yPos;
        }
        
        public void drawParticle() {
            this.xPos++;
            this.yPos++;
            int particleSize = 1;
            if (this.xPos > Particles.this.width)
                this.xPos = -1; 
            if (this.yPos > Particles.this.height)
                this.yPos = -1; 
            Gui.drawRect(this.xPos, this.yPos, this.xPos + 1, this.yPos + 1, Color.RED.getRGB());
        }
    }
}

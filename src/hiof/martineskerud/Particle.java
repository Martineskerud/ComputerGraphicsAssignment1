/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiof.martineskerud;

import java.util.Random;
import javax.media.opengl.GL;
import static javax.media.opengl.GL.GL_BLEND;
import static javax.media.opengl.GL.GL_ONE_MINUS_SRC_ALPHA;
import static javax.media.opengl.GL.GL_SRC_ALPHA;

/**
 *
 * @author martin
 */
public class Particle {

    private float x;
    private float y;
    private float z;

    public Particle(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void draw(GL gl, float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;

        
        //attempt at blending/Transparancy
        gl.glEnable(GL_BLEND);
        gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        gl.glColor4f(0.698f, 0.698f, 0.698f, 0.3f);
        //Gray color
        float particleAmb[] = {0.698f, 0.698f, 0.698f, 0.6f};
        float particleDiff[] = {0.698f, 0.698f, 0.698f, 0.6f};
        float particleSpecular[] = {0.698f, 0.698f, 0.698f, 0.6f};
        Bender.material(gl, particleAmb, particleDiff, particleSpecular);
        Random rand = new Random();
        gl.glPushMatrix();
        gl.glRotatef(rand.nextFloat() * 100, 1f, 1f, 1f);
        gl.glScalef(0.3f, 0.3f, 0.3f);
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3d(0f + x, 0f + y, 0f + z);
        gl.glTexCoord2f(0.1f, 0.0f);
        gl.glVertex3d(0f + x, 0f + y, 0.3f + z);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3d(0.3f + x, 0f + y, 0.3f + z);
        gl.glEnd();
        gl.glPopMatrix();
        float amb[] = {0.498f, 0.498f, 0.498f, 1.0f};
        float diff[] = {0.498f, 0.498f, 0.498f, 1.0f};
        float specular[] = {0.498f, 0.498f, 0.498f, 1.0f};
        Bender.material(gl, amb, diff, specular);
        gl.glDisable(GL_BLEND);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

}

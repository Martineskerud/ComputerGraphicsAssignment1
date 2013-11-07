/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiof.martineskerud;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

/**
 *
 * @author martin
 */
public class Misc {

    public static void Cigar(GL gl) {
        float amb[] = {0.27f, 0.13f, 0f, 0f};
        float diff[] = {0.27f, 0.13f, 0f, 0f};
        float specular[] = {0.27f, 0.13f, 0f, 0f};
        //setting color brown
        Bender.material(gl, amb, diff, specular);
        gl.glPushMatrix();
        gl.glTranslatef(0.2f, 1.9f, 0.45f);
        GLU glu = new GLU();
        GLUquadric gluq = glu.gluNewQuadric();
        gl.glPushMatrix();
        gl.glRotatef(90, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.07f, 0.07f, 0.6f, 20, 20);
        gl.glPopMatrix();
        amb[0] = 0.51f;
        diff[0] = 0.51f;
        specular[0] = 0.51f;
        for (int i = 1; i < 3; i++) {
            amb[i] = 0;
            specular[i] = 0;
            diff[i] = 0;
        }
        //setting color red
        Bender.material(gl, amb, diff, specular);
        gl.glTranslatef(0f, 0f, 0.6f);
        gl.glPushMatrix();
        gl.glRotatef(90, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.07f, 0.07f, 0.1f, 20, 20);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glTranslatef(0f, 0f, 0.11f);
        gl.glScalef(0.07f, 0.07f, 1f);
        //setting the color gray
        for (int i = 0; i < 3; i++) {
            amb[i] = 0.258f;
            specular[i] = 0.258f;
            diff[i] = 0.258f;
        }
        Bender.material(gl, amb, diff, specular);
        
        Geometry.drawCircle2d(gl);

        gl.glPopMatrix();

        //Setting the color yellow/gold
        amb[0] = 0.96f;
        amb[1] = 0.85f;
        amb[2] = 0.10f;
        specular[0] = 0.96f;
        specular[1] = 0.85f;
        specular[2] = 0.10f;
        diff[0] = 0.96f;
        diff[1] = 0.85f;
        diff[2] = 0.10f;
        Bender.material(gl, amb, diff, specular);

        //the cigar-ribbon
        gl.glTranslatef(0f, 0f, -0.5f);
        gl.glPushMatrix();
        gl.glRotatef(90,1f,0f,0f);
        Geometry.cylinder(gl, 0.071f, 0.071f, 0.1f, 20, 20);
        gl.glPopMatrix();
        //setting color back to grey
        for (int i = 0; i < 3; i++) {
            amb[i] = 0.498f;
            specular[i] = 0.498f;
            diff[i] = 0.498f;
        }
        Bender.material(gl, amb, diff, specular);
        gl.glPopMatrix();

    }

}

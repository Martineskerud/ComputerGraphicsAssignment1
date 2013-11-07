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
public class Torso {

    public static void benderTorso(GL gl) {

        GLU glu = new GLU();

        // Draw sphere (possible styles: FILL, LINE, POINT). 
        GLUquadric gluq = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(gluq, GLU.GLU_FILL);
        glu.gluQuadricNormals(gluq, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(gluq, GLU.GLU_OUTSIDE);
        gl.glTranslatef(0f, -0.3f, 0f);
        gl.glPushMatrix();
        //oval top part of torso
        gl.glPushMatrix();
        gl.glScalef(3f, 1f, 3f);
        gl.glTranslatef(0f, 1.59f, 0f);
        glu.gluSphere(gluq, 0.33f, 20, 20);
        gl.glPopMatrix();
        //torso

        gl.glRotatef(90, 1f, 0f, 0f);
        gl.glTranslatef(0f, 0f, -1.6f);
        gl.glRotatef(90, 1f, 0f, 0f);
        Geometry.cylinder(gl, 1f, 0.8f, 2f, 60, 60);
        gl.glPopMatrix();

        //Ass-lid
        gl.glPushMatrix();

        gl.glTranslatef(0f, -0.42f, 0f);
        gl.glScalef(0.8f, 1f, 0.8f);
        gl.glRotatef(90, 1f, 0f, 0f);
        Geometry.drawCircle2d(gl);
        gl.glPopMatrix();

        //front door
        float specular[] = {0f, 0f, 0f, 1.0f};
        float amb[] = {0f, 0f, 0f, 1.0f};
        float diff[] = {0f, 0f, 0f, 1.0f};
        Bender.material(gl, specular, amb, diff);
        //Horizontal lines of "teeth"

        //todo: can this be refactoed to a loop?
        //top
        gl.glPushMatrix();
        //right side of top line
        gl.glTranslatef(0f, 1.5f, 0f);
        torsoLineTopRight(gl);
        gl.glTranslatef(0.1f, 0f, -0.04f);
        torsoLineTopRight(gl);
        gl.glTranslatef(0.1f, 0f, -0.04f);
        torsoLineTopRight(gl);
        gl.glTranslatef(0.1f, 0f, -0.07f);
        torsoLineTopRight(gl);
        gl.glTranslatef(0.1f, 0f, -0.08f);
        torsoLineTopRight(gl);
        gl.glPopMatrix();
        //left side of top side
        gl.glPushMatrix();
        gl.glTranslatef(0f, 1.5f, 0f);
        gl.glTranslatef(-0.1f, 0f, 0.03f);
        torsoLineTopRight(gl);
        gl.glTranslatef(-0.1f, 0f, 0.03f);

        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.25f, -0.25f, 0.9f);
        gl.glVertex3f(0.35f, -0.25f, 0.9f);
        gl.glEnd();
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glTranslatef(0.3f, 1.5f, 2.03f);
        gl.glRotatef(180, 0f, 1f, 0f);
        gl.glBegin(GL.GL_LINES);

        gl.glVertex3f(0.25f, -0.25f, 1.05f);
        gl.glVertex3f(0.35f, -0.25f, 1.05f);
        gl.glEnd();

        //does not show (?)
        gl.glTranslatef(0.3f, 0f, -0.03f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.25f, -0.25f, 1.12f);
        gl.glVertex3f(0.35f, -0.25f, 1.12f);
        gl.glEnd();

        gl.glTranslatef(0.1f, 0f, 0.08f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.25f, -0.25f, 1.08f);
        gl.glVertex3f(0.35f, -0.25f, 1.08f);
        gl.glEnd();

        gl.glTranslatef(0.1f, 0f, 0.08f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.25f, -0.25f, 1.0f);
        gl.glVertex3f(0.35f, -0.25f, 1.08f);
        gl.glEnd();

        gl.glTranslatef(0.1f, 0f, 0.08f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.24f, -0.25f, 1.0f);
        gl.glVertex3f(0.34f, -0.25f, 1.08f);
        gl.glEnd();

        gl.glTranslatef(0.1f, 0f, 0.08f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.24f, -0.25f, 1.0f);
        gl.glVertex3f(0.34f, -0.25f, 1.08f);
        gl.glEnd();

        //middle fudged up part
        gl.glPopMatrix();
        gl.glPushMatrix();

        gl.glTranslatef(-0.5f, 1.5f, -0.05f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.24f, -0.25f, 1.0f);
        gl.glVertex3f(0.44f, -0.25f, 1.03f);
        gl.glEnd();
        gl.glPopMatrix();
        gl.glPushMatrix();
        //left line
        gl.glTranslatef(-0.75f, 1.25f, -0.47f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.15f, -1.4f, 1.05f);
        gl.glVertex3f(0f, -0f, 1.1f);
        gl.glEnd();
        //right line
        gl.glTranslatef(1.5f, 0f, -0f);
        gl.glBegin(GL.GL_LINES);
        //bottom point
        gl.glVertex3f(-0.15f, -1.4f, 1.05f);
        //top point
        gl.glVertex3f(0f, -0f, 1.1f);

        gl.glEnd();
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glTranslatef(0f, -0.05f, 0f);
        //the bottom lines of the front-door. from the right
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.5f, -0.1f, 0.68f);
        gl.glVertex3f(0.6f, -0.1f, 0.58f);
        gl.glEnd();

        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.4f, -0.1f, 0.75f);
        gl.glVertex3f(0.5f, -0.1f, 0.68f);
        gl.glEnd();

        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.3f, -0.1f, 0.78f);
        gl.glVertex3f(0.4f, -0.1f, 0.75f);
        gl.glEnd();

        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.2f, -0.1f, 0.8f);
        gl.glVertex3f(0.3f, -0.1f, 0.8f);
        gl.glEnd();

        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.1f, -0.1f, 0.82f);
        gl.glVertex3f(0.2f, -0.1f, 0.82f);
        gl.glEnd();

        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0f, -0.1f, 0.83f);
        gl.glVertex3f(0.1f, -0.1f, 0.83f);
        gl.glEnd();

        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(-0.1f, -0.1f, 0.85f);
        gl.glVertex3f(0f, -0.1f, 0.85f);
        gl.glEnd();

        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(-0.2f, -0.1f, 0.82f);
        gl.glVertex3f(-0.1f, -0.1f, 0.85f);
        gl.glEnd();

        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(-0.3f, -0.1f, 0.8f);
        gl.glVertex3f(-0.2f, -0.1f, 0.82f);
        gl.glEnd();

        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(-0.4f, -0.1f, 0.77f);
        gl.glVertex3f(-0.3f, -0.1f, 0.8f);
        gl.glEnd();

        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(-0.5f, -0.1f, 0.72f);
        gl.glVertex3f(-0.4f, -0.1f, 0.77f);
        gl.glEnd();

        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(-0.6f, -0.1f, 0.66f);
        gl.glVertex3f(-0.5f, -0.1f, 0.72f);
        gl.glEnd();

        gl.glPopMatrix();
        gl.glPushMatrix();

        float ambTemp[] = {0.6f, 0.6f, 0.6f, 1.0f};
        float diffTemp[] = {0.6f, 0.6f, 0.6f, 1.0f};
        float specularTemp[] = {0.6f, 0.6f, 0.6f, 1.0f};
        Bender.material(gl, specularTemp, ambTemp, diffTemp);
        //the door-knob
        gl.glTranslatef(0.5f, 0.55f, 0.69f);
        glu.gluSphere(gluq, 0.1f, 20, 20);

        gl.glPopMatrix();

        float ambTemp2[] = {0.498f, 0.498f, 0.498f, 1.0f};
        float diffTemp2[] = {0.498f, 0.498f, 0.498f, 1.0f};
        float specularTemp2[] = {0.498f, 0.498f, 0.498f, 1.0f};
        Bender.material(gl, specularTemp2, ambTemp2, diffTemp2);

        /*cylinder*/
    }

    private static void torsoLineTopRight(GL gl) {
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.25f, -0.25f, 0.95f);
        gl.glVertex3f(0.35f, -0.25f, 0.9f);
        gl.glEnd();
    }

    private static void torsoLineTopLeft(GL gl) {
        gl.glBegin(GL.GL_LINES);
        gl.glVertex3f(0.25f, -0.25f, 0.9f);
        gl.glVertex3f(0.35f, -0.25f, 0.95f);
        gl.glEnd();
    }
}

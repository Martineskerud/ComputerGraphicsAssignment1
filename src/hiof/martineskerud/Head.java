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
public class Head {

    public static void benderhead(GL gl) {

        
        GLU glu = new GLU();
        //head relative to y=4

        gl.glTranslatef(0f, 4f, 0);
        gl.glPushMatrix();

        // Draw sphere (possible styles: FILL, LINE, POINT). 
        GLUquadric gluq = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(gluq, GLU.GLU_FILL);
        glu.gluQuadricNormals(gluq, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(gluq, GLU.GLU_OUTSIDE);

        //antenna top
        glu.gluSphere(gluq, 0.1f, 20, 20);
        gl.glRotatef(90, 1f, 0f, 0f);

        //antenna
        gl.glRotatef(90, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.03f, 0.03f, 1f, 80, 80);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glTranslatef(0f, -1.3f, 0f);

        //top part of head
        glu.gluSphere(gluq, 0.5f, 30, 30);
        gl.glPushMatrix();
        gl.glPopMatrix();
        gl.glRotatef(90, 1f, 0f, 0f);

        //head main
        gl.glRotatef(90, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.5f, 0.5f, 1.2f, 20, 20);
        gl.glPopMatrix();
        gl.glPushMatrix();

        //headmount for antenna
        gl.glTranslatef(0f, -0.81f, 0f);
        gl.glScalef(3f, 1f, 3f);
        glu.gluSphere(gluq, 0.057f, 20, 20);
        gl.glPopMatrix();

        //ZEH GOOGLES
        //Left side
        gl.glPushMatrix();
        gl.glTranslatef(-0.45f, -1.5f, -0.01f);
        gl.glRotatef(90, 0f, 0f, 1f);
        Geometry.halfCylinder(gl, 0.2f, 0.2f, 0.8f, 20, 20);
        gl.glPopMatrix();

        // Right side
        gl.glPushMatrix();
        gl.glTranslatef(0.45f, -1.5f, -0.01f);
        gl.glRotatef(-90, 0f, 0f, 1f);
        gl.glNormal3f(0.0f, 0.0f, 1.0f);
        Geometry.halfCylinder(gl, 0.2f, 0.2f, 0.8f, 20, 20);
        gl.glPopMatrix();

        //Middle Part:
        //#TOP
        gl.glPushMatrix();
        gl.glTranslatef(0f, 0.2f, 0f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glNormal3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(-0.45f, -1.5f, 0.0f);
        gl.glVertex3f(-0.45f, -1.5f, 0.8f);
        gl.glVertex3f(0.45f, -1.5f, 0.8f);
        gl.glVertex3f(0.45f, -1.5f, 0.0f);
        gl.glEnd();
        gl.glPopMatrix();

        //#BOTTOM
        gl.glPushMatrix();
        gl.glTranslatef(0f, -0.2f, 0f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glNormal3f(0f, -1f, 0.0f);
        gl.glVertex3f(-0.45f, -1.5f, 0.0f);
        gl.glVertex3f(-0.45f, -1.5f, 0.8f);
        gl.glVertex3f(0.45f, -1.5f, 0.8f);
        gl.glVertex3f(0.45f, -1.5f, 0.0f);
        gl.glEnd();
        gl.glPopMatrix();

        //Coverups for Benders back head
        //Left
        //TODO: drawCircle2D Needs arguements, remove scale to compensate.
        gl.glPushMatrix();
        gl.glTranslatef(0.47f, -1.5f, 0f);
        gl.glScalef(0.195f, 0.195f, 0f);
        Geometry.drawCircle2d(gl);
        gl.glPopMatrix();
        //right
        gl.glPushMatrix();
        gl.glTranslatef(-0.47f, -1.5f, 0f);
        gl.glScalef(0.195f, 0.195f, 0f);
        Geometry.drawCircle2d(gl);

        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(0f, -0.2f, 0f);
        gl.glEnable(GL.GL_TEXTURE_2D);
        if (Bender.eyes != null) {
            Bender.eyes.enable();
            Bender.eyes.setTexParameteri(GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        }

        gl.glBegin(GL.GL_POLYGON);
        gl.glNormal3f(-1f, -1.5f, 0.0f);

        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-0.47f, -1.1f, 0.801f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(0.47f, -1.1f, 0.801f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(0.47f, -1.5f, 0.801f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-0.47f, -1.5f, 0.801f);

        gl.glEnd();
        Bender.eyes.disable();
        gl.glDisable(GL.GL_TEXTURE_2D);
        gl.glPopMatrix();

        //Coverups for Benders eye-socket-thingy.
        //Left
        //TODO: drawCircle2D Needs arguements, remove scale to compensate.
        gl.glPushMatrix();
        gl.glTranslatef(0.47f, -1.5f, 0.8f);
        gl.glScalef(0.195f, 0.195f, 0f);
        if (Bender.eye_cover != null) {
            Bender.eye_cover.enable();
            Bender.eye_cover.setTexParameteri(GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        }
        Geometry.drawCircle2d(gl);
        gl.glPopMatrix();

        //right
        gl.glPushMatrix();
        gl.glTranslatef(-0.47f, -1.5f, 0.8f);
        gl.glScalef(0.195f, 0.195f, 0f);

        Geometry.drawCircle2d(gl);
        Bender.eyes.disable();
        gl.glDisable(GL.GL_TEXTURE_2D);
        gl.glPopMatrix();

        glu.gluDeleteQuadric(gluq);

        //Mouth
        gl.glPushMatrix();
        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glTranslatef(0f, -1.9f, 0.01f);
        gl.glRotatef(90, 1f, 0f, 0f);
        if (Bender.mouth != null) {
            Bender.mouth.enable();
            Bender.mouth.bind();
        }

        Geometry.halfCylinder(gl, 0.5f, 0.5f, 0.4f, 20, 20);

        Bender.mouth.disable();
        gl.glDisable(GL.GL_TEXTURE_2D);

        gl.glPopMatrix();
        gl.glPushMatrix();
        //lines for mouth 
        /* This is a hack to complete the texture for the mouth which is not showing correctly.*/
        float specular[] = {0f, 0f, 0f, 1.0f};
        float amb[] = {0f, 0f, 0f, 1.0f};
        float diff[] = {0f, 0f, 0f, 1.0f};

        //Horizontal lines of "teeth"
        Bender.material(gl, specular, amb, diff);
        gl.glTranslatef(0f, -2f, 0.02f);
        gl.glRotatef(90, 1f, 0f, 0f);
        // Geometry.halfCylinder(gl, 0.5f, 0.5f, 0.02f, 20, 5);
        gl.glPopMatrix();
        gl.glPushMatrix();
    
        gl.glPopMatrix();
        float ambTemp[] = {0.498f, 0.498f, 0.498f, 1.0f};
        float diffTemp[] = {0.498f, 0.498f, 0.498f, 1.0f};
        float specularTemp[] = {0.498f, 0.498f, 0.498f, 1.0f};
        Bender.material(gl, specularTemp, ambTemp, diffTemp);
    }
}

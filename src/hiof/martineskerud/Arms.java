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
public class Arms {

    public static float curLeftArm = 0f;
    public static float curRightArm = 0f;
    public static boolean movingRightArm = false;
    public static float[] rightArmJointArray;

    public static void rightArm(GL gl) {

        GLU glu = new GLU();
        GLUquadric gluq = glu.gluNewQuadric();
        //arm-torso attachment
        gl.glPushMatrix();
        gl.glTranslatef(0.9f, 1f, 0f);
        gl.glRotatef(90, 0f, 1f, 0f);
        Geometry.drawTorus(gl, 0.15f, 0.25f, 20, 20);
        gl.glPopMatrix();
        //forearm cylinders

        
//TODO Refactor this shit into a loop
        //#CYLINDER 1
        gl.glPushMatrix();
        gl.glTranslatef(0.95f, 1f, 0f);
        gl.glRotatef(180, 1f, 0f, 0f);
        //gl.glRotatef(90, 0f, 1f, 0f);
        gl.glRotatef(-45, 0f, 0f, 1f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#CYLINDER 2
        gl.glPushMatrix();
        gl.glTranslatef(1.2f, 0.75f, 0f);
        gl.glRotatef(180, 1f, 0f, 0f);
        gl.glRotatef(-35, 0f, 0f, 1f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#CYLINDER 3
        gl.glPushMatrix();
        gl.glTranslatef(1.4f, 0.45f, 0f);
        gl.glRotatef(180, 1f, 0f, 0f);
        gl.glRotatef(-25, 0f, 0f, 1f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#CYLINDER 4
        gl.glPushMatrix();
        gl.glTranslatef(1.55f, 0.1f, 0f);
        gl.glRotatef(180, 1f, 0f, 0f);
        gl.glRotatef(-10, 0f, 0f, 1f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#CYLINDER 5
        gl.glPushMatrix();
        gl.glTranslatef(1.6f, -0.25f, 0f);
        gl.glRotatef(180, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#CYLINDER 6
        gl.glPushMatrix();
        gl.glTranslatef(1.6f, -0.9f, 0f);
        //      gl.glRotatef(90, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#WRIST
        gl.glPushMatrix();
        gl.glTranslatef(1.6f, -0.8f, 0f);
        gl.glRotatef(180, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.15f, 0.25f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#FINGER 1
        gl.glPushMatrix();
        gl.glTranslatef(1.63f, -1f, 0f);
        gl.glRotatef(180, 1f, 0f, 0f);
        gl.glRotatef(30, 0f, 1f, 0f);
        Geometry.cylinder(gl, 0.05f, 0.05f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#FINGER 2
        gl.glPushMatrix();
        gl.glTranslatef(1.47f, -1.3f, 0f);
        gl.glRotatef(-50, 0f, 0f, 1f);
        gl.glRotatef(10, 0f, 0f, 1f);
        gl.glRotatef(-15, 1f, 1f, 0f);
        Geometry.cylinder(gl, 0.05f, 0.05f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#FINGER 3
        gl.glPushMatrix();
        gl.glTranslatef(1.6f, -1f, 0f);
        gl.glRotatef(90, 1f, 0f, 0f);
        gl.glRotatef(-30, 0f, 1f, 0f);
        gl.glRotatef(35, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.05f, 0.05f, 0.3f, 20, 20);
        gl.glPopMatrix();

    }

    public static void leftArm(GL gl) {
        GLU glu = new GLU();
        GLUquadric gluq = glu.gluNewQuadric();
        //Arm-Torso attachment
        gl.glPushMatrix();
        gl.glTranslatef(-1f, 1f, 0f);
        gl.glRotatef(90, 0f, 1f, 0f);
        gl.glTranslatef(0.0f, 0f, 0.1f);
        Geometry.drawTorus(gl, 0.15f, 0.25f, 20, 20);
        gl.glPopMatrix();

        //TODO: Refactor this mess into a loop (is that even possible?)
        //#CYLINDER 1
        gl.glPushMatrix();
        gl.glTranslatef(-1.15f, 0.9f, 0f);
        gl.glRotatef(180, 0f, 1f, 0f);
        gl.glRotatef(45, 0f, 0f, 1f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#CYLINDER 2
        gl.glPushMatrix();
        gl.glTranslatef(-1.35f, 0.6f, 0f);
        gl.glRotatef(180, 0f, 1f, 0f);
        gl.glRotatef(35, 0f, 0f, 1f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#CYLINDER 3
        gl.glPushMatrix();
        gl.glTranslatef(-1.5f, 0.25f, 0f);
        gl.glRotatef(180, 0f, 1f, 0f);
        gl.glRotatef(25, 0f, 0f, 1f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#CYLINDER 4
        gl.glPushMatrix();
        gl.glTranslatef(-1.6f, -0.1f, 0f);
        gl.glRotatef(180, 0f, 1f, 0f);
        gl.glRotatef(15, 0f, 0f, 1f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#CYLINDER 5
        gl.glPushMatrix();
        gl.glTranslatef(-1.63f, -0.45f, 0f);
        gl.glRotatef(180, 0f, 1f, 0f);
        gl.glRotatef(5, 0f, 0f, 1f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#CYLINDER 6
        gl.glPushMatrix();
        gl.glTranslatef(-1.63f, -0.8f, 0f);
        gl.glRotatef(180, 0f, 1f, 0f);
        gl.glRotatef(-5, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#WRIST
        gl.glPushMatrix();
        gl.glTranslatef(-1.63f, -0.8f, 0f);
        gl.glRotatef(180, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.15f, 0.25f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#FINGER 1
        gl.glPushMatrix();
        gl.glTranslatef(-1.53f, -1f, 0f);
        gl.glRotatef(180, 1f, 0f, 0f);
        gl.glRotatef(30, 0f, 1f, 0f);
        Geometry.cylinder(gl, 0.05f, 0.05f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#FINGER 2
        gl.glPushMatrix();
        gl.glTranslatef(-1.57f, -1f, 0f);
        gl.glRotatef(180, 1f, 0f, 0f);
        gl.glRotatef(-30, 0f, 1f, 0f);
        gl.glRotatef(10, 0f, 0f, 1f);
        gl.glRotatef(-15, 1f, 1f, 0f);
        Geometry.cylinder(gl, 0.05f, 0.05f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#FINGER 3
        gl.glPushMatrix();
        gl.glTranslatef(-1.6f, -1f, 0f);
        gl.glRotatef(180, 1f, 0f, 0f);
        gl.glRotatef(-30, 0f, 1f, 0f);
        gl.glRotatef(35, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.05f, 0.05f, 0.3f, 20, 20);
        gl.glPopMatrix();

    }

    public static void moveRightArm(GL gl) {

        GLU glu = new GLU();
        GLUquadric gluq = glu.gluNewQuadric();
        //arm-torso attachment
        gl.glPushMatrix();
        gl.glTranslatef(0.9f, 1f, 0f);
        gl.glRotatef(90, 0f, 1f, 0f);
        Geometry.drawTorus(gl, 0.15f, 0.25f, 20, 20);
        gl.glPopMatrix();
        //forearm cylinders

        
//TODO Refactor this shit into a loop
        //#CYLINDER 1
        gl.glPushMatrix();
        gl.glTranslatef(0.95f, 1f, 0f);
        gl.glRotatef(180, 1f, 0f, 0f);
        //gl.glRotatef(90, 0f, 1f, 0f);
        gl.glRotatef(-45, 0f, 0f, 1f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#CYLINDER 2
        gl.glPushMatrix();
        gl.glTranslatef(1.2f, 0.75f, 0f);
        gl.glRotatef(180, 1f, 0f, 0f);
        gl.glRotatef(-35, 0f, 0f, 1f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#CYLINDER 3
        gl.glPushMatrix();
        gl.glTranslatef(1.4f, 0.45f, 0f);
        gl.glRotatef(180, 1f, 0f, 0f);
        gl.glRotatef(-25, 0f, 0f, 1f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#CYLINDER 4
        gl.glPushMatrix();
        gl.glTranslatef(1.55f, 0.1f, 0f);
        gl.glRotatef(180, 1f, 0f, 0f);
        gl.glRotatef(-10, 0f, 0f, 1f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#CYLINDER 5
        gl.glPushMatrix();
        gl.glTranslatef(1.6f, -0.25f, 0f);
        gl.glRotatef(180, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#CYLINDER 6
        gl.glPushMatrix();
        gl.glTranslatef(1.6f, -0.9f, 0f);
        //      gl.glRotatef(90, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#WRIST
        gl.glPushMatrix();
        gl.glTranslatef(1.6f, -0.8f, 0f);
        gl.glRotatef(180, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.15f, 0.25f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#FINGER 1
        gl.glPushMatrix();
        gl.glTranslatef(1.63f, -1f, 0f);
        gl.glRotatef(180, 1f, 0f, 0f);
        gl.glRotatef(30, 0f, 1f, 0f);
        Geometry.cylinder(gl, 0.05f, 0.05f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#FINGER 2
        gl.glPushMatrix();
        gl.glTranslatef(1.47f, -1.3f, 0f);
        gl.glRotatef(-50, 0f, 0f, 1f);
        gl.glRotatef(10, 0f, 0f, 1f);
        gl.glRotatef(-15, 1f, 1f, 0f);
        Geometry.cylinder(gl, 0.05f, 0.05f, 0.3f, 20, 20);
        gl.glPopMatrix();

        //#FINGER 3
        gl.glPushMatrix();
        gl.glTranslatef(1.6f, -1f, 0f);
        gl.glRotatef(90, 1f, 0f, 0f);
        gl.glRotatef(-30, 0f, 1f, 0f);
        gl.glRotatef(35, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.05f, 0.05f, 0.3f, 20, 20);
        gl.glPopMatrix();

    }
}

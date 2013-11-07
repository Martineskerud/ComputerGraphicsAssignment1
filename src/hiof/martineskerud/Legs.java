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
public class Legs {

    public static float curLeftLeg = 0f;
    public static float curRightLeg = 0f;
    public static boolean decendingLegRight = false;
    public static boolean decendingLegLeft = false;

    public static void rightLeg(GL gl) {

        GLU glu = new GLU();
        GLUquadric gluq = glu.gluNewQuadric();
        //  moveRightLeg();

        //Leg
        /*This whole block is a result of adapting Lars' cylinder and is messy, dumb and bad.*/
        gl.glPushMatrix();
        gl.glTranslatef(0.5f, -0.7f, 0f);
        gl.glRotatef(90, 1f, 0f, 0f);
        gl.glRotatef(curRightLeg, 1f, 0f, 0f);
        gl.glPushMatrix();
        gl.glRotatef(90, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();
        gl.glTranslatef(0f, 0f, 0.35f);
        gl.glPushMatrix();
        gl.glRotatef(90, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();
        gl.glTranslatef(0f, 0f, 0.35f);
        gl.glPushMatrix();
        gl.glRotatef(90, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();
        gl.glTranslatef(0f, 0f, 0.35f);
        gl.glPushMatrix();
        gl.glRotatef(90, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();
        gl.glRotatef(-90, 1f, 0f, 0f);
        //Foot
        gl.glTranslatef(0f, -0.7f, 0f);
        Geometry.halfSphere(gl, 0.5f, 20, 20);

        //Shoesole
        gl.glTranslatef(0f, -0.03f, 0f);
        gl.glScalef(0.5f, 1f, 0.5f);
        gl.glRotatef(90, 1f, 0f, 0f);
        Geometry.drawCircle2d(gl);
        gl.glPopMatrix();

    }

    public static void leftLeg(GL gl) {
        // moveLeftLeg();

        GLU glu = new GLU();
        GLUquadric gluq = glu.gluNewQuadric();
        gl.glPushMatrix();
        gl.glTranslatef(-0.5f, -0.7f, 0f);
        gl.glRotatef(90, 1f, 0f, 0);
        gl.glRotatef(curLeftLeg, 1f, 0f, 0f);
        /*This whole leg is messed up after trying to adopt away from glucylinder.
         There is definitly an easier way of doing this.*/
        gl.glPushMatrix();
        gl.glRotatef(90, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();
        gl.glTranslatef(0f, 0f, 0.35f);
        gl.glPushMatrix();
        gl.glRotatef(90, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();
        gl.glTranslatef(0f, 0f, 0.35f);
        gl.glPushMatrix();
        gl.glRotatef(90, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();
        gl.glTranslatef(0f, 0f, 0.35f);
        gl.glPushMatrix();
        gl.glRotatef(90, 1f, 0f, 0f);
        Geometry.cylinder(gl, 0.15f, 0.15f, 0.3f, 20, 20);
        gl.glPopMatrix();
        gl.glRotatef(-90, 1f, 0f, 0);

        //foot
        //Create plane and sphere 
        gl.glTranslatef(0f, -0.7f, 0f);
        Geometry.halfSphere(gl, 0.5f, 20, 20);

        //Shoesole
        gl.glTranslatef(0f, -0.03f, 0f);
        gl.glScalef(0.5f, 1f, 0.5f);
        gl.glRotatef(90, 1f, 0f, 0f);
        Geometry.drawCircle2d(gl);
        gl.glPopMatrix();

    }

    public static void run(GL gl) {

        for (int i = 0; i < 360; i++) {
            float temp = (float) i;
        }

    }

    public static void moveLegs(boolean movementForward) {
        //Movement

        if (curLeftLeg < 45 && decendingLegLeft == true) {
            curLeftLeg += 1.3f;
            if (curLeftLeg > 45) {
                decendingLegLeft = false;
            }

        } else {
            curLeftLeg -= 1.3f;
            if (curLeftLeg < -45) {
                decendingLegLeft = true;
            }

        }

        if (curRightLeg < 45 && decendingLegRight == false) {
            curRightLeg += 1.3f;
            if (curRightLeg > 45) {
                decendingLegRight = true;
            }

        } else {
            curRightLeg -= 1.3f;
            if (curRightLeg < -45) {
                decendingLegRight = false;
            }
        }

    }

    public static void rotateBenderRight(GL gl, float deg) {
        gl.glRotatef(Bender.benderRotation, 0f, 1f, 0f);
        if (Bender.benderRotation == 360) {
            Bender.benderRotation = 0;
        }

        Bender.benderRotation += 1;
        System.out.println(Bender.benderRotation);
    }

    public static void rotateBenderLeft(GL gl, float deg) {
        gl.glRotatef(Bender.benderRotation, 0f, 1f, 0f);
        if (Bender.benderRotation == 360 || Bender.benderRotation == -360) {
            Bender.benderRotation = 0;
        }

        Bender.benderRotation -= 1;
        System.out.println(Bender.benderRotation);
    }

    public static void rotateBenderStop(GL gl, float deg) {
        gl.glRotatef(deg, 0f, 1f, 0f);
    }
}

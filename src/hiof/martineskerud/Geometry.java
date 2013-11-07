/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiof.martineskerud;

import javax.media.opengl.GL;
import static javax.media.opengl.GL.GL_TRIANGLE_FAN;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

/**
 *
 * @author martin
 */
public class Geometry {

    /*Class contains different custom geometric figures.*/
    public static void halfSphere(GL gl, float rad, int slices, int stacks) {
        GLU glu = new GLU();
        GLUquadric q = glu.gluNewQuadric();
        //Create plane and sphere 
        double[] cutplane = new double[]{0.0, 1.0, 0.1, 0.0};
        //Define clip plane 
        gl.glClipPlane(GL.GL_CLIP_PLANE2, cutplane, 0);
        //Enable clip plane 
        gl.glEnable(GL.GL_CLIP_PLANE2);
        //Create sphere 
        glu.gluSphere(q, rad, slices, stacks);
        //Delete the part we don't want 
        glu.gluDeleteQuadric(q);
        //Since were finished, disable the plane 
        gl.glDisable(GL.GL_CLIP_PLANE2);
    }

    public static void halfCylinder(GL gl, float rad, float rad2, float height, int slices, int stacks) {
        GLU glu = new GLU();
        GLUquadric q = glu.gluNewQuadric();
        gl.glPushMatrix();
        gl.glRotatef(90, 1f, 0f, 0f);
        //Create plane and sphere 
        double[] cutplane = new double[]{0.0, 1.0, 0.1, 0.0};
        //Define clip plane 
        gl.glClipPlane(GL.GL_CLIP_PLANE2, cutplane, 0);

        //Enable clip plane 
        gl.glEnable(GL.GL_CLIP_PLANE2);
        //Create sphere 
        Geometry.cylinder(gl, rad, rad2, height, slices, stacks);
        //Delete the part we don't want 
        glu.gluDeleteQuadric(q);
        //Since were finished, disable the plane 
        gl.glDisable(GL.GL_CLIP_PLANE2);
        gl.glPopMatrix();
    }

    static void drawTorus(GL gl, float r, float R, int nsides, int rings) {
        float ringDelta = 2.0f * (float) Math.PI / rings;
        float sideDelta = 2.0f * (float) Math.PI / nsides;
        float theta = 0.0f, cosTheta = 1.0f, sinTheta = 0.0f;
        for (int i = rings - 1; i >= 0; i--) {
            float theta1 = theta + ringDelta;
            float cosTheta1 = (float) Math.cos(theta1);
            float sinTheta1 = (float) Math.sin(theta1);
            gl.glBegin(GL.GL_QUAD_STRIP);
            float phi = 0.0f;
            for (int j = nsides; j >= 0; j--) {
                phi += sideDelta;
                float cosPhi = (float) Math.cos(phi);
                float sinPhi = (float) Math.sin(phi);
                float dist = R + r * cosPhi;
                gl.glNormal3f(cosTheta1 * cosPhi, -sinTheta1 * cosPhi, sinPhi);
                gl.glVertex3f(cosTheta1 * dist, -sinTheta1 * dist, r * sinPhi);
                gl.glNormal3f(cosTheta * cosPhi, -sinTheta * cosPhi, sinPhi);
                gl.glVertex3f(cosTheta * dist, -sinTheta * dist, r * sinPhi);
            }
            gl.glEnd();
            theta = theta1;
            cosTheta = cosTheta1;
            sinTheta = sinTheta1;
        }

    }

    public static void cylinder(GL gl, double rb, double rt, double h, int n1, int n2) {

        double pi = Math.PI;
        double dv = 2 * pi / n1;
        double dh = h / n2;

        for (int i = 0; i < n2; i++) {

            gl.glBegin(GL.GL_TRIANGLE_STRIP);
            for (double v = 0; v < 2 * pi + dv; v += dv) {

                double r1 = rb * (1 - ((dh * i) / h)) + rt * ((dh * i) / h);
                double r2 = rb * (1 - ((dh * (i + 1)) / h)) + rt * ((dh * (i + 1)) / h);
                double na = Math.atan((r1 - r2) / dh);

                gl.glNormal3d(Math.cos(v) * Math.cos(na), Math.sin(na), Math.sin(v) * Math.cos(na));
                gl.glTexCoord2d(v / (2 * pi), (double) i / n2);
                gl.glVertex3d(r1 * Math.cos(v), dh * i, r1 * Math.sin(v));
                gl.glTexCoord2d(v / (2 * pi), (double) (i + 1) / n2);

                gl.glVertex3d(r2 * Math.cos(v), dh * (i + 1), r2 * Math.sin(v));

            }
            gl.glEnd();

        }

        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glNormal3d(0.0, 1.0, 0.0);
        gl.glVertex3d(0.0, h, 0.0);
        for (double v = 0; v < 2 * pi + dv; v += dv) {
            gl.glVertex3d(rt * Math.cos(v), h, rt * Math.sin(v));
        }
        gl.glEnd();

    }

    static void drawCircle2d(GL gl) {

        gl.glBegin(GL_TRIANGLE_FAN);
        gl.glColor3f(0.0f, 0.0f, 1.0f);  // Blue
        gl.glVertex2f(0.0f, 0.0f);       // Center of circle
        int numSegments = 20;

        for (int i = 0; i <= numSegments; i++) { // Last vertex same as first vertex
            float angle;
            angle = (float) (Math.PI * i * 2.0f / numSegments);  // 360 deg for all segments
            float cos = (float) Math.cos(angle);
            float sin = (float) Math.sin(angle);
            gl.glVertex2f(1.0f * cos, sin * 1.0f);

        }
        gl.glEnd();
    }

    //not used for bender, only for testing purposes.
    public static void superEllipsoid(GL gl, double rx, double ry, double rz, int n,
            double e1, double e2) {

        double pi = Math.PI;
        double dv = 2 * pi / n;
        double dw = pi / (n / 2);

        double exp1 = 2.0 / e1;
        double exp2 = 2.0 / e2;

        for (double v = 0; v < 2 * pi; v += dv) {
            gl.glBegin(GL.GL_TRIANGLE_STRIP);

            gl.glVertex3d(0.0, 0.0, rz);

            for (double w = dw; w < pi; w += dw) {

                double cosv = Math.cos(v);
                double cosw = Math.cos(w);
                double cosvdv = Math.cos(v + dv);
                double sinv = Math.sin(v);
                double sinw = Math.sin(w);
                double sinvdv = Math.sin(v + dv);

                double acosv = Math.abs(cosv);
                double acosw = Math.abs(cosw);
                double acosvdv = Math.abs(cosvdv);
                double asinv = Math.abs(sinv);
                double asinw = Math.abs(sinw);
                double asinvdv = Math.abs(sinvdv);

                double scosv = Math.signum(cosv);
                double scosw = Math.signum(cosw);
                double scosvdv = Math.signum(cosvdv);
                double ssinv = Math.signum(sinv);
                double ssinw = Math.signum(sinw);
                double ssinvdv = Math.signum(sinvdv);

                gl.glNormal3d((1 / rx) * scosv * Math.pow(acosv, 2.0 - exp1) * ssinw * Math.pow(asinw, 2.0 - exp2),
                        (1 / ry) * ssinv * Math.pow(asinv, 2.0 - exp1) * ssinw * Math.pow(asinw, 2.0 - exp2),
                        (1 / rz) * scosw * Math.pow(acosw, 2.0 - exp2));
                gl.glVertex3d(rx * scosv * Math.pow(acosv, exp1) * ssinw * Math.pow(asinw, exp2),
                        ry * ssinv * Math.pow(asinv, exp1) * ssinw * Math.pow(asinw, exp2),
                        rz * scosw * Math.pow(acosw, exp2));

                gl.glNormal3d((1 / rx) * scosvdv * Math.pow(acosvdv, 2.0 - exp1) * ssinw * Math.pow(asinw, 2.0 - exp2),
                        (1 / ry) * ssinvdv * Math.pow(asinvdv, 2.0 - exp1) * ssinw * Math.pow(asinw, 2.0 - exp2),
                        (1 / rz) * scosw * Math.pow(acosw, 2.0 - exp2));
                gl.glVertex3d(rx * scosvdv * Math.pow(acosvdv, exp1) * ssinw * Math.pow(asinw, exp2),
                        ry * ssinvdv * Math.pow(asinvdv, exp1) * ssinw * Math.pow(asinw, exp2),
                        rz * scosw * Math.pow(acosw, exp2));

            }

            gl.glVertex3d(0.0, 0.0, -rz);

            gl.glEnd();
        }
    }

}

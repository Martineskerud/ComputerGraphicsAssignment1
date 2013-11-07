/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiof.martineskerud;

import javax.media.opengl.GL;

/**
 *
 * @author martin
 */
public class World {

    public static void floor(GL gl) {
        float amb[] = {0.1f, 0.2f, 0.1f, 1f};
        float specular[] = {0f, 0.2f, 0.1f, 1f};
        float diff[] = {0.1f, 0.2f, 0.1f, 1f};

        Bender.material(gl, amb, diff, specular);

        gl.glPushMatrix();
        gl.glTranslatef(0f, -1f, 0f);
        gl.glBegin(GL.GL_POLYGON);
        gl.glNormal3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(-10f, -1.5f, -10f);
        gl.glVertex3f(-10f, -1.5f, 10f);
        gl.glVertex3f(10f, -1.5f, 10f);
        gl.glVertex3f(10f, -1.5f, -10.0f);
        gl.glEnd();
        gl.glPopMatrix();

        for (int i = 0; i < 3; i++) {
            amb[i] = 0.498f;
            specular[i] = 0.498f;
            diff[i] = 0.498f;
        }
        Bender.material(gl, amb, diff, specular);

    }

}

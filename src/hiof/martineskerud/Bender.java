package hiof.martineskerud;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class Bender implements GLEventListener, MouseListener,
        MouseMotionListener, KeyListener {

    private float view_rotx = 20.0f;
    private float view_roty = 30.0f;
    // remember last mouse position
    private int oldMouseX;
    private int oldMouseY;
    public static float xrotate = 0;

    public static Texture mouth = null;
    public static Texture eyes = null;
    public static Texture eye_cover = null;
    public static boolean rightArrowPressed = false;
    public static boolean leftArrowPressed = false;
    public static boolean fPressed = false;
    public static boolean gPressed = false;
    private LinkedList<Particle> particleList = new LinkedList<Particle>();
    public static float benderRotation = 0;

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        oldMouseX = e.getX();
        oldMouseY = e.getY();
    }

    public static void main(String[] args) {
        Frame frame = new Frame("Bender");
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new Bender());
        frame.add(canvas);
        frame.setSize(640, 480);

        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();

            }
        });

        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);

        animator.start();

    }

    public void init(GLAutoDrawable drawable) {
        drawable.addMouseListener(this);
        drawable.addMouseMotionListener(this);
        drawable.addKeyListener(this);

        GL gl = drawable.getGL();
        GLU glu = new GLU();
        GLUquadric gluq = glu.gluNewQuadric();

        Random rand = new Random();
        for (int i = 0; i < 15; i++) {
            Particle partic = new Particle(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
            particleList.push(partic);
        }

        // Set backgroundcolor and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_FLAT);
        // give me some light
        float ambient[] = {0.4f, 0.4f, 0.4f, 1.0f};
        float diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float position[] = {1f, 1f, 1.0f, 0f};

        //float emmition[] = {0.3f, 0.5f, 1f, 1f};
        glu.gluQuadricTexture(gluq, true);
        // gl.glLightfv(GL.GL_LIGHT0, GL.GL_EMISSION, emmition, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ambient, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, diffuse, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, position, 0);

        // and some green material
        float specular[] = {0.2f, 1.0f, 0.2f, 1.0f};
        float amb[] = {0.7f, 0.7f, 0.7f, 1.0f};
        float diff[] = {0.7f, 0.7f, 0.7f, 1.0f};
        material(gl, amb, diff, specular);

        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL.GL_NORMALIZE);
        // addKeyboardListener();
        try {

            mouth = TextureIO.newTexture(new File("mouth.png"), false);
            eyes = TextureIO.newTexture(new File("eyes.png"), false);
            eye_cover = TextureIO.newTexture(new File("eye_cover_side.png"), false);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

    }

    public void reshape(GLAutoDrawable drawable,
            int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        if (height <= 0) // no divide by zero
        {
            height = 1;
        }
        // keep ratio
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        GLU glu = new GLU(); // needed for lookat
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        glu.gluLookAt(0, 0, 10, // eye pos
                0, 0, 0, // look at
                0, 1, 0);  // up

        gl.glTranslatef(0.5f, 0.5f, 0.5f);
        gl.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);
        gl.glTranslatef(-0.5f, -0.5f, -0.5f);

        float amb[] = {0.498f, 0.498f, 0.498f, 1.0f};
        float diff[] = {0.498f, 0.498f, 0.498f, 1.0f};
        float specular[] = {0.498f, 0.498f, 0.498f, 1.0f};
        material(gl, amb, diff, specular);

        /*hack/ trying to center things*/ gl.glTranslatef(0f, -1f, 0f);
        /*hack*/ gl.glPushMatrix();
        World.floor(gl);
        if (rightArrowPressed == true) {
            Legs.rotateBenderRight(gl, benderRotation);
            System.out.println("Rotating by: " + benderRotation + "to the right");
        }
        if (leftArrowPressed == true) {
            Legs.rotateBenderLeft(gl, benderRotation);
            System.out.println("Rotating by: " + benderRotation + "to the left");
        }
        Legs.rotateBenderStop(gl, benderRotation);
        System.out.println(benderRotation);
        gl.glPushMatrix();

        gl.glPopMatrix();
        gl.glPushMatrix();
        Head.benderhead(gl);
        gl.glPopMatrix();
        gl.glPushMatrix();
        Torso.benderTorso(gl);
        gl.glPopMatrix();
        gl.glPushMatrix();
        if (gPressed) {
            Arms.moveRightArm(gl);
        } else {
            Arms.rightArm(gl);
        }
        gl.glPopMatrix();
        gl.glPushMatrix();
        Arms.leftArm(gl);
        gl.glPopMatrix();
        gl.glPushMatrix();
        Legs.leftLeg(gl);
        Legs.run(gl);
        gl.glPopMatrix();
        gl.glPushMatrix();
        Legs.rightLeg(gl);
        gl.glPopMatrix();
        gl.glPushMatrix();
        Misc.Cigar(gl);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glTranslatef(0f, 1.9f, 1f);
        if (fPressed) {
            for (int j = 0; j < 14; j++) {
                Particle cur = particleList.getFirst();
                //  System.out.println(cur.getY());
                if (1 >= cur.getY()) {
                    gl.glTranslatef(0f, 0.01f, 0f);
                    cur.draw(gl, cur.getX(), (cur.getY() + 0.01f), cur.getZ());
                    //         System.out.println("im drawing in the outer if-test");  
                    if (cur.getY() >= 1) {
                        cur.draw(gl, cur.getX(), 0, cur.getZ());

                    }
                }
                particleList.removeFirst();
                particleList.addLast(cur);
            }
        }
        gl.glPopMatrix();

        /*hack*/ gl.glPopMatrix();

        gl.glFlush();

    }

    public void displayChanged(GLAutoDrawable drawable,
            boolean modeChanged, boolean deviceChanged) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Dimension size = e.getComponent().getSize();
        float thetaY = 360.0f * ((float) (x - oldMouseX) / (float) size.width);
        float thetaX = 360.0f * ((float) (oldMouseY - y) / (float) size.height);
        oldMouseX = x;
        oldMouseY = y;
        view_rotx += thetaX;
        view_roty += thetaY;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    /**
     *
     * @param gl GL object.
     * @param amb Float array 4 values, RGBA.
     * @param diff Float array 4 values, RGBA.
     * @param specular Float array 4 values, RGBA.
     */
    public static void material(GL gl, float[] amb, float[] diff, float[] specular) {
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, amb, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, diff, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, specular, 0);
    }

    public void keyTyped(KeyEvent e) {
        // System.out.println("hei");
    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {

            Legs.moveLegs(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            Legs.moveLegs(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftArrowPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            rightArrowPressed = true;

        }
        if (e.getKeyCode() == KeyEvent.VK_F) {
            fPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_G) {
            gPressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftArrowPressed = false;

        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            rightArrowPressed = false;

        }
        if (e.getKeyCode() == KeyEvent.VK_F) {
            fPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_G) {
            gPressed = false;
        }

    }

}

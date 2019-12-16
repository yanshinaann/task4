/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package task4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import task4.draw.IDrawer;
import task4.draw.SimpleEdgeDrawer;
import task4.function.Function;
import task4.math.Vector3;
import task4.models.Prism;
import task4.screen.ScreenConverter;
import task4.third.Camera;
import task4.third.Scene;

/**
 *
 * @author Alexey
 */
public class DrawPanel extends JPanel
        implements CameraController.RepaintListener {
    private Scene scene;
    private ScreenConverter sc;
    private Camera cam;
    private CameraController camController;
    private Function fun;
    private Field field;
    
    public DrawPanel() {
        super();
        sc = new ScreenConverter(-1, 1, 2, 2, 1, 1);
        cam = new Camera();
        fun = new Function();
        field = new Field(0.3, 0.3);
        camController = new CameraController(cam, sc);
        scene = new Scene(Color.WHITE.getRGB());
        scene.showAxes();
        
//        scene.getModelsList().add(new Parallelepiped(
//                new Vector3(-0.4f, -0.4f, -0.4f),
//                new Vector3(0.4f, 0.4f, 0.4f)
//        ));
        scene.getModelsList().add(new Prism(
                fun.getStartPoint(field),
                0.2f,
                6, 0.07, fun

        ));
//        scene.getModelsList().add(new Prism(
//                new Vector3(0, 0.5f, 0),
//                0.5f,
//                6, 0.1
//
//        ));
        camController.addRepaintListener(this);
        addMouseListener(camController);
        addMouseMotionListener(camController);
        addMouseWheelListener(camController);
    }
    
    @Override
    public void paint(Graphics g) {
        sc.setScreenSize(getWidth(), getHeight());
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D)bi.getGraphics();
        IDrawer dr = new SimpleEdgeDrawer(sc, graphics);
        scene.drawScene(dr, cam);
        g.drawImage(bi, 0, 0, null);
        graphics.dispose();
    }

    @Override
    public void shouldRepaint() {
        repaint();
    }
}

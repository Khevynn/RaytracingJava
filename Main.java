import BaseClasses.*;
import SceneObjects.*;
import SceneObjects.Shapes.*;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Main {
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;
    static BufferedImage image;
    
    public static void main(String[] args) {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        
        JFrame frame = new JFrame("Raytracer");
        frame.setSize(WIDTH, HEIGHT);

        Renderer renderer = new Renderer(WIDTH, HEIGHT);
        Camera mainCamera = new Camera(new Vector3(-10, 10, -20), Vector3.ZERO.add(Vector3.UNIT_Y.multiply(4)));
        Color background = Color.Black;
        Scene scene = new Scene(mainCamera, background);
        
        Finish shinyOptions = new Finish();
        shinyOptions.diffuse = 1;
        shinyOptions.shiny = 1;
        shinyOptions.reflection = 0.3;

        Shape[] shapes = {
            new Plane(Vector3.UNIT_Y, 0, new Appearance(Color.White, new Finish())),
            new Sphere(new Vector3(6, 2, 0), 2, new Appearance(Color.Yellow, shinyOptions)),
            new Sphere(new Vector3(6, 1, -4), 1, new Appearance(Color.Green, shinyOptions)),
            new Sphere(new Vector3(-2, 2, 4), 2, new Appearance(Color.Blue, shinyOptions)),
            new Sphere(new Vector3(-4, 4, 10), 4, new Appearance(Color.Pink, shinyOptions)),
            new Sphere(new Vector3(4, 4, 50), 1, new Appearance(Color.White, shinyOptions)),
            new Sphere(new Vector3(-4, 4, 10), 2, new Appearance(Color.Black, shinyOptions)),
            new Sphere(new Vector3(7, 4, 10), 1, new Appearance(Color.Green, shinyOptions)),
            new Sphere(new Vector3(-4, 4, 30), 3, new Appearance(Color.Purple, shinyOptions)),
            new Sphere(new Vector3(10, 4, 20), 4, new Appearance(Color.Black, shinyOptions)),
            new Sphere(new Vector3(-3.2, 1, -1), 1, new Appearance(Color.Purple, shinyOptions))
        };

        Light[] lights = {
            new Light(new Vector3(-30, 25, -12), Color.White)
        };

        for (Shape shape : shapes) {
            scene.addShape(shape);
        }
        for (Light light : lights) {
            scene.addLight(light);
        }

        RenderCallback callback = (x, y, color) -> {
            int rgb = ((int)(color.getR()) << 16) | 
                     ((int)(color.getG()) << 8) | 
                     (int)(color.getB());
            image.setRGB((int)x, (int)y, rgb);
        };
        renderer.render(scene, callback);

        JLabel label = new JLabel(new ImageIcon(image));
        frame.add(label);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
package SceneObjects;

import BaseClasses.Color;
import SceneObjects.Shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class Scene {

    private Camera camera;
    private Color background;
    public List<Shape> shapes;
    public List<Light> lights;

    public Scene(Camera camera, Color backgroundColor){
        this.camera = camera;
        this.background = backgroundColor != null ? backgroundColor : Color.Black;
        shapes = new ArrayList<>();
        lights = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }
    public void addLight(Light light) {
        lights.add(light);
    }

    public Color trace(double x, double y){
        return this.camera.trace(this, x, y);
    }

    public Color getBackground() {
        return background;
    }
}


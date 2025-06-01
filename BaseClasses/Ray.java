package BaseClasses;

import SceneObjects.Scene;
import SceneObjects.Shapes.Shape;

public class Ray {

    final int MAX_DEPTH = 16;

    public Vector3 start;
    public Vector3 direction;

    public Ray(Vector3 start, Vector3 direction){
        this.start = start;
        this.direction = direction;
    }

    public Vector3 reflect(Vector3 normal) {
        return this.direction.subtract(
            normal.multiply(2 * this.direction.dot(normal))
        ); 
    }
    
    public Color trace(Scene scene, int depth){

        if(depth >= MAX_DEPTH){
            return Color.Black;
        }

        double[] distances = scene.shapes.stream()
            .mapToDouble(shape -> shape.closestDistanceAlongRay(this))
            .toArray();

        double shortestDistance = Double.POSITIVE_INFINITY;
        int nearestIndex = -1;
        for(int i = 0; i < distances.length; ++i){
            if (distances[i] < shortestDistance) {
                shortestDistance = distances[i];
                nearestIndex = i;
            }
        }

        if(shortestDistance == Double.POSITIVE_INFINITY){
            return scene.getBackground();
        }

        Shape nearestShape = scene.shapes.get(nearestIndex);
        Vector3 point = this.start.add(this.direction.multiply(shortestDistance)); //TODO: unit()

        return nearestShape.getColorAt(point, this, scene, depth);
    }
}

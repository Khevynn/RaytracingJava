package SceneObjects.Shapes;

import BaseClasses.*;
import SceneObjects.Scene;
import SceneObjects.Light;

public class Shape {
    private Appearance appearance;
    
    private static final double INTERSECTION_EPSILON = 1e-6;
    private static final double SHADOW_EPSILON = 0.001;
    
    public Shape(Appearance appearance){
        this.appearance = appearance;
    }
    public boolean castsShadowFor(Vector3 point, Vector3 lightVector){
        double distanceToLight = lightVector.length();
        Ray ray = new Ray(point, lightVector.unit());  // Normalize the direction
        double intersectionDistance = this.closestDistanceAlongRay(ray);
        // Ignore intersections very close to the surface or beyond the light
        return (intersectionDistance > SHADOW_EPSILON && intersectionDistance < distanceToLight - SHADOW_EPSILON);
    }

    public Vector3 getNormalAt(Vector3 point) {
        throw new RuntimeException("You forgot to override getNormalAt");
    }
    public Color getColorAt(Vector3 point, Ray ray, Scene scene, int depth) {
        Vector3 normal = this.getNormalAt(point);
        Color color = this.appearance.getAmbientColorAt(point);
        
        Vector3 reflex = ray.reflect(normal);
        depth++;

        Color reflection = this.appearance.reflect(point, reflex, scene, depth);
        color = color.add(reflection);

        for(Light light : scene.lights){
            Vector3 vector = Vector3.from(point).to(light.getPosition());
            double brightness = normal.dot(vector.unit());

            // First check if the surface is facing away from light
            if(brightness <= 0){
                continue;  // Skip negative brightness but keep accumulating other lights
            }

            // Then check for shadows
            if(scene.shapes.stream().anyMatch(shape -> shape.castsShadowFor(point, vector))) {
                continue;
            }

            Color illumination = light.illuminate(this.appearance.getMaterial(), point, brightness);
            color = color.add(illumination);

            Color highlight = this.appearance.getFinish().addHighlight(reflex, light, vector);
            color = color.add(highlight);
        }

        return color;
    }
    
    public double[] intersect(Ray ray){
        throw new RuntimeException("Method not implemented");
    }

    public double closestDistanceAlongRay(Ray ray){
        double[] distances = this.intersect(ray);
        double shortestDistance = Double.POSITIVE_INFINITY;
        for(double distance : distances){
            // Only consider intersections that are a meaningful distance in front of the ray
            if(distance > INTERSECTION_EPSILON) {
                shortestDistance = Math.min(shortestDistance, distance);
            }
        }
        return shortestDistance;
    }
}

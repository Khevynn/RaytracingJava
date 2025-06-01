package SceneObjects.Shapes;

import BaseClasses.Appearance;
import BaseClasses.Vector3;
import BaseClasses.Ray;

public class Plane extends Shape {
    private double distanceFromOrigin;
    private Vector3 normalDirection;
    
    public Plane(Vector3 normalDirection, double distanceFromOrigin, Appearance appearance){
        super(appearance);
        this.distanceFromOrigin = distanceFromOrigin;
        this.normalDirection = normalDirection;
    }

    @Override
    public double[] intersect(Ray ray) {
        // Calculate denominator first (ray direction dot normal)
        double denom = ray.direction.dot(this.normalDirection);
        
        // If denominator is zero, ray is parallel to plane (no intersection)
        if (Math.abs(denom) < 1e-6) {
            return new double[0];
        }
        
        // Calculate vector from ray start to point on plane
        Vector3 toPlane = Vector3.from(ray.start).to(normalDirection.multiply(distanceFromOrigin));
        
        // Calculate intersection distance
        double t = toPlane.dot(this.normalDirection) / denom;
        
        // Only return positive intersections (in front of the ray)
        if (t <= 0) {
            return new double[0];
        }
        
        return new double[]{t};
    }

    @Override
    public Vector3 getNormalAt(Vector3 point) {
        // The normal is the same everywhere on the plane
        return this.normalDirection;
    }
}

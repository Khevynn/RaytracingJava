package SceneObjects.Shapes;

import BaseClasses.*;

public class Sphere extends Shape {
    private Vector3 center;
    private double radius;
    
    public Sphere(Vector3 center, double radius, Appearance appearance){
        super(appearance);
        this.center = center;
        this.radius = radius;
    }

    @Override
    public double[] intersect(Ray ray) {
        // Vector from sphere center to ray origin
        Vector3 originToCenter = Vector3.from(this.center).to(ray.start);
        
        // Calculate quadratic equation coefficients
        // For a sphere: (ray.origin + t*ray.direction - sphere.center)² = radius²
        // Simplified to: at² + 2(O-C)·D*t + (O-C)² - r² = 0
        // Where O is ray origin, C is sphere center, D is ray direction, r is radius
        
        double a = ray.direction.dot(ray.direction);  // Should be 1 if normalized
        double b = 2 * originToCenter.dot(ray.direction);
        double c = originToCenter.squid() - this.radius * this.radius;

        // Use numerically stable quadratic solution
        double discriminant = b * b - 4 * a * c;
        
        if(discriminant < -1e-6) {  // Account for floating point error
            return new double[0];
        }
        if(Math.abs(discriminant) < 1e-6) {  // Very close to zero
            double t = -b / (2 * a);
            return t > 0 ? new double[]{t} : new double[0];
        }
        
        // Use numerically stable quadratic formula
        double q = (b > 0) ?
            -0.5 * (b + Math.sqrt(discriminant)) :
            -0.5 * (b - Math.sqrt(discriminant));
            
        double t1 = q / a;
        double t2 = c / q;
        
        // Sort intersections so t1 <= t2
        if (t1 > t2) {
            double temp = t1;
            t1 = t2;
            t2 = temp;
        }
        
        // Handle all cases of intersections being behind the ray
        if (t2 < 0) {
            return new double[0];
        }
        if (t1 < 0) {
            return new double[]{t2};
        }
        return new double[]{t1, t2};
    }

    @Override
    public Vector3 getNormalAt(Vector3 point) {
        // Calculate normal vector from center to point and normalize it
        return Vector3.from(this.center).to(point).unit();
    }
}

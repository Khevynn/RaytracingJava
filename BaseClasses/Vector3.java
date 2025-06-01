package BaseClasses;

public class Vector3 {
    private double x = 0;
    private double y = 0;
    private double z = 0;

    private Vector3 origin = null;
    public static final Vector3 UNIT_X = new Vector3(1, 0, 0);
    public static final Vector3 UNIT_Y = new Vector3(0, 1, 0);
    public static final Vector3 UNIT_Z = new Vector3(0, 0, 1);
    public static final Vector3 ZERO = new Vector3(0, 0, 0);

    public Vector3(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
        origin = this;
    }
    
    public static Vector3 from(Vector3 origin) {
        return new Vector3(origin.x, origin.y, origin.z);
    }
    public Vector3 to(Vector3 target) {
        return target.subtract(origin);
    }

    // Basic vector operations
    public Vector3 add(Vector3 other){
        return new Vector3(this.x + other.x, this.y + other.y, this.z + other.z);
    }
    public Vector3 subtract(Vector3 other){
        return new Vector3(this.x - other.x, this.y - other.y, this.z - other.z);
    }
    public Vector3 multiply(double scalar){
        return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
    }
    public Vector3 divide(double divisor){
        return new Vector3(this.x / divisor, this.y / divisor, this.z / divisor);
    }

    public double dot(Vector3 other){
        return x * other.x + y * other.y + z * other.z;
    }
    public Vector3 cross(Vector3 other) {
        return new Vector3(
            y * other.z - z * other.y,
            z * other.x - x * other.z,
            x * other.y - y * other.x
        );
    }
    
    public Vector3 normalize() {
        double mag = magnitude();
        if (mag == 0) return new Vector3(0, 0, 0);
        return new Vector3(x / mag, y / mag, z / mag);
    }
    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double length(){
        return Math.sqrt(this.squid());
    }
    public double squid(){
        return x*x + y*y + z*z;
    }

    public Vector3 unit(){
        return new Vector3(this.divide(this.length()).x, this.divide(this.length()).y, this.divide(this.length()).z);
    }
    public Vector3 invert(){
        return new Vector3(-this.x, -this.y, -this.z);
    }
    
    // Getters
    public double getX(){ return x; }
    public double getY(){ return y; }
    public double getZ(){ return z; }
}

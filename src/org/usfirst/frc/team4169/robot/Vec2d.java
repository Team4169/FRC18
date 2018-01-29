package org.usfirst.frc.team4169.robot;

public class Vec2d {
	/**
     * The zero vector, for convenience
     */
    public static final Vec2d ZERO = makeCart(0.0, 0.0);
    public double x;
    public double y;
    /**
     * This is only a suggested constructor
     * @param x x-coord
     * @param y y-coord
     */
     private Vec2d(double x, double y) {
    	 this.x = x;
    	 this.y = y;
     }
    /**
     * Factory method to construct a vector given its cartesian coordinates
     * @param x The x-coord
     * @param y The y-coord
     * @return the vector
     */
     public static Vec2d makeCart(double x, double y) {
    	 Vec2d vector = new Vec2d(x, y);
    	 return vector;
     }
    /**
     * Factory method to construct a vector given its polar coordinates
     * @param r The r-coordinate (length)
     * @param theta The theta-coordinate (angle from X axis, in radians)
     * @return the vector
     */
     public static Vec2d makePolar(double r, double theta) {
    	 double x = r * Math.cos(theta);
    	 double y = r * Math.sin(theta);
    	 Vec2d vector = new Vec2d(x, y);
    	 return vector;
     }
    /**
     * Get the x-coordinate
     * @return The x-coordinate
     */
     public double getX() { 
    	 return this.x;
     }
    /**
     * Get the y-coordinate
     * @return The y-coordinate
     */
     public double getY(){
    	 return this.y;
     }
    /**
     * Get the r-coordinate (use Math.hypot)
     * @return The r-coordinate
     */
     public double getR(){
    	 return Math.hypot(x, y);
     }
    /**
     * Get the theta-coordinate (use Math.atan2 !!!)
     * @return The theta-coordinate (angle) in radians, -pi <= res <= pi
     */
     public double getTheta() {
    	 double theta = Math.atan(y/x);
    	 if(x < 0 && y < 0){
    		 return theta - Math.PI;
    	 }else if(x < 0 && y > 0){
    		 return theta + Math.PI;
    	 }else if(x == 0){
    		 if(y > 0){
    			 return Math.PI/2;
    		 }else if(y < 0){
    			 return -Math.PI/2;
    		 }else{
    			 return 0;
    		 }
    	 }else if(y == 0 && x < 0){	
    		 return Math.PI;
    	 }else{
    		 return 0;
    	 }
     }
    /**
     * Add a vector, returning the result
     * @param a addend
     * @return The sum
     */
     public Vec2d add(Vec2d a) {
    	 double x = a.getX();
    	 double y = a.getY();
    	 x += this.x;
    	 y += this.y;
    	 return makeCart(x, y);
     }
     
     
    /**
     * Subtract a vector, returning the result
     * @param s subtrahend
     * @return The difference
     */
     public Vec2d sub(Vec2d s) {
    	 double x = s.getX();
    	 double y = s.getY();
    	 x -= this.x;
    	 y -= this.y;
    	 return makeCart(x, y);
     }
     
    /**
     * Multiply by a scalar (double), returning
     * the result.
     * @param m The scalar to multiply by
     * @return product of this vector and the scalar
     */
     public Vec2d mulScalar(double m) {
    	 double x = this.x;
    	 double y = this.y;
    	 x *= m;
    	 y *= m;
    	 return makeCart(x, y);
     }
     
     public boolean isNear(Vec2d v, double epsilon) {
    	 double distance = Math.sqrt(Math.pow(Math.abs(v.x-this.x), 2)+Math.pow(Math.abs(v.y-this.y), 2));
    	 return distance <= epsilon;
     }
     public boolean isNear(Vec2d v) {
    	 double distance = Math.sqrt(Math.pow(Math.abs(v.x-this.x), 2)+Math.pow(Math.abs(v.y-this.y), 2));
    	 return distance <= 5;
     }

    @Override
    public boolean equals(Object o){
    	if(!(o instanceof Vec2d)){
    		return false;
    	}
    	return !(((Vec2d)o).getX() == this.x && ((Vec2d)o).getY() == this.y);
    }
    @Override
    public int hashCode(){
    	return (int) (this.x + this.y);
    }
     
    @Override
    public String toString(){
    	return ("(" + this.x + ", " + this.y + ")");
    }
    
}


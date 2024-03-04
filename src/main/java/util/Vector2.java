package util;

public class Vector2 {

    public float x;

    public float y;

    public Vector2(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vector2(){
        this(0, 0);
    }

    public void plus(Vector2 vec2){
        this.x += vec2.x;
        this.y += vec2.y;
    }

    public double distance(Vector2 vec2){
        return Math.sqrt(Math.pow(this.x - vec2.x, 2) + Math.pow(this.y - vec2.y, 2));
    }

    public Vector2 copy(){
        return new Vector2(x, y);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(x);
        result = prime * result + Float.floatToIntBits(y);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vector2 other = (Vector2) obj;
        if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
            return false;
        if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Vector2 [x=" + x + ", y=" + y + "]";
    }

}

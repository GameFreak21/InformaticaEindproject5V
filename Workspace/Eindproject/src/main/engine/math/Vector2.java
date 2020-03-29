package main.engine.math;

public class Vector2 {
public float x, y;
	
	public Vector2(){
		this.x = 0.0f;
		this.y = 0.0f;
	}
	public Vector2(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public static Vector2 add(Vector2 a, Vector2 b) {
		Vector2 out = new Vector2(a.x + b.x, a.y + b.y);
		return out;
	}

	public static Vector2 subtract(Vector2 a, Vector2 b) {
		Vector2 out = new Vector2(a.x - b.x, a.y - b.y);
		return out;
	}

	public static Vector2 multiply(Vector2 a, Vector2 b) {
		Vector2 out = new Vector2(a.x * b.x, a.y * b.y);
		return out;
	}

	public static Vector2 devide(Vector2 a, Vector2 b) {
		Vector2 out = new Vector2(a.x / b.x, a.y / b.y);
		return out;
	}

	public static float dot(Vector2 a, Vector2 b) {
		float out = (a.x * b.x) + (a.y * b.y);
		return out;
	}

	public static float length(Vector2 a) {
		float len = (float) Math.sqrt(a.x * a.x + a.y * a.y);
		return len;
	}
	
	public static Vector2 normalize(Vector2 a) {
		float len = Vector2.length(a);
		Vector2 out = Vector2.devide(a, new Vector2(len,len));
		return out;
	}
}

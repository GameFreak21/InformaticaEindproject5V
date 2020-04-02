package main.engine.math;

public class Vector3 {
	public float x, y, z;
	
	public Vector3(){
		this.x = 0.0f;
		this.y = 0.0f;
		this.z = 0.0f;
	}
	public Vector3(float len){
		this.x = len;
		this.y = len;
		this.z = len;
	}
	public Vector3(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static Vector3 add(Vector3 a, Vector3 b) {
		Vector3 out = new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
		return out;
	}

	public static Vector3 subtract(Vector3 a, Vector3 b) {
		Vector3 out = new Vector3(a.x - b.x, a.y - b.y, a.z - b.z);
		return out;
	}

	public static Vector3 multiply(Vector3 a, Vector3 b) {
		Vector3 out = new Vector3(a.x * b.x, a.y * b.y, a.z * b.z);
		return out;
	}

	public static Vector3 devide(Vector3 a, Vector3 b) {
		Vector3 out = new Vector3(a.x / b.x, a.y / b.y, a.z / b.z);
		return out;
	}

	public static float dot(Vector3 a, Vector3 b) {
		float out = (a.x * b.x) + (a.y * b.y) + (a.z * b.z);
		return out;
	}

	public static float length(Vector3 a) {
		float len = (float) Math.sqrt(a.x * a.x + a.y * a.y + a.z * a.z);
		return len;
	}

	public static Vector3 cross(Vector3 a, Vector3 b) {
		Vector3 out = new Vector3((a.y * b.z) - (a.z * b.y), (a.z * b.x) - (a.x * b.z), (a.x * b.y) - (a.y * b.x));
		return out;
	}
	
	public static Vector3 normalize(Vector3 a) {
		float len = Vector3.length(a);
		Vector3 out = Vector3.devide(a, new Vector3(len,len,len));
		return out;
	}
}

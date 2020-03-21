package main.engine.math;

public class Vector4 {
	public float x, y, z, w;
	
	public Vector4(){
		this.x = 0.0f;
		this.y = 0.0f;
		this.z = 0.0f;
		this.w = 0.0f;
	}
	public Vector4(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = 0.0f;
	}
	public Vector4(float x, float y, float z, float w){
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
}

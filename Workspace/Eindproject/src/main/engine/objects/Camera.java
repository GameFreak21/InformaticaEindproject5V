package main.engine.objects;

import main.engine.math.Vector3;

public class Camera {
	public Vector3 position, rotation;
	
	public Camera(Vector3 position, Vector3 rotation) {
		this.position = position;
		this.rotation = rotation;
	}
	
}

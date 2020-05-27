package main.engine.renderer.primitives;

import main.engine.math.Vector3;
import main.engine.objects.GameObject;
import main.engine.renderer.graphics.Mesh;

public class Plane {
	public GameObject gameObject;
	Vector3 pos, rot, scale, speed;
	float mass;
	
	public Plane(Vector3 pos, Vector3 rot, Vector3 size, Mesh mesh) {
		this.pos = pos;
		this.scale = size;
		this.rot = rot;
		this.speed = new Vector3();
		this.mass = 0;
		this.gameObject = new GameObject(this.pos, this.rot, this.scale, mesh, this.mass, this.speed);
	}

	public Plane(Vector3 pos, Vector3 rot, float len, Mesh mesh) {
		this.pos = pos;
		this.scale = new Vector3(len, len, len);
		this.rot = rot;
		this.speed = new Vector3();
		this.mass = 0;
		this.gameObject = new GameObject(this.pos, this.rot, this.scale, mesh, this.mass, this.speed);
	}
}

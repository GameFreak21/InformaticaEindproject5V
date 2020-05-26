package main.engine.renderer.primitives;

import main.engine.math.Vector3;
import main.engine.objects.GameObject;
import main.engine.renderer.graphics.Mesh;

public class Plane {
	public GameObject gameObject;
	Vector3 pos, rot, scale;
	
	public Plane(Vector3 pos, Vector3 rot, Vector3 size, Mesh mesh) {
		this.pos = pos;
		this.scale = size;
		this.rot = rot;
		this.gameObject = new GameObject(this.pos, this.rot, this.scale, mesh);
	}

	public Plane(Vector3 pos, Vector3 rot, float len, Mesh mesh) {
		this.pos = pos;
		this.scale = new Vector3(len, len, len);
		this.rot = rot;
		this.gameObject = new GameObject(this.pos, this.rot, this.scale, mesh);
	}
}

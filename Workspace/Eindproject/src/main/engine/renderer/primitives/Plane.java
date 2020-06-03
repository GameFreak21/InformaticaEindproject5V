package main.engine.renderer.primitives;

import main.engine.math.Vector3;
import main.engine.objects.GameObject;
import main.engine.renderer.graphics.Mesh;

public class Plane extends GameObject{
	Vector3 pos, rot, scale, speed;
	float mass;
	
	public Plane(Vector3 position, Vector3 rotation, Vector3 scale, Mesh mesh, float mass, Vector3 speed, boolean collisions) {
		super(position, rotation, scale, mesh, mass, speed, collisions);
	}
}

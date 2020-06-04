package main.engine.renderer.primitives;

import main.engine.math.Vector2;
import main.engine.math.Vector3;
import main.engine.objects.GameObject;
import main.engine.renderer.graphics.Material;
import main.engine.renderer.graphics.Mesh;
import main.engine.renderer.graphics.Vertex;
	
public class Plane extends GameObject{
	public static Mesh planeMesh = new Mesh(new Vertex[] {
			// top
			new Vertex(new Vector3(-0.5f, 0, -0.5f), new Vector3(), new Vector3(1, 1, 1), new Vector2(0, 0)), // linksachter
			new Vertex(new Vector3(-0.5f, 0, 0.5f), new Vector3(), new Vector3(1, 1, 1), new Vector2(0, 1)), // linksvoor
			new Vertex(new Vector3(0.5f, 0, -0.5f), new Vector3(), new Vector3(1, 1, 1), new Vector2(1, 0)), // rechtsachter
			new Vertex(new Vector3(0.5f, 0, 0.5f), new Vector3(), new Vector3(1, 1, 1), new Vector2(1, 1)) // rechtsvoor
	}, new int[] {
			// top
			0, 1, 3, 0, 3, 2 }, new Material("resources/textures/grass.png"));
	Vector3 pos, rot, scale, speed;
	float mass;
	
	public Plane(Vector3 position, Vector3 rotation, Vector3 scale, float mass, Vector3 speed, boolean collisions) {
		super(position, rotation, scale, planeMesh, mass, speed, collisions);
	}
}

package main.engine.renderer.primitives;

import main.engine.math.Vector3;
import main.engine.renderer.graphics.Mesh;
import main.engine.renderer.graphics.Vertex;
import main.engine.objects.GameObject;

public class Pyramid {
	Vector3 pos;
	Vector3 rot;
	Vector3 scale;
	
	public Pyramid(Vector3 pos, Vector3 size, Vector3 rot) {
		this.pos = pos;
		this.scale = size;
		this.rot = rot;
	}
	
	public Pyramid(Vector3 pos, float len, Vector3 rot) {
			this.pos = pos;
			this.scale = new Vector3(len, len, len);
			this.rot = rot;
	}
	Mesh mesh = new Mesh(
			new Vertex[] {
					//bottom
					new Vertex(new Vector3(-0.5f, 0.0f, -0.5f), new Vector3(1.0f, 0.0f, 0.0f)),
					new Vertex(new Vector3(-0.5f, 0.0f, 0.5f), new Vector3(0.0f, 1.0f, 0.0f)),
					new Vertex(new Vector3(0.5f, 0.0f, -0.5f), new Vector3(0.0f, 0.0f, 1.0f)),
					new Vertex(new Vector3(0.5f, 0.0f, 0.5f), new Vector3(0.5f, 0.0f, 0.5f)),
					
					//top
					new Vertex(new Vector3(0.0f, 0.5f, 0.0f), new Vector3(1.0f, 0.0f, 0.0f))
					
			},
		new int[] {
				0, 1, 4,
				0, 2, 4,
				1, 3, 4,
				2, 3, 4
		});
	public GameObject object = new GameObject(pos, rot, scale, mesh);
}
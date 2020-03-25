package main.engine.renderer.primitives;

import main.engine.math.Vector3;
import main.engine.renderer.graphics.Mesh;
import main.engine.renderer.graphics.Vertex;
import main.engine.objects.GameObject;

public class Wedge {
	Vector3 pos;
	Vector3 rot;
	Vector3 scale;
	
	public Wedge(Vector3 pos, Vector3 size, Vector3 rot) {
		this.pos = pos;
		this.scale = size;
		this.rot = rot;
	}
	public Wedge(Vector3 pos, float len, Vector3 rot) {
		this.pos = pos;
		this.scale = new Vector3(len, len, len);
		this.rot = rot;
	}
	Mesh mesh = new Mesh(
		new Vertex[] {
				//wide
				new Vertex(new Vector3(-0.5f, -0.5f, -0.5f), new Vector3(1.0f, 0.0f, 0.0f)),
				new Vertex(new Vector3(-0.5f, -0.5f, 0.5f), new Vector3(0.0f, 1.0f, 0.0f)),
				new Vertex(new Vector3(-0.5f, 0.5f, -0.5f), new Vector3(0.0f, 0.0f, 1.0f)),
				new Vertex(new Vector3(-0.5f, 0.5f, 0.5f), new Vector3(0.5f, 0.0f, 0.5f)),
				
				//small
				new Vertex(new Vector3(0.5f, 0.0f, -0.5f), new Vector3(1.0f, 0.0f, 0.0f)),
				new Vertex(new Vector3(0.5f, 0.0f, 0.5f), new Vector3(0.0f, 1.0f, 0.0f))
				
		},
		new int[] {
				//wide
				0, 1, 2,
				0, 2, 3,
				
				//wide-to-small-sides
				0, 4, 2,
				1, 5, 3,
				
				//wide-to-small-bottom
				0, 4, 5,
				0, 1, 5,
				
				//wide-to-small-top
				2, 4, 5,
				2, 3, 5
		});
}

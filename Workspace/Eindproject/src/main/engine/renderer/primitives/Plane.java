package main.engine.renderer.primitives;

import main.engine.math.Vector2;
import main.engine.math.Vector3;
import main.engine.objects.GameObject;
import main.engine.renderer.graphics.Material;
import main.engine.renderer.graphics.Mesh;
import main.engine.renderer.graphics.Vertex;

public class Plane {
	public GameObject gameObject;
	Vector3 pos, rot, scale;

	Mesh mesh = new Mesh(
			new Vertex[] { 
					//top
					new Vertex(new Vector3(-0.5f, 0.5f, -0.5f), new Vector3(), new Vector3(1, 1, 1), new Vector2(0, 0)),	//linksachter
					new Vertex(new Vector3(-0.5f, 0.5f, 0.5f), new Vector3(), new Vector3(1, 1, 1), new Vector2(0,1)),		//linksvoor
					new Vertex(new Vector3(0.5f, 0.5f, -0.5f), new Vector3(), new Vector3(1, 1, 1), new Vector2(1, 0)),		//rechtsachter
					new Vertex(new Vector3(0.5f, 0.5f, 0.5f), new Vector3(), new Vector3(1, 1, 1), new Vector2(1,1))		//rechtsvoor
				},
			new int[] { 
					//top
					0, 1, 3,
					0, 3, 2
				}, new Material("resources/textures/grass.png"));
	
	
	public Plane(Vector3 pos, Vector3 rot, Vector3 size) {
		this.pos = pos;
		this.scale = size;
		this.rot = rot;
		this.gameObject = new GameObject(this.pos, this.rot, this.scale, this.mesh);
	}

	public Plane(Vector3 pos, Vector3 rot, float len) {
		this.pos = pos;
		this.scale = new Vector3(len, len, len);
		this.rot = rot;
		this.gameObject = new GameObject(this.pos, this.rot, this.scale, this.mesh);
	}
}

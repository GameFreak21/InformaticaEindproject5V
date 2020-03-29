package main.engine.renderer.primitives;

import main.engine.math.Vector3;
import main.engine.renderer.graphics.Mesh;
import main.engine.renderer.graphics.Vertex;
import main.engine.objects.GameObject;


public class Box {
	
	public GameObject gameObject;
	Vector3 pos, rot, scale;

	Mesh mesh = new Mesh(
			new Vertex[] { 
					//front
					new Vertex(new Vector3(-0.5f, 0.5f, -0.5f), new Vector3(1.0f, 0.0f, 0.0f)),
					new Vertex(new Vector3(0.5f, 0.5f, -0.5f), new Vector3(0.0f, 1.0f, 0.0f)),
					new Vertex(new Vector3(0.5f, -0.5f, -0.5f), new Vector3(0.0f, 0.0f, 1.0f)),
					new Vertex(new Vector3(-0.5f, -0.5f, -0.5f), new Vector3(0.5f, 0.0f, 0.5f)),
					
					//right
					new Vertex(new Vector3(0.5f,0.5f,-0.5f), new Vector3(1.0f,0.0f,0.0f)),
					new Vertex(new Vector3(0.5f,-0.5f,-0.5f), new Vector3(0.0f,1.0f,0.0f)),
					new Vertex(new Vector3(0.5f,0.5f,0.5f), new Vector3(0.0f,0.0f,1.0f)),
					new Vertex(new Vector3(0.5f,-0.5f,0.5f), new Vector3(0.5f,0.0f,0.5f)),
					
					//back
					new Vertex(new Vector3(-0.5f, 0.5f, 0.5f), new Vector3(1.0f, 0.0f, 0.0f)),
					new Vertex(new Vector3(0.5f, 0.5f, 0.5f), new Vector3(0.0f, 1.0f, 0.0f)),
					new Vertex(new Vector3(0.5f, -0.5f, 0.5f), new Vector3(0.0f, 0.0f, 1.0f)),
					new Vertex(new Vector3(-0.5f, -0.5f, 0.5f), new Vector3(0.5f, 0.0f, 0.5f)),
					
					//left
					new Vertex(new Vector3(-0.5f,0.5f,-0.5f), new Vector3(1.0f,0.0f,0.0f)),
					new Vertex(new Vector3(-0.5f,-0.5f,-0.5f), new Vector3(0.0f,1.0f,0.0f)),
					new Vertex(new Vector3(-0.5f,0.5f,0.5f), new Vector3(0.0f,0.0f,1.0f)),
					new Vertex(new Vector3(-0.5f,-0.5f,0.5f), new Vector3(0.5f,0.0f,0.5f)),
					
					//bottom
					new Vertex(new Vector3(-0.5f, -0.5f, -0.5f), new Vector3(1.0f, 0.0f, 0.0f)),
					new Vertex(new Vector3(-0.5f, -0.5f, 0.5f), new Vector3(0.0f, 1.0f, 0.0f)),
					new Vertex(new Vector3(0.5f, -0.5f, -0.5f), new Vector3(0.0f, 0.0f, 1.0f)),
					new Vertex(new Vector3(0.5f, -0.5f, 0.5f), new Vector3(0.5f, 0.0f, 0.5f)),
					
					//top
					new Vertex(new Vector3(-0.5f, 0.5f, -0.5f), new Vector3(1.0f, 0.0f, 0.0f)),
					new Vertex(new Vector3(-0.5f, 0.5f, 0.5f), new Vector3(0.0f, 1.0f, 0.0f)),
					new Vertex(new Vector3(0.5f, 0.5f, -0.5f), new Vector3(0.0f, 0.0f, 1.0f)),
					new Vertex(new Vector3(0.5f, 0.5f, 0.5f), new Vector3(0.5f, 0.0f, 0.5f))
				},
			new int[] { 
					//front
					0, 1, 2, // triangles for rendering , top left, top right, bot right
					0, 3, 2,
					
					//right
					4, 5, 6,
					6, 7, 5,
					
					//back
					8, 9, 10,
					8, 11, 10,
					
					//left
					12, 13, 14,
					14, 15, 13,
					
					//bottom
					16, 17, 18,
					18, 19, 17,
					
					//top
					20, 21, 22,
					22, 23, 21
				});
	
	public Box(Vector3 pos, Vector3 rot, Vector3 size) {
		this.pos = pos;
		this.scale = size;
		this.rot = rot;
		this.gameObject = new GameObject(this.pos, this.rot, this.scale, this.mesh);
	}

	public Box(Vector3 pos, Vector3 rot, float len) {
		this.pos = pos;
		this.scale = new Vector3(len, len, len);
		this.rot = rot;
		this.gameObject = new GameObject(this.pos, this.rot, this.scale, this.mesh);
	}
	
	

}

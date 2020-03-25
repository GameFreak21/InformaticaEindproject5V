package main.engine.renderer.primitives;

import main.engine.io.Window;
import main.engine.math.Vector3;
import main.engine.renderer.graphics.Mesh;
import main.engine.renderer.graphics.Vertex;
import main.engine.objects.GameObject;

import org.lwjgl.opengl.GL31;

public class Box {
	
	Vector3 pos;
	Vector3 rot;
	Vector3 scale;
	
	public Box(Vector3 pos, Vector3 size, Vector3 rot){
		this.pos = pos;
		this.scale = size;
		this.rot = rot;
	}
	public Box(Vector3 pos ,float len, Vector3 rot){
		this.pos = pos;
		this.scale = new Vector3(len,len,len);
		this.rot = rot;
	}
	Mesh mesh = new Mesh(
			new Vertex[] { 
					//front
					new Vertex(new Vector3(-0.5f, 0.5f, 0.0f), new Vector3(1.0f, 0.0f, 0.0f)),
					new Vertex(new Vector3(0.5f, 0.5f, 0.0f), new Vector3(0.0f, 1.0f, 0.0f)),
					new Vertex(new Vector3(0.5f, -0.5f, 0.0f), new Vector3(0.0f, 0.0f, 1.0f)),
					new Vertex(new Vector3(-0.5f, -0.5f, 0.0f), new Vector3(0.5f, 0.0f, 0.5f)),
					
					//right
					new Vertex(new Vector3(0.5f,0.5f,0.0f), new Vector3(1.0f,0.0f,0.0f)),
					new Vertex(new Vector3(0.5f,-0.5f,0.0f), new Vector3(0.0f,1.0f,0.0f)),
					new Vertex(new Vector3(0.5f,0.5f,1.0f), new Vector3(0.0f,0.0f,1.0f)),
					new Vertex(new Vector3(0.5f,-0.5f,1.0f), new Vector3(0.5f,0.0f,0.5f)),
					
					//back
					new Vertex(new Vector3(-0.5f, 0.5f, 1.0f), new Vector3(1.0f, 0.0f, 0.0f)),
					new Vertex(new Vector3(0.5f, 0.5f, 1.0f), new Vector3(0.0f, 1.0f, 0.0f)),
					new Vertex(new Vector3(0.5f, -0.5f, 1.0f), new Vector3(0.0f, 0.0f, 1.0f)),
					new Vertex(new Vector3(-0.5f, -0.5f, 1.0f), new Vector3(0.5f, 0.0f, 0.5f)),
					
					//left
					new Vertex(new Vector3(-0.5f,0.5f,0.0f), new Vector3(1.0f,0.0f,0.0f)),
					new Vertex(new Vector3(-0.5f,-0.5f,0.0f), new Vector3(0.0f,1.0f,0.0f)),
					new Vertex(new Vector3(-0.5f,0.5f,1.0f), new Vector3(0.0f,0.0f,1.0f)),
					new Vertex(new Vector3(-0.5f,-0.5f,1.0f), new Vector3(0.5f,0.0f,0.5f))
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
					14, 15, 13
				});
	public GameObject object = new GameObject(pos, rot, scale, mesh);

}

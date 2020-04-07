package main.engine.renderer.primitives;

import main.engine.math.Vector2;
import main.engine.math.Vector3;
import main.engine.renderer.graphics.Material;
import main.engine.renderer.graphics.Mesh;
import main.engine.renderer.graphics.Vertex;
import main.engine.objects.GameObject;


public class Box {
	
	public GameObject gameObject;
	Vector3 pos, rot, scale;

	Mesh mesh = new Mesh(
			new Vertex[] { 
					//front
					new Vertex(new Vector3(-0.5f, 0.5f, -0.5f), new Vector3(), new Vector3(1.0f, 0.0f, 0.0f), new Vector2(1,1)),	//linksbovenvoor	
					new Vertex(new Vector3(0.5f, 0.5f, -0.5f), new Vector3(), new Vector3(0.0f, 1.0f, 0.0f), new Vector2(1,0)),		//rechtsbovenvoor
					new Vertex(new Vector3(0.5f, -0.5f, -0.5f), new Vector3(), new Vector3(0.0f, 0.0f, 1.0f), new Vector2(0,0)),	//rechtsondervoor
					new Vertex(new Vector3(-0.5f, -0.5f, -0.5f), new Vector3(), new Vector3(0.5f, 0.0f, 0.5f), new Vector2(0,1)),	//linksondervoor
				
					//right
					new Vertex(new Vector3(0.5f,0.5f,-0.5f), new Vector3(), new Vector3(1.0f,0.0f,0.0f), new Vector2(0,1)),			//rechtsbovenachter
					new Vertex(new Vector3(0.5f,-0.5f,-0.5f), new Vector3(), new Vector3(0.0f,1.0f,0.0f), new Vector2(0,1)),		//rechtsonderachter
					new Vertex(new Vector3(0.5f,0.5f,0.5f), new Vector3(), new Vector3(0.0f,0.0f,1.0f), new Vector2(0,1)),			//rechtsbovenvoor
					new Vertex(new Vector3(0.5f,-0.5f,0.5f), new Vector3(), new Vector3(0.5f,0.0f,0.5f), new Vector2(0,1)),			//rechtsondervoor
					
					//back
					new Vertex(new Vector3(-0.5f, 0.5f, 0.5f), new Vector3(), new Vector3(1.0f, 0.0f, 0.0f), new Vector2(0,1)),		//rechtsboven
					new Vertex(new Vector3(0.5f, 0.5f, 0.5f), new Vector3(), new Vector3(0.0f, 1.0f, 0.0f), new Vector2(0,1)),		//linksboven
					new Vertex(new Vector3(0.5f, -0.5f, 0.5f), new Vector3(), new Vector3(0.0f, 0.0f, 1.0f), new Vector2(0,1)),		//linksonder
					new Vertex(new Vector3(-0.5f, -0.5f, 0.5f), new Vector3(), new Vector3(0.5f, 0.0f, 0.5f), new Vector2(0,1)),	//rechtsonder
					
					//left
					new Vertex(new Vector3(-0.5f,0.5f,-0.5f), new Vector3(), new Vector3(1.0f,0.0f,0.0f), new Vector2(0,1)),		//linksbovenachter
					new Vertex(new Vector3(-0.5f,-0.5f,-0.5f), new Vector3(), new Vector3(0.0f,1.0f,0.0f), new Vector2(0,1)),		//linksonderachter
					new Vertex(new Vector3(-0.5f,0.5f,0.5f), new Vector3(), new Vector3(0.0f,0.0f,1.0f), new Vector2(0,1)),			//linksbovenvoor
					new Vertex(new Vector3(-0.5f,-0.5f,0.5f), new Vector3(), new Vector3(0.5f,0.0f,0.5f), new Vector2(0,1)),		//linksondervoor
					
					//bottom
					new Vertex(new Vector3(-0.5f, -0.5f, -0.5f), new Vector3(), new Vector3(1.0f, 0.0f, 0.0f), new Vector2(0,1)),	//rechtsachter
					new Vertex(new Vector3(-0.5f, -0.5f, 0.5f), new Vector3(), new Vector3(0.0f, 1.0f, 0.0f), new Vector2(0,1)),	//rechtsvoor
					new Vertex(new Vector3(0.5f, -0.5f, -0.5f), new Vector3(), new Vector3(0.0f, 0.0f, 1.0f), new Vector2(0,1)),	//linksachter
					new Vertex(new Vector3(0.5f, -0.5f, 0.5f), new Vector3(), new Vector3(0.5f, 0.0f, 0.5f), new Vector2(0,1)),		//linksvoor
					
					//top
					new Vertex(new Vector3(-0.5f, 0.5f, -0.5f), new Vector3(), new Vector3(1.0f, 0.0f, 0.0f), new Vector2(0,1)),	//linksachter
					new Vertex(new Vector3(-0.5f, 0.5f, 0.5f), new Vector3(), new Vector3(0.0f, 1.0f, 0.0f), new Vector2(0,1)),		//linksvoor
					new Vertex(new Vector3(0.5f, 0.5f, -0.5f), new Vector3(), new Vector3(0.0f, 0.0f, 1.0f), new Vector2(0,1)),		//rechtsachter
					new Vertex(new Vector3(0.5f, 0.5f, 0.5f), new Vector3(), new Vector3(0.5f, 0.0f, 0.5f), new Vector2(0,1))		//rechtsvoor
				},
			new int[] { 
					//front					
					1, 0, 3,
					1, 3, 2,
					//right
					4, 5, 6,
					6, 5, 7,
					
					//back
					8, 9, 10,
					10, 11, 8,
					
					//left
					12, 14, 15,
					12, 15, 13,
					
					//bottom
					18, 16, 17,
					18, 17, 19,
					
					//top
					20, 22, 23,
					20, 23, 21
				}, new Material("resources/textures/creeperhead.png"));
	
	
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

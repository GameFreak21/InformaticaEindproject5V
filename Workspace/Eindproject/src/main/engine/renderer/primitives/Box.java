package main.engine.renderer.primitives;

import main.engine.math.Time;
import main.engine.math.Vector2;
import main.engine.math.Vector3;
import main.engine.renderer.graphics.Material;
import main.engine.renderer.graphics.Mesh;
import main.engine.renderer.graphics.Vertex;
import main.engine.objects.GameObject;


public class Box extends GameObject{

	public static Mesh box_mesh = new Mesh(
			new Vertex[] { 
					//front
					new Vertex(new Vector3(-0.5f, 1, -0.5f), new Vector3(), new Vector3(1.0f, 0.0f, 0.0f), new Vector2(1,1)),	//rechtsboven	
					new Vertex(new Vector3(0.5f, 1, -0.5f), new Vector3(), new Vector3(0.0f, 1.0f, 0.0f), new Vector2(1,0)),		//linksboven
					new Vertex(new Vector3(0.5f, 0f, -0.5f), new Vector3(), new Vector3(0.0f, 0.0f, 1.0f), new Vector2(0,0)),	//linksonder
					new Vertex(new Vector3(-0.5f, 0f, -0.5f), new Vector3(), new Vector3(0.5f, 0.0f, 0.5f), new Vector2(0,1)),	//rechtsonder
				
					//right
					new Vertex(new Vector3(0.5f, 1,-0.5f), new Vector3(), new Vector3(1.0f,0.0f,0.0f), new Vector2(0,1)),			//rechtsbovenachter
					new Vertex(new Vector3(0.5f, 0,-0.5f), new Vector3(), new Vector3(0.0f,1.0f,0.0f), new Vector2(0,1)),		//rechtsonderachter
					new Vertex(new Vector3(0.5f, 1,0.5f), new Vector3(), new Vector3(0.0f,0.0f,1.0f), new Vector2(0,1)),			//rechtsbovenvoor
					new Vertex(new Vector3(0.5f, 0f,0.5f), new Vector3(), new Vector3(0.5f,0.0f,0.5f), new Vector2(0,1)),			//rechtsondervoor
					
					//back
					new Vertex(new Vector3(-0.5f, 1, 0.5f), new Vector3(), new Vector3(1.0f, 0.0f, 0.0f), new Vector2(0,1)),		//linksboven
					new Vertex(new Vector3(0.5f, 1, 0.5f), new Vector3(), new Vector3(0.0f, 1.0f, 0.0f), new Vector2(0,1)),		//rechtsboven
					new Vertex(new Vector3(0.5f, 0, 0.5f), new Vector3(), new Vector3(0.0f, 0.0f, 1.0f), new Vector2(0,1)),		//rechtsonder
					new Vertex(new Vector3(-0.5f, 0, 0.5f), new Vector3(), new Vector3(0.5f, 0.0f, 0.5f), new Vector2(0,1)),	//linksonder
					
					//left
					new Vertex(new Vector3(-0.5f, 1,-0.5f), new Vector3(), new Vector3(1.0f,0.0f,0.0f), new Vector2(0,1)),		//linksbovenachter
					new Vertex(new Vector3(-0.5f, 0,-0.5f), new Vector3(), new Vector3(0.0f,1.0f,0.0f), new Vector2(0,1)),		//linksonderachter
					new Vertex(new Vector3(-0.5f, 1,0.5f), new Vector3(), new Vector3(0.0f,0.0f,1.0f), new Vector2(0,1)),			//linksbovenvoor
					new Vertex(new Vector3(-0.5f, 0,0.5f), new Vector3(), new Vector3(0.5f,0.0f,0.5f), new Vector2(0,1)),		//linksondervoor
					
					//bottom
					new Vertex(new Vector3(-0.5f, 0, -0.5f), new Vector3(), new Vector3(1.0f, 0.0f, 0.0f), new Vector2(0,1)),	//rechtsachter
					new Vertex(new Vector3(-0.5f, 0, 0.5f), new Vector3(), new Vector3(0.0f, 1.0f, 0.0f), new Vector2(0,1)),	//rechtsvoor
					new Vertex(new Vector3(0.5f, 0, -0.5f), new Vector3(), new Vector3(0.0f, 0.0f, 1.0f), new Vector2(0,1)),	//linksachter
					new Vertex(new Vector3(0.5f, 0, 0.5f), new Vector3(), new Vector3(0.5f, 0.0f, 0.5f), new Vector2(0,1)),		//linksvoor
					
					//top
					new Vertex(new Vector3(-0.5f, 1, -0.5f), new Vector3(), new Vector3(1.0f, 0.0f, 0.0f), new Vector2(0,1)),	//linksachter
					new Vertex(new Vector3(-0.5f, 1, 0.5f), new Vector3(), new Vector3(0.0f, 1.0f, 0.0f), new Vector2(0,1)),		//linksvoor
					new Vertex(new Vector3(0.5f, 1, -0.5f), new Vector3(), new Vector3(0.0f, 0.0f, 1.0f), new Vector2(0,1)),		//rechtsachter
					new Vertex(new Vector3(0.5f, 1, 0.5f), new Vector3(), new Vector3(0.5f, 0.0f, 0.5f), new Vector2(0,1))		//rechtsvoor
				},
			new int[] { 
					//front					
					0, 1, 3, 
					1, 2, 3, 
					//right
					4, 6, 5,
					6, 7, 5,
					
					//back
					9, 8, 11, 
					11, 10, 9,
					
					//left
					14, 12, 13,
					14, 13, 15,
					
					//bottom
					16, 18, 19,
					16, 19, 17,
					
					//top
					22, 20, 21,
					22, 21, 23,
				}, new Material("resources/textures/box.jpg"));
	
	public Box(Vector3 position, Vector3 rotation, Vector3 scale, float mass, Vector3 speed, boolean collisions) {
		super(position, rotation, scale, box_mesh, mass, speed, collisions);
	}
	
	boolean flip = false;
	@Override
	public void update() {
		if(position.y > 10 || position.y < -10)
			flip = !flip;
		if(!flip)
			position = Vector3.add(position, new Vector3(0,(float)(-5*Time.deltaTime),0));
		else
			position = Vector3.subtract(position, new Vector3(0,(float)(-5*Time.deltaTime),0));
	}
}

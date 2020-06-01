package main.engine.objects;

import org.lwjgl.glfw.GLFW;

import main.engine.math.Time;
import main.engine.math.Vector3;
import main.engine.physics.Collider;
import main.engine.physics.Rigidbody;
import main.engine.renderer.graphics.Mesh;
import main.engine.physics.Rigidbody;

public class GameObject extends Rigidbody {
	public Vector3 position, rotation, scale, collisionMesh, speed;
	public Mesh mesh;
	public float mass;
	public double valtijd;
	private int a = 0;
	private boolean collisions = false;

	double time;

	public GameObject(Vector3 position, Vector3 rotation, Vector3 scale, Mesh mesh, float mass, Vector3 speed, boolean collisions) {
		super(mass);
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.mesh = mesh;
		this.mass = mass;
		this.speed = speed;
		this.valtijd = Time.Time;
		this.collisions = collisions;
		if (collisions) 
			Collider.CreateCollider(this.mesh.positionData, this.scale, this.position);
			
		
//		if ((this.scale.x == 0.5f) && (this.scale.y == 2) && (this.scale.z == 0.5f)) { 
//			 float a = 0f;
////			System.out.println("1 == true");
//		}
//		else if (this.mesh == MAIN_TEST.planeMesh) {
//			float a = 0f;
////			System.out.println("2 == true");
//		}
//		else if (this.scale.x == 500f) {
//			float a = 0f;
//		}
//		else {
//			System.out.println("3 == true");
//			Collider.CreateCollider(GameObject.this);}
	}
	// temp voor test
	public void update() {
		time = GLFW.glfwGetTime();

		// position.x = (float) Math.sin(time)/2.0f;
		rotation = new Vector3(0, (float) time * 60, (float) time * 20);
		// scale = new Vector3((float) Math.sin(time), (float) Math.sin(time), (float)
		// Math.sin(time));
	}
//	
//	public void destroy() {
//		this.mesh.destroy();
//	}
}

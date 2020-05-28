package main.engine.objects;

import org.lwjgl.glfw.GLFW;

import main.engine.math.Vector3;
import main.engine.physics.Rigidbody;
import main.engine.renderer.graphics.Mesh;
import main.engine.physics.Rigidbody;

public class GameObject extends Rigidbody {
	public Vector3 position, rotation, scale, collisionMesh, speed;
	public Mesh mesh;
	float mass;

	double time;

	public GameObject(Vector3 position, Vector3 rotation, Vector3 scale, Mesh mesh, float mass, Vector3 speed) {
		super(mass);
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.mesh = mesh;
		this.mass = mass;
		this.speed = speed;
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

package main.engine.physics;

import main.engine.math.Time;
import main.engine.math.Vector3;
import main.engine.objects.GameObject;

public class Rigidbody {
	float mass;
	
	public Rigidbody() {
		this.mass = 1.0f;
	}
	public Rigidbody(float mass) {
		this.mass = mass;
	}
	
	public void applyGravity(GameObject obj) {
		obj.position = Vector3.add(obj.position, new Vector3(0,(float) (-1*mass*Time.deltaTime), 0));
	}
}

package main.engine.physics;

import main.engine.math.Time;
import main.engine.math.Vector3;
import main.engine.objects.GameObject;

public class Rigidbody {
	float mass;
	private final float MAX_VELOCITY = 7f;
	
	public Rigidbody() {
		this.mass = 1.0f;
	}
	public Rigidbody(float mass) {
		this.mass = mass;
	}
	
	public void applyGravity(GameObject obj) {
//		if (obj.position.y > 0.1f) {
//			if(Math.abs(obj.speed.y) < MAX_VELOCITY)
//				obj.speed.y = (float) (-1*mass*Time.deltaTime + obj.speed.y);
//			if ((obj.position.y + obj.speed.y) > 0) 
//				obj.position = Vector3.add(obj.position, new Vector3(0, obj.speed.y*(float)Time.deltaTime, 0));
//			else
//				obj.position = Vector3.add(obj.position, new Vector3(0, -(obj.position.y)+0.1f, 0));
//		}
		
		if (obj.position.y > 0.1f) {
			obj.valtijd = Time.Time;
			obj.speed.y = (float) (-1*mass*obj.valtijd);
			if ((obj.position.y + obj.speed.y) > 0) 
				obj.position = Vector3.add(obj.position, new Vector3(0, obj.speed.y, 0));
			else
				obj.position = Vector3.add(obj.position, new Vector3(0, -(obj.position.y)+0.1f, 0));
		}
		else
			obj.valtijd = 0;
		

	}
}

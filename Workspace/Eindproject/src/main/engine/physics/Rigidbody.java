package main.engine.physics;

import main.engine.MAIN_TEST;
import main.engine.math.Time;
import main.engine.math.Vector3;
import main.engine.objects.Camera;
import main.engine.objects.GameObject;
import main.engine.objects.PlayerCamera;

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
	public static void applyGravity(Camera camera) {
//		if (obj.position.y > 0.1f) {
//			if(Math.abs(camera.speed.y) < MAX_VELOCITY)
//				camera.speed.y = (float) (-1*mass*Time.deltaTime + camera.speed.y);
//			if ((camera.position.y + camera.speed.y) > 0) 
//				camera.position = Vector3.add(camera.position, new Vector3(0, camera.speed.y*(float)Time.deltaTime, 0));
//			else
//				camera.position = Vector3.add(camera.position, new Vector3(0, -(camera.position.y)+0.1f, 0));
//		}
		
		if (camera.position.y > 1f) {
			camera.valtijd = Time.Time;
			System.out.println(camera.valtijd);
			camera.speed.y = (float) (-1*camera.mass*camera.valtijd);
			if ((camera.position.y + camera.speed.y) > 0) 
				camera.position = Vector3.add(camera.position, new Vector3(0, camera.speed.y, 0));
			else
				camera.position = Vector3.add(camera.position, new Vector3(0, -(camera.position.y)+0.1f, 0));
		}
		else
			camera.valtijd = 0;
		

	}
}

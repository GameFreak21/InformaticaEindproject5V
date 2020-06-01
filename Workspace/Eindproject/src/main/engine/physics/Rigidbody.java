package main.engine.physics;

import main.engine.math.Time;
import main.engine.math.Vector3;
import main.engine.objects.Camera;
import main.engine.objects.GameObject;
import main.game.MAIN_GAME;

public class Rigidbody {
	float mass;

	private static final float MAX_VELOCITY = 70f;
	private static final float GRAVITY = -10;
	
	public static Vector3 Fz = new Vector3(), zwaartekracht = new Vector3(), jump = new Vector3(),
			forces = new Vector3();
	public static boolean floor = false, gravityCollide = false;

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
			obj.valtijd += Time.deltaTime;
			if (-obj.speed.y < MAX_VELOCITY)
				obj.speed.y = (float) (GRAVITY * mass * obj.valtijd);
			if ((obj.position.y + obj.speed.y) > 0)
				obj.position = Vector3.add(obj.position, new Vector3(0, obj.speed.y, 0));
			else
				obj.position = Vector3.add(obj.position, new Vector3(0, -(obj.position.y) + 0.1f, 0));
		} else
			obj.valtijd = 0;

	}

	public static Vector3 applyGravity(Camera camera) {
		Fz = new Vector3();
//		if (obj.position.y > 0.1f) {
//			if(Math.abs(camera.speed.y) < MAX_VELOCITY)
//				camera.speed.y = (float) (-1*mass*Time.deltaTime + camera.speed.y);
//			if ((camera.position.y + camera.speed.y) > 0) 
//				camera.position = Vector3.add(camera.position, new Vector3(0, camera.speed.y*(float)Time.deltaTime, 0));
//			else
//				camera.position = Vector3.add(camera.position, new Vector3(0, -(camera.position.y)+0.1f, 0));
//		}
		if (camera.position.y > 2f) {
			camera.valtijd += Time.deltaTime;
			if (-camera.speed.y < MAX_VELOCITY)
				Fz.y = (float) (GRAVITY * camera.mass * camera.valtijd);
//				if (camera.position.y + Fz.y < 2)
//					Fz.y = 0.1f;
			// floor = false;
		} else {
			camera.valtijd = 0;
			gravityCollide = true;
		}
		if ((Collider.CheckCollision(MAIN_GAME.playerCollider, Collider.allCollider, Vector3.add(camera.position, Fz),
				Collider.allGameObjectPositions))
				|| (Collider.CheckCollision(MAIN_GAME.playerCollider, Collider.allCollider,
						new Vector3(camera.position.x, camera.position.y - 1, camera.position.z),
						Collider.allGameObjectPositions))) {
			Fz = new Vector3();
			gravityCollide = true;
		}
//			else 
//				gravityCollide = false;

		return Fz;

	}

	public static void applyForces(Camera camera) {
//		zwaartekracht = new Vector3();
//		jump = new Vector3();
//		forces = new Vector3();
//		zwaartekracht = applyGravity(camera);
//		jump = camera.speed;
//		forces = Vector3.add(jump, zwaartekracht);
//		camera.speed = Vector3.add(camera.speed,  forces);
//		camera.position = Vector3.add(camera.position, forces);
//		if (camera.position.y <= 0)
//			camera.position.y = 0;
		zwaartekracht = applyGravity(camera);
//		System.out.println(zwaartekracht.y);
//		jump = camera.speed;
//		jump.y =camera.speed.y * (float) Time.deltaTime;
////		System.out.println("jump.y " + jump.y);
//		forces = new Vector3();
//		forces.y = (jump.y + zwaartekracht.y);

		camera.speed.y += zwaartekracht.y;
//		if(camera.speed.y < 0)
//			camera.speed.y = 0;

		// System.out.println(forces.y);
		if (!Collider.CheckCollision(MAIN_GAME.playerCollider, Collider.allCollider,
				new Vector3(camera.position.x, ((camera.position.y - 1f) + forces.y), camera.position.z),
				Collider.allGameObjectPositions)) {
			if (camera.position.y + camera.speed.y * (float) Time.deltaTime > 2f)
				camera.position.y += camera.speed.y * (float) Time.deltaTime;
			else {
				camera.speed.y = 0;
				gravityCollide = true;
			}
		} else {
			camera.speed.y = 0;
			gravityCollide = true;
		}

	}
}

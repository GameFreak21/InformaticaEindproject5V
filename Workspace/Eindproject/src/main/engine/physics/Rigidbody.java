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
		if (camera.position.y > 2f) {
			camera.valtijd += Time.deltaTime;
			if (-camera.speed.y < MAX_VELOCITY)
				Fz.y = (float) (GRAVITY * camera.mass * camera.valtijd);
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
		return Fz;

	}

	public static void applyForces(Camera camera) {
		zwaartekracht = applyGravity(camera);

		camera.speed.y += zwaartekracht.y;

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

package main.engine.objects;

import org.lwjgl.glfw.GLFW;

import main.engine.MAIN_TEST;
import main.engine.io.Input;
import main.engine.math.Time;
import main.engine.math.Vector3;
import main.engine.physics.Collider;
import main.engine.physics.Rigidbody;

public class PlayerCamera extends Camera {
	private float oldMouseX, oldMouseY;
	private float moveSpeed = 10f, sensitivity = 0.15f;
	private Vector3 oldPosition = new Vector3();
	private int doubleChecker = 0;

	public PlayerCamera(Vector3 position, Vector3 rotation) {
		super(position, rotation);
		this.position = position;
		this.rotation = rotation;
	}

	@Override
	public void update() {
		float dx = (float) (Input.mouseX - oldMouseX);
		float dy = (float) (Input.mouseY - oldMouseY);

		oldMouseX = (float) Input.mouseX;
		oldMouseY = (float) Input.mouseY;

		this.valtijd = Time.deltaTime;
		if (!lockCam) {
//			 ((oldPosition.x != position.x) || (oldPosition.y != position.y) || (oldPosition.z != position.z)

			boolean running = Input.keyDown(GLFW.GLFW_KEY_LEFT_CONTROL);
			if (running) {
				moveSpeed = 25f;
			} else {
				moveSpeed = 10f;
			}

			if (rotation.x <= (90f - dy) && rotation.x >= (-90f - dy))
				rotation = Vector3.add(rotation, new Vector3(dy * sensitivity, 0, 0));
			else if (rotation.x > (90f - dy))
				rotation.x = 90f;
			else
				rotation.x = -90f;
			rotation = Vector3.add(rotation, new Vector3(0, dx * sensitivity, 0));

			float x = (float) (Math.cos(Math.toRadians(rotation.y)) * moveSpeed * Time.deltaTime);
			float z = (float) (Math.sin(Math.toRadians(rotation.y)) * moveSpeed * Time.deltaTime);
			// System.out.println(position.z);
			System.out.println("collision " + Collider.CheckCollision(MAIN_TEST.playerCollider, Collider.allCollider,
					new Vector3(position.x, position.y - 1, position.z), Collider.allGameObjectPositions));
			System.out.println("collide " + Rigidbody.gravityCollide);

			Vector3 tempPos = new Vector3();

			if (!Collider.CheckCollision(MAIN_TEST.playerCollider, Collider.allCollider,
					new Vector3(position.x, position.y - 1, position.z), Collider.allGameObjectPositions)) {
				oldPosition = position;
				doubleChecker = 0;
				tempPos = position;
				// if (Rigidbody.gravityCollide) {
				if (Input.keyDown(GLFW.GLFW_KEY_A))
					tempPos = Vector3.add(tempPos, new Vector3(-x, 0, -z));
				if (Input.keyDown(GLFW.GLFW_KEY_D))
					tempPos = Vector3.add(tempPos, new Vector3(x, 0, z));
				if (Input.keyDown(GLFW.GLFW_KEY_S))
					tempPos = Vector3.add(tempPos, new Vector3(-z, 0, x));
				if (Input.keyDown(GLFW.GLFW_KEY_W))
					tempPos = Vector3.add(tempPos, new Vector3(z, 0, -x));
				// }

				if (!Collider.CheckCollision(MAIN_TEST.playerCollider, Collider.allCollider,
						new Vector3(tempPos.x, tempPos.y - 1, tempPos.z), Collider.allGameObjectPositions)) {
					position = tempPos;
				}
				// JUMPING @ GRAVITY ETC

				if (Input.keyDown(GLFW.GLFW_KEY_SPACE)) {

					if (Rigidbody.gravityCollide) { // checken of je niet vanuit de lucht afzet
						Rigidbody.gravityCollide = false;
						this.speed.y = 15;
						Rigidbody.applyForces(this);
					}
					// position = Vector3.add(position, new Vector3(0, (float) (moveSpeed *
					// Time.deltaTime), 0));
				}

				// if (Input.keyDown(GLFW.GLFW_KEY_LEFT_SHIFT))
				// position = Vector3.add(position, new Vector3(0, (float) (-moveSpeed *
				// Time.deltaTime), 0));
//				if (Input.keyDown(GLFW.GLFW_KEY_A) || Input.keyDown(GLFW.GLFW_KEY_D) || Input.keyDown(GLFW.GLFW_KEY_S) || Input.keyDown(GLFW.GLFW_KEY_W) || Input.keyDown(GLFW.GLFW_KEY_SPACE))
//					Rigidbody.gravityCollide = false;

			} else {
				position = oldPosition;
				doubleChecker++;
			}
			if (!Collider.CheckCollision(MAIN_TEST.playerCollider, Collider.allCollider,
					new Vector3(position.x, position.y - 2, position.z), Collider.allGameObjectPositions))
				Rigidbody.gravityCollide = false;
			if (doubleChecker > 1) {
				position.y += 0.01f;
				Rigidbody.gravityCollide = true;
			}

//			System.out.println("loop");
//			System.out.println(Rigidbody.applyGravity(this).y);
			if (!Rigidbody.gravityCollide)
				Rigidbody.applyForces(this);
			MAIN_TEST.player.position = new Vector3(position.x, position.y - 3, position.z);
//			System.out.println("camera.x " + position.x);
//			System.out.println("camera.y " + position.y);
//			System.out.println("camera.z " + position.z);
		}

	}

	@Override
	public void lock(Boolean lock) {
		this.lockCam = (lock ? true : false);
	}
}

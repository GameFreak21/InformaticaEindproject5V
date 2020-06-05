package main.engine.objects;

import org.lwjgl.glfw.GLFW;

import main.engine.io.Input;
import main.engine.math.Time;
import main.engine.math.Vector3;
import main.engine.physics.Collider;
import main.engine.physics.Rigidbody;
import main.game.MAIN_GAME;

public class PlayerCamera extends Camera {
	private float oldMouseX, oldMouseY;
	private float moveSpeed = 10f, sensitivity = 0.15f;
	private Vector3 oldPosition = new Vector3();
	private int doubleChecker = 0;
	private int lastKey;
	//private float timer;

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

		//timer += Time.deltaTime;

		this.valtijd = Time.deltaTime;
		if (!lockCam) {

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
			Vector3 tempPos = new Vector3();

			if (!Collider.CheckCollision(MAIN_GAME.playerCollider, Collider.allCollider,
					new Vector3(position.x, position.y - 1, position.z), Collider.allGameObjectPositions)) {
				oldPosition = position;
				doubleChecker = 0;
				tempPos = position;

				if (Rigidbody.gravityCollide) {
					if (Input.keyDown(GLFW.GLFW_KEY_A)) {
						tempPos = Vector3.add(tempPos, new Vector3(-x, 0, -z));
						lastKey = GLFW.GLFW_KEY_A;
						//timer = 0;
					}
					if (Input.keyDown(GLFW.GLFW_KEY_D)) {
						tempPos = Vector3.add(tempPos, new Vector3(x, 0, z));
						lastKey = GLFW.GLFW_KEY_D;
						//timer = 0;
					}
					if (Input.keyDown(GLFW.GLFW_KEY_S)) {
						tempPos = Vector3.add(tempPos, new Vector3(-z, 0, x));
						lastKey = GLFW.GLFW_KEY_S;
						//timer = 0;
					}
					if (Input.keyDown(GLFW.GLFW_KEY_W)) {
						tempPos = Vector3.add(tempPos, new Vector3(z, 0, -x));
						lastKey = GLFW.GLFW_KEY_W;
						//timer = 0;
					}

					if (!Collider.CheckCollision(MAIN_GAME.playerCollider, Collider.allCollider,
							new Vector3(tempPos.x, tempPos.y - 1, tempPos.z), Collider.allGameObjectPositions)) {
						position = tempPos;
					}
				} else {
					if (lastKey != 0) {
						if (lastKey == GLFW.GLFW_KEY_A)
							tempPos = Vector3.add(tempPos, new Vector3(-x, 0, -z));
						else if (lastKey == GLFW.GLFW_KEY_D)
							tempPos = Vector3.add(tempPos, new Vector3(x, 0, z));
						else if (lastKey == GLFW.GLFW_KEY_S)
							tempPos = Vector3.add(tempPos, new Vector3(-z, 0, x));
						else if (lastKey == GLFW.GLFW_KEY_W)
							tempPos = Vector3.add(tempPos, new Vector3(z * 0.5f, 0, -x * 0.5f));
						if (!Collider.CheckCollision(MAIN_GAME.playerCollider, Collider.allCollider,
								new Vector3(tempPos.x, tempPos.y - 1, tempPos.z), Collider.allGameObjectPositions)) {
							position = tempPos;
						}
					}
				}
				// JUMPING @ GRAVITY ETC

				if (Input.keyDown(GLFW.GLFW_KEY_SPACE)) {

					if (Rigidbody.gravityCollide) { // checken of je niet vanuit de lucht afzet
						Rigidbody.gravityCollide = false;
						this.speed.y = 10;
						Rigidbody.applyForces(this);
					}
				}


			} else {
				position = oldPosition;
				doubleChecker++;
			}
			if (!Collider.CheckCollision(MAIN_GAME.playerCollider, Collider.allCollider,
					new Vector3(position.x, position.y - 2, position.z), Collider.allGameObjectPositions))
				Rigidbody.gravityCollide = false;
			if (doubleChecker > 1) {
				position.y += 0.01f;
				Rigidbody.gravityCollide = true;
			}

			if (!Rigidbody.gravityCollide)
				Rigidbody.applyForces(this);
			MAIN_GAME.player.position = new Vector3(position.x, position.y - 3, position.z);
		}

	}

	@Override
	public void lock(Boolean lock) {
		this.lockCam = (lock ? true : false);
	}
}

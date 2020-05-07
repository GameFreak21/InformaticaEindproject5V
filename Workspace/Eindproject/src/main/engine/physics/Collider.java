package main.engine.physics;

import main.engine.math.Vector3;
import main.engine.objects.GameObject;

public class Collider {
	
	public static boolean CheckCollision(GameObject object1, GameObject object2) {
		boolean collision;
		Vector3 position1 = object1.position;
		Vector3 position2 = object2.position;
		Vector3 scale1 = object1.scale;
		Vector3 scale2 = object2.scale;
		
		boolean collisionX = position1.x + scale1.x >= position2.x && position2.x + scale2.x >= position1.x;
		boolean collisionY = position1.y + scale1.y >= position2.y && position2.y + scale2.y >= position1.y;
		boolean collisionZ = position1.z + scale1.z >= position2.z && position2.z + scale2.z >= position1.z;
		if (collisionX && collisionY && collisionZ) {
			collision = true;
		}
		else {
			collision = false;
		}
		System.out.println("position1.x "+position1.x);
		System.out.println("scale1.x" + scale1.x);
		System.out.println("position2.x" + position2.x);
		System.out.println("collisionX"+ collisionX);
		System.out.println("collisionY" + collisionY);
		System.out.println("CollisionZ" + collisionZ);
		return collision;
	}
}

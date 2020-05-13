package main.engine.physics;

import main.engine.math.Vector3;
import main.engine.objects.GameObject;

public class Collider {
	
	float minX, maxX;
	float minY, maxY;
	float minZ, maxZ;
	
	public Collider(float[] positionData) {
		for (int i = 0; i < (positionData.length /3); i++) {
			float posX = positionData[(i*3)];
			float posY = positionData[(i*3)+1];
			float posZ = positionData[(i*3)+2];
			if (posX > maxX) {
				maxX = posX;
			} else if (posX < minX) {
				minX = posX;
			}
			if (posY > maxY) {
				maxY = posY;
			} else if (posY < minY) {
				minY = posY;
			}
			if (posZ > maxZ) {
				maxZ = posZ;
			} else if (posZ < minZ) {
				minZ = posZ;
			}
		}	
	}
	
	GameObject object1;
	GameObject object2;
	static boolean collision, xcol, ycol, zcol;
	public static boolean CheckCollision(Collider col1, Collider col2) {
		float Xm1 = (col1.maxX + col1.minX) / 2;
		float Ym1 = (col1.maxY + col1.minY) / 2;
		float Zm1 = (col1.maxZ + col1.minZ) / 2;
		float distX1 = Math.abs(Xm1) - Math.abs(col1.maxX);
		float distY1 = Math.abs(Ym1) - Math.abs(col1.maxY);
		float distZ1 = Math.abs(Zm1) - Math.abs(col1.maxZ);
		Vector3 position1 = new Vector3(Xm1, Ym1, Zm1);
		Vector3 scale1 = new Vector3(distX1, distY1, distZ1);
		
		float Xm2 = (col2.maxX + col2.minX) / 2;
		float Ym2 = (col2.maxY + col2.minY) / 2;
		float Zm2 = (col2.maxZ + col2.minZ) / 2;
		float distX2 = Math.abs(Xm2) - Math.abs(col2.maxX);
		float distY2 = Math.abs(Ym2) - Math.abs(col2.maxY);
		float distZ2 = Math.abs(Zm2) - Math.abs(col2.maxZ);
		
		Vector3 position2 = new Vector3(Xm2, Ym2, Zm2);
		Vector3 scale2 = new Vector3(distX2, distY2, distZ2);
		if (position1.x + scale1.x >= position2.x - scale2.x || position1.x - scale1.x >= position2.x + scale2.x || position2.x + scale2.x >= position1.x - scale1.x || position2.x -scale2.x >= position1.x + scale1.x) {
			xcol = true;
			if (position1.y + scale1.y >= position2.y - scale2.y || position1.y - scale1.y >= position2.y + scale2.y || position2.y + scale2.y >= position1.y - scale1.y || position2.y -scale2.y >= position1.y + scale1.y) {
				ycol = true;
				if (position1.z + scale1.z >= position2.z - scale2.z || position1.z - scale1.z >= position2.z + scale2.z || position2.z + scale2.z >= position1.z - scale1.z || position2.z -scale2.z >= position1.z + scale1.z) {
					collision = true;
					zcol = true;
				}
			}
		}
//		boolean collisionX = position1.x + scale1.x >= position2.x && position2.x + scale2.x >= position1.x;
//		boolean collisionY = position1.y + scale1.y >= position2.y && position2.y + scale2.y >= position1.y;
//		boolean collisionZ = position1.z + scale1.z >= position2.z && position2.z + scale2.z >= position1.z;
//		if (collisionX && collisionY && collisionZ) {
//			collision = true;
//		}
//		else {
//			collision = false;
//		}
//		System.out.println("position1.x "+position1.x);
//		System.out.println("scale1.x" + scale1.x);
//		System.out.println("collisionX"+ collisionX);
//		System.out.println("collisionY" + collisionY);
//		System.out.println("CollisionZ" + collisionZ);
//		System.out.println("collision" + collision);
//		System.out.println("xcol" +(position1.x - scale1.x >= position2.x + scale2.x));
//		System.out.println("ycol" + ycol);
//		System.out.println("zcol" + zcol);
		return collision;
	}
}

package main.engine.physics;

import main.engine.math.Vector3;
import main.engine.objects.GameObject;

public class Collider {
	GameObject object1;
	GameObject object2;
	static boolean collision;
	public static boolean CheckCollision(float [] positionData1, float[] positionData2) {
		float maxX1 = 0, minX1 = 0, maxY1 = 0, minY1 = 0, maxZ1 = 0, minZ1 = 0, maxX2 = 0, minX2 = 0, maxY2 = 0, minY2 = 0, maxZ2 = 0, minZ2 = 0;
		float [] position1x = new float [positionData1.length / 3], position1y = new float [positionData1.length / 3], position1z = new float [positionData1.length / 3];
		float [] position2x = new float [positionData2.length / 3], position2y = new float [positionData2.length / 3], position2z = new float [positionData2.length / 3];
		for (int i = 0; i < positionData1.length; i++) {
			if (i%3 ==0) {
				position1x[i/3] = positionData1[i];
			}
			else if ((i - 1)%3 == 0) {
				position1y[(i-1)/3] = positionData1[i];
			}
			else if ((i - 2)%3 == 0) {
				position1z[(i-2)/3] = positionData1[i];
			}
		}
		for (int i = 0; i < positionData2.length; i++) {
			if (i%3 ==0) {
				position2x[i/3] = positionData2[i];
			}
			else if ((i - 1)%3 == 0) {
				position2y[(i-1)/3] = positionData2[i];
			}
			else if ((i - 2)%3 == 0) {
				position2z[(i-2)/3] = positionData2[i];
			}
		}

		for (int x = 0; x < position1x.length; x++) {
			float a = position1x[x];
			if (a > maxX1) {
				maxX1 = a;
			}
			else if (a < minX1) {
				minX1 = a;
			}
			
		}
		for (int x = 0; x < position1y.length; x++) {
			float a = position1y[x];
			if (a > maxY1) {
				maxY1 = a;
			}
			else if (a < minY1) {
				minY1 = a;
			}
		}
		for (int x = 0; x < position1z.length; x++) {
			float a = position1z[x];
			if (a > maxZ1) {
				maxZ1 = a;
			}
			else if (a < minZ1) {
				minZ1 = a;
			}
		}
		for (int x = 0; x < position2x.length; x++) {
			float a = position2x[x];
			if (a > maxX2) {
				maxX2 = a;
			}
			else if (a < minX2) {
				minX2 = a;
			}
		}
		for (int x = 0; x < position2y.length; x++) {
			float a = position2y[x];
			if (a > maxY2) {
				maxY2 = a;
			}
			else if (a < minY2) {
				minY2 = a;
			}
		}
		for (int x = 0; x < position2z.length; x++) {
			float a = position2z[x];
			if (a > maxZ2) {
				maxZ2 = a;
			}
			else if (a < minZ2) {
				minZ2 = a;
			}
		}
		float Xm1 = (maxX1 + minX1) / 2;
		float Ym1 = (maxY1 + minY1) / 2;
		float Zm1 = (maxZ1 + minZ1) / 2;
		float distX1 = Math.abs(Xm1) - Math.abs(maxX1);
		float distY1 = Math.abs(Ym1) - Math.abs(maxY1);
		float distZ1 = Math.abs(Zm1) - Math.abs(maxZ1);
		Vector3 position1 = new Vector3(Xm1, Ym1, Zm1);
		Vector3 scale1 = new Vector3(distX1, distY1, distZ1);
		float Xm2 = (maxX2 + minX2) / 2;
		float Ym2 = (maxY2 + minY2) / 2;
		float Zm2 = (maxZ2 + minZ2) / 2;
		float distX2 = Math.abs(Xm2) - Math.abs(maxX2);
		float distY2 = Math.abs(Ym2) - Math.abs(maxY2);
		float distZ2 = Math.abs(Zm2) - Math.abs(maxZ2);
		Vector3 position2 = new Vector3(Xm2, Ym2, Zm2);
		Vector3 scale2 = new Vector3(distX2, distY2, distZ2);
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
		System.out.println("collisionX"+ collisionX);
		System.out.println("collisionY" + collisionY);
		System.out.println("CollisionZ" + collisionZ);
		System.out.println("collision" + collision);
		return collision;
	}
}

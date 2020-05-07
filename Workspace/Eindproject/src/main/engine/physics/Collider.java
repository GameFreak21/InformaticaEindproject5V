package main.engine.physics;

import java.util.Arrays;
import java.util.Collections;


import main.engine.math.Vector3;
import main.engine.objects.GameObject;

public class Collider {
	
	public static boolean CheckCollision(float [] positionData1, float[] positionData2) {
		float maxX = 0;
		float minX = 0;
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
			if (a > maxX) {
				maxX = a;
			}
			else if (a < minX) {
				minX = a;
			}
			
		}
		System.out.println("minx " + minX);
		System.out.println("maxX " + maxX);
		return true;
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
//		System.out.println("position2.x" + position2.x);
//		System.out.println("collisionX"+ collisionX);
//		System.out.println("collisionY" + collisionY);
//		System.out.println("CollisionZ" + collisionZ);
//		return collision;
	}
}

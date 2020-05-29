package main.engine.physics;

import java.util.ArrayList;

import main.engine.math.Vector3;
import main.engine.objects.GameObject;

public class Collider {
	
	float minX, maxX;
	float minY, maxY;
	float minZ, maxZ;
	static boolean collision = false;	
	public static ArrayList<Collider> allCollider = new ArrayList<Collider>();
	public static ArrayList<Vector3> allGameObjectPositions = new ArrayList<Vector3>();
	//public static Collider[] allCollider = new Collider[1];
//	public static Vector3[] allGameObjectPositions = new Vector3[1];
	private static int i = 0;
	

	
	public Collider(float[] positionData, Vector3 schaal) {
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
		maxX = schaal.x * maxX;
		maxY = schaal.y * maxY;
		maxZ = schaal.z * maxZ;
		minX = schaal.x * minX;
		minY = schaal.y * minY;
		minZ = schaal.z * minZ;
	}
	public static void CreateCollider(GameObject object) {
		float[] positionData = object.mesh.positionData;
		Vector3 schaal = object.scale;
		Collider collider = new Collider(positionData, schaal);
		allCollider.add(collider);
		allGameObjectPositions.add(object.position);
		
//		if ((i < allCollider.length) && (allCollider[i] != null)) { 
//			allCollider[i] = collider;		
//			allGameObjectPositions[i] = object.position;
//		}
//		else {
//			
//
//			Collider[] copy = new Collider[i + 1];
//			Vector3[] copyP = new Vector3[i + 1];
//			System.arraycopy(allCollider, 0, copy, 0, allCollider.length);
//			System.arraycopy(allGameObjectPositions, 0, copyP, 0, allGameObjectPositions.length);
//			allCollider = new Collider[i+1];
//			allGameObjectPositions = new Vector3[i+1];
//			System.arraycopy(copy, 0, allCollider, 0, copy.length);
//			System.arraycopy(copyP, 0, allGameObjectPositions, 0, copyP.length);
//			allCollider[i] = collider;
//			allGameObjectPositions[i] = object.position;
//		}
//		System.out.println(allCollider.length);
//		System.out.println(allGameObjectPositions.length);
//		i++;
	}
	public static void CreateCollider(float[] positionData, Vector3 scale, Vector3 position) {
		Collider collider = new Collider(positionData, scale);
		allCollider.add(collider);
		allGameObjectPositions.add(position);
	}
	GameObject object1;
	GameObject object2;
		public static boolean CheckCollision(Collider col1, Collider col2, Vector3 gameobjectposition1, Vector3 gameobjectposition2) {
		boolean xcol, ycol, zcol;
		collision = false;
		float Xm1 = (col1.maxX + col1.minX) / 2;
		float Ym1 = (col1.maxY + col1.minY) / 2;
		float Zm1 = (col1.maxZ + col1.minZ) / 2;
		float distX1 = Math.abs(Math.abs(Xm1) - Math.abs(col1.maxX));
		float distY1 = Math.abs(Math.abs(Ym1) - Math.abs(col1.maxY));
		float distZ1 = Math.abs(Math.abs(Zm1) - Math.abs(col1.maxZ));
		Vector3 position1 = new Vector3(Xm1, Ym1, Zm1);
		position1 = Vector3.add(position1, gameobjectposition1);
		Vector3 scale1 = new Vector3(distX1, distY1, distZ1);
		
		float Xm2 = (col2.maxX + col2.minX) / 2;
		float Ym2 = (col2.maxY + col2.minY) / 2;
		float Zm2 = (col2.maxZ + col2.minZ) / 2;
		float distX2 = Math.abs(Math.abs(Xm2) - Math.abs(col2.maxX));
		float distY2 = Math.abs(Math.abs(Ym2) - Math.abs(col2.maxY));
		float distZ2 = Math.abs(Math.abs(Zm2) - Math.abs(col2.maxZ));
		
		Vector3 position2 = new Vector3(Xm2, Ym2, Zm2);
		position2 = Vector3.add(position2,  gameobjectposition2);
		Vector3 scale2 = new Vector3(distX2, distY2, distZ2);
		

//		if (position1.x + scale1.x >= position2.x - scale2.x || position1.x - scale1.x >= position2.x + scale2.x || position2.x + scale2.x >= position1.x - scale1.x || position2.x -scale2.x >= position1.x + scale1.x) {
//			xcol = true;
//			if (position1.y + scale1.y >= position2.y - scale2.y || position1.y - scale1.y >= position2.y + scale2.y || position2.y + scale2.y >= position1.y - scale1.y || position2.y -scale2.y >= position1.y + scale1.y) {
//				ycol = true;
//				if (position1.z + scale1.z >= position2.z - scale2.z || position1.z - scale1.z >= position2.z + scale2.z || position2.z + scale2.z >= position1.z - scale1.z || position2.z -scale2.z >= position1.z + scale1.z) {
//					collision = true;
//					zcol = true;
//				}
//			}
//		}
		
		if (position1.x +scale1.x >= position2.x - scale2.x && position1.x +scale1.x <= position2.x + scale2.x) {				//rechterpunt object 1 in object 2
			if (position1.y +scale1.y >= position2.y - scale2.y && position1.y +scale1.y <= position2.y + scale2.y) {			//bovenste punt object 1 in object 2
				ycol = true;
				if (position1.z +scale1.z >= position2.z - scale2.z && position1.z +scale1.z <= position2.z + scale2.z) {		//voorste punt object 1 in object 2
					zcol = true;
					collision = true;
				}
			}
		}
		if (position1.x +scale1.x >= position2.x - scale2.x && position1.x +scale1.x <= position2.x + scale2.x) {				//rechterpunt object 1 in object 2
			xcol = true;
			if (position1.y +scale1.y >= position2.y - scale2.y && position1.y +scale1.y <= position2.y + scale2.y) {			//bovenste punt object 1 in object 2
				ycol = true;
				if (position1.z - scale1.z <= position2.z + scale2.z && position1.z - scale1.z >= position2.z - scale2.z) { 	// achterste punt object 1 in object 2
					zcol = true;
					collision = true;
				}
			}
		}
		if (position1.x +scale1.x >= position2.x - scale2.x && position1.x +scale1.x <= position2.x + scale2.x) {				//rechterpunt object 1 in object 2
			xcol = true;
			if (position1.y - scale1.y <= position2.y + scale2.y && position1.y - scale1.y >= position2.y - scale2.y) {			//onderste punt object 1 in object 2
				ycol = true;
				if (position1.z +scale1.z >= position2.z - scale2.z && position1.z +scale1.z <= position2.z + scale2.z) {		//voorste punt object 1 in object 2
					zcol = true;
					collision = true;
				}
			}
		}
		if (position1.x +scale1.x >= position2.x - scale2.x && position1.x +scale1.x <= position2.x + scale2.x) {				//rechterpunt object 1 in object 2
			xcol = true;
			if (position1.y - scale1.y <= position2.y + scale2.y && position1.y - scale1.y >= position2.y - scale2.y) {			//onderste punt object 1 in object 2
				ycol = true;
				if (position1.z - scale1.z <= position2.z + scale2.z && position1.z - scale1.z >= position2.z - scale2.z) { 	// achterste punt object 1 in object 2
					zcol = true;
					collision = true;
				}
			}
		}
		if (position1.x +scale1.x >= position2.x - scale2.x && position1.x +scale1.x <= position2.x + scale2.x) {				//rechterpunt object 1 in object 2
			xcol = true;
			if (position1.y - scale1.y <= position2.y + scale2.y && position1.y - scale1.y >= position2.y - scale2.y) {			//onderste punt object 1 in object 2
				ycol = true;
				if (position1.z <= position2.z + scale2.z && position1.z >= position2.z - scale2.z) {							// object1 middenin object 2
					collision = true;
				}
			}
		}
		if (position1.x +scale1.x >= position2.x - scale2.x && position1.x +scale1.x <= position2.x + scale2.x) {				//rechterpunt object 1 in object 2
			xcol = true;
			if (position1.y <= position2.y + scale2.y && position1.y >= position2.y - scale2.y) {								// object1 middenin object 2
				ycol = true;
				if (position1.z - scale1.z <= position2.z + scale2.z && position1.z - scale1.z >= position2.z - scale2.z) { 	// achterste punt object 1 in object 2
					zcol = true;
					collision = true;
				}
			}
		}
		if (position1.x +scale1.x >= position2.x - scale2.x && position1.x +scale1.x <= position2.x + scale2.x) {				//rechterpunt object 1 in object 2
			xcol = true;
			if (position1.y <= position2.y + scale2.y && position1.y >= position2.y - scale2.y) {								// object1 middenin object 2
				ycol = true;
				if (position1.z <= position2.z + scale2.z && position1.z >= position2.z - scale2.z) {							// object1 middenin object 2
					collision = true;
				}
			}
		}
		if (position1.x <= position2.x + scale2.x && position1.x >= position2.x - scale2.x) {									// object1 middenin object 2					
			if (position1.y - scale1.y <= position2.y + scale2.y && position1.y - scale1.y >= position2.y - scale2.y) {			//onderste punt object 1 in object 2
				ycol = true;
				if (position1.z - scale1.z <= position2.z + scale2.z && position1.z - scale1.z >= position2.z - scale2.z) { 	// achterste punt object 1 in object 2
					zcol = true;
					collision = true;
				}
			}
		}
		if (position1.x <= position2.x + scale2.x && position1.x >= position2.x - scale2.x) {									// object1 middenin object 2					
			if (position1.y - scale1.y <= position2.y + scale2.y && position1.y - scale1.y >= position2.y - scale2.y) {			//onderste punt object 1 in object 2
				ycol = true;
				if (position1.z <= position2.z + scale2.z && position1.z >= position2.z - scale2.z) {							// object1 middenin object 2
					collision = true;
				}
			}
		}
		if (position1.x <= position2.x + scale2.x && position1.x >= position2.x - scale2.x) {									// object1 middenin object 2					
			if (position1.y <= position2.y + scale2.y && position1.y >= position2.y - scale2.y) {								// object1 middenin object 2
				if (position1.z - scale1.z <= position2.z + scale2.z && position1.z - scale1.z >= position2.z - scale2.z) { 	// achterste punt object 1 in object 2
					zcol = true;
					collision = true;
				}
			}
		}
		if (position1.x <= position2.x + scale2.x && position1.x >= position2.x - scale2.x) {									// object1 middenin object 2					
			if (position1.y <= position2.y + scale2.y && position1.y >= position2.y - scale2.y) {								// object1 middenin object 2
				if (position1.z <= position2.z + scale2.z && position1.z >= position2.z - scale2.z) {							// object1 middenin object 2
					collision = true;
				}
			}
		}
		
		
//
//
		if (position1.x - scale1.x <= position2.x + scale2.x && position1.x - scale1.x >= position2.x - scale2.x) {				//linkerpunt object 1 in object 2
			xcol = true;
			if (position1.y +scale1.y >= position2.y - scale2.y && position1.y +scale1.y <= position2.y + scale2.y) {			//bovenste punt object 1 in object 2
				ycol = true;
				if (position1.z +scale1.z >= position2.z - scale2.z && position1.z +scale1.z <= position2.z + scale2.z) {		//voorste punt object 1 in object 2
					zcol = true;
					collision = true;
				}
			}
		}
		if (position1.x - scale1.x <= position2.x + scale2.x && position1.x - scale1.x >= position2.x - scale2.x) {				//linkerpunt object 1 in object 2
			xcol = true;
			if (position1.y +scale1.y >= position2.y - scale2.y && position1.y +scale1.y <= position2.y + scale2.y) {			//bovenste punt object 1 in object 2
				ycol = true;
				if (position1.z - scale1.z <= position2.z + scale2.z && position1.z - scale1.z >= position2.z - scale2.z) { 	// achterste punt object 1 in object 2
					zcol = true;
					collision = true;
				}
			}
		}
		if (position1.x - scale1.x <= position2.x + scale2.x && position1.x - scale1.x >= position2.x - scale2.x) {				//linkerpunt object 1 in object 2
			xcol = true;
			if (position1.y - scale1.y <= position2.y + scale2.y && position1.y - scale1.y >= position2.y - scale2.y) {			//onderste punt object 1 in object 2
				ycol = true;
				if (position1.z +scale1.z >= position2.z - scale2.z && position1.z +scale1.z <= position2.z + scale2.z) {		//voorste punt object 1 in object 2
					zcol = true;
					collision = true;
				}
			}
		}
		if (position1.x - scale1.x <= position2.x + scale2.x && position1.x - scale1.x >= position2.x - scale2.x) {				//linkerpunt object 1 in object 2
			xcol = true;
			if (position1.y - scale1.y <= position2.y + scale2.y && position1.y - scale1.y >= position2.y - scale2.y) {			//onderste punt object 1 in object 2
				ycol = true;
				if (position1.z - scale1.z <= position2.z + scale2.z && position1.z - scale1.z >= position2.z - scale2.z) { 	// achterste punt object 1 in object 2
					zcol = true;
					collision = true;
				}
			}
		}
		if (position1.x - scale1.x <= position2.x + scale2.x && position1.x - scale1.x >= position2.x - scale2.x) {				//linkerpunt object 1 in object 2
			xcol = true;
			if (position1.y +scale1.y >= position2.y - scale2.y && position1.y +scale1.y <= position2.y + scale2.y) {			//bovenste punt object 1 in object 2
				ycol = true;
				if (position1.z <= position2.z + scale2.z && position1.z >= position2.z - scale2.z) {							// object1 middenin object 2
					collision = true;
				}
			}
		}
		if (position1.x - scale1.x <= position2.x + scale2.x && position1.x - scale1.x >= position2.x - scale2.x) {				//linkerpunt object 1 in object 2
			xcol = true;
			if (position1.y <= position2.y + scale2.y && position1.y >= position2.y - scale2.y) {								// object1 middenin object 2
				if (position1.z +scale1.z >= position2.z - scale2.z && position1.z +scale1.z <= position2.z + scale2.z) {		//voorste punt object 1 in object 2
					zcol = true;
					collision = true;
				}
			}
		}
		if (position1.x - scale1.x <= position2.x + scale2.x && position1.x - scale1.x >= position2.x - scale2.x) {				//linkerpunt object 1 in object 2
			xcol = true;
			if (position1.y <= position2.y + scale2.y && position1.y >= position2.y - scale2.y) {								// object1 middenin object 2
				if (position1.z <= position2.z + scale2.z && position1.z >= position2.z - scale2.z) {							// object1 middenin object 2
					collision = true;
				}
			}
		}
		if (position1.x <= position2.x + scale2.x && position1.x >= position2.x - scale2.x) {									// object1 middenin object 2
			xcol = true;
			if (position1.y +scale1.y >= position2.y - scale2.y && position1.y +scale1.y <= position2.y + scale2.y) {			//bovenste punt object 1 in object 2
				ycol = true;
				if (position1.z +scale1.z >= position2.z - scale2.z && position1.z +scale1.z <= position2.z + scale2.z) {		//voorste punt object 1 in object 2
					zcol = true;
					collision = true;
				}
			}
		}
		if (position1.x <= position2.x + scale2.x && position1.x >= position2.x - scale2.x) {									// object1 middenin object 2
			xcol = true;
			if (position1.y +scale1.y >= position2.y - scale2.y && position1.y +scale1.y <= position2.y + scale2.y) {			//bovenste punt object 1 in object 2
				ycol = true;
				if (position1.z <= position2.z + scale2.z && position1.z >= position2.z - scale2.z) {							// object1 middenin object 2
					collision = true;
				}
			}
		}
		if (position1.x <= position2.x + scale2.x && position1.x >= position2.x - scale2.x) {									// object1 middenin object 2
			xcol = true;
			if (position1.y <= position2.y + scale2.y && position1.y >= position2.y - scale2.y) {								// object1 middenin object 2
				if (position1.z +scale1.z >= position2.z - scale2.z && position1.z +scale1.z <= position2.z + scale2.z) {		//voorste punt object 1 in object 2
					zcol = true;
					collision = true;
				}
			}
		}
		if (position1.x <= position2.x + scale2.x && position1.x >= position2.x - scale2.x) {									// object1 middenin object 2
			xcol = true;
			if (position1.y <= position2.y + scale2.y && position1.y >= position2.y - scale2.y) {								// object1 middenin object 2
				if (position1.z <= position2.z + scale2.z && position1.z >= position2.z - scale2.z) {							// object1 middenin object 2
					collision = true;
				}
			}
			//
			//
			//
			//VAN 1 NAAR 2
			//
			//
						
						if (position2.x +scale2.x >= position1.x - scale1.x && position2.x +scale2.x <= position1.x + scale1.x) {				//rechterpunt object 2 in object 1
							if (position2.y +scale2.y >= position1.y - scale1.y && position2.y +scale2.y <= position1.y + scale1.y) {			//bovenste punt object 2 in object 1
								ycol = true;
								if (position2.z +scale2.z >= position1.z - scale1.z && position2.z +scale2.z <= position1.z + scale1.z) {		//voorste punt object 2 in object 1
									zcol = true;
									collision = true;
								}
							}
						}
						if (position2.x +scale2.x >= position1.x - scale1.x && position2.x +scale2.x <= position1.x + scale1.x) {				//rechterpunt object 2 in object 1
							xcol = true;
							if (position2.y +scale2.y >= position1.y - scale1.y && position2.y +scale2.y <= position1.y + scale1.y) {			//bovenste punt object 2 in object 1
								ycol = true;
								if (position2.z - scale2.z <= position1.z + scale1.z && position2.z - scale2.z >= position1.z - scale1.z) { 	// achterste punt object 2 in object 1
									zcol = true;
									collision = true;
								}
							}
						}
						if (position2.x +scale2.x >= position1.x - scale1.x && position2.x +scale2.x <= position1.x + scale1.x) {				//rechterpunt object 2 in object 1
							xcol = true;
							if (position2.y - scale2.y <= position1.y + scale1.y && position2.y - scale2.y >= position1.y - scale1.y) {			//onderste punt object 2 in object 1
								ycol = true;
								if (position2.z +scale2.z >= position1.z - scale1.z && position2.z +scale2.z <= position1.z + scale1.z) {		//voorste punt object 2 in object 1
									zcol = true;
									collision = true;
								}
							}
						}
						if (position2.x +scale2.x >= position1.x - scale1.x && position2.x +scale2.x <= position1.x + scale1.x) {				//rechterpunt object 2 in object 1
							xcol = true;
							if (position2.y - scale2.y <= position1.y + scale1.y && position2.y - scale2.y >= position1.y - scale1.y) {			//onderste punt object 2 in object 1
								ycol = true;
								if (position2.z - scale2.z <= position1.z + scale1.z && position2.z - scale2.z >= position1.z - scale1.z) { 	// achterste punt object 2 in object 1
									zcol = true;
									collision = true;
								}
							}
						}
						if (position2.x +scale2.x >= position1.x - scale1.x && position2.x +scale2.x <= position1.x + scale1.x) {				//rechterpunt object 2 in object 1
							xcol = true;
							if (position2.y - scale2.y <= position1.y + scale1.y && position2.y - scale2.y >= position1.y - scale1.y) {			//onderste punt object 2 in object 1
								ycol = true;
								if (position2.z <= position1.z + scale1.z && position2.z >= position1.z - scale1.z) {							// object2 middenin object 1
									collision = true;
								}
							}
						}
						if (position2.x +scale2.x >= position1.x - scale1.x && position2.x +scale2.x <= position1.x + scale1.x) {				//rechterpunt object 2 in object 1
							xcol = true;
							if (position2.y <= position1.y + scale1.y && position2.y >= position1.y - scale1.y) {								// object2 middenin object 1
								ycol = true;
								if (position2.z - scale2.z <= position1.z + scale1.z && position2.z - scale2.z >= position1.z - scale1.z) { 	// achterste punt object 2 in object 1
									zcol = true;
									collision = true;
								}
							}
						}
						if (position2.x +scale2.x >= position1.x - scale1.x && position2.x +scale2.x <= position1.x + scale1.x) {				//rechterpunt object 2 in object 1
							xcol = true;
							if (position2.y <= position1.y + scale1.y && position2.y >= position1.y - scale1.y) {								// object2 middenin object 1
								ycol = true;
								if (position2.z <= position1.z + scale1.z && position2.z >= position1.z - scale1.z) {							// object2 middenin object 1
									collision = true;
								}
							}
						}
						if (position2.x <= position1.x + scale1.x && position2.x >= position1.x - scale1.x) {									// object2 middenin object 1					
							if (position2.y - scale2.y <= position1.y + scale1.y && position2.y - scale2.y >= position1.y - scale1.y) {			//onderste punt object 2 in object 1
								ycol = true;
								if (position2.z - scale2.z <= position1.z + scale1.z && position2.z - scale2.z >= position1.z - scale1.z) { 	// achterste punt object 2 in object 1
									zcol = true;
									collision = true;
								}
							}
						}
						if (position2.x <= position1.x + scale1.x && position2.x >= position1.x - scale1.x) {									// object2 middenin object 1					
							if (position2.y - scale2.y <= position1.y + scale1.y && position2.y - scale2.y >= position1.y - scale1.y) {			//onderste punt object 2 in object 1
								ycol = true;
								if (position2.z <= position1.z + scale1.z && position2.z >= position1.z - scale1.z) {							// object2 middenin object 1
									collision = true;
								}
							}
						}
						if (position2.x <= position1.x + scale1.x && position2.x >= position1.x - scale1.x) {									// object2 middenin object 1					
							if (position2.y <= position1.y + scale1.y && position2.y >= position1.y - scale1.y) {								// object2 middenin object 1
								if (position2.z - scale2.z <= position1.z + scale1.z && position2.z - scale2.z >= position1.z - scale1.z) { 	// achterste punt object 2 in object 1
									zcol = true;
									collision = true;
								}
							}
						}
						if (position2.x <= position1.x + scale1.x && position2.x >= position1.x - scale1.x) {									// object2 middenin object 1					
							if (position2.y <= position1.y + scale1.y && position2.y >= position1.y - scale1.y) {								// object2 middenin object 1
								if (position2.z <= position1.z + scale1.z && position2.z >= position1.z - scale1.z) {							// object2 middenin object 1
									collision = true;
								}
							}
						}
						
						
				//
				//
						if (position2.x - scale2.x <= position1.x + scale1.x && position2.x - scale2.x >= position1.x - scale1.x) {				//linkerpunt object 2 in object 1
							xcol = true;
							if (position2.y +scale2.y >= position1.y - scale1.y && position2.y +scale2.y <= position1.y + scale1.y) {			//bovenste punt object 2 in object 1
								ycol = true;
								if (position2.z +scale2.z >= position1.z - scale1.z && position2.z +scale2.z <= position1.z + scale1.z) {		//voorste punt object 2 in object 1
									zcol = true;
									collision = true;
								}
							}
						}
						if (position2.x - scale2.x <= position1.x + scale1.x && position2.x - scale2.x >= position1.x - scale1.x) {				//linkerpunt object 2 in object 1
							xcol = true;
							if (position2.y +scale2.y >= position1.y - scale1.y && position2.y +scale2.y <= position1.y + scale1.y) {			//bovenste punt object 2 in object 1
								ycol = true;
								if (position2.z - scale2.z <= position1.z + scale1.z && position2.z - scale2.z >= position1.z - scale1.z) { 	// achterste punt object 2 in object 1
									zcol = true;
									collision = true;
								}
							}
						}
						if (position2.x - scale2.x <= position1.x + scale1.x && position2.x - scale2.x >= position1.x - scale1.x) {				//linkerpunt object 2 in object 1
							xcol = true;
							if (position2.y - scale2.y <= position1.y + scale1.y && position2.y - scale2.y >= position1.y - scale1.y) {			//onderste punt object 2 in object 1
								ycol = true;
								if (position2.z +scale2.z >= position1.z - scale1.z && position2.z +scale2.z <= position1.z + scale1.z) {		//voorste punt object 2 in object 1
									zcol = true;
									collision = true;
								}
							}
						}
						if (position2.x - scale2.x <= position1.x + scale1.x && position2.x - scale2.x >= position1.x - scale1.x) {				//linkerpunt object 2 in object 1
							xcol = true;
							if (position2.y - scale2.y <= position1.y + scale1.y && position2.y - scale2.y >= position1.y - scale1.y) {			//onderste punt object 2 in object 1
								ycol = true;
								if (position2.z - scale2.z <= position1.z + scale1.z && position2.z - scale2.z >= position1.z - scale1.z) { 	// achterste punt object 2 in object 1
									zcol = true;
									collision = true;
								}
							}
						}
						if (position2.x - scale2.x <= position1.x + scale1.x && position2.x - scale2.x >= position1.x - scale1.x) {				//linkerpunt object 2 in object 1
							xcol = true;
							if (position2.y +scale2.y >= position1.y - scale1.y && position2.y +scale2.y <= position1.y + scale1.y) {			//bovenste punt object 2 in object 1
								ycol = true;
								if (position2.z <= position1.z + scale1.z && position2.z >= position1.z - scale1.z) {							// object2 middenin object 1
									collision = true;
								}
							}
						}
						if (position2.x - scale2.x <= position1.x + scale1.x && position2.x - scale2.x >= position1.x - scale1.x) {				//linkerpunt object 2 in object 1
							xcol = true;
							if (position2.y <= position1.y + scale1.y && position2.y >= position1.y - scale1.y) {								// object2 middenin object 1
								if (position2.z +scale2.z >= position1.z - scale1.z && position2.z +scale2.z <= position1.z + scale1.z) {		//voorste punt object 2 in object 1
									zcol = true;
									collision = true;
								}
							}
						}
						if (position2.x - scale2.x <= position1.x + scale1.x && position2.x - scale2.x >= position1.x - scale1.x) {				//linkerpunt object 2 in object 1
							xcol = true;
							if (position2.y <= position1.y + scale1.y && position2.y >= position1.y - scale1.y) {								// object2 middenin object 1
								if (position2.z <= position1.z + scale1.z && position2.z >= position1.z - scale1.z) {							// object2 middenin object 1
									collision = true;
								}
							}
						}
						if (position2.x <= position1.x + scale1.x && position2.x >= position1.x - scale1.x) {									// object2 middenin object 1
							xcol = true;
							if (position2.y +scale2.y >= position1.y - scale1.y && position2.y +scale2.y <= position1.y + scale1.y) {			//bovenste punt object 2 in object 1
								ycol = true;
								if (position2.z +scale2.z >= position1.z - scale1.z && position2.z +scale2.z <= position1.z + scale1.z) {		//voorste punt object 2 in object 1
									zcol = true;
									collision = true;
								}
							}
						}
						if (position2.x <= position1.x + scale1.x && position2.x >= position1.x - scale1.x) {									// object2 middenin object 1
							xcol = true;
							if (position2.y +scale2.y >= position1.y - scale1.y && position2.y +scale2.y <= position1.y + scale1.y) {			//bovenste punt object 2 in object 1
								ycol = true;
								if (position2.z <= position1.z + scale1.z && position2.z >= position1.z - scale1.z) {							// object2 middenin object 1
									collision = true;
								}
							}
						}
						if (position2.x <= position1.x + scale1.x && position2.x >= position1.x - scale1.x) {									// object2 middenin object 1
							xcol = true;
							if (position2.y <= position1.y + scale1.y && position2.y >= position1.y - scale1.y) {								// object2 middenin object 1
								if (position2.z +scale2.z >= position1.z - scale1.z && position2.z +scale2.z <= position1.z + scale1.z) {		//voorste punt object 2 in object 1
									zcol = true;
									collision = true;
								}
							}
						}
						if (position2.x <= position1.x + scale1.x && position2.x >= position1.x - scale1.x) {									// object2 middenin object 1
							xcol = true;
							if (position2.y <= position1.y + scale1.y && position2.y >= position1.y - scale1.y) {								// object2 middenin object 1
								if (position2.z <= position1.z + scale1.z && position2.z >= position1.z - scale1.z) {							// object2 middenin object 1
									collision = true;
								}
							}
						}
						
		
		
//		if (position1.x - scale1.x <= position2.x + scale2.x && position1.x - scale1.x >= position2.x - scale2.x) {				//linkerpunt object 1 in object 2
//			xcol = true;
//			if (position1.y - scale1.y <= position2.y + scale2.y && position1.y - scale1.y >= position2.y - scale2.y) {			//onderste punt object 1 in object 2
//				ycol = true;
//				if (position1.z - scale1.z <= position2.z + scale2.z && position1.z - scale1.z >= position2.z - scale2.z) { 	// achterste punt object 1 in object 2
//					zcol = true;
//					collision = true;
//				}
//			}
//		}
//		boolean collisionX = position1.x + scale1.x >= position2.x && position2.x + scale2.x >= position1.x;
//		boolean collisionY = position1.y + scale1.y >= position2.y && position2.y + scale2.y >= position1.y;
//		boolean collisionZ = position1.z + scale1.z >= position2.z && position2.z + scale2.z >= position1.z;
//		if (collisionX && collisionY && collisionZ) {
//			collision = true;
//		}
//		else {
//			collision = false;
//		}

		
	}
		return collision;
		}
		public static boolean CheckCollision(Collider col1, ArrayList<Collider> colliderarray, Vector3 gameobjectposition1, ArrayList<Vector3> gameobjectpositionarray) {
			collision = false;
			for (int a = 0; a < colliderarray.size(); a++) {
				Collider col2 = colliderarray.get(a);
				boolean xcol, ycol, zcol;
				float Xm1 = (col1.maxX + col1.minX) / 2;
				float Ym1 = (col1.maxY + col1.minY) / 2;
				float Zm1 = (col1.maxZ + col1.minZ) / 2;
				float distX1 = Math.abs(Math.abs(Xm1) - Math.abs(col1.maxX));
				float distY1 = Math.abs(Math.abs(Ym1) - Math.abs(col1.maxY));
				float distZ1 = Math.abs(Math.abs(Zm1) - Math.abs(col1.maxZ));
				Vector3 position1 = new Vector3(Xm1, Ym1, Zm1);
				position1 = Vector3.add(position1, gameobjectposition1);
				Vector3 scale1 = new Vector3(distX1, distY1, distZ1);
				
				float Xm2 = (col2.maxX + col2.minX) / 2;
				float Ym2 = (col2.maxY + col2.minY) / 2;
				float Zm2 = (col2.maxZ + col2.minZ) / 2;
				float distX2 = Math.abs(Math.abs(Xm2) - Math.abs(col2.maxX));
				float distY2 = Math.abs(Math.abs(Ym2) - Math.abs(col2.maxY));
				float distZ2 = Math.abs(Math.abs(Zm2) - Math.abs(col2.maxZ));
				
				Vector3 position2 = new Vector3(Xm2, Ym2, Zm2);
				position2 = Vector3.add(position2,  gameobjectpositionarray.get(a));
				Vector3 scale2 = new Vector3(distX2, distY2, distZ2);
				
//				System.out.println("pos1.y  " + position1.y);
//				System.out.println("scale1.y " + scale1.y);
//				System.out.println("pos2.y " + position2.y);
//				System.out.println("scale2.y " + scale2.y);
	//			if (position1.x + scale1.x >= position2.x - scale2.x || position1.x - scale1.x >= position2.x + scale2.x || position2.x + scale2.x >= position1.x - scale1.x || position2.x -scale2.x >= position1.x + scale1.x) {
	//				xcol = true;
	//				if (position1.y + scale1.y >= position2.y - scale2.y || position1.y - scale1.y >= position2.y + scale2.y || position2.y + scale2.y >= position1.y - scale1.y || position2.y -scale2.y >= position1.y + scale1.y) {
	//					ycol = true;
	//					if (position1.z + scale1.z >= position2.z - scale2.z || position1.z - scale1.z >= position2.z + scale2.z || position2.z + scale2.z >= position1.z - scale1.z || position2.z -scale2.z >= position1.z + scale1.z) {
	//						collision = true;
	//						zcol = true;
	//					}
	//				}
	//			}
				
				if (position1.x +scale1.x >= position2.x - scale2.x && position1.x +scale1.x <= position2.x + scale2.x) {				//rechterpunt object 1 in object 2
					if (position1.y +scale1.y >= position2.y - scale2.y && position1.y +scale1.y <= position2.y + scale2.y) {			//bovenste punt object 1 in object 2
						ycol = true;
						if (position1.z +scale1.z >= position2.z - scale2.z && position1.z +scale1.z <= position2.z + scale2.z) {		//voorste punt object 1 in object 2
							zcol = true;
							collision = true;
						}
					}
				}
				if (position1.x +scale1.x >= position2.x - scale2.x && position1.x +scale1.x <= position2.x + scale2.x) {				//rechterpunt object 1 in object 2
					xcol = true;
					if (position1.y +scale1.y >= position2.y - scale2.y && position1.y +scale1.y <= position2.y + scale2.y) {			//bovenste punt object 1 in object 2
						ycol = true;
						if (position1.z - scale1.z <= position2.z + scale2.z && position1.z - scale1.z >= position2.z - scale2.z) { 	// achterste punt object 1 in object 2
							zcol = true;
							collision = true;
						}
					}
				}
				if (position1.x +scale1.x >= position2.x - scale2.x && position1.x +scale1.x <= position2.x + scale2.x) {				//rechterpunt object 1 in object 2
					xcol = true;
					if (position1.y - scale1.y <= position2.y + scale2.y && position1.y - scale1.y >= position2.y - scale2.y) {			//onderste punt object 1 in object 2
						ycol = true;
						if (position1.z +scale1.z >= position2.z - scale2.z && position1.z +scale1.z <= position2.z + scale2.z) {		//voorste punt object 1 in object 2
							zcol = true;
							collision = true;
						}
					}
				}
				if (position1.x +scale1.x >= position2.x - scale2.x && position1.x +scale1.x <= position2.x + scale2.x) {				//rechterpunt object 1 in object 2
					xcol = true;
					if (position1.y - scale1.y <= position2.y + scale2.y && position1.y - scale1.y >= position2.y - scale2.y) {			//onderste punt object 1 in object 2
						ycol = true;
						if (position1.z - scale1.z <= position2.z + scale2.z && position1.z - scale1.z >= position2.z - scale2.z) { 	// achterste punt object 1 in object 2
							zcol = true;
							collision = true;
						}
					}
				}
				if (position1.x +scale1.x >= position2.x - scale2.x && position1.x +scale1.x <= position2.x + scale2.x) {				//rechterpunt object 1 in object 2
					xcol = true;
					if (position1.y - scale1.y <= position2.y + scale2.y && position1.y - scale1.y >= position2.y - scale2.y) {			//onderste punt object 1 in object 2
						ycol = true;
						if (position1.z <= position2.z + scale2.z && position1.z >= position2.z - scale2.z) {							// object1 middenin object 2
							collision = true;
						}
					}
				}
				if (position1.x +scale1.x >= position2.x - scale2.x && position1.x +scale1.x <= position2.x + scale2.x) {				//rechterpunt object 1 in object 2
					xcol = true;
					if (position1.y <= position2.y + scale2.y && position1.y >= position2.y - scale2.y) {								// object1 middenin object 2
						ycol = true;
						if (position1.z - scale1.z <= position2.z + scale2.z && position1.z - scale1.z >= position2.z - scale2.z) { 	// achterste punt object 1 in object 2
							zcol = true;
							collision = true;
						}
					}
				}
				if (position1.x +scale1.x >= position2.x - scale2.x && position1.x +scale1.x <= position2.x + scale2.x) {				//rechterpunt object 1 in object 2
					xcol = true;
					if (position1.y <= position2.y + scale2.y && position1.y >= position2.y - scale2.y) {								// object1 middenin object 2
						ycol = true;
						if (position1.z <= position2.z + scale2.z && position1.z >= position2.z - scale2.z) {							// object1 middenin object 2
							collision = true;
						}
					}
				}
				if (position1.x <= position2.x + scale2.x && position1.x >= position2.x - scale2.x) {									// object1 middenin object 2					
					if (position1.y - scale1.y <= position2.y + scale2.y && position1.y - scale1.y >= position2.y - scale2.y) {			//onderste punt object 1 in object 2
						ycol = true;
						if (position1.z - scale1.z <= position2.z + scale2.z && position1.z - scale1.z >= position2.z - scale2.z) { 	// achterste punt object 1 in object 2
							zcol = true;
							collision = true;
						}
					}
				}
				if (position1.x <= position2.x + scale2.x && position1.x >= position2.x - scale2.x) {									// object1 middenin object 2					
					if (position1.y - scale1.y <= position2.y + scale2.y && position1.y - scale1.y >= position2.y - scale2.y) {			//onderste punt object 1 in object 2
						ycol = true;
						if (position1.z <= position2.z + scale2.z && position1.z >= position2.z - scale2.z) {							// object1 middenin object 2
							collision = true;
						}
					}
				}
				if (position1.x <= position2.x + scale2.x && position1.x >= position2.x - scale2.x) {									// object1 middenin object 2					
					if (position1.y <= position2.y + scale2.y && position1.y >= position2.y - scale2.y) {								// object1 middenin object 2
						if (position1.z - scale1.z <= position2.z + scale2.z && position1.z - scale1.z >= position2.z - scale2.z) { 	// achterste punt object 1 in object 2
							zcol = true;
							collision = true;
						}
					}
				}
				if (position1.x <= position2.x + scale2.x && position1.x >= position2.x - scale2.x) {									// object1 middenin object 2					
					if (position1.y <= position2.y + scale2.y && position1.y >= position2.y - scale2.y) {								// object1 middenin object 2
						if (position1.z <= position2.z + scale2.z && position1.z >= position2.z - scale2.z) {							// object1 middenin object 2
							collision = true;
						}
					}
				}
				
				
		//
		//
				if (position1.x - scale1.x <= position2.x + scale2.x && position1.x - scale1.x >= position2.x - scale2.x) {				//linkerpunt object 1 in object 2
					xcol = true;
					if (position1.y +scale1.y >= position2.y - scale2.y && position1.y +scale1.y <= position2.y + scale2.y) {			//bovenste punt object 1 in object 2
						ycol = true;
						if (position1.z +scale1.z >= position2.z - scale2.z && position1.z +scale1.z <= position2.z + scale2.z) {		//voorste punt object 1 in object 2
							zcol = true;
							collision = true;
						}
					}
				}
				if (position1.x - scale1.x <= position2.x + scale2.x && position1.x - scale1.x >= position2.x - scale2.x) {				//linkerpunt object 1 in object 2
					xcol = true;
					if (position1.y +scale1.y >= position2.y - scale2.y && position1.y +scale1.y <= position2.y + scale2.y) {			//bovenste punt object 1 in object 2
						ycol = true;
						if (position1.z - scale1.z <= position2.z + scale2.z && position1.z - scale1.z >= position2.z - scale2.z) { 	// achterste punt object 1 in object 2
							zcol = true;
							collision = true;
						}
					}
				}
				if (position1.x - scale1.x <= position2.x + scale2.x && position1.x - scale1.x >= position2.x - scale2.x) {				//linkerpunt object 1 in object 2
					xcol = true;
					if (position1.y - scale1.y <= position2.y + scale2.y && position1.y - scale1.y >= position2.y - scale2.y) {			//onderste punt object 1 in object 2
						ycol = true;
						if (position1.z +scale1.z >= position2.z - scale2.z && position1.z +scale1.z <= position2.z + scale2.z) {		//voorste punt object 1 in object 2
							zcol = true;
							collision = true;
						}
					}
				}
				if (position1.x - scale1.x <= position2.x + scale2.x && position1.x - scale1.x >= position2.x - scale2.x) {				//linkerpunt object 1 in object 2
					xcol = true;
					if (position1.y - scale1.y <= position2.y + scale2.y && position1.y - scale1.y >= position2.y - scale2.y) {			//onderste punt object 1 in object 2
						ycol = true;
						if (position1.z - scale1.z <= position2.z + scale2.z && position1.z - scale1.z >= position2.z - scale2.z) { 	// achterste punt object 1 in object 2
							zcol = true;
							collision = true;
						}
					}
				}
				if (position1.x - scale1.x <= position2.x + scale2.x && position1.x - scale1.x >= position2.x - scale2.x) {				//linkerpunt object 1 in object 2
					xcol = true;
					if (position1.y +scale1.y >= position2.y - scale2.y && position1.y +scale1.y <= position2.y + scale2.y) {			//bovenste punt object 1 in object 2
						ycol = true;
						if (position1.z <= position2.z + scale2.z && position1.z >= position2.z - scale2.z) {							// object1 middenin object 2
							collision = true;
						}
					}
				}
				if (position1.x - scale1.x <= position2.x + scale2.x && position1.x - scale1.x >= position2.x - scale2.x) {				//linkerpunt object 1 in object 2
					xcol = true;
					if (position1.y <= position2.y + scale2.y && position1.y >= position2.y - scale2.y) {								// object1 middenin object 2
						if (position1.z +scale1.z >= position2.z - scale2.z && position1.z +scale1.z <= position2.z + scale2.z) {		//voorste punt object 1 in object 2
							zcol = true;
							collision = true;
						}
					}
				}
				if (position1.x - scale1.x <= position2.x + scale2.x && position1.x - scale1.x >= position2.x - scale2.x) {				//linkerpunt object 1 in object 2
					xcol = true;
					if (position1.y <= position2.y + scale2.y && position1.y >= position2.y - scale2.y) {								// object1 middenin object 2
						if (position1.z <= position2.z + scale2.z && position1.z >= position2.z - scale2.z) {							// object1 middenin object 2
							collision = true;
						}
					}
				}
				if (position1.x <= position2.x + scale2.x && position1.x >= position2.x - scale2.x) {									// object1 middenin object 2
					xcol = true;
					if (position1.y +scale1.y >= position2.y - scale2.y && position1.y +scale1.y <= position2.y + scale2.y) {			//bovenste punt object 1 in object 2
						ycol = true;
						if (position1.z +scale1.z >= position2.z - scale2.z && position1.z +scale1.z <= position2.z + scale2.z) {		//voorste punt object 1 in object 2
							zcol = true;
							collision = true;
						}
					}
				}
				if (position1.x <= position2.x + scale2.x && position1.x >= position2.x - scale2.x) {									// object1 middenin object 2
					xcol = true;
					if (position1.y +scale1.y >= position2.y - scale2.y && position1.y +scale1.y <= position2.y + scale2.y) {			//bovenste punt object 1 in object 2
						ycol = true;
						if (position1.z <= position2.z + scale2.z && position1.z >= position2.z - scale2.z) {							// object1 middenin object 2
							collision = true;
						}
					}
				}
				if (position1.x <= position2.x + scale2.x && position1.x >= position2.x - scale2.x) {									// object1 middenin object 2
					xcol = true;
					if (position1.y <= position2.y + scale2.y && position1.y >= position2.y - scale2.y) {								// object1 middenin object 2
						if (position1.z +scale1.z >= position2.z - scale2.z && position1.z +scale1.z <= position2.z + scale2.z) {		//voorste punt object 1 in object 2
							zcol = true;
							collision = true;
						}
					}
				}
				if (position1.x <= position2.x + scale2.x && position1.x >= position2.x - scale2.x) {									// object1 middenin object 2
					xcol = true;
					if (position1.y <= position2.y + scale2.y && position1.y >= position2.y - scale2.y) {								// object1 middenin object 2
						if (position1.z <= position2.z + scale2.z && position1.z >= position2.z - scale2.z) {							// object1 middenin object 2
							collision = true;
						}
					}
				}
	//
	//
	//
	//VAN 1 NAAR 2
	//
	//
				
				if (position2.x +scale2.x >= position1.x - scale1.x && position2.x +scale2.x <= position1.x + scale1.x) {				//rechterpunt object 2 in object 1
					if (position2.y +scale2.y >= position1.y - scale1.y && position2.y +scale2.y <= position1.y + scale1.y) {			//bovenste punt object 2 in object 1
						ycol = true;
						if (position2.z +scale2.z >= position1.z - scale1.z && position2.z +scale2.z <= position1.z + scale1.z) {		//voorste punt object 2 in object 1
							zcol = true;
							collision = true;
						}
					}
				}
				if (position2.x +scale2.x >= position1.x - scale1.x && position2.x +scale2.x <= position1.x + scale1.x) {				//rechterpunt object 2 in object 1
					xcol = true;
					if (position2.y +scale2.y >= position1.y - scale1.y && position2.y +scale2.y <= position1.y + scale1.y) {			//bovenste punt object 2 in object 1
						ycol = true;
						if (position2.z - scale2.z <= position1.z + scale1.z && position2.z - scale2.z >= position1.z - scale1.z) { 	// achterste punt object 2 in object 1
							zcol = true;
							collision = true;
						}
					}
				}
				if (position2.x +scale2.x >= position1.x - scale1.x && position2.x +scale2.x <= position1.x + scale1.x) {				//rechterpunt object 2 in object 1
					xcol = true;
					if (position2.y - scale2.y <= position1.y + scale1.y && position2.y - scale2.y >= position1.y - scale1.y) {			//onderste punt object 2 in object 1
						ycol = true;
						if (position2.z +scale2.z >= position1.z - scale1.z && position2.z +scale2.z <= position1.z + scale1.z) {		//voorste punt object 2 in object 1
							zcol = true;
							collision = true;
						}
					}
				}
				if (position2.x +scale2.x >= position1.x - scale1.x && position2.x +scale2.x <= position1.x + scale1.x) {				//rechterpunt object 2 in object 1
					xcol = true;
					if (position2.y - scale2.y <= position1.y + scale1.y && position2.y - scale2.y >= position1.y - scale1.y) {			//onderste punt object 2 in object 1
						ycol = true;
						if (position2.z - scale2.z <= position1.z + scale1.z && position2.z - scale2.z >= position1.z - scale1.z) { 	// achterste punt object 2 in object 1
							zcol = true;
							collision = true;
						}
					}
				}
				if (position2.x +scale2.x >= position1.x - scale1.x && position2.x +scale2.x <= position1.x + scale1.x) {				//rechterpunt object 2 in object 1
					xcol = true;
					if (position2.y - scale2.y <= position1.y + scale1.y && position2.y - scale2.y >= position1.y - scale1.y) {			//onderste punt object 2 in object 1
						ycol = true;
						if (position2.z <= position1.z + scale1.z && position2.z >= position1.z - scale1.z) {							// object2 middenin object 1
							collision = true;
						}
					}
				}
				if (position2.x +scale2.x >= position1.x - scale1.x && position2.x +scale2.x <= position1.x + scale1.x) {				//rechterpunt object 2 in object 1
					xcol = true;
					if (position2.y <= position1.y + scale1.y && position2.y >= position1.y - scale1.y) {								// object2 middenin object 1
						ycol = true;
						if (position2.z - scale2.z <= position1.z + scale1.z && position2.z - scale2.z >= position1.z - scale1.z) { 	// achterste punt object 2 in object 1
							zcol = true;
							collision = true;
						}
					}
				}
				if (position2.x +scale2.x >= position1.x - scale1.x && position2.x +scale2.x <= position1.x + scale1.x) {				//rechterpunt object 2 in object 1
					xcol = true;
					if (position2.y <= position1.y + scale1.y && position2.y >= position1.y - scale1.y) {								// object2 middenin object 1
						ycol = true;
						if (position2.z <= position1.z + scale1.z && position2.z >= position1.z - scale1.z) {							// object2 middenin object 1
							collision = true;
						}
					}
				}
				if (position2.x <= position1.x + scale1.x && position2.x >= position1.x - scale1.x) {									// object2 middenin object 1					
					if (position2.y - scale2.y <= position1.y + scale1.y && position2.y - scale2.y >= position1.y - scale1.y) {			//onderste punt object 2 in object 1
						ycol = true;
						if (position2.z - scale2.z <= position1.z + scale1.z && position2.z - scale2.z >= position1.z - scale1.z) { 	// achterste punt object 2 in object 1
							zcol = true;
							collision = true;
						}
					}
				}
				if (position2.x <= position1.x + scale1.x && position2.x >= position1.x - scale1.x) {									// object2 middenin object 1					
					if (position2.y - scale2.y <= position1.y + scale1.y && position2.y - scale2.y >= position1.y - scale1.y) {			//onderste punt object 2 in object 1
						ycol = true;
						if (position2.z <= position1.z + scale1.z && position2.z >= position1.z - scale1.z) {							// object2 middenin object 1
							collision = true;
						}
					}
				}
				if (position2.x <= position1.x + scale1.x && position2.x >= position1.x - scale1.x) {									// object2 middenin object 1					
					if (position2.y <= position1.y + scale1.y && position2.y >= position1.y - scale1.y) {								// object2 middenin object 1
						if (position2.z - scale2.z <= position1.z + scale1.z && position2.z - scale2.z >= position1.z - scale1.z) { 	// achterste punt object 2 in object 1
							zcol = true;
							collision = true;
						}
					}
				}
				if (position2.x <= position1.x + scale1.x && position2.x >= position1.x - scale1.x) {									// object2 middenin object 1					
					if (position2.y <= position1.y + scale1.y && position2.y >= position1.y - scale1.y) {								// object2 middenin object 1
						if (position2.z <= position1.z + scale1.z && position2.z >= position1.z - scale1.z) {							// object2 middenin object 1
							collision = true;
						}
					}
				}
				
				
		//
		//
				if (position2.x - scale2.x <= position1.x + scale1.x && position2.x - scale2.x >= position1.x - scale1.x) {				//linkerpunt object 2 in object 1
					xcol = true;
					if (position2.y +scale2.y >= position1.y - scale1.y && position2.y +scale2.y <= position1.y + scale1.y) {			//bovenste punt object 2 in object 1
						ycol = true;
						if (position2.z +scale2.z >= position1.z - scale1.z && position2.z +scale2.z <= position1.z + scale1.z) {		//voorste punt object 2 in object 1
							zcol = true;
							collision = true;
						}
					}
				}
				if (position2.x - scale2.x <= position1.x + scale1.x && position2.x - scale2.x >= position1.x - scale1.x) {				//linkerpunt object 2 in object 1
					xcol = true;
					if (position2.y +scale2.y >= position1.y - scale1.y && position2.y +scale2.y <= position1.y + scale1.y) {			//bovenste punt object 2 in object 1
						ycol = true;
						if (position2.z - scale2.z <= position1.z + scale1.z && position2.z - scale2.z >= position1.z - scale1.z) { 	// achterste punt object 2 in object 1
							zcol = true;
							collision = true;
						}
					}
				}
				if (position2.x - scale2.x <= position1.x + scale1.x && position2.x - scale2.x >= position1.x - scale1.x) {				//linkerpunt object 2 in object 1
					xcol = true;
					if (position2.y - scale2.y <= position1.y + scale1.y && position2.y - scale2.y >= position1.y - scale1.y) {			//onderste punt object 2 in object 1
						ycol = true;
						if (position2.z +scale2.z >= position1.z - scale1.z && position2.z +scale2.z <= position1.z + scale1.z) {		//voorste punt object 2 in object 1
							zcol = true;
							collision = true;
						}
					}
				}
				if (position2.x - scale2.x <= position1.x + scale1.x && position2.x - scale2.x >= position1.x - scale1.x) {				//linkerpunt object 2 in object 1
					xcol = true;
					if (position2.y - scale2.y <= position1.y + scale1.y && position2.y - scale2.y >= position1.y - scale1.y) {			//onderste punt object 2 in object 1
						ycol = true;
						if (position2.z - scale2.z <= position1.z + scale1.z && position2.z - scale2.z >= position1.z - scale1.z) { 	// achterste punt object 2 in object 1
							zcol = true;
							collision = true;
						}
					}
				}
				if (position2.x - scale2.x <= position1.x + scale1.x && position2.x - scale2.x >= position1.x - scale1.x) {				//linkerpunt object 2 in object 1
					xcol = true;
					if (position2.y +scale2.y >= position1.y - scale1.y && position2.y +scale2.y <= position1.y + scale1.y) {			//bovenste punt object 2 in object 1
						ycol = true;
						if (position2.z <= position1.z + scale1.z && position2.z >= position1.z - scale1.z) {							// object2 middenin object 1
							collision = true;
						}
					}
				}
				if (position2.x - scale2.x <= position1.x + scale1.x && position2.x - scale2.x >= position1.x - scale1.x) {				//linkerpunt object 2 in object 1
					xcol = true;
					if (position2.y <= position1.y + scale1.y && position2.y >= position1.y - scale1.y) {								// object2 middenin object 1
						if (position2.z +scale2.z >= position1.z - scale1.z && position2.z +scale2.z <= position1.z + scale1.z) {		//voorste punt object 2 in object 1
							zcol = true;
							collision = true;
						}
					}
				}
				if (position2.x - scale2.x <= position1.x + scale1.x && position2.x - scale2.x >= position1.x - scale1.x) {				//linkerpunt object 2 in object 1
					xcol = true;
					if (position2.y <= position1.y + scale1.y && position2.y >= position1.y - scale1.y) {								// object2 middenin object 1
						if (position2.z <= position1.z + scale1.z && position2.z >= position1.z - scale1.z) {							// object2 middenin object 1
							collision = true;
						}
					}
				}
				if (position2.x <= position1.x + scale1.x && position2.x >= position1.x - scale1.x) {									// object2 middenin object 1
					xcol = true;
					if (position2.y +scale2.y >= position1.y - scale1.y && position2.y +scale2.y <= position1.y + scale1.y) {			//bovenste punt object 2 in object 1
						ycol = true;
						if (position2.z +scale2.z >= position1.z - scale1.z && position2.z +scale2.z <= position1.z + scale1.z) {		//voorste punt object 2 in object 1
							zcol = true;
							collision = true;
						}
					}
				}
				if (position2.x <= position1.x + scale1.x && position2.x >= position1.x - scale1.x) {									// object2 middenin object 1
					xcol = true;
					if (position2.y +scale2.y >= position1.y - scale1.y && position2.y +scale2.y <= position1.y + scale1.y) {			//bovenste punt object 2 in object 1
						ycol = true;
						if (position2.z <= position1.z + scale1.z && position2.z >= position1.z - scale1.z) {							// object2 middenin object 1
							collision = true;
						}
					}
				}
				if (position2.x <= position1.x + scale1.x && position2.x >= position1.x - scale1.x) {									// object2 middenin object 1
					xcol = true;
					if (position2.y <= position1.y + scale1.y && position2.y >= position1.y - scale1.y) {								// object2 middenin object 1
						if (position2.z +scale2.z >= position1.z - scale1.z && position2.z +scale2.z <= position1.z + scale1.z) {		//voorste punt object 2 in object 1
							zcol = true;
							collision = true;
						}
					}
				}
				if (position2.x <= position1.x + scale1.x && position2.x >= position1.x - scale1.x) {									// object2 middenin object 1
					xcol = true;
					if (position2.y <= position1.y + scale1.y && position2.y >= position1.y - scale1.y) {								// object2 middenin object 1
						if (position2.z <= position1.z + scale1.z && position2.z >= position1.z - scale1.z) {							// object2 middenin object 1
							collision = true;
						}
					}
				}
				
				
				
				
				
				
				
	//			boolean collisionX = position1.x + scale1.x >= position2.x && position2.x + scale2.x >= position1.x;
	//			boolean collisionY = position1.y + scale1.y >= position2.y && position2.y + scale2.y >= position1.y;
	//			boolean collisionZ = position1.z + scale1.z >= position2.z && position2.z + scale2.z >= position1.z;
	//			if (collisionX && collisionY && collisionZ) {
	//				collision = true;
	//			}
	//			else {
	//				collision = false;
	//			}
	
//				return collision;
//				if (position1.x <= position2.x + scale2.x && position1.x >= position2.x - scale2.x) {									// object1 middenin object 2
//					if (position1.y <= position2.y + scale2.y && position1.y >= position2.y - scale2.y) {								// object1 middenin object 2
//						if (position1.z <= position2.z + scale2.z && position1.z >= position2.z - scale2.z) {							// object1 middenin object 2
//							collision = true;
//						}
//					}
//				}
//				if (position2.x <= position1.x + scale1.x && position2.x >= position1.x - scale1.x) {									// object2 middenin object 1
//					if (position2.y <= position1.y + scale1.y && position2.y >= position1.y - scale1.y) {								// object2 middenin object 1
//						if (position2.z <= position1.z + scale1.z && position2.z >= position1.z - scale1.z) {							// object2 middenin object 1
//							collision = true;
//						}
//					}
//				}
//			}
		
			}
			return collision;
		}
}


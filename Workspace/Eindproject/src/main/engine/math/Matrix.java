package main.engine.math;

import com.sun.tools.sjavac.comp.dependencies.PublicApiCollector;

public class Matrix {
	float[][] matrix;

	public static float[][] matmul(float[][] mat1, float[][] mat2) {
		if (mat1[0].length != mat2.length) {
			return null;
		}
		float[][] mat3 = new float[mat1.length][mat2[0].length];
		for (int i = 0; i < mat3.length; i++) {
			for (int x = 0; x < mat3[0].length; x++) {
				float sum = 0;
				for (int k = 0; k < mat3.length; k++) {
					sum = sum + (mat1[i][k] * mat2[k][x]);
				}
				mat3[i][x] = sum;
			}
		}
		return mat3;
	}

	public static float[][] identity() {
		float[][] outMatrix = { 
				{ 1, 0, 0, 0 }, 
				{ 0, 1, 0, 0 }, 
				{ 0, 0, 1, 0 },
				{ 0, 0, 0, 1 } };
		return outMatrix;
	}

	public static float[][] translate(Vector3 translation) {
		float[][] outMatrix = { 
				{ 1.0f, 0.0f, 0.0f, translation.x }, 
				{ 0.0f, 1.0f, 0.0f, translation.y },
				{ 0.0f, 0.0f, 1.0f, translation.z }, 
				{ 0.0f, 0.0f, 0.0f, 1.0f } };

		return outMatrix;
	}
	
	public static float[][] scale(Vector3 scalar) {
		float[][] outMatrix = { 
				{ scalar.x, 0, 0, 0 }, 
				{ 0, scalar.y, 0, 0 }, 
				{ 0, 0, scalar.z, 0 },
				{ 0, 0, 0, 1 } };

		return outMatrix;
	}

	public static float[][] rotate(Vector3 axis, float angle) {
		float cos = (float) Math.cos(Math.toRadians(angle));
		float sin = (float) Math.sin(Math.toRadians(angle));

		float[][] outMatrix = {
				{ cos + axis.x * axis.x * (1 - cos), axis.x * axis.y * (1 - cos) - axis.z * sin,
						axis.x * axis.z * (1 - cos) + axis.y * sin, 0 },
				{ axis.y * axis.x * (1 - cos) + axis.z * sin, cos + axis.y * axis.y * (1 - cos),
						axis.y * axis.z * (1 - cos) - axis.x * sin, 0 },
				{ axis.z * axis.x * (1 - cos) - axis.y * sin, axis.z * axis.y * (1 - cos) + axis.x * sin,
						cos + axis.z * axis.z * (1 - cos), 0 },
				{ 0, 0, 0, 1 } };

		return outMatrix;
	}
	
	public static float[][] transform(Vector3 position, Vector3 rotation, Vector3 scale) {
		float[][] translationMat = Matrix.translate(position);
		float[][] rotxMat = Matrix.rotate(new Vector3(1, 0, 0), rotation.x);
		float[][] rotyMat = Matrix.rotate(new Vector3(0, 1, 0), rotation.y);
		float[][] rotzMat = Matrix.rotate(new Vector3(0, 0, 1), rotation.z);
		float[][] scaleMat = Matrix.scale(scale);
		
		float[][] outMatrix = Matrix.matmul(Matrix.matmul(translationMat, scaleMat), Matrix.matmul(rotxMat, Matrix.matmul(rotyMat, rotzMat)));

		return outMatrix;
	}
	
	public static float[] toFloatArr(float[][] matrix) {
		float[] newMat = new float[matrix.length * matrix[0].length];
		int index = 0;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				newMat[index] = matrix[i][j];
				index++;
			}
		}
		
		return newMat;
	}
}

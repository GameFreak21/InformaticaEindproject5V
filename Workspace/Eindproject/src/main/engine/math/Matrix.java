package main.engine.math;

public class Matrix {
	float[][] matrix;
	public Matrix() {
			
	}
	
	public float [][] matmul(float[][] mat1, float[][] mat2) {
		if (mat1[0].length != mat2.length) {
			return null;
		}
		float[][] mat3 = new float [mat1.length][mat2[0].length];
		for (int i = 0; i < mat3.length; i++) {
				for (int x = 0; x < mat3[0].length; x++) {
						float sum = 0;
						for (int k = 0; k < mat3.length; k++) {
							sum = sum + (mat1[i][k]*mat2[k][x]);
						}
					mat3[i][x] = sum;	
				}
		}
		return mat3;
	}
}

package main.engine.renderer.graphics;

import main.engine.math.Vector3;
import main.engine.math.Vector2;

public class Vertex {
	public Vector3 position, normal, color;
	public Vector2 texturecoords;
	
	public Vertex(Vector3 position, Vector3 normal, Vector3 color, Vector2 texturecoords) {
		this.position = position;
		this.normal = normal;
		this.color = color;
		this.texturecoords = texturecoords;
	}
	
}

package main.engine.renderer.graphics;

import main.engine.math.Vector3;
import main.engine.math.Vector2;

public class Vertex {
	public Vector3 position, normal, color;
	public Vector2 textureCoords;
	
	public Vertex(Vector3 position, Vector3 normal, Vector3 color, Vector2 textureCoords) {
		this.position = position;
		this.normal = normal;
		this.color = color;
		this.textureCoords = textureCoords;
	}
	
}

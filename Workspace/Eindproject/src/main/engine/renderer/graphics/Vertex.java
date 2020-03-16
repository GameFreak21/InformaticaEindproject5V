package main.engine.renderer.graphics;

import main.engine.math.Vector3;

public class Vertex {
	public Vector3 position, color;
	
	public Vertex(Vector3 position, Vector3 color) {
		this.position = position;
		this.color = color;
	}
}

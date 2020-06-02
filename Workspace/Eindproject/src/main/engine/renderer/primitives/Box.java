package main.engine.renderer.primitives;

import main.engine.math.Time;
import main.engine.math.Vector2;
import main.engine.math.Vector3;
import main.engine.renderer.graphics.Material;
import main.engine.renderer.graphics.Mesh;
import main.engine.renderer.graphics.Vertex;
import main.engine.objects.GameObject;

public class Box extends GameObject {

	public Box(Vector3 position, Vector3 rotation, Vector3 scale, float mass, Vector3 speed, boolean collisions, Mesh box_mesh) {
		super(position, rotation, scale, box_mesh, mass, speed, collisions);
	}

	boolean flip = false;

	@Override
	public void update() {
		if (position.y > 10 || position.y < -10)
			flip = !flip;
		if (!flip)
			position = Vector3.add(position, new Vector3(0, (float) (-5 * Time.deltaTime), 0));
		else
			position = Vector3.subtract(position, new Vector3(0, (float) (-5 * Time.deltaTime), 0));
	}
}

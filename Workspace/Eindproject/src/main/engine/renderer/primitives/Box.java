package main.engine.renderer.primitives;

import main.engine.io.Window;
import main.engine.math.Vector3;
import org.lwjgl.opengl.GL31;

public class Box {
	
	Vector3 pos;
	Vector3 size;
	Window window;
	
	public Box(Vector3 pos, Vector3 size, Window window){
		this.pos = pos;
		this.size = size;
		this.window = window;
	}
	public Box(Vector3 pos ,float len, Window window){
		this.pos = pos;
		this.size = new Vector3(len,len,len);
		this.window = window;
	}
}

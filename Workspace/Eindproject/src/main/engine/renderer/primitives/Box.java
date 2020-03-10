package main.engine.renderer.primitives;
import main.engine.math.Vector3;

public class Box {
	Vector3 pos;
	Vector3 size;
	
	Box(Vector3 pos, Vector3 size){
		this.pos = pos;
		this.size = size;
	}
	Box(Vector3 pos ,float len){
		this.pos = pos;
		this.size = new Vector3(len,len,len);
	}
}

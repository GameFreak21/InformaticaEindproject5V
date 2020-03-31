package main.engine.math;

import org.lwjgl.glfw.GLFW;

public class Time {
	
	private double oldTime;
	
	public static double deltaTime;
	
	public Time() {
		oldTime = GLFW.glfwGetTime();
	}
	
	public void update() {
		deltaTime = GLFW.glfwGetTime() - oldTime;
		oldTime = GLFW.glfwGetTime();
	}
}

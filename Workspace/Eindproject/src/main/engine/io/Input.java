package main.engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

public class Input {
	
	private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
	private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
	
	public static double mouseX, mouseY;
	public static double scrollX, scrollY;
	
	public GLFWKeyCallback keyboardCallback;
	public GLFWCursorPosCallback mousePosCallback;
	public GLFWMouseButtonCallback mouseButtonCallback;
	public GLFWScrollCallback mouseScrollCallback;
	
	public Input() {
		keyboardCallback = new GLFWKeyCallback() {
			public void invoke(long window, int key, int scancode, int action, int mods) {
				keys[key] = (action != GLFW.GLFW_RELEASE);
			}
		};
		mousePosCallback = new GLFWCursorPosCallback() {
			public void invoke(long window, double xpos, double ypos) {
				mouseX = xpos;
				mouseY = ypos;
			}
		};
		mouseButtonCallback = new GLFWMouseButtonCallback() {
			public void invoke(long window, int button, int action, int mods) {
				buttons[button] = (action != GLFW.GLFW_RELEASE);
			}
		};
		mouseScrollCallback = new GLFWScrollCallback() {
			public void invoke(long window, double offsetX, double offsetY) {
				scrollX += offsetX;
				scrollY += offsetY;
			}
		};
	}
	
	public static boolean keyDown(int key) {
		return keys[key];
	}
	
	public static boolean mouseButtonDown(int button) {
		return buttons[button];
	}
	
	public void destroy() {
		keyboardCallback.free();
		mouseButtonCallback.free();
		mousePosCallback.free();
		mouseScrollCallback.free();
	}
}

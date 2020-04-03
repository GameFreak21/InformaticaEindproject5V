package main.engine.renderer.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL31;
//import org.newdawn.slick.opengl.Texture;
//import org.newdawn.slick.opengl.TextureLoader;

public class TextureLoader {
	public int height, width, texture;
	public ByteBuffer buffer;
	String filename;
	
	public TextureLoader(String filename) {
		this.filename = filename;
		LoadTexture(filename);
	}
	public void LoadTexture(String filename) {
//		Texture texture = null;
//		try {
//			texture = TextureLoader.getTexture(filename.split("[.]")[1], material.class.getResourceAsStream(filename));
//		} catch(FileNotFoundException e) {
//			e.printStackTrace();
//		}catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		
//		
		BufferedImage img;
		try {
			img = ImageIO.read(new File(filename));			
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		width = img.getWidth();
		height = img.getHeight();
		int[] pixels = img.getRGB(0, 0, width, height, null, 0, width);
		
		buffer = BufferUtils.createByteBuffer(width*height*3);
		texture = GL31.glGenTextures();
		
		for (int i =0; i < pixels.length; i++) {
			byte red = (byte)(pixels[i] >> 16 & 0xFF);
			byte green = (byte)(pixels[i] >> 8 & 0xFF);
			byte blue = (byte)((pixels[i]) & 0xFF); 
			buffer.put(red);
			buffer.put(green);
			buffer.put(blue);
		}
		buffer.flip();
		
	}	

}

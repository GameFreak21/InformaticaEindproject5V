package main.engine.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtils {
	public static String loadAsString(String path){
		StringBuilder stringBuilder = new StringBuilder();
		//try catch for if there is no file
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(FileUtils.class.getModule().getResourceAsStream(path)))){
			String line = "";
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line).append("\n");
			}
		} catch (IOException e) {
			System.err.println("File not found!");
		}
		return stringBuilder.toString();
	}
}

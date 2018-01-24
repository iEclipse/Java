/*
 * cs445 Project 1
 * Drawings Lines, Circles, & Ellipses using LWJGL
 * Andrew Trang
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;

public class Main {

	// Main Method
	public static void main(String[] args) {
		try {
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		// Updates Window
		// Closes if ESC key or X button on top right is pressed
		while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			readFile();
			Display.update();
		}
		Display.destroy();
	}

	// Reads Coordinates File
	static void readFile() {
		File f = new File("coordinates.txt");
		String current;
		int coords[];
		try {
			Scanner sc = new Scanner(f);
			while (sc.hasNext()) {
				current = sc.nextLine();
				coords = convertToIntArray(current.substring(2, current.length()).split(",| "));
				switch (current.charAt(0)) {
				case 'l':
					drawLine(coords);
					break;
				case 'c':
					drawCircle(coords);
					break;
				case 'e':
					drawEllipse(coords);
					break;
				}
			}
			sc.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	// Converts String[] to Int[] Array for Future Functions
	static int[] convertToIntArray(String[] temp) {
		int[] array = new int[temp.length];
		for (int i = 0; i < array.length; i++)
			array[i] = Integer.parseInt(temp[i]);
		return array;
	}

	// Draws Lines Given Vertices [X1, Y1, X2, Y2]
	static void drawLine(int[] coords) {
		GL11.glColor3f(1, 0, 0);
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex2f(-1f + (float) coords[0] / 320, -1f + (float) coords[1] / 240);
		GL11.glVertex2f(-1f + (float) coords[2] / 320, -1f + (float) coords[3] / 240);
		GL11.glEnd();
	}

	// Draws Circle Given [CenterX, CenterY, Radius]
	static void drawCircle(int[] coords) {
		GL11.glColor3f(0, 0, 1);
		GL11.glBegin(GL11.GL_LINE_LOOP);
		for (int i = 0; i < 360; i++)
			GL11.glVertex2f(-1f + (float) ((Math.cos(i * 3.14159 / 180) * coords[2]) + coords[0]) / 320,
					-1f + (float) ((Math.sin(i * 3.14159 / 180) * coords[2]) + coords[1]) / 240);
		GL11.glEnd();
	}

	// Draws Ellipse Given [CenterX, CenterY, RadiusX, RadiusY]
	static void drawEllipse(int[] coords) {
		GL11.glColor3f(0, 1, 0);
		GL11.glBegin(GL11.GL_LINE_LOOP);
		for (int i = 0; i < 360; i++)
			GL11.glVertex2f(-1f + (float) ((Math.cos(i * 3.14159 / 180) * coords[2]) + coords[0]) / 320,
					-1f + (float) ((Math.sin(i * 3.14159 / 180) * coords[3]) + coords[1]) / 240);
		GL11.glEnd();
	}
}
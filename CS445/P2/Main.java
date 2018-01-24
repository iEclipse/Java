
/*
 * CS445 Project 2
 * Transform & Filling Complex Objects Using Scanline & LWJGL
 * Andrew Trang
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;

public class Main {

	static Stack<String> input = new Stack<>(); // String Input
	static ArrayList<Integer> coords = new ArrayList<>(); // Current Polygon

	// Main Method
	public static void main(String[] args) {
		try {
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.create();
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(-320, 320, -240, 240, -1, 1);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		// GL11.glLoadIdentity();

		// Updates Window
		// Closes if ESC key or X button on top right is pressed
		while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			readFile();
			parseCommands();
		}
		Display.destroy();
	}

	// Reads Coordinates File
	static void readFile() {
		File f = new File("coordinates.txt");
		try {
			Scanner sc = new Scanner(f);
			while (sc.hasNext()) {
				input.push(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	static void parseCommands() {
		String[] cur;
		GL11.glPushMatrix();
		while (!input.isEmpty()) {
			cur = input.pop().split(" ");
			if (cur.length == 2) {
				// Add coordinate to polygon
				coords.add(Integer.parseInt(cur[0]));
				coords.add(Integer.parseInt(cur[1]));
			} else
				switch (cur[0].charAt(0)) {
				// Draw Polygon w/Transforms
				case 'P':
					// Set color
					GL11.glColor3f(Float.parseFloat(cur[1]), Float.parseFloat(cur[2]), Float.parseFloat(cur[3]));
					drawPoly();
					GL11.glPopMatrix();
					coords.clear();
					GL11.glPushMatrix();
					break;
				case 't':
					// Translate
					GL11.glTranslatef(Float.parseFloat(cur[1]), Float.parseFloat(cur[2]), 0f);
					break;
				case 'r':
					// Rotate
					GL11.glRotatef(Float.parseFloat(cur[1]), Float.parseFloat(cur[2]), Float.parseFloat(cur[3]), 1f);
					break;
				case 's':
					// Scale
					GL11.glScalef(Float.parseFloat(cur[1]), Float.parseFloat(cur[2]), 1f);
					break;
				}
		}
		// Displays Shapes
		Display.update();
	}

	// Draws Lines Given Vertices
	static void drawPoly() {
		// Outline Shape
		GL11.glBegin(GL11.GL_LINE_LOOP);
		for (int i = 0; i < coords.size() / 2; i++)
			GL11.glVertex2f((float) coords.get(2 * i), (float) coords.get(2 * i + 1));
		GL11.glEnd();

		// All Edge Table
		ArrayList<Edge> all = new ArrayList<>();
		for (int i = 1; i < coords.size() / 2; i++) {
			Edge e = new Edge();
			e.ymin = coords.get(2 * i + 1) > coords.get(2 * i - 1) ? coords.get(2 * i - 1) : coords.get(2 * i + 1);
			e.ymax = coords.get(2 * i + 1) < coords.get(2 * i - 1) ? coords.get(2 * i - 1) : coords.get(2 * i + 1);
			e.xval = coords.get(2 * i + 1) > coords.get(2 * i - 1) ? coords.get(2 * i - 2) : coords.get(2 * i);
			e.slope = (((double) coords.get(2 * i - 1) - coords.get(2 * i + 1))
					/ (coords.get(2 * i - 2) - coords.get(2 * i)));
			all.add(e);
		}

		// Global Edge Table
		ArrayList<Edge> global = new ArrayList<>();
		for (int i = 0; i < all.size(); i++) {
			if (all.get(i).slope != 0)
				global.add(all.get(i));
		}

		// Set Scanline
		int scanLine = Integer.MAX_VALUE;
		for (int i = 0; i < global.size(); i++)
			if (global.get(i).ymin < scanLine)
				scanLine = global.get(i).ymin;

		ArrayList<Edge> active = new ArrayList<>();

		// Fill Shape
		while (!global.isEmpty()) {

			// Remove Finished Edges
			ArrayList<Edge> del = new ArrayList<>();
			for (int i = 0; i < active.size(); i++) {
				if (active.get(i).ymax == scanLine)
					del.add(active.get(i));
			}

			for (int i = 0; i < del.size(); i++) {
				global.remove(del.get(i));
				active.remove(del.get(i));
			}
			del.clear();

			// Add Edges to Active Edges
			for (int i = 0; i < global.size(); i++) {
				if (global.get(i).ymin == scanLine) {
					active.add(global.get(i));
					del.add(global.get(i));
				}
			}

			// Sort Active Edge Table by X Values
			active = sort(active);

			// Even Parity
			boolean parity = false;

			// Draw ScanLine
			for (int i = 0; i < active.size(); i++) {
				parity = !parity;
				if (parity) {
					GL11.glBegin(GL11.GL_LINES);
					GL11.glVertex2f((float) active.get(i).xval , (float) scanLine);
					GL11.glVertex2f((float) active.get(i + 1).xval, (float) scanLine);
					GL11.glEnd();
				}

				// Update Active Edge Table
				active.get(i).xval += 1.0 / active.get(i).slope;
			}

			// Increment Scanline
			scanLine++;
		}
	}

	// Sorts Edge List By X Value
	public static ArrayList<Edge> sort(ArrayList<Edge> e) {
		for (int i = 0; i < e.size(); i++) {
			for (int j = i; j > 0; j--) {
				if (e.get(j).compareTo(e.get(j - 1)) < 0) {
					int temp1 = e.get(j).ymin;
					int temp2 = e.get(j).ymax;
					float temp3 = e.get(j).xval;
					double temp4 = e.get(j).slope;

					e.get(j).ymin = e.get(j - 1).ymin;
					e.get(j).ymax = e.get(j - 1).ymax;
					e.get(j).xval = e.get(j - 1).xval;
					e.get(j).slope = e.get(j - 1).slope;

					e.get(j - 1).ymin = temp1;
					e.get(j - 1).ymax = temp2;
					e.get(j - 1).xval = temp3;
					e.get(j - 1).slope = temp4;
				}
			}
		}
		return e;
	}
}
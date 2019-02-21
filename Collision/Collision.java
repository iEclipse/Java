public class Collision {

	// Rectangle Collision
	// Obj1 = {MinX, MinY, MaxX, MaxY}
	// Obj2 = {MinX, MinY, MaxX, MaxY}
	public boolean isCollided(double[] obj1, double[] obj2) {
		return !(obj1[0] > obj2[2] || obj2[0] > obj1[2] || obj1[1] > obj2[3] || obj2[1] > obj1[3]);
	}

	// Circle Collision
	// Obj1 = {X,Y}
	// Obj2 = {X,Y}
	public boolean isCollided(double[] obj1, double[] obj2, double r1, double r2) {
		return (Math.sqrt(Math.pow(obj2[0] - obj1[0], 2) + Math.pow(obj2[1] - obj1[1], 2)) <= r1 + r2);
	}
}

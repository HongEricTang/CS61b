public class NBody {
	public static String path = "images/";
	public static String backgroundImage = path + "starfield.jpg";

	public static double readRadius(String fileName){

		In in = new In(fileName);
		int numberOfPlanets = in.readInt();
		double radiusOfUniverse = in.readDouble();
		return radiusOfUniverse;

	}

	public static Body[] readBodies(String fileName){

		In in = new In(fileName);
		int numberOfPlanets = in.readInt();
		double radiusOfUniverse = in.readDouble();
		Body[] bodies = new Body[numberOfPlanets];
		int i = 0;

		while (!in.isEmpty()){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = path + in.readString();

			Body newBody = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
			bodies[i] = newBody;
			i += 1;
		}

		return bodies;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String fileName = args[2];
		double radiusOfUniverse = readRadius(fileName);
		Body[] bodies = readBodies(fileName);
		int waitTimeMilliseconds = 10;

		for (double time = 0.0; time <= T; time += dt) {
			Double[] netForceX = new Double[bodies.length];
			Double[] netForceY = new Double[bodies.length];

			for (int i = 0; i < bodies.length; i++) {
				netForceX[i] = bodies[i].calcNetForceExertedByX(bodies);
				netForceY[i] = bodies[i].calcNetForceExertedByY(bodies);
			}

			for (int i = 0; i < bodies.length; i++) {
				bodies[i].update(dt, netForceX[i], netForceY[i]);
			}
			

			StdDraw.enableDoubleBuffering();
			StdDraw.setScale(-radiusOfUniverse, radiusOfUniverse);
			StdDraw.picture(0, 0, backgroundImage);

			for (Body body : bodies) {
				body.draw();
			}

			StdDraw.show();
			StdDraw.pause(waitTimeMilliseconds);
		}
	}
}
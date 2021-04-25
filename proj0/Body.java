public class Body {
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;

	public Body(double xP, double yP, double xV, double yV, double m, String img){
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Body(Body b){
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b){
		double distance;
		distance = Math.sqrt(Math.pow((this.xxPos - b.xxPos),2) + Math.pow((this.yyPos - b.yyPos),2));
		return distance;
	}

	public double calcForceExertedBy(Body b){
		double force;
		double g =  6.67e-11;
		force = g*this.mass*b.mass/Math.pow(calcDistance(b),2);
		return force;
	}

	public double calcForceExertedByX(Body b){
		double force = calcForceExertedBy(b);
		double xDistance = b.xxPos - this.xxPos;
		double distance = calcDistance(b);
		double forceX = force*xDistance/distance;
		return forceX;
	}

	public double calcForceExertedByY(Body b){
		double force = calcForceExertedBy(b);
		double yDistance = b.yyPos - this.yyPos;
		double distance = calcDistance(b);
		double forceY = force*yDistance/distance;
		return forceY;
	}

	public double calcNetForceExertedByX(Body[] allBodys){
		double sum = 0.0;
		for (int i = 0; i < allBodys.length; i++){
			if (!allBodys[i].equals(this)){
				sum += calcForceExertedByX(allBodys[i]);
			}
		}
		return sum; 
	}

	public double calcNetForceExertedByY(Body[] allBodys){
		double sum = 0.0;
		for (int i = 0; i < allBodys.length; i++){
			if (!allBodys[i].equals(this)){
				sum += calcForceExertedByY(allBodys[i]);
			}
		}
		return sum; 
	}

	public void update(double dt, double fX, double fY){
		this.xxVel = this.xxVel + dt*fX/this.mass;
		this.yyVel = this.yyVel + dt*fY/this.mass;
		this.xxPos = this.xxPos + this.xxVel*dt;
		this.yyPos = this.yyPos + this.yyVel*dt;
	}

	public void draw() {

		StdDraw.picture(xxPos, yyPos, imgFileName);

	}
}
public class BaryPoint {
	private Fraction xCoord;
	private Fraction yCoord;
	private Fraction zCoord;

	//constructors
	public BaryPoint(Fraction u, Fraction v, Fraction w) {
		Fraction coeffSum = Fraction.add(u, v, w);
		if (!coeffSum.isZero()) {
			//normalize if possible
			this.xCoord = u.divide(coeffSum);
			this.yCoord = v.divide(coeffSum);
			this.zCoord = w.divide(coeffSum);
		} else {
			this.xCoord = u;
			this.yCoord = v;
			this.zCoord = w;
		}
	}

	//construct dilation from point
	public BaryPoint dilate(BaryPoint p1, Fraction scaleFactor) {
		//first translate for convenience
		if (this.isInfinitePoint() || p1.isInfinitePoint())
			throw new IllegalArgumentException("infinite points not allowed");
		Fraction resX = Fraction.add(this.xCoord, scaleFactor.multiply(p1.xCoord.subtract(this.xCoord)));
		Fraction resY = Fraction.add(this.yCoord, scaleFactor.multiply(p1.yCoord.subtract(this.yCoord)));
		Fraction resZ = Fraction.add(this.zCoord, scaleFactor.multiply(p1.zCoord.subtract(this.zCoord)));
		return new BaryPoint(resX, resY, resZ);
	}

	//reflect center this
	public BaryPoint reflect(BaryPoint p1) {
		Fraction two = new Fraction(2, 1);
		Fraction resX = two.multiply(this.xCoord).subtract(p1.xCoord);
		Fraction resY = two.multiply(this.yCoord).subtract(p1.yCoord);
		Fraction resZ = two.multiply(this.zCoord).subtract(p1.zCoord);
		return new BaryPoint(resX, resY, resZ);
	}

	//accessors
	public boolean isInfinitePoint() {
		return Fraction.add(this.xCoord, this.yCoord, this.zCoord).isZero();
	}

	public Fraction getX() {
		return this.xCoord;
	}

	public Fraction getY() {
		return this.yCoord;
	}

	public Fraction getZ() {
		return this.zCoord;
	}

	public Fraction[] toArray() {
		Fraction[] res = {this.xCoord, this.yCoord, this.zCoord};
		return res;
	}

	public void display() {
		System.out.print("(");
		this.xCoord.display();
		System.out.print(", ");
		this.yCoord.display();
		System.out.print(", ");
		this.zCoord.display();
		System.out.println(")");
	}

	//checks if on line l
	public boolean onLine(BaryLine l) {
		Fraction res = l.getX().multiply(this.xCoord);
		res = res.add(l.getY().multiply(this.yCoord));
		res = res.add(l.getZ().multiply(this.zCoord));

		return res.isZero();
	}

	//only use this for non infinite points
	public boolean equals(BaryPoint other) {
		if (this.isInfinitePoint())
			throw new IllegalArgumentException("no infinity points plz");
		return (this.xCoord == other.xCoord && this.yCoord == other.yCoord);
		//no need to check z coord since we know same sum
	}

	//gets line through point parallel to line l
	public BaryLine drawParallel(BaryLine l) {
		BaryPoint infinity = l.getInfinitePoint();
		return new BaryLine(this, infinity);
	}

	//static methods

	//check if points are collinear
	public static boolean isCollinear(BaryPoint p1, BaryPoint p2, BaryPoint p3) {
		FractionMatrix temp = new FractionMatrix(p1, p2, p3);
		return temp.isSingular();
	}

	//construct midpoint
	public static BaryPoint midpoint(BaryPoint p1, BaryPoint p2) {
		return p1.dilate(p2, new Fraction(1, 2));
	}
}
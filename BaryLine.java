public class BaryLine {
	//ux + vy + wz = 0
	//store u, v, w
	private Fraction u;
	private Fraction v;
	private Fraction w;

	public BaryLine(Fraction u, Fraction v, Fraction w) {
		this.u = u;
		this.v = v;
		this.w = w;
	}

	//construct eq of line through 2 points
	public BaryLine(BaryPoint p1, BaryPoint p2) {
		this.u = Fraction.multiply(p1.getZ(), p2.getY()).subtract(Fraction.multiply(p2.getZ(), p1.getY()));
		this.v = Fraction.multiply(p1.getX(), p2.getZ()).subtract(Fraction.multiply(p2.getX(), p1.getZ()));
		this.w = Fraction.multiply(p1.getY(), p2.getX()).subtract(Fraction.multiply(p2.getY(), p1.getX()));
	}

	//accessors
	public Fraction getX() {
		return this.u;
	}

	public Fraction getY() {
		return this.v;
	}

	public Fraction getZ() {
		return this.w;
	}

	public Fraction[] toArray() {
		Fraction[] res = {this.u, this.v, this.w};
		return res;
	}

	public void display() {
		this.u.display();
		System.out.print("x + ");
		this.v.display();
		System.out.print("y + ");
		this.w.display();
		System.out.println("z = 0.");
	}

	//returns infinite point of line
	public BaryPoint getInfinitePoint() {
		return new BaryPoint(this.v.subtract(this.w), this.w.subtract(this.u), this.u.subtract(this.v));
	}

	public boolean isParallel(BaryLine other) {
		Fraction one = new Fraction(1);
		BaryLine infinityLine = new BaryLine(one, one, one);
		return isConcurrent(this, other, infinityLine);
	}

	
	//static methods
	public static BaryPoint intersect(BaryLine l1, BaryLine l2) {
		Fraction x = Fraction.multiply(l1.w, l2.v).subtract(Fraction.multiply(l2.w, l1.v));
		Fraction y = Fraction.multiply(l2.w, l1.u).subtract(Fraction.multiply(l1.w, l2.u));
		Fraction z = Fraction.multiply(l2.u, l1.v).subtract(Fraction.multiply(l1.u, l2.v));
		return new BaryPoint(x, y, z);
	}

	//find intersection of p1p2 and p3p4
	public static BaryPoint intersect(BaryPoint p1, BaryPoint p2, BaryPoint p3, BaryPoint p4) {
		BaryLine l1 = new BaryLine(p1, p2);
		BaryLine l2 = new BaryLine(p3, p4);
		return intersect(l1, l2);
	}

	public static boolean isConcurrent(BaryLine l1, BaryLine l2, BaryLine l3) {
		FractionMatrix temp = new FractionMatrix(l1, l2, l3);
		return temp.isSingular();
	}

	public static boolean isConcurrent(BaryPoint p1, BaryPoint p2, BaryPoint p3,
										BaryPoint p4, BaryPoint p5, BaryPoint p6) {
		BaryLine l1 = new BaryLine(p1, p2);
		BaryLine l2 = new BaryLine(p3, p4);
		BaryLine l3 = new BaryLine(p5, p6);
		return isConcurrent(l1, l2, l3);
	}
}
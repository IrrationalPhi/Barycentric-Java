public class BaryReferenceTriangle {
	//side lengths
	private Fraction a;
	private Fraction b;
	private Fraction c;
	private Fraction s;
	private Fraction s_A;
	private Fraction s_B;
	private Fraction s_C;
	private double area;

	//constructor
	public BaryReferenceTriangle (Fraction a, Fraction b, Fraction c) {
		//if any <= 0, bad
		if (a.isNonPositive() || b.isNonPositive() || c.isNonPositive())
			throw new IllegalArgumentException("side lengths must be positive");
		if (!a.isSmaller(Fraction.add(b, c)) || !b.isSmaller(Fraction.add(c, a)) || !c.isSmaller(Fraction.add(a, b)))
			throw new IllegalArgumentException("triangle ineq not satisfied");
		this.a = a;
		this.b = b;
		this.c = c;

		Fraction temp = new Fraction(2, 1);
		this.s = Fraction.add(a, b, c).divide(temp);
		Fraction aSquare = a.multiply(a);
		Fraction bSquare = b.multiply(b);
		Fraction cSquare = c.multiply(c);

		this.s_A = Fraction.add(bSquare, cSquare).subtract(aSquare).divide(temp);
		this.s_B = Fraction.add(cSquare, aSquare).subtract(bSquare).divide(temp);
		this.s_C = Fraction.add(aSquare, bSquare).subtract(cSquare).divide(temp);

		double sDo = s.toDouble();
		double aDo = a.toDouble();
		double bDo = b.toDouble();
		double cDo = c.toDouble();

		area = Math.sqrt(sDo*(sDo-aDo)*(sDo-bDo)*(sDo-cDo));
	}

	public Fraction getA() {
		return this.a;
	}

	public Fraction getB() {
		return this.b;
	}

	public Fraction getC() {
		return this.c;
	}

	//return isog conjugate of point wrt triangle
	public BaryPoint isogonalConjugate(BaryPoint p1) {
		if (p1.getX().isZero() || p1.getY().isZero() || p1.getZ().isZero())
			throw new IllegalArgumentException("isogonal conjugate DNE for zero coords");
		Fraction resX = Fraction.multiply(a, a).divide(p1.getX());
		Fraction resY = Fraction.multiply(b, b).divide(p1.getY());
		Fraction resZ = Fraction.multiply(c, c).divide(p1.getZ());
		return new BaryPoint(resX, resY, resZ);
	}

	//return isotomic conjugate of point wrt triangle
	public BaryPoint isotomicConjugate(BaryPoint p1) {
		if (p1.getX().isZero() || p1.getY().isZero() || p1.getZ().isZero())
			throw new IllegalArgumentException("isogonal conjugate DNE for zero coords");
		Fraction resX = p1.getX().reciprocal();
		Fraction resY = p1.getY().reciprocal();
		Fraction resZ = p1.getZ().reciprocal();
		return new BaryPoint(resX, resY, resZ);
	}

	public BaryLine drawPerp(BaryPoint p, BaryLine l) {
		Fraction f = l.getY().subtract(l.getZ());
		Fraction g = l.getZ().subtract(l.getX());
		Fraction h = l.getX().subtract(l.getY());

		Fraction bigF = Fraction.multiply(s_B, g).subtract(Fraction.multiply(s_C, h));
		Fraction bigG = Fraction.multiply(s_C, h).subtract(Fraction.multiply(s_A, f));
		Fraction bigH = Fraction.multiply(s_A, f).subtract(Fraction.multiply(s_B, g));

		Fraction xRes = FractionMatrix.det(bigG, bigH, p.getY(), p.getZ());
		Fraction yRes = FractionMatrix.det(bigH, bigF, p.getZ(), p.getX());
		Fraction zRes = FractionMatrix.det(bigF, bigG, p.getX(), p.getY());

		return new BaryLine(xRes, yRes, zRes);
	}

	//draw parallel from p to line ab
	public BaryLine drawParallel(BaryPoint p, BaryPoint a, BaryPoint b) {
		BaryLine ab = new BaryLine(a, b);
		return drawParallel(p, ab);
	}

	//draw perp from p to line ab
	public BaryLine drawPerp(BaryPoint p, BaryPoint a, BaryPoint b) {
		BaryLine ab = new BaryLine(a, b);
		return drawPerp(p, ab);
	}

	public Fraction getSqDistance(BaryPoint p1, BaryPoint p2) {
		if (p1.isInfinitePoint() || p2.isInfinitePoint()) 
			throw new IllegalArgumentException("distance is infinity");
		Fraction x = p2.getX().subtract(p1.getX());
		Fraction y = p2.getY().subtract(p1.getY());
		Fraction z = p2.getZ().subtract(p1.getZ());
		Fraction res = a.square().multiply(Fraction.multiply(y, z));
		res = res.add(b.square().multiply(Fraction.multiply(z, x)));
		res = res.add(c.square().multiply(Fraction.multiply(x, y)));
		return res.negate();
	}

	//get signed area of triangle with vertices p1, p2, p3
	public double getArea(BaryPoint p1, BaryPoint p2, BaryPoint p3) {
		FractionMatrix temp = new FractionMatrix(p1, p2, p3);
		double temp1 = temp.det().toDouble();
		return temp1*this.area;
	}

	//valid are entries in [-6, -1] and [1, 8]
	public BaryPoint triangleCenter(int n) {
		Fraction u, v, w;
		switch (n) {
			case -1:
				//vertex A. same with case -2, -3
				u = new Fraction(1, 1);
				v = new Fraction();
				w = new Fraction();
			break;
			case -2:
				u = new Fraction();
				v = new Fraction(1, 1);
				w = new Fraction();
			break;
			case -3:
				u = new Fraction();
				v = new Fraction();
				w = new Fraction(1, 1);
			break;
			//excenters
			case -4:
				u = a.negate();
				v = b;
				w = c;
			break;
			case -5:
				u = a;
				v = b.negate();
				w = c;
			break;
			case -6:
				u = a;
				v = b;
				w = c.negate();
			break;
			//incenter
			case 1:
				u = a;
				v = b;
				w = c;
			break;
			//centroid
			case 2: 
				Fraction temp = new Fraction(1, 1);
				u = temp;
				v = temp;
				w = temp;
			break;
			//circumcenter
			case 3:
				u = a.multiply(a).multiply(this.s_A);
				v = b.multiply(b).multiply(this.s_B);
				w = c.multiply(c).multiply(this.s_C);
			break;
			//orthocenter
			case 4:
				u = Fraction.multiply(s_B, s_C);
				v = Fraction.multiply(s_C, s_A);
				w = Fraction.multiply(s_A, s_B);
			break;
			//nine point center
			case 5: 
				Fraction foo = b.square().subtract(c.square());
				u = a.square().multiply(Fraction.add(b.square(), c.square())).subtract(foo.square());
				foo = c.square().subtract(a.square());
				v = b.square().multiply(Fraction.add(c.square(), a.square())).subtract(foo.square());
				foo = a.square().subtract(b.square());
				w = c.square().multiply(Fraction.add(a.square(), b.square())).subtract(foo.square());
			break;
			//symmedian point
			case 6:
				u = a.square();
				v = b.square();
				w = c.square();
			break;
			//gergonne point
			case 7:
				u = s.subtract(b).multiply(s.subtract(c));
				v = s.subtract(c).multiply(s.subtract(a));
				w = s.subtract(a).multiply(s.subtract(b));
			break;
			//nagel point
			case 8:
				u = s.subtract(a);
				v = s.subtract(b);
				w = s.subtract(c);
			break;
			default:
				throw new IllegalArgumentException("invalid triangle center");
		}
		return new BaryPoint(u, v, w);
	}

	public boolean isPerpendicular(BaryPoint p1, BaryPoint p2, BaryPoint p3, BaryPoint p4) {
		//perpendicularity lemma, need AC^2-AD^2=BC^2-BD^2
		Fraction dist1 = getSqDistance(p1, p3);
		Fraction dist2 = getSqDistance(p1, p4);
		Fraction dist3 = getSqDistance(p2, p3);
		Fraction dist4 = getSqDistance(p2, p4);

		Fraction temp1 = dist1.subtract(dist2);
		Fraction temp2 = dist3.subtract(dist4);

		return temp1.equals(temp2);
	}

	//TODO
	//public boolean isPerpendicular(BaryLine l1, BaryLine l2) {
		//select convenient points on line, and check if perp
	//}

	public boolean equalLength(BaryPoint p1, BaryPoint p2, BaryPoint p3, BaryPoint p4) {
		Fraction temp1 = getSqDistance(p1, p2);
		Fraction temp2 = getSqDistance(p3, p4);

		return temp1.equals(temp2);
	}

	//static methods
	public static BaryLine drawParallel(BaryPoint p, BaryLine l) {
		return p.drawParallel(l);
	}

	public static boolean isParallel(BaryLine l1, BaryLine l2) {
		return l1.isParallel(l2);
	}

	public static boolean isParallel(BaryPoint p1, BaryPoint p2, BaryPoint p3, BaryPoint p4) {
		BaryLine l1 = new BaryLine(p1, p2);
		BaryLine l2 = new BaryLine(p3, p4);
		return isParallel(l1, l2);
	}

	//TODO
	//intersect with line through given a point on the circle
	//public static BaryPoint intersect(Barycircle c, BaryLine l, BaryPoint p) {
		
	//}
}
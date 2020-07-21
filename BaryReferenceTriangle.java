public class BaryReferenceTriangle {
	//side lengths
	private Fraction a;
	private Fraction b;
	private Fraction c;
	private Fraction s;
	private Fraction s_A;
	private Fraction s_B;
	private Fraction s_C;

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
			default:
				throw new IllegalArgumentException("invalid triangle center");
		}
		return new BaryPoint(u, v, w);
	}

	//static methods
	//public static boolean isPerpendicular(BaryLine l1, BaryLine l2) {

	//}
}
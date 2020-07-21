//solves JBMO 2008 Shortlist G11
public class JBMO2008G11 {
	public static void showPoint(BaryPoint p, String s) {
		System.out.printf("%s: ", s);
		p.display();
	}

	public static void showLine(BaryLine l, String s) {
		System.out.printf("%s: ", s);
		l.display();
	}

	public static void displayConcurTest(BaryLine l1, BaryLine l2, BaryLine l3) {
		System.out.print("equation of first line is: ");
		l1.display();
		System.out.print("equation of second line is: ");
		l2.display();
		System.out.print("equation of third line is: ");
		l3.display();
		System.out.println(BaryLine.isConcurrent(l1, l2, l3));
		System.out.println();
	}

	public static void showConfig(BaryReferenceTriangle refTri) {
		System.out.print("Testing problem for the following side lengths: ");
		refTri.getA().display();
		System.out.print(", ");
		refTri.getB().display();
		System.out.print(", ");
		refTri.getC().display();
		System.out.println();
		System.out.println();
	}

	public static void main(String[] args) {
		System.out.println("solves JBMO 2008 shortlist G11");
		System.out.println();
		//initialize reference triangle
		Fraction a = new Fraction(8);
		Fraction b = new Fraction(10);
		Fraction c = new Fraction(13);
		BaryReferenceTriangle refTri = new BaryReferenceTriangle(a, b, c);
		showConfig(refTri);

		//vertices
		BaryPoint pA = refTri.triangleCenter(-1);
		BaryPoint pB = refTri.triangleCenter(-2);
		BaryPoint pC = refTri.triangleCenter(-3);
		BaryPoint pM = BaryPoint.midpoint(pB, pC);

		BaryPoint pH = refTri.triangleCenter(4);
		BaryPoint pD = BaryLine.intersect(pB, pH, pA, pC);
		BaryPoint pE = BaryLine.intersect(pC, pH, pA, pB);

		BaryPoint pP = BaryLine.intersect(pD, pE, pB, pC);

		//line through C perp to BC
		BaryLine l1 = refTri.drawPerp(pC, pB, pC);
		//line through M perp to AC
		BaryLine l2 = refTri.drawPerp(pM, pA, pC);

		BaryPoint pR = BaryLine.intersect(l1, l2);

		//display data
		showPoint(pA, "A");
		showPoint(pB, "B");
		showPoint(pC, "C");
		showPoint(pM, "M");
		showPoint(pH, "H");
		showPoint(pD, "D");
		showPoint(pE, "E");
		showPoint(pP, "P");
		System.out.println();

		showLine(l1, "through C perp to BC");
		showLine(l2, "through M perp to AC");

		System.out.print("intersect lines at ");

		showPoint(pR, "R");

		System.out.println();
		System.out.println("verify AM, PR perpendicular");
		System.out.println(refTri.isPerpendicular(pA, pM, pP, pR));

		System.out.println();
		System.out.println("bonus! verify P, H, R collinear");
		System.out.println(BaryPoint.isCollinear(pP, pH, pR));
	}
}
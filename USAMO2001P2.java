public class USAMO2001P2 {
	public static void showPoint(BaryPoint p, String s) {
		System.out.printf("%s: ", s);
		p.display();
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
		System.out.println("solves USAMO 2001/2");
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

		//problem setup
		BaryPoint pI = refTri.triangleCenter(1);
		//nagel
		BaryPoint pN = refTri.triangleCenter(8);
		//gergonne
		BaryPoint pG = refTri.triangleCenter(7);

		BaryPoint pD1 = BaryLine.intersect(pA, pG, pB, pC);
		BaryPoint pD2 = BaryLine.intersect(pA, pN, pB, pC);
		BaryPoint pE2 = BaryLine.intersect(pB, pN, pC, pA);

		BaryPoint pQ = pI.reflect(pD1);

		showPoint(pA, "A");
		showPoint(pB, "B");
		showPoint(pC, "C");
		showPoint(pI, "I");
		showPoint(pN, "N");
		showPoint(pG, "G");
		showPoint(pD1, "D_1");
		showPoint(pD2, "D_2");
		showPoint(pE2, "E_2");

		System.out.println();
		System.out.println("bashing distance: ");
		Fraction temp1 = refTri.getSqDistance(pA, pQ);
		Fraction temp2 = refTri.getSqDistance(pN, pD2);
		System.out.print("sq length of AQ: ");
		temp1.display();
		System.out.println();
		System.out.print("sq length of ND_2: ");
		temp2.display();
		System.out.println();

		System.out.println(refTri.equalLength(pA, pQ, pN, pD2));
	}
}
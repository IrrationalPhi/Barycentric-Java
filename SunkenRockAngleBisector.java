//solves problem at https://artofproblemsolving.com/community/q1h1458179p8404063
public class SunkenRockAngleBisector {
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
		System.out.println("solves problem at https://artofproblemsolving.com/community/q1h1458179p8404063");
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
		BaryPoint pD = BaryLine.intersect(pA, pI, pB, pC);
		BaryPoint pE = BaryLine.intersect(pB, pI, pC, pA);
		BaryPoint pF = BaryLine.intersect(pC, pI, pA, pB);

		//excenter setup
		BaryPoint pX = refTri.triangleCenter(-5);
		BaryPoint pY = refTri.triangleCenter(-6);

		BaryPoint pM = BaryLine.intersect(pB, pE, pF, pD);
		BaryPoint pN = BaryLine.intersect(pC, pF, pD, pE);

		//display points
		System.out.printf("List of points: \n\n");
		showPoint(pA, "A");
		showPoint(pB, "B");
		showPoint(pC, "C");
		showPoint(pI, "I");
		showPoint(pD, "D");
		showPoint(pE, "E");
		showPoint(pF, "F");
		showPoint(pX, "X");
		showPoint(pY, "Y");
		showPoint(pM, "M");
		showPoint(pN, "N");
		System.out.println();

		//solving the problem itself

		System.out.println("check NX, AD, MY concur");

		BaryLine pNX = new BaryLine(pN, pX);
		BaryLine pAD = new BaryLine(pA, pD);
		BaryLine pMY = new BaryLine(pM, pY);

		displayConcurTest(pNX, pAD, pMY);

		System.out.println("check MY, AN , BC concur");

		BaryLine pAN = new BaryLine(pA, pN);
		BaryLine pBC = new BaryLine(pB, pC);

		displayConcurTest(pMY, pAN, pBC);

		BaryLine pAM = new BaryLine(pA, pM);

		System.out.println("check NX, AM, BC concur");
		displayConcurTest(pNX, pAM, pBC);
	}
}
public class BarySystem {
	public static void main(String[] args) {
		//side lengths of ref triangle
		Fraction a = new Fraction(8);
		Fraction b = new Fraction(10);
		Fraction c = new Fraction(13);

		//ref triangle set up
		BaryReferenceTriangle refTri = new BaryReferenceTriangle(a, b, c);
		BaryPoint pA = refTri.triangleCenter(-1);
		BaryPoint pB = refTri.triangleCenter(-2);
		BaryPoint pC = refTri.triangleCenter(-3);
		BaryPoint pI = refTri.triangleCenter(1);

		//feet of bisectors
		BaryPoint pD = BaryLine.intersect(pA, pI, pB, pC);
		BaryPoint pE = BaryLine.intersect(pB, pI, pC, pA);
		BaryPoint pF = BaryLine.intersect(pC, pI, pA, pB);

		//excenter setup
		BaryPoint pX = refTri.triangleCenter(-5);
		BaryPoint pY = refTri.triangleCenter(-6);

		BaryPoint pM = BaryLine.intersect(pB, pE, pF, pD);
		BaryPoint pN = BaryLine.intersect(pC, pF, pD, pE);

		pI.display();
		pD.display();
		pE.display();
		pF.display();
		pX.display();
		pY.display();
		pM.display();
		pN.display();

		System.out.println("check NX, AD, MY concur");

		BaryLine pNX = new BaryLine(pN, pX);
		BaryLine pAD = new BaryLine(pA, pD);
		BaryLine pMY = new BaryLine(pM, pY);

		pNX.display();
		pAD.display();
		pMY.display();

		
		System.out.println(BaryLine.isConcurrent(pNX, pAD, pMY));
		System.out.println("check MY, AN , BC concur");

		BaryLine pAN = new BaryLine(pA, pN);
		BaryLine pBC = new BaryLine(pB, pC);

		pMY.display();
		pAN.display();
		pBC.display();
		System.out.println(BaryLine.isConcurrent(pMY, pAN, pBC));

	}
}
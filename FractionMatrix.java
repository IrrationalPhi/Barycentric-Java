public class FractionMatrix {
	//only 3x3
	private Fraction[][] matrix;

	//constructors
	public FractionMatrix(Fraction[] param1, Fraction[] param2, Fraction[] param3) {
		matrix = new Fraction[3][3];
		matrix[0] = param1;
		matrix[1] = param2;
		matrix[2] = param3;
	}

	//construct based on points
	public FractionMatrix(BaryPoint p1, BaryPoint p2, BaryPoint p3) {
		this(p1.toArray(), p2.toArray(), p3.toArray());
	}

	//construct based on lines
	public FractionMatrix(BaryLine l1, BaryLine l2, BaryLine l3) {
		this(l1.toArray(), l2.toArray(), l3.toArray());
	}

	//get determinant of matrix
	public Fraction det() {
		Fraction temp1 = Fraction.multiply(matrix[1][1], matrix[2][2]);
		Fraction temp2 = Fraction.multiply(matrix[2][1], matrix[1][2]);
		Fraction term1 = Fraction.multiply(matrix[0][0], temp1.subtract(temp2));

		temp1 = Fraction.multiply(matrix[2][0], matrix[1][2]);
		temp2 = Fraction.multiply(matrix[1][0], matrix[2][2]);
		Fraction term2 = Fraction.multiply(matrix[0][1], temp1.subtract(temp2));

		temp1 = Fraction.multiply(matrix[1][0], matrix[2][1]);
		temp2 = Fraction.multiply(matrix[2][0], matrix[1][1]);
		Fraction term3 = Fraction.multiply(matrix[0][2], temp1.subtract(temp2));

		return Fraction.add(term1, term2, term3);
	}

	public boolean isSingular() {
		return this.det().isZero();
	}
}
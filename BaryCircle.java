public class BaryCircle {
	//-a^2yz-b^2zx-c^2xy+(ux+vy+wz)(x+y+z)=0
	private Fraction u;
	private Fraction v;
	private Fraction w;

	public BaryCircle(Fraction u, Fraction v, Fraction w) {
		this.u = u;
		this.v = v;
		this.w = w;
	}

	//circumcircle
	public BaryCircle() {
		this(new Fraction(), new Fraction(), new Fraction());
	}

	public void display() {
		System.out.print("the equation of the circle is: -a^2yz -b^2zx -c^2xy + (x+y+z)(");
		u.display();
		System.out.print("x + ");
		v.display();
		System.out.print("y + ");
		w.display();
		System.out.println("z) = 0. ");
	}

	//draw radical axis
	public BaryLine radicalAxis(BaryCircle other) {
		Fraction temp1 = this.u.subtract(other.u);
		Fraction temp2 = this.v.subtract(other.v);
		Fraction temp3 = this.w.subtract(other.w);

		return new BaryLine(temp1, temp2, temp3);
	}

	//TODO
	//to add horrible eq of circle given 3 points
}
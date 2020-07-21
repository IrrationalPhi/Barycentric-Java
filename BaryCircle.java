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

	public BaryCircle() {
		this(new Fraction(), new Fraction(), new Fraction());
	}

	//intersect with line through given a point on the circle
	public intersect(BaryLine l, BaryPoint p) {
		
	}
}
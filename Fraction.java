public class Fraction {
	private long nume;
	private long deno;

	//constructor
	//frac always in lowest terms
	public Fraction(long nume, long deno) {
		if (deno == 0)
			throw new IllegalArgumentException("denominator cannt be zero");

		//for convenience, let's make the deno always positive
		if (deno < 0){
			deno *= -1;
			nume *= -1;
		}
		long g = gcd(nume, deno);

		this.nume = nume/g;
		this.deno = deno/g;
	}

	public Fraction() {
		this(0,1);
	}

	public Fraction(long nume) {
		this(nume, 1);
	}

	//this + q
	public Fraction add(Fraction q) {
		return new Fraction(this.nume*q.deno + this.deno*q.nume, this.deno*q.deno);
	}

	//multiplies by -1; changes sign
	public Fraction negate() {
		return new Fraction(this.nume*-1, this.deno);
	}

	//square the value
	public Fraction square() {
		return this.multiply(this);
	}

	//this - q
	public Fraction subtract(Fraction q) {
		return new Fraction(this.nume*q.deno - q.nume*this.deno, this.deno*q.deno);
	}

	//this*q
	public Fraction multiply(Fraction q) {
		return new Fraction(this.nume*q.nume, this.deno*q.deno);
	}

	//this/q
	public Fraction divide(Fraction q) {
		if (q.isZero())
			throw new IllegalArgumentException("division by zero");
		return new Fraction(this.nume*q.deno, this.deno*q.nume);
	}

	public Fraction reciprocal() {
		if (this.isZero())
			throw new IllegalArgumentException("cannot take reciprocal of zero");
		return new Fraction(this.deno, this.nume);
	}

	//accessors
	public long getNume() {
		return this.nume;
	}

	public long getDeno() {
		return this.deno;
	}

	//checks if frac is 0
	public boolean isZero() {
		return this.nume == 0;
	}

	public void display() {
		if (this.isZero())
			System.out.print("0");
		else if (deno == 1)
			System.out.printf("%d", this.nume);
		else
			System.out.printf("%d/%d", this.nume, this.deno);
	}

	public boolean isNegative() {
		return this.nume < 0;
	}

	public boolean isNonPositive() {
		return this.nume <= 0;
	}

	public boolean isPositive() {
		return this.nume > 0;
	}

	public boolean isNonNegative() {
		return this.nume >= 0;
	}

	//returns this < q
	public boolean isSmaller(Fraction q) {
		//we know deno is always > 0
		return this.nume*q.deno < this.deno*q.nume;
	} 

	public double toDouble() {
		return (double)this.nume/this.deno;
	}

	//static methods
	//always positive gcd
	public static long gcd(long a, long b) {
		if (b == 0)
			return a;
		a = Math.abs(a);
		b = Math.abs(b);
		return gcd(b, a % b);
	}

	//add 2 or 3 fractions
	public static Fraction add(Fraction q, Fraction r) {
		return q.add(r);
	}

	public static Fraction add(Fraction q, Fraction r, Fraction p) {
		return q.add(r).add(p);
	}

	//multiply 2 or 3 fractions
	public static Fraction multiply(Fraction q, Fraction r) {
		return q.multiply(r);
	}

	public static Fraction multiply(Fraction q, Fraction r, Fraction p) {
		return q.multiply(r).multiply(p);
	}
}
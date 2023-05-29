public class Polynomial {
	double [] coeffecients;

	public void Polynomial(){
		for (int i = 0; i < coeffecients.length; i++){
			coeffecients[i] = 0;
		}
	}

	public void Polynomial(double[] coeffecients){
		for (int i = 0; i < coeffecients.length; i++){
			this.coeffecients[i] = coeffecients[i];
		}
	}

	public Polynomial add(Polynomail poly){
		for (int i = 0; i < poly.length; i++){
			this.coeffecients[i] = this.coeffecients[i] + poly[i];
		}
		return this.coeffecients;
	}

	public int evaluate(double x){
		double ret = 0;
		for (int i = 0; i < this.coeffecients.length;++){
			ret = ret + coeffecients[i] * (Math.pow(x,i));
		}
		return ret;
	}

	public boolean hasRoot(double x){
		return (evaluate(x) == 0);
	}
	

public class Polynomial {
	double[] coeffecients;

	public Polynomial(double [] coeffecient){
		int l = coeffecient.length;
		coeffecients = new double[l];

		for (int i = 0; i < l; i++){
		coeffecients[i] = coeffecient[i];
		}
	}

	public Polynomial(){
		coeffecients = new double[]{0};
	}



	public Polynomial add(Polynomial poly){
		int l = Math.min(this.coeffecients.length, poly.coeffecients.length);
		if (poly.coeffecients.length < this.coeffecients.length){
			for (int i = 0; i < l; i++){
				this.coeffecients[i] = this.coeffecients[i] + poly.coeffecients[i];
			}
			return this;
		}

		for (int i = 0; i < l; i++){
				poly.coeffecients[i] = this.coeffecients[i] + poly.coeffecients[i];
			}
			return poly;

	}

	public double evaluate(double x){
		double ret = 0;
		for (int i = 0; i < this.coeffecients.length; i++){
			ret = ret + coeffecients[i] * (Math.pow(x,i));
		}
		return ret;
	}

	public boolean hasRoot(double x){
		return (evaluate(x) == 0);
	}
	
}

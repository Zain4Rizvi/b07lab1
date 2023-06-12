import java.util.*;
import java.io.*;

public class Driver {
	public static void main(String [] args) {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,5};
		int[] c2 = {0, 3};
		Polynomial p1 = new Polynomial(c1, c2);
		double [] d1 = {-2,-9};
		int [] d2 = {1, 4};
		Polynomial p2 = new Polynomial(d1, d2);
		Polynomial s = p1.add(p2);
		Polynomial t = p1.multiply(p2);
		double [] e1 = {1};
		int [] e2 = {0};
		Polynomial v = new Polynomial(e1,e2);
		v = v.add(p1);
		v =v.multiply(t);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
			
		System.out.print("t = ");
		for (int i = 0; i < t.coeffecients.length; i++){
			System.out.print(t.coeffecients[i] + "x^("+ t.exponents[i]+") ");
		}
		

		System.out.print("\nv = ");
		for (int i = 0; i < v.coeffecients.length; i++){
			System.out.print(v.coeffecients[i] + "x^("+ v.exponents[i]+") ");
		}


	}
}

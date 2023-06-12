import java.util.*;
import java.io.*;

public class Polynomial {
        double[] coeffecients;
        int[] exponents;

        public Polynomial(double[] coeffecient, int[] exponent){
                int l = coeffecient.length;
                int k = exponent.length;

                coeffecients = new double[l];
                exponents = new int[k];

                for (int i = 0; i < l; i++){
                coeffecients[i] = coeffecient[i];
                }
                
                for (int i = 0; i < k; i++){
                        exponents[i] = exponent[i];
                }

                
        }


        public Polynomial(){
                this.coeffecients = new double[]{0};
                this.exponents = new int[]{0};
        }


        public Polynomial(File file) throws Exception{
                Scanner myscanner = new Scanner(file);

                if (!myscanner.hasNextLine()){
                        this.coeffecients = null;
                        this.exponents = null;
                }
                else{

                        String line = myscanner.nextLine();

                        line = line.replace("-", "+-");

                        String[] poly_array = line.split("+");
                        this.exponents = new int [poly_array.length];
                        this.coeffecients = new double[poly_array.length];

                        for (int i = 0; i < poly_array.length; i++){
                                String[] sub_array = poly_array[i].split("x");

                                this.coeffecients[i] = Double.parseDouble(sub_array[0]);

                                if (sub_array.length == 0){
                                        this.exponents[i] = 0;
                                }
                                else{
                                        this.exponents[i] = Integer.parseInt(sub_array[1]);
                                }
                        }

                }

                myscanner.close();;
        }


        


        public Polynomial add(Polynomial poly){
                int l = this.coeffecients.length;
                int k = poly.coeffecients.length;
                int count = 0;

                for (int i = 0; i < l; i++){
                        for (int j = 0; j < k; j++){
                                if (this.exponents[i] == poly.exponents[j]) count++;
                        }
                }

                double[] new_coeffecients = new double[l + k - count];
                int[] new_exponents = new int[l + k - count];

                int p1 = 0;
                int p2 = 0;
                for (int i = 0; i < l + k - count; i++){
                        if (p2 == k){
                                new_exponents[i] = this.exponents[p1];
                                new_coeffecients[i] = this.coeffecients[p1];
                                p1++;
                        }
                        else if(p1 == l){
                                new_exponents[i] = poly.exponents[p2];
                                new_coeffecients[i] = poly.coeffecients[p2];
                                p2++;
                        }
                        else{
                                if (this.exponents[p1] == poly.exponents[p2]){
                                        new_exponents[i] = this.exponents[p1];
                                        new_coeffecients[i] = this.coeffecients[p1] + poly.coeffecients[p2];
                                        p1++;
                                        p2++;
                                }
                                else if (this.exponents[p1] < poly.exponents[p2]){
                                        new_exponents[i] = this.exponents[p1];
                                        new_coeffecients[i] = this.coeffecients[p1];
                                        p1++;
                                }
                                else{
                                        new_exponents[i] = poly.exponents[p2];
                                        new_coeffecients[i] = poly.coeffecients[p2];
                                        p2++;
                                }
                        }
                }


                int zero_count = 0;
                for (int i = 0; i < new_coeffecients.length; i++){
                        if (new_coeffecients[i] == 0) zero_count++;
                }

                double[] final_coeffecients = new double[new_coeffecients.length - zero_count];
                int[] final_exponents = new int[new_coeffecients.length - zero_count];

                int counter = 0;
                for (int i = 0; i < new_coeffecients.length; i++){
                        if ( new_coeffecients[i] != 0){
                                final_coeffecients[counter] = new_coeffecients[i];
                                final_exponents[counter] = new_exponents[i];
                                counter++;
                        }
                }




                Polynomial new_poly = new Polynomial(final_coeffecients, final_exponents);
                return new_poly;

        }


        public double evaluate(double x){
                double ret = 0;
                for (int i = 0; i < this.coeffecients.length; i++){
                        ret = ret + this.coeffecients[i] * Math.pow(x, (double)this.exponents[i]);
                }
                return ret;
        }


        public boolean hasRoot(double x){
                return (evaluate(x) == 0);
        }
        
        
        public Polynomial multiply(Polynomial poly){
                Polynomial new_poly = new Polynomial();
                int l = this.coeffecients.length;
                int k = poly.coeffecients.length;

                double[] temp_coeffecients = new double[k];
                int[] temp_exponents = new int[k];

                for (int i = 0; i < l; i++){
                        for (int j = 0; j < k; j++){
                                temp_exponents[j] = this.exponents[i] + poly.exponents[j];
                                temp_coeffecients[j] = this.coeffecients[i] * poly.coeffecients[j];
                        }
                        Polynomial temp_poly = new Polynomial(temp_coeffecients, temp_exponents);
                        new_poly = new_poly.add(temp_poly);
                        Arrays.fill(temp_exponents, 0);
                        Arrays.fill(temp_coeffecients, 0.0);
                        
                }


                return new_poly;
        }


        public void saveToFile(String myfile){
                String ret_string = "";

                for (int i = 0; i < this.coeffecients.length; i++){
                        ret_string = ret_string + this.coeffecients[i];
                        if (this.exponents[i] != 0){
                        ret_string = ret_string + "x" + this.exponents[i];
                        }
                        if (i+1 != this.coeffecients.length) ret_string = ret_string + "+";
                }


                try {
                        FileWriter writer = new FileWriter("output.txt");
                        writer.write(ret_string);
                        writer.close();

                    } catch (IOException e) {}
        }    
}
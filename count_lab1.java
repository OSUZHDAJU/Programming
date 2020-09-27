public class count_lab1{
    public static void main(String[] args) {
        short[] d = new short[6];
        double[] x = new double[17];
        double[][] d1 = new double[6][17];
        short y = 5;

        for(int i = 0; i < 6; i++) {
            d[i] =(short)(y+i*2);
        }

        int a = -3;
        int b = 5;

        int i;
        for(i = 0; i < 17; ++i) {
            x[i] = Math.random() * (b - a) + a;
        }
        /**for(i=0;i<6;i++){
            System.out.print(d[i]);
        }
        for(i=0;i<17;i++){
            System.out.print(x[i]);
        }
        System.out.println();
         **/
        for(i = 0; i < 6; ++i) {
            for(int j = 0; j < 17; ++j) {
                if (d[i] == 5) {
                    d1[i][j] = Math.pow(Math.E,Math.pow(Math.pow(Math.E,x[j])*Math.exp(Math.abs(x[j]))+1,3));
                } else if (d[i] == 7 || d[i] == 11 || d[i] == 15) {
                    d1[i][j] = Math.pow(Math.E,Math.pow(Math.E,Math.pow(x[j],1/3)));
                } else {
                    d1[i][j] = Math.pow(Math.pow(Math.E,Math.pow(Math.asin((x[j]+1)/8),3)),1/3-Math.pow((Math.exp(Math.sqrt(Math.abs(x[j])))-1)/(Math.pow(Math.exp(Math.abs(x[j])),1/3)),Math.pow(Math.E,Math.pow(x[j],3))));
                }

                System.out.printf("%.4f", d1[i][j]);
                System.out.print(" ");
            }

            System.out.print("\n");
        }

    }
}
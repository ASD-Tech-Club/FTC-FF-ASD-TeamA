public class triMotor{
     static double Ax;
     static double Ay;
     static double W;

   // static double[][] matixValues = new double[][] {
   // {0.58,-0.33,0.33},
   // {-0.58,-0.33,0.33},
   // {0,0.67,0.33}};
    static double[] Mv;

    public static void main(String[] args) {
        Ax = 0;
        Ay= 0;
        W = 5;


        System.out.println("f1: "+ motorvelocity(Ax,Ay,W)[0]);
        System.out.println("f2: "+ motorvelocity(Ax,Ay,W)[1]);
        System.out.println("f3: "+ motorvelocity(Ax,Ay,W)[2]);
    //    System.out.println(Ay*matixValues[1][1]);
      
        
    }
    
    public static double[] motorvelocity(double Ax, double Ay, double W){
        double[][] matixValues = new double[][] {
            {0.58,-0.33,0.33},
            {-0.58,-0.33,0.33},
            {0,0.67,0.33}};
        Mv = new double[]{
            Ax*matixValues[0][0]+Ay*matixValues[0][1]+W*matixValues[0][2],
            Ax*matixValues[1][0]+Ay*matixValues[1][1]+W*matixValues[1][2],
             Ax*matixValues[2][0]+Ay*matixValues[2][1]+W*matixValues[2][2]
           
        };
        return Mv;


    }
}
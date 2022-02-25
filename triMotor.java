public class triMotor{
	
     static double Ax;
     static double Ay;
     static double W;
	 double[][] matixValues;
    static double[] Mv;
	
	public triMotor(){
		matixValues = new double[][] {
            {0.58,-0.33,0.33},
            {-0.58,-0.33,0.33},
            {0,0.67,0.33}};	
	}

    
    public static double[] motorvelocity(double Ax, double Ay, double W){
        Mv = new double[]{
            Ax*matixValues[0][0]+Ay*matixValues[0][1]+W*matixValues[0][2],
            Ax*matixValues[1][0]+Ay*matixValues[1][1]+W*matixValues[1][2],
             Ax*matixValues[2][0]+Ay*matixValues[2][1]+W*matixValues[2][2]
           
        };
        return Mv;


    }
}
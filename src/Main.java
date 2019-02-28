
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.mariuszgromada.math.mxparser.*;

import java.awt.Dimension;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

//		Constant cons;
//		String s="4";
//		Argument a= new Argument("a=5");
//		Argument b= new Argument("b=25");
//		Function f= new Function("f(x,y)=x+y");
//		
//		
//		Expression e1 = new Expression("f(a,b)",f,a,b);
//		double v = e1.calculate();
//		System.out.println(e1.calculate());
//		e1 = new Expression("(100-20)/"+s);
//		mXparser.consolePrintln(e1.calculate());
//		cons = new Constant("cons=(b-a)/"+s,b,a);
//		v = e1.calculate();
//		s=String.valueOf(v);
//		System.out.println("s="+s);
//		mXparser.consolePrintln(cons.getConstantValue());
//		System.out.println("f="+f.calculate(a,b));
//		System.out.println("____________________________________________");
	/*	DifferentialEquation DU= new DifferentialEquation("2*sin(x)*(1+t)","1.5*sin(x)","0","0","0","pi","1","20","100");
//		DU.showY();
		System.out.println("____________________________________________");
		DU.solve();
		DU.showY();
//		DU.showChart();
	*/	
		
		
//		Scanner sc= new Scanner(System.in);
//		double[][] arg= new double[3][3];
//		for(int i=0;i<3;i++) {
//			for(int j=0;j<3;j++) {
//				arg[i][j]=sc.nextInt();
//			}
//		}
//		double[] arg2= {3,6,2};
//		Matrix m=new Matrix(arg);
//		m.showMatrix();
//		arg2=m.sweepMethod(arg2);
//	    System.out.println(arg2[0]);
//	    System.out.println(arg2[1]);
//	    System.out.println(arg2[2]);
	    
	
		GUI g=new GUI();
		
		 for(;;){
		Thread.sleep(100);
//		double flag = g.Y[g.Y.length/2];
//		System.out.println(flag);
			if(g.flag) {
				XYChart chart = QuickChart.getChart("График", "X", "Y", "u(x)", g.X, g.Y);
				SwingWrapper sw = new SwingWrapper(chart);
				sw.displayChart("График");
				g.flag=false;
			}
		}
	}

	
	
}

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.mariuszgromada.math.mxparser.*;

public class DifferentialEquation {

	private Function f;           //правая часть
	private Function nu0;          //начальное условие
	private Function nu1;          //первое граничное
	private Function nu2;          // второе граничное
	private Constant a;              // левая граница по х
	private Constant b;               // правая граница по х 
	private Constant T;               //граница по t
	private Constant deltaX;           //шаг по х
	private Constant deltaT;           //шаг по t
	private Argument x;              
	private Argument t; 
	private double[] u;
	private double[] xValue;
	private double[][] matr;
	double constanta=1.5;
	
	public DifferentialEquation(String func, String n0, String n1, String n2, String aa,
			String bb, String TTT, String numberOfBreaksX, String numberOfBreaksT ){
		f = new Function("f(u,x,t)="+func);
		nu0 = new Function("nu0(x)="+n0);
		nu1 =  new Function("nu1(t)="+n1);
		nu2 =new Function("nu2(t)="+n2) ;
		a = new Constant("a="+aa);
		b = new Constant("b="+bb);
		T = new Constant("T="+TTT);
		deltaX = new Constant("h=(b-a)/"+numberOfBreaksX,b,a);
		deltaT = new Constant("tau=T/"+numberOfBreaksT,T);
		int quantityX=Integer.parseInt(numberOfBreaksX)+1;
		u = new double[quantityX];
		xValue=new double[quantityX];
		x = new Argument("x="+String.valueOf(a.getConstantValue()));
		t = new Argument("t=0");
		for(int i=0;i<quantityX;i++) {
			x.setArgumentValue(i*deltaX.getConstantValue());
			Expression e= new Expression("nu0(x)",nu0,x);
			xValue[i]=x.getArgumentValue();
			u[i]=e.calculate();
		}
		matr = new double[quantityX][quantityX];
		matr[0][0]=1;
		matr[quantityX-1][quantityX-1]=1;
		for(int i=1;i<quantityX-1;i++) {
			matr[i][i-1]=1;
			matr[i][i]=-(2+deltaX.getConstantValue()*deltaX.getConstantValue()/deltaT.getConstantValue());
			matr[i][i+1]=1;
		}
	}
	
	public DifferentialEquation(String n0, String aa, String bb, int numberOfBreaksX){ //для проверки
		nu0 = new Function("nu0(x)="+n0);
		a = new Constant("a="+aa);
		b = new Constant("b="+bb);
		deltaX = new Constant("cons=(b-a)/"+String.valueOf(numberOfBreaksX),b,a);
		int quantityX=numberOfBreaksX+1;
		u = new double[quantityX];
		x = new Argument("x=a",a);
		
		for(int i=0;i<quantityX;i++) {
			x.setArgumentValue(i*deltaX.getConstantValue());
			Expression e= new Expression("nu0(x)",nu0,x);
			u[i]=e.calculate();
		}
	}
	
	private double[] newValueVector() {
		double[] vVect = new double[u.length];
		double valueT = t.getArgumentValue()+deltaT.getConstantValue();
		vVect[0] = nu1.calculate(valueT);
		vVect[u.length-1] = nu2.calculate(valueT);
		for(int i=1;i<u.length-1;i++) {
			double valueX = deltaX.getConstantValue()*i;
			vVect[i]=-deltaX.getConstantValue()*deltaX.getConstantValue()*
					(f.calculate(u[i],valueX,t.getArgumentValue())+u[i]/deltaT.getConstantValue());
//			vVect[i]=-deltaX.getConstantValue()*deltaX.getConstantValue()*
//					(constanta*y[i]*y[i]+y[i]/deltaT.getConstantValue());
		}
		return vVect;
	}
	
	public void solve() {                                    // to do
		while(t.getArgumentValue()<=T.getConstantValue()) {
			
			Matrix m=new Matrix(matr);
			u=m.sweepMethod(newValueVector());
			Vector vec = new Vector(u);
			double norma = vec.norma();
			if(deltaT.getConstantValue()*norma>1) {
				deltaT = new Constant("h="+1/(2*norma));
			}
			if(norma>1000000) {
				break;
			}
			t.setArgumentValue(t.getArgumentValue()+deltaT.getConstantValue());
		}
	}
	
	
	public String showY() {
		String result="";
		for(int i=0;i<u.length;i++) {
//			x.setArgumentValue(i*deltaX.getConstantValue());
//			Expression e = new Expression("f(x,T)",f,x,T);
			result+=String.valueOf(u[i])+"\n";//*+"    |    "+e.calculate()*/);
		}
		// Create Chart
//	    XYChart chart = QuickChart.getChart("График", "X", "Y", "u(x)", xValue, y);
//
//	    // Show it
//	    new SwingWrapper(chart).displayChart();
	    return result;
	}
//	public void writeInFile() throws IOException {
//		
//		
//		String sX="",sY="";
//		for(int i=0;i<y.length;i++) {
//			sX+=String.valueOf(xValue[i])+" ";
//			sY+=y[i]+" ";
//		}
//		sX+="\n";
//		PrintWriter wr = new PrintWriter(new File("notes.txt"));
//		System.out.println(sX);
//		wr.write(sX);
//		FileWriter writer = new FileWriter("notes.txt", true);
////		writer = new FileWriter("notes.txt", true);
//		writer.write(sY);
//		writer.close();
//	}
	
	public double[] getX() {
		return xValue;
	}
	public double[] getY() {
		return u;
	}
	
	public void showChart() {
		for(int i=0;i<u.length;i++) {
			x.setArgumentValue(i*deltaX.getConstantValue());
			Expression e = new Expression("f(x,T)",f,x,T);
		}
		XYChart chart = QuickChart.getChart("График", "X", "Y", "u(x)", xValue, u);
		new SwingWrapper(chart).displayChart("dsgdf");
	}
	
	
	
	public void show() {
		System.out.println(f.getFunctionExpressionString());
		System.out.println(nu0.getFunctionExpressionString());
		System.out.println(nu1.getFunctionExpressionString());
		System.out.println(nu2.getFunctionExpressionString());
		System.out.println(a.getConstantValue());
		System.out.println(b.getConstantValue());
		System.out.println(T.getConstantValue());
		System.out.println(deltaX.getConstantValue());
		System.out.println(deltaT.getConstantValue());
		
		
		
		Expression e = new Expression("f(pi/2,1)",f);
		System.out.println(e.calculate());
	}	
}

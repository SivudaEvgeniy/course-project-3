
public class Vector {

	private int size;
	protected double[] values;
	public Vector() {
		
	}
	
	public Vector(int n) {
	size = n;	
	values=new double[size];
	}
	public Vector(double[] vect) {
		values=vect;
		size=values.length;
	}
	
	public Vector summ(Vector a, Vector b) {                      //����� ���� ��������
		Vector result = new Vector(a.size);
		for(int i=0;i<a.size;i++) {
			result.values[i]=a.values[i]+b.values[i];
		}
		return result;
	}
	
	public double norma() {                                       //����� �������
		double result = 0;
		for(int i=0;i<size;i++) {
			result+=values[i]*values[i];
		}
		return Math.sqrt(result);
	}
	
	public void show() {                                          //������� ������ �� �������
		for(int i=0;i<size;i++) {
			System.out.println("x["+(i+1)+"]"+values[i]);
		}
	}
	
	public void multiplySkalar(double skalar) {                   //���������� ������� �� ������
		for(int i=0;i<size;i++) {
			values[i]*=skalar;
		}
	}
	
	public Vector multiplySkalar(Vector vect, double skalar) {   //���������� ������� �� ������
		Vector result = new Vector(vect.size);
		for(int i=0;i<vect.size;i++) {
			result.values[i]=vect.values[i]*skalar;
		}
		return result;
	}

}

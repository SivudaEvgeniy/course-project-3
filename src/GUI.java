import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class GUI extends JFrame {
	
	public double[] X= {}, Y= {};
	public boolean flag=false;
	
	GUI(){
		super("Разностные решения нелинейных параболических уравнений");
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		JLabel label= new JLabel();
		label.setBounds(120,10,222,107);
	//	label.setIcon(new ImageIcon("fImage.PNG"));
		JTextField funcField, nu0Field, nu1Field, nu2Field;
		JLabel duLabel=new JLabel();
		duLabel.setBounds(5, 5, 97, 51);
		duLabel.setIcon(new ImageIcon("E:\\Image\\du.PNG"));
		duLabel.setVisible(true);
		
		funcField = new JTextField("0.5*u*u");
		funcField.setBounds(102,15,70,30);
		
		JLabel nu0Label=new JLabel();
		nu0Label.setBounds(10, 60, 75, 25);
		nu0Label.setIcon(new ImageIcon("E:\\\\Image\\\\nu0.PNG"));
		
		nu0Field = new JTextField("1.5*sin(x)");
		nu0Field.setBounds(85,60,80,25);
		
		JLabel nu11Label=new JLabel();
		nu11Label.setBounds(10, 90, 75, 25);
		nu11Label.setIcon(new ImageIcon("E:\\\\Image\\\\nu21.PNG"));
		
		JTextField aField = new JTextField("0");
		aField.setBounds(35, 90, 30, 25);
		
		JLabel nu12Label=new JLabel();
		nu12Label.setBounds(65, 90, 50, 25);
		nu12Label.setIcon(new ImageIcon("E:\\\\Image\\\\nu22.PNG"));
		
		nu1Field = new JTextField("0");
		nu1Field.setBounds(110,90,80,25);
		
		JLabel nu21Label=new JLabel();
		nu21Label.setBounds(10, 120, 25, 25);
		nu21Label.setIcon(new ImageIcon("E:\\\\Image\\\\nu21.PNG"));
		
		JTextField bField = new JTextField("pi");
		bField.setBounds(35, 120, 30, 25);
		
		JLabel nu22Label=new JLabel();
		nu22Label.setBounds(65, 120, 50, 25);
		nu22Label.setIcon(new ImageIcon("E:\\\\Image\\\\nu22.PNG"));
		
		nu2Field = new JTextField("0");
		nu2Field.setBounds(110,120,80,25);
		
		JLabel tLabel=new JLabel();
		tLabel.setBounds(10, 155, 55, 25);
		tLabel.setIcon(new ImageIcon("E:\\\\Image\\\\tt.PNG"));
		
		JTextField tField = new JTextField("1");
		tField.setBounds(65, 155, 30, 25);
		
		JLabel t2Label=new JLabel();
		t2Label.setBounds(95, 155, 55, 25);
		t2Label.setIcon(new ImageIcon("E:\\\\Image\\\\t2.PNG"));
		
		JLabel dtLabel = new JLabel("число разбиений по T:");
		dtLabel.setBounds(5, 175, 150, 30);
		
		JTextField dtField = new JTextField("100");
		dtField.setBounds(140, 175, 30, 25);
		
		JLabel dxLabel = new JLabel("число разбиений по X:");
		dxLabel.setBounds(5, 200, 150, 30);
		
		JTextField dxField = new JTextField("20");
		dxField.setBounds(140, 200, 30, 25);
		
		
		
		
		
		JTextArea area = new JTextArea();
		area.setBounds(190, 10, 230, 350);
//        area.setText("Второе многострочное поле");
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(area);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(230, 10, 235, 350);
//		scroll.setVisible(false);
		
		
		
		JButton button = new JButton("Solve!");
		button.setBounds(10, 260, 70, 35);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DifferentialEquation DU= new DifferentialEquation(funcField.getText(),nu0Field.getText(),nu1Field.getText(),
						nu2Field.getText(),aField.getText(),bField.getText(),tField.getText(),dxField.getText(),dtField.getText());
				DU.solve();
				area.setText(DU.showY());	
//				try {
//					DU.writeInFile();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				X=DU.getX();
				Y=DU.getY();
				flag=true;
				DU.showChart();
			}
		});
		
		
//		JPanel content = new JPanel(new FlowLayout(FlowLayout.LEFT));
		add(label);
		add(duLabel);
		add(funcField);
		add(nu0Label);
		add(nu0Field );
		add(nu11Label);
		add(aField);
		add(nu12Label);
		add(nu1Field );
		add(nu21Label);
		add(bField);
		add(nu22Label);
		add(nu2Field );
		add(tLabel);
		add(tField);
		add(t2Label);
		add(dtLabel);
		add(dtField);
		add(dxLabel);
		add(dxField);
		add(button);
//		add(area);
		add(scroll);
//		add(new JScrollPane(area));
//		setContentPane(content);
		setBounds(350,200,500,450);
//		setSize(400,200);
		setLayout(null);
		setVisible(true);
	}
}
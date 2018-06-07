package practiceForGUI;

import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

class Frame1 extends JFrame{
	Frame1(){
		setSize(600,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Attck!");
	}
}

public class Attack {

	public static void main(String[] args) {
		Frame1 f = new Frame1();
		
		JButton a = new JButton("A");
		a.setBounds(200, 50, 100, 100);
		f.add(a);
		
		JButton b = new JButton("B");
		b.setBounds(350, 150, 100, 100);
		f.add(b);
		
		
		while(true) {
		int Ax = a.getX();
		int Bx = b.getX();
		String closed = null;
		
		int X = (Ax<=Bx)? Ax : Bx;
		if(X==Ax)
			closed = "A";
		else if(X==Bx){
			closed = "B";
		}
		System.out.println("왼쪽에 더 가까이 있는 버튼: "+ closed +", x좌표 : "+ X);
		
		Scanner sc = new Scanner (System.in); //소리 크기 입력
		System.out.print("공격량 : ");
		int vol = sc.nextInt();//소리의 크기에 따른 공격 크기
		
		X = X + vol;
		
		System.out.println("공격 후 " + closed + "의 x좌표: " + X);
		
		if(Ax<=Bx) {
			a.setBounds(X, 50, 100, 100);
			b.setBounds(Bx, 150, 100, 100);
		}
		else if (Ax>Bx) {
			a.setBounds(Ax, 50, 100, 100);
			b.setBounds(X, 150, 100, 100);
			}

		}
	}

}

package practiceForGUI;

import javax.swing.*;

class MyRunnable implements Runnable {
	int num;
	JFrame f;
	public MyRunnable(JFrame jframe) {
		f = jframe;
		num = (int) (Math.random() * 5 + 1);
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//
//		}
	}

	public void run() {
		

		JButton bt1 = new JButton("1");
		JButton bt2 = new JButton("2");
		JButton bt3 = new JButton("3");
		JButton bt4 = new JButton("4");
		JButton bt5 = new JButton("5");

		int speed = (int) (Math.random() * 10 + 1);
		System.out.println(num + " 출력, " + num  + "번 버튼 Moving, 속도 : "+speed);

		if (num  == 1) {
			for (int i = 0; i < 1000; i++) {
				bt1.setBounds(i, 0, 100, 100);
				f.add(bt1);
				try {
					Thread.sleep(10-speed);
				} catch (InterruptedException e) {
				}
			}
		} else if (num  == 2) {
			for (int i = 0; i < 1000; i++) {
				bt2.setBounds(i, 100, 100, 100);
				f.add(bt2);
				try {
					Thread.sleep(10-speed);
				} catch (InterruptedException e) {
				}
			}
		} else if (num  == 3) {
			for (int i = 0; i < 1000; i++) {
				bt3.setBounds(i, 200, 100, 100);
				f.add(bt3);
				try {
					Thread.sleep(10-speed);
				} catch (InterruptedException e) {
				}
			}
		} else if (num == 4) {
			for (int i = 0; i < 1000; i++) {
				bt4.setBounds(i, 300, 100, 100);
				f.add(bt4);
				try {
					Thread.sleep(10-speed);
				} catch (InterruptedException e) {
				}
			}
		} else if (num  == 5) {
			for (int i = 0; i < 1000; i++) {
				bt5.setBounds(i, 400, 100, 100);
				f.add(bt5);
				try {
					Thread.sleep(10-speed);
				} catch (InterruptedException e) {
				}
			}
		} else {
		}

	}
}

public class ButtonMoving {

	public static void main(String[] args) throws InterruptedException {
		JFrame f = new JFrame();
		f.setSize(1000, 600);
		f.setVisible(true);
		f.setTitle("buttons");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		for(int i=0; i<5; i++){
			new Thread(new MyRunnable(f)).start();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
		}
		}

	}



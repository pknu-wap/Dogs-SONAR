package savingModule;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveManager {
	int count=0;
	ArrayList<SaveItem> arr;
	public SaveManager() {
		arr=new ArrayList<SaveItem>();
	}
	public void load(String src) throws IOException {
		try {
			BufferedReader r=new BufferedReader(new FileReader(src));
			count=Integer.parseInt(r.readLine());
			for(int i=0;i<count;i++) {
				SaveItem t;
				String key=r.readLine();
				switch(Integer.parseInt(r.readLine())) {
				case 0://string
					t=new SaveItem(key,r.readLine());
					break;
				case 1://integer
					t=new SaveItem(key,Integer.parseInt(r.readLine()));
					break;
				case 2://double
					t=new SaveItem(key,Double.parseDouble(r.readLine()));
					break;
				default:
					t=new SaveItem(key,null);
					break;
				}
				arr.add(t);
			}
			r.close();
		}catch(FileNotFoundException e) {
			PrintWriter pw = new PrintWriter(src);
			pw.println(0);
			pw.close();
			this.load(src);
		}
	}
	public void add(SaveItem sv) {
		arr.add(sv);
		count+=1;
	}
	public void delete(String key) {
		for(int i=0;i<count;i++) {
			SaveItem t=arr.get(i);
			if(t.key.equals(key)) {
				arr.remove(i);
			}
		}
	}
	public void save(String src) {
		try {
			PrintWriter pw = new PrintWriter(src);
			pw.println(count);
			for(int i=0;i<count;i++) {
				SaveItem t = arr.get(i);
				pw.println(t.getKey());
				switch(t.getType()) {
				case INT:
					pw.println(1);
					pw.println(t.getValueInt());
					break;
				case DOUBLE:
					pw.println(2);
					pw.println(t.getValueDouble());
					break;
				case STRING:
					pw.println(0);
					pw.println(t.getValueString());
					break;
				default:
					break;
				}
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	public static void main(String args[]) {
		Scanner scan=new Scanner(System.in);
		SaveManager sv=new SaveManager();
		int T=Integer.parseInt(scan.nextLine());
		for(int i=0;i<T;i++) {
			String key=scan.nextLine();
			switch(Integer.parseInt(scan.nextLine())) {
			case 0:
				sv.add(new SaveItem(key,scan.nextLine()));
				break;
			case 1:
				sv.add(new SaveItem(key,Integer.parseInt(scan.nextLine())));
				break;
			case 2:
				sv.add(new SaveItem(key,Double.parseDouble(scan.nextLine())));
				break;
			default:
				break;
			}
		}
		sv.save("save.txt");
	}
	*/
	public static void main(String args[]) {
		SaveManager sv=new SaveManager();
		try {
			sv.load("save.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<sv.count;i++) {
			System.out.println(sv.arr.get(i).toString());
					
		}
	}
}

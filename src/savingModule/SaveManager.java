package savingModule;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

import screen.GraphicComponent;

public class SaveManager {
    int count=0;
    String src;
    TreeMap<String,SaveItem> arr;
    public SaveManager(String src) {
        this.src=src;
        arr=new TreeMap<String,SaveItem>();
        try {
            this.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void load() throws IOException {
        try {
            BufferedReader r=new BufferedReader(new FileReader(src));
            count=Integer.parseInt(r.readLine());
            for(int i=0;i<count;i++) {
                SaveItem t;
                String key=r.readLine();
                switch(Integer.parseInt(r.readLine())) {
                case 0://string
                    t=new SaveItem(r.readLine());
                    break;
                case 1://integer
                    t=new SaveItem(Integer.parseInt(r.readLine()));
                    break;
                case 2://double
                    t=new SaveItem(Double.parseDouble(r.readLine()));
                    break;
                default:
                    t=new SaveItem(null);
                    break;
                }
                add(key,t);
            }
            r.close();
        }catch(FileNotFoundException e) {
            PrintWriter pw = new PrintWriter(src);
            pw.println(0);
            pw.close();
            this.load();
        }
    }
    public void add(String key,SaveItem sv) {
        arr.put(key,sv);
    }
    public void delete(String key) {
        for(int i=0;i<count;i++) {
            SaveItem t=arr.get(i);
            if(key.equals(key)) {
                arr.remove(i);
            }
        }
    }
    public void modify(String key,SaveItem target) {
        delete(key);
        add(key,target);
    }
    public void save() {
        try {
            PrintWriter pw = new PrintWriter(src);
            pw.println(count);
            for (Entry<String, SaveItem> entry : arr.entrySet()) {
                String key   = entry.getKey();
                SaveItem value =  entry.getValue();
                pw.println(key);
                switch(value.getType()) {
                case INT:
                    pw.println(1);
                    pw.println(value.getValueInt());
                    break;
                case DOUBLE:
                    pw.println(2);
                    pw.println(value.getValueDouble());
                    break;
                case STRING:
                    pw.println(0);
                    pw.println(value.getValueString());
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

    public SaveItem getItem(String key) {
        SaveItem ret=arr.get(key);
        if(ret==null) {
            ret=new SaveItem();
            arr.put(key,ret);
        }
        return arr.get(key);
    }


    public static void main(String args[]) {
        Scanner scan=new Scanner(System.in);
        SaveManager sv=new SaveManager("save.dat");

        scan.nextLine();

    }
}

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
        try {this.load();} catch (IOException e) {}
    }
    public void load() throws IOException {
        try {
            BufferedReader r=new BufferedReader(new FileReader(src));
            count=Integer.parseInt(r.readLine());
            for(int i=0;i<count;i++) {
                SaveItem t;
                String key=r.readLine();
                t=new SaveItem(r.readLine());
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
            pw.println(count+"");
            for (Entry<String, SaveItem> entry : arr.entrySet()) {
                String key   = entry.getKey();
                SaveItem value =  entry.getValue();
                pw.println(key);
                pw.println(value.getValueString());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public SaveItem getItem(String key) {
        SaveItem ret=arr.get(key);
        if(ret==null) {
            ret=new SaveItem();
            arr.put(key,ret);
            count+=1;
            save();
        }
        return ret;
    }
}

package game;

import java.awt.event.MouseEvent;

import savingModule.SaveItem;
import savingModule.SaveManager;
import screen.*;
public class UpgradePane extends GraphicViewer {
    Window w;
    FixedImage img;
    Group dmg,critChance,critDmg;
    FixedImage moneyIcon=new FixedImage("sprites\\Money\\icon.png",1000,100,50,50);
    FixedImage moneyValue=new FixedImage(null,1060,100,100,50);
    public UpgradePane(int width, int height, int rate,Window w) {
        super(width, height, rate);
        this.w=w;
        img=new FixedImage("sprites\\title\\title.jpg",0,0,1280,720);
        dmg=new Group("damage",100,100,"dmgUp");
        critChance=new Group("critical chance",100,200,"critChanceUp");
        critDmg=new Group("critical damage",100,300,"critDmgUp");
        this.addComponent(img,"background");
        dmg.addGroup(this);
        critChance.addGroup(this);
        critDmg.addGroup(this);
        addComponent(moneyIcon,"moneyIcon");
        addComponent(moneyValue,"moneyValue");
        moneyValue.setText(w.sv.getItem("money").getValueInt()+"");
        addComponent(new Button("sprites\\Buttons\\main.png",1000,650,100,50) {
            @Override
            public void act(MouseEvent e) {
                w.add(new TitlePane(w));
                w.remove(UpgradePane.this);
            }
        },"exit");
    }
    class Group{
        Button btn;
        FixedImage tooltip;
        ValueLabel value;
        String text,svKey;
        int locX,locY;
        public Group(String text,int locX,int locY,String svKey) {
            this.text=text;
            this.locX=locX;
            this.locY=locY;
            this.svKey=svKey;
            btn=new Button("sprites\\Buttons\\main.png",this.locX+220,this.locY,100,50);
            btn.setText("+");
            tooltip=new FixedImage("sprites\\Buttons\\main.png",this.locX,this.locY,100,50);
            tooltip.setText(text);
            value=new ValueLabel("sprites\\Buttons\\main.png",this.locX+110,this.locY,100,50);
            value.update(svKey);
        }
        public void addGroup(GraphicViewer v) {
            v.addComponent(btn,text+"Btn");
            v.addComponent(tooltip,text+"Tooltip");
            v.addComponent(value,text+"Value");
        }
        
    }
    class ValueLabel extends FixedImage{
        String text;
        public ValueLabel(String imgSrc, int locX, int locY, int sizeWidth, int sizeHeight) {
            super(imgSrc, locX, locY, sizeWidth, sizeHeight);
            
        }
        public void update(String key) {
            this.setText(w.sv.getItem(key).getValueInt()+"");
        }
        
    }
    class StatButton extends Button{
        ValueLabel lb;
        SaveManager sv;
        String text;
        public void initSet(ValueLabel lb,SaveManager sv,String text) {
            this.lb=lb;
            this.sv=sv;
            this.text=text;
        }
        public StatButton(String imgSrc, int locX, int locY, int sizeWidth, int sizeHeight) {
            super(imgSrc, locX, locY, sizeWidth, sizeHeight);
            // TODO Auto-generated constructor stub
        }
        @Override
        public void act(MouseEvent e) {
            
            lb.update(this.text);
            sv.getItem(text).setValueInt(sv.getItem(text).getValueInt()+1);
        }
    }
}

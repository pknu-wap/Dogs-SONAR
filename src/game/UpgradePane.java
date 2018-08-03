package game;

import screen.*;
public class UpgradePane extends GraphicViewer {
    Window w;
    FixedImage img;
    Group dmg,critChance,critDmg;
    public UpgradePane(int width, int height, int rate,Window w) {
        super(width, height, rate);
        this.w=w;
        img=new FixedImage("sprites\\title\\title.jpg",0,0,1280,720);
        dmg=new Group("damage",100,100);
        critChance=new Group("critical chance",100,200);
        critDmg=new Group("critical damage",100,300);
        this.addComponent(img,"background");
        dmg.addGroup(this);
        critChance.addGroup(this);
        critDmg.addGroup(this);
    }
    class Group{
        Button btn;
        FixedImage tooltip,value;
        String text;
        int locX,locY;
        public Group(String text,int locX,int locY) {
            this.text=text;
            this.locX=locX;
            this.locY=locY;
            btn=new Button("sprites\\Buttons\\main.png",this.locX+220,this.locY,100,50);
            tooltip=new FixedImage("sprites\\Buttons\\main.png",this.locX,this.locY,100,50);
            value=new FixedImage("sprites\\Buttons\\main.png",this.locX+110,this.locY,100,50);
        }
        public void addGroup(GraphicViewer v) {
            v.addComponent(btn,text+"Btn");
            v.addComponent(tooltip,text+"Tooltip");
            v.addComponent(value,text+"Value");
        }
        
    }
}

package game;

import java.awt.event.MouseEvent;

import savingModule.SaveItem;
import savingModule.SaveManager;
import screen.*;
public class UpgradePane extends GraphicViewer {
    Window w;
    FixedImage img;
    Group dmg,critChance,critDmg;
    FixedImage mouseTooltip=new FixedImage("sprites\\Buttons\\tooltip.png");
    FixedImage moneyIcon=new FixedImage("sprites\\Money\\icon.png",1000,100,50,50);
    FixedImage moneyValue=new FixedImage(null,1060,100,100,50);
    public UpgradePane(int width, int height, int rate,Window w) {
        super(width, height, rate);
        this.w=w;
        img=new FixedImage("sprites\\title\\title.jpg",0,0,1280,720);
        dmg=new Group("피해량",100,100,"dmgUp");
        critChance=new Group("크리티컬 확률",100,200,"critChanceUp");
        critDmg=new Group("크리티컬 배수",100,300,"critDmgUp");
        this.addComponent(img,"background");
        dmg.addGroup(this);
        critChance.addGroup(this);
        critDmg.addGroup(this);
        addComponent(moneyIcon,"moneyIcon");
        addComponent(moneyValue,"moneyValue");
        moneyValue.setText(w.sv.getItem("money").getValueInt()+"");
        addComponent(new Button("sprites\\Buttons\\main.png",1000,600,130,50) {
            @Override
            public void act(MouseEvent e) {
                remove(w);
                w.add(new TitlePane(1280,720,200,w));
                
            }
        },"exit");
        getComponentByName("exit").setText("메인 메뉴");
        addComponent(new Button("sprites\\Buttons\\main.png",100,600,130,50) {
            @Override
            public void act(MouseEvent e) {
                remove(w);
                w.add(new GamePane(1280,720,100,w));
                
            }
        },"start");
        getComponentByName("start").setText("게임 시작");
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
            btn=new Button("sprites\\Buttons\\main.png",this.locX+370,this.locY,100,50) {
              @Override
              public void act(MouseEvent e) {
                  SaveManager sv=UpgradePane.this.w.sv;
                  if(sv.getItem("money").getValueInt()>100*sv.getItem(svKey).getValueInt()) {
                        System.out.println("upgraded");
                        sv.getItem("money").setValueInt(sv.getItem("money").getValueInt()-100*sv.getItem(svKey).getValueInt());
                        sv.getItem(svKey).setValueInt(sv.getItem(svKey).getValueInt()+1);
                        sv.save();
                        tooltip.setText(text+" : "+100*UpgradePane.this.w.sv.getItem(svKey).getValueInt());
                        value.update(svKey);
                        moneyValue.setText(sv.getItem("money").getValueInt()+"");
                  }else {
                      System.out.println("not upgraded");
                  }
              }
            };
            btn.setText("+");
            tooltip=new FixedImage("sprites\\Buttons\\tooltip.png",this.locX,this.locY,250,50);
            tooltip.setText(text+" : "+100*UpgradePane.this.w.sv.getItem(svKey).getValueInt());
            value=new ValueLabel("sprites\\Buttons\\main.png",this.locX+260,this.locY,100,50);
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

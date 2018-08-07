package game;

import java.awt.event.MouseEvent;

import savingModule.*;
import screen.*;

public class ResultPane extends GraphicViewer{
    Window w;
    boolean success=false;
    int money=0;
    SaveManager sv;
    Button nextDay=new Button("sprites\\Buttons\\main.png",600,440,300,50) {
        @Override
        public void act(MouseEvent e) {
            w.remove(ResultPane.this);
            w.add(new GamePane(1280,720,100,w));
        }
    };
    Button upgrade=new Button("sprites\\Buttons\\main.png",780,500,120,50) {
        @Override
        public void act(MouseEvent e) {
            w.remove(ResultPane.this);
            w.add(new UpgradePane(1280,720,100,w));
        }
    };
    Button toMain=new Button("sprites\\Buttons\\main.png",600,500,120,50) {
        @Override
        public void act(MouseEvent e) {
            w.remove(ResultPane.this);
            w.add(new TitlePane(1280,720,100,w));
        }
    };
    public ResultPane(int width, int height, int rate, Window w,boolean success,int money) {
        super(width, height, rate);
        this.w=w;
        this.success=success;
        this.money=money;
        nextDay.setText("Next Day");
        upgrade.setText("Upgrade");
        toMain.setText("Main Menu");
        sv=w.sv;
        addComponent(new FixedImage("sprites\\Title\\title.jpg",0,0,1280,720),"background");
        addComponent(new FixedImage("sprites\\Buttons\\main.png",600,260,300,50),"title");
        getComponentByName("title").setText("Result");
        addComponent(new FixedImage("sprites\\Buttons\\main.png",600,320,300,50),"day");
        addComponent(new FixedImage("sprites\\Buttons\\main.png",600,380,300,50),"money");
        getComponentByName("day").setText("day "+sv.getItem("day").getValueString());
        getComponentByName("money").setText(money+" earned");
        addComponent(nextDay,"next");
        addComponent(upgrade,"upgrade");
        addComponent(toMain,"toMain");
        addComponent(nextDay,"nextGame");
        sv.getItem("money").setValueInt(sv.getItem("money").getValueInt()+money);
        sv.save();
        // TODO Auto-generated constructor stub
    }

}

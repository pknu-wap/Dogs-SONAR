package game;

import java.awt.Color;
import java.awt.event.MouseEvent;

import savingModule.*;
import screen.*;

public class ResultPane extends GraphicViewer{
    Window w;
    boolean success=false;
    int money=0;
    SaveManager sv;
    Button nextDay=new Button("sprites\\Buttons\\main.png",500,410,300,50) {
        @Override
        public void act(MouseEvent e) {
            remove(w);
            w.add(new GamePane(1280,720,17,w));
        }
    };
    Button upgrade=new Button("sprites\\Buttons\\main.png",680,480,120,50) {
        @Override
        public void act(MouseEvent e) {
            remove(w);
            w.add(new UpgradePane(1280,720,17,w));
        }
    };
    Button toMain=new Button("sprites\\Buttons\\main.png",500,480,120,50) {
        @Override
        public void act(MouseEvent e) {
            remove(w);
            w.add(new TitlePane(1280,720,17,w));
        }
    };
    public ResultPane(int width, int height, int rate, Window w,boolean success,int money) {      
        super(width, height);
        this.w=w;
        this.success=success;
        this.money=money;
        if(success) nextDay.setText("다음 날로");
        else nextDay.setText("재도전");
        upgrade.setText("업그레이드");
        toMain.setText("메인 메뉴");
        sv=w.sv;
        if(success) nextDay.setTooltip(new Tooltip(sv.getItem("day").getValueString()+"일 차 게임을\n시작합니다.",5,5,Color.BLACK,Color.WHITE,15));
        else nextDay.setTooltip(new Tooltip(sv.getItem("day").getValueString()+"일 차 게임을\n다시 시작합니다.",5,5,Color.BLACK,Color.WHITE,15));
        upgrade.setTooltip(new Tooltip("업그레이드 페이지로\n이동합니다.",5,5,Color.BLACK,Color.WHITE,15));
        toMain.setTooltip(new Tooltip("메인 메뉴 페이지로\n이동합니다.",5,5,Color.BLACK,Color.WHITE,15));
        addComponent(new FixedImage("sprites\\Title\\title.jpg",0,0,1280,720),"background");
        addComponent(new FixedImage("sprites\\Buttons\\main.png",500,200,300,50),"title");
        if(success) getComponentByName("title").setText("아침까지 버텼습니다!");
        else getComponentByName("title").setText("게임 오버-도둑을 못막았습니다.");
        addComponent(new FixedImage("sprites\\Buttons\\main.png",500,270,300,50),"day");
        addComponent(new FixedImage("sprites\\Buttons\\main.png",500,340,300,50),"money");
        getComponentByName("day").setText(sv.getItem("day").getValueString()+"일 차");
        if(success) {
            getComponentByName("money").setText(money+"X2원 획득-승리보너스");
            money*=2;
        }else getComponentByName("money").setText(money+"원 획득");
        addComponent(nextDay,"next");
        addComponent(upgrade,"upgrade");
        addComponent(toMain,"toMain");
        addComponent(nextDay,"nextGame");
        sv.getItem("money").setValueInt(sv.getItem("money").getValueInt()+money);
        sv.save();
    }
}
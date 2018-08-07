package game;

import java.awt.event.MouseEvent;

import screen.*;

public class Pauser {
    GamePane pane;
    FixedImage fader=new FixedImage("sprites\\bars\\back.png",0,0,1280,720);
    FixedImage pauseTooltip=new FixedImage("sprites\\buttons\\main.png",600,300,200,50,"Paused");
    Button resume=new Button("sprites\\buttons\\main.png",600,360,200,50) {
        @Override
        public void act(MouseEvent e) {
            resume();
        }
    };
    Button toMain=new Button("sprites\\buttons\\main.png",600,420,200,50) {
        @Override
        public void act(MouseEvent e) {
            pane.enemyController.dispose();
            pane.w.add(new TitlePane(1280,720,100,pane.w));
            pane.w.remove(pane);
        }
    };
    public Pauser(GamePane pane) {
        this.pane=pane;
        resume.setText("resume");
        toMain.setText("main menu");
        fader.setAlpha(0.6f);
        pause();
    }
    public void pause() {
        pane.w.pauseSound();
        pane.enemyController.pauseGame();
        pane.isPaused=true;
        pane.addComponent(fader, "fader");
        pane.addComponent(resume, "resume");
        pane.addComponent(pauseTooltip, "pauseTooltip");
        pane.addComponent(toMain, "toMainPause");
    }
    public void resume() {
        pane.w.resumeSound();
        pane.enemyController.resumeGame();
        
        pane.isPaused=false;
        fader.dispose();
        resume.dispose();
        pauseTooltip.dispose();
        toMain.dispose();
    }
}

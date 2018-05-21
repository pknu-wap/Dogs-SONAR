package soundCapture;
/*
 * 소리 캡쳐
 * 
 */
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;

interface soundHandler{//이 인터페이스의 action 함수에서 할 행동들 선언
    void action(double[] pack);
}

public class LevelMeter implements Runnable {
    soundHandler target;
    private AudioFormat fmt = new AudioFormat(44100f, 16, 1, true, false);
    private int bufferByteSize = 2048;
    private TargetDataLine line;
    private double now=0,nowPeak=0,lastPeak=0,lastNow=0;
	private double evAvg=0,evPeak=0;
    private int time=-1,timer;
    private double division;
    private boolean recorded=false;
    Thread thread;
    
    public double getNow() {
		return now;
	}

	public void setNow(double now) {
		this.now = now;
	}

    public double getDivision() {
        return division;
    }

    public void setDivision(double division) {
        this.division = division;
        
    }
    public LevelMeter(soundHandler t,double div,int sampleDuration) {//생성자 t:액션대상 div:최소 이벤트 발생 레벨 sampleDuration:이벤트 발생시 측정하는 시간
        target=t;
        division=div;
        timer=sampleDuration;
        thread=new Thread(this);
        thread.start();
    }
    
    @Override
    public void run() {
        try {
            line = AudioSystem.getTargetDataLine(fmt);
            line.open(fmt, bufferByteSize);
        } catch(LineUnavailableException e) {
            System.err.println(e);
            return;
        }

        byte[] buf = new byte[bufferByteSize];
        double[] samples = new double[bufferByteSize / 2];
        line.start();
        for(int b; (b = line.read(buf, 0, buf.length)) > -1;) {
            
            for(int i = 0, s = 0; i < b;) {
                int sample = 0;
                sample |= buf[i++] & 0xFF;
                sample |= buf[i++] << 8;
                // normalize to range of +/-1
                samples[s++] = sample / 32768f;
            }
            double rms = 0f;
            double peak = 0f;
            for(double sample : samples) {
                double abs = Math.abs(sample);
                if(abs > peak) {
                    peak = abs;
                }

                rms += sample * sample;
            }
            rms = Math.sqrt(rms / samples.length);
            if(lastPeak > peak) {
                peak = lastPeak * 0.875f;
            }
            lastPeak = peak;
            lastNow=now;
            nowPeak=peak;
            now=rms;
            if(now>division&&!recorded) {
                recorded=true;
                time=timer;
                evPeak=0;
                evAvg=0;
            }
            if(recorded) {
                if(time<0) {
                    recorded=false;
                    double[] t={1000*evAvg/(double)timer,1000*evPeak};
                    target.action(t);
                }
                time-=1;
                if(now>evPeak) {
                    evPeak=now;
                }
                evAvg+=now;
            }
        }
    }
    /*public static void main(String args[]) {
        LevelMeter lv=new LevelMeter(new ExampleSoundHandler(),0.1,3);
    }*/
}
class ExampleSoundHandler implements soundHandler{

    @Override
    public void action(double[] pack) {
        System.out.println("now : "+(int)pack[0]+" peak : "+(int)pack[1]);
    }
    
}
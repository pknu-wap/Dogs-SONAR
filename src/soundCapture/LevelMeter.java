package soundCapture;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;


public class LevelMeter implements Runnable {
    SoundHandler target;
    private AudioFormat fmt = new AudioFormat(44100f, 16, 1, true, false);
    private int bufferByteSize = 2048;
    private TargetDataLine line;
    private double now=0,nowPeak=0,lastPeak=0,lastNow=0;
	private double evAvg=0,evPeak=0;
    private int time=-1,timer;
    private double division;
    private boolean recorded=false;
    public boolean isDisposed=false;
    public Thread thread;
    
    public double getNow() {
		return now;
	}

    public double getDivision() {
        return division;
    }

    public void setDivision(double division) {
        this.division = division;
        
    }
    public LevelMeter(SoundHandler t,double div,int sampleDuration) {//������ t:�׼Ǵ�� div:�ּ� �̺�Ʈ �߻� ���� sampleDuration:�̺�Ʈ �߻��� �����ϴ� �ð�
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
            e.printStackTrace();
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
                peak = lastPeak;// * 0.875f;
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
                    target.action(1000*evAvg/(double)timer,1000*evPeak);
                }
                time-=1;
                if(now>evPeak) {
                    evPeak=now;
                }
                evAvg+=now;
            }
            if(isDisposed) {
                line.stop();
                line.flush();
                break;
            }
        }
    }
    public void dispose() {
        isDisposed=true;
    }
    public static void main(String[] args) {
        ExampleSoundHandler h=new ExampleSoundHandler();
        LevelMeter lm=new LevelMeter(h, 0.08, 10);
        try {
            lm.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class ExampleSoundHandler implements SoundHandler{

    @Override
    public void action(double now,double peak) {
        System.out.println("now : "+(int)now+" peak : "+(int)peak);
    }
    
}

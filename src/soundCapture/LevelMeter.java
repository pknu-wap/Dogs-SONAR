package soundCapture;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;

interface soundHandler{
    void action(double[] pack);
}

public class LevelMeter implements Runnable {
    soundHandler target;
    AudioFormat fmt = new AudioFormat(44100f, 16, 1, true, false);
    final int bufferByteSize = 2048;
    TargetDataLine line;
    double now=0,nowPeak=0,lastPeak=0,lastNow=0;
    double evAvg=0,evPeak=0;
    int time=-1,timer;
    double division;
    boolean recorded=false;
    public double getDivision() {
        return division;
    }

    public void setDivision(double division) {
        this.division = division;
        
    }

    Thread thread;
    
    public LevelMeter(soundHandler t,double div,int sampleDuration) {
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
            // convert bytes to samples here
            for(int i = 0, s = 0; i < b;) {
                int sample = 0;
                sample |= buf[i++] & 0xFF; // (reverse these two lines
                sample |= buf[i++] << 8;   //  if the format is big endian)
                // normalize to range of +/-1.0f
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
    
}
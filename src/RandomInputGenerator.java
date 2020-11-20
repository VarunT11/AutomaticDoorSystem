import java.util.Random;

public class RandomInputGenerator {

    private long timeInterval;
    private boolean isAutomatic;
    RandomInputInterface randomInputInterface;

    public void setAutomatic(boolean isAutomatic){
        this.isAutomatic=isAutomatic;
    }

    public RandomInputGenerator(RandomInputInterface randomInputInterface, long timeInterval){
        this.randomInputInterface=randomInputInterface;
        this.timeInterval=timeInterval;
        this.isAutomatic=false;
    }

    public void startRandomGeneration(){
        setAutomatic(true);
        Thread thread=new Thread(){
            @Override
            public void run() {
                super.run();
                while (isAutomatic){
                    long currTime=System.currentTimeMillis();
                    boolean x=true;
                    while (System.currentTimeMillis()<=currTime+timeInterval){
                        if(!isAutomatic){
                            x=false;
                            break;
                        }
                    }
                    if(x){
                        Random random=new Random();
                        int num=random.nextInt(2);
                        switch (num){
                            case 0->{
                                randomInputInterface.onInputGenerated(PersonInput.NoInput);
                            }
                            case 1->{
                                randomInputInterface.onInputGenerated(PersonInput.Approaching);
                            }
                        }

                    }
                    else
                        break;
                }
            }
        };
        if(isAutomatic)
            thread.start();
    }

    public void stopRandomGeneration(){
        setAutomatic(false);
    }

}

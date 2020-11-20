import java.util.Random;

public class Person {

    private PersonInputInterface personInputInterface;
    private long delayTime;
    private int personID;

    public Person(PersonInputInterface personInputInterface, int personID){
        this.personInputInterface=personInputInterface;
        this.personID=personID;
        startInputGeneration();
        delayTime=2500;
    }

    private void startInputGeneration(){
        Thread thread=new Thread(){
            @Override
            public void run() {
                super.run();
                long startTime=System.currentTimeMillis();
                while (System.currentTimeMillis()<=startTime+delayTime);
                Random random=new Random();
                int numRandom=random.nextInt(2);
                switch (numRandom){
                    case 0 ->{
                        personInputInterface.onPersonInputGenerated(PersonInput.Crossing,personID);
                        long startTime1=System.currentTimeMillis();
                        while (System.currentTimeMillis()<=startTime1+delayTime);
                        personInputInterface.onPersonInputGenerated(PersonInput.Leaving,personID);
                    }
                    case 1 ->{
                        personInputInterface.onPersonInputGenerated(PersonInput.Leaving,personID);
                    }
                }

            }
        };
        thread.start();
    }
}

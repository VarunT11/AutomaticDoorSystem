import java.util.Date;

public class ControlSystem {

    private long doorOpenTime;
    private long doorCloseTime;
    private long doorIdleTime;
    private DoorState doorState;
    private boolean isGateOpening, isGateClosing;
    private long openPercent, closePercent;
    private DataListenerInterface dataListenerInterface;

    Thread doorOpeningThread, doorClosingThread, doorIdleThenCloseThread;

    public boolean isPersonApproaching;

    public ControlSystem(DataListenerInterface dataListenerInterface, long openTime, long closeTime){
        this.dataListenerInterface=dataListenerInterface;
        doorOpenTime=openTime;
        doorCloseTime=closeTime;
        doorState=DoorState.Closed;
        isPersonApproaching=false;
    }

    public void setPersonApproaching(boolean personApproaching){
        this.isPersonApproaching=personApproaching;
    }

    public boolean isGateOpen(){
        return doorState.equals(DoorState.Open);
    }

    public boolean isGateClosed(){
        return doorState.equals(DoorState.Closed);
    }

    public void OpenDoor(){
        doorOpeningThread=new Thread(){
            @Override
            public void run() {
                super.run();
                switch (doorState){
                    case Open -> {
                        System.out.println(new Date().toString()+" : Door State - "+"Door Already Open");
                    }
                    case CurrentlyOpening -> {
                        System.out.println(new Date().toString()+" : Door State - "+"Door Already Opening");
                    }
                    case CurrentlyClosing -> {
                        System.out.println(new Date().toString()+" : Door State - "+"Stopping the Door Closing and Starting the Door Opening");
                        isGateClosing=false;
                        openPercent=(100-closePercent);
                        isGateOpening=true;
                        doorState=DoorState.CurrentlyOpening;
                        dataListenerInterface.updateData(DoorState.CurrentlyClosing);
                        long startTime=(100*System.currentTimeMillis())-(openPercent*doorOpenTime);
                        startTime=startTime/100;
                        while (openPercent<=100 && isGateOpening){
                            openPercent=(System.currentTimeMillis()-startTime);
                            openPercent*=100;
                            openPercent/=doorOpenTime;
                            dataListenerInterface.updateData(DoorState.CurrentlyClosing);
                        }
                        if(isGateOpening){
                            System.out.println(new Date().toString()+" : Door State - "+"Door Opened");
                            doorState=DoorState.Open;
                            dataListenerInterface.updateData(DoorState.CurrentlyOpening);
                            isGateOpening=false;
                        }
                    }
                    case Closed -> {
                        System.out.println(new Date().toString()+" : Door State - "+"Opening Door");
                        openPercent=0;
                        isGateOpening=true;
                        doorState=DoorState.CurrentlyOpening;
                        dataListenerInterface.updateData(DoorState.Closed);
                        long startTime=System.currentTimeMillis();
                        while (openPercent<=100 && isGateOpening){
                            openPercent=(System.currentTimeMillis()-startTime);
                            openPercent*=100;
                            openPercent/=doorOpenTime;
                            dataListenerInterface.updateData(DoorState.Closed);
                        }
                        if(isGateOpening){
                            System.out.println(new Date().toString()+" : Door State - "+"Door Opened");
                            doorState=DoorState.Open;
                            dataListenerInterface.updateData(DoorState.CurrentlyOpening);
                            isGateOpening=false;
                        }
                    }
                }
            }
        };
        doorOpeningThread.start();
    }

    public void keepDoorIdleThenClose(){
        doorIdleThenCloseThread=new Thread(){
            @Override
            public void run() {
                super.run();
                if (!isPersonApproaching && isGateOpen()){
                    System.out.println(new Date().toString()+" : Door State - "+"Keeping the Door State Open");
                    long startTime=System.currentTimeMillis();
                    while (System.currentTimeMillis()<=startTime+doorIdleTime){
                        if(isPersonApproaching){
                            break;
                        }
                    }
                    if(!isPersonApproaching){
                        dataListenerInterface.onDoorClosing();
                        CloseDoor();
                    }
                }
            }
        };
        doorIdleThenCloseThread.start();
    }

    public void CloseDoor(){
        doorClosingThread=new Thread(){
            @Override
            public void run() {
                super.run();
                if (!isPersonApproaching) {
                    switch (doorState) {
                        case Open -> {
                            System.out.println(new Date().toString()+" : Door State - "+"Closing Door");
                            closePercent = 0;
                            isGateClosing = true;
                            doorState = DoorState.CurrentlyClosing;
                            dataListenerInterface.updateData(DoorState.Open);
                            long startTime = System.currentTimeMillis();
                            while (closePercent <= 100 && isGateClosing) {
                                closePercent = (System.currentTimeMillis() - startTime);
                                closePercent *= 100;
                                closePercent /= doorCloseTime;
                                dataListenerInterface.updateData(DoorState.Open);
                            }
                            if (isGateClosing) {
                                System.out.println(new Date().toString()+" : Door State - "+"Door Closed");
                                doorState = DoorState.Closed;
                                dataListenerInterface.updateData(DoorState.CurrentlyOpening);
                                isGateClosing = false;
                            }
                        }
                        case CurrentlyOpening -> {
                            System.out.println(new Date().toString()+" : Door State - "+"Stopping the Door Opening and Starting the Door Closing");
                            isGateOpening = false;
                            closePercent = (100 - openPercent);
                            isGateClosing = true;
                            doorState = DoorState.CurrentlyClosing;
                            dataListenerInterface.updateData(DoorState.CurrentlyOpening);
                            long startTime = (100 * System.currentTimeMillis()) - (closePercent * doorCloseTime);
                            startTime = startTime / 100;
                            while (closePercent <= 100 && isGateClosing) {
                                closePercent = (System.currentTimeMillis() - startTime);
                                closePercent *= 100;
                                closePercent /= doorCloseTime;
                                dataListenerInterface.updateData(DoorState.CurrentlyOpening);
                            }
                            if (isGateClosing) {
                                System.out.println(new Date().toString()+" : Door State - "+"Door Closed");
                                doorState = DoorState.Closed;
                                dataListenerInterface.updateData(DoorState.CurrentlyClosing);
                                isGateClosing = false;
                            }
                        }
                        case CurrentlyClosing -> {
                            System.out.println(new Date().toString()+" : Door State - "+"Door Already Closing");
                        }
                        case Closed -> {
                            System.out.println(new Date().toString()+" : Door State - "+"Door Already Closed");
                        }
                    }
                }
            }
        };
        doorClosingThread.start();
    }

    public long getDoorOpenTime() {
        return doorOpenTime;
    }

    public void setDoorOpenTime(long doorOpenTime) {
        this.doorOpenTime = doorOpenTime;
    }

    public long getDoorCloseTime() {
        return doorCloseTime;
    }

    public void setDoorCloseTime(long doorCloseTime) {
        this.doorCloseTime = doorCloseTime;
    }

    public long getDoorIdleTime() {
        return doorIdleTime;
    }

    public void setDoorIdleTime(long doorIdleTime) {
        this.doorIdleTime = doorIdleTime;
    }

    public DoorState getDoorState(){
        return doorState;
    }

    public void setDoorState(DoorState doorState){
        this.doorState = doorState;
    }

    public boolean isGateOpening() {
        return isGateOpening;
    }

    public void setGateOpening(boolean gateOpening) {
        isGateOpening = gateOpening;
    }

    public boolean isGateClosing() {
        return isGateClosing;
    }

    public void setGateClosing(boolean gateClosing) {
        isGateClosing = gateClosing;
    }

    public long getOpenPercent() {
        return openPercent;
    }

    public void setOpenPercent(int openPercent) {
        this.openPercent = openPercent;
    }

    public long getClosePercent() {
        return closePercent;
    }

    public void setClosePercent(int closePercent) {
        this.closePercent = closePercent;
    }
}

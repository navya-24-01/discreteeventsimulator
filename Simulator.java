import java.util.function.Supplier;

public class Simulator {
    private final int qmax;
    private final int numOfServers;
    private final ImList<Double> arrivalTimes;
    private final Supplier<Double> serviceTime;
    private final Supplier<Double> restTimes;
    private final int numOfSelfChecks;

    Simulator(int numOfServers, int numOfSelfChecks, int qmax, ImList<Double> arrivalTimes, 
         Supplier<Double> serviceTime, Supplier<Double> restTimes) {
        this.numOfServers = numOfServers;
        this.arrivalTimes = arrivalTimes;
        this.serviceTime = serviceTime;
        this.restTimes = restTimes;
        this.qmax = qmax;
        this.numOfSelfChecks = numOfSelfChecks;
    }

    ImList<Server> createAllServersList() {
        ImList<Server> allServers = new ImList<Server>();
        for (int i = 0; i < this.numOfServers; i++) {
            allServers = allServers.add(new HumanServer(0, i, 
                 new Queue(this.qmax, 0), restTimes));
        }
        for (int j = 0; j < this.numOfSelfChecks; j++) {
            if (j == 0) {
                allServers = allServers.add(new SelfCheckOutCounter(0, this.numOfServers + j, 
                     new Queue(this.qmax, 0), restTimes));
            } else {
                allServers = allServers.add(new SelfCheckOutCounter(0, this.numOfServers + j, 
                     new Queue(0, 0), restTimes));
            }
        
        }
        //System.out.println(allServers);
        return allServers;
    }
    
    PQ<Events> createPQ() {
        PQ<Events> pqEvents = new PQ<Events>(new EventsComp());
        for (int  i = 0; i < arrivalTimes.size(); i++) {
            pqEvents = pqEvents.add(new Arrival(arrivalTimes.get(i),
                 new Customer(arrivalTimes.get(i),
                     i, serviceTime),new Server(0, 0,
                          new Queue(this.qmax, 0), restTimes)));
        }
        return pqEvents;
    }

    ImList<Double> createEmptyImList(int size) {
        ImList<Double> empty = new ImList<Double>();
        for (int i = 0; i < size; i++) {
            empty = empty.add(0.00);
        }
        return empty;
    }

    String simulate() {
        int count = 0;
        ImList<Double> waitingTime = createEmptyImList(arrivalTimes.size());
        double waitTime = 0;
        String s = new String();
        ImList<Double> waitingTimeS = createEmptyImList(arrivalTimes.size());
        ImList<Double> waitingTimeE = createEmptyImList(arrivalTimes.size());
        Shop shop = new Shop(this.createAllServersList(), this.numOfServers);
        PQ<Events> pqEvents = createPQ();
        while (pqEvents.isEmpty() == false) {
            Events currEvent = pqEvents.poll().first();
            pqEvents = pqEvents.poll().second();
            currEvent = currEvent.isServed(shop);
            if (currEvent.hasLeft()) { 
                count = count + 1;
                //System.out.println(count);
            }
            pqEvents = currEvent.nextEvent(shop, pqEvents);
            if (currEvent.waitingTimeStarts() > 0) {
                waitingTimeS = currEvent.setWaitingTime(waitingTimeS,
                      currEvent.waitingTimeStarts());
            }
            if (currEvent.waitingTimeEnds() > 0) {
                waitingTimeE = currEvent.setWaitingTime(waitingTimeE,
                      currEvent.waitingTimeEnds());
            }
            shop = currEvent.changeServerList(shop);
            s = s + currEvent.toString();
        }

        for (int i = 0; i < waitingTime.size(); i++) {
            waitingTime = waitingTime.set(i,waitingTimeE.get(i) - waitingTimeS.get(i));
            waitTime = waitTime + waitingTime.get(i);
        }
        //System.out.println("startwaiting" + waitingTimeS);
        //System.out.println("stopwaiting" + waitingTimeE);
        //System.out.println("waitingtimes are" + waitingTime);
        int numServed = this.arrivalTimes.size() - count;
        if (numServed == 0) {
            return s + "[" + String.format("%.3f",(0)) + 
                 " " + (this.arrivalTimes.size() - count) + " " + count + "]";
        }
        return s + "[" + String.format("%.3f",(waitTime / 
             (double)(this.arrivalTimes.size() - count))) + 
             " " + (this.arrivalTimes.size() - count) + " " + count + "]";
    }

}

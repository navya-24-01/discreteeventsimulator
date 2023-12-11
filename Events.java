abstract class Events {
    protected final Customer customer;
    protected final double timeStamp;
    protected final Server server;

    Events(Double timeStamp, Customer customer, Server server) {
        this.timeStamp = timeStamp;
        this.customer = customer;
        this.server = server;
    }

    abstract Events isServed(Shop shop);

    abstract boolean hasLeft();

    double waitingTimeStarts() {
        return 0;
    }

    double waitingTimeEnds() {
        return 0;
    }

    abstract Shop changeServerList(Shop shop);

    abstract PQ<Events> nextEvent(Shop shop, PQ<Events> pqEvents);
    
    public abstract String toString();  

    abstract boolean isWaiting(Server server, Customer customer, double time);

    int compare(Events e) {
        if (this.timeStamp - e.timeStamp > 0) {
            return 1;
        } else if (this.timeStamp - e.timeStamp < 0) {
            return -1;
        } else {
            return this.customer.compare(e.customer);
        }
    }

    ImList<Double> setWaitingTime(ImList<Double> waitingTimes, Double time) {
        return this.customer.setWaitingTime(waitingTimes, time);
    }
}

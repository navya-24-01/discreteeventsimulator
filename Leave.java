class Leave extends Events {
    
    Leave(Double timeStamp, Customer customer, Server server) {
        super(timeStamp, customer, server);
    }

    Events isServed(Shop shop) {
        return new Leave(super.timeStamp, super.customer, super.server);
    }

    Shop changeServerList(Shop shop) {
        return shop;
    }

    PQ<Events> nextEvent(Shop shop, PQ<Events> pqEvents) { 
        return pqEvents;
    }

    boolean hasLeft() {
        return true;
    }


    public String toString() {
        return String.format("%.3f", super.timeStamp) +
             " " + (super.customer) +
                 " leaves" +  System.lineSeparator();
    }
    
    boolean isWaiting(Server server, Customer customer, double time) {
        return false;
    }
}
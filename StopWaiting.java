class StopWaiting extends Events {
    StopWaiting(Double timeStamp, Customer customer, Server server) {
        super(timeStamp, customer, server);
    }

    Events isServed(Shop shop) {
        return new StopWaiting(super.timeStamp, super.customer, 
             shop.getServer(super.server.getServerID()));
    }

    Shop changeServerList(Shop shop) {
        return shop.updateAfterWait(super.server);
    }


    PQ<Events> nextEvent(Shop shop, PQ<Events> pqEvents) {
        return pqEvents.add(new Serve(super.timeStamp,
                super.customer,shop.getServer(super.server.getServerID())));
    }

    boolean hasLeft() {
        return false;
    }

    @Override
    double waitingTimeEnds() {
        return super.timeStamp;
    }

    
    boolean isWaiting(Server thisserver, Customer thiscustomer, double time) {
        if (super.server.equals(thisserver)) {
            if (super.customer.compare(thiscustomer) == -1) {
                return true;
            }
        }
        return false;
    }
    


    public String toString() {
        return "";
    }
    
}

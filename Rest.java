class Rest extends Events {
    
    Rest(Double timeStamp, Customer customer, Server server) {
        super(timeStamp, customer, server);
    }

    Events isServed(Shop shop) {
        return new Rest(super.timeStamp, super.customer,
             shop.getServer(super.server.getServerID()));
    }

    Shop changeServerList(Shop shop) {
        return shop.setServer(super.server.getServerID(), 
             super.server.changeAvailableTime(super.server.getAvailaibleTime() +  
                  super.server.getRestTime()));
    }

    PQ<Events> nextEvent(Shop shop, PQ<Events> pqEvents) { 
        return pqEvents;
    }

    boolean hasLeft() {
        return false;
    }

    public String toString() {
        return "";
    }
    
    boolean isWaiting(Server thisserver, Customer customer, double time) {
        if (super.server.equals(thisserver)) {
            return true;
        }
        return false;
    }
}

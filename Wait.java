class Wait extends Events {
    
    Wait(Double timeStamp, Customer customer, Server server) {
        super(timeStamp, customer, server);
    }

    Events isServed(Shop shop) {
        //System.out.println("WAIT isServed" + shop.getServer(super.server.getServerID()));;
        return new Wait(super.timeStamp, super.customer,shop.getServer(super.server.getServerID()));
    }
    
    Shop changeServerList(Shop shop) {
        return shop;
    }

    PQ<Events> nextEvent(Shop shop, PQ<Events> pqEvents) {
        Server fastestServer = shop.moveQueue(super.server);
        //System.out.println("WAIT next event fastest server" + fastestServer);
        return pqEvents.add(new ContinueWaiting(fastestServer.getAvailaibleTime(),
                 super.customer,fastestServer));
    }
    

    boolean hasLeft() {
        return false;
    }

    public String toString() {
        return String.format("%.3f", super.timeStamp) +
             " " +  (super.customer) +
                  " waits at " + super.server +  System.lineSeparator();
    }

    @Override
    double waitingTimeStarts() {
        return super.timeStamp;
    }


    boolean isWaiting(Server server, Customer customer, double time) {
        return false;
    }

  

}
    
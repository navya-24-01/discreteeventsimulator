class Done extends Events {
    
    Done(Double timeStamp, Customer customer, Server server) {
        super(timeStamp, customer, server);
    }
   
    Events isServed(Shop shop) {
        return new Done(super.timeStamp, super.customer,
              shop.getServer(super.server.getServerID()));
    }
   
    Shop changeServerList(Shop shop) {
        return shop.setServer(super.server.getServerID(), super.server);         
    }
   
    PQ<Events> nextEvent(Shop shop, PQ<Events> pqEvents) { 
        return pqEvents.add(new Rest(super.server.getAvailaibleTime(), 
            super.customer, super.server));
    }
        
    boolean hasLeft() {
        return false;
    }

   
    public String toString() {
        return String.format("%.3f", super.timeStamp) +
             " " + (super.customer) + " done serving by " +
                 super.server +  System.lineSeparator();
    }

    boolean isWaiting(Server thisserver, Customer thiscustomer, double time) {
        if (super.server.equals(thisserver)) {
            if (super.customer.compare(thiscustomer) == -1) {
                return true;
            }
        }
        return false;
    }
}
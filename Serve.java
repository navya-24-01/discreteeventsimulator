class Serve extends Events {
    
    Serve(Double timeStamp, Customer customer, Server server) {
        super(timeStamp, customer, server);
    }

    Events isServed(Shop shop) {
        double customerServiceTime = super.customer.getServiceTime();
        if (super.server.getAvailaibleTime() == 0 ||
                 super.timeStamp > super.server.getAvailaibleTime()) {
            customerServiceTime = customerServiceTime + super.timeStamp;
        } else {
            customerServiceTime = customerServiceTime + 
                 super.server.getAvailaibleTime();
        }
        return new Serve(super.timeStamp, super.customer,
             shop.getServer(super.server.getServerID()).changeAvailableTime(customerServiceTime));
    }


    Shop changeServerList(Shop shop) {
        return shop.setServer(super.server.getServerID(), super.server);
    }
    
    boolean hasLeft() {
        return false;
    }

    PQ<Events> nextEvent(Shop shop, PQ<Events> pqEvents) { 
        return pqEvents.add(new Done(super.server.getAvailaibleTime(),
              super.customer, super.server));
    }

    public String toString() {
        return String.format("%.3f", super.timeStamp) +
             " " + (super.customer) +
                 " serves by " + (super.server) + System.lineSeparator();
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
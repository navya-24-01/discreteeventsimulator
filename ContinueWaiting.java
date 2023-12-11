class ContinueWaiting extends Events {
    
    ContinueWaiting(Double timeStamp, Customer customer, Server server) {
        super(timeStamp, customer, server);
    }

    Events isServed(Shop shop) {
        return new ContinueWaiting(super.timeStamp, super.customer,
              shop.getServer(super.server.getServerID()));
    }

    Shop changeServerList(Shop shop) {
        return shop.setServer(super.server.getServerID(),super.server);
    }

    PQ<Events> nextEvent(Shop shop, PQ<Events> pqEvents) {
        Server fastestServer = shop.moveQueue(super.server);
        boolean serve = false;
        PQ<Events> tempEvents = pqEvents;
        while (tempEvents.isEmpty() == false) {
            Events currEvent = tempEvents.poll().first();
            tempEvents = tempEvents.poll().second();
            serve = currEvent.isWaiting(fastestServer, super.customer, super.timeStamp);
            if (serve == true) {
                break;
            }
        }
        if (serve == true) {
            return pqEvents.add(new ContinueWaiting(
                     shop.getServerAvailableTime(fastestServer.getServerID()),
                         super.customer, shop
                             .getServer(fastestServer.getServerID())));
        }
        return pqEvents.add(new StopWaiting(
                 shop.getServerAvailableTime(
                     fastestServer.serverID),
                          super.customer,shop.getServer(fastestServer.getServerID())));
    }

    boolean hasLeft() {
        return false;
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

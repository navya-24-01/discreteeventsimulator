class Arrival extends Events {
 
    Arrival(Double timeStamp, Customer customer, Server server) {
        super(timeStamp, customer, server);
    }

    Events isServed(Shop shop) {
        if (shop.isServerAvailable(super.timeStamp)) {
            return new Arrival(super.timeStamp, super.customer,
                 shop.availableServer(super.timeStamp));
        } else if (shop.isServerQueueFree(super.timeStamp)) {
            return new Arrival(super.timeStamp, super.customer,
                  shop.queueAtServer(super.timeStamp));
        } else {
            return new Arrival(super.timeStamp, super.customer, super.server);
        }
    }

    Shop changeServerList(Shop shop) {
        if (shop.isServerAvailable(super.timeStamp)) {
            return shop;
        }
        if (shop.isServerQueueFree(super.timeStamp)) {
            return shop.addToQueue(shop.queueAtServer(super.timeStamp));
        }
        return shop;
    }

    PQ<Events> nextEvent(Shop shop, PQ<Events> pqEvents) {
        if (shop.noServers()) {
            return pqEvents.add(new Leave(super.timeStamp,
                 super.customer,super.server));
        }
        if (shop.isServerAvailable(super.timeStamp)) {
            return pqEvents.add(new Serve(super.timeStamp,
                 super.customer, super.server));
        } else if (shop.isServerQueueFree(super.timeStamp)) {
            return pqEvents.add(new Wait(super.timeStamp,super.customer,
                 shop.getServer(super.server.getServerID()).addToServerQueue()));
        } else {
            return pqEvents.add(new Leave(super.timeStamp,
                  super.customer,super.server)); 
        }
    }

    boolean hasLeft() {
        return false;
    }

    public String toString() {
        return String.format("%.3f", super.timeStamp) +
             " " + (super.customer) +
                 " arrives" + System.lineSeparator();
    }   

   
    boolean isWaiting(Server server, Customer customer, double time) {
        return false;
    }
}

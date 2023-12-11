import java.util.function.Supplier;

class Shop {
    private final ImList<Server> allServers;
    private final int numOfServers;

    Shop(ImList<Server> allServers, int numOfServers) {
        this.allServers = allServers;
        this.numOfServers = numOfServers;
    }

    Boolean isServerAvailable(double time) {
        for (int i = 0; i < allServers.size(); i++) {
            if (allServers.get(i).available(time)) {
                return true;    
            }
        }  
        return false;
    }

    Server availableServer(double time) {
        for (int i = 0; i < allServers.size(); i++) {
            if (allServers.get(i).available(time)) {
                return allServers.get(i);    
            }
        }  
        return new Server(0.00, -1, new Queue(0,0),() -> 0.0);
    }

    Boolean isServerQueueFree(double time) {
        for (int j = 0; j < allServers.size(); j++) {
            if (allServers.get(j).isQueueFree()) {
                return true;
            }
        }
        return false;
    }

    Server queueAtServer(double time) {
        for (int j = 0; j < allServers.size(); j++) {
            if (allServers.get(j).isQueueFree()) {
                return allServers.get(j);
            }
        }
        return new Server(0.00, -1, new Queue(0,0), () -> 0.0);
    }

    Shop addToQueue(Server server) {
        return new Shop(this.allServers.set(server.getServerID(),
              server.addToServerQueue()), this.numOfServers);
    }

    Shop removeFromQueue(Server server) {
        return new Shop(this.allServers.set(server.getServerID(),
              server.removeFromServerQueue()), this.numOfServers);
    }

    Shop changeAvailableTime(Server server, double availableTime) {
        return new Shop(this.allServers.set(server.getServerID(),
              server.changeAvailableTime(availableTime)), this.numOfServers);
    }

    Boolean noServers() {
        if (allServers.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    Server getServer(int serverID) {
        return this.allServers.get(serverID);
    }

    Shop setServer(int serverID, Server server) {
        return new Shop(this.allServers.set(serverID, server), this.numOfServers);
    }

    Double getServerAvailableTime(int serverID) {
        return allServers.get(serverID).getAvailaibleTime();
    }

    Server moveQueue(Server server) {
        if (this.numOfServers <= server.getServerID()) {
            double nextTime = this.getServer(server.getServerID()).getAvailaibleTime();
            int index = server.getServerID();
            for (int i = this.numOfServers; i < this.allServers.size(); i++) {
                if (nextTime > this.allServers.get(i).getAvailaibleTime()) {
                    index = i;
                }
            }
            return this.getServer(index);
        } else {
            return server;
        }
    }

    Shop updateAfterWait(Server server) {
        if (server.getServerID() > this.numOfServers) {
            return this.setServer(this.numOfServers, 
                 this.getServer(this.numOfServers).removeFromServerQueue());
        }
        return this.setServer(server.getServerID(), server.removeFromServerQueue());
    }

    /*int getNumOfServers() {
        return this.numOfServers;
    }*/

    
}


import java.util.function.Supplier;

class Server {
    protected final double availableTime;
    protected final int serverID;
    protected final Queue queue;
    protected final Supplier<Double> restTimes;

    Server(double availableTime, int serverID, Queue queue, Supplier<Double> restTimes) {
        this.availableTime = availableTime;
        this.serverID = serverID;
        this.queue = queue;
        this.restTimes = restTimes;
    }
    
    Boolean available(double arrivalTime) {
        if (this.availableTime == 0.0) {
            return true;
        } else if (this.availableTime <= arrivalTime) {
            return true;
        } else {
            return false;
        }
    }

    Boolean isQueueFree() {
        return this.queue.isQueueFree();
    }

    Boolean isNotZERO() {
        if (this.serverID != 0) {
            return true;
        } else {
            return false;
        }
    }

    Double getAvailaibleTime() {
        return this.availableTime;
    }

    Double getRestTime() {
        return restTimes.get();
    }

    public String toString() {
        return "" + (this.serverID + 1);
    }
    
    
    int getServerID() {
        return serverID;
    }

    Server addToServerQueue() {
        return new Server(this.availableTime, this.serverID,
              this.queue.addToQueue(), this.restTimes);
    }

    Server removeFromServerQueue() {
        return new Server(this.availableTime, this.serverID, 
             this.queue.removeFromQueue(), this.restTimes);
    }

    Server changeQueue(Queue newQueue) {
        return new Server(this.availableTime, 
             this.serverID, newQueue, this.restTimes);
    }

    Server changeAvailableTime(Double newAvailableTime) {
        return new Server(newAvailableTime, this.serverID, 
             this.queue, this.restTimes);
    }

    boolean equals(Server other) {
        if (this.serverID == other.serverID) {
            return true;
        } else {
            return false;
        }
    }

}


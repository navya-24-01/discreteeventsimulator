import java.util.function.Supplier;

class HumanServer extends Server {
    HumanServer(double availableTime, int serverID, Queue queue, Supplier<Double> restTimes) {
        super(availableTime, serverID, queue, restTimes);
    }

    HumanServer addToServerQueue() {
        return new HumanServer(this.availableTime, this.serverID,
              this.queue.addToQueue(), this.restTimes);
    }

    HumanServer removeFromServerQueue() {
        return new HumanServer(this.availableTime, this.serverID, 
             this.queue.removeFromQueue(), this.restTimes);
    }

    HumanServer changeQueue(Queue newQueue) {
        return new HumanServer(this.availableTime, 
             this.serverID, newQueue, this.restTimes);
    }

    HumanServer changeAvailableTime(Double newAvailableTime) {
        return new HumanServer(newAvailableTime, this.serverID, 
             this.queue, this.restTimes);
    }

}

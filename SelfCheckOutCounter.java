import java.util.function.Supplier;

class SelfCheckOutCounter extends Server {
    SelfCheckOutCounter(double availableTime, int serverID, 
         Queue queue, Supplier<Double> restTimes) {
        super(availableTime, serverID, queue, () -> 0.0);
    }

    SelfCheckOutCounter addToServerQueue() {
        return new SelfCheckOutCounter(this.availableTime, this.serverID,
              this.queue.addToQueue(), this.restTimes);
    }

    SelfCheckOutCounter removeFromServerQueue() {
        return new SelfCheckOutCounter(this.availableTime, this.serverID, 
             this.queue.removeFromQueue(), this.restTimes);
    }

    SelfCheckOutCounter changeQueue(Queue newQueue) {
        return new SelfCheckOutCounter(this.availableTime, 
             this.serverID, newQueue, this.restTimes);
    }

    SelfCheckOutCounter changeAvailableTime(Double newAvailableTime) {
        return new SelfCheckOutCounter(newAvailableTime, this.serverID, 
             this.queue, this.restTimes);
    }

    @Override
    public String toString() {
        return "self-check " + (super.getServerID() + 1);
        //+ "avTime is " + super.getAvailaibleTime() + "queue" + super.getQueue();
    }
}
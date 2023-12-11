class Queue {
    private final int qmax;
    private final int numOfCustomers;
 
    Queue(int qmax, int numOfCustomers) {
        this.qmax = qmax;
        this.numOfCustomers = numOfCustomers;
    }

    boolean isQueueFree() {
        if (this.numOfCustomers < this.qmax) {
            return true;
        } else {
            return false;
        }
    }

    Queue addToQueue() {
        return new Queue(this.qmax, (this.numOfCustomers + 1));

    }

    Queue removeFromQueue() {
        return new Queue(this.qmax, (this.numOfCustomers - 1));
    }

    Queue changeQueueTime(double queueNextTime) {
        return new Queue(this.qmax, this.numOfCustomers);
    }

    public String toString() {
        return " " + " numofc = " + this.numOfCustomers + " qmax = " + this.qmax;
    }
}
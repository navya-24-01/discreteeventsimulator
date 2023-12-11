import java.util.function.Supplier;

class Customer {
    private final int customerID;
    private final Supplier<Double> serviceTime;

    Customer(double arrivalTime, int customerID, Supplier<Double> serviceTime) {
        this.customerID = customerID;
        this.serviceTime = serviceTime;
    }

    double getServiceTime() {
        return this.serviceTime.get();
    }

    boolean isNotZERO() {
        if (this.customerID != 0) {
            return true;
        } else { 
            return false;
        }

    }

    int compare(Customer other) {
        if (this.customerID >= other.customerID) {
            return 1;
        }
        return -1;
    }


    ImList<Double> setWaitingTime(ImList<Double> waitingTimes, Double time) {
        return waitingTimes.set(this.customerID - 1, time);
    }

    public String toString() {
        return "" + (this.customerID  + 1);
    }  
}

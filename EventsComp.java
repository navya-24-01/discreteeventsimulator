import java.util.Comparator;

class EventsComp implements Comparator<Events> {

    public int compare(Events e1, Events e2) {
        return e1.compare(e2);  
    }
}

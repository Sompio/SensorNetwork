import java.util.Random;

/**
 * Created by per-joelsompio on 18/05/16.
 */
public class SuperNode extends Node {

    public SuperNode(Position p, Network n) {
        this.network = n;
        this.position = p;

    }

    public Event getRandomEvent() {
        Random rand = new Random();

        int x = rand.nextInt( this.network.getEvents().size() );
        Event event  = this.network.getEvents().get(x);
        return event;
    }



    public Request makeRequest() {
        // ta id från tabe

        Event event = getRandomEvent();
        return new Request(this.position, event);
    }
}
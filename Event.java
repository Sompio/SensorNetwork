import java.util.ArrayList;

/**
 * Created by rasblo on 2016-05-17.
 */
public class Event {
    private int IDTime;
    private int ID;
    private Position position;


    public Event(int ID, int IDTime, Position p) {
        this.ID = ID;
        this.IDTime = IDTime;
        this.position = p;

    }

    public int getTime() {
        return this.IDTime;
    }

    public int getID() {
        return this.ID;
    }

    public Position getPosition() {
        return this.position;
    }
}

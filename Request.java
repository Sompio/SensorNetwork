import java.util.Stack;

/**
 * Created by rasblo on 2016-05-17.
 */
public class Request extends Message {
    private Stack<Node> stackMovement;
    private boolean answer;
    private Event targetEvent;
    private int maxSteps;


    public Request(Position startPos, Event e){
        System.out.println("REQUEST");
        this.targetEvent = e;
        this.position = new Position(startPos.getX(), startPos.getY());
        this.stackMovement = new Stack<>();
        this.answer = false;
        this.maxSteps = 45;
    }

    public void move(Node n){
        this.maxSteps--;
        if(!this.answer) {
            this.stackMovement.push(n);
        }
        this.position = new Position(n.getPosition().getX(), n.getPosition().getY());
        if(this.stackMovement.isEmpty()){
            System.out.println("MÅL");
        }
    }

    public int getEventID(){
        return this.targetEvent.getID();
    }

    public boolean reachedRightNode(){
        return this.answer;
    }

    public void setToAnswer(){
        this.answer = true;
    }

    public int getMaxSteps(){
        return this.maxSteps;
    }
    public Node prevNode(){
        return this.stackMovement.pop();
    }
}

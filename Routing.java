/**
        * Created by rasblo on 2016-05-23.
        */
public class Routing {
    private Node node;
    private int steps;

    public Routing(Node n, int s){
        this.node = n;
        this.steps = s;
    }

    public Node getNode(){
        return this.node;
    }

    public int getSteps(){
        return this.steps;
    }

    public void stepUp(){
        this.steps++;
    }
}

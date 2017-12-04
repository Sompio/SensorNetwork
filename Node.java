import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class Node {
    //protected Network network;
    protected Position position;
    protected LinkedList<Node> neighbours;
    protected ArrayList<Event> eventTable;
    protected ArrayList<Routing> routingTable;
    protected boolean free;
    protected LinkedList<Message> messageQueue;

    public Node(){}

    public Node(Position p, Network n){
        //this.network = n;
        this.position = p;
        this.neighbours = new LinkedList<>();
        this.eventTable = new ArrayList<>();
        this.routingTable = new ArrayList<>();
        this.free = true;
        this.messageQueue = new LinkedList<>();
    }

    public void addNeighbour(Node n){
        this.neighbours.add(n);
    }

    public void addToQueue(Message m){
        this.messageQueue.add(m);
    }

    public Position getPosition(){
        return this.position;
    }

    public void detectEvent(){

        Node n = this.neighbours.remove();
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(10000);
        if(randomInt == 1) {
            Event e = new Event(id, , n.getPosition());
        }




        Node n = this.neighbours.remove();
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(10000);
        if(randomInt == 1){
            Event e = new Event(n.getPosition(), this.network);
            this.addEvent(e);
            this.addRouting(n, 1);
            this.network.addEvent(e);
            int randInt = randomGenerator.nextInt(2);
            if(randInt == 1){
                Agent a = new Agent(this.position);
                this.addToQueue(a);
            }
        }
        this.neighbours.add(n);
    }

    public void checkMessageQueue(){
        if(this.messageQueue.size() > 0) {
            Message m = this.messageQueue.remove();
            if (m instanceof Agent) {
                this.sendAgent((Agent)m);
            }
            else if(m instanceof Request){
                this.sendRequest((Request)m);
            }
        }
    }

    public void addEvent(Event e){
        this.eventTable.add(e);
    }

    public void addRouting(Node n, int steps){
        this.routingTable.add(new Routing(n, steps));
    }

    public void sendRequest(Request r){
        //Kolla tabellerna efter händelse och väg
        if(r.reachedRightNode()) {
            Node p = r.prevNode();
            r.move(p.getPosition());
            p.addToQueue(r);
        }
        else{
            boolean found = false;
            for (int i = 0; i < this.eventTable.size() && !found; i++) {
                if (r.getEventID() == this.eventTable.get(i).getID()) {
                    found = true;
                    r.move(this.routingTable.get(i).getNode());
                    this.routingTable.get(i).getNode().addToQueue(r);
                    if (this.routingTable.get(i).getSteps() == 1) {
                        r.setToAnswer();
                    }
                }
            }
            if (!found && r.getMaxSteps() > 0) {
                Node randN = this.randomNeighbour();
                r.move(randN);
                randN.addToQueue(r);
            }
        }
    }

    public void sendAgent(Agent a){
        //Uppdatera info från och till agent
        ArrayList<Event> agentEvents = a.getEvents();
        ArrayList<Routing> agentRouting = a.getRoutingTable();

        for(int i = 0; i < this.eventTable.size(); i++){
            if(!agentEvents.contains(this.eventTable.get(i))){
                agentEvents.add(this.eventTable.get(i));
                agentRouting.add(this.routingTable.get(i));
            }
        }

        for(int i = 0; i < agentEvents.size(); i++){
            if(!this.eventTable.contains(agentEvents.get(i))){
                this.eventTable.add(agentEvents.get(i));
                this.routingTable.add(agentRouting.get(i));
            }
        }

        //Flyttar agenten
        Node randN = this.randomNeighbour();

        int maxLimit = 16;
        while(Arrays.asList(a.getVisited()).contains(randN) && maxLimit > 0){
            randN = this.randomNeighbour();
            maxLimit--;
        }

        a.move(this.randomNeighbour().getPosition());
        randN.addToQueue(a);
    }

    private Node randomNeighbour(){
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(this.neighbours.size());
        Node n = this.neighbours.get(randomInt);

        return n;
    }
}
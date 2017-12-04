import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Agent extends Message {

    private ArrayList<Event> eventTable;
    private ArrayList<Routing> routingTable;
    private Node[] visitedNodes;


    public Agent(Position pos){
        this.position = pos;
        this.eventTable = new ArrayList<>();
        this.routingTable = new ArrayList<>();
        this.visitedNodes = new Node[50];
    }

    //Agenten rör sig slumpartat, ska användas en gång per tidssteg (Den kan inte gå tillbaka till en redan besökt nod och den måste hålla sig inom nätverkets storlek)
    public void move(Position p){
        this.position = new Position(p.getX(), p.getY());

        for(int i = 0; i < this.routingTable.size(); i++){
            this.routingTable.get(i).stepUp();
        }
    }

    //Agenten ska utbyta info med varje ny besökt nod, info är eventTable, och RoutingTable
    public ArrayList<Routing> getRoutingTable(){
        return this.routingTable;
    }

    public ArrayList<Event> getEvents(){
        return this.eventTable;
    }

    public Node[] getVisited(){
        return this.visitedNodes;
    }
}

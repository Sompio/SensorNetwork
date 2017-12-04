import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by rasblo on 2016-05-17.
 */
public class Network {

    private int time = 0;
    private Node[][] board;
    private ArrayList<Event> allEvents;

    public Network() {
        Position[] p = this.makeSuperNode();
        this.allEvents = new ArrayList<>();

        this.board = new Node[50][50];

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                boolean done = false;
                this.board[i][j] = new SuperNode(new Position(i, j), this);
                /*if(done){
                    //Skapa Vanlig Node
                    this.board[i][j] = new Node(new Position(i,j), this);
                }*/
            }
        }

        for (int nodeX = 0; nodeX < 50; nodeX++) {
            for (int nodeY = 0; nodeY < 50; nodeY++) {
                this.checkNeighbours(this.board[nodeX][nodeY]);
            }
        }
    }

    public void timeIncrease() {
        this.time++;
        System.out.println(this.time);



        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                this.board[i][j].detectEvent();
                this.board[i][j].checkMessageQueue();
                if (this.board[i][j] instanceof SuperNode && this.time % 400 == 0) {
                    System.out.println("lllll");
                }
            }
        }


        if (this.time == 10000) {
            System.out.printf("Slut");
            //Hur ska det se ut då programmet är slut?
        }
    }

    public Position[] makeSuperNode() {
        Position[] nodes = new Position[4];

        Random rand = new Random();

        for (int i = 0; i < 4; i++) {
            boolean duplicate = false;
            int x = rand.nextInt(50);
            int y = rand.nextInt(50);

            for (int j = 0; j < i; j++) {
                if (nodes[j].getX() == x && nodes[j].getY() == y) {
                    duplicate = true;
                }
            }
            //Jämför arrayens innehåll med den nya positionen
            if (!duplicate) {
                //Lägger till ny position i array
                nodes[i] = new Position(x, y);
            } else {
                i--;
            }
        }

        return nodes;
    }

    public void addEvent(Event bigE) {

        this.allEvents.add(bigE);
    }

    public void checkNeighbours(Node n) {
        for (int x = n.getPosition().getX() - 1; x <= n.getPosition().getX() + 1; x++) {
            for (int y = n.getPosition().getY() - 1; y <= n.getPosition().getY() + 1; y++) {
                if ((x != n.getPosition().getX()) && (y != n.getPosition().getY())) {
                    if (this.withinNetwork(x, y)) {
                        this.board[n.getPosition().getX()][n.getPosition().getY()].addNeighbour(this.board[x][y]);
                    }
                }
            }
        }
    }

    public boolean withinNetwork(int x, int y) {
        if (((x >= 0) && (x < 50)) && ((y >= 0) && (y < 50))) {
            return true;
        }
        return false;
    }

    public ArrayList<Event> getEvents() {
        return this.allEvents;
    }

    public int getTime() {
        return this.time;
    }


    public void makeEvent() {

    }
}
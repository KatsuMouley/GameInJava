package players;

import java.awt.Color;
import java.util.ArrayList;

public class DrawPlayer {
    ArrayList<Player> players = new ArrayList<Player>();

    public DrawPlayer() {
        players.add(new Player(Color.blue));
        players.add(new Player(Color.orange));
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}

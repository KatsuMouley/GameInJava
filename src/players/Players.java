package players;

import java.awt.Color;
import java.util.ArrayList;

public class Players {
    ArrayList<Player> players = new ArrayList<Player>();

    public void setPlayers() {
        players.add(new Player(Color.red));
        players.add(new Player(Color.YELLOW));
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}

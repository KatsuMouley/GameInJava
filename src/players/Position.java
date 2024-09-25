package players;

class Position {
    int x, y;
    int lifetime = 120; // Number of frames this position will last

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void reduceLifetime() {
        lifetime--;
    }

    public boolean isExpired() {
        return lifetime <= 0;
    }
}
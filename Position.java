public class Position {

    private double x;
    private double y;

    /**default constructor sets x and y to 0*/
    public Position() {
        x = 0;
        y = 0;
    }

    /**overloaded constructor for specific x and y values
     * @param x x coordinate of the new position
     * @param y y coordinate of the new position
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**get the position's x-coordinate
     * @return  x-coordinate
     */
    public double getX() {
        return x;
    }

    /**get the position's y-coordinate
     * @return y-coordinate
     */
    public double getY() {
        return y;
    }

    /**change the position's coordinates absolutely
     * @param x new x-coordinate for this position
     * @param y new y-coordinate for this position
     */
    public void tp(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**change the position's coordinates relatively
     * @param x add x to position's current x-coordinate
     * @param y add y to position's current y-coordinate
     */
    public void move(double x, double y) {
        this.x += x;
        this.y += y;
    }

    /**check if positions are equal
     * @param o another position to check against
     * @return  true if coordinates of both positions are the same, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position pos)) { return false; }
        return (pos.x == this.x && pos.y == this.y);
    }
}

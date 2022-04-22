package lib;

/**
 * @author Jeroen Avonts
 * 
 * Date: Nov 13, 2004 Package: lib Project: AI TODO
 */
public class Pos {
    /**
     * <code>x</code> coordinaat x
     */
    private int x;

    /**
     * <code>y</code> coordinaat y
     */
    private int y;

    /**
     * @param x
     *            position x
     * @param y
     *            position y Construct a Position at (x,y)
     */
    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param p
     *            Position Copy construct a position
     */
    public Pos(Pos p) {
        x = p.x;
        y = p.y;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o) {
        Pos tmp = (Pos) o;
        return tmp.x == x && tmp.y == y;

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return 0;
    }

    /**
     * @param p
     *            Position vector Add p to a Position
     */
    public void add(Pos p) {
        x += p.x;
        y += p.y;
    }

    /**
     * @return x value of the position
     */
    public int getX() {
        return x;
    }

    /**
     * @return y value of the position
     */
    public int getY() {
        return y;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "x=" + x + " y=" + y;
    }
}


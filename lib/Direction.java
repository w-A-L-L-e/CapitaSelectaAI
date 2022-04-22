package lib;

/**
 * @author Jeroen Avonts
 * 
 * Date: Nov 13, 2004 Package: lib Project: AI TODO
 */
public class Direction {

    /**
     * Direction <code>north</code>
     */
    public final static int north = 0;

    /**
     * Direction <code>east</code>
     */
    public final static int east = 1;

    /**
     * Direction <code>south</code>
     */
    public final static int south = 2;

    /**
     * Direction <code>west</code>
     */
    public final static int west = 3;

    /**
     * <code>type</code> het type van richting
     */
    private int type;

    /**
     * Constructor Standard direction is north
     */
    public Direction() {
        type = north;
    }

    /**
     * @param type
     *            Direction Construct direction to type
     */
    public Direction(int type) {
        this.type = type % 4;
    }

    /**
     * @param dir
     *            Direction Copy construct direction from dir
     */
    public Direction(Direction dir) {
        type = dir.type;
    }

    /**
     * @param rotations
     *            number of rotations Turn the number of rotations Positive is
     *            right Negative is left
     */
    public void add(int rotations) {
        set(type + rotations);
    }

    /**
     * @param type
     *            Direction Set direction to type
     */
    public void set(int type) {
        while (type < 0)
            type += 4;
        this.type = type % 4;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o) {
        Direction tmp = (Direction) o;
        return type == tmp.type;
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
     * @return Position vector Creates position vector from the direction
     */
    public Pos drive() {
        int x = 0, y = 0;
        if (type % 2 == 0) {
            y = (type == north ? -1 : 1);
        } else {
            x = (type == east ? 1 : -1);
        }
        return new Pos(x, y);
    }

    /**
     * @return type Get the type of direction
     */
    public int getType() {
        return type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        switch (type) {
        case north:
            return "north";
        case east:
            return "east";
        case south:
            return "south";
        case west:
            return "west";
        default:
            return "heaven";
        }
    }
}
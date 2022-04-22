package lib;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Jeroen Avonts
 * 
 * Date: Nov 13, 2004 Package: lib Project: AI TODO
 */
public class Robot {
    /**
     * <code>distance</code> afgelegde afstand
     */
    private double distance = 0;

    /**
     * <code>turns</code> aantal draaiingen
     */
    private double turns = 0;

    /**
     * <code>intersections</code> aantal kruispunten bekeken met de getlight
     * functie
     */
    private double intersections = 0;

    /**
     * <code>lastAction</code> de laatste actie uitgevoerd
     */
    private String lastAction = "";

    /**
     * <code>maze</code> het doolhof waarin de robot zich bevindt
     */
    private Maze maze;

    /**
     * <code>pos</code> huidige positie van de robot
     */
    private Pos pos;

    /**
     * <code>dir</code> de richting waarin de robot zich bevindt
     */
    private Direction dir = new Direction();

    /**
     * @param m
     *            Construct a robot in maze m at cStart
     */
    public Robot(Maze m) {
        maze = m;
        pos = new Pos(m.getFStart());
        if (pos.getX() == 0)
            dir.set(Direction.east);
        else if (pos.getX() == maze.getCols() - 1)
            dir.set(Direction.west);
        else if (pos.getY() == 0)
            dir.set(Direction.south);
    }

    /**
     * @param b
     *            Robot Copyconstruct a robot from b
     */
    public Robot(Robot b) {
        maze = b.maze;
        pos = new Pos(b.pos);
        dir = new Direction(b.dir);
        distance = b.distance;
        turns = b.turns;
        intersections = b.intersections;
    }

    /**
     * @param p
     *            Position
     * @param d
     *            Direction Set robot on p in d Do not abuse, for offline search
     *            only!
     */
    public void setPos(Pos p, Direction d) {
        pos = new Pos(p);
        dir = new Direction(d);
    }

    /**
     * @return Lightvalue in front of robot
     */
    public int getLight() {
        Pos tmp = dir.drive();
        tmp.add(pos);
        int cell = maze.get(tmp.getX(), tmp.getY());
        if (cell == Maze.cTurn)
            intersections++;
        return cell;
    }

    /**
     * Turn left
     */
    public void left() {
        dir.add(-1);
        turns++;
        lastAction = "LEFT";
    }

    /**
     * Turn right
     */
    public void right() {
        dir.add(1);
        turns++;
        lastAction = "RIGHT";
    }

    /**
     * Step forward
     */
    public void forward() {
        pos.add(dir.drive());
        distance++;
        lastAction = "FORWARD";
    }

    /**
     * @return direction of the robot
     */
    public Direction getDir() {
        return dir;
    }

    /**
     * @return position of the robot
     */
    public Pos getPosition() {
        return pos;
    }

    /**
     * @return Maze where the robot moves in
     */
    public Maze getMaze() {
        return maze;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "robot at " + pos + " in direction " + dir.toString()
                + "\n distance =" + distance + "; turns = " + turns
                + "; intersections = " + intersections + "\n";
    }

    /**
     * @return Last action performed
     */
    public String getLastAction() {
        return lastAction;
    }

    /**
     * @return Total distance (number of forward steps)
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @param in
     *            read commands from
     * @throws IOException
     *             Read commands from input and execute
     */
    public void readIn(BufferedReader in) throws IOException {
        int line = 0;
        String cmd = "";
        while ((cmd = in.readLine()) != null) {
            cmd = cmd.toLowerCase();
            line++;
            if (cmd.equals("forward"))
                forward();
            else if (cmd.equals("left"))
                left();
            else if (cmd.equals("right"))
                right();
            else
                System.err.println("invalid command at line " + line);
        }
    }
}


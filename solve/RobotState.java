package solve;

import java.util.LinkedList;

import lib.Direction;
import lib.Pos;

/**
 * @author Jeroen Avonts
 * 
 * Date: Nov 17, 2004 Package: solve Project: AI TODO
 *
 * Bugfixed in 2020 by Walter Schreppers
 */
public class RobotState {

    /**
     * <code>dir</code> Richting waarin de robot in deze staat is gekomen
     */
    protected Direction dir;

    /**
     * <code>back</code> Richting om uit deze staat te gaan
     */
    protected Direction back;

    /**
     * <code>pos</code> Positie van de staat
     */
    protected Pos pos;

    /**
     * <code>actions</code> Lijst van mogelijke acties in de staat
     */
    protected LinkedList<Direction> actions;

    /**
     * @param ppos
     *            Position
     * @param pdir
     *            Direction Construct a state with ppos and pdir A position and
     *            a direction defines a state
     */
    public RobotState(Pos ppos, Direction pdir) {
        dir = new Direction(pdir);
        pos = new Pos(ppos);
        back = new Direction(pdir);
        back.add(2);
        actions = new LinkedList<Direction>();
        Direction d = new Direction(pdir);
        d.add(1);
        actions.add(new Direction(d));
        d.add(-1);
        actions.add(new Direction(d));
        d.add(-1);
        actions.add(d);
    }

    /**
     * @param p
     *            BaseState Copyconstruct a state
     */
    public RobotState(RobotState p) {
        dir = new Direction(p.dir);
        pos = new Pos(p.pos);
        back = new Direction(p.back);
        // actions = (LinkedList<Direction>) p.actions.clone();
        actions = new LinkedList<Direction>(p.actions);
    }

    /**
     * @return Direction to exit the state
     */
    public Direction getBack() {
        return back;
    }

    /**
     * @return Direction at entering the state
     */
    public Direction getDir() {
        return dir;
    }

    /**
     * @return State position
     */
    public Pos getPosition() {
        return pos;
    }

    /**
     * @return Current available actions in this state
     */
    public LinkedList getActions() {
        return actions;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o) {
        RobotState tmp = (RobotState) o;
        return pos.equals(tmp.pos);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return 0;
    }

}

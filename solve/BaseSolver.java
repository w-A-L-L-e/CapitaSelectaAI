package solve;

import gui.GuiMazeInterface;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Stack;

import lib.Direction;
import lib.Maze;
import lib.Pos;
import lib.Robot;

import solve.RobotState;


/**
 * @author Jeroen Avonts
 * 
 * Date: Nov 13, 2004 Package: solve Project: AI TODO
 */
public class BaseSolver extends Thread implements SolverInterface {

    /**
     * <code>fast</code> Geeft aan of er een delay op de acties moet zijn
     */
    protected boolean fast = true;

    /**
     * <code>found</code> Geeft aan of de end staat is gevonden
     */
    protected boolean found = false;

    /**
     * <code>gui</code> De gui voor de nodige callbacks
     */
    protected GuiMazeInterface gui;

    /**
     * <code>rob</code> robot dat we besturen
     */
    protected Robot rob;

    /**
     * Comment for <code>out</code>
     */
    protected PrintStream out;

    /**
     * @param fast
     *            true voor geen delay
     */
    public BaseSolver(boolean fast) {
        this.fast = fast;
    }

    /**
     * @param out
     *            Stream to print to Prints the output commando's to out
     */
    public void setOut(PrintStream out) {
        this.out = out;
    }

    /**
     * @param p
     *            de positie naartoe we willen rij door tot aan positie p
     */
    protected void driveForwardTo(Pos p) {
        while (!rob.getPosition().equals(p)) {
            goForward();
        }
    }

    /**
     * @return getlight na het einde het rechtdoor lopen loop rechtdoor tot er
     *         geen weg meer is
     */
    protected int forward() {
        int light;
        while ((light = rob.getLight()) == Maze.cRoad) {
            goForward();
        }
        if (light == Maze.cEnd) {
            found = true;
        }
        return light;
    }

    /**
     * doe ??n stap rechtdoor
     */
    protected void goForward() {
        rob.forward();
        if (out != null)
            out.println("forward");
        if(gui!=null) gui.update();
        if (!fast)
            sleepSec(1);
    }

    /**
     * draai naar links
     */
    protected void goLeft() {
        rob.left();
        if (out != null)
            out.println("left");
        if(gui!=null) gui.update();
        if (!fast)
            sleepSec(1);
    }

    /**
     * draai naar rechts
     */
    protected void goRight() {
        rob.right();
        if (out != null)
            out.println("right");
        if(gui!=null) gui.update();
        if (!fast)
            sleepSec(1);
    }

    /**
     * @param seconds
     *            slaap aantal seconden
     */
    public void sleepSec(int seconds) {
        if (!fast) {
            try {
                sleep(seconds * 250);
            } catch (Exception e) {

            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see solve.SolverInterface#solveMaze(lib.Robot, gui.GuiMazeInterface)
     */
    public void solveMaze(Robot rob, GuiMazeInterface callback) {
    }

    /**
     * @param d
     *            nieuwe richting draai van de huidige richting naar richting d
     *            in zo weinig mogelijk draaiingen
     */
    protected void turnShortTo(Direction d) {
        Direction robdir = rob.getDir();
        if (d.equals(robdir))
            return;
        Direction tmp = new Direction(d);

        tmp.add(1);
        if (robdir.equals(tmp)) {
            goLeft();
        }
        tmp.add(1);
        if (robdir.equals(tmp)) {
            goLeft();
            goLeft();
        }
        tmp.add(1);
        if (robdir.equals(tmp)) {
            goRight();
        }
    }

    /**
     * @return afstand tot het einde van huidige positie
     */
    protected int distanceToEnd() {
        Pos x = rob.getPosition();
        Pos y = rob.getMaze().getFEnd();
        return distance(x, y);
    }

    /**
     * @param p
     *            positie p
     * @return afstand van positie p tot het einde
     */
    protected int distanceToEnd(Pos p) {
        return distance(p, rob.getMaze().getFEnd());
    }

    /**
     * @param points
     *            een path met punten
     * @return de afstand langs al de punten van points
     */
    protected int distanceTraveled(Stack points) {
        int distance = 0;
        Iterator it = points.iterator();
        Pos old = (Pos) it.next();
        while (it.hasNext()) {
            Pos pos = (Pos) it.next();
            distance += distance(old, pos);
            old = pos;
        }
        return distance;
    }

    /**
     * @param a
     *            positie a
     * @param b
     *            positie b
     * @return De afstand tussen a en b
     */
    protected int distance(Pos a, Pos b) {
        return Math.abs(b.getX() - a.getX()) + Math.abs(b.getY() - a.getY());
    }

    /**
     * @param oldState
     *            Draai 180? en rij terug naar de vorige positie
     */
    protected void returnTo(RobotState oldState) {
        goLeft();
        goLeft();
        driveForwardTo(oldState.getPosition());
    }

    /**
     * @return Robot The robot that is used in this solver
     */
    public Robot getRob() {
        return rob;
    }

    /*
     * (non-Javadoc)
     * 
     * @see solve.SolverInterface#fileExtension()
     */
    public String fileExtension() {
        return null;
    }
}

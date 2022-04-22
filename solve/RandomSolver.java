package solve;

import gui.GuiMazeInterface;

import java.util.Random;

import lib.Maze;
import lib.Robot;

/**
 * @author Jeroen Avonts, adapted by Walter Schreppers
 * 
 * Date: Nov 13, 2004 Package: solve Project: AI TODO
 */
public class RandomSolver extends BaseSolver {

    /**
     * @param fast
     *            False defines delay on moves
     */
    public RandomSolver(boolean fast) {
        super(fast);
    }

    public String fileExtension(){
      return new String(".solution");
    }
    /**
     * @param searchRobot
     *            Current robot
     * @param callback
     *            Gui Callback
     */
    public void solveMaze(Robot searchRobot, GuiMazeInterface callback) {
        rob=searchRobot;
        gui=callback;
        Random rnd = new Random();
        while (searchRobot.getLight() != Maze.cEnd) {

            if (searchRobot.getLight() == Maze.cTurn) {

                goForward();

                if (rnd.nextInt(10) < 4) {
                    goLeft();

                } else if (rnd.nextInt(10) < 7) {
                    goRight();
                }
                //else go straight
            }

            while (searchRobot.getLight() == Maze.cFloor) {
                if (rnd.nextInt(10) > 4) {
                    goLeft();
                } else {
                    goRight();
                }
            }
            goForward();
        }
        goForward();

    }
}
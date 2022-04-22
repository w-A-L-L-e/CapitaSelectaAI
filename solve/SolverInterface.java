package solve;

import gui.GuiMazeInterface;
import lib.Robot;

/**
 * @author Jeroen Avonts
 * 
 * Date: Nov 13, 2004 Package: solve Project: AI TODO
 */
public interface SolverInterface {
    /**
     * @param rob
     *            Current Robot
     * @param callback
     *            Gui callback
     */
    public void solveMaze(Robot rob, GuiMazeInterface callback);

    /**
     * @return Extension for generated files
     */
    public String fileExtension();

}
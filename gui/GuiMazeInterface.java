package gui;

import lib.Robot;

/**
 * @author Jeroen Avonts
 * 
 * Date: Nov 13, 2004 Package: gui Project: AI TODO
 */
public interface GuiMazeInterface {
    /**
     * Update the interface
     */
    public void update();

    /**
     * @param rob
     *            Robot Set the guirobot to rob
     */
    public void setRobot(Robot rob);
}
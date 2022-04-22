# Capita Selecta AI
![Demo maze](images/demo_maze.png?raw=true "Demo maze in gui mode")

# Robot Maze Solver
Implement an offline and online search algo where a Mindstorms robot can find the most efficient
path between start and ending of a maze.

Here we simulate the mindstorms with a java gui. But afterwards the code can be used to
operate on a real lego rover.

There are 4 examples in the mazes directory. The dimensions of each maze are given
on the first two lines (rows then columns). 

Meaning of different fields in maze files:
```
_ : Floor/walls
# : Crossing
* : Path robot needs to follow (for the real mindstorms this is a line on the floor)
S : Starting point
E : End point to reach.
```


# Compiling

```
$ ./compile.sh
```


# Running

For running in text mode (fast):

```
$ ./run.sh 

*** Maze: mazes/maze1.data***
Search Robot :
robot at x=7 y=3 in direction north
 distance =85.0; turns = 83.0; intersections = 65.0


Offline bot : 
robot at x=7 y=3 in direction north
 distance =85.0; turns = 83.0; intersections = 0.0


*** Maze: mazes/maze2.data***
Search Robot :
robot at x=0 y=2 in direction north
 distance =182.0; turns = 63.0; intersections = 75.0


Offline bot : 
robot at x=0 y=2 in direction north
 distance =182.0; turns = 63.0; intersections = 0.0


*** Maze: mazes/maze3.data***
Search Robot :
robot at x=6 y=4 in direction north
 distance =26.0; turns = 17.0; intersections = 15.0


Offline bot : 
robot at x=6 y=4 in direction north
 distance =26.0; turns = 17.0; intersections = 0.0


*** Maze: mazes/maze4.data***
Search Robot :
robot at x=9 y=19 in direction south
 distance =85.0; turns = 40.0; intersections = 41.0


Offline bot : 
robot at x=9 y=19 in direction south
 distance =85.0; turns = 40.0; intersections = 0.0

```

Running with a gui that shows the rover driving execute:

```
$ ./rungui.sh
```

The original assignment is given in dutch in opgave.txt in this repo.
I just recently had some spare time and updated the code to work and 
compile without errors or warnings on openjdk 11+

The first version was written by Jeroen Avonts and was based on my C++ version for lego mindstorms.
I just refactored + fixed some bugs in this java version so it works on modern java compilers.

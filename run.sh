for file in `ls mazes/*.data`; do
	java solve.Main $1 $file;
done;

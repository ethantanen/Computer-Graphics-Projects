
This program renders a series of 3D fractals on a JFrame with the option to rotate the fractal and change
the depth of the fractal. The main method for the program is in the class titled Controller. The program 
can render a series of Sierpinski polyhedrons and the Menger sponge. The algorithms work similarly in that
for each recursive call the program determines the new polyhedron by first calculating the center of the new
polyhedron and from that calculating the polyhedrons vertices. The size of the next smallest polyhedron is
determined by a factor lambda which is a function of the number of new polyhedron formed each recursive call
and the golden ratio. (Hausdorff Dimension)	}

---------HOW CODE WORKS---------

My program reads data from the input file and intializes the Graph object with nodes (intersections) and edges (roads). 
If user writes "--show", the program draws the map using Java Graphics by visiting all nodes and their unvisited roads. 
Dijkstra algorithm is implemented to find the shortest path between two locations if user writes "--directions".

---------LIST OF JAVA FILES---------

My program consists of 5 java files:
1. Node.java
Represent intersections.
Each Node contains an ArrayList of edges (distances to its neighbors).

2. Edge.java
Represent roads.

3. Graph.java
Contains a HashMap of String - Node to access Node with String.

4. Draw.java
Contains drawing methods with automatic scaling.

5. StreetMap.java
Contains Dijkstra algorithm, using Priority Queue.

---------EXPECTED RUNTIME---------

- Initialize graph: O(|V| + |E|)
- Draw map: O(|V| + |E|)
- Find the shortest path: The Dijkstra algorithm runtime is O(|E|log|E| + |V|log|V|). 
However, in the program, I use HashMap to find Node and index, so the runtime is O(|E|*(log|E|)^2 + |V|*(log|V|)^2),
as the hash code takes O(log N) on average.





Loc Bui
URID: 31320986
email: lbui3@u.rochester.edu
Class CSC 172 and Lab 52330.

zip contains 7 files: 
	+ Graph.java
	+ GUI.java
	+ Heap.java
	+ Intersection.java
	+ README.txt
	+ Road.java
	+ StreetMap.java

Intersection class contains: 
	+ the id of the intersection
	+ latitude and longitude
	+ list of roads that start from that intersection

Road class cotains:
	+ the id of the road
	+ the other end of the road
	+ its weight (length)

Heap class: 
	+ Hashing the intersectionId to number from 1 to (number of intersection)
	+ heap array contains integer id of intersection after hashing
	+ value array: value[i] is shortest distance from source to the intersection that has hash ID euqals i
	+ positionOnHeap: the position of hash ID on heap (using for removeTop or addElement)
	+ functions work like their names

Read data from file and construct a list of Intersection objects in class Graph.
* Dijkstra part:
	+ Work on the Intersection list and Heap class.
	+ Using another array trace, trace[intersectionId] = roadID, storing the last roadID connect source and intersection
	+ roadIdChosen store the list of roadId on the shortest path from start to end
-> Dijkstra is O(nlog) but I need to find hashId from the intersectionId in each loop using hashMap and function get(key) is O(log). So my code is roughly O(N*log*log)
* GUI part (displayign the map:
	+ Using the Intersection list to draw map
	+ Using roadIdChosen to know which road is on the shortest path and marked with red color
-> I need to access roadIdChosen to know if a road should be marked in each loop and function contains is O(log). So my code is O(Nlog)

=> Totally it will be about O(N*log*log)
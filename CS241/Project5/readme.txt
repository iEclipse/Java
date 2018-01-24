Description
==========================================
In this assignment you will practice the concepts learned in class involving graphs and hash maps. You are to build a simplified version of a GPS navigation system. You will represent the map with a graph data structures, and use the algorithms learned in class to provide lookup and point-to-point navigation. The map consists of the following 12 locations in the city of Los Santos:

Los Santos Public Library (keywords: Library, Recreation, Landmark).
Los Santos Saints' Stadium (keywords: Recreation, Landmark, Sports). 3 mi east of Los Santos Public Library, on Main Ave. with 2nd St.
Vinewood Boulevard (keywords: Recreation, Landmark). 2 mi east of Los Santos Saints' Stadium, on Main Ave. with 3rd St.
Los Santos Forum (keywords: Recreation, Landmark, Sports). 5 mi east of Vinewood Bvd., on Main Ave. with 4th St.
Los Santos City Hall (keywords: Landmark). 5 mi south of Los Santos Public Library, on 1st St. with Centennial Ave.
Centennial Theater (keywords: Recreation, Arts, Landmark). 1 mi south of Los Santos Saints' Stadium, 4 mi east of Los santos City Hall, on 2nd St. with Centennial Ave.
All Saints General Hospital (keywords: Hospital, Health&Care). 6 mi south of Vinewood Bvd., 7 mi east of Centennial Theater, on 3rd St. with Centennial Ave.
Richman Country Club (keywords: Recreation). 1 mi south of Los Santos Forum, 3 mi east of All Saints General Hospital, on 4th St. with Centennial Ave.
BurgerShot (keywords: Dining, Fast-food, Restaurant). 2 mi south of Centennial Theater, on 2nd St. with General Ave.
Los Santos Gym (keywords: Fitness, Health&Care). 1 mi south of All Saints General Hospital, 1 mi east of BurgerShot, on 3rd St. with General Ave.
Cluckin'Bell (keywords: Dining, Fast-food, Restaurant). 3 mi south of Richman Country Club, 1 mi east of Los Santos Gym, on 4th St. with General Ave.
Pimiento's (keywords: Dining, Restaurant) 3 mi south of Cluckin'Bell, 2 mi south-east of Los Santos Gym, on 4th St. with Food Alley.
You will represent this map as a graph, with the restriction that Avenues are two-way, but streets are one-way, alternating with going north, then south, starting with 1st St. going south. Once the graph has been implemented, your program should provide the following functionality:

Once the program loads, the user's initial location is Los Santos City Hall. The user is presented with two options: point-to-point navigation, and find destination.
In the point-to-point navigation, the user is allowed to enter any two locations by name, and the program provides directions from point A to point B. The directions correspond to the shortest path between the two points. For example, if the user entered City Hall and Vinewood Blvd, the program would display something like:
Drive 11 mi east on Centennial Ave.
Turn north on 3rd St.
Drive 6 mi north on 3rd St.
Arrive at Vinewood Blvd.
If the user enters find destination, then the user presented with the option of searching by name or by keyword. If the user searches by keyword, the keyword can be one of: Library, Recreation, Landmark, Sports, Arts, Hospital, Health&Care, Dining, Fast-food, Restaurant, and Fitness. When searching by keyword the user will be presented with a list of matching results, and have the option of selecting one. In both cases, once a location is found the user is presented with two options: get directions, and navigate. The get directions option will give a list of directions similar to the previous example. but the navigate will present instruction by instruction, followed by the prompt Next, upon which the next instruction is given until the destination is reached.
At any point during navigation, the user is presented with, in addition to getting the next instruction, the option to Reroute, upon which the program will compute an alternate route (the second best option from the current point), and present the new next set of instructions.
Finally, when searching locations by keyword the user will be given the option to find closest. For example, find nearest restaurant.
The navigation and directions system should be implemented using Dijkstra's Shortest Path Algorithm. As for the lookup search, it is recommended you use a Hash Map, and use the keywords as keys to store locations. Finally, for the find closest option, you should use Breadth-First Search.

Information
==========================================
Andrew Trang
CS241.02
Professor Rodriguez

Approach
==========================================
I programmed the navigation using the algorithm discussed in class. Most of the program used arraylists to hold the data, except for the use of a hashmap to store a few locations. It was fairly simple, but because most of the work was just in adding the extra features, it took quite some time to complete. Other than having to use graphs, there was nothing I haven't seen or tried to use before.

Conclusion
==========================================
I think Dijkstra's algorithm is pretty neat which made programming this last project a bit of fun. It was interesting to see how you could use it to trace the path from end to start.
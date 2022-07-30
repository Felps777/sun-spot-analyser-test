# AIRBUS - Exercise for sun-spot-analyser-test
------------------------------------------------------------
##Challenge description 

**Airbus**  aerospace systems wishes to analyze areas of the Sun for thermal activity. Their sensors
provide raw data which can be visualized as a (rows X cols) GRID of raw heat measurements that represent the
Sun's surface, i.e.

In the raw data the range of measurements will always range from 0 (low heat) to 5 (high heat).  

This data needs to be analysed to find likely areas of increased solar activity. The analysis consists of
generating a Solar Activity Score for each location on the grid. The score is determined by adding the
location's own raw heat value to its surrounding raw heat values. For instance, the score of location
(1,1) is as follows:  

5 + 3 + 1 + 4 + 1 + 1 + 2 + 3 + 2 = 22   

When dealing with locations around the edge of the grid the score should ignore values outside the
grid. For instance the score of location (0,0) is as follows:   

5 + 3 + 4 + 1 = 13


*   first_item
*   second_item
###Input 

As the Sun Spot Analyser is expected to be used by several business units within Airbus an API REST
must be implemented. The solution should implement (at least) the following REST API endpoints:
● POST -> /sun-spot-analyser-api/grid
○ Json payload:
{ "size": 3, "values": "4, 2, 3, 2, 2, 1, 3, 2, 1"}
The POST endpoint must accept 2 parameters:
1. Size of the grid
2. Values of the grid (as a string separated by commas)
○ Expected OK response payload:
{ "id": 1 }
The endpoint must return and HTTP OK response that contains the internal ID of the
grid. This internal ID will be used to query the scores of this specific grid using the
following GET endpoint:
● GET-> /sun-spot-analyser-api/scores?id=1
Expected 200 OK response payload:
{“scores": [ {"x": 1, "y": 1, "score": 10}, { "x": 1, "y": 2, "score": 14}, { "x": 1, "y": 3,
"score":8} … ]}

## Application

This application aims to enable the all necessary elements to make this possible.

Its structure is composed by:

* **API REST**  - To receive input data related to the matrix to be analyzed and output the results calculated.
* **ENGINE**  - To process the received data, do the calculations and format the output.
* **DB**  - For the sake of simplicity, we preferred just keep a MemeroyDB using H2 Database Engine tool. 


-------------------	-----------------------------------------

> API-REST:     
This is the Interface of the applciation. Its has some endpoints to enable the comunication wioth the core of application. [SWAGGER-UI](http://localhost:8080/swagger-ui/index.html#/ "SWAGGER Interface") 


> ENGINE

   to receive a JSON input of a integer matrix and response a JSON document with an numerical analisis of subareas of the matrix.

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

As the Sun Spot Analyzer is expected to be used by several business units within Airbus an API REST
must be implemented. The solution should implement (at least) the following REST API endpoints:

* **POST**  -> /sun-spot-analyzer-api/grid
	* Json payload: `{ "size": 3, "values": "4, 2, 3, 2, 2, 1, 3, 2, 1"}`
		* The endpoint must accept 2 parameters:   

			1.  Size of the grid 
			2.  Values of the grid (as a string separated by commas)   

		* Expected status OK and response payload: `{ "id": 1 }`  

* **GET** -> /sun-spot-analyzer-api/scores?id=1

	* The endpoint must return and HTTP OK response that contains the internal ID of the grid. This internal ID will be used to query the scores of this specific grid using the following GET endpoint:

	* Expected Status 200 OK response and payload:   
	
		{“scores": [ {"x": 1, "y": 1, "score": 10}, { "x": 1, "y": 2, "score": 14}, { "x": 1, "y": 3, "score":8} … ]}



## Application

This application aims to enable an API-REST interface and a processor module to do calculations needed.

Its structure is composed by:

* **API REST**  - To receive input data related to the matrix to be analyzed and output the results calculated.
* **ENGINE**  - To process the received data, do the calculations and format the output.
* **DB**  - For the sake of simplicity, we preferred just keep a MemeroyDB using H2 Database Engine tool. 


-------------------	-----------------------------------------


1.  **API REST:**   
This is the Interface of the application. Its has some endpoints to enable the communication with the core of application. [SWAGGER-UI](http://localhost:8080/swagger-ui/index.html#/ "SWAGGER Interface"). 
	* this link only works when the App is UP and running on same machine of this file.   

2.  **ENGINE:**  
This is the core of application containing functions that process the matrix values, calculates follwing the rules defined below and return the total values to the API-REST requests.ç

3. **DB:**   
This is an virtual (inMemory Database). It will be available only while the App is running just to save input matrix data and support the requests for Spot calculations.
* In this version of App, we don not want to focus on saving all data or keep them indefinitely.
   

  ## Technologies
Project is created with:
* Spring-Boot starters (Web, Data-JPA, Test): 2.6.7
* Spring-Boot Data-JPA: 2.6.7
* Lombok: 1.18.24
* H2 : 1.4.200 (simple db library)
* Apache Commons-Lang-3:  3.12.0
	
## Setup  
##### (this info is based on Windows system) 

To run this project on traditional SO, you can:

- Import the zip/project into Eclipse (preferable STS) and run the app on Spring-boot dashboard launcher from IDE; 
or
- With system command line (for example windows cmd /powershell) you need go to the project root folder "sun-spot-analyzer-test" and run "mvn clean spring-boot:run" 

To run it on a Container system (for example Docker), you can:
- edit the Dockerfile contained into project folder to prepare **Working dir ** and **Jar file** name you desire, then just run **"launchInDocker.bat"** batch (this into windows cmd). This will repackage the project and generates an updated Jar file to be used to generates the new docker image.


**OBS**: Of course to run do all the steps on Unix you will need to change/adapt a few commands syntax related to the SO command line.
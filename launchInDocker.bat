# this creates the image to be used on docker
docker build -t sun-spot-analyzer-testApp/sun-spot-analyzer-test-1.0.0

# it launches the app on container apply mapping the port 9092
docker run -p 9092:8080 -t sun-spot-analyzer-testApp/sun-spot-analyzer-test-1.0.0
FROM maven:3.8-jdk-11
RUN mkdir /sun-spot-analyzer-testApp
COPY . /sun-spot-analyzer-testApp
WORKDIR /sun-spot-analyzer-testApp
RUN mvn clean package -DskipTests
ENTRYPOINT ["java","-jar","target/sun-spot-analyzer-test-1.0.0.jar"]

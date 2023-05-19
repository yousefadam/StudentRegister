# Use a Java base image
FROM openjdk:11

# Set the working directory
WORKDIR /usr/src/myapp/src

# Copy the Java application JAR file to the container
COPY . /usr/src/myapp

# Define the entry point and command to run the application
CMD ["java", "Main"]

# Mount the batch data folder
VOLUME /app/data/batch

RUN javac Main.java


# Build the Docker image:
# docker build -t your-image-name .

# Run the Docker container and mount the host folder to the container volume:
# docker run -it -v batch:/app/data/batch -v Main-DB.txt:/app/data/Main-DB.txt studentregister

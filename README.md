Option 1 (Docker):
  1. Make sure docker machine and docker-compose are installed on your machine.
  2. run "docker-compose up" from the project root directory.
  
Option 2 (Manual)
  1. Make sure NodeJS is installed.
  2. Make sure JAVA_HOME environment variable set properly.
  3. Go to api directory
    3.1. run "gradlew build"
    3.2. run "java -jar ./build/libs/carviewer-1.0.jar"
  4. Go to ui directory
    4.1 run "npm run build"
    4.2 run "serve -s build"

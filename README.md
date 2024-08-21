# temp-harsh-experiments
Repo for some of the experiments

## How to run the app
1. Run `docker-compose.yml` at the root of the folder and verify containers are running in docker
2. Run the maven build using Jenkins or by running `mvn clean install`
3. Run the Quarkus app using IntelliJ or through terminal
    - **Using IntelliJ**: Run -> Edit Configurations -> `+` -> Quarkus -> Select the application module
    - **Using terminal**: `mvn clean quarkus:dev`

## How to generate native image and run it
1. Run `docker-compose.yml` at the root of the folder and verify containers are running in docker
2. Run the maven build with `native` profile using IntelliJ or using `mvn clean install -P native` 
    - To Generate the native image, you need to have your java set to GraalVM.
3. Verify the native image `temp-harsh-experiments-1.0-SNAPSHOT-runner` is generated under `target` directory
4. Now to run the app you can do `./temp-harsh-experiments-1.0-SNAPSHOT-runner`. 
    - In this case though, since we need `XYz_aPi_KeY` as an env variable, we need to run `export XYz_aPi_KeY=some-key-here && ./temp-harsh-experiments-1.0-SNAPSHOT-runner`
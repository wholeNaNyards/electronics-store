#Electronics Store

This web app is a fake online store where users can view, rate, and purchase various electronics. I created it to help me learn and practice Spring and AngularJS. It is made with AngularJS, Bootstrap, Spring REST, Spring JDBC, and Gradle. With help from some gradle plugins, it runs on an embedded Jetty server and uses an embedded H2 SQL Database. If you don't want to download it, you can always view the code and screenshots (see link at bottom) here on GitHub.

*Running the App*

* Clone this repository
* `cd` to the top level
* Run the following command:
    * `gradlew.bat appRunWar` (Windows)
    * `./gradlew appRunWar` (Unix)
* Leave the server running in the background
* Go to `localhost:8080` in your favorite web browser
* Resize the window to check out the mobile views as well

*Unit Tests*

* `cd` to the top level
* Run the following command:
    * `gradlew.bat test` (Windows)
    * `./gradlew test` (Unix)
* Test results can be viewed at: `build/reports/tests/index.html`

*Additional Info*

* Database schema and data can be found [here](src/main/resources)
* You can change the port in the file `build.gradle`
* List of REST GET calls - Try going to the following URLs when the server is running
    * `localhost:8080/users/1`
    * `localhost:8080/users/1/products`
    * `localhost:8080/products`
    * `localhost:8080/categories`

*Links*

* [Screenshots](docs/screenshots/screenshots.md)
* [Bugs](docs/bugs.md)
* [Future Work](docs/future_work.md)
* [User Feedback](docs/user_feedback.md)

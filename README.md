# Filter System Application

This is a Spring Boot application for managing transactions. It uses an H2 in-memory database and Flyway for database migrations.

## Setup Instructions

### Prerequisites

- Java 17
- Gradle
- Git

### Running the Application

1. **Clone the Repository**

    ```sh
    git clone https://github.com/kadiliimand/filter-system.git
    cd filter-system
    ```

2. **Set Environment Variables**

   Ensure you have the necessary environment variables set up for the database credentials. You can set them directly in your terminal or use a tool like dotenv for local development.

   - **For Unix-based systems (Linux, macOS)**:

     ```sh
     export DB_USERNAME=yourusername
     export DB_PASSWORD=yourpassword
     ```

   - **For Windows**:

     ```cmd
     setx DB_USERNAME "yourusername"
     setx DB_PASSWORD "yourpassword"
     ```

3. **Build and Run the Application**

    ```sh
    ./gradlew bootRun
    ```

4. **Access the H2 Console**

   Open your web browser and go to [http://localhost:8080/h2-console](http://localhost:8080/h2-console). Use the following credentials:

   - **JDBC URL**: `jdbc:h2:mem:testdb`
   - **User Name**: `${DB_USERNAME}` (default: `sa`)
   - **Password**: `${DB_PASSWORD}` (default: `pass`)

### Front-End Setup

The front-end part of the application is located in the `web` directory. It uses Node.js and npm for managing dependencies and building the project.

1. **Install Node.js Dependencies**

    ```sh
    cd web
    npm install
    ```

2. **Run the Front-End Development Server**

   To start the front-end development server and work on the front-end independently of the back-end:

    ```sh
    npm start
    ```

   The development server will start at [http://localhost:3000](http://localhost:3000).


3. **Build the Front-End for Production**

   To build the front-end for production, which will generate the static files in the `web/build` directory:

    ```sh
    npm run build
    ```

4. **Serve the Application**

   The front-end will be served automatically as part of the Spring Boot application. When you run the `./gradlew bootRun` command, the static files from the `web/build` directory will be served.

## Project Structure

- `src/main/java`: Java source code
- `src/main/resources`: Configuration files
- `src/main/resources/db`: Flyway migration scripts
- `src/test`: Unit tests
- `web`: React front-end source code

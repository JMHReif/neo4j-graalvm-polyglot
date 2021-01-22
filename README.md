# neo4j-graalvm-polyglot
Neo4j extension to execute various languages with Cypher

Steps to run the examples:
1) Download Neo4j Community Server from neo4j.com
2) Follow download instructions to set up the database and start it
3) Open a file folder where you installed the database (we'll use this in a minute)
4) Clone this repository, open in your preferred IDE
5) Build the project, then run `mvn clean package`
6) In your IDE or project folder, copy the .jar file (neo4j-graalvm-polyglot-1.0-SNAPSHOT.jar) and paste it in the /plugins folder where Neo4j is installed.
7) If already started, restart Neo4j
8) Open a Neo4j Browser window (web browser and go to localhost:7474)
9) Run some test commands to see how it works!
  * Example 1: `CALL polyglot.run('js', '"hello"')`
  * Example 2: `CALL polyglot.run('js','print("hello")')`
  * Example 3: `CALL polyglot.run('python','print("Nice to see you!")')`
  * Languages accepted as 1st parameter: llvm, R, js, python, ruby

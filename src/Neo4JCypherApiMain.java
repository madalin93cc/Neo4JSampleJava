import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

/**
 * Neo4J Sample Cypher API
 */
public class Neo4JCypherApiMain {
    public static void main(String[] args) {
        GraphDatabaseFactory graphDatabaseFactory = new GraphDatabaseFactory();
        GraphDatabaseService graphDatabaseService = graphDatabaseFactory.newEmbeddedDatabase("D:\\Projects\\Neo4JSampleJava\\NEODB");
        ExecutionEngine executionEngine = new ExecutionEngine(graphDatabaseService);

        // execute any cypher query
        ExecutionResult executionResult = executionEngine.execute("MATCH (n) return n");
        System.out.println(executionResult.dumpToString());
    }
}

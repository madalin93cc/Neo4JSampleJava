import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.schema.IndexCreator;
import org.neo4j.graphdb.schema.Schema;
import org.neo4j.kernel.impl.coreapi.schema.IndexCreatorImpl;
import org.neo4j.kernel.impl.coreapi.schema.InternalSchemaActions;

/**
 * Neo4J Sample
 * Before using make sure you have in you classpath neo4j libs
 */
public class Neo4JNativeApiMain {
    public static void main(String[] args) {
        GraphDatabaseFactory graphDatabaseFactory = new GraphDatabaseFactory();
        GraphDatabaseService graphDatabaseService = graphDatabaseFactory.newEmbeddedDatabase("D:\\Projects\\Neo4JSampleJava\\NEODB");
        try (Transaction tx = graphDatabaseService.beginTx()){
//             db operations
//             native API example

//             create nodes
            Node user = graphDatabaseService.createNode(Labels.USER);
            user.setProperty("name", "Madalin");
            user.setProperty("born", 1993);
            user.addLabel(Labels.DEVELOPPER);

            Node user2 = graphDatabaseService.createNode(Labels.USER);
            user.setProperty("name", "Manager");

            Node dept = graphDatabaseService.createNode(Labels.DEPARTMENT);
            dept.setProperty("name", "Solutii");

//             create relationships
            Relationship relationship = user.createRelationshipTo(dept, Relationships.EMPLOYED);
            relationship.setProperty("function", "Java developper");

            Relationship relationship2 = user2.createRelationshipTo(dept, Relationships.EMPLOYED);
            relationship2.setProperty("function", "Senior software developper");

            Relationship relationship1 = user2.createRelationshipTo(user, Relationships.MANAGES);
//            in functie de tx.success sau tx.failure tranzactia curenta esta marcata ca successful sau failed
//            si se commit modificareile sau se face rollback

            System.out.println(user.getId());
            System.out.println(user.getDegree());
            System.out.println(user.getLabels());
            System.out.println(user.getRelationships());
            System.out.println(user.getRelationships(Direction.OUTGOING));

            tx.success();
//            tx.failure();
        }

        try (Transaction tx = graphDatabaseService.beginTx()){
            //             create index
            Schema schema = graphDatabaseService.schema();
            schema.indexFor(Labels.USER).on("name").create();
            schema.indexFor(Labels.DEPARTMENT).on("name").create();
            tx.success();
        }
        System.out.println("Done successfully");
    }
}

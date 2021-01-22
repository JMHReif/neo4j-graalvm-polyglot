package polyglotProcedures;

import org.graalvm.polyglot.Value;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.logging.Log;
import org.neo4j.procedure.Context;
import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.Procedure;

import java.io.IOException;
import java.util.stream.Stream;

import static polyglotProcedures.TypeConverter.convert;

public class PolyglotUtil {
    @Context
    public GraphDatabaseService db;
    @Context
    public Log log;

    @Procedure(value = "polyglot.run")
    @Description("polyglot.run(language, code) - Executes the code given. Throws things otherwise.")
    public Stream<Output> execute(@Name("language") String language, @Name("code") String code) throws IOException {
        try (var context = org.graalvm.polyglot.Context.newBuilder().allowAllAccess(true).build()) {
            var bindings = context.getPolyglotBindings();
            bindings.putMember("db", db);

            Value v = context.eval(language, code);
            log.info("Check value equals " + v);

            Object result = convert(v);

            // Map these to a generic output as a type hack around the uncertainty of what comes back
            // Neo4j procs require a stream of concrete types
            return Stream.of(new Output(result));
        } catch (Exception exc) {
            exc.printStackTrace();
            throw exc;
        }
    }

    public class Output {
        public Object result;
        public Output(Object thingy) {
            result = thingy;
        }
    }
}
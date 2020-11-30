import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {

            CharStream input = CharStreams.fromStream(System.in);
            GraphQLLexer lexer = new GraphQLLexer(input);

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            System.out.println(tokens.getText() );
            GraphQLParser parser = new GraphQLParser(tokens);
            ParseTree tree = parser.document();
            System.out.println(tree.toStringTree(parser));

            ParseTreeWalker walker = new ParseTreeWalker();
            MyListener listener = new MyListener();
            walker.walk(listener, tree);
            buildStatements(listener.queries);

            execute

    }

    public static ArrayList<String> statements = new ArrayList<>();

    public static ArrayList<String> buildStatements(ArrayList<Query> queries) {
        ArrayList<String> result = new ArrayList<>();

        for (Query query : queries) {
            String statement = buildStatement(query);
            query.statement = statement;
            buildStatements(query.fields);
            result.add(statement);
            statements.add(statement);
        }
        return result;
    }

    public static String buildStatement(Query query) {

            String attributes = "";
            for(Integer k = 0; k < query.attributes.size(); k++){
                attributes = attributes + query.attributes.get(k) + ", ";
            }
            attributes = attributes.substring(0, attributes.length() - 2);
            String statement = "SELECT " + attributes + " FROM " + query.table;

            if(query.conditions.size() > 0) {
                String conditions = "";

                for(Integer k = 0; k < query.conditions.size(); k++){
                    String key = query.conditions.get(k).keySet().toArray()[0].toString();
                    conditions = conditions + key + "=" + query.conditions.get(k).get(key) + " AND ";
                }
                conditions = conditions.substring(0, conditions.length() - 5);
                statement = statement + " WHERE " + conditions;
            }
        return statement;
    }


}


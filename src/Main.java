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
            ArrayList<String> statements = buildStatements(listener.queries);

            System.out.println(listener.queries);

    }

    public static ArrayList<String> buildStatements(ArrayList<Query> queries) {
        ArrayList<String> result = new ArrayList<>();
        for(Integer i = 0; i < queries.size(); i++) {
            String attributes = "";
            for(Integer k = 0; k < queries.get(i).attributes.size(); k++){
                attributes = attributes + queries.get(i).attributes.get(k) + ", ";
            }
            attributes = attributes.substring(0, attributes.length() - 2);
            String statement = "SELECT " + attributes + " FROM " + queries.get(i).table;

            if(queries.get(i).conditions.size() > 0) {
                String conditions = "";

                for(Integer k = 0; k < queries.get(i).conditions.size(); k++){
                    String key = queries.get(i).conditions.get(k).keySet().toArray()[0].toString();
                    conditions = conditions + key + "=" + queries.get(i).conditions.get(k).get(key) + " AND ";
                }
                conditions = conditions.substring(0, conditions.length() - 5);
                statement = statement + " WHERE " + conditions;
            }
            queries.get(i).statement = statement;
            result.add(statement);
        }
        return result;
    }


}


import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.PrintWriter;

public class Main {
    static PrintWriter out;
    public static void main(String[] args) {
        try {
            out = new PrintWriter((args.length==0)?"salida.txt" : args[0]);
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

        }
        catch(Exception e) {
            System.out.println("Error " + e ); }
        out.close();
    }

}


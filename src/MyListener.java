import javax.print.attribute.standard.PrinterMessageFromOperator;
import java.util.*;

public class MyListener extends GraphQLBaseListener{
    @Override
    public void exitDocument(GraphQLParser.DocumentContext ctx) {

        super.exitDocument(ctx);
    }

    /* Stack<Integer> pila = new Stack<>();
    Stack<Character> pOper = new Stack<>();
    Stack<String> idtemp = new Stack<>();
    String label;
    int valuetemp;



    @Override
    public void exitExpr(ExprParser.ExprContext ctx) {

        if(ctx.ID().size() != 0){
            Program.labels.put(ctx.ID(0).getText(), Program.progs.size());
        }
        System.out.println("EL RESULTADO DE LA EXPR ES:" + valuetemp);
    }

    @Override
    public void exitGoTo(ExprParser.GoToContext ctx) {
        if(ctx.ID().size() == 2){
            Program.labels.put(ctx.ID(0).getText(), Program.progs.size());
            label = ctx.ID(1).getText();
        }else{
            label = ctx.ID(0).getText();
        }
        Label l = new Label(label);
        GoTo gt = new GoTo(l);
        Program.progs.add(gt);
    }

    @Override
    public void exitIfTrue(ExprParser.IfTrueContext ctx) {
        if(ctx.ID().size() == 2){
            Program.labels.put(ctx.ID(0).getText(), Program.progs.size());
            label = ctx.ID(1).getText();
        }else{
            label = ctx.ID(0).getText();
        }
        Label l = new Label(label);
        ifTrue ift = new ifTrue(valuetemp, l);
        Program.progs.add(ift);
    }


    @Override
    public void exitIfFalse(ExprParser.IfFalseContext ctx) {
        if(ctx.ID().size() == 2){
            Program.labels.put(ctx.ID(0).getText(), Program.progs.size());
            label = ctx.ID(1).getText();
        }else{
            label = ctx.ID(0).getText();
        }
        Label l = new Label(label);
        ifFalse iff = new ifFalse(valuetemp, l);
        Program.progs.add(iff);
        //System.out.println("EXIT IFFALSE");
    }

    @Override
    public void exitPrint(ExprParser.PrintContext ctx) {
        String var;
        if(ctx.ID().size() == 2){
            Program.labels.put(ctx.ID(0).getText(), Program.progs.size());
            var = ctx.ID(1).getText();
        }else{
            var = ctx.ID(0).getText();
        }

        Print prnt = new Print(var);
        Program.progs.add(prnt);
    }


    @Override
    public void exitAssign(ExprParser.AssignContext ctx) {
        //System.out.println(ctx.ID());

        if(ctx.ID().size() != 0){
            Program.labels.put(ctx.ID(0).getText(), Program.progs.size());
            //System.out.println(Program.progs.size() + "   LINE:   " + Program.labels.get(ctx.ID(0).getText()));

        }

        System.out.println("ASSIGNAND " + valuetemp + " A " );

        Program.vari.put(idtemp.pop(),valuetemp);


        //System.out.println(valuetemp + "   IS EQUAL   " + Program.vari.get(idtemp));
        /*Assign s1 = new Assign(idtemp.pop(), valuetemp);
        Program.progs.add(s1);
    }

    @Override
    public void exitAcc(ExprParser.AccContext ctx) {

        if(ctx.getChildCount()==3){
            Array arr = new Array(ctx.ID().getText());
            Program.arrays.put(ctx.ID().getText(), arr);
        }
        //System.out.println("CTX" + ctx.getText());
        if(ctx.ID().getText() != null) {

            if(Program.vari.get(ctx.ID().getText() )!= null){
                pila.push(Program.vari.get(ctx.ID().getText()));
                System.out.println("VALOR EN MAPA" + Program.vari.get(ctx.ID().getText()));
            }else{
                idtemp.push(ctx.ID().getText());
            }
        }



    }



    @Override
    public void enterBool(ExprParser.BoolContext ctx) {
        if(ctx.getChildCount() == 3) {
            if (ctx.getChild(1).getText().equals("&&")) {
                //System.out.println("&&");
                pOper.add('&');
            } else if (ctx.getChild(1).getText().equals("==")) {
                //System.out.println("==");
                pOper.add('=');
            }else if (ctx.getChild(1).getText().equals("||")) {
                //System.out.println("||");
                pOper.add('|');
            }
        }
    }

    @Override
    public void exitBool(ExprParser.BoolContext ctx) {
        if(!pOper.empty()) {
            if (pOper.peek() == '&') {
                pOper.pop();
                int a = pila.pop();
                int b = pila.pop();
                if(b ==10004 && a == 10004){
                    System.out.println("10004");
                    pila.push(10004);
                }else{
                    System.out.println("0");
                    pila.push(0);
                }

            } else if (pOper.peek() == '|') {
                pOper.pop();
                int a = pila.pop();
                int b = pila.pop();
                if(b ==10004 || a == 10004){
                    System.out.println("10004");
                    pila.push(10004);
                }else{
                    System.out.println("0");
                    pila.push(0);
                }
            }else if (pOper.peek() == '=') {
                pOper.pop();
                int a = pila.pop();
                int b = pila.pop();
                if(b==a){
                    System.out.println("10004");
                    pila.push(10004);
                }else{
                    System.out.println("0");
                    pila.push(0);
                }
            }
        }
        System.out.println("Bool size: " + pila.size() + " poper: " + pOper.size());
        valuetemp = pila.pop();
        System.out.println(valuetemp);

    }

    @Override
    public void enterRel(ExprParser.RelContext ctx) {
        if(ctx.getChildCount() == 3) {
            if (ctx.getChild(1).getText().equals("<")) {
                System.out.println("<");
                pOper.add('<');
            }
        }
    }

    @Override
    public void exitRel(ExprParser.RelContext ctx) {
        if(!pOper.empty()) {
            if (pOper.peek() == '<') {
                int a = pila.pop();
                int b = pila.pop();

                pOper.pop();
                if(b<a ){
                    System.out.println("10004");
                    pila.push(10004);
                }else{
                    System.out.println("0");
                    pila.push(0);
                }
            }
        }
        System.out.println("Rel size: " + pila.size() + " poper: " + pOper.size());

    }

    @Override
    public void enterAdd(ExprParser.AddContext ctx) {

        if(ctx.getChildCount() == 3) {
            if (ctx.getChild(1).getText().equals("+")) {
                System.out.println("+");
                pOper.add('+');
            } else if (ctx.getChild(1).getText().equals("-")) {
                System.out.println("-");
                pOper.add('-');
            }
        }
    }

    @Override
    public void exitAdd(ExprParser.AddContext ctx) {
        if(!pOper.empty()) {
            if (pOper.peek() == '+') {
                int a = pila.pop();
                int b = pila.pop();
                System.out.println(a+b);
                pila.push(a + b);
                pOper.pop();
            } else if (pOper.peek() == '-') {

                int a = pila.pop();
                int b = pila.pop();
                System.out.println(b-a);
                pila.push(b-a);
                pOper.pop();
            }
        }
        System.out.println("Add size: " + pila.size() + " poper: " + pOper.size());

    }

    @Override
    public void enterTerm(ExprParser.TermContext ctx) {
        if(ctx.getChildCount() == 3) {
            if (ctx.getChild(1).getText().equals("*")) {
                System.out.println("*");
                pOper.add('*');
            } else if (ctx.getChild(1).getText().equals("/")) {
                System.out.println("/");
                pOper.add('/');
            }
        }
    }

    @Override
    public void exitTerm(ExprParser.TermContext ctx) {
        if(!pOper.empty()) {
            if (pOper.peek() == '*') {
                int a = pila.pop();
                int b = pila.pop();
                System.out.println(a*b);
                pila.push(a * b);
                pOper.pop();
            } else if (pOper.peek() == '/') {
                int a = pila.pop();
                int b = pila.pop();
                System.out.println(b/a);
                pila.push(b/a);
                pOper.pop();
            }
        }
        System.out.println("Term size: " + pila.size() + " poper: " + pOper.size());
    }

    @Override
    public void exitFactor(ExprParser.FactorContext ctx) {


        System.out.println(ctx.getText());


        if (ctx.getText() == null) {
            System.out.println("NOT" );
        }
        try {
            int d = Integer.parseInt(ctx.getText());
            pila.push(d);
        } catch (NumberFormatException nfe) {
            System.out.println("NOT" );
        }
        /*if(ctx.NUM().getText() != null ) {
            System.out.println("CTX" + Integer.parseInt(ctx.NUM().getText()));
            pila.push(Integer.parseInt(ctx.NUM().getText()));
            System.out.println(ctx.NUM().getText());
        }

        System.out.println("Factor size: " + pila.size() + " poper: " + pOper.size());
    }
*/
}

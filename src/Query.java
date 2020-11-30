import java.util.ArrayList;
import java.util.Map;

public class Query {
    public String table;
    public ArrayList<String> attributes;
    public ArrayList<Map<String, String>> conditions;
    public String alias;
    public String statement;
    public ArrayList<Query> fields;
    public Query parentQuery;
    public Query() {
        this.attributes = new ArrayList<>();
        this.conditions = new ArrayList<>();
    }

}

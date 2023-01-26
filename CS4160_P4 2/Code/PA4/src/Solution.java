import java.util.ArrayList;

public class Solution implements ArgumentElement {
    String id;
    String desc;
    String content;
    String xmlId;
    String type = "solution";
    boolean visited = false;
    ArrayList<AssertedRelationship> relations = new ArrayList<AssertedRelationship>();

    public Solution(String id, String desc, String content, String xmlId){
        this.id=id;
        this.desc=desc;
        this.content=content;
        this.xmlId=xmlId;
    }
    public void addDesc(String desc) {
        this.desc = desc;
    }
    @Override
    public String accept(Print_Visitor visitor) {
        // TODO Auto-generated method stub
        return visitor.print(this);
    }
    @Override
    public ArrayList<AssertedRelationship> getRelations() {
        // TODO Auto-generated method stub
        return this.relations;
    }
    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return id;
    }
    

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getXmlId() {
        return xmlId;
    }

    @Override
    public boolean getVisited() {
        return visited;
    }

    @Override
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    @Override
    public String changeDesc(String desc) {
        // TODO Auto-generated method stub
        
        return null;
    }
    
}

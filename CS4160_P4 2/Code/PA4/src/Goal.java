import java.util.ArrayList;
public class Goal implements ArgumentElement{
    String id;
    String desc;
    String content;
    String xmlId;
    String type = "goal";
    boolean visited = false;
    ArrayList<AssertedRelationship> relations = new ArrayList<AssertedRelationship>();
    // AssertedRelationship supportedBy[];
    
    public Goal(String id, String desc, String content, String xmlId){
        this.id=id;
        this.desc=desc;
        this.content=content;
        this.xmlId=xmlId;
        
        // this.supportedBy = new SupportedBy[0];
    }
    @Override
    public ArrayList<AssertedRelationship> getRelations() {
        // TODO Auto-generated method stub
        return this.relations;
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
}

import java.util.ArrayList;

public class Claim implements ArgumentElement {
    String id;
    String desc;
    String content;
    String xmlId;
    String type = "claim";
    boolean visited = false;
    public boolean assumed;
    public boolean toBeSupported;

    public Claim(String id, String desc, String content, String xmlId) {
        this.id = id;
        this.desc = desc;
        this.content = content;
        this.xmlId = xmlId;
    }
    
  
    @Override
    public String accept(Print_Visitor visitor) {
        return desc;
        // TODO Auto-generated method stub
        
    }


    @Override
    public ArrayList<AssertedRelationship> getRelations() {
        // TODO Auto-generated method stub
        return null;
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

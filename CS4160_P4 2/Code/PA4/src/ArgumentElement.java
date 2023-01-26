import java.util.ArrayList;

public interface ArgumentElement {
    public ArrayList<AssertedRelationship> getRelations();
    public String accept(Print_Visitor visitor);
    public String getId();
    public String getXmlId();
    public String getType();
    public String changeDesc(String desc);
    public boolean getVisited();
    public void setVisited(boolean visited);
    // public void delete_node(GSN_Visitor visitor);
    // public void delete_relation(GSN_Visitor visitor);
    // public void modify_relation(GSN_Visitor visitor);
    // public void modify_node_content(GSN_Visitor visitor);

}


import java.util.ArrayList;

public class SupportedBy implements AssertedRelationship {

    String id;
    ArgumentElement parent;
    ArgumentElement child;
    public SupportedBy(String id, ArgumentElement parent, ArgumentElement child) {
        this.id = id;
        this.parent = parent;
        this.child = child;
    }
    @Override
    public String accept(Print_Visitor visitor) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public ArrayList<AssertedRelationship> getRelations() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public ArgumentElement getChild() {
        // TODO Auto-generated method stub
        return child;
    }
    @Override
    public ArgumentElement getParent() {
        // TODO Auto-generated method stub
        return parent;
    }
    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return id;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getXmlId() {
        return id;
    }

    @Override
    public boolean getVisited() {
        return false;
    }

    @Override
    public void setVisited(boolean visited) {
        return;
    }
}

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GSN_Model{
    private ArrayList<ArgumentElement> nodes = new ArrayList<ArgumentElement>();
    private ArrayList<Inference> relations = new ArrayList<Inference>();
    private Goal root;
    public GSN_Model() {
        this.root = null;
    }
    public void addRoot(Goal g) {
        this.root = g;
        nodes.add(root);
    }
    public Goal getRoot() {
        return root;
    }
    public ArrayList<ArgumentElement> getNodes() {
        return  nodes;
    }
    public ArrayList<Inference> getRelations() {
        return relations;
    }
    public void removeRelation(Inference inference) {
        relations.remove(inference);
    }
    public void addRelation(Inference relationship) {
        relations.add(relationship);
    }
    public Inference getRelation(String id) {
        for(Inference relation : relations) {
            if(relation.id.equals(id)) {
                return relation;
            }
        }
        return null;
    }
    public ArgumentElement getNode(String id) {
        for(ArgumentElement node : nodes) {
            if(node.getXmlId().equals(id)) {
                return node;
            }
        }
        return null;
    }
    public Goal create_Goal(String node_id, String xml_id, String content, String desc) {
        // TODO Auto-generated method stub
        Goal g = new Goal(node_id, desc, content, xml_id);
        nodes.add(g);
        return g;
    }

  
    public Assumption create_Assumption(String node_id, String xml_id, String content, String desc) {
        // TODO Auto-generated method stub
        Assumption a = new Assumption(node_id, desc, content, xml_id);
        nodes.add(a);
        return a;
    }

    
    public Justification create_Justification(String node_id, String xml_id, String content, String desc) {
        // TODO Auto-generated method stub
        Justification j = new Justification(node_id, desc, content, xml_id);
        nodes.add(j);
        return j;
    }

    public Solution create_Solution(String node_id, String xml_id, String content, String desc) {
        // TODO Auto-generated method stub
        Solution s = new Solution(node_id, desc, content, xml_id);
        nodes.add(s);
        return s;
    }

    public Context create_Context(String node_id, String xml_id, String content, String desc) {
        // TODO Auto-generated method stub
        Context c = new Context(node_id, desc, content, xml_id);
        nodes.add(c);
        return c;
    }

    public Strategy create_Strategy(String node_id, String xml_id, String content, String desc, String[] describedInference) {
        // TODO Auto-generated method stub
        Strategy s = new Strategy(node_id, desc, content, xml_id,describedInference);
        nodes.add(s);
        return s;
    }

    
    public void create_InContextOf(String id, ArgumentElement child, ArgumentElement parent ) {
        // TODO Auto-generated method stub
        InContextOf contextRelation = new InContextOf(id, parent, child);
        if (parent.getType() == "goal") {
            Goal goal = (Goal) parent;
            for(AssertedRelationship relationship: goal.relations) {
                if (relationship.getChild().getId() == child.getId() && relationship.getParent().getId() == parent.getId()){
                    return;
                }
            }
            goal.relations.add(contextRelation);


        } else if (parent.getType() == "strategy") {
            Strategy strategy = (Strategy) parent;
            for(AssertedRelationship relationship: strategy.relations) {
                if (relationship.getChild().getId() == child.getId() && relationship.getParent().getId() == parent.getId()){
                    return;
                }
            }
            strategy.relations.add(contextRelation);

        }
    }

    public void create_SupportedBy(String id, ArgumentElement child, ArgumentElement parent) {
        // TODO Auto-generated method stub
        SupportedBy supportRelation = new SupportedBy(id, parent, child);
        if (parent.getType() == "goal") {
            Goal goal = (Goal) parent;
            for(AssertedRelationship relationship: goal.relations) {
                if (relationship.getChild().getId() == child.getId() && relationship.getParent().getId() == parent.getId()){
                    return;
                }
            }
            goal.relations.add(supportRelation);

        } else if (parent.getType() == "strategy") {
            Strategy strategy = (Strategy) parent;
            for(AssertedRelationship relationship: strategy.relations) {
                if (relationship.getChild().getId() == child.getId() && relationship.getParent().getId() == parent.getId()){
                    return;
                }
            }
            strategy.relations.add(supportRelation);
        }
        
    }

    public boolean delete_node(String node_id) {
		Queue<ArgumentElement> q = new LinkedList<>(); // Create a queue
		q.add(root); // Enqueue root
		while (!q.isEmpty()) {
			int n = q.size();

			// If this node has children
			while (n > 0) {
				// Dequeue an item from queue
				// and print it
				ArgumentElement p = q.peek();
				q.remove();
                for(int j=0; j < p.getRelations().size(); j++){
                    if(p.getRelations().get(j).getChild().getId()==node_id) {
                        p.getRelations().remove(j);
                    }
                }
				// Enqueue all children of
				// the dequeued item
				for (int i = 0; i < p.getRelations().size(); i++)
					q.add(p.getRelations().get(i).getChild());
				n--;
			}

			// Print new line between two levels
			System.out.println();
		}
        return false;
    }

    
    public boolean delete_relation() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean modify_node_content(String node_id) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean modify_relation() {
        // TODO Auto-generated method stub
        return false;
    }
    
}

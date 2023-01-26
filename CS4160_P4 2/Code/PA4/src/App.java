import java.io.*;
import java.util.*;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

public class App {
	static int printCount = 1;
	
	public static void main(String[] args) throws Exception {
		GSN_Model gsnModel = new GSN_Model();
		PrintVisitorClient printVisitor = new PrintVisitorClient();
		makeGsnFromFile(gsnModel,"XML file/F1Tenth_initial_AC.xmi");
		printVisitor.writeToFile(gsnModel.getRoot(), 0);
		App.printCount++;
		//TODO: ask user for input and loop while user give $
		System.out.println("Are safety requirements R1, R2 and R3 all satisfied?");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		if(input.equals("Yes")) {
				System.out.println("LOOP ONE WORKS");
				GSN_Model gsnModel2 = new GSN_Model();
				gsnModel2.getNode("Sn1.1.1.1.1.1.1.1");

				PrintVisitorClient printVisitor2 = new PrintVisitorClient();
				makeGsnFromFile(gsnModel2, "XML file/GSN_first_model.xmi" );
				ArgumentElement sheiNodeta = gsnModel2.getNode("Sn1.1.1.1.1.1.1.1");
				sheiNodeta.changeDesc("");

				printVisitor2.writeToFile(gsnModel2.getRoot(), 0);
				App.printCount++;
		}
		
		
		
		
		

		

	}
	

	public static void makeGsnFromFile(GSN_Model gsnModel, String fileName) {
		String inputFileName = fileName;
		File inputFile = new File(inputFileName);
		SAXReader xmlReader = new SAXReader();
		try {
			org.dom4j.Document currDoc = xmlReader.read(inputFile);
			for (Element child : currDoc.getRootElement().elements()) {
				List<Attribute> attributes = child.attributes();

				String type = attributes.get(0).getStringValue();
				String xml_id = attributes.get(1).getStringValue();
				String id = attributes.get(2).getStringValue();
				String desc = attributes.get(3).getStringValue();
				String content = attributes.get(4).getStringValue();

				if (gsnModel.getRoot() == null) {
					Goal root = gsnModel.create_Goal(id, xml_id, content, desc);
					gsnModel.addRoot(root);
				} else {
					switch (type) {
						case "ARM:Claim":
							gsnModel.create_Goal(id, xml_id, content, desc);
							break;
						case "ARM:InformationElement":
							if (id.contains("Sn")) {
								gsnModel.create_Solution(id, xml_id, content, desc);
							} else if (id.contains("C")) {
								gsnModel.create_Context(id, xml_id, content, desc);
							}
							break;
						case "ARM:ArgumentReasoning":
							String d = attributes.get(5).getStringValue();
							String[] describedInference = d.split(" ");
							gsnModel.create_Strategy(id, xml_id, content, desc, describedInference);
							break;
						case "ARM:AssertedInference":
							String si = attributes.get(5).getStringValue();
							String ti = attributes.get(6).getStringValue();
							Inference inf = new Inference(xml_id, si, ti);
							gsnModel.addRelation(inf);
							break;
						case "ARM:AssertedContext":
							String sc = attributes.get(5).getStringValue();
							String tc = attributes.get(6).getStringValue();
							ArgumentElement childContext = gsnModel.getNode(sc);
							ArgumentElement parentContext = gsnModel.getNode(tc);
							if (childContext != null && parentContext != null) {
								gsnModel.create_InContextOf(xml_id, childContext, parentContext);
							}
							break;
						case "ARM:AssertedEvidence":
							String se = attributes.get(5).getStringValue();
							String te = attributes.get(6).getStringValue();
							ArgumentElement childEvidence = gsnModel.getNode(se);
							ArgumentElement parentEvidence = gsnModel.getNode(te);
							if (childEvidence != null && parentEvidence != null) {
								gsnModel.create_SupportedBy(xml_id, childEvidence, parentEvidence);
							}
							break;
					}
				}
			}

			ArrayList<ArgumentElement> nodes = gsnModel.getNodes();
			for (ArgumentElement node : nodes) {
				if (node.getType().equals("strategy")) {
					Strategy s = (Strategy) node;
					for (String i : s.describedInference) {
						Inference inf = gsnModel.getRelation(i);
						String source = inf.source;
						String target = inf.target;
						ArgumentElement child = gsnModel.getNode(source);
						ArgumentElement parent = gsnModel.getNode(target);
						gsnModel.create_SupportedBy(inf.id, s, parent);
						gsnModel.create_SupportedBy(inf.id, child, s);
						gsnModel.removeRelation(inf);
					}
				}
			}

			for (Inference inf : gsnModel.getRelations()) {
				String source = inf.source;
				String target = inf.target;
				ArgumentElement child = gsnModel.getNode(source);
				ArgumentElement parent = gsnModel.getNode(target);
				gsnModel.create_SupportedBy(inf.id, child, parent);
			}

		} catch(DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


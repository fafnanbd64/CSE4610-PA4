import java.util.LinkedList;
import java.util.Queue;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class PrintVisitorClient {
	public void print(ArgumentElement g, int level) {
		Print_Visitor v = new PrintVisitorImplementation();
		if (g == null)
			return;

		g.setVisited(true);
		if (!g.getId().equals("G1")) {
			if (g.getType() == "context") {
				for(int i = 0; i< level; i++ ) {
					System.out.print("\t");
				}
				System.out.println("InContextOf");
			} else {
				for(int i = 0; i< level; i++ ) {
					System.out.print("\t");
				}
				System.out.println("SupportedBy");
			}
			for(int i = 0; i< level+1; i++ ) {
				System.out.print("\t");
			}
		} else {
			for(int i = 0; i< level; i++ ) {
				System.out.print("\t");
			}
		}
		System.out.println(g.accept(v));


		for(AssertedRelationship elem: g.getRelations()) {
			if (!elem.getChild().getVisited()) {
				print(elem.getChild(), level+1);
			}
		}

	}

	public void writeToFile(ArgumentElement g, int level) {
		try {
			File myObj = new File("GSN_F1Tenth_Race_Car_Evolution["+ App.printCount +"].txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		Print_Visitor v = new PrintVisitorImplementation();
		if (g == null)
			return;

		g.setVisited(true);

		try {
			FileWriter myWriter = new FileWriter("GSN_F1Tenth_Race_Car_Evolution["+ App.printCount +"].txt", true);
			if (!g.getId().equals("G1")) {
				if (g.getType() == "context") {
					for(int i = 0; i< level; i++ ) {
						myWriter.write("\t");
					}
					myWriter.write("InContextOf\n");
				} else {
					for(int i = 0; i< level; i++ ) {
						myWriter.write("\t");
					}
					myWriter.write("SupportedBy\n");
				}
				for(int i = 0; i< level+1; i++ ) {
					myWriter.write("\t");
				}
			} else {
				for(int i = 0; i< level; i++ ) {
					myWriter.write("\t");
				}
			}
			myWriter.write(g.accept(v)+"\n");
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		for(AssertedRelationship elem: g.getRelations()) {
			if (!elem.getChild().getVisited()) {
				writeToFile(elem.getChild(), level+1);
			}
		}

	}

    public void PrintGSN(Goal g) {
        Print_Visitor v = new PrintVisitorImplementation();
		if (g == null)
			return;

		// Standard level order traversal code
		// using queue
		Queue<ArgumentElement> q = new LinkedList<>(); // Create a queue
		q.add(g); // Enqueue root
		while (!q.isEmpty()) {
			int n = q.size();

			// If this node has children
			while (n > 0) {
				// Dequeue an item from queue
				// and print it
				ArgumentElement p = q.peek();
				q.remove();
				String s = p.accept(v);
				if (s != null) {
					System.out.print(s + " ");
				}

				// Enqueue all children of
				// the dequeued item
				for (int i = 0; i < p.getRelations().size(); i++)
					q.add(p.getRelations().get(i).getChild());
				n--;
			}

			// Print new line between two levels
			System.out.println("=========================================");
		}

	}
}

package q1.extract_method.refactored;

import java.util.List;

public class A {
	Node m1(List<Node> nodes, String p) {
		extractedMethod(nodes, p);
		return null;
	}

	Edge m2(List<Edge> edgeList, String p) {
		extractedMethod(edgeList, p);
		return null;
	}

	<T extends graph> void extractedMethod(List<T> list, String p) {
		for (T node : list) {
			if (node.contains(p))
				System.out.println(node);
		}
	}
}

class Node extends graph {
}

class Edge extends graph {
}

class graph {
	String name;

	public boolean contains(String p) {
		return name.contains(p);
	}
}
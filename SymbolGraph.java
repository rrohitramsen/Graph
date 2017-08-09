package com.program.graph.algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SymbolGraph {
	Graph1 g;
	public SymbolGraph(String fileName, String delim) throws FileNotFoundException {
		
		g = new Graph1(Graph1.TYPE.UNDIRECTED);
		
		/*FileInputStream fis = new FileInputStream(fileName);
		InputStreamReader in = new InputStreamReader(fis);*/
		Scanner in = new Scanner(new File(fileName));
		//BufferedReader reader = new BufferedReader(new FileReader(fileName));
		List<Graph1.Vertex> vertices = new ArrayList<Graph1.Vertex>();
		List<Graph1.Edge> edges = new ArrayList<Graph1.Edge>();
		int index;
		
		while (in.hasNextLine()) {
			
			String [] input = in.nextLine().split(delim);
			Graph1.Vertex fromVertex, toVertex;
			fromVertex = new Graph1.Vertex(input[0]);
			vertices.add(fromVertex);
			
			for (int i=1; i<input.length; i++) {
				toVertex = new Graph1.Vertex(input[i]);
				
				if (vertices.contains(toVertex)){
					index = vertices.indexOf(toVertex);
					toVertex = vertices.get(index);
				} else {
					vertices.add(toVertex);
				}
				
				Graph1.Edge e = new Graph1.Edge(0,fromVertex, toVertex);
				fromVertex.getEdges().add(e);
				edges.add(e);
				if (g.getType() == Graph1.TYPE.UNDIRECTED) {
					Graph1.Edge reciprocal = new Graph1.Edge(0, toVertex, fromVertex);
					toVertex.getEdges().add(reciprocal);
					edges.add(reciprocal);
				}

			}
		}
		g.setEdges(edges);
		g.setVertices(vertices);
		
	}

	public Graph1 getGraph(){
		return this.g;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		SymbolGraph sg = new SymbolGraph("movies.txt", "/");
		BFS bfs = new BFS(sg.getGraph().getVertices().size());
		bfs.bfs(sg.getGraph(), sg.getGraph().getVertices().get(0));
		bfs.printPath(sg.getGraph(), new Graph1.Vertex("Tin Men (1987)"), new Graph1.Vertex("Hershey, Barbara..."));
	}
}

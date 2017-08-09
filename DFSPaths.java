package com.program.graph.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFSPaths {
	private Graph.Vertex [] edgeTo;
	private boolean visited[];
	
	/*
	 * its a recursive approach no need of method getAdjUnvisitedVertex()
	 */
	public void dfs(Graph g, Graph.Vertex v){
		
		//visited[g.getIndexOf(v)] = true;
		v.visited = true;
		for (Graph.Edge e : v.getEdges()) {
			Graph.Vertex w = e.getTo();
			if (!w.visited) {
				edgeTo[g.getIndexOf(w)] = v; // edge path, it says how can w access from v, like edgTo[B] = A;, if we are starting from A,
				dfs(g, w);
			}
		}
		
	}
	
	
	/*
	 * Used stack coz want to print path in source to target order else
	 * we can print it in target to source order without using stack
	 */
	public void printPath(Graph g, Graph.Vertex source, Graph.Vertex target){
		Stack<Graph.Vertex> path = new Stack<Graph.Vertex>();
		
		for(Graph.Vertex v = target; v != source; v = edgeTo[g.getIndexOf(v)]){
			path.add(v);
		}
		path.add(source);
		//print paths
		while (!path.isEmpty()) {
			System.out.println(path.pop());
		}
	}
	
	
	public DFSPaths(int size) {
		edgeTo = new Graph.Vertex[size];
		visited = new boolean[size];
	}
	
	public static void main(String[] args) {
		List<Graph.Vertex> vertices = new ArrayList<Graph.Vertex>(); 
		Graph.Vertex A = new Graph.Vertex('A');
		/*
		 * If Inner class graph and edge were not static then 
		 * First we have to instantiate Graph and then vertex/edge
		 * eg :
		 * Grapg g1 = new Graph();
		 * Graph.vertex A = g1.new Vertex('A');
		 * 
		 */
		Graph.Vertex B = new Graph.Vertex('B');
		Graph.Vertex C = new Graph.Vertex('C');
		Graph.Vertex D = new Graph.Vertex('D');
		Graph.Vertex E = new Graph.Vertex('E');
		Graph.Vertex F = new Graph.Vertex('F');
		Graph.Vertex G = new Graph.Vertex('G');
		Graph.Vertex H = new Graph.Vertex('H');
		vertices.add(A);
		vertices.add(B);
		vertices.add(C);
		vertices.add(D);
		vertices.add(E);
		vertices.add(F);
		vertices.add(G);
		vertices.add(H);
		
		List<Graph.Edge> edges = new ArrayList<Graph.Edge>();
		edges.add(new Graph.Edge(0,A,B));
		edges.add(new Graph.Edge(0,B,C));
		edges.add(new Graph.Edge(0,B,H));
		edges.add(new Graph.Edge(0,C,D));
		edges.add(new Graph.Edge(0,C,E));
		edges.add(new Graph.Edge(0,E,H));
		edges.add(new Graph.Edge(0,E,F));
		edges.add(new Graph.Edge(0,E,G));
		
		Graph g = new Graph(vertices, edges);
		DFSPaths dfs = new DFSPaths(g.getVertices().size());
		dfs.dfs(g, A);
		dfs.printPath(g, A, F);
		
	}

}

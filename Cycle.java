package com.program.graph.algo;

import java.util.ArrayList;
import java.util.List;

public class Cycle {
	
	private boolean hasCycle;
	
	public Cycle(Graph g){

		for (Graph.Vertex v: g.getVertices()) {
			if (!v.visited) {
				dfs(g, v, v);
			}
		}
	}
	
	/*
	 * For each vertex we have three option 
	 * 1> Either next vertex will be unvisited, then simply call dfs(q, w, prev)
	 * 2> Or next vertex is visited. then we go to else part
	 * 3> if (w != prev), here if w == prev means w next vertex is same vertex we are coming from
	 *    and if its visited and its not equal to prev then we have encountered loop in graph.
	 *    coz this vertex w is already visited and its not equal to prev vertex.
	 */
	public void dfs(Graph g, Graph.Vertex v, Graph.Vertex prevVertex ){
		
		v.visited = true;
		Graph.Vertex w;
		for (Graph.Edge e : v.getEdges()) {
			w = e.getTo();
			if (!w.visited) {
				dfs(g, w, v); // here v is previous visited vertex
			}else if (w != prevVertex){
				hasCycle = true;
			}
		}
	}
	
	public boolean hasCycle(){
		return hasCycle;
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
		Cycle cyc = new Cycle(g);
		System.out.println("Graph has cycle ::: "+cyc.hasCycle());
	}

}

package com.program.graph.algo;

import java.util.ArrayList;
import java.util.List;

public class TwoColor {
	private boolean[] color;
	private boolean isTwoColorable = true;
	
	public TwoColor(Graph g){
		color = new boolean[g.getVertices().size()];
		for (Graph.Vertex v : g.getVertices()) {
			if (!v.visited) {
				dfs(g, v);
			}
		}
	}
	public void printColor(){
		
		for (boolean val : color) {
			System.out.print("--"+val);
		}
	}
	
	public void dfs(Graph g, Graph.Vertex v){
		v.visited = true;
		Graph.Vertex w;
		for (Graph.Edge e : v.getEdges()) {
			w = e.getTo();
			if (!w.visited) {
				color[g.getIndexOf(w)] =  !color[g.getIndexOf(v)];
				dfs(g, w);
			} else if (color[g.getIndexOf(w)] == color[g.getIndexOf(v)]) {
				isTwoColorable = false;
			}
		}
	}
	
	public boolean isBipartite(){
		return isTwoColorable;
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
		Graph.Vertex I = new Graph.Vertex('I');
		Graph.Vertex J = new Graph.Vertex('J');
		Graph.Vertex K = new Graph.Vertex('K');
		Graph.Vertex L = new Graph.Vertex('L');
		vertices.add(A);
		vertices.add(B);
		vertices.add(C);
		vertices.add(D);
		vertices.add(E);
		vertices.add(F);
		vertices.add(G);
		vertices.add(H);
		vertices.add(I);
		vertices.add(J);
		vertices.add(K);
		vertices.add(L);
		
		List<Graph.Edge> edges = new ArrayList<Graph.Edge>();
		edges.add(new Graph.Edge(0,A,B));
		edges.add(new Graph.Edge(0,B,C));
		edges.add(new Graph.Edge(0,B,H));
		edges.add(new Graph.Edge(0,C,D));
		edges.add(new Graph.Edge(0,C,E));
		edges.add(new Graph.Edge(0,E,H));
		edges.add(new Graph.Edge(0,E,F));
		edges.add(new Graph.Edge(0,E,G));
		
		edges.add(new Graph.Edge(0,I,J));
		edges.add(new Graph.Edge(0,J,K));
		edges.add(new Graph.Edge(0,I,L));
		edges.add(new Graph.Edge(0,I,K));
		
		
		Graph g = new Graph(vertices, edges);
		TwoColor tc = new TwoColor(g);
		System.out.println("Is graph Bipartite :: "+tc.isBipartite());
		System.out.println("---PRINT COLOR---");
		tc.printColor();
	}
}

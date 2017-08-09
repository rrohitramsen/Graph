package com.program.graph.algo;

import java.util.ArrayList;
import java.util.List;

public class ConnectedComponents {
	
	private int [] connectedId;
	private int processCount;
	
	public ConnectedComponents(Graph g) {
		this.connectedId = new int[g.getVertices().size()];
		this.processCount = 0;
		populateConnectedId(g);
	}
	
	public void populateConnectedId(Graph g){
		
		for (Graph.Vertex v : g.getVertices()) {
			if (!v.visited){
				dfs(g, v);
				processCount++;
			}
		}
	}
	
	public void dfs(Graph g, Graph.Vertex v){
		
		v.visited = true;
		connectedId[g.getIndexOf(v)] = processCount;
		for (Graph.Edge e : v.getEdges()) {
			if (!e.getTo().visited) {
				dfs(g,e.getTo());
			}
		}
	}
	
	
	public boolean isConnected(Graph g, Graph.Vertex v, Graph.Vertex w){
		return connectedId[g.getIndexOf(v)] == connectedId[g.getIndexOf(w)];
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
		ConnectedComponents cc = new ConnectedComponents(g);
		System.out.println("A is Connected to G ::: "+cc.isConnected(g, A, G));
		System.out.println("I is Connected to A ::: "+cc.isConnected(g, I, A));
		System.out.println("I is Connected to  K ::: "+cc.isConnected(g, I, K));
		
	}

}

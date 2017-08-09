package com.program.graph.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DirectedCycle {
	
	private DiGraph.Vertex [] edgeTo;
	private Stack<DiGraph.Vertex> cyclePath;
	private boolean onStack[];
	
	public DirectedCycle(DiGraph g){
		int size = g.getVertices().size();
		onStack = new boolean[size];
		edgeTo = new DiGraph.Vertex[size];
		
		for (DiGraph.Vertex v : g.getVertices()) {
			if (!v.isVisited()) {
				dfs(g, v);//, v);
			}
		}
	}
	
	public void dfs(DiGraph g, DiGraph.Vertex v) {//, DiGraph.Vertex prev){
		v.setVisited(true);
		onStack[g.getIndexOf(v)] = true;
		
		for (DiGraph.Edge e : v.getEdges()) {
			DiGraph.Vertex w = e.getTo();
			if (this.hashCycle()) {
				return;
			}else if (!w.isVisited()) {
				edgeTo[g.getIndexOf(w)] = v;
				dfs(g, w);//, v);
			} else if ( onStack[g.getIndexOf(w)]) { // cycle has found
				cyclePath = new Stack<DiGraph.Vertex>();
				//cycle path from source w to target v.
				for (DiGraph.Vertex x = v; x != w; x = edgeTo[g.getIndexOf(x)]) { 
					cyclePath.push(x);
				}
				cyclePath.push(w);
				cyclePath.push(v); // coz cycle will end again at v
			}
			onStack[g.getIndexOf(v)]=false; // once a vertex has visited completely it must be set to false, means its all edges has been visited;
		}
	}

	public boolean hashCycle() {
		return cyclePath != null;
	}
	
	public Stack<DiGraph.Vertex> getCyclePath(){
		return cyclePath;
	}
	public static void main(String[] args) {
		List<DiGraph.Vertex> vertices = new ArrayList<DiGraph.Vertex>(); 
		DiGraph.Vertex A = new DiGraph.Vertex("A");
		DiGraph.Vertex B = new DiGraph.Vertex("B");
		DiGraph.Vertex C = new DiGraph.Vertex("C");
		DiGraph.Vertex D = new DiGraph.Vertex("D");
		DiGraph.Vertex E = new DiGraph.Vertex("E");
		DiGraph.Vertex F = new DiGraph.Vertex("F");
		DiGraph.Vertex G = new DiGraph.Vertex("G");
		DiGraph.Vertex H = new DiGraph.Vertex("H");
		vertices.add(A);
		vertices.add(B);
		vertices.add(C);
		vertices.add(D);
		vertices.add(E);
		vertices.add(F);
		vertices.add(G);
		vertices.add(H);
		
		List<DiGraph.Edge> edges = new ArrayList<DiGraph.Edge>();
		edges.add(new DiGraph.Edge(0,A,B));
		edges.add(new DiGraph.Edge(0,B,C));
		edges.add(new DiGraph.Edge(0,H,B));
		edges.add(new DiGraph.Edge(0,C,D));
		edges.add(new DiGraph.Edge(0,C,E));
		edges.add(new DiGraph.Edge(0,E,H));
		edges.add(new DiGraph.Edge(0,E,F));
		edges.add(new DiGraph.Edge(0,E,G));
		
		DiGraph g = new DiGraph(DiGraph.TYPE.DIRECTED, vertices, edges);
		DirectedCycle dc = new DirectedCycle(g);
		for (DiGraph.Vertex v : dc.getCyclePath()){
			System.out.println("-"+v);
		}
	}

}

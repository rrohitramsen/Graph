package com.test.graph.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;


public class BFS {
	
	private Graph.Vertex edgeTo[];
	
	public BFS(int size) {
		edgeTo = new Graph.Vertex[size];
	}
	
	public void bfs(Graph g, Graph.Vertex q){
		
		Queue<Graph.Vertex> queue = new ArrayBlockingQueue<Graph.Vertex>(g.getVertices().size());
		q.visited = true;
		System.out.println("Vertex : "+q);
		queue.add(q);
		Graph.Vertex w,v;
		while (!queue.isEmpty()) {
			v = queue.remove();
			while ( (w = getAdjUnvisitedVertex(v)) != null) {
				w.visited = true;
				queue.add(w);
				edgeTo[g.getIndexOf(w)] = v; // creating paths from v
				System.out.println("Vertex : "+w);
			}
		}
	}
	
	public Graph.Vertex getAdjUnvisitedVertex(Graph.Vertex v){
		for (Graph.Edge e : v.getEdges()) {
			if (!e.getTo().visited){
				return e.getTo();
			}
		}
		return null;
	}
	
	public void printPath(Graph g, Graph.Vertex source, Graph.Vertex target){
		
		Stack<Graph.Vertex> path = new Stack<Graph.Vertex>();
		for (Graph.Vertex v = target; v != source ; v = edgeTo[g.getIndexOf(v)]){
			path.add(v);
		}
		path.add(source);
		System.out.print("Path");
		while (!path.isEmpty()) {
			System.out.print("--"+path.pop());
		}
	}
	
	
	public static void main(String[] args) {
		
		List<Graph.Vertex> vertices = new ArrayList<Graph.Vertex>(); 
		Graph.Vertex A = new Graph.Vertex('A');
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
		
		BFS bfsObj = new BFS(g.getVertices().size());
		bfsObj.bfs(g, A);
		bfsObj.printPath(g, B, G);
	}
	
}

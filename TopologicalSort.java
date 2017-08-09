package com.program.graph.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class TopologicalSort {
	
	private int inDegree[];
	DiGraph.Vertex topologicalOrder [];
	
	public void buildInDegree(DiGraph g) {
		inDegree = new int[g.vertexCount];
		DiGraph.Vertex to;
		int index;
		for (DiGraph.Edge e : g.getEdges()) {
			to = e.getTo();
			index = g.getIndexOf(to);
			inDegree[index] += 1;
		}
	}
	
	public TopologicalSort(DiGraph g) {
		buildInDegree(g);
		topologicalOrder = new DiGraph.Vertex[g.vertexCount];
		Queue<DiGraph.Vertex> queue = new ArrayBlockingQueue<DiGraph.Vertex>(g.vertexCount);
		int counter = 0;
		// add all vertex with indegree 0 in queue
		for (DiGraph.Vertex v : g.getVertices()) {
			if (inDegree[g.getIndexOf(v)] == 0) {
				queue.add(v);
			}
		}
		
		DiGraph.Vertex v,w;
		while (!queue.isEmpty()) {
			v = queue.remove();
			topologicalOrder[counter++] = v;
			System.out.println("--"+v);
			for (DiGraph.Edge e : v.getEdges()) { // for each w adjacent to v
				w = e.getTo();
				if ( --inDegree[g.getIndexOf(w)] == 0) { 
					// decrease indegree of each vertex once visited by 1 and add to queue if indegree becomes 0
					queue.add(w);
				}
			}
		}
		if (counter != g.vertexCount) {
			System.out.println("graph has cycle");
		}
	}
	
	public static void main(String[] args) {
		List<DiGraph.Vertex> vertices = new ArrayList<DiGraph.Vertex>(); 
		DiGraph.Vertex A = new DiGraph.Vertex("A");
		/*
		 * If Inner class graph and edge were not static then 
		 * First we have to instantiate Graph and then vertex/edge
		 * eg :
		 * Grapg g1 = new Graph();
		 * Graph.vertex A = g1.new Vertex('A');
		 * 
		 */
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
		edges.add(new DiGraph.Edge(0,B,H));
		edges.add(new DiGraph.Edge(0,C,D));
		edges.add(new DiGraph.Edge(0,C,E));
		edges.add(new DiGraph.Edge(0,E,H));
		edges.add(new DiGraph.Edge(0,E,F));
		edges.add(new DiGraph.Edge(0,E,G));
		
		DiGraph g = new DiGraph(DiGraph.TYPE.DIRECTED, vertices, edges);
		TopologicalSort tp = new TopologicalSort(g);
		for (DiGraph.Vertex v : tp.topologicalOrder) {
			System.out.println("--"+v);
		}
	}

}

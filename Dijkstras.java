package com.program.graph.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Dijkstras {
	
	private int distance[];
	private DiGraph.Vertex [] path;
	private final static int INT_MAX = 999999;
	
	public Dijkstras(DiGraph g){
		distance = new int[g.vertexCount];
		for (int i=0; i<g.vertexCount; i++){
			distance[i]=-1;
		}
		path = new DiGraph.Vertex[g.vertexCount];
	}
	
	public void dijkstraAlgo(DiGraph g, DiGraph.Vertex s){
		DiGraph.Vertex v, w;
		int d, wIndex;
		Queue<DiGraph.Vertex> pq = new ArrayBlockingQueue<DiGraph.Vertex>(g.vertexCount);
		pq.add(s);
		distance[g.getIndexOf(s)] = 0;
		
		while (!pq.isEmpty()) {
			v = pq.remove();
			
			for (DiGraph.Edge e : v.getEdges()){
				w = e.getTo();
				wIndex = g.getIndexOf(w);
				d = distance[g.getIndexOf(v)] + w.getWeight() + 1;
				if (distance[wIndex] == -1) {
					distance[wIndex] = d;
					pq.add(w);
					path[wIndex] = v;
				}
				if (distance[wIndex] > d) {
					distance[wIndex] = d;
					pq.add(w);
					path[wIndex] = v;
				}
			}
		}
	}
		
	/*
	 * It will consider negative edges as well
	 * in this we intialise distance[] with INT_MAX value, so that we can only update in case
	 * distance[w] > distance[v] + w.weight;
	 */
	public void bellmanFordAlgo(DiGraph g, DiGraph.Vertex s){
		
		for (int i=0; i<g.vertexCount; i++){
			distance[i] = INT_MAX;
		}
		
		DiGraph.Vertex v, w;
		int d, wIndex;
		Queue<DiGraph.Vertex> q = new ArrayBlockingQueue<DiGraph.Vertex>(g.vertexCount);
		q.add(s);
		distance[g.getIndexOf(s)] = 0;
		
		while (!q.isEmpty()){
			v = q.remove();
			for (DiGraph.Edge e : v.getEdges()) {
				w = e.getTo();
				wIndex = g.getIndexOf(w);
				d = distance[g.getIndexOf(v)] + w.getWeight() + 1;
				if (distance[wIndex] > d){
					distance[wIndex]=d;
					path[wIndex] = v;
					if (!q.contains(w)) {
						q.add(w);
					}
				}
			}
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
		edges.add(new DiGraph.Edge(0,H,B));
		edges.add(new DiGraph.Edge(0,C,D));
		edges.add(new DiGraph.Edge(0,C,E));
		edges.add(new DiGraph.Edge(0,E,H));
		edges.add(new DiGraph.Edge(0,E,F));
		edges.add(new DiGraph.Edge(0,E,G));
		
		DiGraph g = new DiGraph(DiGraph.TYPE.DIRECTED, vertices, edges);
		Dijkstras dj = new Dijkstras(g);
		dj.dijkstraAlgo(g, A);
		int dist=0;
		for (int i= g.getIndexOf(A); i<= g.getIndexOf(F); i++){
			dist += dj.distance[i];
		}
		System.out.println("Distance between "+A+"---"+F+"---is --"+dist);
		
		dj.bellmanFordAlgo(g, C);
		dist=0;
		for (int i= g.getIndexOf(C); i<= g.getIndexOf(F); i++){
			dist += dj.distance[i];
		}
		System.out.println("Distance BellMan ford between "+C+"---"+F+"---is --"+dist);
	}
}

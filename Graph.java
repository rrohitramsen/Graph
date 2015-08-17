package com.test.graph.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*
 * @author rrohit
 */
public class Graph {
	
	private List <Vertex> vertices = new CopyOnWriteArrayList<Vertex>();
	private List <Edge> edges = new CopyOnWriteArrayList<Edge>();
	private TYPE type = TYPE.UNDIRECTED;
	
	public enum TYPE {
		DIRECTED, UNDIRECTED;
	}
	
	public Graph(TYPE type){
		this.type = type;
	}
	
	public Graph (List<Vertex> vertices, List<Edge> edges) {
		this(TYPE.UNDIRECTED, vertices, edges);
	}
	
	public Graph(TYPE type, List<Vertex> vertices, List<Edge> edges){
		this(type);
		this.vertices.addAll(vertices);
		this.edges.addAll(edges);
		
		/*
		 * Now construct graph with the help of the above vertices and edges 
		 */

		for (Edge e : edges) {
			Vertex from = e.from;
			Vertex to = e.to;
			
			if (!this.vertices.contains(from) || !this.vertices.contains(to)) {
				continue;
			}
			
			//get from and to vertex from list of Vertices using from and to in the respective edge
			int index = vertices.indexOf(from);
			Vertex fromVertex = this.vertices.get(index);
			index = vertices.indexOf(to);
			Vertex toVertex = this.vertices.get(index);
			
			fromVertex.edges.add(e); // fromVertex.addEdge(e);
			
			if (this.type == TYPE.UNDIRECTED) {
				// add reciprocal edge also i.e A-->B and B-->A is reciprocal
				Edge reciprocal = new Edge(e.cost, toVertex, fromVertex);
				toVertex.edges.add(reciprocal); // toVertex.addEdge(reciprocal)
				this.edges.add(reciprocal);
			}
		}
	}
	
	/*
	 * return index of the given vertex from the list of vertex, vertices
	 */
	public int getIndexOf(Vertex v){
		return this.vertices.indexOf(v);
	}
	
	/*
	 * Deep copy of a graph from another graph
	 */
	public Graph(Graph g){
		/*
		 * Copy the vertices which copies edges
		 */
		for (Vertex v : g.vertices) {
			this.vertices.add(new Vertex(v));
		}
		/*
		 * update the object references i.e update edges
		 */
		for (Vertex v : this.vertices){
			for (Edge e : v.edges) {
				Vertex from = e.getFrom();
				Vertex to = e.getTo();
				int index = this.vertices.indexOf(from);
				e.from = this.vertices.get(index);
				index = this.vertices.indexOf(to);
				e.to = this.vertices.get(index);
				this.edges.add(e);
			}
		}
		
	}
	
	
	public List<Vertex> getVertices() {
		return vertices;
	}

	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
	}


	 static class Vertex {
		
		private char label;
		private int weight;
		public boolean visited;
		private List<Edge> edges = new ArrayList<Edge>();
		
		public Vertex(char label){
			this.label = label;
			weight=0;
			visited=false;
		}
		
		public Vertex(Vertex v){
			this(v.label);
			for (Edge e : v.edges) {
				this.edges.add(new Edge(e));
			}
		}
		
		/*
		 * Find path from A to B, means fromVertex is A and toVertex is B
		 */
		private boolean pathTo(Vertex v){
			for (Edge e : edges) {
				if (e.to.equals(v)) {
					return true;
				}
			}
			return false;
		}
		
		public boolean equals(Object obj){
			if(!(obj instanceof Vertex)){
				return false;
			}
			
			Vertex v = (Vertex) obj;
			if (v.label != this.label){
				return false;
			}
			
			if (v.weight != this.weight){
				return false;
			}
			return true;
		}
		
		public String toString(){
			return "["+this.label+"]";
		}
		
		public char getLabel() {
			return label;
		}

		public void setLabel(char label) {
			this.label = label;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		public List<Edge> getEdges() {
			return edges;
		}

		public void setEdges(List<Edge> edges) {
			this.edges = edges;
		}

		public boolean isVisited() {
			return visited;
		}

		public void setVisited(boolean visited) {
			this.visited = visited;
		}
		
	}
	
	
	static class Edge{

		private Vertex from;
		private Vertex to;
		private int cost;
		
		public Edge(int cost, Vertex from, Vertex to){
			this.from = from;
			this.to = to;
			this.cost=cost;
		}
		
		public Edge(Edge e){
			this(e.cost, e.from, e.to);
		}

		public Vertex getFrom() {
			return from;
		}

		public void setFrom(Vertex from) {
			this.from = from;
		}

		public Vertex getTo() {
			return to;
		}

		public void setTo(Vertex to) {
			this.to = to;
		}

		public int getCost() {
			return cost;
		}

		public void setCost(int cost) {
			this.cost = cost;
		}
		
	}

}

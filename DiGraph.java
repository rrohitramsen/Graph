package com.program.graph.algo;

import java.util.ArrayList;
import java.util.List;

public class DiGraph {
	
	private List<Vertex> vertices = new ArrayList<Vertex>();
	private List<Edge> edges = new ArrayList<Edge>();
	public int vertexCount;
	
	private Vertex [] edgeTo;
	
	public enum TYPE{
		DIRECTED, UNDIRECTED;
	};
	
	private TYPE type;
	
	public DiGraph(){
		this.type=TYPE.DIRECTED;
	}
	
	public DiGraph(TYPE type){
		this.type=type;
	}
	
	public DiGraph(TYPE type, List<Vertex> vertices, List<Edge> edges){
		this(type);
		this.vertices.addAll(vertices);
		this.edges.addAll(edges);
		this.vertexCount = vertices.size();
		
		for (Edge e : edges){
			Vertex from = e.from;
			Vertex to = e.to;
			
			if (!vertices.contains(from) || !vertices.contains(to)){
				continue;
			}
			
			int index = vertices.indexOf(from);
			from = vertices.get(index);
			index = vertices.indexOf(to);
			to = vertices.get(index); // only if u want reciprocal edge to be added
			
			from.addEdge(e);
			
			if (this.type == TYPE.UNDIRECTED) {
				Edge reciprocal = new Edge(to, from);
				to.addEdge(reciprocal);
				this.edges.add(reciprocal);
			}
			
		}
	}
	
	public DiGraph(DiGraph g){
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
				Vertex from = e.from;
				Vertex to = e.to;
				int index = this.vertices.indexOf(from);
				e.from = this.vertices.get(index);
				index = this.vertices.indexOf(to);
				e.to = this.vertices.get(index);
				this.edges.add(e);
			}
		}
		
	}
	
	public int getIndexOf(Vertex v){
		return this.vertices.indexOf(v);
	}
	
	/*
	 * Returns the reveres DiGraph of the current graph.
	 */
	public DiGraph reverse(){
		DiGraph R = new DiGraph();
		
		for (Vertex v : vertices){
			R.vertices.add(new Vertex(v));
		}
		
		for (Vertex v : R.vertices) {
			for (Edge e : v.edges) {
				Vertex from = e.from;
				Vertex to = e.to;
				int index = R.vertices.indexOf(to);
				e.from = R.vertices.get(index);
				index = R.vertices.indexOf(from);
				e.to =  R.vertices.get(index);
				R.edges.add(e);
			}
		}
		return R;
	}
	
	public void dfs(DiGraph g, Vertex v){
		v.visited=true;
		System.out.println("-"+v);
		for (Edge e : v.edges) {
			Vertex w = e.to;
			if (!w.visited) {
				edgeTo[g.getIndexOf(w)] = v;
				dfs(g, w);
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

	public Vertex[] getEdgeTo() {
		return edgeTo;
	}

	public void setEdgeTo(Vertex[] edgeTo) {
		this.edgeTo = edgeTo;
	}

	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
	}


	public static class Vertex{
		private String label;
		private List<Edge> edges = new ArrayList<Edge>();
		private int weight;
		private boolean visited;
		
		public Vertex(){
			weight = 0;
			visited = false;
		}
		
		public Vertex(String label){
			this();
			this.label=label;
		}
		
		public Vertex(Vertex v){
			this(v.label);
			for (Edge e : v.edges) {
				edges.add(new Edge(e.from, e.to));
			}
			
		}
		
		public void addEdge(Edge e){
			this.edges.add(e);
		}
		
		public boolean equals(Object obj){
			
			if (  !(obj instanceof Vertex) && obj != null) {
				return false;
			}
			
			Vertex v = (Vertex)obj;
			if (!this.label.equals(v.label)){
				return false;
			}
			
			if (this.weight != v.weight){
				return false;
			}
			
			return true;
		}
		
		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public List<Edge> getEdges() {
			return edges;
		}

		public void setEdges(List<Edge> edges) {
			this.edges = edges;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		public boolean isVisited() {
			return visited;
		}

		public void setVisited(boolean visited) {
			this.visited = visited;
		}
		
		public String toString(){
			return "Vertex["+this.label+"] -- Weight = "+this.weight;
		}
		
		
	}
	
	public static class Edge{
		
		private Vertex from;
		private Vertex to;
		private int cost;
		
		public Edge(int cost){
			this.cost = 0;
		}
		
		public Edge(Vertex from, Vertex to){
			this(0);
			this.from=from;
			this.to=to;
		}
		
		public Edge(int cost, Vertex from, Vertex to){
			this(from, to);
			this.cost=cost;
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

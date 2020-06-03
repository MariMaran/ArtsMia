package it.polito.tdp.artsmia.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
  Graph<ArtObject, DefaultWeightedEdge> grafo;
  ArtsmiaDAO dao;
  List<ArtObject> oggettiArte;
  Map<Integer,ArtObject> idMap;
  
  public Model() {
	  dao=new ArtsmiaDAO();
  }
  
  public void creaGrafo() {
	  idMap=new HashMap();
	  grafo=new SimpleWeightedGraph(DefaultWeightedEdge.class);
	  oggettiArte=dao.listObjects();
	  for(ArtObject a: oggettiArte) {
		  grafo.addVertex(a);
		  idMap.put(a.getId(), a);
	  }
	  List<Collegamento> collegamenti=dao.getCollegamenti(idMap);
	  for(Collegamento c: collegamenti) {
		  if(grafo.getEdge(c.a1, c.a2)==null) {
		  grafo.addEdge(c.a1, c.a2);
		  grafo.setEdgeWeight(c.a1, c.a2, c.peso);
		  }
	  }
	  
  }
}

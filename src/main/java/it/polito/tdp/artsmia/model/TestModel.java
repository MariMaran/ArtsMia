package it.polito.tdp.artsmia.model;

import java.util.List;
import java.util.Set;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
           Model m=new Model();
           m.creaGrafo();
           System.out.println(m.grafo.edgeSet().size());
           System.out.println(m.grafo.vertexSet().size());
           List<ArtObject> prova=m.oggettiArte;
           int i=0;
           for( ArtObject a :prova) {
        	   if(a.getId()==654)
        	   System.out.println(a);
        	   i++;
           }
           System.out.println(i);
           
	}

}

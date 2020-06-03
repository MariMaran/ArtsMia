package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Collegamento;

public class ArtsmiaDAO {

	public List<ArtObject> listObjects() {
		
		String sql = "SELECT * from objects";
		List<ArtObject> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				ArtObject artObj = new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title"));
				
				result.add(artObj);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Collegamento> getCollegamenti(Map<Integer,ArtObject> idMap){
		String sql="SELECT eo.object_id AS a1, eo2.object_id AS a2, COUNT(DISTINCT e.exhibition_id) AS peso "+
				"FROM exhibitions e join exhibition_objects eo JOIN exhibition_objects eo2 "+
				"WHERE e.exhibition_id=eo.exhibition_id && eo2.exhibition_id=e.exhibition_id && eo2.object_id!=eo.object_id "+
				"GROUP BY eo.object_id, eo2.object_id";
		
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			List<Collegamento> result=new ArrayList();
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Collegamento c=new Collegamento(idMap.get(res.getInt("a1")),idMap.get(res.getInt("a2")),res.getInt("peso"));
				result.add(c);
				
			}
			conn.close();
			return result ;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}

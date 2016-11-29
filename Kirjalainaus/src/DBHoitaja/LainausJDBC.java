package DBHoitaja;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kohdeluokat.Asiakas;
import kohdeluokat.Kirja;
import kohdeluokat.Lainaus;
import kohdeluokat.Nide;
import kohdeluokat.Niteenlainaus;
import kohdeluokat.PostinumeroAlue;

public class LainausJDBC {
	JDBC jdbc = new JDBC();
	private java.sql.ResultSet rs;
	private java.sql.Statement stmt;
	
	
	public List<Integer> getLainausNumerot(){
		int numero;
		List<Integer> lista = new ArrayList<Integer>();
		String sql = "select numero from lainaus";
		 if(jdbc.getConnection() != null){
			 try {
				stmt = jdbc.getConnection().prepareStatement(sql);
				rs = stmt.executeQuery(sql);
				while(rs.next()){
					numero = rs.getInt("numero");
					lista.add(numero);
				}
				jdbc.getConnection().commit();
				
			} catch (SQLException e) {
				try {
					jdbc.getConnection().rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		
	    }
		return lista;
	}
	
	public Lainaus getLainauksen(int numero){
		 Lainaus l = new Lainaus();
		 Asiakas a = new Asiakas();
		 PostinumeroAlue p = new PostinumeroAlue();
		 String sql = "select l.numero, l.lainauspvm, a.etunimi, a.sukunimi, a.osoite, p.postinro, p.postitmp"+ 
		              " from lainaus as l, asiakas as a, postinumeroAlue as p"+
				      " where l.asiakasnro_FK = a.numero AND p.postinro = a.postinro_FK"+
		              " AND l.numero ="+numero;
		 if(jdbc.getConnection() != null){
			 try {
				stmt = jdbc.getConnection().prepareStatement(sql);
				rs = stmt.executeQuery(sql);
				while(rs.next()){
					p.setPostinro(rs.getString("p.postinro"));
					p.setPostitmp(rs.getString("p.postitmp"));
					
					a.setEtunimi(rs.getString("a.etunimi"));
					a.setSukunimi(rs.getString("a.sukunimi"));
					a.setOsoite(rs.getString("a.osoite"));
					a.setPosti(p);
					
					l.setNumero(rs.getInt("l.numero"));
					l.setLainausPvm(rs.getDate("l.lainauspvm"));
					l.setLainaaja(a);
				}
				jdbc.getConnection().commit();
			} catch (SQLException e) {
				try {
					jdbc.getConnection().rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		 }
		return l;
	}
	
	public List<Niteenlainaus> getLiteenlainaukset(){
		List<Niteenlainaus> lista = new ArrayList<Niteenlainaus>();
		
		String sql = "select n.isbn_FK, k.nimi, k.kirjoittaja, k.painos, k.kustantaja,"+
		             " n.nidenro,"+
				     " nl.palautuspvm"+
		             " from kirja as k, nide as n, niteenlainaus as nl "+
				     " where k.isbn = n.isbn_FK and n.nidenro = nl.nidenro";
		if(jdbc.getConnection()!= null) {
		try {
			stmt = jdbc.getConnection().prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Niteenlainaus n = new Niteenlainaus();
				Nide nide = new Nide();
				Kirja k = new Kirja();
				k.setIsbn(rs.getString("n.isbn_FK"));
				k.setKirjoittaja(rs.getString("k.kirjoittaja"));
				k.setKustantaja(rs.getString("k.kustantaja"));
				k.setNimi(rs.getString("k.nimi"));
				k.setPainos(rs.getInt("k.painos"));
				
				nide.setNidenro(rs.getInt("n.nidenro"));
				nide.setKirja(k);
				
				n.setPalautusPvm(rs.getDate("nl.palautuspvm"));
				n.setNide(nide);
				
				lista.add(n);
			}
			jdbc.getConnection().commit();
		} catch (SQLException e) {
			try {
				jdbc.getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	    }
		return lista;
	}

}

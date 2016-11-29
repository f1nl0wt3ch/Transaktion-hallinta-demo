package DBHoitaja;

import java.util.ArrayList;
import java.util.List;

import kohdeluokat.Niteenlainaus;

public class test {
    
	public static void main(String[] args) {
		LainausJDBC lainaus = new LainausJDBC();
		List<Niteenlainaus> list = new ArrayList<Niteenlainaus>();
		list = lainaus.getLiteenlainaukset();
		for(Niteenlainaus nl : list){
			System.out.println(nl.getNide().getKirja().getIsbn());
		}
		
	}
}

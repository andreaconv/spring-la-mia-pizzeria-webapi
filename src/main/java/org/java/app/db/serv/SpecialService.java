package org.java.app.db.serv;

import java.util.List;

import org.java.app.db.pojo.Special;
import org.java.app.db.repo.SpecialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialService {
	
	@Autowired
	private SpecialRepo specialRepo;
	
	public List<Special> findAll() {
		
		return specialRepo.findAll();
	}
	public Special findById(int id) {
		
		return specialRepo.findById(id).get();
	}
	public void save(Special special) {
		
		specialRepo.save(special);
	}
	public void deleteSpecial(Special special) {
		
		specialRepo.delete(special);
	}

}

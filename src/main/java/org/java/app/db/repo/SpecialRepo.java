package org.java.app.db.repo;

import java.util.List;

import org.java.app.db.pojo.Pizza;
import org.java.app.db.pojo.Special;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialRepo extends JpaRepository<Special, Integer> {
	
	public List<Special> findByPizza(Pizza pizza);

}

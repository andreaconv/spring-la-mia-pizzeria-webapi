package org.java.app.auth.repo;

import org.java.app.auth.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	public User findByUsername(String username);
}
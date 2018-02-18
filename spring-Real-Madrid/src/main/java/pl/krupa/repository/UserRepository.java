package pl.krupa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.krupa.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String email);
	public User findByUserId(Long userId);
	
	@Modifying
	@Query("UPDATE User u SET u.firstName = ?1, u.lastName = ?2, u.email = ?3 WHERE u.userId = ?4")
	@Transactional
	public void setUserInfoById(String firstName, String lastName, String email, Long userId);
	
	@Modifying
	@Query("UPDATE User u SET u.firstName = ?1, u.lastName = ?2, u.email = ?3, u.password = ?4 WHERE u.userId = ?5")
	@Transactional
	public void setUserInfoByIdAndPassword(String firstName, String lastName, String email, String password, Long userId);
	
	@Modifying
    @Transactional
    @Query("DELETE from User u WHERE u.userId = ?1")
    public void deleteUserByUserId(Long userId);
}


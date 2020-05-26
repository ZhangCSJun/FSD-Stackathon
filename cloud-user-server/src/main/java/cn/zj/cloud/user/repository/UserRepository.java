package cn.zj.cloud.user.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.zj.cloud.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	@Modifying
	@Transactional
	@Query(value="update users set confirmed='1' where id=:id", nativeQuery=true)
	public int activeUser(String id);
	
	@Query(value="select * from users where username=:username and password=:password and confirmed ='1'", nativeQuery=true)
	List<User> queryUser(String username, String password);

	
	public Optional<User> findById(String id);
	
	@Modifying
	@Transactional
	@Query(value="update users set password=:newpwd where id=:id and password=:oldpwd and usertype='0'", nativeQuery=true)
	public int updatePassword(String id, String oldpwd, String newpwd);

	@Query(value="select * from users where usertype='0' order by id desc limit 1", nativeQuery=true)
	List<User> queryMaxId();
	
	@Query(value="select * from users where username=:username", nativeQuery=true)
	List<User> queryName(String username);
}

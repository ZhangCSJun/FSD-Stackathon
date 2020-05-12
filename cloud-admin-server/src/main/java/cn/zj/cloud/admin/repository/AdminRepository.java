package cn.zj.cloud.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.zj.cloud.admin.entity.User;

@Repository
public interface AdminRepository extends JpaRepository<User, String>{
	@Query(value="select * from users where username=:username and password=:password and userType ='1'", nativeQuery=true)
	List<User> queryUser(String username, String password);

}

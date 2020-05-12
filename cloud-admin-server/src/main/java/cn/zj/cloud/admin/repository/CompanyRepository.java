package cn.zj.cloud.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.zj.cloud.admin.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String>{
	@Query(value="select * from company where name like concat(:companyname,'%')", nativeQuery=true)
	List<Company> queryByCompanyName(String companyname);

	@Modifying
	@Transactional
	@Query(value="update company set status=:status where id=:id", nativeQuery=true)
	int updateCompanyStatus(String id, String status);

	@Query(value="select * from company order by id desc limit 1", nativeQuery=true)
	List<Company> queryMaxId();
}

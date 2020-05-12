package cn.zj.cloud.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.zj.cloud.user.entity.IpoDetail;

@Repository
public interface IpodetailRepository extends JpaRepository<IpoDetail, String>{
	@Query(value="select * from ipodetails where companyname=:companyname", nativeQuery=true)
	List<IpoDetail> queryByCompanyName(String companyname);
}

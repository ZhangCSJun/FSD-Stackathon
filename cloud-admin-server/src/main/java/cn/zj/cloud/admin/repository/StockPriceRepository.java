package cn.zj.cloud.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.zj.cloud.admin.entity.StockPrice;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, String> {
	@Query(value="select * from stockprice order by id desc limit 1", nativeQuery=true)
	List<StockPrice> queryMaxId();
}

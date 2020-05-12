package cn.zj.cloud.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zj.cloud.user.entity.IpoDetail;
import cn.zj.cloud.user.entity.User;
import cn.zj.cloud.user.model.LoginInfo;
import cn.zj.cloud.user.repository.IpodetailRepository;
import cn.zj.cloud.user.repository.UserRepository;
import cn.zj.cloud.user.util.Util;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private IpodetailRepository ipodetailRepository;
	
	/**
	 * User Regist
	 * @param user
	 * @return
	 */
	public void regist(User user) {
		// generate user id
		String id = "8888888";
		user.setId(id);

		User newUser = userRepository.save(user);
		String userId = newUser.getId();
		// TODO
		// sendMail to user for confirm
	}
	
	/**
	 * Active User
	 * @param id
	 * @return
	 */
	public int activeUser(String id) {
		return userRepository.activeUser(id);
	}
	
	/**
	 * Get User Login Info
	 * @param user
	 * @return
	 */
	public Map<String, String> login(String username, String password){
		Map<String, String> result = new HashMap<String, String>();
		List<User> userList = userRepository.queryUser(username, password);
		if(userList.size() == 1) {
			User user = (User)userList.get(0);
			result.put("status", "1");
			result.put("id", user.getId());
		} else {
			result.put("status", "0");
		}

		return result;
	}
	
	/**
	 * Update User's password
	 * @param user
	 * @return
	 */
	public Map<String, String> updatePassword(String id, String oldpwd, String newpwd){
		int rowNum = userRepository.updatePassword(id, oldpwd, newpwd);
		
		Map<String, String> result = new HashMap<String, String>();
		if(rowNum == 1) {
			result.put("status", "1");
		} else {
			result.put("status", "0");
		}
		return result;
	}
	
	/**
	 * Query IpoDetails
	 * @return
	 */
	public List<Map<String, String>> queryIpoDetails(){
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		List<IpoDetail> ipoDetails = ipodetailRepository.findAll();
		
		for(IpoDetail ipoDetail: ipoDetails){
			Map<String, String> ipoMap = new HashMap<String, String>();
			ipoMap.put("id", ipoDetail.getId());
			ipoMap.put("company", ipoDetail.getCompanyName());
			ipoMap.put("stockexchange", ipoDetail.getStockExchange());
			ipoMap.put("pircepershare", ipoDetail.getPricePerShare().toString());
			ipoMap.put("totalnumberofshares", String.valueOf(ipoDetail.getTotalNumberOfShares()));
			ipoMap.put("opendatatime", Util.plainDate(ipoDetail.getOpenDataTime()));
			result.add(ipoMap);
		}

		return result;
	}
	
	/**
	 * Query IpoDetails By Company Name
	 * @param companyName String
	 * @return
	 */
	public List<Map<String, String>> queryIpoDetailsByCompanyName(String companyName){
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		List<IpoDetail> ipoDetails = ipodetailRepository.queryByCompanyName(companyName);
		
		for(IpoDetail ipoDetail: ipoDetails){
			Map<String, String> ipoMap = new HashMap<String, String>();
			ipoMap.put("id", ipoDetail.getId());
			ipoMap.put("company", ipoDetail.getCompanyName());
			ipoMap.put("stockexchange", ipoDetail.getStockExchange());
			ipoMap.put("pircepershare", ipoDetail.getPricePerShare().toString());
			ipoMap.put("totalnumberofshares", String.valueOf(ipoDetail.getTotalNumberOfShares()));
			ipoMap.put("opendatatime", Util.plainDate(ipoDetail.getOpenDataTime()));
			result.add(ipoMap);
		}

		return result;
	}
}

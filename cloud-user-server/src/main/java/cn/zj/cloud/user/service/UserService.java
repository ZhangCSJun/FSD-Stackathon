package cn.zj.cloud.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import cn.zj.cloud.constant.Constant;
import cn.zj.cloud.user.entity.IpoDetail;
import cn.zj.cloud.user.entity.User;
import cn.zj.cloud.model.Response;
import cn.zj.cloud.user.repository.IpodetailRepository;
import cn.zj.cloud.user.repository.UserRepository;
import cn.zj.cloud.util.Email;
import cn.zj.cloud.util.StringUtil;


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
	public Response regist(User user) {
		Response response = new Response();
		// check whether the user name is existed
//		if(isExistUser(user.getUserName())) {
//			response.setStatus(HttpStatus.OK.value());
//			response.setCode(Constant.CODE_ZERO);
//			response.setMessage(Constant.REGIST_FAIL_USER_NAME_EXISTED);
//			return response;
//		}
		
		// generate user id
		String id = generateUserId();
		user.setId(id);
		user.setUserType(Constant.NUM_ZERO);
		// register user
		User newUser = null;
		try {
			newUser = userRepository.save(user);
			if (newUser == null) {
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				response.setCode(Constant.CODE_ZERO);
				response.setMessage(Constant.INTERNAL_SERVER_ERROR);
			}
		} catch(DataIntegrityViolationException e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setCode(Constant.CODE_ZERO);
			response.setMessage(Constant.REGIST_FAIL_USER_NAME_EXISTED);
		}
		
		
		String userId = newUser.getId();
		String mailAddress = newUser.getEmail();
		
		if(!StringUtil.isNullOrEmpty(userId) && !StringUtil.isNullOrEmpty(mailAddress)) {
			// if mailAddress and userId are not empty, send mail
			Email mail = new Email();
			mail.sendMail(mailAddress, userId);

			response.setStatus(HttpStatus.OK.value());
			response.setCode(Constant.CODE_ONE);
			response.setMessage(Constant.REGIST_SUCESS);
		} else {
			response.setStatus(HttpStatus.OK.value());
			response.setCode(Constant.CODE_ZERO);
			response.setMessage(Constant.REGIST_FAIL);
		}
		return response;
	}
	
	/**
	 * Active User
	 * @param id
	 * @return
	 */
	public Response activeUser(String id) {
		Response response = new Response();
		int rowNum = userRepository.activeUser(id);
		if(rowNum == 1) {
			response.setStatus(HttpStatus.OK.value());
			response.setCode(Constant.CODE_ONE);
			response.setMessage(Constant.ACTIVE_SUCESS);
		} else {
			response.setStatus(HttpStatus.OK.value());
			response.setCode(Constant.CODE_ZERO);
			response.setMessage(Constant.ACTIVE_FAIL);
		}
		return response;
	}
	
	/**
	 * Get User Login Info
	 * @param user
	 * @return
	 */
	public Response login(String username, String password){
		Response response = new Response();
		List<User> userList = userRepository.queryUser(username, password);
		if(userList.size() == 1) {
			User user = userList.get(0);
			response.setStatus(HttpStatus.OK.value());
			response.setCode(Constant.CODE_ONE);
			response.setMessage(Constant.LOGIN_SUCESS);
			Map<String, Object> business = new LinkedHashMap<String, Object>();
			business.put(Constant.BUSINESS_DATA_ID, user.getId());
			business.put(Constant.BUSINESS_DATA_ROLE, user.getUserType());
			response.setBusiness(business);
		} else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setCode(Constant.CODE_ZERO);
			response.setMessage(Constant.LOGIN_FAIL);
		}

		return response;
	}
	
	/**
	 * Query User Info By id
	 * @param id
	 * @return
	 */
	public Response queryUserById(String id) {
		Response response = new Response();
		Optional<User> userOptional = userRepository.findById(id);
		if(userOptional.isPresent()) {
			response.setStatus(HttpStatus.OK.value());
			response.setCode(Constant.CODE_ONE);
			response.setMessage(Constant.QUERY_DATA_SUCESS.replace("{0}", Constant.NUM_ONE));
			Map<String, Object> business = new LinkedHashMap<String, Object>();
			business.put(Constant.BUSINESS_DATA_DATA, userOptional.get());
			response.setBusiness(business);
		} else {
			response.setStatus(HttpStatus.OK.value());
			response.setCode(Constant.CODE_ONE);
			response.setMessage(Constant.QUERY_DATA_SUCESS.replace("{0}", Constant.NUM_ZERO));
		}
		return response;
	}
	
	/**
	 * Update User's password
	 * @param user
	 * @return
	 */
	public Response updatePassword(String id, String oldpwd, String newpwd){
		Response response = new Response();
		int rowNum = userRepository.updatePassword(id, oldpwd, newpwd);
		if(rowNum == 1) {
			response.setStatus(HttpStatus.OK.value());
			response.setCode(Constant.CODE_ONE);
			response.setMessage(Constant.UPDATE_PASSWORD_SUCESS);
		} else {
			response.setStatus(HttpStatus.OK.value());
			response.setCode(Constant.CODE_ZERO);
			response.setMessage(Constant.UPDATE_PASSWORD_FAIL);
		}
		return response;

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
			ipoMap.put("opendatetime", StringUtil.plainDate(ipoDetail.getOpenDateTime()));
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
			ipoMap.put("opendatetime", StringUtil.plainDate(ipoDetail.getOpenDateTime()));
			result.add(ipoMap);
		}

		return result;
	}
	
	/**
	 * Check whether the userName is existed
	 * @param userName String
	 * @return`check result
	 */
	private boolean isExistUser(String userName) {
		boolean isExistFlg = false;
		List<User> userList = userRepository.queryName(userName);
		if(userList.size()>0) {
			isExistFlg = true;
		}
		return isExistFlg;
	}
	
	/**
	 * Generate user id
	 * @return`next id
	 */
	private String generateUserId() {
		String currentId = Constant.EMPTY_STRING;
		String nextId = Constant.EMPTY_STRING;
		List<User> queryList = userRepository.queryMaxId();
		if(queryList.size()==1) {
			currentId = queryList.get(0).getId();
		}else{
			nextId = "U10000101";
		};

		if(!StringUtil.isNullOrEmpty(currentId)) {
			StringBuilder prefix =new StringBuilder(currentId.substring(0, 1));
			int id = Integer.valueOf(currentId.substring(1))+1;
			nextId = prefix.append(String.valueOf(id)).toString();
		}

		return nextId;
	}
}

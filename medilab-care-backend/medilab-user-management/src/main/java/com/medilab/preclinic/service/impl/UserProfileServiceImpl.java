package com.medilab.preclinic.service.impl;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.RandomAccess;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medilab.preclinic.bean.MedilabUserBean;
import com.medilab.preclinic.bean.UserRoleBean;
import com.medilab.preclinic.bean.UserTypeBean;
import com.medilab.preclinic.domain.MedilabUser;
import com.medilab.preclinic.domain.MedilabUserType;
import com.medilab.preclinic.domain.UserRole;
import com.medilab.preclinic.domain.VerificationCode;
import com.medilab.preclinic.repo.MedilabUserRepository;
import com.medilab.preclinic.repo.UserVerificationCodeRepo;
import com.medilab.preclinic.service.UserProfileService;
import com.medilab.preclinic.service.UserRoleService;
import com.medilab.preclinic.service.UserTypeService;
import com.medilab.preclinic.util.OutboundCommunicator;
import com.medilab.preclinic.util.ServiceUtil;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private MedilabUserRepository userRepo;
	
	@Autowired
	private UserRoleService roleService;
	
	@Autowired
	private UserTypeService userTypeService;
	
	@Autowired
	private UserVerificationCodeRepo codeVerifyRepo;
	
	@Override
	public MedilabUserBean createUser(MedilabUserBean userBean, String callbackUrl) {
		MedilabUser userModel = userRepo.save(mapBeanToDomain(userBean));
		JSONObject confirmAccountJsonBody = new JSONObject();
		if(userModel != null && userModel.getId() >0) {
			//Generate a Verification Code
			VerificationCode vcode = new VerificationCode();
			vcode.setCode(RandomStringUtils.randomAlphabetic(15));
			vcode.setCodeGenDate(new Date());
			vcode.setUser(userModel);
			codeVerifyRepo.save(vcode);
			//callbackUrl = callbackUrl+"/"+vcode.getCode();
			confirmAccountJsonBody.put("userVerifyCode", vcode.getCode());
		}
		
		//we have to call the notification-service to send an email
		
		confirmAccountJsonBody.put("from", "narsi.sandra@gmail.com");
		confirmAccountJsonBody.put("to", new String[] {userModel.getEmail()});
		confirmAccountJsonBody.put("emailTemplateType", "AccountConfirmation");
		confirmAccountJsonBody.put("callbackUrl", callbackUrl);
		OutboundCommunicator.sendConfirmAccountEmail(confirmAccountJsonBody.toString());
		return mapDomainToBean(userModel);
	}

	@Override
	public MedilabUserBean findUserById(String UserId) {
		return mapDomainToBean(userRepo.findById(Integer.valueOf(UserId)).get());

	}

	@Override
	public MedilabUserBean updateUser(MedilabUserBean userBean) {
		return mapDomainToBean(userRepo.save(mapBeanToDomain(userBean)));
	}

	@Override
	public List<MedilabUserBean> deleteUser(MedilabUserBean userBean) {
		userRepo.delete(mapBeanToDomain(userBean));
		return findAllUsers();
	}

	@Override
	public List<MedilabUserBean> deleteUser(String userId) {
		userRepo.deleteById(Integer.valueOf(userId));
		return findAllUsers();
	}

	@Override
	public MedilabUserBean provisioningUser(String userId, String roleId) {
		MedilabUser userDomain = userRepo.findById(Integer.valueOf(userId)).get();
		if(userDomain != null) {
			UserRole roleDomain = userDomain.getRole();
			//lets write some better way of checking the role equality later on time
			if(roleDomain != null && roleDomain.getId() != Integer.valueOf(roleId)) {
				
				UserRoleBean promotedRoleBean = roleService.findRoleById(roleId);
				userDomain.setRole(roleService.mapBeanToDomain(promotedRoleBean));
			}
		}
		return mapDomainToBean(userRepo.save(userDomain));
	}

	/**
	 * revoking a role to a user is called deprovisioning
	 */
	@Override
	public MedilabUserBean deProvisioningUser(String userId, String roleId) {
		MedilabUser userDomain = userRepo.findById(Integer.valueOf(userId)).get();
		if(userDomain != null) {
			UserRole roleDomain = userDomain.getRole();
			//lets write some better way of checking the role equality later on time
			if(roleDomain != null && roleDomain.getId() == Integer.valueOf(roleId)) {
				userDomain.setRole(null);
			}
		}
		return mapDomainToBean(userRepo.save(userDomain));
	}

	@Override
	public List<MedilabUserBean> findAllUsers() {
		List<MedilabUser> userDomainList = userRepo.findAll();
		List<MedilabUserBean> userBeanList = new ArrayList<>();
		if(userDomainList != null && userDomainList.size() >0) {
			userDomainList.stream().forEach(userDomain->{
				userBeanList.add(mapDomainToBean(userDomain));
			});
		}
		
		return userBeanList;
	}
	
	private MedilabUser mapBeanToDomain(MedilabUserBean userBean) {
		
		MedilabUser userDomain = new MedilabUser();
		BeanUtils.copyProperties(userBean, userDomain);
		
		String inputDob = userBean.getDob();
		try {
			userDomain.setDob(ServiceUtil.convertStringToDate(inputDob));
		} catch (ParseException e) {
			//Todo: convert to user definided exception later on time
			e.printStackTrace();
		}
		
		/**
		 * auto provisioning while creating user
		 */
		UserRoleBean userRoleBean = userBean.getRole();
		if(userRoleBean != null) {
			UserRole roleDomain = roleService.mapBeanToDomain(userRoleBean);
			userDomain.setRole(roleDomain);
		}
		
		UserTypeBean userTypeBean = userBean.getUserType();
		if(userTypeBean != null) {
			MedilabUserType userType = userTypeService.mapBeanToDomain(userTypeBean);
			userDomain.setUserType(userType);
		}
		
		return userDomain;
	}
	
	private MedilabUserBean mapDomainToBean(MedilabUser user) {
		MedilabUserBean userBean = new MedilabUserBean();
		BeanUtils.copyProperties(user, userBean);
		
		Date dobDate = user.getDob();
		if(dobDate != null) {
			String dob = dobDate.toString();
			userBean.setDob(dob);
		}
		
		
		UserRole userRoleDomain = user.getRole();
		if(userRoleDomain != null) {
			UserRoleBean roleBean = roleService.mapDomainToBean(userRoleDomain);
			userBean.setRole(roleBean);
		}
		
		MedilabUserType userTypeDomain = user.getUserType();
		if(userTypeDomain != null) {
			UserTypeBean userTypeBean = userTypeService.mapDomainToBean(userTypeDomain);
			userBean.setUserType(userTypeBean);
		}
		
		
		return userBean;
	}
	 
	public static void main(String[] args) {
		System.out.println(Math.random());
		System.out.println(RandomStringUtils.randomAlphabetic(15));
	}

	@Override
	public boolean verifyUser(String verificationCode) {
		boolean isCodeVrified = false;
		VerificationCode verificationDomain = codeVerifyRepo.findByVerificationCode(verificationCode);
		if(!verificationDomain.isCodeVerified()) {
			MedilabUser medilabUser = verificationDomain.getUser();
			if(medilabUser != null && medilabUser.getEmail() != null) {
				medilabUser.setAccountDisabled(false);
				medilabUser.setAccountLocked(false);
				verificationDomain.setCodeVerified(true);
				verificationDomain.setCodeActiveDate(new Date());
				verificationDomain = codeVerifyRepo.save(verificationDomain);
				if(verificationDomain.isCodeVerified()) {
					isCodeVrified = true;
				}
			}
		}else {
			//throw a user friendly exception message saying that
			//.isCodeVrifieduser is already verified
			isCodeVrified = true;
		}
		
		return isCodeVrified;
	}

}

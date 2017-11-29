package com.assismoraes.bank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.assismoraes.bank.helpers.ClientHelper;
import com.assismoraes.bank.helpers.UsrEditable;
import com.assismoraes.bank.models.Usr;
import com.assismoraes.bank.repos.AccountRepo;
import com.assismoraes.bank.repos.UserRepository;


@Service
public class UsrService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AccountRepo accountRepo;
	
	public Object saveUser(Usr user) {
		try {
			return this.repository.save(user);
		} catch (DataIntegrityViolationException e) {
			return e;
		}		
	}
	
	public Object updatePassword(String newPassword) {
		Usr usr = this.accountRepo.findByNumber(ClientHelper.loggedUser().getName()).getUsr();
		usr.setPassword(newPassword);
		return this.repository.save(usr);
	}
	
	public void verifyPasswords(UsrEditable usrEditable, Errors errors) {
		String currentPass = usrEditable.getCurrentPassword();
		String newPass = usrEditable.getNewPassword();
		String newPassConfirm = usrEditable.getNewPasswordConfirm();		
		if(currentPass != null && this.incorrectPassword(currentPass))
			errors.rejectValue("currentPassword", "currentPassword", "Senha atual incorreta");
		
		System.out.println(newPass + " - " + newPassConfirm);
		
		if(newPass != null && newPassConfirm != null && !newPass.equals(newPassConfirm))
			errors.rejectValue("newPasswordConfirm", "newPasswordConfirm", "A senha de confirmação está incorreta");
	}
	
	public Double getCurrentBalance() {
		return this.accountRepo.findByNumber(ClientHelper.loggedUser().getName()).getCurrentBalance();
	}
	
	private Boolean incorrectPassword(String password) {
		return !this.accountRepo.findByNumber(ClientHelper.loggedUser().getName()).getUsr().getPassword().equals(password);
	}	
}

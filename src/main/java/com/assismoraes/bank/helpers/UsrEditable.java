package com.assismoraes.bank.helpers;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class UsrEditable {
	
	@NotEmpty(message="A senha atual é obrigatória")
	@NotNull(message="A senha atual é obrigatória")
	private String currentPassword;
	
	@NotEmpty(message="A nova senha é obrigatória")
	@NotNull(message="A nova senha é obrigatória")
	private String newPassword;
	
	@NotEmpty(message="A confirmação da nova senha é obrigatória")
	@NotNull(message="A confirmação da nova senha é obrigatória")
	private String newPasswordConfirm;

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}
}
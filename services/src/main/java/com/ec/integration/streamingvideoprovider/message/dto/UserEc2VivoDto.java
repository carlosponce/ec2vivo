package com.ec.integration.streamingvideoprovider.message.dto;

import java.io.Serializable;

import com.ec.vivo.domain.UserEc2Vivo;


public class UserEc2VivoDto implements Serializable {

    /**
	 * Serial version
	 */
	private static final long serialVersionUID = -3725814952283823995L;
	
	private UserEc2Vivo  user;

	public UserEc2Vivo getUser() {
		return user;
	}

	public void setUser(UserEc2Vivo user) {
		this.user = user;
	}
    
    
}
package com.formation.projet7.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name="biblio-mailing", url="localhost:8080/mail")
public interface MicroServiceMail {

	@ResponseBody
	@RequestMapping("/sendSimpleEmail")
	public String sendSimpleEmail();

}

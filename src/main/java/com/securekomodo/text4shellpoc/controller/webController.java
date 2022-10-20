package com.securekomodo.text4shellpoc.controller;

import org.apache.commons.text.StringSubstitutor;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class webController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String index() {
		String response = "Text4Shell POC Test -@securekomodo" + "</br>"
						+ "Send payloads to /reflected?poc=yourpayload" + "</br>"
						+ "OR Send payloads to /blind with payload as your userAgent";
		return response;
	}

	@RequestMapping(value = "/reflected", method = RequestMethod.GET)
	@ResponseBody
	public String reflected(@RequestParam(defaultValue="") String poc) {
		StringSubstitutor interpolator = StringSubstitutor.createInterpolator();
		try{
			String vuln = interpolator.replace(poc);
		} catch(Exception e) {
			System.out.println(e);
		}
		return "Result: " + poc;
	}

	@RequestMapping(value = "/blind", method = RequestMethod.GET)
	@ResponseBody
	public String blind(@RequestHeader(value = "User-Agent") String userAgent) {
		StringSubstitutor interpolator = StringSubstitutor.createInterpolator();
		try{
			String vuln = interpolator.replace(userAgent);
		} catch(Exception e) {
			System.out.println(e);
		}
		return "Result: Thank you, your userAgent has been processed";
	}


}
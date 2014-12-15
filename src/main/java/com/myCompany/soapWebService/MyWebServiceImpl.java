package com.myCompany.soapWebService;

import javax.jws.WebService;

import com.myCompany.application.MyService;
import com.myCompany.application.MyServiceImpl;

@WebService(endpointInterface = "com.myCompany.soapWebService.MyWebService")
public class MyWebServiceImpl implements MyWebService{

	MyService myService;
	
	public MyService getMyService() {
		return myService;
	}

	public void setMyService(MyService myService) {
		this.myService = myService;
	}

	@Override
	public String methode() {
		try {
			return myService.methode();
		} catch (Exception e) {
			e.printStackTrace();
			return "Erreur : " + e.getLocalizedMessage();
		}
	}

}

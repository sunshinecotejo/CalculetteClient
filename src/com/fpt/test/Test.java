package com.fpt.test;


import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.fpt.service.CalculetteService;
import com.fpt.service.CalculetteServiceImpl;

public class Test {

	
	public static void main(String[] args) {
		
		CalculetteService calculette = null;
	
		try {
			Properties properties = new Properties();
			properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			
			/*
			 * Remote naming project
			 **/
			//properties.put(Context.INITIAL_CONTEXT_FACTORY,"org.jboss.naming.remote.client.InitialContextFactory");
			//properties.put("java.naming.provider.url","http-remoting://localhost:8080");
			//properties.setProperty("java.naming.provider.url","remote://localhost:4447");
			//properties.put("jboss.naming.client.ejb.context", "true");
			//properties.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
			
			Context context = new InitialContext(properties);
			System.out.println("done context");
			
			
			//calculette = (CalculetteService) context.lookup("/Calculette/CalculetteServiceImpl!com.fpt.service.CalculetteService");
			/*
			 * EJB Client Library
			 * jboss-ejb-client.properties
			 */
			
			calculette = (CalculetteService) context.lookup("ejb:/Calculette//CalculetteServiceImpl!com.fpt.service.CalculetteService");
			
			
			System.out.println("La somme est:"+calculette.add(10, 10));
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
		
	}
	
	  private static String getLookupName() {
		 
          String appName = "";
          String moduleName = "Calculette";
          String distinctName = "";
   
          // The EJB bean implementation class name
          String beanName = CalculetteServiceImpl.class.getSimpleName();
   
          // Fully qualified remote interface name
          final String interfaceName = CalculetteService.class.getName();
   
          // Create a look up string name
          String name = "ejb:" + appName + "/" + moduleName + "/" + 
              distinctName    + "/" + beanName + "!" + interfaceName;
   
          return name;
	}
}



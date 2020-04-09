package com.brightstar.plibmobi.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public final class XMLUtil {
	
	public static String toXML(Object bean){
		
		XStream xstream = new  XStream(new DomDriver());
		if(bean != null){
			xstream.alias(bean.getClass().getSimpleName(), bean.getClass());		
			return xstream.toXML(bean);
		}
		else 
			return null;
		
	}
	
	
	
}

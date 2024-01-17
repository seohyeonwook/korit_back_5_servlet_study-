package com.study.servlet_study.utils;//그냥 매개변수로 넣어서 클래스에서 한번에 하자

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ParamsConvert {
	
	public static Map<String, String> convertParamsMapToMap (Map < String, String[]>paramsMap) {
			Map<String, String> map = new HashMap<>();//복붙 해라
    	
			paramsMap.forEach((k, v) -> {
    		
    	
    		StringBuilder builder = new StringBuilder();
    		
    		Arrays.asList(v).forEach(value -> builder.append(value));
    		
    		map.put(k, builder.toString());
    	});
			
			return map;
	}

}

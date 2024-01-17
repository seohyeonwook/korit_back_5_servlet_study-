package com.study.servlet_study.servlet;//2번째

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.utils.ParamsConvert;

@WebServlet("/http")
public class HttpStudyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HttpStudyServlet() {//생성자
        super();
    }
    // 이렇게 4개를 Http메소드라고 부름 - 어떤요청을 보내느냐에 따라서 각각 다르다.
    // POST요청   -> C reate(추가)
    // GET요청    -> R ead(조회)//get요청외에 body사용
    // PUT요청    -> U pdate(수정)
    // DELETE요청 -> D elete(삭제)
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//request.setCharacterEncoding("utf-8");//요청때또 해쪼야한따포쓰트요청떄만
    	//post는 body
    	
    	Map<String, String> paramsMap = new HashMap<>();
    	
    	request.getParameterMap().forEach((k, v) -> {//k값하나랑 v= String 배열
    		
    	
    		StringBuilder builder = new StringBuilder();
    		
    		Arrays.asList(v).forEach(value -> builder.append(value));//배열을 Sting List로 바꿔준다.
    		
    		paramsMap.put(k, builder.toString());
    	});
    	
    	
    		System.out.println(paramsMap);
    		
    		System.out.println(paramsMap);
    		System.out.println(request.getParameter("name"));
    		
    		Map<String, String> paramsMap2 = new HashMap<>();
    		Iterator<String> ir = request.getParameterNames().asIterator();
    		while(ir.hasNext()) {
    			String key = ir.next();
    			paramsMap2.put(key, request.getParameter(key));
    		}
    		
    		
    		
    		String nameParams = request.getParameter("name");
    		String phoneParams = request.getParameter("phone");
    		String emailParams = request.getParameter("email");
    		String addressParams = request.getParameter("address");
    	
		
		
		
		//요청과 응답 각각 header와 body가있다
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> paramsMap = ParamsConvert.convertParamsMapToMap(request.getParameterMap());
		
		//GET은 Params에서 // 주소창에 입력된다
//		Map<String, String> paramsMap = new HashMap<>();//복붙 해라 이부분 static으로 만든다
//    	
//    	request.getParameterMap().forEach((k, v) -> {
//    		
//    	
//    		StringBuilder builder = new StringBuilder();
//    		
//    		Arrays.asList(v).forEach(value -> builder.append(value));
//    		
//    		paramsMap.put(k, builder.toString()); 
//    	});
    	System.out.println(paramsMap);
	}

	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	

}

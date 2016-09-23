package com.myhome.common.filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.web.util.HtmlUtils;

import com.myhome.utils.Tools;
public class XSSRequestWrapper extends HttpServletRequestWrapper {

    public XSSRequestWrapper(HttpServletRequest request) {  
        super(request);  
    }  
  
    @Override  
    public String getHeader(String name) {  
        return StringEscapeUtils.escapeHtml4(super.getHeader(name));  
    }  
  
    @Override  
    public String getQueryString() {  
        return StringEscapeUtils.escapeHtml4(super.getQueryString());  
    }  
  
    @Override  
    public String getParameter(String name) {  
       String rename=  StringEscapeUtils.escapeHtml4(super.getParameter(name));
       if(rename!=null&&rename.length()>0&&!rename.trim().equals("")){
            rename=rename.replaceAll("&hellip;", "...").replaceAll("&mdash;", "—").replaceAll("\t", " ").replaceAll("&lt;", "《").replaceAll("&gt;", "》").replaceAll("&amp;", "&").replaceAll("&rsquo;", "'").replaceAll("&quot;", "'").replaceAll("'&lsquo;", "'").replaceAll("&rdquo;", "'").replaceAll("&ldquo;", "'").replaceAll("&lsquo;", "'").replaceAll("&middot;", "·");
            return rename;
       }else{
           return rename;
       }
         
    }  
  
    @Override  
    public String[] getParameterValues(String name) {  
        String[] values = super.getParameterValues(name);  
        if(values != null) {  
            int length = values.length;  
            String[] escapseValues = new String[length];  
            for(int i = 0; i < length; i++){  
                escapseValues[i] = StringEscapeUtils.escapeHtml4(values[i]);  
                escapseValues[i]=escapseValues[i].toString().replaceAll("&hellip;", "...").replaceAll("&mdash;", "—").replaceAll("\t", " ").replaceAll("\t", " ").replaceAll("&lt;", "《").replaceAll("&gt;", "》").replaceAll("&amp;", "&").replaceAll("&rsquo;", "'").replaceAll("&quot;", "'").replaceAll("'&lsquo;", "'").replaceAll("&rdquo;", "'").replaceAll("&ldquo;", "'").replaceAll("&lsquo;", "'").replaceAll("&yen;", "￥");
            }  
            return escapseValues;  
        }  
        return super.getParameterValues(name);  
    }  
      
}  
//    public XSSRequestWrapper(HttpServletRequest servletRequest) {
//        super(servletRequest);
//    }
//    @Override
//    public String[] getParameterValues(String parameter) {
//        String[] values = super.getParameterValues(parameter);
//        if (values == null) {
//            return null;
//        }
//        int count = values.length;
//        String[] encodedValues = new String[count];
//        for (int i = 0; i < count; i++) {
//            encodedValues[i] = stripXSS(values[i]);
//          //  encodedValues[i].toString().replaceAll("&lt;", "<").replaceAll("&gt;", ">");
//          //encodedValues[i].replace("&lt;", "<");
//            encodedValues[i]=encodedValues[i].toString().replaceAll("&lt;", "").replaceAll("&gt;", "").replaceAll("&amp;", "&").replaceAll("&quot", "\\");
//            //System.out.println( encodedValues[i].toString().replaceAll("&lt;", "<").replaceAll("&gt;", ">"));
//        }
//      
//        return   encodedValues;
//    }
//    @Override
//    public String getParameter(String parameter) {
//        String value = super.getParameter(parameter);
//        
//        String valueRetrun = stripXSS(value);
//        if(!Tools.isEmpty(valueRetrun)){
//        	 valueRetrun=valueRetrun.toString().replaceAll("&lt;", "").replaceAll("&gt;", "").replaceAll("&amp;", "&").replaceAll("&quot;", "");
//        }
//        return  valueRetrun;
//    }
//    @Override
//    public String getHeader(String name) {
//        String value = super.getHeader(name);
//        return stripXSS(value);
//    }
//    private String stripXSS(String value) {
//        if (value != null) {
//        	value=HtmlUtils.htmlEscape(value);
//        }
//        
//        return value;
//    }
//    
//    
//    public static void main(String[] args) {
//    	String s = "zhangsan,lisi,wangwu";
//    	  String[] arr = s.split(",");
//    	  for(int x=0; x<arr.length; x++)
//    	  {
//    	   sop(arr[x]);
//    	  }
//    }
//    public static void sop(Object obj)
//    {
//     System.out.println(obj);
//    }
//}
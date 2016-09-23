package com.myhome.html5.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.myhome.common.PublicConstants;
import com.myhome.entity.WithPicture;
import com.myhome.utils.ReturnData;

/**
 * 生活照
 * 
 * @author gwb
 * @version $Id: WithPictureController.java, v 0.1 2015年10月17日 上午11:59:52 gwb Exp $
 */
@Controller("H5WithPictureController")
@RequestMapping(value = "/H5/WithPicture", produces = "text/html;charset=UTF-8")
public class WithPictureController extends AbstractController {

    private static Logger logger = LoggerFactory.getLogger(WithPictureController.class);

    /**
     * 新增
     * 
     * @param withPicture
     * @return
     */
    @RequestMapping("/addMobileWithPicture")
    @ResponseBody
    public String addMobileWithPicture(HttpServletRequest request, HttpServletResponse response,
                                       WithPicture withPicture) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            super.getWithPictureService().addMobileWithPicture(withPicture);
            json.put("withPicture", withPicture);
            returnData.setData(json);
        } catch (Exception e) {
            logger.error("", e);
            returnData.setMsg("新增WithPicture 信息失败");
            returnData.setReturnCode(PublicConstants.ADD_ERROR);
            returnData.setSuccess(false);
        }
        return returnData.toJSon(returnData);
    }

    @RequestMapping("/getMobileWithPicture")
    @ResponseBody
    public String getMobileWithPicture(HttpServletRequest request, HttpServletResponse response,
                                       WithPicture withPicture) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            WithPicture wh = super.getWithPictureService().getMobileWithPicture(withPicture);
            json.put("withPicture", wh);
            json.put("user", wh.getUser());
            returnData.setData(json);
        } catch (Exception e) {
            logger.error("", e);
            returnData.setMsg("查询WithPicture 信息失败");
            returnData.setReturnCode(PublicConstants.ADD_ERROR);
            returnData.setSuccess(false);
        }
        return returnData.toJSon(returnData);
    }

    @RequestMapping("/updateMobileWithPicture")
    @ResponseBody
    public String updateMobileWithPicture(HttpServletRequest request, HttpServletResponse response,
                                          WithPicture withPicture) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            WithPicture wh = super.getWithPictureService().updateMobileWithPicture(withPicture);
            json.put("withPicture", wh);
            json.put("user", wh.getUser());
            returnData.setData(json);
        } catch (Exception e) {
            logger.error("", e);
            returnData.setMsg("修改WithPicture 信息失败");
            returnData.setReturnCode(PublicConstants.ADD_ERROR);
            returnData.setSuccess(false);
        }
        return returnData.toJSon(returnData);
    }

    @RequestMapping("/deleteMobileWithPicture")
    @ResponseBody
    public String deleteMobileWithPicture(HttpServletRequest request, HttpServletResponse response,
                                          WithPicture withPicture) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            super.getWithPictureService().deleteMobileWithPicture(withPicture);
        } catch (Exception e) {
            logger.error("", e);
            returnData.setMsg("新增WithPicture 信息失败");
            returnData.setReturnCode(PublicConstants.ADD_ERROR);
            returnData.setSuccess(false);
        }
        return returnData.toJSon(returnData);
    }

    @RequestMapping("/getMobileWithPictureList")
    @ResponseBody
    public String getMobileWithPictureList(HttpServletRequest request,
                                           HttpServletResponse response, WithPicture withPicture) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        try {
            List<WithPicture> whList = super.getWithPictureService().getMobileWithPictureList(
                withPicture);
            json.put("withPictureList", whList);
            returnData.setData(json);
        } catch (Exception e) {
            logger.error("", e);
            returnData.setMsg("新增WithPicture 信息失败");
            returnData.setReturnCode(PublicConstants.ADD_ERROR);
            returnData.setSuccess(false);
        }
        return returnData.toJSon(returnData);
    }

//    @RequestMapping("/pictureToBase64")
//    @ResponseBody
//    public String pictureToBase64(HttpServletRequest request, HttpServletResponse response,
//                                  String picture) {
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        ReturnData returnData = new ReturnData();
//        JSONObject json = new JSONObject();
//        if (null == picture || "".equals(picture)) {
//            returnData.setSuccess(false);
//            ;
//            returnData.setMsg("图片不能为空");
//            return returnData.toString();
//        }
//
//        //图片转化成base64字符串  
//        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
//        InputStream in = null;
//        byte[] data = null;
//        //读取图片字节数组  
//        try {
//            in = new FileInputStream(picture);
//            data = new byte[in.available()];
//            in.read(data);
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //对字节数组Base64编码  
//        json.put("base64", new sun.misc.BASE64Encoder().encode(data));
//        returnData.setData(json);
//        return returnData.toJSon(returnData);
//    }

    
//    @RequestMapping("/fileUrlToBase64")
//    @ResponseBody
//    public String saveUrlAs(HttpServletResponse response,String fileUrl)/*fileUrl网络资源地址*/
//    {
//        
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        ReturnData returnData = new ReturnData();
//        JSONObject json = new JSONObject();
//        byte[] data = null;
//        try {
//            URL url = new URL(fileUrl);/*将网络资源地址传给,即赋值给url*/
//            /*此为联系获得网络资源的固定格式用法，以便后面的in变量获得url截取网络资源的输入流*/
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            DataInputStream in = new DataInputStream(connection.getInputStream());
//            data = new byte[in.available()];
//            in.read(data);
//            in.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //对字节数组Base64编码  
//        json.put("base64", new sun.misc.BASE64Encoder().encode(data));
//        returnData.setData(json);
//        System.out.print(json);
//        
//        return returnData.toJSon(returnData);
//    }
    /**
     * 将网络图片进行Base64位编码
     * 
     * @param imgUrl
     *            图片的url路径，如http://.....xx.jpg
     * @return
     */
    @RequestMapping("/fileUrlToBase64")
    @ResponseBody
    public static String encodeImgageToBase64(HttpServletResponse response, URL fileUrl) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        response.addHeader("Access-Control-Allow-Origin", "*");
        ReturnData returnData = new ReturnData();
        JSONObject json = new JSONObject();
        ByteArrayOutputStream outputStream = null;
        try {
            String url = fileUrl.toString();
            url = url.substring(url.lastIndexOf(".")+1);
            BufferedImage bufferedImage = ImageIO.read(fileUrl);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, url, outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        //      BASE64Encoder encoder = new BASE64Encoder();
        json.put("base64", new sun.misc.BASE64Encoder().encode(outputStream.toByteArray()));
        returnData.setData(json);
        return returnData.toJSon(returnData);

        //      return encoder.encode(outputStream.toByteArray());// 返回Base64编码过的字节数组字符串
        //      return "";
    }
}

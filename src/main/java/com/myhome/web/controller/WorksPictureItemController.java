package com.myhome.web.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.myhome.entity.UserInfo;
import com.myhome.entity.WorksPictureItem;
import com.myhome.utils.BaseCodeParam;
import com.myhome.utils.CutImage;
import com.myhome.utils.ImageUtil;
import com.myhome.utils.PicturePath;
import com.myhome.utils.ResultData;
import com.myhome.utils.SysConstants;



@Controller
@RequestMapping(value = "/works/picture/item", produces = "text/html;charset=UTF-8")
public class WorksPictureItemController extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(WorksPictureItemController.class);
    @RequestMapping("/test")
    public String  test() {
        return "/test";
    }

    @RequestMapping("/get")
    public WorksPictureItem get(@RequestParam("id") Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    /**
     * 图片上传功能
     * @param request
     * @param response
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String add(
            HttpServletRequest request,HttpServletResponse response,
            @RequestParam(value = "file", required = false) MultipartFile file){
        response.addHeader("Access-Control-Allow-Origin", "*");
    	Long start=System.currentTimeMillis();
    	
        ResultData data = new ResultData();
        try {
            response.addHeader("Access-Control-Allow-Origin", "*");
            String type = request.getParameter("typeimg");
            Date date = new Date();
            String str = String.valueOf(date.getTime());
            String filename = str +  file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            if(type.equals("1")){//艺术家图片上传
                File targetFileArtist = new File(PicturePath.artist, filename);
                if (!targetFileArtist.exists()) {
                    targetFileArtist.mkdirs();
                }
                file.transferTo(targetFileArtist);
                data.setOrginpath(BaseCodeParam.getObject(BaseCodeParam.imageUrl)+"/artist/thumb_"+filename);
                data.setPath(filename);//缩略图
                new ImageUtil().thumbnailImage(PicturePath.artist+"/"+filename, 600, 600);//生成缩略图
            }else if(type.equals("0")){//作品蹄片上传
                File targetFileWorks = new File(PicturePath.works, filename);
                if (!targetFileWorks.exists()) {
                    targetFileWorks.mkdirs();
                }
                file.transferTo(targetFileWorks);
                data.setOrginpath(BaseCodeParam.getObject(BaseCodeParam.imageUrl)+"/works/thumb_"+filename);
                data.setPath(filename);
                new ImageUtil().thumbnailImage(PicturePath.works+"/"+filename, 600, 600);//生成缩略图
            }else if(type.equals("2")){//教师证书图片
                File targetFileWorks = new File(PicturePath.teacher, filename);
                if (!targetFileWorks.exists()) {
                    targetFileWorks.mkdirs();
                }
                file.transferTo(targetFileWorks);
                data.setOrginpath(BaseCodeParam.getObject(BaseCodeParam.imageUrl)+"/teacher/thumb_"+filename);
                data.setPath(filename);
                new ImageUtil().thumbnailImage(PicturePath.teacher+"/"+filename, 600, 600);//生成缩略图
            }else if(type.equals("3")){//幸运小朋友头像
                File targetFileWorks = new File(PicturePath.helpchild, filename);
                if (!targetFileWorks.exists()) {
                    targetFileWorks.mkdirs();
                }
                file.transferTo(targetFileWorks);
                data.setOrginpath(BaseCodeParam.getObject(BaseCodeParam.imageUrl)+"/helpchild/thumb_"+filename);
                data.setPath(filename);
                new ImageUtil().thumbnailImage(PicturePath.helpchild+"/"+filename, 600, 600);//生成缩略图
                
                File filesss=new File("/home/goujia/project/html/test_store/myhome/helpchild/thumb_"+filename);
                
                if(filesss.exists()){
                	logger.error("-----------------------filesss"+filesss+"存在");
                }else{
                	logger.error("-----------------------filesss"+filesss+"不存在");
                }
                
            }else if(type.equals("4")){//幸运小朋友附加图片
                File targetFileWorks = new File(PicturePath.helppic, filename);
                if (!targetFileWorks.exists()) {
                    targetFileWorks.mkdirs();
                }
                file.transferTo(targetFileWorks);
                data.setOrginpath(BaseCodeParam.getObject(BaseCodeParam.imageUrl)+"/helppic/thumb_"+filename);
                data.setPath(filename);
                new ImageUtil().thumbnailImage(PicturePath.helppic+"/"+filename, 600, 600);//生成缩略图
            }else if(type.equals("5")){//公司logo
                File targetFileWorks = new File(PicturePath.comlogo, filename);
                if (!targetFileWorks.exists()) {
                    targetFileWorks.mkdirs();
                }
                file.transferTo(targetFileWorks);
                data.setOrginpath(BaseCodeParam.getObject(BaseCodeParam.imageUrl)+"/comlogo/thumb_"+filename);
                data.setPath(filename);
                new ImageUtil().thumbnailImage(PicturePath.comlogo+"/"+filename, 600, 600);//生成缩略图
            }else if(type.equals("6")){//公司营业执照
                File targetFileWorks = new File(PicturePath.comlicence, filename);
                if (!targetFileWorks.exists()) {
                    targetFileWorks.mkdirs();
                }
                file.transferTo(targetFileWorks);
                data.setOrginpath(BaseCodeParam.getObject(BaseCodeParam.imageUrl)+"/comlicence/thumb_"+filename);
                data.setPath(filename);
                new ImageUtil().thumbnailImage(PicturePath.comlicence+"/"+filename, 600, 600);//生成缩略图
            }else if(type.equals("7")){//公司证书文件
                File targetFileWorks = new File(PicturePath.comfile, filename);
                if (!targetFileWorks.exists()) {
                    targetFileWorks.mkdirs();
                }
                file.transferTo(targetFileWorks);
                data.setOrginpath(BaseCodeParam.getObject(BaseCodeParam.imageUrl)+"/comfile/thumb_"+filename);
                data.setPath(filename);
                new ImageUtil().thumbnailImage(PicturePath.comfile+"/"+filename, 600, 600);//生成缩略图
            }else if(type.equals("8")){//老师照片
                File targetFileWorks = new File(PicturePath.teacherphoto, filename);
                if (!targetFileWorks.exists()) {
                    targetFileWorks.mkdirs();
                }
                file.transferTo(targetFileWorks);
                data.setOrginpath(BaseCodeParam.getObject(BaseCodeParam.imageUrl)+"/teacherphoto/thumb_"+filename);
                data.setPath(filename);
                new ImageUtil().thumbnailImage(PicturePath.teacherphoto+"/"+filename, 600, 600);//生成缩略图
            } else if(type.equals("9")){//阳台照片
                File targetFileWorks = new File(PicturePath.myBalcony, filename);
                if (!targetFileWorks.exists()) {
                    targetFileWorks.mkdirs();
                }
                file.transferTo(targetFileWorks);
                data.setOrginpath(BaseCodeParam.getObject(BaseCodeParam.imageUrl)+"/myBalcony/thumb_"+filename);
                data.setPath(filename);
                new ImageUtil().thumbnailImage(PicturePath.myBalcony+"/"+filename, 750, 750);//生成缩略图
            } else if (type.equals("10")) {// 法人身份证(正面)
                File targetFileWorks = new File(PicturePath.comidCodePositive, filename);
                if (!targetFileWorks.exists()) {
                    targetFileWorks.mkdirs();
                }
                file.transferTo(targetFileWorks);
                data.setOrginpath(BaseCodeParam.getObject(BaseCodeParam.imageUrl)+"/comidCodePositive/thumb_"+filename);
                data.setPath(filename);
                new ImageUtil().thumbnailImage(PicturePath.comidCodePositive+"/"+filename, 600, 600);//生成缩略图
            } else if (type.equals("11")){// 法人身份证(反面)
                File targetFileWorks = new File(PicturePath.comidCodeBack, filename);
                if (!targetFileWorks.exists()) {
                    targetFileWorks.mkdirs();
                }
                file.transferTo(targetFileWorks);
                data.setOrginpath(BaseCodeParam.getObject(BaseCodeParam.imageUrl)+"/comidCodeBack/thumb_"+filename);
                data.setPath(filename);
                new ImageUtil().thumbnailImage(PicturePath.comidCodeBack+"/"+filename, 600, 600);//生成缩略图
            }
        }catch(Exception e){
        	logger.error(e.getMessage());
            e.printStackTrace();
            data.setMsg(e.toString());
            data.setSuccess(false);
            data.setResult(false);
        }
        Long end=System.currentTimeMillis();
        
        logger.info("-----------------------------------sssssss"+(end-start));
        
        return data.toString();
    }
    /**
     * 图片剪切接口 
     * 参数： x y w h src type 
     * @param request
     * @param response
     * @param file
     * @return
     */
    @RequestMapping("/cut")
    @ResponseBody
    public String cut(
            HttpServletRequest request,HttpServletResponse response,
            @RequestParam(value = "file", required = false) MultipartFile file){
        ResultData data = new ResultData();
        try {
            response.addHeader("Access-Control-Allow-Origin", "*");
            Integer userid =Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
            Integer x = Integer.valueOf(request.getParameter("x"));
            Integer y = Integer.valueOf(request.getParameter("y"));
            Integer w = Integer.valueOf(request.getParameter("w"));
            Integer h = Integer.valueOf(request.getParameter("h"));
            Integer width = Integer.valueOf(request.getParameter("width"));
            
            Date date = new Date();
            String str = String.valueOf(date.getTime());
            String filename = str +  file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
                File targetFileWorks = new File(PicturePath.user, filename);
                if (!targetFileWorks.exists()) {
                    targetFileWorks.mkdirs();
                }
                file.transferTo(targetFileWorks);
                String img = BaseCodeParam.getObject(BaseCodeParam.imageUrl)+"/user/thumb_"+filename;//缩略图
                String originImg = BaseCodeParam.getObject(BaseCodeParam.imageUrl)+"/user/"+filename;//缩略图
                data.setPath(filename);
                BufferedImage sourceImg =ImageIO.read(new FileInputStream(targetFileWorks));  
                 x= sourceImg.getWidth()*x/width;
                 y= sourceImg.getWidth()*y/width;
                 w= sourceImg.getWidth()*w/width;
                 h= sourceImg.getWidth()*h/width;
                CutImage.cutImage(PicturePath.user+"/"+filename, PicturePath.user+"/thumb_"+filename, x, y, w, h);
                UserInfo  userinfo = getUserInfoService().get(Long.valueOf(userid));
                userinfo.setImg(img);
                userinfo.setOriginImg(originImg);
                getUserInfoService().update(userinfo);
                data.setPath(img);
        }catch(Exception e){
        	logger.error(e.getMessage());
            e.printStackTrace();
            data.setMsg(e.toString());
            data.setSuccess(false);
            data.setResult(false);
        }
        return data.toString();
    }
    /**
     * 图片剪切接口 
     * 参数： x y w h src type 
     * @param request
     * @param response
     * @param file
     * @return
     */
    @RequestMapping("/workscut")
    @ResponseBody
    public String workscut(
            HttpServletRequest request,HttpServletResponse response,
            @RequestParam(value = "file", required = false) MultipartFile file){
        ResultData data = new ResultData();
        try {
            response.addHeader("Access-Control-Allow-Origin", "*");
            Integer userid =Integer.valueOf(request.getSession().getAttribute(SysConstants.USER_ID).toString());
            Integer x = Integer.valueOf(request.getParameter("x"));
            Integer y = Integer.valueOf(request.getParameter("y"));
            Integer w = Integer.valueOf(request.getParameter("w"));
            Integer h = Integer.valueOf(request.getParameter("h"));
            Integer width = Integer.valueOf(request.getParameter("width"));
            
            Date date = new Date();
            String str = String.valueOf(date.getTime());
            String filename = str +  file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
                File targetFileWorks = new File(PicturePath.user, filename);
                if (!targetFileWorks.exists()) {
                    targetFileWorks.mkdirs();
                }
                file.transferTo(targetFileWorks);
                String img = BaseCodeParam.getObject(BaseCodeParam.imageUrl)+"/user/thumb_"+filename;//缩略图
                String originImg = BaseCodeParam.getObject(BaseCodeParam.imageUrl)+"/user/"+filename;//缩略图
                data.setPath(filename);
                BufferedImage sourceImg =ImageIO.read(new FileInputStream(targetFileWorks));  
                 x= sourceImg.getWidth()*x/width;
                 y= sourceImg.getWidth()*y/width;
                 w= sourceImg.getWidth()*w/width;
                 h= sourceImg.getWidth()*h/width;
                CutImage.cutImage(PicturePath.user+"/"+filename, PicturePath.user+"/thumb_"+filename, x, y, w, h);
                Long picId = getWorksService().addImg(filename);
                data.setPath(img);
                data.setPicId(picId);
        }catch(Exception e){
        	logger.error(e.getMessage());
            e.printStackTrace();
            data.setMsg(e.toString());
            data.setSuccess(false);
            data.setResult(false);
        }
        return data.toString();
    }
    @RequestMapping("/update")
    public int update(@ModelAttribute("worksPictureItem") WorksPictureItem worksPictureItem) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    @RequestMapping("/remove")
    public int remove(@RequestParam("id") Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    @RequestMapping("/delete")
    public int delete(@RequestParam("id") Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }

}

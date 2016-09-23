package com.myhome.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.myhome.entity.Artist;
import com.myhome.entity.ArtistInfo;
import com.myhome.entity.Authentication;
import com.myhome.entity.Game;
import com.myhome.entity.GameWorksItem;
import com.myhome.entity.LoginAuthentication;
import com.myhome.entity.MobileAuthentication;
import com.myhome.entity.Picture;
import com.myhome.entity.Region;
import com.myhome.entity.Tag;
import com.myhome.entity.User;
import com.myhome.entity.UserInfo;
import com.myhome.entity.Works;
import com.myhome.entity.WorksPictureItem;
import com.myhome.entity.WorksTagItem;
import com.myhome.entity.vo.ImpWorks;
import com.myhome.utils.BaseCodeParam;
import com.myhome.utils.CommonUtils;
import com.myhome.utils.CompressFile;
import com.myhome.utils.DateUtil;
import com.myhome.utils.ImageUtil;
import com.myhome.utils.PicturePath;
import com.myhome.utils.RandomDataUtil;
import com.myhome.utils.ResultData;
import com.myhome.utils.SysConstants;
import com.myhome.utils.UtilMD5;
import com.myhome.utils.UtilMD5Encoder;

@Controller
@RequestMapping(value = "/artist/upload", produces = "text/html;charset=UTF-8")
public class ArtistUploadController extends AbstractController {

    public static void main(String[] args) throws ParseException {
        //		File ee = new File("D:\\Users\\20150107\\myhome_web2\\src\\main\\webapp\\temp\\445944\\guantou4792.jpg");
        //		File ss = new File("D:\\Users\\20150107\\myhome_web2\\src\\main\\webapp\\artist\\443947145275948515212.jpg");
        //		copyImg(ee ,ss);
        boolean s = false;
        boolean b = false;
        if (!s && b) {
            System.out.println(1);
        } else {
            System.out.println(2);
        }
    }

    /**
     * 批量导入Import
     */
    @RequestMapping("/Import")
    @ResponseBody
    public String Import(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam(value = "file", required = false) MultipartFile file,
                         Model model) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        ResultData data = new ResultData();
        try {
            String fileType = file.getOriginalFilename().substring(
                file.getOriginalFilename().lastIndexOf(".") + 1,
                file.getOriginalFilename().length());
            CommonsMultipartFile cf = (CommonsMultipartFile) file; //这个myfile是MultipartFile的
            DiskFileItem fi = (DiskFileItem) cf.getFileItem();
            FileInputStream is = new FileInputStream(fi.getStoreLocation()); //获取文件  
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
            Workbook wb = null;
            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook(is);
            } else if (fileType.equals("xlsx")) {
                wb = new XSSFWorkbook(is);
            } else {
                System.out.println("您输入的excel格式不正确");
                data.setSuccess(false);
                data.setMsg("请按照模板导入数据！");
                return data.toString();
            }
            int sheetNum = wb.getNumberOfSheets(); //获取sheet
            for (int i = 0; i < sheetNum; i++) {
                Sheet childSheet = wb.getSheetAt(i);
                int rowNum = childSheet.getLastRowNum(); //获取当前sheet有多少行
                List<ImpWorks> impWorks = new ArrayList<ImpWorks>();
                if (rowNum > 0) {
                    List<String> msgs = new ArrayList<String>();
                    for (int j = 4; j < rowNum + 1; j++) {
                        if (!childSheet.getRow(0).getCell(0).toString().trim().equals("画家批量导入模板")) {
                            data.setSuccess(false);
                            data.setMsg("请导入正确的excle表格！");
                            break;
                        } else if (childSheet.getRow(35) != null
                                   && !childSheet.getRow(35).getCell(0).toString().equals("")) {
                            data.setSuccess(false);
                            data.setMsg("一次最多上传30个用户！");
                            break;
                        }
                        Row row = childSheet.getRow(j);
                        int cellNum = row.getLastCellNum(); //每行有多少列(格)
                        if ((!row.getCell(0).toString().equals(""))
                            && (!row.getCell(1).toString().equals("")) && cellNum > 14) {
                            ImpWorks impWork = new ImpWorks();
                            String msg = "";
                            impWork.setMobile(changedData(row.getCell(1).toString().trim()));//手机号码
                            if (numHave(impWork.getMobile())) {
                                msg = msg + "手机号码已被注册!";
                            }
                            impWork.setUsername(row.getCell(2).toString().trim().equals("") ? null : row
                                .getCell(2).toString().trim());//用户名
                            if (impWork.getUsername() == null) {
                                msg = msg + "用户名未填!";
                            } else {
                                if (numName(impWork.getUsername())) {
                                    msg = msg + "用户名已被注册!";
                                }
                            }
                            if (row.getCell(3).toString().trim().equals("")) {
                                msg = msg + "姓名未填!";
                            } else {
                                impWork.setName(row.getCell(3).toString().trim());//名称
                            }
                            for (int k = 0; k < impWorks.size(); k++) {
                                if (impWorks.get(k).getMobile() != null
                                    && impWorks.get(k).getMobile()
                                        .equals(changedData(row.getCell(1).toString().trim()))) {
                                    msg = msg + "手机号码重复!";
                                    break;
                                }
                                if (impWorks.get(k).getUsername() != null
                                    && impWorks.get(k).getUsername()
                                        .equals(row.getCell(2).toString().trim())) {
                                    msg = msg + "用户名重复!";
                                    break;
                                }
                            }
                            if (row.getCell(4).toString().trim().equals("")) {
                                msg = msg + "性别未填!";
                            } else {
                                impWork.setSex(row.getCell(4).toString().trim());//性别
                            }
                            if (row.getCell(5).toString().trim().equals("")) {
                                msg = msg + "生日未填!";
                            } else {
                            	System.out.println(row.getCell(5).toString().trim());
                                impWork.setBirthday(sdf.parse(row.getCell(5).toString().trim()));//生日
                            }
                            if(!row.getCell(6).toString().trim().equals("")){
                            	Region city=getRegionService().getRegionByName(row.getCell(6).toString().trim());
                            	if(city==null){
                            		msg=msg+"选送城市填写不正确!";
                            	}else{
                            		if(city.getLevel() == 2){
                            			 impWork.setCity(city.getId());
                            		}else{
                            			msg=msg+"选送城市填写不正确!";
                            		}
                            	}
                            }else{
                            	msg = msg+"选送城市未填";
                            }

                            if (!row.getCell(7).toString().trim().equals("")) {
                                Region region = getRegionService().getRegionByName(
                                    row.getCell(7).toString().trim());
                                if (region == null) {
                                    msg = msg + "选送地区填写不正确!";
                                } else {
                                	if(region.getLevel() == 3){
                                		Region city=getRegionService().getRegionByName(row.getCell(6).toString().trim());
                                		if(region.getParent().getId().equals(city.getId())){
                                			impWork.setRegion(region.getId());
                                		}else{
                                			msg=msg+"选送城市或选送地区不正确!";
                                		}
                                	}else{
                                		msg=msg+"选送地区填写不正确!";
                                	}
                                }
                            } else {
                                msg = msg + "选送地区未填!";
                            }
                            if (row.getCell(8).toString().trim().equals("")) {
                                msg = msg + "家长联系方式未填!";
                            } else {
                                impWork.setParentMobile(changedData(row.getCell(8).toString().trim()));//家长号码
                            }
                            if (row.getCell(9).toString().trim().equals("")) {
                                msg = msg + "培训机构未填!";
                            } else {
                                impWork.setTrainingInstitution(row.getCell(9).toString().trim());//培训机构
                            }
                            if (row.getCell(10).toString().trim().equals("")) {
                                msg = msg + "作品名称未填!";
                            } else {
                                impWork.setWorksName(row.getCell(10).toString().trim());//作品名称
                            }
                            if (row.getCell(11).toString().trim().equals("")) {
                                msg = msg + "作品类型未填!";
                            } else {
                                impWork.setWorksType(row.getCell(11).toString().trim());//作品类型
                            }
                            if (row.getCell(12).toString().trim().equals("")) {
                                msg = msg + "作品简介未填!";
                            } else {
                                impWork.setDescription(row.getCell(12).toString().trim());//简介
                            }
                            if (row.getCell(13).toString().trim().equals("")) {
                                msg = msg + "指导老师未填!";
                            } else {
                                impWork.setInstructor(row.getCell(13).toString().trim());//指导老师
                            }
                            if (row.getCell(14).toString().trim().equals("")) {
                                msg = msg + "指导老师联系方式未填!";
                            } else {
                                impWork
                                    .setInstructorMobile(changedData(row.getCell(14).toString().trim()));//指导老师号码
                            }

                            if (!msg.equals("")) {
                                msgs.add(row.getCell(0).toString()
                                    .substring(0, row.getCell(0).toString().indexOf("."))
                                         + "号:" + msg);
                            }
                            impWorks.add(impWork);
                        } else {
                        	if( (!row.getCell(1).toString().equals("")) && cellNum <= 14){
                        		data.setSuccess(false);
                        		data.setMsg("请上传正确的模版！");
                        	}
                            if (j == 4 && row.getCell(1).toString().equals("")) {
                                data.setSuccess(false);
                                data.setMsg("请按照模板添加导入信息！");
                            }
                            break;
                        }
                    }
                    request.getSession().setAttribute(SysConstants.IMPWORKS, impWorks);
                    data.setMsgs(msgs);
                    data.setPath("/teacher/list.do");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.setMsg(e.toString());
            data.setSuccess(false);
            data.setResult(false);
        }
        return data.toString();
    }

    /**
     * 科学计数法转化为数值
     * @param str
     * @return
     */
    public static String changedData(String str) {
        try {
            BigDecimal bd = new BigDecimal(str);
            String result = bd.toPlainString();
            return result;
        } catch (Exception e) {
            return str;
        }

    }

    /**
     * 判断号码是否在
     */
    public boolean numHave(String mobile) {
        // 一判断该手机是否已经注册
        List<Authentication> authenticationlist = getAuthenticationService().findByLogin(mobile);
        if (CommonUtils.isNotEmpty(authenticationlist)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断用户名是否在
     */
    public boolean numName(String usename) {
        // 一判断该用户名是否已经注册
        List<UserInfo> list = getUserInfoService().findByName(usename);
        if (CommonUtils.isNotEmpty(list)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 复制图片
     */
    public static void copyImg(File file, File resultfile) {
        try {
            //要拷贝的图片
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            //目标的地址
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(resultfile));
            try {
                int val = -1;
                while ((val = bis.read()) != -1) {

                    bos.write(val);
                }
                bos.flush();
                bos.close();
                bis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传图片upload
     */
    @RequestMapping("/artistImgUpload")
    @ResponseBody
    public String artistImgUpload(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value = "file", required = false) MultipartFile file) {
        ResultData data = new ResultData();
        try {
            @SuppressWarnings("unchecked")
            List<ImpWorks> impWorks = (List<ImpWorks>) request.getSession().getAttribute(
                SysConstants.IMPWORKS);
            Integer random = RandomDataUtil.SixNumRadom();
            CompressFile.ZipFiles(file, PicturePath.temp + "/" + random);
            File f = new File(PicturePath.temp + "/" + random);
            if (f.isDirectory()) {
                File[] fList = f.listFiles();
                for (int j = 0; j < fList.length; j++) {
                    File files = fList[j];
                    for (int i = 0; i < impWorks.size(); i++) {
                        Date date = new Date();
                        String str = RandomDataUtil.SixNumRadom() + String.valueOf(date.getTime());
                        String filename = str
                                          + files.getName().substring(files.getName().indexOf("."));
                        if (impWorks
                            .get(i)
                            .getUsername()
                            .trim()
                            .equals(
                                (files.getName().substring(0, files.getName().indexOf("."))).trim()
                                    .replace("\\", ""))) {
                            File targetFileArtist = new File(PicturePath.artist, filename);
                            copyImg(files, targetFileArtist);
                            new ImageUtil().thumbnailImage(PicturePath.artist + "/" + filename,
                                600, 600);//生成缩略图
                            impWorks.get(i).setHeadImg(
                                BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/artist/thumb_"
                                        + filename);
                            break;
                        }
                    }
                }
            }
            f.delete();
            request.getSession().setAttribute(SysConstants.IMPWORKS, impWorks);
        } catch (Exception e) {
            e.printStackTrace();
            data.setMsg(e.toString());
            data.setSuccess(false);
            data.setResult(false);
        }
        return data.toString();
    }

    /**
     * 上传图片upload
     */
    @RequestMapping("/worksImgUpload")
    @ResponseBody
    public String worksImgUpload(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "file", required = false) MultipartFile file) {
        ResultData data = new ResultData();
        try {
            @SuppressWarnings("unchecked")
            List<ImpWorks> impWorks = (List<ImpWorks>) request.getSession().getAttribute(
                SysConstants.IMPWORKS);
            Integer random = RandomDataUtil.SixNumRadom();
            CompressFile.ZipFiles(file, PicturePath.temp + "/" + random);
            File f = new File(PicturePath.temp + "/" + random);
            if (f.isDirectory()) {
                File[] fList = f.listFiles();
                for (int j = 0; j < fList.length; j++) {
                    File files = fList[j];
                    for (int i = 0; i < impWorks.size(); i++) {
                        Date date = new Date();
                        String str = RandomDataUtil.SixNumRadom() + String.valueOf(date.getTime());
                        String filename = str
                                          + files.getName().substring(files.getName().indexOf("."));
                        if (impWorks
                            .get(i)
                            .getUsername()
                            .trim()
                            .equals(
                                (files.getName().substring(0, files.getName().indexOf("."))).trim()
                                    .replace("\\", ""))) {
                            File targetFileArtist = new File(PicturePath.works, filename);
                            copyImg(files, targetFileArtist);
                            new ImageUtil().thumbnailImage(PicturePath.works + "/" + filename, 600,
                                600);//生成缩略图
                            impWorks.get(i).setWorksImg(
                                BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/works/thumb_"
                                        + filename);
                            break;
                        }
                    }
                }
            }
            f.delete();
            request.getSession().setAttribute(SysConstants.IMPWORKS, impWorks);
        } catch (Exception e) {
            e.printStackTrace();
            data.setMsg(e.toString());
            data.setSuccess(false);
            data.setResult(false);
        }
        return data.toString();
    }

    /**
     * 单张上传图片功能
     */
    @RequestMapping("/singleUpload")
    @ResponseBody
    public String singleUpload(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "file", required = false) MultipartFile file) {
        String mobile = request.getParameter("mobile");
        String type = request.getParameter("picType");
        ResultData data = new ResultData();
        try {
            response.addHeader("Access-Control-Allow-Origin", "*");
            Date date = new Date();
            String str = String.valueOf(date.getTime());
            String filename = str
                              + file.getOriginalFilename().substring(
                                  file.getOriginalFilename().indexOf("."));
            if (type.equals("0")) {//艺术家图片上传
                File targetFileArtist = new File(PicturePath.artist, filename);
                if (!targetFileArtist.exists()) {
                    targetFileArtist.mkdirs();
                }
                file.transferTo(targetFileArtist);
                data.setOrginpath(BaseCodeParam.getObject(BaseCodeParam.imageUrl)
                                  + "/artist/thumb_" + filename);
                data.setPath(filename);//缩略图
                new ImageUtil().thumbnailImage(PicturePath.artist + "/" + filename, 600, 600);//生成缩略图
            } else if (type.equals("1")) {//作品蹄片上传
                File targetFileWorks = new File(PicturePath.works, filename);
                if (!targetFileWorks.exists()) {
                    targetFileWorks.mkdirs();
                }
                file.transferTo(targetFileWorks);
                data.setOrginpath(BaseCodeParam.getObject(BaseCodeParam.imageUrl) + "/works/thumb_"
                                  + filename);
                data.setPath(filename);
                new ImageUtil().thumbnailImage(PicturePath.works + "/" + filename, 600, 600);//生成缩略图
            }
            //从session获取导入信息
            List<ImpWorks> impWorks = (List<ImpWorks>) request.getSession().getAttribute(
                SysConstants.IMPWORKS);
            for (int i = 0; i < impWorks.size(); i++) {
                if (mobile.equals(impWorks.get(i).getMobile())) {
                    if (type.equals("0")) {
                        impWorks.get(i).setHeadImg(data.getOrginpath());
                    } else if (type.equals("1")) {
                        impWorks.get(i).setWorksImg(data.getOrginpath());
                    }
                    break;
                }
            }
            //导入信息中加入图片
            request.getSession().setAttribute(SysConstants.IMPWORKS, impWorks);
        } catch (Exception e) {
            e.printStackTrace();
            data.setMsg(e.toString());
            data.setSuccess(false);
            data.setResult(false);
        }
        Long end = System.currentTimeMillis();
        return data.toString();
    }

    /**
     * 保存
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public String save(HttpServletRequest request, Model model) {
        ResultData data = new ResultData();
        String msg = "";
        try {
            List<ImpWorks> impWorks = (List<ImpWorks>) request.getSession().getAttribute(
                SysConstants.IMPWORKS);
            for (int i = 0; i < impWorks.size(); i++) {
                if (impWorks.get(i).getHeadImg() == null) {
                    msg = "有图片未上传";
                    break;
                }
                if (impWorks.get(i).getWorksImg() == null) {
                    msg = "有图片未上传";
                    break;
                }
            }
            if (msg.equals("")) {
                for (int i = 0; i < impWorks.size(); i++) {
                    String mobile = impWorks.get(i).getMobile();
                    String name = impWorks.get(i).getUsername();
                    User user = new User();
                    //注册
                    if (name != "" && mobile != "") {
                        String password = UtilMD5.getMD5ofStr(mobile.substring(5)).toLowerCase();
                        List<Authentication> authenticationlist = getAuthenticationService()
                            .findByLogin(mobile);
                        List<UserInfo> list = getUserInfoService().findByName(name);
                        if (CommonUtils.isEmpty(authenticationlist) && CommonUtils.isEmpty(list)) {
                            String salt = RandomDataUtil.SixRadom();
                            UserInfo userinfo = new UserInfo();
                            userinfo.setName(name);
                            userinfo.setType("1");
                            user.setUserInfo(userinfo);
                            userinfo.setUser(user);
                            userinfo.setRegion(null);
                            getUserService().add(user);
                            // 注册成功后吧信息写入session
                            //保存手机认证
                            MobileAuthentication mobileAuthentication = new MobileAuthentication();
                            mobileAuthentication.setUser(user);
                            mobileAuthentication.setLogin(mobile);
                            mobileAuthentication.setSalt(salt);
                            mobileAuthentication.setInvalid(false);
                            mobileAuthentication.setPassword(UtilMD5Encoder.encodePassword(
                                password, salt));
                            getMobileAuthenticationService().add(mobileAuthentication);
                            //保存用户名认证
                            LoginAuthentication loginAuthentication = new LoginAuthentication();
                            loginAuthentication.setUser(user);
                            loginAuthentication.setLogin(name);
                            loginAuthentication.setSalt(salt);
                            loginAuthentication.setInvalid(false);
                            loginAuthentication.setPassword(UtilMD5Encoder.encodePassword(password,
                                salt));
                            getLoginAuthenticationService().add(loginAuthentication);

                            /**
                             * 成为小画家
                             */
                            Artist artist = new Artist();
                            Works works = new Works();
                            ArtistInfo artistinfo = new ArtistInfo();
                            List<Tag> tagList = getTagService().getTagListByName(
                                impWorks.get(i).getWorksType());
                            WorksTagItem workstag = new WorksTagItem();
                            //所属城市
                            Region region = new Region();
                            region.setId(impWorks.get(i).getRegion());
                            //画家信息
                            artistinfo.setInvalid(false);
                            artistinfo.setAge(DateUtil.getage(impWorks.get(i).getBirthday()));
                            artistinfo.setImg(impWorks.get(i).getHeadImg());
                            artistinfo.setOrginimg(impWorks.get(i).getHeadImg() == null ? null
                                : impWorks.get(i).getHeadImg().replace("thumb_", ""));
                            artistinfo.setName(impWorks.get(i).getName());
                            artistinfo.setBirthday(new java.sql.Date(impWorks.get(i).getBirthday()
                                .getTime()));
                            artistinfo.setMale("男".equals(impWorks.get(i).getSex()));
                            artistinfo.setRegion(region);
                            artistinfo.setZodiac(DateUtil.getZodica(impWorks.get(i).getBirthday()));
                            artistinfo.setConstellation(DateUtil.getConstellation(impWorks.get(i)
                                .getBirthday()));
                            artistinfo.setParentMobile(impWorks.get(i).getParentMobile());
                            artistinfo.setSchool(impWorks.get(i).getSchool());
                            artistinfo.setTrainingInstitution(impWorks.get(i)
                                .getTrainingInstitution());
                            artistinfo.setCartoon(impWorks.get(i).getCartoon());
                            artistinfo
                                .setCreatedDatetime(new Timestamp(System.currentTimeMillis()));
                            // 2.0 新增
                            artistinfo.setAlipayAccount(impWorks.get(i).getAlipayAccount());
                            artistinfo.setBankAccount(impWorks.get(i).getBankAccount());
                            artistinfo.setAccountName(impWorks.get(i).getAccountName());
                            artistinfo.setWeChatAccount(impWorks.get(i).getWeChatAccount());
                            artistinfo.setUser(user);
                            artist.setInvalid(false);
                            artist.setStatus(2);
                            artist.setArtistInfo(artistinfo);
                            artistinfo.setArtist(artist);
                            getArtistService().add(artist);
                            //保存作品
                            works.setInvalid(false);
                            works.setStatus(2);
                            works.setAge(artistinfo.getAge());
                            works.setArtist(artist);
                            works.setMale("男".equals(impWorks.get(i).getSex()));
                            works.setRegion(region);
                            works.setVotenum(0);
                            works.setName(impWorks.get(i).getWorksName());
                            works.setAuthor(impWorks.get(i).getName());
                            works.setInstructor(impWorks.get(i).getInstructor());
                            works.setInstructorMobile(impWorks.get(i).getInstructorMobile());
                            works.setSchool(impWorks.get(i).getSchool());
                            works.setDescription(impWorks.get(i).getDescription());
                            //图片塞值
                            Set<WorksPictureItem> worksPictureItemSet = new LinkedHashSet<WorksPictureItem>();
                            WorksPictureItem workspictureitem = new WorksPictureItem();
                            workspictureitem.setWorks(works);
                            Picture picture = new Picture();
                            picture.setPath(impWorks.get(i).getWorksImg());
                            picture.setOrginpath(impWorks.get(i).getWorksImg() == null ? null
                                : impWorks.get(i).getWorksImg().replace("thumb_", ""));
                            workspictureitem.setPicture(picture);
                            worksPictureItemSet.add(workspictureitem);
                            works.setWorksPictureItemSet(worksPictureItemSet);
                            getWorksService().add(works);
                            //保存作品类型
                            workstag.setWorks(works);
                            workstag.setInvalid(false);
                            workstag.setTag(tagList.get(0));
                            getWorksTagItemService().add(workstag);
                            //保存比赛信息
                            GameWorksItem gameworks = new GameWorksItem();
                            List<Game> gamelist = getGameService().findList();
                            if (CommonUtils.isNotEmpty(gamelist)) {
                                gameworks.setGame(gamelist.get(0));
                            }
                            gameworks.setWorks(works);
                            gameworks.setApplicant(user);
                            getGameService().add(gameworks);
                            //保存角色类型
                            if ("3".equals(userinfo.getType())) {
                                userinfo.setType("6");
                            } else {
                                userinfo.setType("2");
                            }
                            getUserInfoService().update(userinfo);
                        }
                    }
                }
                data.setPath("/teacher/batchimport.do");
            } else {
                data.setSuccess(false);
                data.setMsg(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.setMsg(e.toString());
            data.setSuccess(false);
            data.setResult(false);
        }
        return data.toString();

    }

    /**
     * test
     * @return
     */
    @RequestMapping("/test")
    public String test(HttpServletRequest request, Model model) {
        ImpWorks impworks = new ImpWorks();
        request.getSession().setAttribute(SysConstants.IMPWORKS, impworks);
        String ss = BaseCodeParam.getObject("loginUrl") + "/res/img/painter.jpg";
        System.out.print(BaseCodeParam.getObject("loginUrl") + "/res/img/painter.jpg");
        return "redirect:" + ss;
    }
}

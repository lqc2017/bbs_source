package grp3022.bbs.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import grp3022.bbs.po.BBSUser;
import grp3022.bbs.service.BBSUserService;

@Controller
public class UploadController {
	
	@Resource
	private BBSUserService userService;
	/**
	 * 2017年5月13日 下午5:22:54
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/ckeditorUpload",produces = "text/html;charset=UTF-8")
	public @ResponseBody String ckeditorUpload(@RequestParam(value = "upload") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setCharacterEncoding("UTF-8"); 
		String uploadContentType = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));

		System.out.println("uploadFileName:" + file.getName());
		System.out.println("uploadContentType:" + uploadContentType);
		// CKEditor提交的很重要的一个参数
		String callback = request.getParameter("CKEditorFuncNum");

		String result = "";
		
		String expandedName = ""; 
		if (uploadContentType.equals(".jpg")) {
			expandedName = ".jpg";
		} else if (uploadContentType.equals(".png")) {
			expandedName = ".png";
		} else if (uploadContentType.equals(".gif")) {
			expandedName = ".gif";
		} else if (uploadContentType.equals(".bmp")) {
			expandedName = ".bmp";
		} else {
			result += "<script type=\"text/javascript\">";
			result += "window.parent.CKEDITOR.tools.callFunction(" +
					  callback + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');";
			result += "</script>";
			return result;
		}

		System.out.println("size:" + file.getSize());
		if (file.getSize() > 2 * 1024 * 1024) {
			result += "<script type=\"text/javascript\">";
			result += "window.parent.CKEDITOR.tools.callFunction(" +
					  callback + ",''," + "'文件大小不得大于2MB');";
			result += "</script>";
			return result;
		}

		String fileName = UUID.randomUUID().toString() + expandedName;
		System.out.println("new fileName:" + fileName);
		
		String path2 = request.getSession().getServletContext().getRealPath("/upload/")+fileName;
		File destFile2 = new File(path2);
		FileUtils.copyInputStreamToFile(file.getInputStream(), destFile2);

		// 返回“图像”选项卡并显示图片
		result += "<script type=\"text/javascript\">";
		result += "window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + "/upload/" + fileName + "','')";
		result += "</script>";

		return result;
	}
	
	/**
	 * 2017年5月27日 下午3:05:59
	 * @param file
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/upload/portrait")
	public @ResponseBody String portraitUpload(@RequestParam("myFile") MultipartFile file,HttpServletRequest request,
			HttpSession session){
		Map<String, Object> json = new HashMap<String, Object>();
		
		try {
			Long userId = Long.parseLong(session.getAttribute("userId").toString());
			BBSUser user = userService.getById(userId);
			String fileName = System.currentTimeMillis()+"_"+user.getNickname()
					+"_"+ file.getOriginalFilename();
			
			String path = session.getServletContext().getRealPath("/upload/") + fileName;
			File destFile2 = new File(path);
			FileUtils.copyInputStreamToFile(file.getInputStream(), destFile2);
			System.out.println("save success!");
			json.put("success", "/upload/" + fileName);
			
			user.setProtraitUrl("/upload/" + fileName);
			System.out.println(user.getProtraitUrl());
			System.out.println(user.getProtraitUrl().length());
			userService.updateById(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(json);
	}
}

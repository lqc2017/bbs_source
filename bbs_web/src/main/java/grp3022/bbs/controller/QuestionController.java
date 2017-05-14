package grp3022.bbs.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import grp3022.bbs.po.Answer;
import grp3022.bbs.po.Question;
import grp3022.bbs.service.AnswerService;
import grp3022.bbs.service.QuestionService;
import grp3022.bbs.so.AnswerSo;
import grp3022.bbs.so.QuestionSo;
import grp3022.bbs.util.Format;

/**
 * @author 全琛
 * @create_time 上午11:07:01
 *
 */
@Controller
@RequestMapping("/question")
public class QuestionController {

	@Resource
	private QuestionService questionService;
	@Resource
	private AnswerService answerService;
	private final String pathPrefix = "question";
	

	
	/**
	 * 2017年5月13日 下午5:22:21
	 * @return
	 */
	@RequestMapping(value = "/home")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView(pathPrefix + "/home");
		return mav;
	}
	
	/**
	 * 2017年5月13日 下午6:56:35
	 * @param questionId
	 * @return
	 */
	@RequestMapping(value = "")
	public ModelAndView question(Long q) {
		Question question = questionService.getRecordById(q);
		AnswerSo answerSo = new AnswerSo();
		answerSo.setQuestionId(q);
		List<Answer> answers = answerService.getAllBySo(answerSo);
		ModelAndView mav = new ModelAndView(pathPrefix + "/question");
		
		mav.addObject("question", question);
		mav.addObject("answers", answers);
		return mav;
	}
	
	/**
	 * 2017年5月13日 下午6:30:10
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(QuestionSo questionSo,Integer pn) {
		PageInfo<Question> pageInfo = questionService.getPageBySo(questionSo,pn,null);
		ModelAndView mav = new ModelAndView(pathPrefix + "/list");
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("so", questionSo);
		mav.addObject("username", "username");
		mav.addObject("format", new Format());
		return mav;
	}

	
	/**
	 * 2017年5月13日 下午5:22:38
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit() {
		ModelAndView mav = new ModelAndView(pathPrefix + "/edit");
		return mav;
	}
	
	/**
	 * 2017年5月13日 下午5:45:40
	 * @param question
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add(Question question) {
		try {
			question.setCreateBy((long)1);
			question.setUpdateBy((long)1);
			questionService.add(question);
		} catch (Exception e) {
			return "/question/ask_fail";
		}
		return "/question/ask_success";
	}

	
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

	/*
	 * private String getPrincipal(){ String userName = null; Object principal =
	 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 * 
	 * if (principal instanceof UserDetails) { userName =
	 * ((UserDetails)principal).getUsername(); } else { userName =
	 * principal.toString(); } return userName; }
	 */
}

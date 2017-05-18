package grp3022.bbs.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

import grp3022.bbs.po.Answer;
import grp3022.bbs.po.AnswerHelpKey;
import grp3022.bbs.po.Question;
import grp3022.bbs.service.AnswerHelpService;
import grp3022.bbs.service.AnswerService;
import grp3022.bbs.service.QuestionService;
import grp3022.bbs.so.AnswerSo;
import grp3022.bbs.so.QuestionSo;
import grp3022.bbs.type.Tag;
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
	@Resource
	private AnswerHelpService answerHelpService;
	private final String pathPrefix = "question";
	

	
	/**
	 * 2017年5月13日 下午5:22:21
	 * @return
	 */
	@RequestMapping(value = "/home")
	public ModelAndView home(QuestionSo questionSo,Integer pn) {
		if((questionSo.getKeywords()==null||questionSo.getKeywords().equals("")) && questionSo.getTimeFrame()==null)
			questionSo.setTimeFrame((short)10);
		PageInfo<Question> pageInfo = questionService.getPageBySo(questionSo,pn,10);
		
		/*初始化标签*/
		Map<String,ArrayList<Tag>> tagsMap = new HashMap<String,ArrayList<Tag>>();
		for (Question question : pageInfo.getList()) {
			ArrayList<Tag> tags = new ArrayList<Tag>();
			List<Integer> indexes = JSON.parseArray(question.getTags(), Integer.class);
			for (int index : indexes) {
				for (Tag tag : Tag.values()) {
					if (tag.getIndex() == index) {
						tags.add(tag);
					}
				}
			}
			tagsMap.put(question.getId().toString(),tags);
		}
		/*初始化选中标签*/
		Tag s_tag = null;
		if (questionSo.getTagIndex() != null && !questionSo.getTagIndex().equals("")) {
			int index = Integer.parseInt(questionSo.getTagIndex());
			for (Tag tag : Tag.values()) {
				if (tag.getIndex() == index) {
					s_tag=tag;
				}
			}
		}
			
		
		ModelAndView mav = new ModelAndView(pathPrefix + "/home");
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("tagsMap", tagsMap);
		mav.addObject("s_tag", s_tag);
		mav.addObject("questionSo", questionSo);
		mav.addObject("format", new Format());
		return mav;
	}
	
	
	/**
	 * 2017年5月15日 下午4:29:50
	 * @param q
	 * @return
	 */
	@RequestMapping(value = "")
	public ModelAndView question(Long q,AnswerSo answerSo) {
		long userId = 2;
		
		/*初始化问题答案*/
		if(answerSo==null)
			answerSo = new AnswerSo();
		answerSo.setQuestionId(q);
		List<Answer> answers = answerService.getAllBySo(answerSo);
		/*初始化答案评价*/
		List<Boolean> helpEnable = new ArrayList<Boolean>();
		for(Answer answer : answers){
			AnswerHelpKey key = new AnswerHelpKey();
			key.setUserId(userId);
			key.setAnswerId(answer.getId());
			if(answerHelpService.getByKey(key)!=null)
				helpEnable.add(false);
			else
				helpEnable.add(true);
		}
		/*更新问题信息*/
		Question question = questionService.getById(q);
		question.setViews(question.getViews()+1);
		questionService.updateById(question);
		/*初始化问题标签*/
		List<Tag> tags = new ArrayList<Tag>();
		List<Integer> indexes = JSON.parseArray(question.getTags(), Integer.class);
		for(int index:indexes){
			for  (Tag tag : Tag.values()) {  
	            if  (tag.getIndex() == index) {  
	            	tags.add(tag);
	            }
	        }  
		}
		
		ModelAndView mav = new ModelAndView(pathPrefix + "/question");
		
		mav.addObject("question", question);
		mav.addObject("userId", (long)1);
		mav.addObject("tags", tags);
		mav.addObject("answers", answers);
		mav.addObject("answerSo", answerSo);
		mav.addObject("helpEnable", helpEnable);
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
			System.out.println(e.getMessage());
			return "/question/ask_fail";
		}
		return "/question/ask_success";
	}
	
	/**
	 * 2017年5月17日 下午7:55:23
	 * @param q
	 * @param u
	 * @return
	 */
	@RequestMapping(value = "/solved")
	public @ResponseBody String solved(Long q,Long u) {
		try {
			Question question = questionService.getById(q);
			if(question.getCreateBy()!=u)
				return "fail";
			question.setStatus((short)20);
			question.setUpdateBy(u);
			question.setUpdateTime(new Date());
			questionService.updateById(question);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "fail";
		}
		return "success";
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

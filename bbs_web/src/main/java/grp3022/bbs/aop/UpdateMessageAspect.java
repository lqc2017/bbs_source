package grp3022.bbs.aop;

import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import grp3022.bbs.po.BBSUser;
import grp3022.bbs.po.Question;
import grp3022.bbs.service.BBSUserService;
import grp3022.bbs.service.QuestionService;
import grp3022.bbs.so.QuestionSo; 

@Aspect
@Component  
public  class UpdateMessageAspect {  
	@Resource
	private BBSUserService userService;
	@Resource
	private QuestionService questionService;
  
    //Controller层切点  
    @Pointcut("@annotation(grp3022.bbs.aop.UpdateMessage)")  
     public  void controllerAspect() {  
    }  
  
    @Before("controllerAspect()")  
     public  void doBefore(JoinPoint joinPoint) {  
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
        HttpSession session = request.getSession();  
        
        if(session.getAttribute("userId")==null){
        	System.out.println("用户未登录");  
        	return;
        }
		else {
			// 读取session中的用户
			System.out.println("用户已登陆登录");
			BBSUser user = userService.getById((Long) session.getAttribute("userId"));
			List<Question> questions = questionService.getAllBySo(new QuestionSo(user.getId(),(short)10));
			int messageCnt = 0;
			for(Question q : questions){
				if(q.getReminder()<q.getAnswers())
					messageCnt += q.getAnswers() - q.getReminder();
			}
			System.out.println("未读消息数量："+messageCnt);
			if(messageCnt>0)
				request.setAttribute("messageCnt", messageCnt);
			//
			try {
				// *========控制台输出=========*//
				System.out.println("=====前置通知开始=====");
				System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "."
						+ joinPoint.getSignature().getName() + "()"));
				System.out.println("方法描述:" + getControllerMethodDescription(joinPoint));
				System.out.println("请求人:" + user.getNickname());

			} catch (Exception e) {
				System.out.printf("异常信息:{1}", e.getMessage());
			}
		}
    }  
  
  
  
    /** 
     * 获取注解中对方法的描述信息 用于Controller层注解 
     * 
     * @param joinPoint 切点 
     * @return 方法描述 
     * @throws Exception 
     */  
     @SuppressWarnings("rawtypes")
	public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {  
        String targetName = joinPoint.getTarget().getClass().getName();  
        String methodName = joinPoint.getSignature().getName();  
        Object[] arguments = joinPoint.getArgs();  
        Class targetClass = Class.forName(targetName);  
        Method[] methods = targetClass.getMethods();  
        String description = "";  
         for (Method method : methods) {  
             if (method.getName().equals(methodName)) {  
                Class[] clazzs = method.getParameterTypes();  
                 if (clazzs.length == arguments.length) {  
                    description = method.getAnnotation(UpdateMessage. class).description();  
                     break;  
                }  
            }  
        }  
         return description;  
    }  
}  

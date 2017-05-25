package grp3022.bbs.aop;

import java.lang.annotation.*;  

/** 
 *自定义注解 拦截Controller 
 */  
  
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public  @interface TestAnno {  
  
    String description()  default "";  
  
  
}  

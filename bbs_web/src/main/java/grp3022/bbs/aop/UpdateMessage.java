package grp3022.bbs.aop;

import java.lang.annotation.*;  

@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public  @interface UpdateMessage {  
    String description()  default "";  
}  

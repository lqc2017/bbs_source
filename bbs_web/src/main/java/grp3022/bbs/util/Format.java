package grp3022.bbs.util;

import java.util.Date;

public final class  Format {
	/**
	 * 2017年5月14日 下午1:55:40
	 * @param date
	 * @return
	 */
	public final static String getTime(Date date){
		Date now = new Date();
		Long value = (now.getTime()-date.getTime())/1000;
		
		if(value<60){
			return value.toString()+"秒前";
		}else if(value>60&&value<3600){
			value = value/60;
			return value.toString()+"分钟前";
		}else if(value>3600&&value<86400){
			value = value/3600;
			return value.toString()+"小时前";
		}else if(value>86400&&value<604800){
			value = value/86400;
			return value.toString()+"天前";
		}else if(value>604800&&value<604800*4.4){
			value = value/604800;
			return value.toString()+"周前";
		}else if(value>604800*4.4&&value<86400*365){
			value = value/2661120;
			return value.toString()+"月前";
		}else{
			value = value/31536000;
			return value.toString()+"年前";
		}
	}
}

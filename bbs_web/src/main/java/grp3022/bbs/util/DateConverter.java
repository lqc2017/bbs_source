package grp3022.bbs.util;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 全琛 on 2017/2/9.
 */
public class DateConverter implements Converter<String, Date> {
    public Date convert(String s) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            if(s!=null&&s.trim()!="")
                return dateFormat.parse(s);
            else
                return null;
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

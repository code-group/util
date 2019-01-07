package toolkit;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author zhull
 * @date 2018/10/26
 * <P>日期工具</P>
 */
public class DateUtil {

    /**
     * 获取相对于某日期的其他日期
     *
     * @param baseDate
     * @param bias 偏移天数
     * @return
     */
    public static Date getDateFrom(Date baseDate, int bias) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(baseDate);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + bias);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        System.out.println(simpleDateFormat.format(calendar.getTime()));
        return calendar.getTime();
    }
}

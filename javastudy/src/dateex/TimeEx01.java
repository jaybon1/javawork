package dateex;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

// 구버전 (자바1.7이하)
// java.sql.Date
// java.sql.Time (X)
// java.sql.Timestamp
// java.util.Caleander (X)
// java.util.Date

// 신버전 (자바 1.8이상)
// java.time.LocalDate
// java.time.LoaclDateTime
// java.time.LocalTime


public class TimeEx01 {
	public static void main(String[] args) {
		
		// Calender 안씀
//		Calendar cal = Calendar.getInstance();
//		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		String today = formater.format(cal.getTime());
//		System.out.println("cal.getTime() " + cal.getTime());
//		System.out.println("today " + today);
		
		// java.util.Date 안씀
//		Date time2 = new Date();
//		System.out.println("time2 : " + time2);
//		System.out.println("time2.getTime() : " + time2.getTime());
//		System.out.println("time2.getDay() : " + time2.getDay());
		
		// java.sql.Date / Date는 1970.01.01을 기준으로 하여 경과된 밀리초를 계산
		// 안씀
//		java.sql.Date time3 = new java.sql.Date(86400001);
//		System.out.println(time3);
		
		// java.sql.Timestamp
//		SimpleDateFormat sf = new SimpleDateFormat();
//		Date d = new Date();
//		String timestamp = sf.format(d.getTime());
//		Timestamp ts = Timestamp.valueOf(timestamp);
//		System.out.println(ts);
		
		// 5. LocalDate
		Timestamp nowDate = Timestamp.valueOf(LocalDateTime.now());
		System.out.println(nowDate);
		System.out.println(LocalDateTime.now());
		System.out.println(LocalDate.now());
		System.out.println(LocalTime.now());
		
		// 6. LocalDateTime (Timestamp to LocaldateTime)
		LocalDateTime ldt = nowDate.toLocalDateTime();
		System.out.println(ldt);
		
		
		
	}
}

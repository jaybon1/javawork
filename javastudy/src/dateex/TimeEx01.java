package dateex;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

// ������ (�ڹ�1.7����)
// java.sql.Date
// java.sql.Time (X)
// java.sql.Timestamp
// java.util.Caleander (X)
// java.util.Date

// �Ź��� (�ڹ� 1.8�̻�)
// java.time.LocalDate
// java.time.LoaclDateTime
// java.time.LocalTime


public class TimeEx01 {
	public static void main(String[] args) {
		
		// Calender �Ⱦ�
//		Calendar cal = Calendar.getInstance();
//		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		String today = formater.format(cal.getTime());
//		System.out.println("cal.getTime() " + cal.getTime());
//		System.out.println("today " + today);
		
		// java.util.Date �Ⱦ�
//		Date time2 = new Date();
//		System.out.println("time2 : " + time2);
//		System.out.println("time2.getTime() : " + time2.getTime());
//		System.out.println("time2.getDay() : " + time2.getDay());
		
		// java.sql.Date / Date�� 1970.01.01�� �������� �Ͽ� ����� �и��ʸ� ���
		// �Ⱦ�
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

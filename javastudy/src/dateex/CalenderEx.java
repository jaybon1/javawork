package dateex;

import java.util.Calendar;

public class CalenderEx {
	// 2020-03-18
	public static void printCalender(String msg, Calendar cal) {
		int year = cal.get(Calendar.YEAR); // 2020 
		
		int month = cal.get(Calendar.MONTH) + 1; // 1월은 0으로 나온다. 그래서 +1해줘야함
		String mon = (month < 10)?"0" + month:"" + month;
		
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String day1 = (day < 10)?"0" + day:"" + day;
		
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		String hour1 = (hour < 10)?"0" + hour:"" + hour;
		
		int minute = cal.get(Calendar.MINUTE);
		String minute1 = (minute < 10)?"0" + minute:"" + minute;
		
		int second = cal.get(Calendar.SECOND);
		String second1 = (second < 10)?"0" + second:"" + second;
		
		System.out.println(year+"-"+mon+"-"+day+":"+hour1+":"+minute1+":"+second1);
		
	}
	
	public static void main(String[] args) {
		Calendar a = Calendar.getInstance(); // 싱글톤
		
		printCalender("현재", a);

		
	}
}

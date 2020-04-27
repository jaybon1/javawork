package ch16;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 모델, beans (비현실 세계에서 현실세계로 구현하는 것)
// 데이터베이스의 자료를 자바 자료로 구현
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	private int id;
	private String name;
	private String email;
	private String password;
}

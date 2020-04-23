package ch01;

interface Chordata {// 포유강
	// 우선 "포유강 계열 은 뛸수있다" 를 출력하기위한 void를 생성하세요"
	void run();
}

abstract class Mammalia implements Chordata {// 식육목
	// "식육목 동물은 이빨을 가지고 있다"를 출력하기위한 void를 생성하세요.
	abstract void tooth();

}

class Caniformia extends Mammalia { // 개아목
	// "개아목은 광견병에 걸릴수있다" 를 출력하기위한 void 를 생성하세요.

	@Override
	public void run() {
		System.out.println("포유강 계열은 뛸 수 있다.");
	}

	@Override
	void tooth() {
		System.out.println("식육목 동물은 이빨을 가지고 있다.");
	}

	void crazyDog() {
		System.out.println("개아목은 광견병에 걸릴 수 있다.");
	}
}

class Procyonidae extends Caniformia {// 아메리카너구리과
	int 다리길이;
	int 이빨길이;
	String 이름;
	// 여기서는 오버라이딩을 이용해서 Mammalia(식육목)에서만든 "식육목 동물은 이빨을 가지고있다"말고 "라쿤의 이빨은 귀엽다!"를
	// 출력하게해보세요

	// 그리고 생성자를 3개만들어서 각각 매개변수를 다르게하여 라쿤에 대해 넣고싶은걸 넣고 3개다 출력해보세요 (오버로딩 이용!)

	public Procyonidae(int legsize, int toothsize, String name) {
		다리길이 = legsize;
		이빨길이 = toothsize;
		이름 = name;
	}

	public Procyonidae(int legsize, int toothsize) {
		this(legsize, toothsize, "라쿤");
	}

	public Procyonidae() {
		this(20, 3, "라쿤");
	}

	@Override
	void tooth() {
		System.out.println("라쿤의 이빨은 귀엽다!");
	}

}

/*
 * 문제 잘읽고 푸시길 바랍니다
 * 
 * 이건 '라쿤'에관한 정보입니다.
 * 
 * 인터페이스(포유강)~ class procyonidae(아메리카 너구리과) 까지는 라군의 분류학적으로 나눈겁니다.
 * 
 * 즉, 인터페이스(포유강) 이가진건 식육목도 가지고 식육목이가진건 개아목이 가지고 무튼 이런식으로 '상속됩니다'
 * 
 * 일단 안에 주석을 다읽고 푸십시요.
 * 
 * "객체생성은 'Procyonidae'로만 쓸꺼니 잘생각해서 읽고 만드세요.
 * 
 * 
 * 
 */

public class Sibal {

	public static void main(String[] args) {
		// 여기선 Procyonidae하나만 객체로해서

		// 위에있는적힌거 다출력해보세요.

		// 할꺼
		// 1.라쿤의 이빨은 귀엽다!" 출력 <-- 이건 오버라이딩을 꼭이용
		// 2."포유강 계열 은 뛸수있다" <--출력
		// 3."식육목 동물은 이빨을 가지고있다"<--출력
		// 4."개아목은 광견병에 걸릴수있다" <--출력
		// 5.그리고 생성자를 3개만들어서 각각 매개변수를 다르게하여 라쿤에 대해 넣고싶은걸 넣고 3개다 출력해보세요 (오버로딩 이용!)

		Procyonidae a = new Procyonidae();
		a.tooth();
		a.run();
		Caniformia b = new Caniformia();
		b.tooth();
		b.crazyDog();
		
		Procyonidae c = new Procyonidae(12,1,"라쿤이");
		System.out.println(c.이름);

	}

}

package composite;

import lombok.Data;

// �ڹٴ� ���� ����� �ȵ� (�θ� ������ �� �� ����)

@Data
public class BigBurgerSet {
	// ��������(�߿�) - ����� �ƴ� ����� ���� Ŭ������ �������� ��
	private BigBurger bigBurger;
	private Coke coke;
	private FrenchFried frenchFried;
	
	public BigBurgerSet() {
		this(new BigBurger(), new Coke(), new FrenchFried());
	}
	
	public BigBurgerSet(BigBurger bigBurger) {
		this.bigBurger = bigBurger;
		this.coke = new Coke();
		this.frenchFried = new FrenchFried();
	}

	public BigBurgerSet(BigBurger bigBurger, Coke coke, FrenchFried frenchFried) {
		this.bigBurger = bigBurger;
		this.coke = coke;
		this.frenchFried = frenchFried;
	}
	
	public static void main(String[] args) {
		System.out.println(new BigBurgerSet().getBigBurger());
	}

}

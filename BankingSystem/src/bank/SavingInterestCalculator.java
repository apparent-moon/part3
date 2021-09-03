package bank;

import java.math.BigDecimal;

//InterestCalculator 인터페이스 구현, 적금계좌 이자를 반환해주는 SavingInterestCalculator 클래스
public class SavingInterestCalculator implements InterestCalculator {
    public BigDecimal getInterest(BigDecimal balance) {

        //이자
        BigDecimal interest;

        //금액
        BigDecimal oneMillion = new BigDecimal("1000000");

        //이자율
        BigDecimal interestOneMillion = new BigDecimal("0.5");
        BigDecimal interestOther = new BigDecimal("0.01");

        if (balance.compareTo(oneMillion) >= 0) {
            interest = balance.multiply(interestOneMillion);
            return interest;
        } else {
            interest = balance.multiply(interestOther);
            return interest;
        }
    }
}
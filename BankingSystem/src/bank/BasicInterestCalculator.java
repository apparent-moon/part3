package bank;

import java.math.BigDecimal;

//InterestCalculator 인터페이스 구현, 예금계좌 이자를 반환해주는 BasicInterestCalculator 클래스
public class BasicInterestCalculator implements InterestCalculator{
    public BigDecimal getInterest(BigDecimal balance) {
        //이자
        BigDecimal interest;

        //금액
        BigDecimal tenMillion = new BigDecimal("10000000");
        BigDecimal fiveMillion = new BigDecimal("5000000");
        BigDecimal oneMillion = new BigDecimal("1000000");
        BigDecimal tenThousand = new BigDecimal("10000");

        //이자율
        BigDecimal interestTenMillion = new BigDecimal("0.5");
        BigDecimal interestFiveMillion = new BigDecimal("0.07");
        BigDecimal interestOneMillion = new BigDecimal("0.04");
        BigDecimal interestTenThousand = new BigDecimal("0.02");
        BigDecimal interestOther = new BigDecimal("0.01");

        if(balance.compareTo(tenMillion) >= 0) {
            interest = balance.multiply(interestTenMillion);
            return interest;
        }else if(balance.compareTo(fiveMillion) >= 0){
            interest = balance.multiply(interestFiveMillion);
            return interest;
        }else if(balance.compareTo(oneMillion) >= 0){
            interest = balance.multiply(interestOneMillion);
            return interest;
        }else if(balance.compareTo(tenThousand) >= 0) {
            interest = balance.multiply(interestTenThousand);
            return interest;
        }else {
            interest = balance.multiply(interestOther);
            return interest;
        }
    }
}

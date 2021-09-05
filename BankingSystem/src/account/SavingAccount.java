package account;

import bank.Bank;

import java.math.BigDecimal;
import java.text.DecimalFormat;

//Account 클래스에서 상속받으므로 extends를 사용한다.
public class SavingAccount extends Account{

    public static DecimalFormat df = new DecimalFormat("#,###");

    //TODO: 적금 계좌 클래스의 속성은 목표금액 속성을 추가로 가집니다.
    private BigDecimal goalAmount = new BigDecimal(100000);

    //적금 계좌에 대한 생성자
    public SavingAccount(){
        this.setActive(true);
        this.setCategory("S");
    }

    //생성자 오버로딩
    public SavingAccount(String accNo, String owner, BigDecimal balance, BigDecimal goalAmount) {
        this.setCategory("S");
        this.setActive(true);
        this.setAccNo(accNo);
        this.setOwner(owner);
        this.setBalance(balance);
        this.goalAmount = goalAmount;
    }

    //goalAmount에 대한 getter생성. setter는 이미 목표금액이 100000으로 설정되어 외부에서 수정을 할 수 없도록 선언하지 않는다.
    public BigDecimal getGoalAmount() {
        return goalAmount;
    }

    //적금계좌에서는 목표금액도 보여준다.
    public void getAccountInfo(Account account) {
        System.out.printf("계좌종류: %s | 계좌번호: %s | 계좌주명: %s | 잔액: %s원 | 목표금액 : %s원\n", account.getCategory(), account.getAccNo(), account.getOwner(), df.format(account.getBalance()), df.format(this.getGoalAmount()));
    }
}

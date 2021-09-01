package account;

//import bank.Bank;

import java.math.BigDecimal;

public class Account {
    private String category;            // String 계좌 종류
    private String accNo;               // String 계좌번호
    private String owner;               // String 소유자
    private BigDecimal balance;         // BigDecimal 잔액
    private boolean isActive;           // boolean 활성화 여부

    //생성자
    public Account() {
        this.isActive = true;
        this.category = "N";
    }

    //생성자 오버로딩
    public Account(String accNo, String owner, BigDecimal balance) {
        //기본 생성자에서 제공하는 값들도 제공해야하므로 추가했음.
        this.isActive = true;
        this.category = "N";
        //오버로딩하면서 받아오는 값들
        this.accNo = accNo;
        this.owner = owner;
        this.balance = balance;
    }

    //private으로 설정되어 외부에서 접근이 불가능하므로, getter setter를 설정했다.
    //getter는 외부에서 값을 읽어올때 사용하고, setter는 외부에서 값을 설정할때 사용한다.
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    //return값이 void인 메소드. 계좌의 기본 정보를 return 해준다. 파라미터로 Account객체의 account를 호출한다.
    public void getAccountInfo(Account account){
        System.out.printf("계좌종류: %s | 계좌번호: %s | 계좌주명: %s | 잔액: %s원", account.category, account.accNo, account.owner, account.balance);
    }

    //BigDecimal 타입의 withdraw(출금) 메소드. 매개변수로 BigDecimal타입인 amount라는 이름의 변수를 파라미터로 받는다.
    public BigDecimal withdraw(BigDecimal amount){
        this.balance = this.balance.subtract(amount);
        return amount;
    }

    //BigDecimal 타입의 deposit(입금) 메소드. 매개변수로 BigDecimal타입인 amount라는 이름의 변수를 파라미터로 받는다.
    public BigDecimal deposit(BigDecimal amount){
        balance = this.balance.add(amount);
        return amount;
    }
}


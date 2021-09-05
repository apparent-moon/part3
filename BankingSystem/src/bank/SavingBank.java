package bank;

import account.SavingAccount;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.NoSuchElementException;

public class SavingBank extends Bank {

    public void withdraw(SavingAccount account) throws Exception{
        // TODO: Account의 출금 메서드에서 잔액/목표 금액 체크하여 조금 다르게 구현
        // throws Exception 적금 계좌는 잔액이 목표 금액(%s원) 이상이어야 출금 가능합니다.
    }

    public void deposit(SavingAccount account) throws Exception {
        try {
            System.out.print("입금하실 금액을 입력해주세요 : ");
            BigDecimal input = scanner.nextBigDecimal();
            System.out.println("입금이 완료되었습니다.\n");
            System.out.println("========================================================================");
            account.setBalance(account.getBalance().add(input));
            System.out.printf("계좌종류: %s | 계좌번호: %s | 계좌주명: %s | 잔액: %s원 | 목표금액 : %s원\n", account.getCategory(),
                    account.getAccNo(), account.getOwner(), df.format(account.getBalance()),
                    df.format(account.getGoalAmount()));
            System.out.println("========================================================================");
        } catch (Exception e) {
            System.out.println("입금하는 과정에서 오류가 발생하였습니다.");
            return;
        }
    }

    // TODO: 목표금액을 입력받아서 SavingAccount 객체 생성하도록 재정의
    @Override
    public SavingAccount createAccount() throws NoSuchElementException{
        try {
            System.out.print("적금계좌명 혹은 적금계좌 소유자의 이름을 입력해주세요: ");
            String owner = scanner.next();
            String ano = String.format(new DecimalFormat("0000").format(++seq));
            System.out.print("목표 금액을 설정해주세요: " );
            BigDecimal goal = scanner.nextBigDecimal();
            System.out.printf("\n %s 계좌가 발급되었습니다.\n", owner);
            //Account 생성자를 호출해서 account라는 객체를 생성했다.
            SavingAccount account = new SavingAccount(ano, owner, new BigDecimal("0"), goal);
            return account ;
        }catch (Exception e){
            System.out.println("계좌를 생성하는 과정에서 오류가 발생하였습니다.");
            return null;
        }
    }
}
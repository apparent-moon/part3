package bank;

import account.SavingAccount;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class SavingBank extends Bank {

    public void withdraw(SavingAccount account) throws Exception {
        HashMap<String, InterestCalculator> interestCalculators = new HashMap<>();
        interestCalculators.put("S", new SavingInterestCalculator());
        try {
            BigDecimal accountBalance = account.getBalance();
            BigDecimal accountGoalAmount = account.getGoalAmount();

            // output 변수로 출금 할 금액을 입력받는다.
            System.out.printf("출금하실 금액을 입력해주세요 (잔액:%s원): \n", df.format(accountBalance));
            BigDecimal output = scanner.nextBigDecimal();

            // 만약 출금 하려는 금액이 잔액보다 클 경우
            if (accountGoalAmount.compareTo(output) > 0) {
                System.out.println("목표금액을 달성해야 출금이 가능합니다. 처음 화면으로 돌아갑니다.\n");
                System.out.println("=====================================================");
                System.out.printf("계좌종류: %s | 계좌번호: %s | 계좌주명: %s | 잔액: %s원 | 목표금액 : %s원\n", account.getCategory(), account.getAccNo(), account.getOwner(), df.format(accountBalance), df.format(accountGoalAmount));
                System.out.println("=====================================================");
            } else {
                BigDecimal interest = interestCalculators.get("S").getInterest(accountBalance);
                System.out.println("현재 " + account.getAccNo() + " 계좌에 지급되어있는 이자는 " + df.format(interest) + "원 입니다.");
                account.setBalance(account.getBalance().add(interest));
                System.out.printf(output + "원 출금이 완료되었습니다.\n");
                account.setBalance(account.getBalance().subtract(output));
                System.out.println("=====================================================");
                System.out.printf("계좌종류: %s | 계좌번호: %s | 계좌주명: %s | 잔액: %s원 | 목표금액 : %s원\n", account.getCategory(), account.getAccNo(), account.getOwner(), df.format(account.getBalance()));
                System.out.println("=====================================================");
            }
        } catch (Exception e) {
            System.out.println("출금하는 과정에서 오류가 발생하였습니다.");
            return;
        }
    }

    public void deposit(SavingAccount account) throws Exception {
        try {
            System.out.print("입금하실 금액을 입력해주세요 : ");
            BigDecimal input = scanner.nextBigDecimal();
            System.out.println("입금이 완료되었습니다.\n");
            System.out.println("========================================================================");
            account.setBalance(account.getBalance().add(input));
            System.out.printf("계좌종류: %s | 계좌번호: %s | 계좌주명: %s | 잔액: %s원 | 목표금액 : %s원\n", account.getCategory(), account.getAccNo(), account.getOwner(), df.format(account.getBalance()), df.format(account.getGoalAmount()));
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
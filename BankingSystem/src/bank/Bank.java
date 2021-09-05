package bank;

import account.Account;
import account.SavingAccount;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.*;

public class Bank {
    //TODO: Bank 클래스는 출금, 입금, 송금, 계좌 생성, 계좌 검색 기능들을 갖고 있습니다.
    protected static Scanner scanner = new Scanner(System.in);
    protected static int seq = 0;
    public static DecimalFormat df = new DecimalFormat("#,###");

    // 뱅킹 시스템의 기능들
    // 출금 메소드 withdraw.
    public void withdraw() throws Exception {

        // TODO: key, value 형태의 HashMap을 이용하여 interestCalculators 구현
        // key: category
        // value: category의 InterestCalculator 인스턴스
        HashMap<String, InterestCalculator> interestCalculators = new HashMap<>();
        interestCalculators.put("N", new BasicInterestCalculator());

        Account account;
        while (true) {
            // 계좌번호 입력
            System.out.println("\n출금하실 계좌의 계좌번호를 입력하세요: ");
            String accountNum = scanner.next();
            if (findAccount(accountNum) == null) {
                System.out.println("존재하지 않는 계좌번호입니다. 계좌번호를 다시 입력해주세요.");
                // 재귀호출을 통해서 올바른 값이 들어올때까지 해당 과정을 진행한다.
                withdraw();
            } else {
                // 계좌를 찾아서 account 변수에 집어넣음
                account = findAccount(accountNum);

                // 만약 적금계좌 타입의 계좌가 입력되었다면 SavingBank.java의 메소드를 실행한다.
                // Q. 질문 여기서 this가 가리키는게 account가 맞는지??? 왜 this밖에 안되는건지 이해가 안간다.
                if (account.getCategory().equals("S")) {
                    SavingBank bank = (SavingBank) this;
                    bank.withdraw((SavingAccount) account); // == ((SavingBank) this).withdraw((SavingAccount) account);
                    return;
                }break;
            }
        }

        try {
            BigDecimal accountBalance = account.getBalance();

            // output 변수로 출금 할 금액을 입력받는다.
            System.out.printf("출금하실 금액을 입력해주세요 (잔액:%s원): \n", df.format(accountBalance));
            BigDecimal output = scanner.nextBigDecimal();

            // 만약 출금 하려는 금액이 잔액보다 클 경우
            if (accountBalance.compareTo(output) < 0) {
                System.out.println("해당 금액은 출금할 수 없는 금액입니다. 처음 화면으로 돌아갑니다.\n");
                System.out.println("=====================================================");
                System.out.printf("계좌종류: %s | 계좌번호: %s | 계좌주명: %s | 잔액: %s원\n", account.getCategory(),
                        account.getAccNo(), account.getOwner(), df.format(accountBalance));
                System.out.println("=====================================================");
            } else {
                BigDecimal interest = interestCalculators.get("N").getInterest(accountBalance);
                System.out.println("현재 " + account.getAccNo() + " 계좌에 지급된 이자는 " + df.format(interest) + "원 입니다.");
                account.setBalance(account.getBalance().add(interest));
                System.out.printf(output + "원 출금이 완료되었습니다.\n");
                account.setBalance(account.getBalance().subtract(output));
                System.out.println("=====================================================");
                System.out.printf("계좌종류: %s | 계좌번호: %s | 계좌주명: %s | 잔액: %s원 \n", account.getCategory(), account.getAccNo(), account.getOwner(), df.format(account.getBalance()));
                System.out.println("=====================================================");
            }
        } catch (Exception e) {
            System.out.println("출금하는 과정에서 오류가 발생하였습니다.");
            return;
        }
    }

    // 입금 메소드 deposit.
    public void deposit() throws Exception {
        Account account;
        while (true) {
            // 계좌번호 입력
            System.out.print("\n입금하실 계좌의 계좌번호를 입력해주세요 : ");
            String accountNum = scanner.next();
            if (findAccount(accountNum) == null) {
                System.out.println("존재하지 않는 계좌번호입니다. 계좌번호를 다시 입력해주세요.");
                // 재귀호출을 통해서 올바른 값이 들어올때까지 해당 과정을 진행한다.
                deposit();
            } else {
                // 계좌를 찾아서 account 변수에 집어넣음
                account = findAccount(accountNum);
                if (account.getCategory().equals("S")) {
                    SavingBank bank = (SavingBank) this;
                    bank.deposit((SavingAccount) account); // == ((SavingBank) this).withdraw((SavingAccount) account);
                    return;
                }
                break;
            }
        }
        try {
            System.out.print("입금하실 금액을 입력해주세요 : ");
            BigDecimal input = scanner.nextBigDecimal();
            System.out.println("입금이 완료되었습니다.\n");
            System.out.println("=====================================================");
            account.setBalance(account.getBalance().add(input));
            System.out.printf("계좌종류: %s | 계좌번호: %s | 계좌주명: %s | 잔액: %s원 \n", account.getCategory(), account.getAccNo(),
                    account.getOwner(), df.format(account.getBalance()));
            System.out.println("=====================================================");
        } catch (Exception e) {
            System.out.println("입금하는 과정에서 오류가 발생하였습니다.");
            return;
        }

    }

    // 계좌를 생성하는 createAccount 메소드.
    public Account createAccount() throws InputMismatchException {
        try {
            System.out.print("예금계좌명 혹은 예금계좌 소유자의 이름을 입력해주세요: ");
            String owner = scanner.next();
            String ano = String.format(new DecimalFormat("0000").format(++seq));
            System.out.printf("%s 계좌가 발급되었습니다.\n", owner);
            System.out.println("=====================================================");
            // Account 생성자를 호출해서 account라는 객체를 생성했다. 계좌의 초기금액은 0원이므로 0으로 선언했다.
            Account account = new Account(ano, owner, new BigDecimal("0"));
            return account;
        } catch (Exception e) {
            System.out.println("계좌를 생성하는 과정에서 오류가 발생하였습니다.");
            return null;
        }
    }

    //이체, 출금, 송금을 위해서 계좌를 검색하여 반환해주는 findAccount 메소드
    public Account findAccount(String accNo){
        //centralBank 객체 호출
        CentralBank centralBank = CentralBank.getInstance();
        //centralBank객체 안에 있는 List를 호출한다
        List<Account> accountList = centralBank.getAccountList();

        //accountList list를 순회하면서, accNo와 일치하는 account객체가 있다면 반환한다.
        for(Account account : accountList) {
            if(account.getAccNo().equals(accNo)) {
                return account;
            }
        }
        return null;
    }

    public void transfer() throws Exception{
        //TODO: 송금 메서드 구현
        // 잘못 입력하거나 예외처리시 다시 입력가능하도록
        //TODO
        System.out.println("\n송금하시려는 계좌번호를 입력해주세요.");
        //TODO
        System.out.println("\n어느 계좌번호로 보내시려나요?");
        //TODO
        System.out.println("\n본인 계좌로의 송금은 입금을 이용해주세요.");
        //TODO
        System.out.println("\n적금 계좌로는 송금이 불가합니다.");
        //TODO
        System.out.println("\n송금할 금액을 입력하세요.");
        //TODO
        }
    }

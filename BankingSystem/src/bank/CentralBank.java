package bank;

import account.Account;

import java.util.ArrayList;

//싱글톤 패턴으로 설계한 중앙은행 클래스입니다. 싱글톤으로 구현 한 이유는, 은행은 단 하나이기 때문에 하나만 존재해야 하는 객체를 만들어야해서 싱글톤으로 구현한다.
public class CentralBank {
    //자신의 타입인 정적 필드를 선언하고 객체를 생성하여 초기화한다.
    //외부에서는 불가능하지만, 클래스 내부에서는 private 접근제한자로 선언되어있어도 new연산자로 생성자 호출이 가능하다.
    //정적필드도 외부에서 값 변경을 하지 못하도록 private으로 선언한다.
    //이름이 instance인 생성자를 호출하여 instance 이름의 객체를 생성했다.
    private static CentralBank instance = new CentralBank();

    //생성자를 외부에서 호출하지 못하도록 private 접근제한자를 붙여서 선언한다.
    private CentralBank() {
    }

    //외부에서 호출할 수 있는 정적 메소드인 getInstance를 선언한다.
    public static CentralBank getInstance() {
        if(instance == null) {
            instance = new CentralBank();
        }
        //정적필드에서 참조하고 있는 자신의 객체(instance)를 return 한다.
        return instance;
    }

    //TODO: accountList(Account로 이루어진 ArrayList)
    private ArrayList<Account> accountList = new ArrayList<>();

    //private으로 선언되었으므로, 접근이 가능하도록 getter, setter를 선언한다.
    public ArrayList<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(ArrayList<Account> accountList) {
        this.accountList = accountList;
    }

    //TODO: BANK_NAME(은행명)
    //CentralBank 이름을 선언했다.
    String BANK_NAME = "CentralBank";
}

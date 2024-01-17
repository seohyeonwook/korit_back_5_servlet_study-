package com.study.servlet_study.service;//4 번 전송??

import com.study.servlet_study.entity.Account;//Account import했는데 
import com.study.servlet_study.repository.AccountRepository;

public class AccountService {
	private static AccountService instance; // 클래스의 유일한 인스턴스를 저장할 변수 //싱글톤
//	이 부분은 AccountService 클래스의 정적 변수로, 
//	클래스 레벨에서 하나의 인스턴스만을 유지하기 위해 사용됩니다.
//	static 키워드는 클래스가 로딩될 때 한 번만 초기화되고 모든 인스턴스가 이 값을 공유하게 만듭니다.
	
	private AccountRepository accountRepository; // 계정 정보를 저장하고 조회하는 데 사용할 Repository
//	AccountService 클래스 내에 AccountRepository 타입의 인스턴스 변수를 가지고 있습니다. 
//	이 변수를 통해 AccountService는 계정 관련 비즈니스 로직을 수행할 때 AccountRepository의 
//	메서드를 호출할 수 있습니다.
//	
	private AccountService() {
		accountRepository = AccountRepository.getInstance(); // AccountRepository의 인스턴스를 얻어와서 초기화
		
	}
	
//		이 메서드는 AccountService 클래스의 private 생성자입니다.
//		외부에서 이 클래스의 인스턴스를 직접 생성할 수 없도록 하기 위해
//		private로 선언되었습니다.
//		생성자 내부에서 accountRepository = AccountRepository.getInstance();
//	    코드를 통해 AccountRepository 클래스의 인스턴스를 가져와 accountRepository 
//	    변수에 초기화합니다.
//		이는 AccountService 클래스가 AccountRepository를 사용하기 위해 해당 인스턴스를
//		얻어오는 역할을 합니다.
	
	
	// 싱글톤 패턴에서 인스턴스를 반환하는 메서드
	public static AccountService getInstance() {
		if (instance == null ) {
			instance = new AccountService(); // 인스턴스가 없으면 새로운 인스턴스 생성
		}
		return instance; // 인스턴스 반환
	}
//
//		이 메서드는 AccountService 클래스의 싱글톤 인스턴스를 반환하는 정적 메서드입니다.
//		instance 변수가 null인 경우에만 새로운 인스턴스를 생성하고, 
//		그렇지 않으면 기존의 인스턴스를 반환합니다.
//		이렇게 하면 어플리케이션 내에서 하나의 AccountService 인스턴스만이 존재하도록 보장됩니다.
//		따라서 getInstance() 메서드를 통해 항상 동일한 인스턴스에 접근할 수 있게 됩니다.

	
	
	
	
	
	 // 계정을 추가하는 메서드
	public int addAccount(Account account) {
		return accountRepository.saveAccount(account); // AccountRepository를 통해 계정 정보를 저장
	}
	
//	이 메서드는 Account 객체를 매개변수로 받아서 해당 계정을 저장하는 역할을 합니다.
//	내부에서 accountRepository.saveAccount(account)를 호출하여 AccountRepository 클래스의
//	saveAccount 메서드를 사용하여 계정 정보를 저장합니다.
//	메서드는 저장이 성공하면 1을 반환하며, 실제로는 저장 여부를 나타내기 위한 값입니다.
	
	
	
	// 유저네임으로 계정을 조회하는 메서드
	public Account getAccount(String username) {
		return accountRepository.findAccountByusername(username); // AccountRepository를 통해 계정 조회
	}
//	이 메서드는 유저네임을 매개변수로 받아서 해당 유저네임에 해당하는 계정을 조회하는 역할을 합니다.
//	내부에서 accountRepository.findAccountByusername(username)를 호출하여 AccountRepository 클래스의 
//	findAccountByusername 메서드를 사용하여 계정을 조회합니다.
//	조회된 계정을 반환합니다. 만약 해당 유저네임에 해당하는 계정이 없으면 null을 반환합니다.
	
//	이렇게 AccountService 클래스는 실제로 비즈니스 로직을 담당하고, 
//	계정 저장 및 조회와 같은 기능을 AccountRepository 클래스에 위임하고 있습니다.
//    이렇게 하면 각 클래스가 담당하는 역할이 분리되어 코드가 구조적이고 유지보수가 용이해집니다.

}

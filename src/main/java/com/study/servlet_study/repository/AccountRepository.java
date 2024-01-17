package com.study.servlet_study.repository;//4 저장

import java.util.ArrayList;
import java.util.List;

import com.study.servlet_study.entity.Account;

public class AccountRepository {
	private List<Account> accountList; // 계정 정보를 저장하는 리스트
	
//	접근 제어자 (private): private는 해당 변수가 선언된 클래스 내에서만 
//	접근 가능하도록 접근을 제한하는 키워드입니다. 다른 클래스에서는 직접적인 접근이 불가능하며,
//	이 변수에 접근하려면 해당 클래스 내에서 제공하는 메서드를 통해야 합니다.
//
//	데이터 타입 (List<Account>): 변수가 담을 수 있는 데이터의 타입을 명시합니다. 
//	여기서는 List 인터페이스를 구현한 어떤 종류의 리스트를 사용할 것이고, 그 리스트의 요소
//	타입은 Account 클래스의 객체여야 합니다.
//
//	변수 이름 (accountList): 변수의 이름을 나타냅니다. 
//	이 변수는 accountList라는 이름으로 참조됩니다.
	
	
	//싱글톤 만들기
	private static AccountRepository instance; // 클래스의 유일한 인스턴스를 저장할 변수
	
	private AccountRepository () {
		accountList = new ArrayList<>();// 계정 정보를 저장할 리스트 초기화
	}
//	    productList = new ArrayList<>();://///
//
//		이 코드는 productList 변수를 ArrayList 객체로 초기화하는 것을 의미합니다.
//		ArrayList는 동적 배열 기반의 리스트 구조이며, 객체를 담을 수 있는 컬렉션입니다.
//		따라서 이 코드는 ProductRepository 클래스 내의
//		productList 변수가 ArrayList의 인스턴스를 참조하게 합니다.
//		
//		productList = null;:///////
//
//		이 코드는 productList 변수에 null 값을 할당하는 것을 의미합니다.
//		null은 Java에서 '아무 값도 없음'을 나타내는 특별한 값입니다.
//		따라서 이 코드는 ProductRepository 클래스 내의 productList 변수가 
//		더 이상 어떠한 객체도 참조하지 않음을 나타냅니다. 다시 말해, 
//		아무 것도 가리키지 않게 됩니다.
//		
//		간단히 말하면, 첫 번째 코드는 productList에 실제로 객체를 할당하고 참조하는 것이며, 
//		두 번째 코드는 productList를 아무 것도 가리키지 않게 만드는 것입니다.


	
	// 싱글톤 패턴에서 인스턴스를 반환하는 메서드
	public static AccountRepository getInstance() {
		if(instance == null) {//null 일때만 새로 생성해라
			instance = new AccountRepository(); // 인스턴스가 없으면 새로운 인스턴스 생성
		}
		return instance; // 인스턴스 반환

	}
//
//	싱글톤 패턴을 사용하는 이유는 여러 인스턴스가 생성되는 것을 방지하고, 
//	애플리케이션 전체에서 하나의 인스턴스를 공유하여 사용하기 위함입니다. 
//	이 패턴을 사용하면 리소스를 효율적으로 관리하고, 객체 간에 일관성 있는 상태를 유지할 수 있습니다.
//	클래스는 여러 곳에서 사용될 것으로 보이기 때문에, 싱글톤 패턴을 사용하여 인스턴스를 전역적으로 
//  공유하고자 한 것으로 추측됩니다.
	
	//끝
	
	 // 계정을 저장하는 메서드
	public int saveAccount(Account account) {
		accountList.add(account); // 계정 정보를 리스트에 추가
		return 1; // 성공적으로 저장되었음을 나타내는 값을 반환
	}
	
//	메서드 시그니처:
//
//		public int saveAccount(Account account): 이 메서드는 public으로 선언되었으며,
//		정수(int) 값을 반환합니다. 메서드 이름은 saveProduct이고, Product 
//		타입의 매개변수인 account를 받습니다.
//		
//	기능 설명:
//
//		accountList.add(account);: productList라는 리스트에 매개변수로 전달된 account 
//		객체를 추가합니다. accountList는 AccountRepository 클래스의 멤버 변수로 선언된 리스트입니다.
//		이 코드는 제품을 리스트에 추가하는 역할을 합니다.
//		
//	반환 값:
//
//		return 1;: 메서드는 정수 값을 반환하고 있습니다. 
//		실제로는 성공적으로 제품을 저장했는지 여부를 나타내기 위해 사용되는 것으로 보입니다. 
//		보통은 성공 시 1, 실패 시 0 또는 음수 등을 반환하는 관례를 따르기도 합니다.
//		따라서 이 메서드는 주어진 Account 객체를 accountList에 추가하고, 
//		성공 여부를 나타내는 값을 반환하는 것으로 이해할 수 있습니다.
	
	
	// 유저네임으로 계정을 찾는 메서드
	public Account findAccountByusername(String username) {
		Account findAccount = null; // 찾은 계정을 저장할 변수 초기화
		
//	    메서드 시그니처:
//
//			public Product findProductByproductname(String productname): 
//			이 메서드는 public으로 선언되었으며, 반환 타입은 Product입니다. 
//			메서드 이름은 findProductByproductname이고, 문자열(String) 값을 
//			매개변수로 받습니다.
//	
//			기능 설명:
//				
//			Product findProduct = null;: findProduct라는 변수를 선언하고 null로 초기화합니다.
//			이 변수는 나중에 제품 이름에 해당하는 제품을 찾았을 때 해당 제품을 참조할 때 사용됩니다.
//	
		
		for(Account account : accountList) {
			if(account.getUsername().equals(username)) {// 유저네임이 일치하는 계정을 찾으면
				findAccount = account; // 찾은 계정으로 변수를 업데이트하고
				break;// 반복문 종료
				
//				반복문 및 조건문:
//
//					for (Product product : productList) {:
//						productList라는 리스트에 대한 향상된 for 루프를 사용하여 모든 제품을 순회합니다.
//					}
//					if (product.getProductName().equals(productname)) {: 
//						현재 순회 중인 제품의 이름이 주어진 productname과 같은지를 확인합니다.
//					}
//					findProduct = product; break;: 
//						만약 이름이 일치하는 제품을 찾으면 findProduct 변수에 해당 
//						제품을 할당하고 반복문을 종료합니다.
//						
			}
			
		}
		return findAccount; // 찾은 계정 반환
	}


//	반환 값:
//
//	return findProduct;: 
//  메서드는 찾은 제품을 반환합니다. 만약 일치하는 제품이 없다면 
//  findProduct는 여전히 null일 것이고, 이는 찾지 못했음을 나타냅니다.
//	따라서 이 메서드는 주어진 제품 이름에 해당하는 제품을 찾으면 
//	해당 제품을 반환하고, 찾지 못한 경우에는 null을 반환하는 것으로 이해할 수 있습니다.
//
	

	

}

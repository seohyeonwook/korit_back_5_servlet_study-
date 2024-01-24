package com.study.servlet_study.test;

import java.util.function.Consumer;

class Print<T> implements Consumer<T> {//클래스화
	// Consumer 인터페이스 자체적으로 생성불가  추상화적인 애들을 구체화시켜야함 ->객체화 시켜야함
	// Consumer라는 제네릭을 implements를 하려면 class도 제네릭 타입이어야한다 -> class Print<T>
	// 추상메서드니까 무조건 오버라이드 해줘야해
	

	@Override
	public void accept(T t) { //int a 
		System.out.println(t);//t라는 매개변수받아서 그냥 호출 해주는
		
	}
	
}

public class LamdaMain {
	
	public static void main(String[] args) {
		Consumer<Author> print0 = new Print<Author>();//Author 객체가 제네릭 타입이니까 위에 T도 다 Author가 들어간다
		
		
		Consumer<Author> print1 = new Consumer<Author>() {
			
			@Override
			public void accept(Author author) {//임시로 생성한다 위에 오버라이딩이랑 같은모양 근데 위에서author라고했으니까
											//T대신 author
				System.out.println(author);
			}
		}; 
		Consumer<Author> print2 = (author) -> System.out.println(author); //람다 -> 추상메서드가 무조건 하나만 있어야한다
		//정의해서(author) -> System.out.println(author) 생성한다. print2 
		
		//자료형 정해짐Author라는 객체
		// 세개 다 같은 코드다
		print0.accept(Author.builder().authorId(1).authorName("김준일").build());
		print1.accept(Author.builder().authorId(2).authorName("김준이").build());//주소값이 들어간거라고 생각
		print2.accept(Author.builder().authorId(3).authorName("김준삼").build());
		
		forEach(print2);//Consumer<Author>자료형이 람다식이랑 같다
		forEach(author -> System.out.println(author));
		
		
		
	}
	
	public static void forEach(Consumer<Author> action) {
		action.accept(Author.builder().authorId(4).authorName("김준사").build());
		//주소를 넘겨주는다는 개념이 중요해
		//데이터의 자료형이 정해져 있어야 한다!!!!!!!!
		
	}

}

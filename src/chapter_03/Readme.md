# Kotlin in Action 3장 - 함수 정의와 호출

## 3.1 컬렉션

- set, list, map이 있다.
- 코틀린 자체 컬렉션을 제공하지 않고, 자바 컬렉션을 제공한다.
- `println(list.javaClass) -> class java.util.ArrayList`
- 4장에서 상세히 다룬다.

## 3.2 함수 호출

- [ReadMe](./function_parameter)
- 이름을 붙인 인자
- 디폴드 파라미터 값
- @JvmOverloads

### 3.2.3 최상위 함수와 프로퍼티

- [Readme](./top_level)
- 코틀린파일 최상위(클래스 밖에) 정의한 함수, 프로퍼티(변수)
- 최상위 함수, 프로퍼티를 자바파일에서 사용할 경우
- @JvmName

## 3.3 확장 함수

- [Readme](./extension_function)
- 확장 함수의 사용
- java파일에서 확장 함수 호출
- 확장 함수 override (불가능)
- 확장 프로퍼티 - 클래스에 프로퍼티를 추가한다.
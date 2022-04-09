# if를 when으로 변경

- https://hongku.tistory.com/352
- 글을 적는것 보단 코드를 보는 것이 더 좋을것 같다.


### nullable 스마트 캐스팅

> 예제파일 : [IfToWhen.kt](IfToWhen.kt)
> 
> 기본적으로 `nullable`한 변수가 있을 때, if문을 이용해서 null-check를 한 경우
>
> 해당 if 문에선 non-null로 변수가 동작한다.
>
> 하지만 var변수인 경우, if문 내에서 null값으로 바뀔 위험이 있기 때문에, non-null로 동작하지 않는데
>
> 이것도, 만약 해당 변수의 생명주기가 메서드 내부라면, non-null로 생각하지만, 최상위 변수(다른곳에서 null이 될 수 있을 때)는 if문 내에도 nullable로 인식된다.

- 해당 메서드 내의 지역변수인 경우, 상황에 따라 non-null인지, nullable인지 체크해서 캐스팅이 이루어진다.
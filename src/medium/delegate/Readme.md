# BaseActivity대신 Delegate를 사용하자.

> [Medium Link](https://levelup.gitconnected.com/android-nightmares-base-classes-ccf55dbd0604)

특정 클래스 or 객체가 A, B 기능을 동시에 가지고있는 상태에서, A기능만 필요할 때, 기존에는 Boolean의 Flag를 이용하여 A기능만 수행하도록 하였지만, DelegatePattern을 이용하여 쉽게 구현할 수 있다.

이를 위해선 아래 두가지가 필요하다.

1. A, B기능 각각의 인터페이스와, 이를 구현한 구현체 (Single Responsibility Principle을 준수해야 한다.)
2. 특정 클래스 or 객체가 A, B기능이 있는 인터페이스를 구현하고, 이를 구현체에게 위임한다.


---

이런 케이스는?

- viewModel을 참조해야하는 케이스
  - 기존 클래스에서 구현하는 경우, 자유롭게 viewModel을 사용할 수 있지만, 특정 구현체는 viewModel을 가지고있지 않는다.
- 위 1번에서 확장하여 viewModel을 필요로 하지만, 서로다른 a, b클래스에서 다른 viewModel을 주어야 하는 경우

---

다른 Medium link

> [Fun With Delegation in Kotlin](https://medium.com/@imdjay/fun-with-delegation-in-kotlin-28be084e2d5)

위임패턴은 하나의 객체가 가지고있는 특정 책임을 다른 객체에게 위임하는것을 의미한다.

이렇게 다른 객체에게 위임함으로써, 다른 객체가 그 역할을 수행한다.
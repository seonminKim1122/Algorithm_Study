# Direct Address Table
```
가장 간단한 형태의 해시테이블로 키 값을 곧 주소로 사용하는 테이블

ex)
1 ~ 10 까지의 숫자가 여러 개 주어질 때 0 ~ 10 index 를 갖는 리스트를 만들고 
숫자의 값을 곧 index 로 사용하는 방식
```
하지만 키 값을 바로 주소로 사용할 수 없는 경우 문제가 발생하고 이를 극복하기 위해 
등장한 것이 Hash


# Hash
```
원본 데이터(key)를 정수로 변환하는 Hash Function 을 만들고 
Hash Function(key) 의 리턴값(HashCode)을 주소로 사용하면 
Direct Address Table 의 문제를 해결
```

하지만 Hash Function 에 따라 동일한 주소에 너무 많은 데이터가 저장되는 문제(Chaining 방식)
가 발생할 수 있고 뿐만 아니라 key 값 중복에 따른 Collision 이 발생할 수 있다

수정
<br></br>
#### 참고자료
https://baeharam.netlify.app/posts/data%20structure/hash-table

# HashMap

- key - value 형태로 데이터를 저장하고 각 value 들이 key 에 매핑
- key 에 해시 함수를 적용한 해시 코드를 통해 value 에 접근하니 탐색, 삽입, 삭제 등의 연산이 O(1)
- key 는 중복 허용하지 않고, value 는 중복 허용

# HashSet
- 내부적으로 HashMap 을 사용해 데이터를 저장하므로 Hash Table 과 유사한 자료구조로 데이터 저장
- 중복을 허용하지 않음

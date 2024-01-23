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


<br></br>
#### 참고자료
https://baeharam.netlify.app/posts/data%20structure/hash-table
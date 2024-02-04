# Binary & Parametric Search

### Binary Search

- 정렬된 자료에 대해서 원하는 값을 빠르게 찾는 알고리즘
- start index, end index, mid index 를 이용해 찾고자 하는 값이 mid index 보다 크면 start 를 키우고 반대라면 end 를 줄이는 방식이다

```java
int start = 0;
int end = arr.length;

while (start <= end) {
    int mid = (start + end) / 2;
    
    if (arr[mid] < target) {
        start = mid + 1;
    } else if (arr[mid] > target) {
        end = mid - 1;
    } else {
        return mid;
    }
}
```

### Parametric Search

- 원래 주어진 문제를 결정 문제로(크냐 작냐) 변환하여 Binary Search 를 이용해 문제를 해결하는 방법
- 어떤 조건을 만족하는 최소값 또는 최대값을 찾으라고 하는 문제로 변환이 가능해야 한다.
- ex) 배터리가 얼마나 차있는지 출력하는 문제
    
    ```java
    /*
    ['#', '#', '#, '_', '_'] -> 60%
    와 같이 배터리가 얼마나 충전되어 있는지 찾는 경우
    
    이 문제를 변환하면 '#' 가 등장하는 마지막 위치가 어디인가를 묻는 문제이고 이는
    특정 인덱스의 값이 '_' 인가 '#' 인가에 따라 탐색하는 범위를 조절하는 Binary Search
    문제로 바뀌게 된다.
    */
    ```
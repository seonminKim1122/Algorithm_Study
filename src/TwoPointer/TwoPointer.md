# Two Pointer

- 배열을 탐색할 때 2개의 포인터를 이용하는 알고리즘
- 2개의 포인터를 이동시키면서 조건에 따라 end pointer 또는 start pointer 를 이동시키면서 문제를 해결하는 방식
- 대표적으로 배열의 구간합이 target 을 만족하는 구간 찾기 문제

```
int target = 30;
int[] numbers = {1, 3, 5, 7, 9, 14};
int[] S = {0, 1, 4, 9, 16, 25, 39};

int start = 1;
int end = 1;

while (start <= 6 && end <= 6) {
    int temp = S[end] - S[start - 1];
    if (temp > target) {
        start++;
    } else if (temp < target) {
        end++;
    } else {
        break;
    }
}

System.out.println(start + " " + end);
```
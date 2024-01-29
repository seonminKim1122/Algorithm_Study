# Prefix Sum(구간 합)

- 누적합을 통해 구간합을 구함으로써 O(1) 의 시간복잡도로 구간합을 구하는 방식이다.
- 물론 누적합을 구하기 위한 시간은 입력의 크기 N 에 따라 달리진다.
- 크기가 N 인 1차원 배열이면 누적합 계산에 O(N)
크기가 N * N 인 2차원 배열이면 누적합 계산에 O(N*N)

### 1차원 배열에서의 Prefix Sum

- S[i] : 0번째 원소부터 i번째 원소까지의 합
- prefixSum(a, b) : a번째 원소부터 b번째 원소까지의 합 → S[b] - S[a - 1]

```
int[] numbers = {1, 2, 3, 4, 5, 6};

int[] S = new int[7];
for (int i = 1; i < 6; i++) {
    S[i] = S[i - 1] + numbers[i - 1];
}

// a 번째 원소부터 b 번째 원소까지의 합
int a = Integer.parseInt(br.readLine()) + 1;
int b = Integer.parseInt(br.readLine()) + 1;
System.out.println(S[b] - S[a - 1]);
```

### 2차원 배열에서의 Prefix Sum

- S[i][j] : (0, 0) 에서 (i, j)까지의 직사각형의 합 → S[i - 1][j] + S[i][j - 1] - S[i - 1][j - 1] + numbers[i][j]
- prefixSum((x1, y1), (x2, y2)) : (x1, y1) ~ (x2, y2) 까지의 직사각형의 합
    
    → S[x2][y2] - S[x1 - 1][y2] - S[x2][y1 - 1] + S[x1 - 1][y1 - 1]
    

```
int[][] numbers = {
    {1, 2, 3, 4, 5},
    {6, 7, 8, 9, 10},
    {11, 12, 13, 14, 15},
    {16, 17, 18, 19, 20},
    {21, 22, 23, 24, 25}
};

int[][] S = new int[6][6]; // 편의를 위해 6 x 6 으로 선언

for (int i = 1; i < 6; i++) {
    for (int j = 1; j < 6; j++) {
        S[i][j] = S[i - 1][j] + S[i][j - 1] - S[i - 1][j - 1] + numbers[i - 1][j - 1];
    }
}

int x1 = Integer.parseInt(br.readLine()) + 1;
int y1 = Integer.parseInt(br.readLine()) + 1;

int x2 = Integer.parseInt(br.readLine()) + 1;
int y2 = Integer.parseInt(br.readLine()) + 1;

System.out.println(S[x2][y2] - S[x1 - 1][y2] - S[x2][y1 - 1] + S[x1 - 1][y1- 1]);
```
# FloydWarshall

### 정의

```
모든 정점 간의 최단 경로를 구하는 알고리즘
```


### 특징

```
1) 음의 간선을 사용할 수 있다
2) 시간복잡도 : O(N^3)
```


### Floyd-Warshall 동작 원리

```
정점의 개수 : N

1. 1번 노드를 중간 노드로 설정하여 최단 경로 갱신
2. 2번 노드를 중간 노드로 설정하여 최단 경로 갱신
3. ...
4. N번 노드를 중간 노드로 설정하여 최단 경로 갱신

이렇게 하면 1번의 link 로 갈 수는 없더라도 중간 노드들을 껴서 갈 수 있는 경우에 대해서까지 전부 탐색이 가능하다

따라서 모든 정점 간의 최단 경로를 구할 수 있게 되는 것이다
```


### Sample Code

```java
public class TwoPointer._n1644_ {

	static int INF = 987654321;

    public static void main(String[] args) {
	    int[][] graph = new int[5][5];
	    /*
	    간선 정보
	    1 -> 3 : 2
	    3 -> 1 : 2
	    3 -> 4 : 3
	    4 -> 3 : 3
	    2 -> 4 : 4
	    4 -> 2 : 4
	    4 -> 5 : 6
	    5 -> 4 : 6
		
		예상 결과
		[[ 0  9 2 5 11],
		 [ 9  0 7 4 10],
		 [ 2  7 0 3  9],
		 [ 5  4 3 0  6],
		 [11 10 9 6  0]]
	    */
	    for (int i = 0; i < 5; i++) {
	        Arrays.fill(graph[i], INF);
	        graph[i][i] = 0;
	    }
	    
	    graph[0][2] = graph[2][0] = 2;
	    graph[2][3] = graph[3][2] = 3;
	    graph[1][3] = graph[3][1] = 4;
	    graph[3][4] = graph[4][3] = 6;
	    
	    System.out.println(Arrays.deepToString(floydWarshall(graph)));
    }

	public static int[][] floydWarshall(int[][] graph) {
		int N = graph.length; // 정점의 개수

		int[][] dist = new int[N][N];
		for (int i = 0; i < N; i++) { // dist 배열 초기화
			for (int j = 0; j < N; j++) {
				dist[i][j] = graph[i][j];
			}
		}
		
		for (int mid = 0; mid < N; mid++) {
			for (int from = 0; from < N; from++) {
				for (int to = 0; to < N; to++) {
					dist[from][to] = Math.min(dist[from][to], dist[from][mid] + dist[mid][to])
				}
			} 
		}
	
		return dist;
	}
}
```

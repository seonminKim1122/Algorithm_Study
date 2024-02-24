# BellmanFord

### 정의
`한 노드에서 다른 모든 노드까지의 최단 경로를 구하는 알고리즘`


### Bellman-Ford vs Dijkstra

```
다익스트라 알고리즘과 벨만-포드 알고리즘 모두 하나의 노드를 출발점으로 하여 다른 노드들까지의 최단 경로를 구하는 알고리즘이다. 다익스트라는 양의 가중치만을 허용하지만 벨만-포드는 음의 가중치도 허용한다는 차이가 있다`
```


### Bellman-Ford 동작 원리

```
총 노드의 개수 : N

1. 시작 노드 선택
2. 모든 간선에 대해 탐색하며 시작 노드에서 도달할 수 있는 케이스는 최단 거리 갱신
3. 2번 작업을 N - 1번 반복

N - 1 번 반복하는 이유는 노드의 개수가 N 개 일 때 아무리 많은 간선으로 연결이 되어 있더라도 N - 1 개의 간선을 거치고 나면 최단 거리가 확정이 되기 때문이다

단, 간선의 가중치에 음수를 허용하기 때문에 '음수 사이클'이 발생할 수 있는데 이는 마지막에 2번 작업을 한 번 더 수행하여 최단거리가 갱신되는 노드가 있는지 확인하는 것으로 알 수 있다

최단 거리를 구했는데 또 갱신이 된다는 것은 최단 거리가 음수여서 무한히 작아질 수 있다는 뜻이기 때문이다
```


### Sample Code

```
public class Main {
    
    static int INF = 987654321;
    
	public static void main(String[] args) {
		List<Link> links = new ArrayList<>();

		/*
		간선 정보 입력 받아서 links 리스트에 추가
		ex)
		1 -> 2 : 10
		1 -> 3 : 4
		2 -> 3 : -90
		
		시작점 1일 때 예상 결과
		dist : [INF, 0, 10, -80]
		*/
		links.add(new Link(1, 2, 10));
		links.add(new Link(1, 3, 4));
		links.add(new Link(2, 3, -90));

		int[] result = bellmanFord(links, 1, 3);
		System.out.println(Arrays.toString(result));
	}

    public static int[] bellmanFord(List<Link> links, int start, int N) {
        /*
        links : 간선 정보
        start : 시작 노드
        N : 노드 개수
        */
	    int[] dist = new int[N + 1];
	    Arrays.fill(dist, INF);
	    dist[start] = 0;

		for (int i = 1; i <= N - 1; i++) { // 최단 거리 갱신 작업 n - 1번 반복
			
			for (Link link : links) {
				if (dist[link.from] == INF) continue; // INF 면 시작 노드와 단절된 노드
				
				if (dist[link.to] > dist[link.from] + link.cost) {
					dist[link.to] = dist[link.from] + link.cost;
				}
			}
		}

		for (Link link : links) { // 음수 사이클 확인위해 1번 더 최단거리 갱신 수행
			if (dist[link.from] == INF) continue;
				
			if (dist[link.to] > dist[link.from] + link.cost) { // 음수 사이클 존재
				dist[link.to] = -INF;
			}
		}

		return dist;
    }
	
	private static class Link {
		int from;
		int to;
		int cost;
		
		Link (int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
}
```







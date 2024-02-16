# Dijkstra


### 정의

`한 노드에서 다른 노드들까지의 최단 경로를 구하는 알고리즘`

### Dijkstra vs Bellman-Ford

```
간선의 가중치에 대해서 다익스트라는 양의 가중치만을 허용하고 벨만-포드는 음수 가중치까지도 허용한다는 차이가 있다
```


### Dijkstra 동작 원리

```
총 노드의 수 : N

1. 시작 노드를 선택
2. 아직 방문하지 않는 노드 중 최단 경로를 갖는 노드 선택해서 방문
3. 2 번에서 선택된 노드와 연결된 노드들 최단 경로 갱신
4. 모든 노드를 방문할 때까지 2, 3 번 과정 반복

이 과정을 통해서 시작 노드로부터 다른 모든 노드들까지의 최단 경로를 구할 수 있는 이유는 간선의 가중치가 '양수'로 이루어져있기 때문이다

(A -> B) < (A -> C) 라고 할 때
(A -> B) < (A -> C -> B) 가 보장이 되므로 한 번 방문한 노드에 대해서는 다시 갱신을 해줄 필요가 없는 것이다

만약 음수 가중치를 허용한다면 위 내용을 보장할 수 없으므로 벨만-포드 알고리즘을 적용해야 한다
```


### Sample Code 1 : 반복문

```java
public class Main {

	static int INF = 987654321;
	
	public static void main(String[] args) {
		// 노드 개수 : 5 개
		List<Link>[] links = new List[5 + 1];
		for (int i = 1; i <= 5; i++) {
		    links[i] = new ArraysList<>();
		}

		/*
		간선 정보
		1 -> 2 : 5
		1 -> 3 : 2
		3 -> 2 : 1
		3 -> 4 : 10
		3 -> 5 : 1
		5 -> 4 : 6
		
		예상 결과
		start 1 : [INF, 0, 3, 2, 9, 3]
		*/
		link[1].add(new Link(2, 5));
		link[1].add(new Link(3, 2));
		link[3].add(new Link(2, 1));
		link[3].add(new Link(4, 10));
		link[3].add(new Link(5, 1));
		link[5].add(new Link(4, 6));
		
		System.out.println(Arrays.toString(dijkstra(links, 1, 5)));
	}
	
	public static int[] dijkstra(List<Link>[] links, int start, int N) {
		boolean[] visited = new boolean[N + 1];
		int[] dist = new int[N + 1];
		
		Arrays.fill(dist, INF);
		dist[start] = 0;

		for (int i = 0; i < N; i++) { // 1번 반복할 때마다 노드 1개 방문
		    int node = -1;
		    int max = INF;
			
			for (int idx = 1; idx <= N; idx++) { // 최단 경로를 갖는 노드 찾기
				if (!visited[idx] && dist[idx] < max) {
					node = idx;
					max = dist[idx];
				}
			}
			
			visited[node] = true;
			
			for (Link link : links[node]) { // 찾은 노드에서 갈 수 있는 노드 갱신
				if (dist[link.node] > dist[node] + link.cost) {
				    dist[link.node] = dist[node] + link.cost;
				}
			}
		}
		
		return dist;
	}
	
	private static class Link {  
	    int node;  
	    int cost;  
  
	    Link (int node, int cost) {  
	        this.node = node;  
	        this.cost = cost;  
	    }
    }
}
```


### Sample Code 2 : 우선순위 큐

```java
public class Main {

	static int INF = 987654321;

	public static void main(String[] args) {
		// 노드 개수 : 6 개
		List<Link>[] links = new List[5 + 1];
		for (int i = 1; i <= 5; i++) {
		    links[i] = new ArraysList<>();
		}
		
		/*
		간선 정보
		1 -> 2 : 5
		1 -> 3 : 2
		3 -> 2 : 1
		3 -> 4 : 10
		3 -> 5 : 1
		5 -> 4 : 6
		
		예상 결과
		start 1 : [INF, 0, 3, 2, 9, 3]
		*/
		links[1].add(new Link(2, 5));
		links[1].add(new Link(3, 2));
		links[3].add(new Link(2, 1));
		links[3].add(new Link(4, 10));
		links[3].add(new Link(5, 1));
		links[5].add(new Link(4, 6));
		
		
		System.out.println(dijkstra(links, 1, 5));
	}
	
	public static int[] dijkstra(List<Link>[] links, int start, int N) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((n1, n2) -> n1[1] - n2[1]);
		priorityQueue.add(new int[]{start, dist[start]});
		
		while (!priorityQueue.isEmpty()) {
			int[] now = priorityQueue.poll();
			
			int from = now[0];
			for (Link link : links[from]) {
			    if (dist[link.node] > dist[from] + link.cost) {
				    dist[link.node] = dist[from] + link.cost;
				    priorityQueue.add(new int[]{link.node, dist[link.node]});
			    }
			}
		}
		
		return dist;
	}
	
	private static class Link {  
	    int node;  
	    int cost;  
  
	    Link (int node, int cost) {  
	        this.node = node;  
	        this.cost = cost;  
	    }
    }
}
```


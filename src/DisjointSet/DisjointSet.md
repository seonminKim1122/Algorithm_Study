# Disjoint Set(Union-Find)

### 개념
주어진 정보를 바탕으로 서로소 집합을 만드는 알고리즘<br>
이를 통해 두 원소가 같은 집합에 속하는지 아닌지를 판단할 수 있다.<br>
부모 배열을 이용해서 루트 부모가 동일하면 원소끼리 같은 집합, 다르면 다른 집합으로 판단한다.

### 코드
```
# 기본적으로 각각의 원소를 서로 독립된 집합으로 두고 주어진 정보를 바탕으로 합해가는 과정이 발생한다
# union(node1, node2) -> 두 원소의 루트 부모를 확인하고 다르면 하나로 합하는 메서드
# find(node) -> 한 원소의 루트 부모를 확인하는 메서드


void union(int a, int b) {
    int parentA = find(a);
    int parentB = find(b);
    
    if (parentA == parentB) return;
    
    parent[parentB] = parentA
}

int find(int a) {
    if (parent[a] == a) return a;
    return find(parent[a])
}
```
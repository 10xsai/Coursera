"""
A very effective improvement, it's called weighting. And it might have occurred to
you while we are looking at these algorithms. The idea is to when
implementing the quick union algorithm take steps to avoid having tall trees. If
you've got a large tree and a small tree to combine together what you want to try
to do is avoid putting the large tree lower, that's going to lead to long tall
trees. And there's a relatively easy way to do that. What we'll do is we'll keep
track of the number of objects in each tree and then, we'll maintain balance by
always making sure that we link the root of the smaller tree to the root of the
larger tree. So, we, we avoid this first situation here where we put the larger
tree lower. 

Main difference comes during the Union operation. We'll compare height and make union.
Maintain extra array sz[i] to count number of objects in the tree rooted at i
"""

class WeightedQuickUnionUF:
    ids = []
    sz = []
    
    def __init__(self, N):
        self.ids = [i for i in range(N)]
        self.sz = [0]*N
        
    def union(self, p: int, q: int) -> None:
        # First we'll get the parents for p and q and 
        # we'll attach the p's parent to q's parent
        i = self.root(p)
        j = self.root(q)
        
        if i == j: return
        
        if self.sz[i] < self.sz[j]: self.ids[i] = j; sz[j] += sz[i]
        else                      : self.ids[j] = i; sz[i] += sz[j]
    
    def connected(self, p: int, q: int) -> bool:
        # Finding if parents are same or not
        return self.root(p) == self.root(q)
    
    def root(self, p: int) -> int:
        while p != self.ids[p]:
            p = self.ids[p]
        return p
    

if __name__=='__main__':
    N = int(input())
    
    uf = WeightedQuickUnionUF(N)
    
    for i in range(N):
        p, q = list(map(int, input().split()))
        if not uf.connected(p, q):
            uf.union(p, q)
            print(p, q)
    
    print(uf.ids)
    
"""
Quick Union is also too slow. 

Cost Model: 
intialize   - N
union       - lgN (takes constant time, given roots)
find        - lgN (Takes proportional time to depth of p and q)

Quadratic runtime. N**2 accesses to process a sequence of N union commands on N objects

Drawbacks - 
1. Trees can get tall
2. Find too expensive (could be N array accesses)
"""
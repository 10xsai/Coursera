"""
could we improve it even further. And in this case, it's very
easy to improve it much, much more. And that's the idea of path compression. And
this idea is that, well, when we're trying to find the root of the tree containing a,
a given node. We're touching all the nodes on the path from that node to the root.
While we're doi ng that we might as well make each one of those just point to the
root. There's no reason not to. So when we're looking, we're trying to find the
root of, of P. After we find it, we might as well just go back and make every node
on that path just point to the root. That's going to be a constant extra cost.
We went up the path once to find the root. Now, we'll go up again to just flatten the
tree out. And the reason would be, no reason not to do that. We had one line of
code to flatten the tree, amazingly. Actually to make a one liner code, we use
a, a simple variant where we make every other node in the path point to its
grandparent on the way up the tree. Now, that's not quite as good as totally 
flattening actually in practice that it actually is just about as good. 
"""

class PathCompressedWeightedQuickUnionUF:
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
        
        if self.sz[i] < self.sz[j]: self.ids[i] = j; self.sz[j] += self.sz[i]
        else                      : self.ids[j] = i; self.sz[i] += self.sz[j]
    
    def connected(self, p: int, q: int) -> bool:
        # Finding if parents are same or not
        return self.root(p) == self.root(q)
    
    def root(self, p: int) -> int:
        while p != self.ids[p]:
            self.ids[p] = self.ids[self.ids[p]] 
            p = self.ids[p]
        return p
    

if __name__=='__main__':
    N = int(input())
    
    uf = PathCompressedWeightedQuickUnionUF(N)
    
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
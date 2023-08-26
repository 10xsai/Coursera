"""
Lazy Approach

All right so QuickFind is too slow for huge problems. So, how are we going to do
better? Our first attempt is an alternative called, Quick-union. This is
so called lazy approach to algorithm design where we try to avoid doing work
until we have to. It uses the same data structure or array ID with size M but now
it has a different interpretation. We are going to think of that array as
representing a set of trees that's called a forest as depicted at right. So, each
entry in the array is going to contain a reference to its parent in the tree. So,
for example, 3's parent is four, 4's parent is nine. So 3's entry is four and
4's entry is nine in the array. Now each entry in the array has associated with it
a root. That's the root of its tree. Elements that are all by themselves in
just, in their own connected component, point to themselves, so one points to
itself but also nine points to itself. It's the root of the tree, containing two,
four and three. So, from this data structure we can associate with each item
a root, which is representative, say, of it's connected component. So that's the
root of three is nine, going up that root. Now, once we can calculate these roots,
then we can implement the find operation just by checking whether the two items
that we're supposed to check with are connective where they have the same root
"""

class QuickUnionUF:
    ids = []
    
    def __init__(self, N):
        self.ids = [i for i in range(N)]
        
    def union(self, p: int, q: int) -> None:
        # First we'll get the parents for p and q and 
        # we'll attach the p's parent to q's parent
        p_parent = self.root(p)
        q_parent = self.root(q)
        if p_parent == q_parent: return
        
        self.ids[p_parent] = q_parent
    
    def connected(self, p: int, q: int) -> bool:
        # Finding if parents are same or not
        return self.root(p) == self.root(q)
    
    def root(self, p: int) -> int:
        while p != self.ids[p]:
            p = self.ids[p]
        return p
    

if __name__=='__main__':
    N = int(input())
    
    uf = QuickUnionUF(N)
    
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
union       - N (varies, inlcudes cost of finding roots)
find        - N

Quadratic runtime. N**2 accesses to process a sequence of N union commands on N objects

Drawbacks - 
1. Trees can get tall
2. Find too expensive (could be N array accesses)
"""
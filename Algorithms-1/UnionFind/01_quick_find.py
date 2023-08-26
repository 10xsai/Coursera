"""

Now we'll look at our first implementation of an algorithm for solving the dynamic
connectivity problem, called Quick-find. This is a so called eager algorithm. 
We won't build any trees, we'll use simple arrays for solving using this approach. 

When p is union with q, we'll take index of p and put it in q. 
We'll do it for all the connections. At the end connected values will have
same index.

if N value is 9
then the connected list may look like 
 0  1  2  3  4  5  6  7  8  9
[0, 1, 1, 8, 8, 0, 0, 1, 8, 8]
"""


class QuickFindUF:
    N = 0
    ids = []
    
    def __init__(self, N: int):
        self.N = N
        self.ids = [i for i in range(N)]
        
    def union(self, p: int, q: int) -> None:
        pid, qid = self.ids[p], self.ids[q]
        for i in range(self.N):
            if self.ids[i] == pid:
                self.ids[i] = qid        
    
    def connected(self, p: int, q: int) -> bool:
        return self.ids[p] == self.ids[q]

    

if __name__=='__main__':
    N = int(input())
    
    uf = QuickFindUF(N)
    
    for i in range(N):
        p, q = list(map(int, input().split()))

        if not uf.connected(p, q):
            uf.union(p, q)
            print(p, q)
    print(uf.ids)


"""
Quick find is too slow. 

Cost Model: 
intialize   - N
union       - N
find        - 1

Quadratic runtime. N**2 accesses to process a sequence of N union commands on N objects
"""

"""
General Note

We can't accept quadratic
time algorithms for large problems. The reason is they don't scale. As computers
get faster and bigger, quadratic algorithms actually get slower. Now, let's
just talk roughly about what I mean by that. A very rough standard, say for now,
is that people have computers that can run billions of operations per second, and
they have billions of entries in main memory. So, that means that you could
touch everything in the main memory in about a second. That's kind of an amazing
fact that this rough standard is really held for 50 or 60 years. The computers get
bigger but they get faster so to touch everything in the memory is going to take
a few seconds. Now it's true when computers only have a few thousand words
of memory and it's true now that they have billions or more. So let's accept that as
what computers are like. Now, that means is that, with that huge memory, we can
address huge problems. So we could have, billions of objects, and hope to do
billions of union commands on them. And, but the problem with that quick find
algorithm is that, that would take ten^18th operations, or, say array axises
or touching memory. And if you do the math, that works out to 30 some years of
computer time. Obviously, not practical to address such a problem on today's
computer. And, and the reason is, and the problem is that quadratic algorithms don't
scale with technology. You might have a new computer that's ten times as fast but
you could address a problem that's ten times as big. And with a quadratic
algorithm when you do that. It's going to be ten times as slow. That's the kind of
situation we're going to try to avoid by developing more efficient algorithms for
solving problems like this. 
"""
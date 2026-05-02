class Node:
    def __init__(self,value):
        self.value = value
        self.children = {}
        self.isEnd = False

class PrefixTree:

    def __init__(self):
        self.root = Node("")


    def insert(self, word: str) -> None:
        cur = self.root
        for i,c in enumerate(word):
            if c in cur.children:
                cur = cur.children[c]
            else:
                cur.children[c] = Node(c)
                cur = cur.children[c]
            if i == len(word)-1:
                cur.isEnd = True

        
            

    def search(self, word: str) -> bool:
        cur = self.root
        for c in word:
            if c in cur.children:
                cur = cur.children[c]
            else:
                return False
        return cur.isEnd

    def startsWith(self, prefix: str) -> bool:
        cur = self.root
        for c in prefix:
            if c in cur.children:
                cur = cur.children[c]
            else:
                return False
        return True

from collections import deque


def bfs(lab):
    start = (1, 1)
    end = (len(lab) - 2, len(lab) - 2)
    path = []
    queue = [start]
    visited = [start]
    while queue:
        (x, y) = queue.pop(0)
        path.append((x, y))
        if (x, y) == end:
            return path
        if (x + 1, y) not in visited:
            visited.append((x + 1, y))
            if(lab[y][x + 1]):
                queue.append((x + 1, y))
        if (x - 1, y) not in visited:
            visited.append((x - 1, y))
            if(lab[y][x - 1]):
                queue.append((x - 1, y))
        if (x, y + 1) not in visited:
            visited.append((x, y + 1))
            if(lab[y + 1][x]):
                queue.append((x, y + 1))
        if (x, y - 1) not in visited:
            visited.append((x, y - 1))
            if(lab[y - 1][x]):
                queue.append((x, y - 1))
    print("no path")

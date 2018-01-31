#!/usr/bin/python
# -*- coding: utf-8 -*-

INIT = ( 1 , 4 , 2 ,
         5 , 3 , 0 )

# GOAL = ( 0 , 1 , 2 ,
#          5 , 4 , 3 )

GOAL = ( 1 , 0 , 4 ,
         5 , 3 , 2 )

def print_board(board):
    print("""
_____________
| %d | %d | %d |
| %d | %d | %d |
¯¯¯¯¯¯¯¯¯¯¯¯¯
""" % (board[0], board[1], board[2], board[3], board[4], board[5]))

def move_up(board, idx):
    # if idx < 3 or idx > 5:
    #     return ()
    board = list(board)
    board[idx] = board[idx+3]
    board[idx+3] = 0
    return tuple(board)

def move_right(board, idx):
    # if idx == 2 or idx == 5:
    #     return ()
    board = list(board)
    board[idx] = board[idx-1]
    board[idx-1] = 0
    return tuple(board)

def move_down(board, idx):
    # if idx < 0 or idx > 2:
    #     return ()
    board = list(board)
    board[idx] = board[idx-3]
    board[idx-3] = 0
    return tuple(board)

def move_left(board, idx):
    # if idx == 0 or idx == 3:
    #     return ()
    board = list(board)
    board[idx] = board[idx+1]
    board[idx+1] = 0
    return tuple(board)

OPS = {"up":move_up, "right":move_right, "down":move_down, "left":move_left}
VALID_OPS = {0:(move_up, move_left), 1:(move_up, move_left, move_right), 2:(move_up, move_right),
             3:(move_down, move_left), 4:(move_down, move_left, move_right), 5:(move_down, move_right)}

def create_children(board, idx):
    # idx is location of empty square
    children = []
    global VALID_OPS
    children = [action(board, idx) for action in VALID_OPS[idx]]
    print children
    children = sorted(children, cmp=lambda child1, child2: child2[idx] - child1[idx])
    print children
    return children

def dfs(board):
    stack = []
    visited = set()
    path = []
    idx = 5
    global GOAL
    global OPS
    stack.append(board)
    while stack:
        crt_brd = stack.pop(-1)
        visited.add(crt_brd)
        if (crt_brd == GOAL) or path:
            node = stack.pop(-1)
            if node in visited:
                path.append(node)
            continue
        nxt_brds = []
        for child in create_children(board, idx):
            if child not in visited:
                nxt_brds.append(child)
        if not nxt_brds:
            stack.pop(-1)
        else:
            stack += nxt_brds
    return path

def rec_dfs(board, idx, visited, path, goal, limit):
    visited.add(board)
    if board == GOAL:
        return path
    elif limit == 0:
        return []
    else:
        for child in create_children(board, idx):
            path.append(child)
            result_path = rec_dfs(child, idx, visited, path, goal, limit-1)
            if result_path:
                return result_path
            else:
                path.pop(-1)
    return []

def main():
    global INIT
    global GOAL
    # path = dfs(INIT)
    path = rec_dfs(INIT, 5, set(), [], GOAL, 18)
    print_board(INIT)
    print(path)
    return 0

if __name__ == '__main__':
    exit(main())

#!/usr/bin/python
# -*- coding: utf-8 -*-

INIT = ( 1 , 4 , 2 ,
         5 , 3 , 0 )

GOAL = ( 0 , 1 , 2 ,
         5 , 4 , 3 )

def print_board(board):
    print("""
_____________
| %d | %d | %d |
| %d | %d | %d |
¯¯¯¯¯¯¯¯¯¯¯¯¯
""" % (board[0], board[1], board[2], board[3], board[4], board[5]))

def move_up(board, idx):
    if idx < 3 or idx > 5:
        return ()
    board = list(board)
    board[idx-3] = board[idx]
    board[idx] = 0
    return tuple(board)

def move_right(board, idx):
    if idx == 2 or idx == 5:
        return ()
    board = list(board)
    board[idx+1] = board[idx]
    board[idx] = 0
    return tuple(board)

def move_down(board, idx):
    if idx < 0 or idx > 2:
        return ()
    board = list(board)
    board[idx+3] = board[idx]
    board[idx] = 0
    return tuple(board)

def move_left(board, idx):
    if idx == 0 or idx == 3:
        return ()
    board = list(board)
    board[idx-1] = board[idx]
    board[idx] = 0
    return tuple(board)

OPS = {"up":move_up, "right":move_right, "down":move_down, "left":move_left}
VALID_OPS = {0:(move_up, move_left), 1:(move_up, move_left, move_right), 2:(move_up, move_right),
             3:(move_down, move_left), 4:(move_down, move_left, move_right), 5:(move_down, move_right)}

def create_children(board, idx):
    # ops = []
    # idx is location of empty square
    for i in (idx-3, idx+1, idx+3, idx-1):
        if idx < 3 or idx > 5:
            continue
        if idx == 2 or idx == 5:
            return ()
        if idx < 0 or idx > 2:
            return ()
        if idx == 0 or idx == 3:
            return ()
    global VALID_OPS
    return VALID_OPS[idx]

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
        if crt_brd == GOAL:
            return path
        if crt_brd not in visited:
            visited.add(crt_brd)
        for i in [i if i > 0 and i < 5 else 6 for i in (idx-3, idx+1, idx+3, idx-1)]:
            for op in OPS.iteritems():
                nxt_brd = op[1](crt_brd, i)
                idx = i
                stack.append(nxt_brd)
                path.append(op[0])
    return path

def main():
    global INIT
    path = dfs(INIT)
    print_board(path)
    return 0

if __name__ == '__main__':
    exit(main())

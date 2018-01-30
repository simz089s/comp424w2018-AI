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
        for op in OPS.iteritems():
            nxt_brd = op[1](crt_brd, idx)
            stack.append(nxt_brd)
            if nxt_brd and (nxt_brd not in visited):
                path.append(op[0])
    return path

def main():
    global INIT
    path = dfs(INIT)
    print_board(path)
    return 0

if __name__ == '__main__':
    exit(main())

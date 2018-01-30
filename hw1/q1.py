#!/usr/bin/python
# -*- coding: utf-8 -*-

# import networkx as nwx
# import matplotlib as mpl
# from matplotlib import pyplot as plt

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

        if crt_brd == GOAL:
            return path
        for op in OPS.iteritems():
            nxt_brd = op[1](crt_brd, idx)
            if (crt_brd not in visited) and nxt_brd:
                path.append(op[0])
            visited.add(crt_brd)
    return 0

def main():
    global INIT
    print_board(INIT)
    return 0

if __name__ == '__main__':
    exit(main())

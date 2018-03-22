#!/usr/bin/python
# -*- coding : utf-8 -*-
#
# calc.py
from __future__ import print_function
import os, sys
import numpy as np
import scipy as sp

r, b, a, t, s = "r", "b", "a", "t", "s"

PROBS = {
    b : 0.2, r : 0.15, "t|r,b,a" : 0.95, "t|r,-b,a" : 0.92, "t|r,b,-a" : 0.90,
    "t|r,-b,-a" : 0.85, "t|-r,b,a" : 0.35, "t|-r,b,-a" : 0.4, "t|-r,-b,a" : 0.6,
    "t|-r,-b,-a" : 0.05, "s|a" : 0.8, "s|-a" : 0.2, "a|b": 0.6, "a|-b" : 0.4,
}

def P(x):
    try:
        return PROBS[x]
    except KeyError:
        return P2(x)

PROBS2 = {
    "s,r" : P(r) * P("s|a") * P("a|b")
}

def P2(x):
    return PROBS2[x]

def main(args):
    print( "P(s,r) = ", P("s,r") )
    return 0

if __name__ == "__main__":
    sys.exit(main(sys.argv))

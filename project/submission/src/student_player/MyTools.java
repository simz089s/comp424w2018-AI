package student_player;

import java.util.ArrayList;
import java.util.List;

import coordinates.Coord;
import coordinates.Coordinates;
import tablut.TablutBoardState;
import tablut.TablutMove;

public class MyTools {
    /**
     * Node evaluation function
     * 
     * @param tbs A board state
     * @return The value of a node from a max player perspective (the caller)
     */
    public static int evalMove(TablutBoardState tbs) {
    	int minPlayer = tbs.getOpponent();
    	int maxPlayer = 1 - minPlayer;
    	
//		Swedes
    	if (maxPlayer == TablutBoardState.SWEDE) {
    		Coord kingPos = tbs.getKingPosition();
    		int value = Coordinates.distanceToClosestCorner(kingPos) * 5;
    		List<Coord> neighbors = Coordinates.getNeighbors(kingPos);
    		for (Coord pos : neighbors) {
    			if (tbs.isOpponentPieceAt(pos)) value-=10;
//    			if (Coordinates.isCorner(pos)) value--;
    		}
    		return value;
    	}
    	
//		Muscovites
    	else if (maxPlayer == TablutBoardState.MUSCOVITE) {
    		Coord kingPos = tbs.getKingPosition();
    		int value = -Coordinates.distanceToClosestCorner(kingPos) * 5;
    		value += tbs.getNumberPlayerPieces(maxPlayer);
//    		value -= tbs.getNumberPlayerPieces(minPlayer);
    		List<Coord> neighbors = Coordinates.getNeighbors(kingPos);
    		for (Coord pos : neighbors) {
    			if (tbs.getPieceAt(pos) == TablutBoardState.Piece.BLACK) value+=10;
//    			if (Coordinates.isCorner(pos)) value++;
    		}
    		return value;
    	}
    	
    	else {
    		return 0;
    	}
    }
    
    /**
     * @param tbs A board state
     * @param originalMaxPlayer The original max player that called this
     * @param depth A search depth
     * @param maxDepth A maximum search depth
     * @return Minimax value
     */
    public static int findMove(TablutBoardState tbs, int originalMaxPlayer, int depth, int maxDepth) {
    	int possibleWinner = tbs.getWinner();
    	int minPlayer = tbs.getOpponent();
    	int maxPlayer = 1 - minPlayer;
    	if (depth == maxDepth || tbs.getTurnNumber() >= 99) {
    		return evalMove(tbs);
    	}
    	if (possibleWinner == maxPlayer) {
    		return Integer.MAX_VALUE;
    	}
    	if (possibleWinner == minPlayer) {
    		return Integer.MIN_VALUE;
    	}
    	
    	ArrayList<TablutMove> moves = tbs.getAllLegalMoves();
    	
    	int bestValue = Integer.MIN_VALUE;
    	TablutBoardState currState = tbs;
    	
//    	Try each move by going as deep as chosen for minimaxing
    	for (TablutMove move : moves) {
    		currState = (TablutBoardState)tbs.clone();
    		currState.processMove(move);
    		
    		minPlayer = currState.getOpponent();
        	maxPlayer = 1 - minPlayer;
    		
        	if (depth == maxDepth || tbs.getTurnNumber() >= 99) {
        		return evalMove(tbs);
        	}
        	if (possibleWinner == maxPlayer) {
        		return Integer.MAX_VALUE;
        	}
        	if (possibleWinner == minPlayer) {
        		return Integer.MIN_VALUE;
        	} else {
//        		Keep going
        		
        		int newValue = findMove(currState, originalMaxPlayer, depth+1, maxDepth);
        		
//        		Update min/max value
        		if (originalMaxPlayer == maxPlayer) {
//        			Max node
        			if (bestValue < newValue) {
        				bestValue = newValue;
        			}
        		} else {
//        			Min node
        			if (bestValue > newValue) {
        				bestValue = newValue;
        			}
        		}
    		}
    	}
	    	
    	return bestValue;
    }
    
    /**
     * @param tbs A board state
     * @param maxDepth A maximum search depth
     * @return The move with the best value
     */
    public static TablutMove pickMove(TablutBoardState tbs, int maxDepth) {
    	int minPlayer = tbs.getOpponent();
    	int maxPlayer = 1 - minPlayer;
    	ArrayList<TablutMove> moves = tbs.getAllLegalMoves();
    	int bestValue = Integer.MIN_VALUE;
    	TablutMove bestMove = moves.get(0);
    	
//    	From the root, get the best move of all the children/possible moves
    	for (TablutMove move : moves) {
        	TablutBoardState currState = tbs;
        	currState = (TablutBoardState)tbs.clone();
        	currState.processMove(move);
        	
//        	Get the value of this move and update the best value if better
        	int newValue = findMove(currState, maxPlayer, 0, maxDepth);
        	
        	if (bestValue < newValue) {
        		bestValue = newValue;
        		bestMove = move;
        	}
    	}
    	return bestMove;
    }
}

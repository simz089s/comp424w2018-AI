package student_player;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import coordinates.Coord;
import coordinates.Coordinates;
import tablut.TablutBoardState;
import tablut.TablutMove;

public class MyTools {
    /**
     * @param tbs A board state
     * @return The value of a node from a max player perspective
     */
    public static int evalMove(TablutBoardState tbs) {
    	int minPlayer = tbs.getOpponent();
    	int maxPlayer = 1 - minPlayer;
    	if (maxPlayer == TablutBoardState.SWEDE) {
//    		Swedes
    		Coord kingPos = tbs.getKingPosition();
    		int value = Coordinates.distanceToClosestCorner(kingPos);
    		List<Coord> neighbors = Coordinates.getNeighbors(kingPos);
    		for (Coord pos : neighbors) {
    			if (tbs.isOpponentPieceAt(pos)) value--;
    			if (Coordinates.isCorner(pos)) value--;
    		}
    		return value;
    	} else if (maxPlayer == TablutBoardState.MUSCOVITE) {
//    		Muscovites
    		Coord kingPos = tbs.getKingPosition();
    		int value = -Coordinates.distanceToClosestCorner(kingPos);
    		value += tbs.getNumberPlayerPieces(maxPlayer);
    		value -= tbs.getNumberPlayerPieces(minPlayer);
    		List<Coord> neighbors = Coordinates.getNeighbors(kingPos);
    		for (Coord pos : neighbors) {
    			if (tbs.getPieceAt(pos) == TablutBoardState.Piece.BLACK) value++;
//    			if (Coordinates.isCorner(pos)) value++;
    		}
    		return value;
    	} else {
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
    	if (depth == maxDepth || tbs.getWinner() != Board.NOBODY || tbs.getTurnNumber() == 100) {
    		return evalMove(tbs);
    	}
    	
//    	int minPlayer = tbs.getOpponent();
//    	int maxPlayer = 1 - minPlayer;
    	ArrayList<TablutMove> moves = tbs.getAllLegalMoves();
    	
    	int bestValue = Integer.MIN_VALUE;
    	TablutBoardState currState = tbs;
    	
    	for (TablutMove move : moves) {
    		currState = (TablutBoardState)tbs.clone();
    		currState.processMove(move);
    		
    		int minPlayer = currState.getOpponent();
        	int maxPlayer = 1 - minPlayer;
    		
    		if (currState.getWinner() != Board.NOBODY || currState.getTurnNumber() == 100) {
//    			Terminal state node or maximum depth reached
    			return evalMove(currState);
        	} else {
//        		Keep going
        		
        		int newValue = findMove(currState, originalMaxPlayer, depth+1, maxDepth);
        		
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
     * @return The move with the best value
     */
    public static TablutMove pickMove(TablutBoardState tbs, int maxDepth) {
    	int minPlayer = tbs.getOpponent();
    	int maxPlayer = 1 - minPlayer;
    	ArrayList<TablutMove> moves = tbs.getAllLegalMoves();
    	int bestValue = Integer.MIN_VALUE;
    	TablutMove bestMove = moves.get(0);
    	
    	for (TablutMove move : moves) {
        	TablutBoardState currState = tbs;
        	currState = (TablutBoardState)tbs.clone();
        	
        	currState.processMove(move);
        	int newValue = findMove(currState, maxPlayer, 0, maxDepth);
        	
        	if (bestValue < newValue) {
        		bestValue = newValue;
        		bestMove = move;
        	}
    	}
    	return bestMove;
    }
}

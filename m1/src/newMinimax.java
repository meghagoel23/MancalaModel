
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Suyash Misra
 */
public class newMinimax {
    
    //int depth = 4;
//    int counter = 0;
    int bestPitFinder(State currentState){
        
        ArrayList<Integer> allPossibleMoves = currentState.availableMoves();
        
        int bestScore = Integer.MIN_VALUE;
        
        int bestMove = 0;
        
        for (Integer move : allPossibleMoves) {
            
            State movedState = new State(currentState);
            movedState.Turn(currentState.getPlayer(), move);        //make turn in the copy of the current state
            int depth = 2;      //set the depth of minimax search
            /*
            System.out.println("Current State after turn:");
            for(int i = 0;i<6; i++){
                System.out.print(currentState.pitB[i].getAmount()+" ");
            }
            System.out.println();
            System.out.println("mancalaA of currentState = "+currentState.mancalaA.getAmount());
            System.out.println("mancalaB of currentState = "+currentState.mancalaB.getAmount());
            
            System.out.println("State after turn:");
            for(int i = 0;i<6; i++){
                System.out.print(movedState.pitB[i].getAmount()+" ");
            }
            System.out.println();
            System.out.println("mancalaA of moved state = "+movedState.mancalaA.getAmount());
            System.out.println("mancalaB of moved state = "+movedState.mancalaB.getAmount());*/
            
            
            
            movedState.setPlayer('A');      //after making turn, set the player of the moved state to the opponent
            int score = minimax(movedState, depth, false);      //call minimax on this state and get the best score associated with this move
            
            if(score > bestScore){      //check if the score is bigger than the best previous score
                bestScore = Math.max(score, bestScore);
                bestMove = move;
            }
        }
        return bestMove;
    }
    
    
    int minimax(State currentState, int depth, boolean  isMaximizing){
        if(isTerminal(currentState) || depth == 0){
            return Evaluate(currentState);
        }
        
        if(isMaximizing){
//            System.out.println("counter = "+counter);
           currentState.setPlayer('B');
           int bestScore = Integer.MIN_VALUE;
           ArrayList<State> children = currentState.getChildren();
            for (State child : children) {
                int score = minimax(child, depth-1, !isMaximizing);
//                System.out.println("counter = "+counter);
                bestScore = Math.max(score, bestScore);
            }
            return bestScore;
        }
        else{
//            System.out.println("counter = "+counter);
           currentState.setPlayer('A');
           int bestScore = Integer.MAX_VALUE;
           ArrayList<State> children = currentState.getChildren();
            for (State child : children) {
                int score = minimax(child, depth-1, isMaximizing);
//                System.out.println("counter = "+counter);
                bestScore = Math.min(score, bestScore);
            }
            return bestScore;
        }
    }
    
    boolean isTerminal(State currState){
        if(currState.availableMoves().isEmpty()){
            return true;
        }
        else
            return false;
    }
    
    int Evaluate(State currState){
        /*if(currState.getPlayer()=='A')
            return currState.mancalaA.getAmount() - currState.mancalaB.getAmount();
        else*/
            return currState.mancalaB.getAmount() - currState.mancalaA.getAmount();
    }
}

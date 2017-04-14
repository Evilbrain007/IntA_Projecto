package eightpuzzle;

import agent.Heuristic;

public class HeuristicTileDistance extends Heuristic<EightPuzzleProblem, EightPuzzleState>{

    public double compute(EightPuzzleState state){
        //TODO        
        return 0;
    }
    
    @Override
    public String toString(){
        return "Tiles distance to final position";
    }
}
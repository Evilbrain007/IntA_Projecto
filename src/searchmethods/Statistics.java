package searchmethods;

public class Statistics {
    public double numExpandedNodes;
    public double numGeneratedNodes = 1; //due to the initial node
    public double maxFrontierSize;
    
    public void reset(){
        numExpandedNodes = 0;
        numGeneratedNodes = 1;
        maxFrontierSize = 0;
    }
}

package searchmethods;

import agent.State;
import java.util.List;
import utils.NodePriorityQueue;

public class BeamSearch extends AStarSearch {

    private int beamSize;

    public BeamSearch() {
        this(100);
    }

    public BeamSearch(int beamSize) {
        this.beamSize = beamSize;
    }

    @Override
    public void addSuccessorsToFrontier(List<State> successors, Node parent) {
        super.addSuccessorsToFrontier(successors, parent);

        NodePriorityQueue nodePriorityQueue = new NodePriorityQueue();

        while(nodePriorityQueue.size() <= beamSize && frontier.size() > 0){

            nodePriorityQueue.add(frontier.poll()); //o poll devolve o k esta na cabeça da fronteira (os k tem
            //prioridade

        }
        this.frontier = nodePriorityQueue;
    }

    public void setBeamSize(int beamSize) {
        this.beamSize = beamSize;
    }

    public int getBeamSize() {
        return beamSize;
    }

    @Override
    public String toString() {
        return "Beam search";
    }
}

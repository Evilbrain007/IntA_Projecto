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
        //TODO
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

package agent;


public abstract class Action <S extends State>{
    private double cost;

    public Action(double cost){
        this.cost = cost;
    }

    public abstract void execute(S state);

    public abstract boolean isValid(S state);

    public double getCost(){
        return cost;
    }
}
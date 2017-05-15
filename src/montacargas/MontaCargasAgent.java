package montacargas;

import agent.Agent;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by sorai on 15-Apr-17.
 */
public class MontaCargasAgent extends Agent<MontaCargasState>{

    private MontaCargasState initialEnvironment;

    public MontaCargasAgent(MontaCargasState environment) {
        super(environment);
        //o metodo clone devolve sempre Object por isso faço cast para MontaCargasState
        initialEnvironment = (MontaCargasState) environment.clone();

        //Inserir heuristicas
        //heuristics.add(new HeuristicBoxesInTheWay());
        //heuristic = heuristics.get(0);

    }

    public MontaCargasState readInitialStateFromFile(File file) throws IOException{
        java.util.Scanner scanner = new Scanner(file);
        //vai receber o ficheiro que tem a matriz e le o 1º numero do ficheiro que
        //indica o tamanho da matriz: ex 6 significa matriz 6*6
        int size = scanner.nextInt();
        int[][] matrix = new int[size][size];

        scanner.nextLine();

        for (int l = 0; l < size; l++) {
            for (int c = 0; c < size; c++) {
                matrix[l][c] = scanner.nextInt();
            }
            scanner.nextLine();
        }
        this.initialEnvironment = new MontaCargasState(matrix);
        resetEnvironment();
        return this.environment;
    }

    public MontaCargasState resetEnvironment() {
        this.environment = (MontaCargasState) initialEnvironment.clone();
        return this.environment;
    }

}

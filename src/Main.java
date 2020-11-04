import finiteAutomata.FiniteAutomaton;
import finiteAutomata.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> states = new ArrayList<String>(Arrays.asList(readStatesFromFile()));
        ArrayList<String> alphabet = new ArrayList<String>(Arrays.asList(readAlphabetFromFile()));
        String initialState= readInitialStateFromFile();
        ArrayList<String> finalStates = new ArrayList<String>(Arrays.asList(readStatesFromFile()));
        HashMap<Pair, ArrayList<String>> transitions = readTransitionsFromFile();


        System.out.println("States: " + states);
        System.out.println("Alphabet: " + alphabet);
        System.out.println("Initial State: " + initialState);
        System.out.println("Final States: " + finalStates);
        System.out.println("Transitions: " + transitions);

        FiniteAutomaton finiteAutomaton = new FiniteAutomaton(states, alphabet, transitions, initialState, finalStates);

        if(finiteAutomaton.isDeterministic())
            System.out.println("FA is deterministic");
        else
            System.out.println("FA is not deterministic");

    }

    private static String[] readStatesFromFile() {
        String[] states = new String[0];
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("fa.in"));
            String line = reader.readLine();
            int lineIndex = 0;
            while(line != null){
                if(lineIndex == 0)
                    states = line.split(":")[1].split(",");
                line = reader.readLine();
                lineIndex++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return states;
    }

    private static String[] readAlphabetFromFile() {
        String[] alphabet = new String[0];
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("fa.in"));
            String line = reader.readLine();
            int lineIndex = 0;
            while(line != null){
                if(lineIndex == 1)
                    alphabet = line.split(":")[1].split(",");
                line = reader.readLine();
                lineIndex++;
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alphabet;
    }

    private static String readInitialStateFromFile() {
        String initialState = null;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("fa.in"));
            String line = reader.readLine();
            int lineIndex = 0;
            while(line != null){
                if(lineIndex == 2)
                    initialState = line.split(":")[1];
                line = reader.readLine();
                lineIndex++;
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return initialState;
    }

    private static String[] readFinalStatesFromFile() {
        String[] finalStates = new String[0];
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("fa.in"));
            String line = reader.readLine();
            int lineIndex = 0;
            while(line != null){
                if(lineIndex == 4)
                    finalStates = line.split(":")[1].split(",");
                line = reader.readLine();
                lineIndex++;
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalStates;
    }

    private static HashMap<Pair, ArrayList<String>> readTransitionsFromFile() {
        HashMap<Pair, ArrayList<String>> transitions = new HashMap<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("fa.in"));
            String line = reader.readLine();
            int lineIndex = 0;
            while(line != null){
                if(lineIndex == 3)
                {
                    String[] transitionStrings = line.split(":")[1].split(";");
                    for(String transitionString: transitionStrings) {
                        String[] elements = transitionString.split("-");
                        Pair pair = new Pair(elements[0], elements[1]);
                        String[] aux = elements[2].split(",");
                        ArrayList<String> states = new ArrayList<String>(Arrays.asList(aux));

                        transitions.put(pair, states);
                    }
                }
                line = reader.readLine();
                lineIndex++;
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transitions;
    }
}

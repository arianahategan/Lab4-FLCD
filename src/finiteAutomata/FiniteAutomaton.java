package finiteAutomata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FiniteAutomaton {
    private ArrayList<String> states;
    private ArrayList<String> alphabet;
    private HashMap<Pair, ArrayList<String>> transitions;
    private String initialState;
    private ArrayList<String> finalStates;

    public FiniteAutomaton(ArrayList<String> states, ArrayList<String> alphabet, HashMap<Pair, ArrayList<String>> transitions, String initialState, ArrayList<String> finalStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.transitions = transitions;
        this.initialState = initialState;
        this.finalStates = finalStates;
    }

    public ArrayList<String> getStates() {
        return states;
    }

    public void setStates(ArrayList<String> states) {
        this.states = states;
    }

    public ArrayList<String> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(ArrayList<String> alphabet) {
        this.alphabet = alphabet;
    }

    public HashMap<Pair, ArrayList<String>> getTransitions() {
        return transitions;
    }

    public void setTransitions(HashMap<Pair, ArrayList<String>> transitions) {
        this.transitions = transitions;
    }

    public String getInitialState() {
        return initialState;
    }

    public void setInitialState(String initialState) {
        this.initialState = initialState;
    }

    public ArrayList<String> getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(ArrayList<String> finalStates) {
        this.finalStates = finalStates;
    }

    public boolean isDeterministic() {
        for(Map.Entry<Pair, ArrayList<String>> e : transitions.entrySet()){
            if(e.getValue().size() > 1)
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FiniteAutomaton{" +
                "states=" + states +
                ", alphabet=" + alphabet +
                ", transitions=" + transitions +
                ", initialState='" + initialState + '\'' +
                ", finalStates=" + finalStates +
                '}';
    }
}

import finiteAutomata.FiniteAutomaton;
import finiteAutomata.validator.FAValidator;
import scanner.PIF;
import scanner.Pair;
import scanner.Scanner;
import symbolTable.BinarySearchTree;
import symbolTable.SymbolTable;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static finiteAutomata.FAUtils.*;
import static scanner.ScannerUtils.*;

public class Main {

    public static void main(String[] args) {

    	//FA part
        ArrayList<String> states = new ArrayList<>(Arrays.asList(readStatesFromFile("fa.in")));
        ArrayList<String> alphabet = new ArrayList<>(Arrays.asList(readAlphabetFromFile("fa.in")));
        String initialState = readInitialStateFromFile("fa.in");
        ArrayList<String> finalStates = new ArrayList<>(Arrays.asList(readFinalStatesFromFile("fa.in")));
        HashMap<finiteAutomata.Pair, ArrayList<String>> transitions = readTransitionsFromFile("fa.in");


        System.out.println("States: " + states);
        System.out.println("Alphabet: " + alphabet);
        System.out.println("Initial State: " + initialState);
        System.out.println("Final States: " + finalStates);
        System.out.println("Transitions: " + transitions);

        FiniteAutomaton finiteAutomaton = new FiniteAutomaton(states, alphabet, transitions, initialState, finalStates);
        FAValidator validator = new FAValidator();
        validator.validate(finiteAutomaton);

        if (finiteAutomaton.isDeterministic())
            System.out.println("FA is deterministic");
        else
            System.out.println("FA is not deterministic");
        if (finiteAutomaton.sequenceAccepted("s"))
            System.out.println("Sequence accepted");
        else
            System.out.println("Sequence not accepted");


        if(checkIdentifierWithFA("aAb1098"))
			System.out.println("is iden");
        else
			System.out.println("is not iden");

		if(checkIntegerWithFA("-109"))
			System.out.println("is int");
		else
			System.out.println("is not int");



        //scanner part
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        SymbolTable symbolTable = new SymbolTable(binarySearchTree);
        PIF PIF = new PIF();


        ArrayList<String> separators = new ArrayList<>(Arrays.asList(readSeparatorsFromFile()));

        ArrayList<String> operators = new ArrayList<>(Arrays.asList(readOperatorsFromFile()));

        ArrayList<String> reservedWords = new ArrayList<>(Arrays.asList(readReservedWordsFromFile()));

        Scanner scanner = new Scanner(separators, operators);
        BufferedReader reader;
        try {


            String inputProgram = chooseInputProgram();

            reader = new BufferedReader(new FileReader(inputProgram));
            String line = reader.readLine();
            int lineIndex = 1;
            while (line != null) {
                for (String token : scanner.getTokensFromLine(line)) {
                    if (isOperator(token, operators) || isSeparator(token, separators) || isReservedWord(token, reservedWords)) {
                        PIF.add(new Pair(token, -1));

                    } else {
                        //if (isConstant(token) || isIdentifier(token)) {
						if (isConstant(token) || checkIdentifierWithFA(token) || checkIntegerWithFA(token)) {
                            symbolTable.insert(token);
                            int indexInST = symbolTable.search(token);
                            //if (isConstant(token))
							if (isConstant(token) || checkIntegerWithFA(token))
                                PIF.add(new Pair("const", indexInST));
                            else
                                PIF.add(new Pair("Id", indexInST));
                        } else {
                            if (!token.equals(" "))
                                System.out.println("Lexical error on line " + lineIndex + ": " + token);
                        }
                    }
                }

                line = reader.readLine();
                lineIndex++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeToPIFFile(PIF);
        writeToSTFile(symbolTable);

    }

    private static boolean checkIdentifierWithFA(String sequence){
		ArrayList<String> states = new ArrayList<>(Arrays.asList(readStatesFromFile("identifier.in")));
		ArrayList<String> alphabet = new ArrayList<>(Arrays.asList(readAlphabetFromFile("identifier.in")));
		String initialState = readInitialStateFromFile("identifier.in");
		ArrayList<String> finalStates = new ArrayList<>(Arrays.asList(readFinalStatesFromFile("identifier.in")));
		HashMap<finiteAutomata.Pair, ArrayList<String>> transitions = readTransitionsFromFile("identifier.in");

		FiniteAutomaton finiteAutomaton = new FiniteAutomaton(states, alphabet, transitions, initialState, finalStates);
		FAValidator validator = new FAValidator();
		validator.validate(finiteAutomaton);

		return finiteAutomaton.sequenceAccepted(sequence);
	}

	private static boolean checkIntegerWithFA(String sequence){
		ArrayList<String> states = new ArrayList<>(Arrays.asList(readStatesFromFile("integer.in")));
		ArrayList<String> alphabet = new ArrayList<>(Arrays.asList(readAlphabetFromFile("integer.in")));
		String initialState = readInitialStateFromFile("integer.in");
		ArrayList<String> finalStates = new ArrayList<>(Arrays.asList(readFinalStatesFromFile("integer.in")));
		HashMap<finiteAutomata.Pair, ArrayList<String>> transitions = readTransitionsFromFile("integer.in");

		FiniteAutomaton finiteAutomaton = new FiniteAutomaton(states, alphabet, transitions, initialState, finalStates);
		FAValidator validator = new FAValidator();
		validator.validate(finiteAutomaton);

		return finiteAutomaton.sequenceAccepted(sequence);
	}


}

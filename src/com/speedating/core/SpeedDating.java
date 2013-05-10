package com.speedating.core;

import java.util.*;

public class SpeedDating {

    private DataSource dataSource = new DataSource();

    private Map<Participant, Feeling> analyzes = new HashMap<>();

    public SpeedDating() {
        dataSource.generateQuestions();
        dataSource.generateMemoryDataBase();
        initializeBeforeRunningAnalyzesStorage();
    }

    public static void main(String[] args) {
        SpeedDating speedDating = new SpeedDating();
        speedDating.start();
        speedDating.runFeelingAnalysisForEachParticipant(speedDating);
        speedDating.printSpeedDatingAnalysis(speedDating);
    }

    private void initializeBeforeRunningAnalyzesStorage() {
        for (Participant participant : getParticipants()) {
            initializeAnalysisStorage(participant);
        }
    }

    private void initializeAnalysisStorage(Participant participant) {
        analyzes.put(participant, new Feeling());
    }

    private void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Quel est votre prénom ?");
            Participant participant = registerNewParticipant(scanner);
            askQuestions(scanner, participant);
            if (IsNotThereNextParticipant(scanner)) {
                break;  //exit the loop and thus the SpeedDating software
            }
        }
        scanner.close();
    }

    private void askQuestions(Scanner scanner, Participant participant) {
        for (int questionIndex = 0; questionIndex < dataSource.getQuestionsNumber(); questionIndex++) {
            System.out.println(dataSource.getParticularLabelQuestion(questionIndex));
            if (scanner.hasNextInt()) {
                int answer = scanner.nextInt();
                dataSource.storeAnswer(participant, questionIndex, answer);
            }
        }
    }

    private Participant registerNewParticipant(Scanner scanner) {
        Participant participant = new Participant(scanner.next());
        dataSource.storeParticipant(participant);
        initializeAnalysisStorage(participant);
        return participant;
    }

    private boolean IsNotThereNextParticipant(Scanner scanner) {
        System.out.println("Y'a t'il un autre participant? (Tapez 'oui' pour confirmer ou toute autre touche pour quitter)");
        return !(scanner.hasNext() && scanner.next().equalsIgnoreCase("oui"));
    }

    private Set<Participant> retrieveMatchingParticipants(Participant participant, Question question) {
        Integer participantAnswer = question.retrieveParticipantAnswer(participant);
        Set<Participant> matchingParticipants = question.retrieveParticipantsMatchingAnswer(participantAnswer);
        matchingParticipants.remove(participant);
        return matchingParticipants;
    }

    void runFeelingAnalysis(Participant participant, Set<Participant> matchedParticipants) {
        Feeling feeling = analyzes.get(participant);
        feeling.update(matchedParticipants);
    }

    private Set<Participant> getParticipants() {
        return dataSource.getParticipants();
    }

    private List<Question> getQuestions() {
        return dataSource.getQuestions();
    }

    private void runFeelingAnalysisForEachParticipant(SpeedDating speedDating) {
        for (Participant participant : speedDating.getParticipants()) {
            for (Question question : speedDating.getQuestions()) {
                Set<Participant> matchingParticipants = speedDating.retrieveMatchingParticipants(participant, question);
                speedDating.runFeelingAnalysis(participant, matchingParticipants);
            }
        }
    }

    private void printSpeedDatingAnalysis(SpeedDating speedDating) {
        System.out.println("\n Et voici Les meilleurs feelings !!! :");
        for (Map.Entry<Participant, Feeling> analysis : speedDating.analyzes.entrySet()) {
            System.out.print(analysis.getKey().name + " a un super feeling avec : ");
            for (Participant matchedParticipant : analysis.getValue().bestFeeling()) {
                System.out.print(matchedParticipant.name + " ");
            }
            System.out.print("\n");
        }
    }
}

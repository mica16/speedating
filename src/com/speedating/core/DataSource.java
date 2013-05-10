package com.speedating.core;

import java.util.*;

class DataSource {

    private List<Question> questions = new ArrayList<>();

    private Set<Participant> participants = new HashSet<>();

    void generateQuestions() {
        Set<Integer> possibleAnswers1 = new HashSet<>();
        possibleAnswers1.add(1);
        possibleAnswers1.add(2);
        possibleAnswers1.add(3);
        addQuestion("Question 1", possibleAnswers1);

        Set<Integer> possibleAnswers2 = new HashSet<>();
        possibleAnswers2.add(1);
        possibleAnswers2.add(2);
        possibleAnswers2.add(3);
        addQuestion("Question 2", possibleAnswers2);

        Set<Integer> possibleAnswers3 = new HashSet<>();
        possibleAnswers3.add(1);
        possibleAnswers3.add(2);
        possibleAnswers3.add(3);
        addQuestion("Question 3", possibleAnswers3);
    }


    void addQuestion(String label, Set<Integer> possibleAnswers) {
        questions.add(new Question(label, possibleAnswers));
    }

    void generateMemoryDataBase() {
        Participant michael = new Participant("Michael");
        Participant gamliel = new Participant("Gamliel");
        Participant david = new Participant("David");
        populateExistingParticipantsAnswers(michael, gamliel, david);
        participants.add(michael);
        participants.add(gamliel);
        participants.add(david);
    }

    void populateExistingParticipantsAnswers(Participant michael, Participant gamliel, Participant david) {
        questions.get(0).affectAnswer(gamliel, 1);
        questions.get(0).affectAnswer(michael, 1);
        questions.get(0).affectAnswer(david, 1);

        questions.get(1).affectAnswer(gamliel, 1);
        questions.get(1).affectAnswer(michael, 2);
        questions.get(1).affectAnswer(david, 1);

        questions.get(2).affectAnswer(gamliel, 1);
        questions.get(2).affectAnswer(michael, 1);
        questions.get(2).affectAnswer(david, 1);
    }

    void storeAnswer(Participant participant, int questionNumber, int answer){
        questions.get(questionNumber).affectAnswer(participant, answer);
    }

    void storeParticipant(Participant participant){
        participants.add(participant);
    }

    List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    Set<Participant> getParticipants() {
        return Collections.unmodifiableSet(participants);
    }

    int getQuestionsNumber(){
        return questions.size();
    }

    String getParticularLabelQuestion(int questionIndex){
        return questions.get(questionIndex).getLabel();
    }
}

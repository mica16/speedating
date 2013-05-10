package com.speedating.core;

import java.util.*;

public class Question {

    private String label;

    private Map<Integer, Set<Participant>> answers = new HashMap<>();

    private Set<Integer> possibleAnswers = new HashSet<>();

    public Question(String label, Set<Integer> possibleAnswers) {
        this.label = label;
        this.possibleAnswers = possibleAnswers;
    }

    public void affectAnswer(Participant participant, int answer) {
        if(answers.get(answer) == null){
            answers.put(answer, new HashSet<Participant>());
        }
        answers.get(answer).add(participant);
    }

    public Set<Participant> retrieveParticipantsMatchingAnswer(Integer answer){
        return new HashSet<>(answers.get(answer));
    }

    public Integer retrieveParticipantAnswer(Participant participant){
        for(Map.Entry<Integer, Set<Participant>> entry : answers.entrySet()){
            if(entry.getValue().contains(participant)){
                return entry.getKey();
            }
        }
        return null;  // should not reach this line
    }

    public String getLabel(){
        return label;
    }
}

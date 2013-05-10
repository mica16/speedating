package com.speedating.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Feeling {

    private int bestMatchingNumber;

    private Map<Participant, Integer> participantWithMatchNumber = new HashMap<>();

    public void update(Set<Participant> matchedParticipants) {
        for (Participant matchedParticipant : matchedParticipants) {
            if (participantWithMatchNumber.get(matchedParticipant) == null) {
                participantWithMatchNumber.put(matchedParticipant, 1);
                if (bestMatchingNumber == 0) {
                    bestMatchingNumber++;
                }
            } else {
                Integer currentMatchValue = participantWithMatchNumber.get(matchedParticipant);
                participantWithMatchNumber.put(matchedParticipant, ++currentMatchValue);
                if (currentMatchValue > bestMatchingNumber) {
                    bestMatchingNumber++;
                }
            }
        }
    }

    public Set<Participant> bestFeeling() {
        Set<Participant> bestFeeling = new HashSet<>();
        for (Map.Entry<Participant, Integer> entry : participantWithMatchNumber.entrySet()) {
            if (entry.getValue() == bestMatchingNumber) {
                bestFeeling.add(entry.getKey());
            }
        }
        return bestFeeling;
    }
}

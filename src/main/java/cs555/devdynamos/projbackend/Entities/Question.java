package cs555.devdynamos.projbackend.domain;

import java.util.UUID;

public record Question(UUID questionId,String question,String answer,String questionType) {
}

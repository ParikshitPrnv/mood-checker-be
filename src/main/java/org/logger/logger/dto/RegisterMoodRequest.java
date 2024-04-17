package org.logger.logger.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterMoodRequest {
    private String userId;
    private String mood;
}

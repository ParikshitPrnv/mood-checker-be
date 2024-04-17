package org.logger.logger.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RegisterMoodResponse {
    private String userId;
    private String mood;
}

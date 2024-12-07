package com.test.details;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class userDetails {
    private Long id;
    private List<LocalDateTime> attendance;
}

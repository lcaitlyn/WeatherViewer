package edu.lcaitlyn.weatherviewer.models;

import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    @NonNull
    private String email;
    @NonNull
    private String password;
}

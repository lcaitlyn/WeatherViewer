package edu.lcaitlyn.weatherviewer.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessions", schema = "weatherviewer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSession {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    @NonNull
    private User user;

    @NonNull
    private LocalDateTime expiresAt;
}

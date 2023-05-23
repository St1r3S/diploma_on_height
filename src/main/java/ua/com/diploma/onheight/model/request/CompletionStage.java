package ua.com.diploma.onheight.model.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "completion_stage")
public class CompletionStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "request_status", nullable = false)
    private RequestStatus requestStatus;

    @Column(name = "completion_datetime", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @PastOrPresent(message = "{PastOrPresent.Entity.Date}")
    private LocalDateTime completionDateTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id", referencedColumnName = "id", nullable = false)
    private Request request;

    public CompletionStage(Long id, RequestStatus requestStatus, LocalDateTime completionDateTime, Request request) {
        this.id = id;
        this.requestStatus = requestStatus;
        this.completionDateTime = completionDateTime;
        this.request = request;
    }

    public CompletionStage(RequestStatus requestStatus, LocalDateTime completionDateTime, Request request) {
        this(null, requestStatus, completionDateTime, request);
    }
}

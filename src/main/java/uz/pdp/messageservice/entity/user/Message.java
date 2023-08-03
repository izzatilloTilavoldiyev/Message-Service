package uz.pdp.messageservice.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import uz.pdp.messageservice.entity.BaseEntity;

@Entity(name = "messages")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Message extends BaseEntity {

    @Column(nullable = false)
    private String text;

    @ManyToOne
    private User sender;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User receiver;
}

package com.diploma.todolist.adaptor.persistence.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tasks", schema = "public")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_id_seq")
    @SequenceGenerator(name = "tasks_id_seq", sequenceName = "tasks_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "title")
    private String title;
    @Column(name = "is_completed")
    private boolean isCompleted;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

package com.myschool.adminservice.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties
@Entity
@Table
public class UserMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String message;

    private String postedBy;

    private LocalDateTime createdTime;

    private String priority;

    @Column(columnDefinition="boolean default false")
    private boolean readStatus;

    //sent to
    @ManyToOne
    @JoinColumn(name="to_username")
    @JsonBackReference
    private User user;



}

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
    private Long id;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String message;

    private String postedBy;

    private LocalDateTime createdTime;

    private String priority;

    @Column(columnDefinition="boolean default FALSE")
    private boolean readStatus;

    private String postedTo;

    //sent to
    @ManyToOne
    @JoinColumn(name="postedTo", referencedColumnName = "username", updatable = false, insertable = false)
    @JsonBackReference
    private User user;



}

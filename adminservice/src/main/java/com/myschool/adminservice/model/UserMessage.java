package com.myschool.adminservice.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

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

    private String message;

    private String postedBy;

    private Long createdTime;

    private String priority;

    @Column(columnDefinition="tinyint(1) default 0")
    private boolean readStatus;

    //sent to
    @ManyToOne
    @JoinColumn(name="to_username")
    @JsonBackReference
    private User user;



}

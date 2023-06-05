package com.myschool.adminservice.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "Subject cannot be blank")
    private String subject;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Message cannot be blank")
    private String message;

    private String postedBy;

    @ApiModelProperty(dataType = "java.lang.String", example = "2019-01-23T17:09:42.411")
    private LocalDateTime createdTimeStamp;

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

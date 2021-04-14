package com.myschool.adminservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties
@Entity
@Table
public class CourseMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Subject cannot be blank")
    private String subject;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Message cannot be blank")
    private String message;

    private String postedBy;

    private LocalDateTime createdTimeStamp;

    private String priority;

    private Integer courseId;

    @ManyToOne
    @JoinColumn(name="courseId", referencedColumnName = "id", updatable = false, insertable = false)
    @JsonBackReference
    private Course mesCourse;

}

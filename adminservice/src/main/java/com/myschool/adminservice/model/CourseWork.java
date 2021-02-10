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
public class CourseWork {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String type;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String topic;

    private LocalDateTime createdTimeStamp;

    private String videoLink;

    private String fileName;

    @Lob
    private byte[] uploadedFile;

    @Column(columnDefinition = "BIGINT default 0")
    private long courseId;

    @ManyToOne
    @JoinColumn(name="courseId", referencedColumnName = "id", updatable = false, insertable = false)
    @JsonBackReference
    private Course course;
}

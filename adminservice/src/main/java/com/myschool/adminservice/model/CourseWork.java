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
public class CourseWork {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String type;

    @NotBlank(message = "Title name cannot be blank")
    private String title;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Description cannot be blank")
    private String description;

    private String topic;
    @ApiModelProperty(dataType = "java.lang.String", example = "2019-01-23T17:09:42.411")
    private LocalDateTime createdTimeStamp;

    private String videoLink;

    private String fileName;

    private long fileSize;

    @Lob
    private byte[] uploadedFile;

    private Integer courseId;

    @ManyToOne
    @JoinColumn(name="courseId", referencedColumnName = "id", updatable = false, insertable = false)
    @JsonBackReference
    private Course course;

    public CourseWork(long id, String type, String title, String description, String topic, LocalDateTime createdTimeStamp, String videoLink, String fileName, long fileSize, Integer courseId) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.createdTimeStamp = createdTimeStamp;
        this.videoLink = videoLink;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.courseId = courseId;
    }
}

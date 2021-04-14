package com.myschool.adminservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mysql.cj.protocol.ColumnDefinition;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties
@Entity
@Table
public class SchoolMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Subject cannot be blank")
    private String subject;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Message cannot be blank")
    private String message;

    private String postedBy;

    @ApiModelProperty(dataType = "java.lang.String", example = "")
    private LocalDateTime createdTimeStamp;

    private String priority;

    @Column(columnDefinition = "BIGINT default 0")
    private long schoolId;

    @ManyToOne
    @JoinColumn(name="schoolId", referencedColumnName = "id", updatable = false, insertable = false)
    @JsonBackReference
    private School school;
}

package com.glebklim.examplebusiness.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "photos")
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Document {

    @Id
    String id;

    @NotEmpty
    @Column(name = "file_name")
    String fileName;

    @NotEmpty
    @Column(name = "content_type")
    String contentType;

    @JsonIgnore
    byte [] data;

    @Column(name = "isPrivate")
    boolean isPrivate;

}

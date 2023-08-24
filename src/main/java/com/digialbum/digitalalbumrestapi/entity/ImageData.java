package com.digialbum.digitalalbumrestapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "digital_album_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "CRT_TS", columnDefinition = "TIMESTAMP")
    private Timestamp CRT_TS;
    @Column(name = "UPD_TS", columnDefinition = "TIMESTAMP")
    private Timestamp UPD_TS;

    private char STATUS;

    @Lob
    @Column(name = "raw_data", columnDefinition = "MEDIUMBLOB")
    private byte[] raw_data;

    private String file_name;
    @Column(name = "file_type")
    private String filetype;
    private String caption;
    private String description;
}

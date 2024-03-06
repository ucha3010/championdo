package com.championdo.torneo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "document_manager")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DocumentManager {

    @Id
    @SequenceGenerator(name = "documentManagerGenerator", sequenceName = "CHANGE_LOW_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "documentManagerGenerator")
    private int id;
    @Column(name = "filename", nullable = false, length = 100)
    private String filename;
    @Column(name = "extension", nullable = false, length = 10)
    private String extension;
    @Column(name = "section", nullable = false, length = 50)
    private String section;
    @Column(name = "sectionDescription", length = 100)
    private String sectionDescription;
    @Column(name = "path", nullable = false, length = 200)
    private String path;
    private boolean needsSignature;
    private boolean signature;
    @Column(name = "idCard", length = 45)
    private String idCard;
    private Date creationDate;
    private Date signatureDate;
    private int idOriginalOperative;
    private int idGym;
    @Column(name = "nameGym", length = 100)
    private String nameGym;
    private boolean platformDocument;
    private Date deleteDate;

}

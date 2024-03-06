package com.championdo.torneo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DocumentManagerModel {

    private int id;
    private String filename;//
    private String extension;//
    private String section;//
    private String sectionDescription;
    private String path;//
    private boolean needsSignature;
    private boolean signature;
    private String idCard;//
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;//
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date signatureDate;
    private int idOriginalOperative;//
    private int idGym;
    private String nameGym;
    private boolean platformDocument;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deleteDate;

    public String getFullPath() {
        return path.concat(filename).concat(extension);
    }

}

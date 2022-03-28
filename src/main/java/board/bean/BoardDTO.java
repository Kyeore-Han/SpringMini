package board.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;

import lombok.Data;

@Data
public class BoardDTO {
	private int seq;
    private String id;
    private String name;
    private String email;
    private String subject;
    private String content;
    
    private int ref;
    private int lev;
    private int step;
    private int pseq;
    private int reply;
    
    private int hit;
    //private String logtime; // DB로 변환
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    private Date logtime; 

}


















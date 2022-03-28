package guestbook.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class GuestBookDTO {
	private int seq;
    private String name;
    private String email;
    private String homepage;
    private String subject;
    private String content;
    
    //private String logtime; // DB로 변환
    @JsonFormat(pattern = "yyyy.MM.dd")
    private Date logtime;
    
    


}


















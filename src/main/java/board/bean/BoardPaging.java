package board.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class BoardPaging {
	private int currentPage;
	private int pageBlock; // [1][2][3] [다음]
	private int pageSize; //1페이지당 5개씩
	private int totalA; //총글수
	private StringBuffer pagingHTML;
	
	public void makePagingHTML() {
		pagingHTML = new StringBuffer();
		
		int totalP = (totalA + pageSize-1) / pageSize;//총 페이지 수
		
		int startPage = (currentPage-1) / pageBlock * pageBlock + 1; //1 4 7 
		
		int endPage = startPage + pageBlock - 1;//9
		if(endPage > totalP) endPage = totalP;
			
		if(startPage > pageBlock) // 페이지블록보다 start페이지 번호가 클경우 항상[이전] 버튼을 뿌려준다.
			pagingHTML.append("<span id=paging onclick=boardPaging(" + (startPage-1) + ")>이전</span>");
				
		for(int i=startPage; i<=endPage; i++) {
			if(i == currentPage)
				pagingHTML.append("<span id=currentPaging onclick=boardPaging(" + i + ")>" + i + "</span>");
			else
				pagingHTML.append("<span id=paging onclick=boardPaging(" + i + ")>" + i + "</span>");
		} // 현재페이지에 해당하면 현재페이지로 초록색으로 별도로 표시되고, 나머지는 for문 돌면서 start페이지번호부터 end페이지번호까지 번호를 뿌려준다.
		
		if(endPage < totalP)
			pagingHTML.append("<span id=paging onclick=boardPaging(" + (endPage+1) + ")>다음</span>");
		// totalPage와 endPage가 서로 같다는 것은 마지막 페이지블록이라는 뜻이므로 [다음]이 존재하지 않는다.
	}

}






















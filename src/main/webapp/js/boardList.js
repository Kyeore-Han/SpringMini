//목록
$(function(){
	$.ajax({
		type: 'post',
		url: '/SpringProject/board/getBoardList',
		data: 'pg='+ $('#pg').val(),
		dataType: 'json',
		success: function(data){
			console.log(JSON.stringify(data));
						
			$.each(data.list, function(index, items){
				$('<tr/>')
					.append($('<td/>',{
						align: 'center',
						text: items.seq
					})).append($('<td/>',{
									
						}).append($('<a/>',{
							href: '#',
							text: items.subject,
							class: 'subjectA   subjectA_' + items.seq 
						}))
					
					).append($('<td/>',{
						align: 'center',
						text: items.id
					})).append($('<td/>',{
						align: 'center',
						text: items.logtime
					})).append($('<td/>',{
						align: 'center',
						text: items.hit
					})).appendTo($('#boardListTable'));
					
					//답글
					for(i=0; i<items.lev; i++){
						$('.subjectA_'+items.seq).before('&emsp;');
					}
					if(items.pseq != 0){
						$('.subjectA_'+items.seq).before($('<img/>',{
							src: '/SpringProject/image/reply.gif'
						}));
					}
					 
			});//each
			
			//로그인 여부
			$('.subjectA').click(function(){
				//alert(data.memId);
				
				//alert(this.tagName); //A
				//alert($(this).parent().prev().text()); //seq가져온다		
				
				var seq = $(this).parent().prev().text();
					
				if(data.memId == null){
					alert('먼저 로그인하세요');
				}else{
					location.href='/SpringProject/board/boardView?seq='+seq+'&pg='+$('#pg').val();
				}
				
			});
			
			//페이징 처리
			$('#boardPagingDiv').html(data.boardPaging.pagingHTML);
			
		},
		error:  function(err){
			alert(err);
		}
	});
});

//검색
$('#boardSearchBtn').click(function(){
	if($('#keyword').val() == '') 
		alert('검색어를 입력하세요');
	else{
		$('#key').val($('#keyword').val());
		
		$.ajax({
			type: 'post',
			url: '/SpringProject/board/getBoardSearchList',
			data: $('#boardSearchForm').serialize(), //pg(id=searchPg), searchOption, keyword
			dataType: 'json',
			success: function(data){
				console.log(JSON.stringify(data));
				
				$('#boardListTable tr:gt(0)').remove();
				
				$.each(data.list, function(index, items){
					$('<tr/>')
						.append($('<td/>',{
							align: 'center',
							text: items.seq
						})).append($('<td/>',{
										
							}).append($('<a/>',{
								href: '#',
								text: items.subject,
								class: 'subjectA   subjectA_' + items.seq 
							}))
						
						).append($('<td/>',{
							align: 'center',
							text: items.id
						})).append($('<td/>',{
							align: 'center',
							text: items.logtime
						})).append($('<td/>',{
							align: 'center',
							text: items.hit
						})).appendTo($('#boardListTable'));
						
						//답글
						for(i=0; i<items.lev; i++){
							$('.subjectA_'+items.seq).before('&emsp;');
						}
						if(items.pseq != 0){
							$('.subjectA_'+items.seq).before($('<img/>',{
								src: '/SpringProject/image/reply.gif'
							}));
						}
					 
				});//each
				
				//페이징 처리
				$('#boardPagingDiv').html(data.boardPaging.pagingHTML);
			},
			error:  function(err){
				alert(err);
			}
		});//ajax
	}//else
});


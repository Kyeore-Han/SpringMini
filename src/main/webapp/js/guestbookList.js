$(function(){
   $.ajax({
      type: 'post',
      url: '/SpringProject/guestbook/getGuestBookList',
      data: 'pg='+ $('#pg').val(),
      dataType: 'json',
      success: function(data){
         //alert(JSON.stringify(data));
         console.log(JSON.stringify(data));
                  
         $.each(data.list, function(index, items){
            $('<table/>', {
               style: "table-layout: 'fixed';",
               width: '600px',
               cellspacing:"0",
               cellpadding:"5", 
               border: '1',
               align: 'center'
               })
               .append($('<tr/>',{
                  align: 'center'
               })
               .append($('<td/>',{
                  align: 'center',
                  text: "작성자"
               })).append($('<td/>',{
                  align: 'center',
                  text: items.name
                  })
               ).append($('<td/>',{
                  align: 'center',
                  text: '작성일'
               })).append($('<td/>',{
                  align: 'center',
                  text: items.logtime
               }))).append($('<tr/>',{
                  align: 'center'
               })
               .append($('<td/>',{
                  align: 'center',
                  text: "이메일"
               })).append($('<td/>',{
                  align: 'center',
                  text: items.email,
                  colspan: 3
                  })
               )).append($('<tr/>',{
                  align: 'center'
               })
               .append($('<td/>',{
                  align: 'center',
                  text: "홈페이지"
               })).append($('<td/>',{
                  align: 'center',
                  text: items.homepage,
                  colspan: 3
                  })
               )).append($('<tr/>',{
                  align: 'center'
               })
               .append($('<td/>',{
                  align: 'center',
                  text: "제목"
               })).append($('<td/>',{
                  align: 'center',
                  text: items.subject,
                  colspan: 3
                  })
               )).append($('<tr/>',{
                  align: 'center'
               })
               .append($('<td/>',{
                  align: 'center',
                  style: "overflow:scroll; width: 100px; height:50px;",
                  text: items.content,
                  colspan: 4
               }))).appendTo($('#guestBookTable'));
               
               $('<br>').appendTo($('#guestBookTable'));
                
         });//each
         
         //페이징 처리
         console.log(data);
         
         $('#guestbookPagingDiv').html(data.guestBookPaging.pagingHTML);
         
      },
      error:  function(err){
         alert(err);
      }
   });
});
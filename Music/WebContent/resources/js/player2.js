//Context Menu 생성
function handleCreateContextMenu(event, i,k){
  // 기본 Context Menu가 나오지 않게 차단
  event.preventDefault();
  
  // Context Menu Element 생성
  const ctxMenuId = 'context_menu';
  const ctxMenu = document.createElement('div');
  
  // Context Menu Element 옵션 설정
  ctxMenu.id = ctxMenuId;
  ctxMenu.className = 'custom-context-menu';
  
  // 위치 설정
  ctxMenu.style.top = event.pageY+'px';
  ctxMenu.style.left = event.pageX+'px';
  
  // 메뉴 목록 생성
  ctxMenu.appendChild(renderContextMenuList([
    {
     label: "삭제",
      onClick: function(event){
		var url="deletePlaySong.jsp?Num="+i+"&Num2="+k;
		window.open(url, "재생목록삭제", "width=300,height=200,left=450,top=200");
      }
    },
  ]));
  
  // 이전 Element 삭제
  const prevCtxMenu = document.getElementById( ctxMenuId );
  if( prevCtxMenu ){
    prevCtxMenu.remove();
  }
  
  // Body에 Context Menu를 추가.
  document.body.appendChild( ctxMenu );
}


 // Context Menu List 렌더링
      function renderContextMenuList( list ){
        // List Element 생성
        const ctxMenuList = document.createElement('ul');
        
        // List Item 생성
        list.forEach(function( item ){
          // Item Element 생성
          const ctxMenuItem = document.createElement('li');
          const ctxMenuItem_a = document.createElement('a');
          const ctxMenuItem_a_text = document.createTextNode(item.label);
          
          // 클릭 이벤트 설정
          if( item.onClick ){
            ctxMenuItem.addEventListener( 'click', item.onClick, false );
          }
 
          // Item 추가
          ctxMenuItem_a.appendChild( ctxMenuItem_a_text );
          ctxMenuItem.appendChild( ctxMenuItem_a );
          ctxMenuList.appendChild( ctxMenuItem );
        });
        
        // List Element 반환
        return ctxMenuList;
      }
    
      
      
      // Context Menu 제거
      function handleClearContextMenu(event){
        const ctxMenu = document.getElementById('context_menu');
        if( ctxMenu ){
          ctxMenu.remove();
        }
      }
      
      // 이벤트 바인딩
      //document.addEventListener('contextmenu', handleCreateContextMenu, false);
      document.addEventListener('click', handleClearContextMenu, false);

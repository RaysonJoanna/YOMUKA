function handleButtonClick(e) {		 
		//예약현황조회
			
		e.preventDefault();
		
		//alert((e.target.id).substr(0,4));
		
		urlAddr = "/yomuka/board/freeBoard"
				     
	    var npage = document.getElementById("npage");
	    
	    //조회조건
	    var title = document.getElementById("title");
	    var memberid = document.getElementById("memberid");
	    	        	 
	    inParm = "?title=" + title.value + "&memberid=" + memberid.value + "&notice=N";
	    	    	    	    					
		if(e.target.id == "inquiry01"){ //조회 버튼
			document.location.href = urlAddr + inParm;
		}else if(e.target.id == "n1"){		//다음페이지
			document.location.href = urlAddr + inParm + "&npage=" + npage.value + "&npage2=n1";  	
		}else if(e.target.id == "n2"){		//마지막페이지
			document.location.href = urlAddr + inParm + "&npage=" + npage.value + "&npage2=n2";										
		}else if(e.target.id == "b1"){		//이전페이지
			document.location.href = urlAddr + inParm + "&npage=" + npage.value + "&npage2=b1";	
		}else if(e.target.id == "b2"){		//처음페이지
			document.location.href = urlAddr + inParm + "&npage=" + npage.value + "&npage2=b2";	
		}else if((e.target.id).substr(0,4) == "page"){	//특정페이지 번호
			document.location.href = urlAddr + inParm + "&npage=" + (e.target.id).substr(4) + "&npage2=page";
		}	
	};
	

$(function(){
	//조회조건 지정  	
    //직전 조회시 조회조건을 새 화면에 다시 세팅
	var ntitle = document.getElementById("ntitle");
	var nmemberid = document.getElementById("nmemberid");
				  		    	  
	if(nmemberid.value != null){		
		document.getElementById("memberid").value = nmemberid.value;
	}
	if(ntitle.value != null){		
		document.getElementById("ntitle").value = ntitle.value;
	}	
});
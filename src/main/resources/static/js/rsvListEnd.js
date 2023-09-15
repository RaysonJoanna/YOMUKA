function handleButtonClick(e) {		 		
		e.preventDefault();
		
		urlAddr = "/yomuka/rsv/rsvListEnd"
		     
	    var from = document.getElementById("From");
	    var to = document.getElementById("To");
	    var npage = document.getElementById("npage");
	    
	    inParm = "?From=" + from.value + "&To=" + to.value;
	    
        if(from.value =="" || to.value == "") {					
			alert("조회대상 예약일자를 입력해 주세요");					
		}else if(e.target.id == "inquiry01"){ //조회 버튼
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
	
	//진료결과 입력 부트스트랩 모달
	var myModal = new bootstrap.Modal($('#resultInput'));
		
	$('.btn.btn-primary.resultInput').click(function(){		
		data=this.value;		
		//alert(text);
		
		parts = data.split('$#');		
		$('#reserveid').val(parts[0]);
		$('input[name="resultgubun"]').val([parts[1]]);
		$('#resultcontent').val(parts[2]);
		if(parts[4] != "" && parts[4] != " "){
			$('#resultdate').val(parts[4]);
		}else{
			$('#resultdate').val(parts[3]);
		}
						
		myModal.show(); // 모달 열기
	});
	
	//진료결과 입력후 저장 버튼 클릭
	$('#resultSave').click(function(){
		url='/yomuka/rsv/result';
		type='PUT';
		data={
			"reserveid" 	: $('#reserveid').val(),
			"resultgubun" 	: $('input[name="resultgubun"]').val(),
		    "resultcontent" : $('#resultcontent').val(),
		    "resultdate" 	: $('#resultdate').val(),
		}	
				
		$.ajax({
			url 	: url,
			enctype	: 'multipart/form-data', 
			timeout	: 30000, 
			type	: type,
			data	: data,		
			success	: function(data){
						alert("정상적으로 저장 되었습니다"); 
						myModal.hide();
					  },			
			error	: function(data){alert("진료결과 등록시 오류가 발생했습니다");},					  
			complete : function(data){location.reload();}			
		})
	});
	
	$('.form-control.datepicker').datepicker({
		  format: 'yyyy-mm-dd', 	         
	      autoclose: true, 
	      calendarWeeks: false, 
	      clearBtn: false,          
	      title: '일자 선택', //캘린더 상단에 보여주는 타이틀
	      todayHighlight: true, //오늘 날짜에 하이라이팅 기능 기본값 :false	      
	      weekStart: 0, //달력 시작 요일 선택하는 것 기본값은 0인 일요일
	      language: 'ko', //달력의 언어 선택, 그에 맞는 js로 교체해줘야한다.	                    
	  	});	    
	   	
	  	
	var fromDate = document.getElementById("fromDate");       //직전 조회시 조회조건을 새 화면에 다시 세팅
	var toDate = document.getElementById("toDate");
		  		    	  
	if(fromDate.value =="") {
		$('#From').datepicker('setDate', '-30D');  		//진료기록조회 조회시작일자 기본값	
	}else{
		$('#From').datepicker('setDate', fromDate.value);  		//진료기록조회 조회시작일자 기본값
	}
	if(toDate.value =="") {
		$('#To').datepicker('setDate', '+7D');  	    //진료기록조회 조회종료일자 기본값	
	}else{
		$('#To').datepicker('setDate', toDate.value);  	    //진료기록조회 조회종료일자 기본값
	}
	
		
});
function handleButtonClick(e) {		 
		//예약현황조회
			
		e.preventDefault();
		
		//alert((e.target.id).substr(0,4));
		
		urlAddr = "/yomuka/rsv/rsvListStart"
				     
	    var npage = document.getElementById("npage");
	    
	    //조회조건
	    var from = document.getElementById("From");
	    var to = document.getElementById("To");
	    var hospitalid = document.getElementById("hospitalid");
	    var memberid = document.getElementById("memberid");
	    	        	 
	    var hospitalid2 = hospitalid.options[hospitalid.selectedIndex].value;
	    var memberid2 = memberid.options[memberid.selectedIndex].value;
	    
	    inParm = "?From=" + from.value + "&To=" + to.value + "&hospitalid=" + hospitalid2 + "&memberid=" + memberid2;
	    	    	    	    
        if(from.value =="" || to.value == "" || hospitalid2 == "" || memberid2 == "") {					
			alert("조회조건을 입력해 주세요");					
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
	
	//예약일자 입력 및 수정 부트스트랩 모달
	var myModal = new bootstrap.Modal($('#reserveInput'));
	
	$('.btn.btn-primary.reserveInput').click(function(){
			    		
		data=this.value;		
		//alert(text);
		
		parts = data.split('$#');		
		$('#preserveid').val(parts[0]);
		$('#phospitalid').val([parts[1]]);		
		$('#prsvdate').val(parts[2]);
		$('#prsvtime').val(parts[3]);
		$('#pcontent').val(parts[4]);
		$('#pmemberid').val(parts[5]);
						
			
		myModal.show(); // 모달 열기
	});
	
	//예약내용 입력후 저장 버튼 클릭
	$('#reserveSave').click(function(){	
		if($('#phospitalid').val() =="" || $('#prsvdate').val() =="" || $('#prsvtime').val() =="" || $('#pcontent').val() == "") {		
			alert("모든 항목을 입력해 주세요");					
		}else{					
			url='/yomuka/rsv/reserve';
			type='PUT';
			data={
				"reserveid" 	: $('#preserveid').val(),
				"hospitalid" 	: $('#phospitalid').val(),
			    "rsvdate" 		: $('#prsvdate').val(),
			    "rsvtime" 		: $('#prsvtime').val(),		   
			    "content" 		: $('#pcontent').val(),
			    "memberid" 		: $('#pmemberid').val()
			}		
			
			//alert(data["hospitalid"]);
					
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
				error	: function(data){alert("예약내용 등록시 오류가 발생했습니다");},					  
				complete : function(data){location.reload();}			
			})
	    }
	});
	
	//일자 선택(공통)
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
	  		  	
	//조회조건 지정  	
	var fromDate = document.getElementById("fromDate");       //직전 조회시 조회조건을 새 화면에 다시 세팅
	var toDate = document.getElementById("toDate");
	var nhospitalid = document.getElementById("nhospitalid");
	var nmemberid = document.getElementById("nmemberid");
				  		    	  
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
		
	if(nhospitalid.value != null){		
		document.getElementById("hospitalid").value = nhospitalid.value;
	}
	if(nmemberid.value != null){		
		document.getElementById("memberid").value = nmemberid.value;
	}
			
});
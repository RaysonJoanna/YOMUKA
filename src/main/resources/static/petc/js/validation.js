function validatePetInfo(){
	
	var petName = document.getElementById("petName");
	var petAge = document.getElementById("petAge");
	var petWeight = document.getElementById("petWeight");
	var petAgeUnit = document.getElementById("petAgeUnit");

	//반려동물 이름 유효성 체크
		if(petName.value.length<1 || petName.value.length>9){
			alert("이름은 최소 1글자에서 최대 8글자까지 입력 가능합니다");	
			petName.select();
			petName.focus();
			return false;
		} else if(!check(/^[가-힣a-zA-Z]+$/, petName, "이름은 한글과 영문만 입력 가능합니다")){
			petName.select();
			petName.focus();
			return false;
		} else if(!check(/^(?!^\s*$).*/,petName,"값을 입력해주세요")){
			petName.select();
			petName.focus();
			return false;
		}
	//반려동물 나이 유효성 체크
		if(isNaN(petAge.value)){
			alert("나이는 숫자만 입력 가능합니다");
			petAge.select();
			petAge.focus();
			return false;
		} else if(petAge.value.length<1 || petAge.value.length>3 || petAge.value <0){
			alert("나이는 1~99까지 입력 가능합니다");
			petAge.select();
			petAge.focus();
			return false;
		} else if(!check(/^(?!^\s*$).*/,petAge,"값을 입력해주세요")){
			petAge.select();
			petAge.focus();
			return false;
		}
		
	//반려동물 몸무게 유효성 체크
		if(petWeight.value<=0) {
			alert("몸무게를 입력해주세요");
			petWeight.select();
			petWeight.focus();
			return false;
		} else if(!check(/^\d+(\.\d{1,2})?$/, petWeight, "몸무게는 소수점 둘째자리까지만 입력 가능합니다")){
			return false;
		} else if(!check(/^(?!^\s*$).*/,petWeight,"값을 입력해주세요")){
			petWeight.select();
			petWeight.focus();
			return false;
		}
		

      	if(petAgeUnit.value == "개월"){
      		if(petAge.value >='12'){
      			alert("12개월 이상은 n살로 입력해주세요");
      			petAgeUnit.focus();
      			return false;
      		}
		}
		
		for(var i=0; i<petNameList.length; i++){
			if(inputName.val()==petNameList[i]){
				alert("같은 이름을 가진 반려동물이 있습니다!");
				inputName.focus();
				return false;
			}
    	}
		
	function check(regExp,e,msg) {
			if(regExp.test(e.value)){
				return true;
			}
			alert(msg);
			e.select();
			e.focus();
			return false;
	}
	document.pet.submit();
}
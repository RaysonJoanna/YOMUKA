function animal(what) {
	//what으로 받아온 내용을 animal에 입력
	const animal = $('.' + what);
	var animal2, animal3
	//what에 따라 닫을 종류 2개 설정
	if (what == 'cat') {
		animal2 = $('.dog');
		animal3 = $('.small');
	}
	else if (what == 'dog') {
		animal2 = $('.cat');
		animal3 = $('.small');
	}
	else {
		animal2 = $('.dog');
		animal3 = $('.cat');
	}

	//만약 animal 표시 형식이 inline이면 닫기
	if (animal.css('display').indexOf('inline') != -1) {
		animal.css('display', 'none');
	}
	//만약 animal 표시 형식이 none이면 열고 나머지 다 닫기
	else if (animal.css('display') === 'none') {
		animal.css('display', 'inline')
		animal2.css('display', 'none')
		animal3.css('display', 'none');
	}
}

//수량 증가, 감소
document.addEventListener('DOMContentLoaded', function() {
	const plusButton = document.getElementById('plusButton');
	const minusButton = document.getElementById('minusButton');
	const result = document.getElementById('result');
	const salePrice = document.getElementById('salePrice');

	let i = parseFloat(document.querySelector('#result').textContent);
	let isButtonDisabled = false; // 버튼 비활성화 상태를 저장하는 변수


	const priceElement = document.querySelector('.price');
	const Tcost = parseFloat(priceElement.textContent);
	const cost = parseFloat(priceElement.textContent.replace(/,/g, ''));

	function FinalTotalCost() {
		let totalcost = i * Tcost;
		let totalcostNum = i * cost;
		const formattedPrice = Intl.NumberFormat().format(totalcostNum);
		salePrice.textContent = formattedPrice + "원";
		totalPrice.value = totalcost;
	}

	plusButton.addEventListener('click', function() {
		if (!isButtonDisabled) {
			i++;
			result.textContent = i;
			quantity.value = i;
			FinalTotalCost();
			isButtonDisabled = true; // 버튼 비활성화
			setTimeout(function() {
				isButtonDisabled = false;
			}, 100);
		}
	});

	minusButton.addEventListener('click', function() {
		if (!isButtonDisabled && i > 1) {
			i--;
			result.textContent = i;
			quantity.value = i;
			FinalTotalCost();

			isButtonDisabled = true; // 버튼 비활성화
			setTimeout(function() {
				isButtonDisabled = false;
			}, 100);
		}
	});
});

// 000단위로 , 작성
document.addEventListener('DOMContentLoaded', function() {
	
	const mainPrices = document.querySelectorAll('.product-text');

	mainPrices.forEach(function(mPrice) {
		const mprice = parseFloat(mPrice.innerText);
		const formattedPrice = Intl.NumberFormat().format(mprice);
		mPrice.textContent = formattedPrice + "원";
	});
});

// 버튼 누르면 파라미터 제출용
function setProductType(productType) {
	// 파라미터 값을 설정
	document.getElementById("productTypeInput").value = productType;
	// 폼을 제출
	document.getElementById("productForm").submit();
}

function Input_PageURL(Index, gudokBool, Exist) {
	//현재 페이지값 설정
	var page = window.location.href;
	Index.value = page;
	//구독 여부 확인
	console.log("구독여부 : " + Exist)
	if (Exist) {
		//이미 구독이 되어있으면 취소하는 상황
		gudokBool.value = "false";
		alert("구독이 취소되었습니다.");
	}
	else {
		//새로운 구독 추가
		gudokBool.value = "true";
		alert("구독이 완료되었습니다.");
	}
}


function deletecheck() {
	var delArray = new Array();
	var checkconfirm = $('input[name="productName"]').toArray();
	for(var i=0; i<checkconfirm.length; i++){
		if(checkconfirm[i].checked){
			delArray.push(checkconfirm[i].value)
		}
	}
	
	if(delArray.length == 0){
		alert("선택된 품목이 없습니다.");
		
	}
}

function allcanCheck() {
	const check = $("#allcheck").prop("checked");
	if (check) {
		$('input[name="productName"]').each(function() {
			$(this).prop('checked', true);
		});
	} else{
		$('input[name="productName"]').each(function() {
			$(this).prop('checked', false);
		});
	};
}
	function check() {
		$('.checkBox').prop('checked', !$('#chechBox').prop('checked'));
	}
	
function customerSession(){
	session.getAttribute("loggedUser");
}
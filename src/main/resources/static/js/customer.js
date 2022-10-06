// 우편번호
function openDaumPostcode() {
	new daum.Postcode({
		oncomplete : function(data) {
			document.getElementById('signup-post').value = data.zonecode;
			document.getElementById('signup-address').value = data.address;
		}
	}).open();
}

// 비밀번호찾기 창
function pwFind() {
	window.open("pwFind", "비밀번호 찾기", "width=450, height=500");
}

// 비밀번호 찾기
$(function() {
	$("#mailtoken").hide();
});

function pwFindCheck() {
	if ($.trim($("#pwfind-id").val()) == "") {
		alert("아이디를 입력해주세요.");
		$("#pwfind-id").val("").focus();
		return false;
	}
	if ($.trim($("#pwfind-name").val()) == "") {
		alert("이름을 입력해주세요.");
		$("#pwfind-name").val("").focus();
		return false;
	}
}

// var result;
// function send() {
// $("#mailtoken").show();
//
// var email = $("#signup-email").val();
// var pattern = new RegExp(
// /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/);
//
// if (email == '') {
// alert("이메일 주소를 입력해주세요.");
// $("#signup-email").val("").focus();
// return false;
// }
//
// if (email.match(pattern) == null) {
// alert("이메일 주소 형식이 맞지 않습니다.");
// $("#signup-email").val("").focus();
// return false;
// }
//
// $.post("sendmail", {
// "email" : email
// }, function(data) {
// alert("인증번호가 발송되었습니다. 메일함을 확인해주세요.");
// result = data;
// });
// }
//
// function mailcheck() {
// var emailtoken = $("#signup-emailtoken").val();
//
// if (result == emailtoken) {
// alert("인증 성공");
// $("#mailtoken").hide();
// $("#signup-tel").focus();
//
// return false;
// } else {
// alert("인증 실패");
// $("#signup-emailtoken").val("").focus();
//
// return false;
// }
// }

// 회원가입 공백검사
var idchecks = false;

function check() {
	if ($.trim($("#signup-id").val()) == "") {
		alert("아이디를 입력해주세요.");
		$("#signup-id").val("").focus();
		return false;
	}else if ($.trim($("#signup-password").val()) == "") {
		alert("비밀번호를 입력해주세요.");
		$("#signup-password").val("").focus();
		return false;
	}else if ($.trim($("#signup-password-confirm").val()) == "") {
		alert("비밀번호 확인을 입력해주세요.");
		$("#signup-password-confirm").val("").focus();
		return false;
	}else if ($.trim($("#signup-password").val()) != $.trim($(
			"#signup-password-confirm").val())) {
		alert("비밀번호가 일치하지 않습니다.");
		$("#signup-password").val("");
		$("#signup-password-confirm").val("");
		$("#signup-password").focus();
		return false;
	}else if($("#signup-id").val() == $("#signup-password").val()){
        alert("아이디와 비밀번호가 같습니다");
        $("#signup-password").val("");
        $("#signup-password").focus();
        return false;
      }else if ($.trim($("#signup-name").val()) == "") {
		alert("이름을 입력해주세요.");
		$("#signup-name").val("").focus();
		return false;
	}else if ($.trim($("#signup-jumin1").val()) == "") {
			alert("주민번호 앞자리를 입력해주세요.");
			$("#signup-jumin1").val("").focus();			
			return false;
	}else if ($.trim($("#signup-jumin1").val()).length != 6) {
		alert("주민번호는 6자리로 입력해주세요.");
		$("#signup-jumin1").val("").focus();			
		return false;
	}else if ($.trim($("#signup-jumin2").val()) == "") {
		alert("주민번호 뒷자리를 입력해주세요.");
		$("#signup-jumin2").val("").focus();
		return false;
	}else if ($.trim($("#signup-jumin2").val()).length != 7) {
		alert("주민번호는 7자리로 입력해주세요.");
		return false;
	}else if ($.trim($("#signup-email").val()) == "") {
		alert("이메일을 입력해주세요.");
		$("#signup-email").val("").focus();
		return false;
	}
	/*
	 * if ($.trim($("#signup-emailtoken").val()) == "") { alert("인증번호를
	 * 입력해주세요."); $("#signup-emailtoken").val("").focus(); return false; }
	 */
	else if ($.trim($("#signup-tel").val()) == "") {
		alert("연락처를 입력해주세요.");
		$("#signup-tel").val("").focus();
		return false;
	}else if ($.trim($("#signup-post").val()) == "") {
		alert("우편번호를 입력해주세요.");
		$("#signup-post").val("").focus();
		return false;
	}else if ($.trim($("#signup-address").val()) == "") {
		alert("주소를 입력해주세요.");
		$("#signup-address").val("").focus();
		return false;
	}else if ($.trim($("#signup-address-detail").val()) == "") {
		alert("상세주소를 입력해주세요.");
		$("#signup-address-detail").val("").focus();
		return false;
	}else if (!idchecks) {
		alert("ID중복 버튼을 클릭해주세요.");
		$("#signup-id").focus();
		return false;
	}else{
		alert("회원가입이 완료되었습니다.");
	}
}
$('#joinmenu').click(function(){
	if(idchecks == 0){
		idcheck();
	}
})
$('#check').click(function(){
		check();
	});
// 우편번호 검색
function postSearch() {
	alert("우편번호 검색 버튼을 클릭해주세요.");
}

function postcheck() {
	window.open("postFind", "우편번호 검색",
			"width=420,height=200,scrollbars=yes");
}

// 아이디 중복 체크
function idcheck() {
	$("#idcheck").hide();
	
	var id = $("#signup-id").val();

	if ($.trim($("#signup-id").val()).length < 4 || $.trim($("#signup-id").val()).length > 12) {
		var newtext = '<font color="red">아이디는 4자 이상,12자 이하이어야 합니다.</font>';
		$("#idcheck").text('');
		$("#idcheck").show();
		$("#idcheck").append(newtext);
		$("#signup-id").val("").focus();
		return false;
	};
	if (!(validateUserid(id))) { // 입력 아이디 유효성 검사
		var newtext = '<font color="red">아이디는 영문 소문자, 숫자만 가능합니다.</font>';
		$("#idcheck").text('');
		$("#idcheck").show();
		$("#idcheck").append(newtext);
		$("#signup-id").val("").focus();
		return false;
	};

	// 아이디 중복확인
	$.ajax({
		// async : false, // true면 비동기방식을 사용하겠다는 의미.
		type : "POST",  // post방식으로 보낼 건데,
		data : {id:"id"},// id라는 데이터를 보낼 것이다.
		url : "idcheck?id="+id, // 컨트롤러의 idcheck를 탈 것이다.
		dataType: "json",  // json형식의 데이터를 보낼 것이다.
		contentType: "application/json; charset=UTF-8", // json형태로 보낼때는
														// application/json를
														// 붙여줘야한다.
		success : function(data) {
			if (data == 1) {
				var newtext = '<font color="red">중복 아이디입니다.</font>';
				$("#idcheck").text('');
				$("#idcheck").show();
				$("#idcheck").append(newtext);
				$("#signup-id").val('').focus();
				return false;
			} else {
				var newtext = '<font color="blue">사용 가능한 아이디입니다.</font>';
				$("#idcheck").text('');
				$("#idcheck").show();
				$("#idcheck").append(newtext);
				$("#signup-password").focus();
				idchecks = true;
			}
		},
		error : function(e) {
			alert("data error" + JSON.stringify(e));
		}
	});
};

function validateUserid(id) {
	var pattern = new RegExp(/^[a-z0-9_]+$/);

	return pattern.test(id);
};

// 가입버튼을 클릭했을 때 처리해줄 내용
let index = {
	    init: function () {
	        $("#joinmenu").on("click", ()=>{ // function(){} 대신 ()=>{} 를 쓴 이유
												// : this를 바인딩하기 위해서
	            this.save();
	        });
	    },

	    save: function () {
	        // alert('user의 save함수 호출됨');
	        let data = {
	        		id: $("#id").val(),
	        		pw: $("#pw").val(),
	            name: $("#name").val(),
	            jumin1: $("#jumin1").val(),
	            jumin2: $("#jumin2").val(),
	            email: $("#email").val(),
	            tel: $("#tel").val(),
	            post: $("#post").val(),
	            addr1: $("#addr1").val(),
	            addr2: $("#addr2").val()
	        };

	        // console.log(data);

	        // ajax 호출시 default가 비동기 호출 -> 순서 상관없음
	        // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
	        // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환
	        $.ajax({
	            // 회원가입 수행 요청
	            type: "POST",
	            url: "join",
	            data: JSON.stringify(data), // http body 데이터
	            contentType: "application/json; charset=utf-8", // body 데이터가 어떤
																// 타입인지 (MIME)
	            dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이
									// String(문자열), 만약 생긴게 json이라면 javascript
									// 오브젝트로 변경
	        }).done(function (resp) {
	            // 결과가 정상이면 done 실행
	            alert("회원가입이 완료되었습니다.");
	            // console.log(resp);
	            location.href = "main";
	        }).fail(function (error) {
	            // 실패하면 fail 실행
	            alert("회원가입이 실패하였습니다.");
	            alert(JSON.stringify(error));
	        });
	    }
	}
	// 로그인
	function checks() {
		var id = $("#signin-id").val();
		var pw = $("#signin-password").val();
		
		if ($.trim($("#signin-id").val()) == "") {
			alert("아이디를 입력해주세요.");
			$("#signin-id").val("").focus();
			return false;
		}
		if ($.trim($("#signin-password").val()) == "") {
			alert("비밀번호를 입력해주세요.");
			$("#signin-password").val("").focus();
			return false;
		}
	}
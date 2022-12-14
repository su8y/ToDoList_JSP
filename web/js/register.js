function register(e){
    fetch('http://localhost:8080/member', {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json'
        },
        // body 값이 직접적으로 서버에게 보내는 값
        body: JSON.stringify({
            id : e.form.id.value,
            pw : e.form.password.value,
            name : e.form.name.value,
            email : e.form.email.value,
        })
    }).then((response) => response.json()).then((data) => {
        console.log(data);
        if (data.status_code === "FAIL") {
            alert("회원가입을 축하합니다.");
        } else {
            alert("회원가입을 해주세요.");
        }
    });
}

var input = document.getElementById("id"); 
var pw = document.getElementById("pw"); 
var text = document.getElementById("text"); 
var email = document.getElementById("email"); 


input.value = null;
pw.value = null;
text.value = null;
email.value = null;




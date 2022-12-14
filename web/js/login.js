function login(login){
    fetch('http://localhost:8080/member', {
        method: 'PUT',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json'
        },
        // body 값이 직접적으로 서버에게 보내는 값
        body: JSON.stringify({
            id : login.form.id.value,
            pw : login.form.password.value,

        })
    }).then((response) => response.json()).then((data) => {
        console.log(data);
        if (data.status_code === "SUCCESS") {
            alert("로그인 성공");
            var username = id.value;
            sessionStorage.setItem("username", id.value);
            window.location.href ="http://localhost:8080/";
        } else {
            alert("로그인 실패");
        }

    });
    
}


// var login = "jess2";
// sessionStorage.setItem("name", "jess2");
// sessionStorage.getItem("name");
// var getValue = sessionStorage.getItem(login);
// console.log(getValue);










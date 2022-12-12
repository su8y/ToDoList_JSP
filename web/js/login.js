function register(login){
    fetch('http://localhost:8080/member', {
        method: 'get',
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
    });
}


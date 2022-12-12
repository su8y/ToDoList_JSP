function register(form){
    console.log(form.id.value);
    fetch('http://localhost:8080/member', {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json'
        },
        // body 값이 직접적으로 서버에게 보내는 값
        body: JSON.stringify({
            id : form.id.value,
            pw : form.password.value,
            name : form.name.value,
            email : form.email.value,
        })
    }).then((response) => response.json()).then((data) => {
        console.log(data);
    });
}


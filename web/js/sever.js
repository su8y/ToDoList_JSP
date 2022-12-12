fetch('http://127.0.0.1:5500/web/join.html?', {
    method: 'POST',
    cache: 'no-cache',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify({
        aaa: 'a1',
        bbb: 'b1'
    })
}).then((response) => response.json()).then((data) => {
    console.log(data);
});
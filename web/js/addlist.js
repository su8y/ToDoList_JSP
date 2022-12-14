
    function deleteToDo(event) {
        const form = event.target.parentElement;
        form.remove();
    }




function createE(){
    var text = document.getElementById("inputText").value;
    var tTag = document.querySelector("article");

    var addDiv = document.createElement("div");
    document.getElementById("inputText").value ="";
    addDiv.className = "qwe";
    addDiv.innerHTML = "<div class=\"\">\n" +
        "            <form action=\"\">\n" +
        "                <div>\n" +
        "                    <button>\n" +
        "                        V\n" +
        "                    </button>\n" +
        "                    <span>"+text+"</span>\n" +
        "                </div>\n" +
        "                <button>\n" +
        "                    X\n" +
        "                </button>\n" +
        "            </form>    \n" +
        "        </div>";

    tTag.prepend(addDiv);

    for (var i = 5; i>=addDiv.innerHTML; i++){

    }
    // api request /list method="post"
}
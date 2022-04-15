function getMsg(msg) {
    return `
        <div class="sent_msg">
            <p>${msg}</p>
            <span class="time_date"> 11:18 | Today</span>
        </div>
   `;
}

document.querySelector("#chat-outgoing-button").addEventListener("click", () => {
    const chatBox = document.querySelector("#chat-box");
    const msgInput = document.querySelector("#chat-outgoing-msg");

    const chatOutgoingBox = document.createElement("div");
    chatOutgoingBox.className = "outgoing_msg";

    chatOutgoingBox.innerHTML = getMsg(msgInput.value);
    chatBox.append(chatOutgoingBox);
    msgInput.value=""; // msg 송출 후 value 값을 클리어해주기
});

document.querySelector("#chat-outgoing-msg").addEventListener("keydown", (e) => {
    console.log(e.keyCode);
    if (e.keyCode === 13) {
       const chatBox = document.querySelector("#chat-box");
       const msgInput = document.querySelector("#chat-outgoing-msg");

       const chatOutgoingBox = document.createElement("div");
       chatOutgoingBox.className = "outgoing_msg";

       chatOutgoingBox.innerHTML = getMsg(msgInput.value);
       chatBox.append(chatOutgoingBox);
       msgInput.value=""; // msg 송출 후 value 값을 클리어해주기
    }
});
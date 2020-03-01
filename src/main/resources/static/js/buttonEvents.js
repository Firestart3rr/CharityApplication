// $(() =>{
//     let blockButtonTitle = $('#blockButton').innerText;
//     $('#blockButton').on('click', ()=> {
//         if(blockButtonTitle === "Zablokuj użytkownika"){
//             $('#blockButton').innerText = "Odblokuj użytkownika";
//         } else if(blockButtonTitle === "Odblokuj użytkownika"){
//             $('#blockButton').innerText = "Zablokuj użytkownika";
//         }
//     })
// })

// document.addEventListener("DOMContentLoaded", () => {
//     document
//         .querySelectorAll('#blockButton')
//         .forEach((el) => {
//             el.addEventListener("click", function () {
//                 let blockButtonTitle = document.querySelectorAll("#blockButton").innerText.toString();
//                 if (typeof blockButtonTitle == "Zablokuj użytkownika") {
//                     document.querySelectorAll('#blockButton').Text = "Odblokuj użytkownika";
//                 } else if (typeof blockButtonTitle == "Odblokuj użytkownika") {
//                     document.querySelectorAll('#blockButton').Text = "Zablokuj użytkownika";
//                 }
//             })
//         })
// })


document.addEventListener("DOMContentLoaded", () => {

    let checkIfEnabled = document.getElementById("blockButton").name

    function addDescriptionToLink(checkIfEnabled){
        if(checkIfEnabled){
            document.getElementById("blockButton").innerText="Zablokuj użytkownika"
        } else if(!checkIfEnabled){
            document.getElementById("blockButton").innerText="Odblokuj użytkownika"
        }
    }



    document.getElementById("blockButton").onclick=function(){
        let buttonTitle = document.getElementById("blockButton").innerText
        // document.getElementById("blockButton").innerText("Odblokuj");
        if(buttonTitle == "Odblokuj użytkownika"){
            this.innerText="Zablokuj użytkownika"
        } else if(buttonTitle == "Zablokuj użytkownika"){
            this.innerText="Odblokuj użytkownika"
        }
    }
})
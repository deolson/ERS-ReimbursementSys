console.log("employee!");

let tableCount = 0;
let myTableJSON;

window.onload = function() {
    ajaxGetUserReim();

    document
        .getElementById("createReimForm")
        .addEventListener("submit",submitReim);
}

function ajaxGetUserReim() {
    console.log("Starting Ajax");

    fetch('http://localhost:8080/proj1-ers/json/allUserReim')
        .then(function(response) {
            const convertedResponse = response.json();
            return convertedResponse;
            })
        .then(function(secondResponse) {
                tableCount = secondResponse.filter(ele => (ele.rStatus=="PENDING")).length;
                myTableJSON = secondResponse;
                console.log(secondResponse);
                addUserReim(secondResponse);
                fillHeadcards(secondResponse);
            })

    fetch('http://localhost:8080/proj1-ers/json/getUser')
    .then(function(response) {
        const convertedResponse = response.json();
        return convertedResponse;
        })
    .then(function(secondResponse) {
            console.log(secondResponse);
            firstname = secondResponse.firstName;
            lastname = secondResponse.lastName;
            document.getElementById("WelcomeUser").innerHTML = "Welcome "+firstname+" "+lastname;
        })
}

function addUserReim(objJSON) {
    for(let i = 0; i < objJSON.length; i++) {
        
        let newTR = document.createElement('tr');
        newTR.setAttribute("data-bs-toggle","collapse");
        newTR.setAttribute("href","#collapse"+i);
        newTR.setAttribute("aria-expanded","false");
        newTR.setAttribute("role","button");
        newTR.setAttribute("aria-controls","collapse"+i);

        let newTH = document.createElement('th');
        newTH.setAttribute("scope","row");
        let myTextTH = document.createTextNode(objJSON[i].reimbId);
        newTH.appendChild(myTextTH);

        let newTD = document.createElement('td');
        let myTextTD = document.createTextNode("$"+objJSON[i].reimbursAmount);
        newTD.appendChild(myTextTD);

        let newTD2 = document.createElement('td');
        let myTextTD2 = document.createTextNode(new Date(objJSON[i].submitted).toLocaleString());
        newTD2.appendChild(myTextTD2);

        let newTD3 = document.createElement('td');
        let myTextTD3 = document.createTextNode(objJSON[i].rType);
        newTD3.appendChild(myTextTD3);

        let newTD4 = document.createElement('td');
        let myTextTD4 = document.createTextNode(objJSON[i].rStatus);
        newTD4.appendChild(myTextTD4);

        let newTD5 = document.createElement('td');
        let myTextTD5;
        if(objJSON[i].resolver.firstName == null) {
            myTextTD5 = document.createTextNode("-");
        } else {
            myTextTD5 = document.createTextNode(new Date(objJSON[i].resolved).toLocaleString());
        }
        newTD5.appendChild(myTextTD5);

        let newTD7 = document.createElement('td');
        let myTextTD7;
        if(objJSON[i].resolver.firstName == null) {
            myTextTD7 = document.createTextNode("-");
        } else {
            myTextTD7 = document.createTextNode(objJSON[i].resolver.firstName+" "+objJSON[i].resolver.lastName);
        }
        newTD7.appendChild(myTextTD7);

        newTR.appendChild(newTH);
        newTR.appendChild(newTD);
        newTR.appendChild(newTD2);
        newTR.appendChild(newTD3);
        newTR.appendChild(newTD4);
        newTR.appendChild(newTD5);
        newTR.appendChild(newTD7);

        let newSelection = document.querySelector("#userReimTable")
        newSelection.appendChild(newTR);



        let newTR2 = document.createElement('tr');
        newTR2.setAttribute("class","collapse multi-collapse");
        newTR2.setAttribute("id","collapse"+i);

        let newTD6 = document.createElement('td');
        newTD6.setAttribute("colspan","100%");
        newTD6.setAttribute("text-align","center");
        let reimDes = objJSON[i].description;
        let myTextTD6;
        if(reimDes) {
            myTextTD6 = document.createTextNode(reimDes);
        } else {
            myTextTD6 = document.createTextNode("No Description Provided!");
        }

        newTD6.appendChild(myTextTD6);

        newTR2.appendChild(newTD6);
        newSelection.appendChild(newTR2);
    }
}


function fillHeadcards(objJSON){
    let total = 0;
    let openReimCount = 0;
    let closedReimCount = 0;
    let typeCount = [0,0,0,0];
    let types = ["LODGING","TRAVEL","FOOD","OTHER"];

    for(let i = 0; i < objJSON.length; i++) {
        if(objJSON[i].rStatus == "PENDING"){
            openReimCount++;
        }
        if(objJSON[i].rStatus == "DENIED" || objJSON[i].rStatus == "APPROVED"){
            closedReimCount++;
            if(objJSON[i].rStatus == "APPROVED"){
                total += objJSON[i].reimbursAmount;
            }
        }
        if(objJSON[i].rType == "LODGING"){
            typeCount[0]++;
        }
        if(objJSON[i].rType == "TRAVEL"){
            typeCount[1]++;
        }
        if(objJSON[i].rType == "FOOD"){
            typeCount[2]++;
        }
        if(objJSON[i].rType == "OTHER"){
            typeCount[3]++;
        }


    }
    
    let newSelection = document.querySelector("#openReim");
    let p = document.createElement('p');
    p.textContent = openReimCount;
    newSelection.appendChild(p);

    let newSelection2 = document.querySelector("#closedReim");
    let p2 = document.createElement('p');
    p2.textContent = closedReimCount;
    newSelection2.appendChild(p2);

    let newSelection3= document.querySelector("#totalIssued");
    let p3 = document.createElement('p');
    p3.textContent = "$"+total;
    newSelection3.appendChild(p3);

    let newSelection4 = document.querySelector("#mostCommon");
    let p4 = document.createElement('p');
    p4.textContent = types[findBiggestIndex(typeCount)];
    newSelection4.appendChild(p4);




}

function findBiggestIndex(array){
    let maxIndex = 0;
    let max = 0;
    for(let i = 0; i < array.length; i++){
        if(array[i]>max){
            max = array[i];
            maxIndex = i;
        }
    }
    return maxIndex;
}

function submitReim(event) {
    event.preventDefault();

    let form = document.getElementById('createReimForm');

    let reimPost = {
        "reimbursAmount": form.reimbursAmount.value,
        "rType": form.rType.value,
        "description": form.description.value
    };

    fetch("http://localhost:8080/proj1-ers/db/createReim", {
        method: "post",
        'headers': {
            'content-type': 'application/json'
        },
        'body': JSON.stringify(reimPost)
    });


    let count = tableCount++;
    let newTR = document.createElement('tr');
    newTR.setAttribute("data-bs-toggle","collapse");
    newTR.setAttribute("href","#collapse"+count);
    newTR.setAttribute("aria-expanded","false");
    newTR.setAttribute("role","button");
    newTR.setAttribute("aria-controls","collapse"+count);

    let newTH = document.createElement('th');
    newTH.setAttribute("scope","row");
    let myTextTH = document.createTextNode("-");
    newTH.appendChild(myTextTH);

    let newTD = document.createElement('td');
    let myTextTD = document.createTextNode("$"+form.reimbursAmount.value);
    newTD.appendChild(myTextTD);

    let newTD2 = document.createElement('td');
    let myTextTD2 = document.createTextNode(new Date().toLocaleString());
    newTD2.appendChild(myTextTD2);

    let newTD3 = document.createElement('td');
    let myTextTD3 = document.createTextNode(form.rType.value);
    newTD3.appendChild(myTextTD3);

    let newTD4 = document.createElement('td');
    let myTextTD4 = document.createTextNode("PENDING");
    newTD4.appendChild(myTextTD4);

    let newTD5 = document.createElement('td');
    let myTextTD5 = document.createTextNode("-");

    newTD5.appendChild(myTextTD5);

    let newTD7 = document.createElement('td');
    let myTextTD7 = document.createTextNode("-");

    newTD7.appendChild(myTextTD7);

    newTR.appendChild(newTH);
    newTR.appendChild(newTD);
    newTR.appendChild(newTD2);
    newTR.appendChild(newTD3);
    newTR.appendChild(newTD4);
    newTR.appendChild(newTD5);
    newTR.appendChild(newTD7);

    let newSelection = document.querySelector("#userReimTable")
    newSelection.appendChild(newTR);

    let newTR2 = document.createElement('tr');
    newTR2.setAttribute("class","collapse multi-collapse");
    newTR2.setAttribute("id","collapse"+count);

    let newTD6 = document.createElement('td');
    newTD6.setAttribute("colspan","100%");
    newTD6.setAttribute("text-align","center");
    let myTextTD6 = document.createTextNode(form.description.value);
    newTD6.appendChild(myTextTD6);
    newTR2.appendChild(newTD6);
    newSelection.appendChild(newTR2);


    openCard = document.getElementById("openReim");
    openCard.lastChild.innerHTML = tableCount;

}
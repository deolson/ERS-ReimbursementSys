console.log("manager!");
let reimJSON;
let firstname;
let lastname;

window.onload = function() {
    ajaxGetUserReim();
}

function ajaxGetUserReim() {
    console.log("Starting Ajax");

    fetch('http://localhost:8080/proj1-ers/json/allReim')
        .then(function(response) {
            const convertedResponse = response.json();
            return convertedResponse;
            })
        .then(function(secondResponse) {
                console.log(secondResponse);
                reimJSON = secondResponse;
                addReim(reimJSON);
                fillHeadcards(reimJSON);
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

function addReim(objJSON) {
    for(let i = 0; i < objJSON.length; i++) {
        
        let newTR = document.createElement('tr');
        newTR.setAttribute("data-bs-toggle","collapse");
        newTR.setAttribute("href","#collapse"+i);
        newTR.setAttribute("aria-expanded","false");
        newTR.setAttribute("role","button");
        newTR.setAttribute("aria-controls","collapse"+i);
        newTR.setAttribute("id","row"+i);

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

        let newTD8 = document.createElement('td');
        let myTextTD8 = document.createTextNode(objJSON[i].author.firstName+" "+objJSON[i].author.lastName)
        newTD8.appendChild(myTextTD8);

        let newTD5 = document.createElement('td');
        let myTextTD5;
        if(objJSON[i].resolved == null) {
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


        let newTD9 = document.createElement('td');
        
        if(objJSON[i].rStatus=="PENDING"){
            let newbutton = document.createElement('button');
            newbutton.setAttribute("id","abutton"+i);
            newbutton.setAttribute("type","submit");
            newbutton.setAttribute("class","btn btn-success")
            newbutton.setAttribute("onclick","resolveReimTrue(event)");
            
            let newSVG = document.createElement('svg');
            newSVG.setAttribute("xmlns","http://www.w3.org/2000/svg");
            newSVG.setAttribute("width","25");
            newSVG.setAttribute("height","25");
            newSVG.setAttribute("fill","currentColor");
            newSVG.setAttribute("class","bi bi-check2-circle");
            newSVG.setAttribute("viewBox","0 0 16 15");

            let newPath = document.createElement('path');
            newPath.setAttribute("d","M2.5 8a5.5 5.5 0 0 1 8.25-4.764.5.5 0 0 0 .5-.866A6.5 6.5 0 1 0 14.5 8a.5.5 0 0 0-1 0 5.5 5.5 0 1 1-11 0z")
            let newPath2 = document.createElement('path');
            newPath2.setAttribute("d","M15.354 3.354a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0l7-7z");

            newSVG.appendChild(newPath);
            newSVG.appendChild(newPath2);
            newbutton.appendChild(newSVG);
            newTD9.appendChild(newbutton);



            let newbutton2 = document.createElement('button');
            newbutton2.setAttribute("id","dbutton"+i);
            newbutton2.setAttribute("type","submit");
            newbutton2.setAttribute("class","btn btn-danger")
            newbutton2.setAttribute("onclick","resolveReimFalse(event)");
            
            let newSVG2 = document.createElement('svg');
            newSVG2.setAttribute("xmlns","http://www.w3.org/2000/svg");
            newSVG2.setAttribute("width","25");
            newSVG2.setAttribute("height","25");
            newSVG2.setAttribute("fill","currentColor");
            newSVG2.setAttribute("class","bi bi-x-circle");
            newSVG2.setAttribute("viewBox","0 0 16 15");

            let newPath3 = document.createElement('path');
            newPath3.setAttribute("d","M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z")
            let newPath4 = document.createElement('path');
            newPath4.setAttribute("d","M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z");

            newSVG2.appendChild(newPath3);
            newSVG2.appendChild(newPath4);
            newbutton2.appendChild(newSVG2);
            newTD9.appendChild(newbutton2);
        } else {
            newTD9.innerHTML = "-";
        }


        newTR.appendChild(newTH);
        newTR.appendChild(newTD);
        newTR.appendChild(newTD2);
        newTR.appendChild(newTD3);
        newTR.appendChild(newTD4);
        newTR.appendChild(newTD8);
        newTR.appendChild(newTD5);
        newTR.appendChild(newTD7);
        newTR.appendChild(newTD9);

        let newSelection = document.querySelector("#allReimTable")
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

        if(objJSON[i].rStatus=="PENDING") {
            document
            .getElementById("abutton"+i).parentElement
            .addEventListener("click", resolveReimTrue);
        
            document
                .getElementById("dbutton"+i).parentElement
                .addEventListener("click", resolveReimFalse);
        }

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

function resolveReimTrue(event) {
    event.stopPropagation();
    event.preventDefault();

    console.log(event);
    let row = document.getElementById(event.delegateTarget.id);
    let rowhidden = document.getElementById(event.delegateTarget.attributes[5].nodeValue);
    console.log(event.delegateTarget.attributes[5].nodeValue);
    row.remove();
    rowhidden.remove();


    console.log(row);

    let resolvedReimPost = {
        "reimbId": row.children[0].innerHTML,
        "rStatus" : "APPROVED"
    };

    console.log(resolvedReimPost);

    fetch("http://localhost:8080/proj1-ers/db/resolveReim", {
        method: "post",
        'headers': {
            'content-type': 'application/json'
        },
        'body': JSON.stringify(resolvedReimPost)
    });

    let table = document.getElementById("allReimTable");
    table.querySelectorAll('*').forEach(n => n.remove());
    reimJSON.forEach(function(item) {
        if(item.reimbId == resolvedReimPost.reimbId){
            item.rStatus = "APPROVED";
            item.resolved = Date();
            item.resolver.firstName = firstname;
            item.resolver.lastName = lastname;
        }
    })
    addReim(reimJSON);
    document.getElementById("openReim").lastChild.remove();
    document.getElementById("closedReim").lastChild.remove();
    document.getElementById("totalIssued").lastChild.remove();
    document.getElementById("mostCommon").lastChild.remove();
    fillHeadcards(reimJSON);

}

function resolveReimFalse(event) {
    event.stopPropagation();
    event.preventDefault();

    console.log(event);
    let row = document.getElementById(event.delegateTarget.id);
    let rowhidden = document.getElementById(event.delegateTarget.attributes[5].nodeValue);
    console.log(event.delegateTarget.attributes[5].nodeValue);
    row.remove();
    rowhidden.remove();


    console.log(row);

    let resolvedReimPost = {
        "reimbId": row.children[0].innerHTML,
        "rStatus" : "DENIED"
    };

    console.log(resolvedReimPost);

    fetch("http://localhost:8080/proj1-ers/db/resolveReim", {
        method: "post",
        'headers': {
            'content-type': 'application/json'
        },
        'body': JSON.stringify(resolvedReimPost)
    });

    let table = document.getElementById("allReimTable");
    table.querySelectorAll('*').forEach(n => n.remove());
    reimJSON.forEach(function(item) {
        if(item.reimbId == resolvedReimPost.reimbId){
            item.rStatus = "DENIED";
            item.resolved = Date();
            item.resolver.firstName = firstname;
            item.resolver.lastName = lastname;
        }
    });
    addReim(reimJSON);
    document.getElementById("openReim").lastChild.remove();
    document.getElementById("closedReim").lastChild.remove();
    document.getElementById("totalIssued").lastChild.remove();
    document.getElementById("mostCommon").lastChild.remove();
    fillHeadcards(reimJSON);

}

function listPending() {
    let table = document.getElementById("allReimTable");
    table.querySelectorAll('*').forEach(n => n.remove());
    let filterd = reimJSON.filter( element => element.rStatus == "PENDING");
    addReim(filterd);
}

function listDenied() {
    let table = document.getElementById("allReimTable");
    table.querySelectorAll('*').forEach(n => n.remove());
    let filterd = reimJSON.filter( element => element.rStatus == "DENIED");
    addReim(filterd);
}

function listApproved() {
    let table = document.getElementById("allReimTable");
    table.querySelectorAll('*').forEach(n => n.remove());
    let filterd = reimJSON.filter( element => element.rStatus == "APPROVED");
    addReim(filterd);
}

function viewAll() {
    let table = document.getElementById("allReimTable");
    table.querySelectorAll('*').forEach(n => n.remove());
    addReim(reimJSON);
}
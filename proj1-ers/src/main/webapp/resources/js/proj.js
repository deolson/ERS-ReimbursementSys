const createAccount = document.getElementById("createAccount");
const createAccountCol = document.getElementById("colmflex");
const createAccountCol2 = document.getElementById("colmflex2");
const login = document.getElementById("login");

function moveToCreate() {
    
    login.classList.add("animate__backOutLeft");
    const animated = document.querySelector('.animate__backOutLeft');

    animated.addEventListener('animationend', createControl);    
    
    function createControl() {
        createAccountCol.classList.add("offset-3","col-6");
        createAccountCol2.classList.remove("offset-3","col-6");
        createAccount.classList.add("animate__backInRight");
        createAccount.style.display = "flex";
        login.style.display = "none";
        login.classList.remove("animate__backOutLeft","animate__backInLeft");
        animated.removeEventListener('animationend', createControl); 
    }
}

function moveToLogin() {
    
    createAccount.classList.add("animate__backOutRight");
    
    const animated2 = document.querySelector('.animate__backOutRight');
    animated2.addEventListener('animationend', loginControl);   
    
    function loginControl() {
        createAccount.style.display = "none";
        login.style.display = "flex";
        login.classList.add("animate__backInLeft");
        createAccountCol.classList.remove("offset-3","col-6");
        createAccountCol2.classList.add("offset-3","col-6");
        createAccount.classList.remove("animate__backOutRight","animate__backInRight");
        animated2.removeEventListener('animationend', loginControl);
    }
}

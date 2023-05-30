const sideMenu = document.querySelector(".sidebar");
const menuBtn = document.querySelector("#menu-btn");
const closeBtn = document.querySelector("#close-btn");
const themeToggler = document.querySelector(".theme-toggler");
var userName = document.getElementById("userName");

userName.innerHTML = sessionStorage.NOME_USUARIO;


// menuBtn.addEventListener('click', () => {
//     sideMenu.style.display = 'block';
// })


// closeBtn.addEventListener('click', () =>{
//     sideMenu.style.display = 'none';
// })

themeToggler.addEventListener('click', () => {
    console.log("apertou!!");
    document.body.classList.toggle('dark-theme-variables');

    themeToggler.querySelector('span:nth-child(1)').classList.toggle('active');
    themeToggler.querySelector('span:nth-child(2)').classList.toggle('active');
})


// BOTOES



const addM = document.querySelector("#btn-add");
const delM = document.querySelector("#btn-delete")

// btnCancelar.addEventListener('click', () => {
//     modal.style.display = 'none'
// })

// addM.addEventListener('click', () => {
//     modal.style.display = 'block'
// })

// confirma.addEventListener('click', () => {
//     modal.style.display = 'none'
// })



// function add(){
//     const modal = document.querySelector(".adc-maq")
//     modal.style.display = 'block'
//  }




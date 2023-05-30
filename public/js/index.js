const sideMenu = document.querySelector("aside");
const menuBtn = document.querySelector("#menu-btn");
const closeBtn = document.querySelector("#close-btn");
const themeToggler = document.querySelector(".theme-toggler");

var userName = document.getElementById("userName");
userName.innerHTML = sessionStorage.NOME_USUARIO;

menuBtn.addEventListener('click', () => {
    sideMenu.style.display = 'block';
})


closeBtn.addEventListener('click', () =>{
    sideMenu.style.display = 'none';
})

themeToggler.addEventListener('click', () => {
    document.body.classList.toggle('dark-theme-variables');

    themeToggler.querySelector('span:nth-child(1').classList.toggle('active');
    themeToggler.querySelector('span:nth-child(1').classList.toggle('active');
})
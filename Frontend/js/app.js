const API_URL = "http://localhost:8080/api";

function getUser() {
    const user = localStorage.getItem("campusmartUser");
    return user ? JSON.parse(user) : null;
}

function setUser(user) {
    localStorage.setItem("campusmartUser", JSON.stringify(user));
}

function logout() {
    localStorage.removeItem("campusmartUser");
    localStorage.removeItem("campusmartCart");
    window.location.href = "index.html";
}

function requireLogin() {
    if (!getUser()) {
        window.location.href = "login.html";
    }
}

function showToast(message) {
    let toast = document.getElementById("toast");

    if (!toast) {
        toast = document.createElement("div");
        toast.id = "toast";
        toast.className = "toast";
        document.body.appendChild(toast);
    }

    toast.textContent = message;
    toast.style.display = "block";

    setTimeout(() => {
        toast.style.display = "none";
    }, 2500);
}

function updateNavbar() {

    const user = getUser();
    const loginArea = document.getElementById("loginArea");

    if (!loginArea) return;

    if (user) {
        loginArea.innerHTML = `
            <a href="profile.html">Hi, ${user.name.split(" ")[0]}</a>
            <button class="btn btn-outline" onclick="logout()">Logout</button>
        `;
    } else {
        loginArea.innerHTML = `
            <a href="login.html" class="nav-btn">Login</a>
        `;
    }
}

document.addEventListener("DOMContentLoaded", updateNavbar);
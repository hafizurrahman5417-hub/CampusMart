const AUTH_API = "http://localhost:8080/api/auth";

async function registerUser(event) {

    event.preventDefault();

    const user = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value,
        phone: document.getElementById("phone").value,
        hostel: document.getElementById("hostel").value,
        role: "STUDENT"
    };

    try {

        const response = await fetch(`${AUTH_API}/register`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        });

        const data = await response.json();

        if (!response.ok) {
            throw new Error(data.error || "Registration failed");
        }

        alert("Registration successful! Please login.");

        window.location.href = "login.html";

    } catch (error) {
        alert(error.message);
    }
}


async function loginUser(event) {

    event.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    try {

        const response = await fetch(`${AUTH_API}/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email,
                password
            })
        });

        const data = await response.json();

        if (!response.ok) {
            throw new Error(data.error || "Login failed");
        }

        localStorage.setItem(
            "campusmartUser",
            JSON.stringify(data)
        );

        window.location.href = "index.html";

    } catch (error) {
        alert(error.message);
    }
}
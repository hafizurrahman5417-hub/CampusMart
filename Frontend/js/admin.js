const ADMIN_API = "http://localhost:8080/api/admin";

async function loadAdminDashboard() {

    const user = getUser();

    if (!user || user.role !== "ADMIN") {
        alert("Admin access required.");
        window.location.href = "index.html";
        return;
    }

    try {

        const response = await fetch(`${ADMIN_API}/stats`);

        const stats = await response.json();

        document.getElementById("totalUsers").textContent =
            stats.totalUsers;

        document.getElementById("totalProducts").textContent =
            stats.totalProducts;

        document.getElementById("totalOrders").textContent =
            stats.totalOrders;

    } catch (error) {

        console.error(error);

        alert("Could not load admin dashboard.");
    }
}
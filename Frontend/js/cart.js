const CART_API = "http://localhost:8080/api/cart";

function getCart() {
    const cart = localStorage.getItem("campusmartCart");
    return cart ? JSON.parse(cart) : [];
}

function saveCart(cart) {
    localStorage.setItem("campusmartCart", JSON.stringify(cart));
}


async function addToCart(productId) {

    const user = getUser();

    if (!user) {
        alert("Please login to add products to cart.");
        window.location.href = "login.html";
        return;
    }

    try {

        const response = await fetch(`${CART_API}/add`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                userId: user.id,
                productId: productId,
                quantity: 1
            })
        });

        if (!response.ok) {
            throw new Error("Could not add product");
        }

        showToast("Added to cart!");

    } catch (error) {

        console.error(error);

        // Local fallback
        const cart = getCart();

        const existing = cart.find(
            item => item.productId === productId
        );

        if (existing) {
            existing.quantity++;
        } else {
            cart.push({
                productId: productId,
                quantity: 1
            });
        }

        saveCart(cart);

        showToast("Added to cart!");
    }
}


async function loadCart() {

    const user = getUser();
    const container = document.getElementById("cartItems");

    if (!user) {
        container.innerHTML = `
            <div class="empty-state">
                <h2>Please login</h2>
                <a href="login.html" class="btn btn-primary">
                    Login
                </a>
            </div>
        `;
        return;
    }

    try {

        const response = await fetch(
            `${CART_API}/${user.id}`
        );

        if (!response.ok) {
            throw new Error("Cart unavailable");
        }

        const cart = await response.json();

        displayCart(cart);

    } catch (error) {

        container.innerHTML = `
            <div class="empty-state">
                <h2>Unable to load cart</h2>
            </div>
        `;
    }
}


function displayCart(cart) {

    const container = document.getElementById("cartItems");

    if (!cart.length) {

        container.innerHTML = `
            <div class="empty-state">
                <h2>Your cart is empty 🛒</h2>
                <p>Find something useful for your campus life.</p>
                <a href="index.html" class="btn btn-primary">
                    Browse Products
                </a>
            </div>
        `;

        document.getElementById("cartTotal").textContent = "₹0";
        return;
    }

    let total = 0;

    container.innerHTML = cart.map(item => {

        const itemTotal = item.price * item.quantity;

        total += itemTotal;

        return `
            <div class="cart-item">

                <div>
                    <h3>Product #${item.productId}</h3>
                    <p>Quantity: ${item.quantity}</p>
                    <strong>₹${item.price}</strong>
                </div>

                <button
                    class="btn btn-outline"
                    onclick="removeCartItem(${item.id})"
                >
                    Remove
                </button>

            </div>
        `;

    }).join("");

    document.getElementById("cartTotal").textContent =
        `₹${total}`;
}


async function removeCartItem(id) {

    try {

        await fetch(`${CART_API}/${id}`, {
            method: "DELETE"
        });

        loadCart();

    } catch (error) {
        alert("Unable to remove item.");
    }
}
const PRODUCT_API = "http://localhost:8080/api/products";

async function loadProducts(url = PRODUCT_API) {

    const container = document.getElementById("productGrid");

    if (!container) return;

    container.innerHTML = `
        <div class="empty-state">
            <h2>Loading products...</h2>
        </div>
    `;

    try {

        const response = await fetch(url);

        if (!response.ok) {
            throw new Error("Could not load products");
        }

        const products = await response.json();

        displayProducts(products);

    } catch (error) {

        container.innerHTML = `
            <div class="empty-state">
                <h2>Unable to connect to CampusMart</h2>
                <p>Make sure the Spring Boot backend is running.</p>
            </div>
        `;
    }
}


function displayProducts(products) {

    const container = document.getElementById("productGrid");

    if (!products.length) {

        container.innerHTML = `
            <div class="empty-state">
                <h2>No products found</h2>
                <p>Try another search or category.</p>
            </div>
        `;

        return;
    }

    container.innerHTML = products.map(product => {

        const image = product.imageUrl
            ? `<img src="${product.imageUrl}" alt="${product.name}">`
            : "📦";

        return `
            <div class="product-card">

                <div class="product-image">
                    ${image}
                </div>

                <div class="product-info">

                    <span class="badge">
                        ${product.listingType || "SALE"}
                    </span>

                    <h3>${product.name}</h3>

                    <p>${product.description || "Campus marketplace item"}</p>

                    <div class="product-price">
                        ₹${product.price || 0}
                    </div>

                    <div class="product-meta">
                        <span>${product.category || "General"}</span>
                        <span>📍 ${product.location || "Campus"}</span>
                    </div>

                    <a
                        href="product.html?id=${product.id}"
                        class="btn btn-primary"
                        style="display:block;text-align:center"
                    >
                        View Product
                    </a>

                </div>

            </div>
        `;

    }).join("");
}


async function searchProducts() {

    const keyword = document
        .getElementById("searchInput")
        .value
        .trim();

    if (!keyword) {
        loadProducts();
        return;
    }

    loadProducts(
        `${PRODUCT_API}/search?keyword=${encodeURIComponent(keyword)}`
    );
}


function filterCategory(category) {

    loadProducts(
        `${PRODUCT_API}/category/${encodeURIComponent(category)}`
    );
}


function filterType(type) {

    loadProducts(
        `${PRODUCT_API}/type/${encodeURIComponent(type)}`
    );
}
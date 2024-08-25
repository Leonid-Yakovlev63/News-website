document.addEventListener('DOMContentLoaded', function () {
    const searchForm = document.getElementById('searchForm');
    const searchInput = document.getElementById('searchInput');
    const searchResults = document.getElementById('searchResults');

    searchInput.addEventListener('input', async function () {
        const query = searchInput.value.trim();
        
        if (query.length === 0) {
            searchResults.innerHTML = '';
            searchResults.style.display = 'none';
            return;
        }

        try {
            const response = await fetch(`http://127.0.0.1:8080/news/search?title=${encodeURIComponent(query)}`);
            const results = await response.json();
            displaySearchResults(results);
        } catch (error) {
            console.error('Error fetching search results:', error);
            searchResults.innerHTML = '';
            searchResults.style.display = 'none';
        }
    });

    searchResults.addEventListener('click', function (e) {
        const target = e.target.closest('li');
        if (target) {
            const newsId = target.getAttribute('data-id');
            window.location.href = `news-detail.html?id=${newsId}`;
        }
    });

    function displaySearchResults(results) {
        if (results.length === 0) {
            searchResults.innerHTML = '';
            searchResults.style.display = 'none';
        } else {
            searchResults.innerHTML = results.map(news => `
                <li data-id="${news.id}">
                    <h5>${news.title}</h5>
                    <p>${news.content.substring(0, 100)}...</p>
                </li>
            `).join('');
            searchResults.style.display = 'block';
        }
    }
});

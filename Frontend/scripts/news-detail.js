let fetchNewsDetail = async () => {
    const urlParams = new URLSearchParams(window.location.search);
    const newsId = urlParams.get('id');
    const url = `http://127.0.0.1:8080/news/${newsId}`;
    try {
        let response = await fetch(url);
        if (response.ok) {
            let news = await response.json();
            document.querySelector(".news-detail").innerHTML = `
                <h1>${news.title}</h1>
                <div class="category">
                    <h2>Категория:</h2>
                    <h2 style="color:${news.category.color}">${news.category.name}</h2>
                </div>
                <p>${news.content}</p>
            `;
        } else {
            document.querySelector(".news-detail").innerHTML = "404 Not Found";
        }
    } catch (error) {
        console.error('Error fetching news detail:', error);
        document.querySelector(".news-detail").innerHTML = "Произошла ошибка при получении информации о новостях.";
    }
}
fetchNewsDetail();

let fetchNews = async () => {
    const url = "http://127.0.0.1:8080/news";
    try {
        let response = await fetch(url);
        if (response.ok) {
            let newsList = await response.json();
            if (newsList.length === 0) {
                document.querySelector(".newsContainer").innerHTML = "Нет доступных новостей.";
                return;
            }

            let template = '';
            for (let i = 0; i < newsList.length; i++) {
                template += `
                <div class="news" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">${newsList[i].title}</h5>
                        <div class="category">
                            <h4>Категория:</h4>
                            <h4 style="color:${newsList[i].category.color}">${newsList[i].category.name}</h4>
                        </div>
                        <p class="card-text">${newsList[i].content}</p>
                        <a href="news-detail.html?id=${newsList[i].id}">Читать подробнее</a>
                    </div>
                </div>`;
            }
            document.querySelector(".newsContainer").innerHTML = template;
        } else {
            document.querySelector(".newsContainer").innerHTML = "404 Not Found";
        }
    } catch (error) {
        console.error('Error fetching news:', error);
        document.querySelector(".newsContainer").innerHTML = "Произошла ошибка при получении информации о новостях.";
    }
}   
fetchNews();
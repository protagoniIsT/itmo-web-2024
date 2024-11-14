<#-- @ftlvariable name="articles" type="ru.itmo.wp.model.domain.Article[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles">
        <template>
            <article>
                <div class="title"></div>
                <div class="author"></div>
                <div class="text"></div>
            </article>
        </template>

        <div class="noData">
            No articles.
        </div>
    </div>


    <script>
        $(function () {
            const $noData = $(".noData");
            $noData.text("Loading articles...");
            $.ajax({
                dataType: "json",
                data: {
                    action: "findArticles"
                },
                success: function(response) {
                    if (response["articles"].length === 0) {
                        $noData.text("No articles.");
                        return;
                    }
                    $noData.hide();
                    response["articles"].forEach(function(article) {
                        if (article["hidden"]) {
                            return;
                        }
                        $noData.hide();
                        const $article = $($(".articles template").prop("content")).clone();
                        $article.find(".title").text(article["title"]);
                        $article.find(".text").text(article["text"]);
                        const $auth = $article.find(".author");
                        ajax({action: "findUser", userId: article["userId"]},
                            function (response) {
                                $auth.text("By " + response.user.login + " on " + article["creationTime"]);
                            }
                        );
                        $(".articles").append($article);
                    });
                },
                type: "POST"
            });
        });
    </script>
</@c.page>
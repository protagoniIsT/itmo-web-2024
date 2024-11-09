package ru.itmo.web.hw4.util;

import ru.itmo.web.hw4.model.Post;
import ru.itmo.web.hw4.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mike Mirzayanov", User.Color.RED),
            new User(6, "pashka", "Pavel Mavrin", User.Color.GREEN),
            new User(9, "geranazavr555", "Georgiy Nazarov", User.Color.BLUE),
            new User(11, "tourist", "Gennady Korotkevich", User.Color.RED)
    );

    private static final List<Post> POSTS = Arrays.asList(
            new Post(1, "Hello, Codeforces", "Codeforces Round #510 (Div. 2) will start at Monday, September 17, 2018 at 11:05. The round will be\n" +
                    "rated for Div. 2 contestants (participants with the rating below 2100). Div. 1 participants can take\n" +
                    "a part out of competition as usual. This round is held on the tasks of the school stage All-Russian Olympiad of Informatics 2018/2019\n" +
                    "year in city Saratov. The problems were prepared by PikMike, fcspartakm, Ne0n25, BledDest, Ajosteen\n" +
                    "and Vovuh. Great thanks to our coordinator _kun_ for the help with the round preparation! I also\n" +
                    "would like to thank our testers DavidDenisov, PrianishnikovaRina, Decibit and Vshining.", 1),

            new Post(2,  "Hello, CF!", "New task arrived: BadTreap.",6),

            new Post(3,  "Привет, Codeforces!", "Благодаря поддержке Neapolis University Pafos, продолжается серия образовательных раундов." +
                    " Университет предлагает получение степени бакалавра в области компьютерных наук и искусственного интеллекта со стипендиями JetBrains. " +
                    "Получите передовые навыки в области искусственного интеллекта и машинного обучения, которые подготовят вас к востребованным техническим карьерам. " +
                    "Любопытно? Ознакомьтесь с учебной программой прямо сейчас. Доступно ограниченное количество стипендий. Не упустите свой шанс учиться в Европе бесплатно!",9),

            new Post(4,  "Hola, Codeforces!", "We're glad to invite you to take part in Codeforces Round 978 (Div. 2), which will start on воскресенье, 13 октября 2024 г. " +
                    "в 22:35. You will be given 5 problems and 2 hours to solve them. Some problems will be divided into subtasks.",1)
    );

    public static void addData(HttpServletRequest request, Map<String, Object> data) {
        data.put("users", USERS);
        data.put("posts", POSTS);
        data.put("currUri", request.getRequestURI());
        for (User user : USERS) {
            if (Long.toString(user.getId()).equals(request.getParameter("logged_user_id"))) {
                data.put("user", user);
            }
        }
    }
}

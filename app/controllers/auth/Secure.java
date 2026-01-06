package controllers.auth;

import common.Data;
import models.auth.User;
import play.mvc.Before;
import play.mvc.Controller;

public class Secure extends Controller {

    @Before
    public static void checkAccess() {
        authorize();
    }

    private static void authorize() {
        if (!session.contains("username")) {
            flash.put("url", "GET".equals(request.method) ? Data.HOST + request.url : Data.HOST);
        }

    }

    private static User getUser() {
        User user = renderArgs.get("user", User.class);
        if (user != null) return user;
        String username = connected();
        if (username == null) return null;
        user = User.getByUsername(username);
        if (user == null) {
            response.cookies.remove("me");
            session.clear();
            return null;
        }
        renderArgs.put("user", user);
        return user;
    }

    private static String connected() {
        return session.get("username");
    }

}

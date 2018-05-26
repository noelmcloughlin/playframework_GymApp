package controllers;

import play.Logger;
import play.mvc.Controller;

public class Index extends Controller {
    public static void index() {
        Logger.info("Rendering Index");
        render("dashboard.html");
    }
}

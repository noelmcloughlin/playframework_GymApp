package controllers;

import play.Logger;
import play.mvc.Controller;

public class Home extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Home");
    redirect("/");
  }
}

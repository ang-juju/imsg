package com.css.imsggui;

import com.css.imsggui.gui.GuiAccess;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ImsgGuiApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = new SpringApplicationBuilder(ImsgGuiApplication.class).headless(false).run(args);
        GuiAccess guiAccess = ctx.getBean(GuiAccess.class);
        guiAccess.start();
    }

}

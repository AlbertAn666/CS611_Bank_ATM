package edu.bu.homeworkteam.bankatm.pagesUI;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GuiConfiguration {

    @Bean
    //automatically run the method when Spring Application ready
    public GuiManager createGui(){
        GuiManager guiManager = GuiManager.getInstance();
        guiManager.startGui();
        return guiManager;
    }
}

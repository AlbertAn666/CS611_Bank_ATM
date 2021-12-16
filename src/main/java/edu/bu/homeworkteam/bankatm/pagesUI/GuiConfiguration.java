package edu.bu.homeworkteam.bankatm.pagesUI;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;

@Configuration
public class GuiConfiguration {

    @Bean
    //automatically run the method when Spring Application ready
    public JFrame startGui(){
        return new CustomerLoginFrame();
    }
}

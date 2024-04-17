package org.logger.logger.controllers;

import lombok.NoArgsConstructor;
import org.logger.logger.loggingFramework.LogStructure;

@NoArgsConstructor
public class Daddy {
    public String whoSYourDaddy(){
        LogStructure.logInfo("DADDY_EVENT", "Daddy");
        return "You Daddy!";
    }
}

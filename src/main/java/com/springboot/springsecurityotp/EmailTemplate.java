package com.springboot.springsecurityotp;


import com.springboot.springsecurityotp.exceptions.EmailTemplateException;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.nio.file.Files;
import java.util.Map;

public class EmailTemplate {

    private String template;

    private Map<String, String> replacementParams;

    public EmailTemplate() {
        try {
            this.template = loadTemplate();
        } catch (Exception e) {
            throw new EmailTemplateException("Exception While reading the template file");
        }
    }

    private String loadTemplate() throws Exception {
        File source = new ClassPathResource("templates/SendOtp.html").getFile();
        String content = "";
        try {
            content = new String(Files.readAllBytes(source.toPath()));

        }catch (Exception exception){
            throw new Exception("Could not read the template ");
        }
        return content;
    }
    public String getTemplate(Map<String, String> replacementParams) {
        String cTemplate = this.template;
        for (Map.Entry<String, String> entry : replacementParams.entrySet()) {
            cTemplate = cTemplate.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return cTemplate;
    }
}

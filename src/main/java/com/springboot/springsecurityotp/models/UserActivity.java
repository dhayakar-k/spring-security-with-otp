package com.springboot.springsecurityotp.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user-activity")
@Builder
public class UserActivity {
}

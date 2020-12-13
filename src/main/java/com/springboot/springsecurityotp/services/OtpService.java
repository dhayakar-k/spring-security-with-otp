package com.springboot.springsecurityotp.services;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class OtpService {

    private static final Integer EXPIRE_MINUTES = 4;

    private final LoadingCache<String, Integer> otpCache ;

    public OtpService() {
        super();
        otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINUTES, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) {
                        return 0;
                    }
                });
    }
   public int generateOtp(String key) {

       Random random = new Random();
       int otp = 100000 + random.nextInt(900000);
       log.info("Generated OTP is "+otp);
       otpCache.put(key, otp);
       return otp;
   }
   public int getOtp(String key) {
        try {
            return otpCache.get(key);
        }catch (Exception e) {
          log.info(e.getMessage());
          return 0;
        }
   }
   public void clearOtp(String key) {
        otpCache.invalidate(key);
   }
}

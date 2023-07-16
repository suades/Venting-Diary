package com.suad.venttome;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeAgo {

    public String getTimeAgo(long duration){
        Date now = new Date();

        long seconds = TimeUnit.MILLISECONDS.toSeconds(now.getTime() - duration);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - duration);
        long hours = TimeUnit.MILLISECONDS.toHours(now.getTime() - duration);
        long days = TimeUnit.MILLISECONDS.toDays(now.getTime() - duration);
        long years = days/365;

        if(seconds < 60){
            return "Just Now";
        }else if(minutes == 1){
            return "A Minute Ago";
        }else if(minutes > 1 && minutes < 60){
            return minutes + " Minutes Ago";
        }else if(hours == 1){
            return "An Hour Ago";
        }else if(hours > 1 && hours < 24){
            return hours + " Hours Ago";
        }else if(days == 1){
            return "A Day Ago";
        }else if(days > 1 && days < 365){
            return days + " Days Ago";
        }else if(days > 365){
            return "A Year Ago";
        }else if(days > 730){
            return "2 Years Ago";
        }else if(days == 1095){
            return "3 Years Ago";
        }else{
            return"More Than 3 Years Ago";
        }

    }


}

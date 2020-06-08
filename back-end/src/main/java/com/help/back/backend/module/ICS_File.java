package com.help.back.backend.module;

import com.help.back.backend.domain.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ICS_File {
    public List<Schedule> getSchedule(String url){
        List<Schedule> schedules = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new URL(url)
                                    .openStream()));
            String str;
            boolean stFlag = false, etFlag = false, cFlag = false, tFlag = false, stf = false, enf = false;
            String startTime = "", endTime = "", content = "", title = "";
            while ((str = br.readLine()) != null) {
                String[] strSplit = str.split(":");
                if("BEGIN".equals(strSplit[0])) {
                    stFlag = etFlag = tFlag = cFlag = stf = enf = false;
                    startTime = endTime = content = title = "";
                }
                else if("END".equals(strSplit[0])) {
                    if ((stFlag && etFlag && tFlag && cFlag) || (stf && enf && tFlag && cFlag)) {
                        schedules.add(new Schedule(title, content, startTime, endTime));
                        stFlag = etFlag = tFlag = cFlag = stf = enf = false;
                    }
                }
                if ("DTSTART;VALUE=DATE".equals(strSplit[0])) {
                    stf = true;
                    stFlag = false;
                    if (strSplit.length >= 2) {
                        String dateStr = strSplit[1];
                        LocalDateTime ldt = LocalDateTime.of(Integer.parseInt(dateStr.substring(0, 4)),
                                Integer.parseInt(dateStr.substring(4, 6)), Integer.parseInt(dateStr.substring(6, 8)),
                                0,0,0);
                        startTime = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    }
                } else if ("DTEND;VALUE=DATE".equals(strSplit[0])) {
                    enf = true;
                    etFlag = false;
                    if (strSplit.length >= 2) {
                        String dateStr = strSplit[1];
                        LocalDateTime ldt = LocalDateTime.of(Integer.parseInt(dateStr.substring(0, 4)),
                                Integer.parseInt(dateStr.substring(4, 6)), Integer.parseInt(dateStr.substring(6, 8)),
                                0,0,0).minusMinutes(1);;
                        endTime = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    }
                } else if ("DTSTART".equals(strSplit[0])) {
                    stFlag = true;
                    if (strSplit.length >= 2) {
                        String dateStr = strSplit[1];
                        LocalDateTime ldt = LocalDateTime.of(Integer.parseInt(dateStr.substring(0, 4)),
                                Integer.parseInt(dateStr.substring(4, 6)), Integer.parseInt(dateStr.substring(6, 8)),
                                Integer.parseInt(dateStr.substring(9, 11)), Integer.parseInt(dateStr.substring(11, 13)),
                                Integer.parseInt(dateStr.substring(13, 15))).plusHours(9);
                        ;
                        startTime = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    }
                } else if ("DTEND".equals(strSplit[0])) {
                    etFlag = true;
                    if (strSplit.length >= 2) {
                        String dateStr = strSplit[1];
                        LocalDateTime ldt = LocalDateTime.of(Integer.parseInt(dateStr.substring(0, 4)),
                                Integer.parseInt(dateStr.substring(4, 6)), Integer.parseInt(dateStr.substring(6, 8)),
                                Integer.parseInt(dateStr.substring(9, 11)), Integer.parseInt(dateStr.substring(11, 13)),
                                Integer.parseInt(dateStr.substring(13, 15))).plusHours(9);
                        ;
                        endTime = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    }
                } else if ("SUMMARY".equals(strSplit[0])) {
                    tFlag = true;
                    if (strSplit.length >= 2)
                        title = strSplit[1];
                } else if ("DESCRIPTION".equals(strSplit[0])) {
                    cFlag = true;
                    if (strSplit.length >= 2)
                        content = strSplit[1];
                }

            }
            return schedules;
        }catch (Exception e){
            return schedules;
        }
    }
}

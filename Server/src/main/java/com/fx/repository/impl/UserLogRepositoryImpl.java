package com.fx.repository.impl;

import com.fx.model.UserLog;
import com.fx.repository.UserLogRepository;
import com.fx.util.ResultMessage;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Description:
 * Created by Hanxinhu at 14:24 2018/6/21/021
 */
public class UserLogRepositoryImpl implements UserLogRepository {
    Gson gson = new Gson();
    public UserLogRepositoryImpl(){
        File file = new File("./data/log.txt");
      try {
          if(!file.exists())
              file.createNewFile();
      }catch (Exception e){
          e.printStackTrace();
      }
    }
    @Override
    public ResultMessage addUserLog(UserLog userLog) {
        writeUserLog(userLog);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage updateUserLog(UserLog userLog) {
        return null;
    }

    @Override
    public List<UserLog> findUserLogByTime(String time) {
       List<UserLog> userLogs = readAllUserLog();
       List<UserLog> log = new ArrayList<>();
       for (UserLog u: userLogs
             ) {
            if(u.getTime().equals(time))
                log.add(u);
        }
        return log;
    }

    @Override
    public List<UserLog> findUserLogByUser(String username) {
        List<UserLog> userLogs = readAllUserLog();
        List<UserLog> log = new ArrayList<>();
        for (UserLog u: userLogs
            ) {
            if(u.getUsername().equals(username))
                log.add(u);
        }
        return log;
    }

    @Override
    public List<UserLog> findUserLogByTimeAndAction(String time, String action) {
        List<UserLog> userLogs = readAllUserLog();
        List<UserLog> log = new ArrayList<>();
        for (UserLog u: userLogs
            ) {
            if(u.getTime().equals(time) && u.getAction().equals(action))
                log.add(u);
        }
        return log;
    }

    @Override
    public List<UserLog> findUserLogByAction(String action) {
        List<UserLog> userLogs = readAllUserLog();
        List<UserLog> log = new ArrayList<>();
        for (UserLog u: userLogs
            ) {
            if( u.getAction().equals(action))
                log.add(u);
        }
        return log;
    }

    private List<UserLog> readAllUserLog(){
        ArrayList<UserLog> logs = new ArrayList<>();
        try{
            File file = new File("./data/log.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String s = scanner.nextLine();
                UserLog log = gson.fromJson(s,UserLog.class);
                logs.add(log);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return logs;
    }

    private void writeAllUserLog(List<UserLog> logs){
        try{
            File file = new File("./data/log.txt");
            PrintWriter pw = new PrintWriter(file);
            for (int i = 0; i < logs.size(); i++) {
                pw.println(gson.toJson(logs.get(i)));
            }
            pw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void  writeUserLog(UserLog userLog){
        try{
            File file = new File("./data/log.txt");
            PrintWriter pw = new PrintWriter(new FileWriter(file),true);
            pw.println(gson.toJson(userLog));
            pw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

<<<<<<< HEAD
package com.fx.service.impl;

import com.fx.bean.*;
import com.fx.counting.NormalDistribution;
import com.fx.model.Mission;
import com.fx.model.User;
import com.fx.repository.MissionRepository;
import com.fx.repository.UserRepository;
import com.fx.repository.impl.MissionRepositoryImpl;
import com.fx.repository.impl.UserRepositoryImpl;
import com.fx.service.AnalysisService;
import com.fx.util.TimeUtil;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkpad on 2018/4/1.
 */
@Service
public class AnalysisServiceImpl implements AnalysisService {

    UserRepository userRepository = new UserRepositoryImpl();
    MissionRepository missionRepository = new MissionRepositoryImpl();
    /**
     * 获得不同等级的工作者数
     * @return 代表不同等级的用户数目。一般是从1开始，到n结束，目前还没确定n这个上限,注意0一般是没有用的
     */
    public List<UserLevelChart> getWorkerLevelChart(){

        List<User> list = userRepository.findUserByType("Worker");

        List<UserLevelChart> levels = new ArrayList<UserLevelChart>();

        String[] names = new String[]{"大众用户","黄金用户","铂金用户","钻石用户","星耀用户"};

        for(int i=1;i<=5;i++){
            levels.add(new UserLevelChart(0,names[i-1]));
        }
        int max;
        for(int i=0;i<=list.size()-1;i++){
            for(int j=0;j<=levels.size()-1;j++){
                if(levels.get(j).getName().equals(names[list.get(i).getLevel()])){
                    levels.get(j).setValue(levels.get(j).getValue()+1);
                }
            }
        }
        return levels;
    }

    /**
     * 获得不同等级发布者数
     * @return 代表不同等级的用户数目。一般是从1开始，到n结束，目前还没确定n这个上限,注意0一般是没有用的
     */
    public List<UserLevelChart> getRequestorLevelChart(){

        List<User> list = userRepository.findUserByType("Requestor");

        List<UserLevelChart> levels = new ArrayList<UserLevelChart>();

        String[] names = new String[]{"大众用户","黄金用户","铂金用户","钻石用户","星耀用户"};

        for(int i=1;i<=5;i++){
            levels.add(new UserLevelChart(0,names[i-1]));
        }
        int max;
        for(int i=0;i<=list.size()-1;i++){
            for(int j=0;j<=levels.size()-1;j++){
                if(levels.get(j).getName().equals(names[list.get(i).getLevel()])){
                    levels.get(j).setValue(levels.get(j).getValue()+1);
                }
            }
        }
        return levels;
    }

    /**
     * 获得工作者的地理位置图表
     * @return
     */
    public UserLocationChart getWorkerLocationChart(){

        return null;
    }

    /**
     * 获得发布者的地理位置图表
     * @return
     */
    public UserLocationChart getRequestorLocationChart(){

        return null;
    }

    public int getWorkerNumber(){

        return userRepository.findUserByType("Worker").size();
    }

    public int getRequestorNumber(){

        return userRepository.findUserByType("Requestor").size();
    }

    @Override
    public MissionMonthChart getMissionMonthChart() {
        List<List<Mission>> missions = new ArrayList<>();
       List<Mission> missions1 =  missionRepository.findMissionByBeginAndEnd("2018-01-01","2018-01-31");
       List<Mission> missions2 =  missionRepository.findMissionByBeginAndEnd("2018-02-01","2018-02-28");
        List<Mission> missions3 =  missionRepository.findMissionByBeginAndEnd("2018-03-01","2018-03-31");
        List<Mission> missions4 =  missionRepository.findMissionByBeginAndEnd("2018-04-01","2018-04-30");
        List<Mission> missions5 =  missionRepository.findMissionByBeginAndEnd("2018-05-01","2018-05-31");
        List<Mission> missions6 =  missionRepository.findMissionByBeginAndEnd("2018-06-01","2018-06-30");

        missions.add(missions1);
        missions.add(missions2);
        missions.add(missions3);
        missions.add(missions4);
        missions.add(missions5);
        missions.add(missions6);

        //a1保存已完成数
         int[] a1 = new int[]{0,0,0,0,0,0};
        int[] a2 = new int[]{0,0,0,0,0,0};
        for(int i=0;i<=missions.size()-1;i++){
            for(int j=0;j<=missions.get(i).size()-1;j++){
                if(missions.get(i).get(j).getCurrentNumber()>=missions.get(i).get(j).getMaxNumber()){
                    a1[i]++;
                }
                else{
                    a2[i]++;
                }
            }
        }

        int[] a3 = new int[]{0,0,0,0,0,0};
        for(int i=0;i<=a3.length-1;i++){
            a3[i] = missions.get(i).size();
        }

        MissionMonthChart missionMonthChart = new MissionMonthChart(a1,a2,a3);


        return missionMonthChart;
    }

    public List<List<Integer>> getBoxChart(){

        //System.out.println("123");
        //List<List<U>>
        List<Integer> result = new ArrayList<>();
        List<User> list = userRepository.findUserByType("Worker");

        List<List<Integer>> levels = new ArrayList<>();

        String[] names = new String[]{"大众用户","黄金用户","铂金用户","钻石用户","星耀用户"};

        for(int i=1;i<=5;i++){
            levels.add(new ArrayList<>());
        }
        //int max;
        for(int i=0;i<=list.size()-1;i++){
            levels.get(list.get(i).getLevel()).add(list.get(i).getLevel());
        }

       return levels;

//        List<List<Integer>> key = new ArrayList<>();
//
//        for(int i=1;i<=5;i++){
//            key.add(new ArrayList<>());
//        }
//        key.get(0).add(10);
//        key.get(0).add(20);
//        key.get(0).add(34);
//        key.get(0).add(47);
//
//        key.get(1).add(56);
//        key.get(1).add(64);
//        key.get(1).add(78);
//        key.get(1).add(90);
//
//        key.get(2).add(90);
//        key.get(2).add(98);
//        key.get(2).add(101);
//        key.get(2).add(108);
//
//        key.get(3).add(117);
//        key.get(3).add(140);
//        key.get(3).add(150);
//        key.get(3).add(180);
//
//        key.get(4).add(300);
//        key.get(4).add(110);
//        key.get(4).add(130);
//        key.get(4).add(140);
//
//        return key;





    }

    public float getPredictChart(){

       //String str1 = "123",str2 = "456";
        List<User> list = userRepository.findUserByType("Worker");

        list = quickSortByTime(list,0,list.size()-1);

        List<User> samples = new ArrayList<>();

        for(int i=0;i<=list.size()-1;i++){
            if((i+1)>list.size()-1||!list.get(i).getLatestSignIn().equals(list.get(i+1).getLatestSignIn())){

                samples.add(list.get(i));
            }
            int index =0;
            while(list.get(i+index).getLatestSignIn().equals(list.get(i+index+1).getLatestSignIn())){
                index++;
                //i++;
            }
            for(int j=0;j<=index/2-1;j++){
                samples.add(list.get(i+j));
            }
            i = i+index;
        }

        int active = 0;
        /**
         * 记录活跃数量
         */
        TimeUtil current = new TimeUtil();
        for(int i=0;i<=samples.size()-1;i++){


            if(current.minusDay(7).toString().compareTo(samples.get(i).getLatestSignIn())==1){
                active++;
            }
        }


        NormalDistribution normalDistribution = new NormalDistribution();

        float p=active/(samples.size());

        int n=active;

        int num =n/2;

        float k = (float)((num-n*p)/Math.sqrt(n*p*(1-p)));

        float result = 1-normalDistribution.selfCaculate(k);



        return result;
    }

    public List<User> quickSort(List<User> a,int start,int end) {

        /**
         * 这边要全部改成标注数
         */
        int base = a.get(end).getExp();
        //start一旦等于end，就说明左右两个指针合并到了同一位置，可以结束此轮循环。
        while (start < end) {
            while (start < end && a.get(start).getExp() <= base)
                //从左边开始遍历，如果比基准值小，就继续向右走
                start++;
            //上面的while循环结束时，就说明当前的a[start]的值比基准值大，应与基准值进行交换
            if (start < end) {
                //交换
                User temp = a.get(start);
                a.set(start, a.get(end));
                a.set(end, temp);
                //交换后，此时的那个被调换的值也同时调到了正确的位置(基准值右边)，因此右边也要同时向前移动一位
                end--;
            }
            while (start < end && a.get(end).getExp() >= base)
                //从右边开始遍历，如果比基准值大，就继续向左走
                end--;
            //上面的while循环结束时，就说明当前的a[end]的值比基准值小，应与基准值进行交换
            if (start < end) {
                //交换
                User temp = a.get(start);
                a.set(start, a.get(end));
                a.set(end, temp);
                //交换后，此时的那个被调换的值也同时调到了正确的位置(基准值左边)，因此左边也要同时向后移动一位
                start++;
            }

        }
        return a;
    }

    public List<User> quickSortByTime(List<User> a,int start,int end){
        /**
         * 这边要全部改成Time
         */
        String base = a.get(end).getLatestSignIn();
        //start一旦等于end，就说明左右两个指针合并到了同一位置，可以结束此轮循环。
        while (start < end) {
            while (start < end && a.get(start).getLatestSignIn().compareTo( base)==-1)
                //从左边开始遍历，如果比基准值小，就继续向右走
                start++;
            //上面的while循环结束时，就说明当前的a[start]的值比基准值大，应与基准值进行交换
            if (start < end) {
                //交换
                User temp = a.get(start);
                a.set(start, a.get(end));
                a.set(end, temp);
                //交换后，此时的那个被调换的值也同时调到了正确的位置(基准值右边)，因此右边也要同时向前移动一位
                end--;
            }
            while (start < end && a.get(start).getLatestSignIn().compareTo( base)!=-1)
                //从右边开始遍历，如果比基准值大，就继续向左走
                end--;
            //上面的while循环结束时，就说明当前的a[end]的值比基准值小，应与基准值进行交换
            if (start < end) {
                //交换
                User temp = a.get(start);
                a.set(start, a.get(end));
                a.set(end, temp);
                //交换后，此时的那个被调换的值也同时调到了正确的位置(基准值左边)，因此左边也要同时向后移动一位
                start++;
            }

        }
        return a;
    }

    public static void main(String[] args){
//        User u1 = new User();
//        u1.setLatestSignIn("2018-06-10");
//        User u2 = new User();
//        u2.setLatestSignIn("2018-06-07");
//        User u3 = new User();
//        u3.setLatestSignIn("2018-06-08");
//
//        List<User> list = new ArrayList<>();
//
//        list.add(u1);
//        list.add(u2);
//        list.add(u3);
//
//        AnalysisServiceImpl k = new AnalysisServiceImpl();
//
//        k.quickSortByTime(list,0,2);
//
//        for(int i=0;i<=2;i++){
//            System.out.println(list.get(i).getLatestSignIn());
//        }

        int active = 10;
        int samples = 20;
        float p=(float)0.02;

        int n=400;

        int num =1;

        NormalDistribution normalDistribution = new NormalDistribution();
        float k = (float)((num-n*p)/Math.sqrt(n*p*(1-p)));

        float result = 1-normalDistribution.selfCaculate(k);

        System.out.println(result);
    }
}
=======
package com.fx.service.impl;

import com.fx.bean.LineChart;
import com.fx.bean.MissionMonthChart;
import com.fx.bean.UserLevelChart;
import com.fx.bean.UserLocationChart;
import com.fx.counting.NormalDistribution;
import com.fx.model.Mission;
import com.fx.model.User;
import com.fx.model.UserLog;
import com.fx.repository.MissionRepository;
import com.fx.repository.UserLogRepository;
import com.fx.repository.UserRepository;
import com.fx.repository.impl.MissionRepositoryImpl;
import com.fx.repository.impl.UserLogRepositoryImpl;
import com.fx.repository.impl.UserRepositoryImpl;
import com.fx.service.AnalysisService;
import com.fx.util.TimeUtil;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by thinkpad on 2018/4/1.
 */
@Service
public class AnalysisServiceImpl implements AnalysisService {

    UserRepository userRepository = new UserRepositoryImpl();
    MissionRepository missionRepository = new MissionRepositoryImpl();
    UserLogRepository userLogRepository = new UserLogRepositoryImpl();

    /**
     * 获得不同等级的工作者数
     *
     * @return 代表不同等级的用户数目。一般是从1开始，到n结束，目前还没确定n这个上限,注意0一般是没有用的
     */
    public List<UserLevelChart> getWorkerLevelChart() {

        List<User> list = userRepository.findUserByType("Worker");

        List<UserLevelChart> levels = new ArrayList<UserLevelChart>();

        String[] names = new String[]{"大众用户", "黄金用户", "铂金用户", "钻石用户", "星耀用户"};

        for (int i = 1; i <= 5; i++) {
            levels.add(new UserLevelChart(0, names[i - 1]));
        }
        int max;
        for (int i = 0; i <= list.size() - 1; i++) {
            for (int j = 0; j <= levels.size() - 1; j++) {
                if (levels.get(j).getName().equals(names[list.get(i).getLevel()])) {
                    levels.get(j).setValue(levels.get(j).getValue() + 1);
                }
            }
        }
        return levels;
    }

    /**
     * 获得不同等级发布者数
     *
     * @return 代表不同等级的用户数目。一般是从1开始，到n结束，目前还没确定n这个上限,注意0一般是没有用的
     */
    public List<UserLevelChart> getRequestorLevelChart() {

        List<User> list = userRepository.findUserByType("Requestor");

        List<UserLevelChart> levels = new ArrayList<UserLevelChart>();

        String[] names = new String[]{"大众用户", "黄金用户", "铂金用户", "钻石用户", "星耀用户"};

        for (int i = 1; i <= 5; i++) {
            levels.add(new UserLevelChart(0, names[i - 1]));
        }
        int max;
        for (int i = 0; i <= list.size() - 1; i++) {
            for (int j = 0; j <= levels.size() - 1; j++) {
                if (levels.get(j).getName().equals(names[list.get(i).getLevel()])) {
                    levels.get(j).setValue(levels.get(j).getValue() + 1);
                }
            }
        }
        return levels;
    }

    /**
     * 获得工作者的地理位置图表
     *
     * @return
     */
    public UserLocationChart getWorkerLocationChart() {

        return null;
    }

    /**
     * 获得发布者的地理位置图表
     *
     * @return
     */
    public UserLocationChart getRequestorLocationChart() {

        return null;
    }

    public int getWorkerNumber() {

        return userRepository.findUserByType("Worker").size();
    }

    public int getRequestorNumber() {

        return userRepository.findUserByType("Requestor").size();
    }

    @Override
    public MissionMonthChart getMissionMonthChart() {
        List<List<Mission>> missions = new ArrayList<>();
        List<Mission> missions1 = missionRepository.findMissionByBeginAndEnd("2018-01-01", "2018-01-31");
        List<Mission> missions2 = missionRepository.findMissionByBeginAndEnd("2018-02-01", "2018-02-28");
        List<Mission> missions3 = missionRepository.findMissionByBeginAndEnd("2018-03-01", "2018-03-31");
        List<Mission> missions4 = missionRepository.findMissionByBeginAndEnd("2018-04-01", "2018-04-30");
        List<Mission> missions5 = missionRepository.findMissionByBeginAndEnd("2018-05-01", "2018-05-31");
        List<Mission> missions6 = missionRepository.findMissionByBeginAndEnd("2018-06-01", "2018-06-30");

        missions.add(missions1);
        missions.add(missions2);
        missions.add(missions3);
        missions.add(missions4);
        missions.add(missions5);
        missions.add(missions6);

        //a1保存已完成数
        int[] a1 = new int[]{0, 0, 0, 0, 0, 0};
        int[] a2 = new int[]{0, 0, 0, 0, 0, 0};
        for (int i = 0; i <= missions.size() - 1; i++) {
            for (int j = 0; j <= missions.get(i).size() - 1; j++) {
                if (missions.get(i).get(j).getCurrentNumber() >= missions.get(i).get(j).getMaxNumber()) {
                    a1[i]++;
                } else {
                    a2[i]++;
                }
            }
        }

        int[] a3 = new int[]{0, 0, 0, 0, 0, 0};
        for (int i = 0; i <= a3.length - 1; i++) {
            a3[i] = missions.get(i).size();
        }

        MissionMonthChart missionMonthChart = new MissionMonthChart(a1, a2, a3);


        return missionMonthChart;
    }

    public List<Integer> getBoxChart() {

        //List<List<U>>
        List<Integer> result = new ArrayList<>();
        List<User> list = userRepository.findUserByType("Worker");

        List<List<User>> levels = new ArrayList<>();

        String[] names = new String[]{"大众用户", "黄金用户", "铂金用户", "钻石用户", "星耀用户"};

        for (int i = 1; i <= 5; i++) {
            levels.add(new ArrayList<>());
        }
        //int max;
        for (int i = 0; i <= list.size() - 1; i++) {
            levels.get(list.get(i).getLevel()).add(list.get(i));
        }

        for (int i = 0; i <= levels.size() - 1; i++) {
            levels.set(i, quickSort(levels.get(i), 0, levels.get(i).size() - 1));
        }

        for (int i = 0; i <= levels.size() - 1; i++) {
            List<User> mid = levels.get(i);
            int size = mid.size() - 1;
            /**
             * 这里需要改成标注数
             */
            result.add(mid.get(0).getLevel());
            result.add(mid.get(size / 4).getLevel());
            result.add(mid.get(size / 2).getLevel());
            result.add(mid.get(3 * size / 2).getLevel());
            result.add(mid.get(size).getLevel());
        }

        List<Integer> key = new ArrayList<>();

        key.add(10);
        key.add(20);
        key.add(34);
        key.add(47);

        key.add(56);
        key.add(64);
        key.add(78);
        key.add(90);

        key.add(90);
        key.add(98);
        key.add(101);
        key.add(108);

        key.add(117);
        key.add(140);
        key.add(150);
        key.add(180);

        key.add(10);
        key.add(10);
        key.add(10);
        key.add(10);

        return key;


    }

    public float getPredictChart() {

        //String str1 = "123",str2 = "456";
        List<User> list = userRepository.findUserByType("Worker");

        list = quickSortByTime(list, 0, list.size() - 1);

        List<User> samples = new ArrayList<>();

        for (int i = 0; i <= list.size() - 1; i++) {
            if ((i + 1) > list.size() - 1 || true/**!!!**/) {

            }
            int index = 0;
            while (/****/list.get(i).getExp() == 0) {
                index++;
                //i++;
            }
            for (int j = 0; j <= index / 2 - 1; j++) {
                samples.add(list.get(i + j));
            }
            i = i + index;
        }

        int active = 0;
        /**
         * 记录活跃数量
         */
        for (int i = 0; i <= samples.size() - 1; i++) {
            active++;
        }


        NormalDistribution normalDistribution = new NormalDistribution();

        float p = active / (samples.size() * 7);

        int n = 0;

        int num = 1;

        float k = (float) ((num - n * p) / Math.sqrt(n * p * (1 - p)));

        float result = 1 - normalDistribution.selfCaculate(k);

        float mid = (float) 0.9987;

        return mid;
    }

    public List<User> quickSort(List<User> a, int start, int end) {

        /**
         * 这边要全部改成标注数
         */
        int base = a.get(end).getExp();
        //start一旦等于end，就说明左右两个指针合并到了同一位置，可以结束此轮循环。
        while (start < end) {
            while (start < end && a.get(start).getExp() <= base)
                //从左边开始遍历，如果比基准值小，就继续向右走
                start++;
            //上面的while循环结束时，就说明当前的a[start]的值比基准值大，应与基准值进行交换
            if (start < end) {
                //交换
                User temp = a.get(start);
                a.set(start, a.get(end));
                a.set(end, temp);
                //交换后，此时的那个被调换的值也同时调到了正确的位置(基准值右边)，因此右边也要同时向前移动一位
                end--;
            }
            while (start < end && a.get(end).getExp() >= base)
                //从右边开始遍历，如果比基准值大，就继续向左走
                end--;
            //上面的while循环结束时，就说明当前的a[end]的值比基准值小，应与基准值进行交换
            if (start < end) {
                //交换
                User temp = a.get(start);
                a.set(start, a.get(end));
                a.set(end, temp);
                //交换后，此时的那个被调换的值也同时调到了正确的位置(基准值左边)，因此左边也要同时向后移动一位
                start++;
            }

        }
        return a;
    }

    public List<User> quickSortByTime(List<User> a, int start, int end) {
        /**
         * 这边要全部改成Time
         */
        String base = a.get(end).getAvatar();
        //start一旦等于end，就说明左右两个指针合并到了同一位置，可以结束此轮循环。
        while (start < end) {
            while (start < end && a.get(start).getAvatar().compareTo(base) == -1)
                //从左边开始遍历，如果比基准值小，就继续向右走
                start++;
            //上面的while循环结束时，就说明当前的a[start]的值比基准值大，应与基准值进行交换
            if (start < end) {
                //交换
                User temp = a.get(start);
                a.set(start, a.get(end));
                a.set(end, temp);
                //交换后，此时的那个被调换的值也同时调到了正确的位置(基准值右边)，因此右边也要同时向前移动一位
                end--;
            }
            while (start < end && a.get(start).getAvatar().compareTo(base) != -1)
                //从右边开始遍历，如果比基准值大，就继续向左走
                end--;
            //上面的while循环结束时，就说明当前的a[end]的值比基准值小，应与基准值进行交换
            if (start < end) {
                //交换
                User temp = a.get(start);
                a.set(start, a.get(end));
                a.set(end, temp);
                //交换后，此时的那个被调换的值也同时调到了正确的位置(基准值左边)，因此左边也要同时向后移动一位
                start++;
            }

        }
        return a;
    }

    /**
     * 得到总用户数近30天的变化图
     *
     * @return
     */
    @Override
    public LineChart getMemberLine() {
        List<UserLog> logs = userLogRepository.findUserLogByAction(UserLog.REGISTER);

        TimeUtil timeUtil = new TimeUtil();
        TimeUtil startTime = timeUtil.minusDay(29);
        ArrayList<String> x = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            x.add(startTime.addDay(i).toString());
        }
        int [] numbers = new int[30];
        for (int i = 0; i < logs.size(); i++) {
            String time = logs.get(i).getTime();
            int index = startTime.IntervalDay(new TimeUtil(time));
            if(index<=0||index>=29)
                numbers[0]++;
            else
                numbers[index]++;
        }
        for (int i = 0; i < 30 - 1; i++) {
            numbers[i+1] += numbers[i];
        }
        ArrayList<Integer> y = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            y.add(numbers[i]);
        }
        LineChart lineChart = new LineChart();
        lineChart.setY(y);
        lineChart.setX(x);
        return lineChart;
    }

    /**
     * 得到新增用户近30天的变化图
     *
     * @return
     */
    @Override
    public LineChart getNewMemberLine() {
        TimeUtil timeUtil = new TimeUtil();
        TimeUtil startTime = timeUtil.minusDay(29);
        ArrayList<String> x = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            x.add(startTime.addDay(i).toString());
        }
        ArrayList<Integer> y = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < 30; i++) {
            List<UserLog> userLogs = userLogRepository.findUserLogByTimeAndAction(x.get(i), UserLog.REGISTER);
            for (int j = 0; j < userLogs.size(); j++) {
                set.add(userLogs.get(j).getUsername());
            }
            y.add(set.size());
            set.clear();
        }
        LineChart lineChart = new LineChart();
        lineChart.setX(x);
        lineChart.setY(y);
        return lineChart;
    }

    /**
     * 得到近30天活跃用户的变化图
     */
    @Override
    public LineChart getActiveMemberLine() {
        TimeUtil timeUtil = new TimeUtil();
        TimeUtil startTime = timeUtil.minusDay(29);
        ArrayList<String> x = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            x.add(startTime.addDay(i).toString());
        }
        ArrayList<Integer> y = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < 30; i++) {
            List<UserLog> userLogs = userLogRepository.findUserLogByTime(x.get(i));
            for (int j = 0; j < userLogs.size(); j++) {
                set.add(userLogs.get(j).getUsername());
            }
            y.add(set.size());
            set.clear();
        }
        LineChart lineChart = new LineChart();
        lineChart.setX(x);
        lineChart.setY(y);
        return lineChart;
    }

    /**
     * 得到推荐命中率的图
     *
     * @return
     */
    @Override
    public LineChart getRecommendRate() {
        return null;
    }

    /**
     * 得到总体推荐平均的权重值
     *
     * @return
     */
    @Override
    public int[] getRecommendWeight() {
        return new int[0];
    }

    /**
     * 得到目标用户的总体推荐权重的用户值
     *
     * @param username
     * @return
     */
    @Override
    public int[] getRecommendWeight(String username) {
        return new int[0];
    }

    /**
     * work的热力图
     *
     * @param username
     * @return
     */
    @Override
    public LineChart getWorkerChart(String username) {
        TimeUtil timeUtil = new TimeUtil();
        TimeUtil startTime = timeUtil.minusDay(29);
        ArrayList<String> x = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            x.add(startTime.addDay(i).toString());
        }
        ArrayList<Integer> y = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String time = startTime.addDay(i).toString();
            List<UserLog> logs = userLogRepository.findUserLogByUsernameAndActionAndTime(username,
                UserLog.WORK,time);
            y.add(logs.size());
        }
        LineChart lineChart = new LineChart();
        lineChart.setX(x);
        lineChart.setY(y);
        return lineChart;
    }

    /**
     * requestor
     *
     * @param username
     * @return
     */
    @Override
    public LineChart getRequestorChart(String username) {
        TimeUtil timeUtil = new TimeUtil();
        TimeUtil startTime = timeUtil.minusDay(29);
        ArrayList<String> x = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            x.add(startTime.addDay(i).toString());
        }
        ArrayList<Integer> y = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String time = startTime.addDay(i).toString();
            List<UserLog> logs = userLogRepository.findUserLogByUsernameAndActionAndTime(username,
                UserLog.RELEASE,time);
            y.add(logs.size());
        }
        LineChart lineChart = new LineChart();
        lineChart.setX(x);
        lineChart.setY(y);
        return lineChart;
    }
}
>>>>>>> a718624ad56adcea65b83f63b0217ece8d075176

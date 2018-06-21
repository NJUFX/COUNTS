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
//        List<User> list = userRepository.findUserByType("Worker");
//
//        List<List<User>> levels = new ArrayList<>();
//
//        String[] names = new String[]{"大众用户","黄金用户","铂金用户","钻石用户","星耀用户"};
//
//        for(int i=1;i<=5;i++){
//            levels.add(new ArrayList<>());
//        }
//        //int max;
//        for(int i=0;i<=list.size()-1;i++){
//            levels.get(list.get(i).getLevel()).add(list.get(i));
//        }
//
//        for(int i=0;i<=levels.size()-1;i++){
//            levels.set(i,quickSort(levels.get(i),0,levels.get(i).size()-1));
//        }
//
//        for(int i=0;i<=levels.size()-1;i++){
//            List<User> mid = levels.get(i);
//            int size = mid.size()-1;
//            /**
//             * 这里需要改成标注数
//             */
//            result.add(mid.get(0).getLevel());
//            result.add(mid.get(size/4).getLevel());
//            result.add(mid.get(size/2).getLevel());
//            result.add(mid.get(3*size/2).getLevel());
//            result.add(mid.get(size).getLevel());
//        }

        List<List<Integer>> key = new ArrayList<>();

        for(int i=1;i<=5;i++){
            key.add(new ArrayList<>());
        }
        key.get(0).add(10);
        key.get(0).add(20);
        key.get(0).add(34);
        key.get(0).add(47);

        key.get(1).add(56);
        key.get(1).add(64);
        key.get(1).add(78);
        key.get(1).add(90);

        key.get(2).add(90);
        key.get(2).add(98);
        key.get(2).add(101);
        key.get(2).add(108);

        key.get(3).add(117);
        key.get(3).add(140);
        key.get(3).add(150);
        key.get(3).add(180);

        key.get(4).add(300);
        key.get(4).add(110);
        key.get(4).add(130);
        key.get(4).add(140);

        return key;





    }

    public float getPredictChart(){

//       //String str1 = "123",str2 = "456";
//        List<User> list = userRepository.findUserByType("Worker");
//
//        list = quickSortByTime(list,0,list.size()-1);
//
//        List<User> samples = new ArrayList<>();
//
//        for(int i=0;i<=list.size()-1;i++){
//            if((i+1)>list.size()-1||true/**!!!**/){
//
//            }
//            int index =0;
//            while(/****/list.get(i).getExp()==0){
//                index++;
//                //i++;
//            }
//            for(int j=0;j<=index/2-1;j++){
//                samples.add(list.get(i+j));
//            }
//            i = i+index;
//        }
//
//        int active = 0;
//        /**
//         * 记录活跃数量
//         */
//        for(int i=0;i<=samples.size()-1;i++){
//            active++;
//        }
//
//
//        NormalDistribution normalDistribution = new NormalDistribution();
//
//        float p=active/(samples.size()*7);
//
//        int n=0;
//
//        int num =1;
//
//        float k = (float)((num-n*p)/Math.sqrt(n*p*(1-p)));
//
//        float result = 1-normalDistribution.selfCaculate(k);

        float mid = (float)0.9987;

        return mid;
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
        String base = a.get(end).getAvatar();
        //start一旦等于end，就说明左右两个指针合并到了同一位置，可以结束此轮循环。
        while (start < end) {
            while (start < end && a.get(start).getAvatar().compareTo( base)==-1)
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
            while (start < end && a.get(start).getAvatar().compareTo( base)!=-1)
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
}
